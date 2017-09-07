package com.hfocean.uavportal.console.airplan.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfocean.common.excel.XSSFExcelTool;
import com.hfocean.common.notify.NotifyHelper;
import com.hfocean.common.util.AppContextHelper;
import com.hfocean.common.util.DateHandler;
import static com.hfocean.common.util.VerdictUtil.*;
import com.hfocean.uavportal.console.airplan.service.AirPlanService;
import com.hfocean.uavportal.console.airplan.vo.AirPlanPageParam;
import com.hfocean.uavportal.console.airplan.vo.AirPlans;
import com.hfocean.uavportal.console.airplan.vo.AirPlansExcel;
import com.hfocean.uavportal.core.airapply.vo.AirspaceVo;
import com.hfocean.uavportal.core.airapply.vo.PlaneInfo;
import com.hfocean.uavportal.core.airplan.enumerate.AirPlanEnum;
import com.hfocean.uavportal.core.airplan.po.AirPlanPO;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.hql.HqlHelper;
import com.hfocean.uavportal.core.common.hql.body.Join;
import com.hfocean.uavportal.core.common.hql.impl.HqlMan;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import com.hfocean.uavportal.core.sms.enm.SmsTemplate;
import com.hfocean.uavportal.core.sms.provider.SMSProvider;
import com.hfocean.uavportal.core.user.enm.UserEnum;
import com.hfocean.uavportal.core.user.po.UserCompanyPO;
import com.hfocean.uavportal.core.user.po.UserPersonalPO;
import com.hfocean.uavportal.core.user.po.UserWxPO;
import com.hfocean.uavportal.core.user.repository.UserCompanyRepository;
import com.hfocean.uavportal.core.user.repository.UserPersonalRepository;
import com.hfocean.uavportal.core.user.repository.UserWxRepository;
import com.hfocean.uavportal.weixin.notify.enm.WechatTempType;
import com.hfocean.uavportal.weixin.notify.provider.WechatProvider;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.*;

/**
* 
* @author Leslie.Lam
* @create 2017-06-22 16:02
**/
@Service("consoleAirPlanService")
public class AirPlanServiceImpl extends BaseServiceImpl<AirPlanPO,String> implements AirPlanService {

    private final static ObjectMapper objectMapper=new ObjectMapper();

    @Resource
    private UserPersonalRepository userPersonalRepository;

    @Resource
    private UserCompanyRepository userCompanyRepository;

    @Resource
    private UserWxRepository userWxRepository;

    @Override
    public Pager queryAirplansByPage(AirPlanPageParam param, Pager pager) throws Exception {
        HqlMan hql = HqlHelper.from("AirPlans a");
//        hql.leftJoin("a.airspaces ap");
        getAirPlansWhitParam(param, hql);
        return findByPage(hql, pager);
    }

    private void getAirPlansWhitParam(AirPlanPageParam param, HqlMan hql) throws Exception {
        if(gtOrEtoZero(param.getType())) hql.and("a.type = ?",param.getType());

        if(isNotBlank(param.getPlanType())) hql.and("a.planType like ?","%"+param.getPlanType()+"%");

        if(isNotBlank(param.getLanding())) hql.and("a.landingInfo like ?","%"+param.getLanding()+"%");

        if(isNotBlank(param.getAir())) hql.and("a.airInfo like ?","%"+param.getAir()+"%");

        if(isNotBlank(param.getAirArea())) hql.and("a.airInfo like ?","%"+param.getAirArea()+"%");

        if(isNotBlank(param.getAirName())) hql.and("a.airInfo like ?","%"+param.getAirName()+"%");

        if(isNotBlank(param.getTerritorial())) hql.and("a.airInfo like ?","%"+param.getTerritorial()+"%");
//        if(isNotBlank(param.getTerritorial())) hql.and("ap.territorial like ?","%"+param.getTerritorial()+"%");

        if(isNotBlank(param.getCode())) hql.and("a.number like ?","%"+param.getCode()+"%");

        if(isNotBlank(param.getUnit())) hql.and("a.userName like ?","%"+param.getUnit()+"%");

        if(isNotBlank(param.getStartDate())) hql.and("a.beginTime >= ?", DateHandler.parse(param.getStartDate(),"yyyy-MM-dd"));

        if(isNotBlank(param.getEndDate())) hql.and("a.beginTime <= ?",DateHandler.addDay(DateHandler.parse(param.getEndDate(),"yyyy-MM-dd"),1));

        if(null!=param.getStatus())  hql.and("a.status = ?",param.getStatus());

        hql.orderBy("a.createTime desc");
    }

