package com.hfocean.uavportal.weixin.verification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.httpclient.HttpHelper;
import com.hfocean.common.httpclient.ResponseContent;
import com.hfocean.common.httpclient.WebServiceHelper;
import com.hfocean.common.redis.RedisService;
import com.hfocean.uavportal.weixin.bean.ConfigVO;
import com.hfocean.uavportal.weixin.bean.TicketVO;
import com.hfocean.uavportal.weixin.bean.TokenVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* 获取微信相关信息
* @author Leslie.Lam
* @create 2017-06-25 11:46
**/
@Service("wechatService")
public class WechatService {

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.secret}")
    private String appSecret;

//    @Value(("${serviceToken}"))
//    private String serviceToken;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static Log log = LogFactory.getLog(WechatService.class);

    @Autowired(required = false)
    private RedisService redisService;

    //获取验证token,有效期2个小时
    public TokenVO getToken() throws Exception{
        TokenVO tk;
        String key = "_wx_access_token";
        String token = redisService.get(key);
        if(null!=token) {
            tk = objectMapper.readValue(token, TokenVO.class);
        }else {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret;
            ResponseContent rc = HttpHelper.getUrlRespContent(url);

            Map<String,String> map = objectMapper.readValue(rc.getContent("utf-8"), Map.class);
            String t ;
            if(map.get("errcode")==null){
                t = map.get("access_token");
            }else{
                throw new RuntimeException(String.format("获取access_token失败,%s",map.toString()));
            }
            tk = new TokenVO();
            tk.setToken(t);
            redisService.set(key, objectMapper.writeValueAsString(tk),7100);//7100秒后过期
        }
        return tk;
    }

    //获取验证ticket,有效期2个小时
    public TicketVO getTicket() throws Exception{
        TicketVO tk;
        String key = "_wx_access_tickey";
        String tickey = redisService.get(key);
        if(null!=tickey){
            tk=objectMapper.readValue(tickey,TicketVO.class);
        }else {
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("access_token", this.getToken().getToken()));
            params.add(new BasicNameValuePair("type", "jsapi"));

            String result = WebServiceHelper.webServiceGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket", params);
            Map<String,String> map = objectMapper.readValue(result, Map.class);
            String t = "";
            try{
                if("ok".equals(map.get("errmsg"))){
                    t = map.get("ticket");
                }
            }
            catch(Exception e){
                log.error("获取ticket失败"+e);
                throw new RuntimeException("获取ticket失败"+e);
            }
            tk = new TicketVO();
            tk.setTicket(t);
            redisService.set(key, objectMapper.writeValueAsString(tk),7100);//7100秒后过期
        }
        return tk;
    }

    //需要调用微信js-sdk方法的页面都得进行验证
    public ConfigVO getConfig(String url) throws Exception{

        Date nowDate = new Date(System.currentTimeMillis());

        String jsapi_ticket = this.getTicket().getTicket();
        String nonceStr = createNonceStr();
        String timestamp  = createTimestamp();
        String string;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;
        log.debug(string);

        //shar1加密
        signature = WebServiceHelper.shar1(string);

        ConfigVO config = new ConfigVO();

        config.setAppId(appId);
        config.setNonceStr(nonceStr);
        config.setToken(getToken().getToken());
        config.setTicket(jsapi_ticket);
        config.setSignature(signature);
        config.setTimestamp(timestamp);
        config.setValidity(7100);
        config.setCreatDate(nowDate);
        config.setExpirationDate(getExpirationDate(nowDate, 7100));

        return config;
    }

    public Date getExpirationDate(Date data, Integer second){
        Date expirationDate = null;
        if(data!=null){
            Calendar calendar  = Calendar.getInstance();
            calendar.setTime(data);
            calendar.add(Calendar.SECOND, second);
            expirationDate = calendar.getTime();
        }
        return expirationDate;
    }

    private static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    private static String createNonceStr() {
        return UUID.randomUUID().toString();
    }

    public String checkSignature(String signature, String timestamp, String nonce, String echostr){
        //字典排序
        List<String> list = new ArrayList<>();
//    	list.add(serviceToken);
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);
        String sortString = list.get(0)+list.get(1)+list.get(2);

        //shar1加密
        String mySignature = WebServiceHelper.shar1(sortString);

        //比较，判断服务器是否验证成功
        String objecString = "";
        if (signature.equals(mySignature))objecString = echostr;
        return objecString;
    }

    //每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期
    public String getOpenId(String code) throws Exception{

        String openId;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("appid", appId));
        params.add(new BasicNameValuePair("secret",appSecret));
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));

        String json = WebServiceHelper.webServiceGet("https://api.weixin.qq.com/sns/oauth2/access_token", params);

        Map<String,String> map=objectMapper.readValue(json,Map.class);
        try{
            openId = map.get("openid");
        }
        catch(Exception e){
            log.error("获取openId失败"+e);
            throw new BaseBusinessException(BaseBusinessError.NOT_FOUND.getCode(),"获取openId失败"+e);
        }
        return openId;
    }
}
