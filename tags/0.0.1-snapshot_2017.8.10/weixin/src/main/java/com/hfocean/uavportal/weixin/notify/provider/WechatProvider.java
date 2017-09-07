package com.hfocean.uavportal.weixin.notify.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.httpclient.HttpHelper;
import com.hfocean.common.httpclient.ResponseContent;
import com.hfocean.common.notify.NotifyManager;
import com.hfocean.common.util.AppContextHelper;
import com.hfocean.common.util.VerdictUtil;
import com.hfocean.uavportal.weixin.notify.po.MsgTemp;
import com.hfocean.uavportal.weixin.notify.repository.MsgTempRepository;
import com.hfocean.uavportal.weixin.notify.template.ParamData;
import com.hfocean.uavportal.weixin.notify.template.WechatTemp;
import com.hfocean.uavportal.weixin.verification.WechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WechatProvider implements NotifyManager {

	private static final transient Logger log = LoggerFactory.getLogger(WechatProvider.class);

    private final static transient ObjectMapper objectMapper=new ObjectMapper();

	private transient WechatService wechatService= AppContextHelper.getBean(WechatService.class);

	private transient MsgTempRepository msgTempRepository=AppContextHelper.getBean(MsgTempRepository.class);

    private Integer type;

    private Object[] param;

	private String target;

	public WechatProvider(String target, Integer type, Object...param) {
		this.target = target;
        this.type=type;
		this.param = param;
	}
	
	@Override
	public String doNotify() throws Exception{
        String result;
        MsgTemp temp = msgTempRepository.findOne(Example.of(new MsgTemp(type)));

        //将参数填入消息模板并生成对象
        WechatTemp wechatTemp = objectMapper.readValue(String.format(temp.getContentValue(), param).replaceAll("\\\\",""), WechatTemp.class);

        if(temp==null){
			log.error("找不到微信消息模板");
			return null;
		}
        //组装微信推送消息体
        Map<String, Object> body = new HashMap<>();
        List<String> keywords = wechatTemp.getKeywords();
        if(!VerdictUtil.isValidCollection(keywords)||keywords.size()<2) throw new BaseBusinessException(BaseBusinessError.PARAMETER_ERROR);
        body.put("first",new ParamData(wechatTemp.getFirst()));
        for (int i=0;i<keywords.size();i++){
            body.put("keyword"+(i+1),new ParamData(wechatTemp.getKeywords().get(i)));
        }
        body.put("remark",new ParamData(wechatTemp.getRemark()));

		try {
			Map<String,Object> map = new HashMap<>();
			map.put("touser", target);
			map.put("template_id", temp.getTempId());
			map.put("topcolor", "#FF0000");
			map.put("data", body);

			String ACCESS_TOKEN = wechatService.getToken().getToken();
			
			log.debug("--->获取access_token值为:"+ACCESS_TOKEN);
			
			log.debug("开始--->推送微信消息");
			
			String jsonBody = objectMapper.writeValueAsString(map);
			
			String url = String.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s", ACCESS_TOKEN);
			
			log.debug(String.format("请求url地址:%s", url));
			
			ResponseContent rc = HttpHelper.postJsonEntity(url, jsonBody);

            result = rc.getContent("UTF-8");

			log.debug("返回结果:"+result);

            Map<String,Integer> resultMap = objectMapper.readValue(result, Map.class);

			if(resultMap.get("errcode")!=0){

                log.info("发送失败");
			}else{

                log.info("发送成功");
			}
		} catch (Exception e) {
			log.error("推送run方法错误",e);
			result = e.getMessage();
		}
		
		log.debug("结束--->推送微信消息");
		
		return result;
	}

}