    @Override
    public void exportAirplansExcel(AirPlanPageParam param) throws Exception {
        assertNotBlank(param.getMap());
        HqlMan hql = HqlHelper.from("AirPlans a");
        getAirPlansWhitParam(param, hql);
        List<AirPlans> plans = findAll(hql, AirPlans.class);
        List<AirPlansExcel> excels = new ArrayList<>();
        if(isValidCollection(plans)){
            AirPlansExcel excel;
            for (AirPlans plan:plans){
                excel=new AirPlansExcel();
                String beginTime = DateHandler.dateToString(plan.getBeginTime(),"yyyyMMdd HH:mm");//yyyyMMdd HH:mm
                String endTime = DateHandler.dateToString(plan.getEndTime(),"yyyyMMdd HH:mm");
                if(beginTime.substring(0,8).equals(endTime.substring(0,8))){
                    excel.setPlanTime(beginTime+" 至 "+endTime.substring(9,14));
                }else {
                    excel.setPlanTime(beginTime+" 至 "+endTime);
                }
                excel.setUserName(plan.getUserName());
                excel.setNumber(plan.getNumber());
                excel.setPlanType(plan.getPlanType());
                List<PlaneInfo> planeList = objectMapper.readValue(plan.getPlanes(), new TypeReference<List<PlaneInfo>>() {});
                if(isValidCollection(planeList)){
                    StringBuffer sb=new StringBuffer();
                    for (PlaneInfo planeInfo:planeList){
                        sb.append(planeInfo.getName()).append("、");
                    }
                    excel.setPlanes(sb.substring(0,sb.lastIndexOf("、")));
                }
                excel.setType(AirPlanEnum.name(plan.getType(),"type"));
                List<AirspaceVo> airList = objectMapper.readValue(plan.getAirInfo(), new TypeReference<List<AirspaceVo>>() {});
                AirspaceVo airspaceVo = airList.get(0);
                excel.setSpaceName(isBlank(airspaceVo.getName())?airspaceVo.getLocation():airspaceVo.getName());
                excel.setHigh(airspaceVo.getHigh());
                if(null!=airspaceVo.getId())excel.setArae(new StringBuffer().append(airspaceVo.getProvinceName()).append(airspaceVo.getCityName()).append(airspaceVo.getAreaName()).toString());
                excel.setAirTerritorial(airspaceVo.getTerritorial());
                excel.setStatus(AirPlanEnum.name(plan.getStatus(),"status"));
                String startTime = DateHandler.dateToString(plan.getStartTime(),"yyyyMMdd HH:mm");
                if(isNotBlank(startTime)){
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("起飞 ").append(startTime.substring(9,14));
                    String finishTime = DateHandler.dateToString(plan.getFinishTime(),"yyyyMMdd HH:mm");
                    if(isNotBlank(finishTime)){
                        buffer.append("  降落 ").append(finishTime.substring(9,14));
                    }
                    excel.setSituation(buffer.toString());
                }
                excel.setApplyTime(DateHandler.dateToString(plan.getCreateTime(),"yyyy-MM-dd"));
                Map<String,Object> map = objectMapper.readValue(plan.getContactInfo(), Map.class);
                if(Objects.equals(plan.getType(),AirPlanEnum.二类飞行计划.code())){
                    excel.setContactNameAndPhone(map.get("controller")+"\n"+map.get("controllerContact"));
                }else {
                    excel.setContactNameAndPhone(map.get("fieldContactName")+"\n"+map.get("fieldContactPhone"));
                }
                excels.add(excel);
            }
            LinkedHashMap linkedHashMap = objectMapper.readValue(URLDecoder.decode(param.getMap(),"utf-8"), LinkedHashMap.class);
            XSSFExcelTool.exportExcel(linkedHashMap, "sheet1", "飞行计划动态", excels, AppContextHelper.getRequest(), AppContextHelper.getResponse());
        }
    }

    @Override
    @Transactional
    public void auditAirPlan(String flyplanId,String param) throws Exception {
        AirPlanPO po = findOne(flyplanId);
        assertNotNull(po);
        Map<String,Object> map = objectMapper.readValue(param, Map.class);
        String s = map.get("status").toString();
        assertNotNull(s);
        Integer status = Integer.valueOf(s);
        String temp;
        Integer tempType;
        if(1==status){
            po.setStatus(AirPlanEnum.审核通过.code());
            temp=SmsTemplate.申请通过.stemp();
            tempType=WechatTempType.飞行计划审核通过.code();
        }else{//失败，需要原因
            po.setStatus(AirPlanEnum.未通过审核.code());
            String r = map.get("reason").toString();
            assertNotBlank(r);
            po.setReason(r);
            temp=SmsTemplate.申请不通过.stemp();
            tempType=WechatTempType.飞行计划审核不通过.code();
        }
        po.setAuditTime(new Date());
        save(po);
        //短信推送
        if(Objects.equals(po.getType(),AirPlanEnum.管制飞行计划.code())){
            NotifyHelper.push(new SMSProvider(getPhone(po.getUserType(), po.getUserId()),temp,po.getNumber()));
        }
        //微信推送
        UserWxPO wx = userWxRepository.findOne(Example.of(new UserWxPO(po.getUserId())));
        if(null!=wx&&null!=wx.getId()){
            NotifyHelper.push(new WechatProvider(wx.getId(),tempType,po.getNumber()));
        }
    }

    private String getPhone(Integer userType,String userId) {
        String phone;
        if(Objects.equals(userType, UserEnum.个人用户.getCode())){
            UserPersonalPO user = userPersonalRepository.findOne(userId);
            assertNotNull(user);
            phone=user.getPhone();
        }else {
            UserCompanyPO user = userCompanyRepository.findOne(userId);
            assertNotNull(user);
            phone=user.getContactPhone();
        }
        return phone;
    }
}
