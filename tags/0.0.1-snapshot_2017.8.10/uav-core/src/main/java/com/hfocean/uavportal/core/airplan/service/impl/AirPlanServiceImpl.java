package com.hfocean.uavportal.core.airplan.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.notify.NotifyHelper;
import com.hfocean.common.util.DateHandler;
import com.hfocean.common.util.ValidateUtil;
import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.airapply.vo.AirspaceVo;
import com.hfocean.uavportal.core.airapply.vo.LandingVo;
import com.hfocean.uavportal.core.airplan.enumerate.AirPlanEnum;
import com.hfocean.uavportal.core.airplan.po.*;
import com.hfocean.uavportal.core.airplan.repository.AirPlanTempRepository;
import com.hfocean.uavportal.core.airplan.repository.FlyCountRepository;
import com.hfocean.uavportal.core.airplan.repository.PlanAirspaceRefRepository;
import com.hfocean.uavportal.core.airplan.repository.PlanLandingRefRepository;
import com.hfocean.uavportal.core.airplan.service.AirPlanService;
import com.hfocean.uavportal.core.airplan.vo.AirPlanParam;
import com.hfocean.uavportal.core.airplan.vo.AirPlanTempParam;
import com.hfocean.uavportal.core.airplan.vo.FlyCountVo;
import com.hfocean.uavportal.core.airplan.vo.MyAirPlan;
import com.hfocean.uavportal.core.airspace.po.AirspacePO;
import com.hfocean.uavportal.core.airspace.repository.AirspaceRepository;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.constant.Constant;
import com.hfocean.uavportal.core.common.hql.HqlHelper;
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
import com.hfocean.uavportal.core.user.session.BaseSessionUser;
import com.hfocean.uavportal.core.util.NumberGenerator;
import com.hfocean.uavportal.weixin.notify.enm.WechatTempType;
import com.hfocean.uavportal.weixin.notify.provider.WechatProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.Query;
import java.io.IOException;
import java.util.*;

import static com.hfocean.common.util.VerdictUtil.*;

/**
* 
* @author Leslie.Lam
* @create 2017-06-24 9:55
**/
@Service
public class AirPlanServiceImpl extends BaseServiceImpl<AirPlanPO,String> implements AirPlanService {

    private final static transient Logger log = LoggerFactory.getLogger(AirPlanServiceImpl.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Resource
    private PlanLandingRefRepository planLandingRefRepository;

    @Resource
    private PlanAirspaceRefRepository planAirspaceRefRepository;

    @Resource
    private AirPlanTempRepository airPlanTempRepository;

    @Resource
    private UserPersonalRepository userPersonalRepository;

    @Resource
    private UserCompanyRepository userCompanyRepository;

    @Resource
    private UserWxRepository userWxRepository;

    @Resource
    private FlyCountRepository flyCountRepository;

    @Resource
    private AirspaceRepository airspaceRepository;

    @Override
    @Transactional
    public void addAirPlan(String param, BaseSessionUser user) throws Exception {
        AirPlanParam p = objectMapper.readValue(param,AirPlanParam.class);
        ValidateUtil.validate(p);
        AirPlanPO po = new AirPlanPO();
        Integer type = p.getType();
        boolean flag = Objects.equals(type, AirPlanEnum.管制飞行计划.code());//是否为管制飞行计划
        if(flag) {
            assertNotBlank(p.getApplyOfficialNo());
            po.setApplyOfficialNo(p.getApplyOfficialNo());
        }
        Date beginTime = DateHandler.parse(p.getBeginTime(), "yyyy-MM-dd HH:mm");
        Date cur = new Date();//当前时间
        if(DateHandler.compare(beginTime,cur)==-1)throw new BaseBusinessException(BaseBusinessError.FORBIDDEN.getCode(),"计划开始时间不能小于当前时间");
        if(flag||Objects.equals(type, AirPlanEnum.一类飞行计划.code())){
            Date dayTime = DateHandler.getDayTime(cur);//当前时间日期
            if(DateHandler.compare(cur,DateHandler.addHour(dayTime,16))==1||DateHandler.compare(beginTime,DateHandler.addDay(dayTime,1))==-1){
                throw new BaseBusinessException(BaseBusinessError.FORBIDDEN.getCode(),"请于计划开始时间前天下午4点前提交该类申请。");
            }
        }else if(Objects.equals(type,AirPlanEnum.二类飞行计划.code())){
            if(DateHandler.compare(beginTime,DateHandler.addHour(cur,1))==-1)
                throw new BaseBusinessException(BaseBusinessError.FORBIDDEN.getCode(),"该类飞行计划必须于当天提前一小时申请！");
        }
        List<AirspaceVo> airInfo = p.getAirInfo();//空域信息
        AirspaceVo air = airInfo.get(0);
        if(null!=air.getId()){
            AirspacePO one = airspaceRepository.findOne(air.getId());
            if(null!=one)air.setTerritorial(one.getTerritorial());
        }
        po.setNumber(NumberGenerator.getAirPlanNo(p.getType(),"GZ",air.getId()));
        po.setType(p.getType());
        po.setPlanType(p.getPlanType());
        String userId = user.getUserId();
        Integer userType = user.getType();
        po.setUserType(userType);
        po.setUserId(userId);
        if(Objects.equals(userType, UserEnum.个人用户.getCode())){
            UserPersonalPO up = userPersonalRepository.findOne(userId);
            assertNotNull(up);
            po.setUserName(up.getName());
        }else {
            UserCompanyPO uc = userCompanyRepository.findOne(userId);
            assertNotNull(uc);
            po.setUserName(uc.getCompanyName());
        }
        po.setCreateTime(cur);
        po.setBeginTime(beginTime);//开始时间
        po.setEndTime(DateHandler.parse(p.getEndTime(),"yyyy-MM-dd HH:mm"));//结束时间
        po.setPlanes(objectMapper.writeValueAsString(p.getPlanes()));//使用机型
        po.setAirInfo(objectMapper.writeValueAsString(airInfo));
        List<LandingVo> landingInfo = p.getLandingInfo();
        po.setLandingInfo(objectMapper.writeValueAsString(landingInfo));//起降场信息
        po.setContactInfo(objectMapper.writeValueAsString(p.getContactInfo()));//联系人信息
        po.setRemark(p.getRemark());//备注
        po.setWeatherCondition(p.getWeatherCondition());//天气条件
        po.setStatus(AirPlanEnum.审核中.code());
        save(po);
        //维护飞行计划和空域信息关系
        if(!flag){
            assertNotNull(air.getId());
            planAirspaceRefRepository.save(new PlanAirspaceRefPO(air.getId(),po.getId()));
        }
        //维护飞行计划和起降场关系
        if(isValidCollection(landingInfo)){
            for (LandingVo vo:landingInfo){
                if(null!=vo.getId())planLandingRefRepository.save(new PlanLandingRefPO(vo.getId(),po.getId()));
                if(!flag)break;
            }
        }
    }

    @Override
    public List<MyAirPlan> queryAirplans(Integer period, String type, BaseSessionUser user) {
        StringBuffer hql = new StringBuffer("FROM MyAirPlan a WHERE 1=1");
        List<Object> params = new ArrayList<>();
        hql.append(" and a.userId = ?");
        params.add(user.getUserId());
        if(gtOrEtoZero(period)){
            hql.append(" and a.createTime >= ?");
            params.add(DateHandler.addDay(new Date(),-period));
        }
        if(StringUtils.isNotBlank(type)) {
            hql.append(" and a.planType like ?");
            params.add(type+"%");
        }
        hql.append(" order by a.createTime desc");
        return findAll(hql.toString(),params);
    }

    @Override
    @Transactional
    public ResponseEntity<WebResponse> startAirplan(String flycode) {
        AirPlanPO po = findOne(Example.of(new AirPlanPO(flycode)));
        assertNotNull(po);
        Integer status = po.getStatus();
        if(Objects.equals(status,AirPlanEnum.已关闭.code())){
            throw new BaseBusinessException(BaseBusinessError.FORBIDDEN.getCode(),"飞行计划已失效");
        }
        if(Objects.equals(status,AirPlanEnum.执行中.code())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new WebResponse(403501,"飞行计划已开始"));
        }
        if(!Objects.equals(status,AirPlanEnum.审核通过.code())) throw new BaseBusinessException(BaseBusinessError.FORBIDDEN.getCode(),"该计划还未通过审核");
        if(DateHandler.compare(DateHandler.addMinute(new Date(),30),po.getBeginTime())==-1){
            throw new BaseBusinessException(BaseBusinessError.FORBIDDEN.getCode(),"只能提前30分钟开始计划");
        }
        po.setStartTime(new Date());
        po.setStatus(AirPlanEnum.执行中.code());
        save(po);
        return ResponseEntity.ok(new WebResponse());
    }

    @Override
    @Transactional
    public ResponseEntity<WebResponse> stopAirplan(String flycode) {
        AirPlanPO po = findOne(Example.of(new AirPlanPO(flycode)));
        assertNotNull(po);
        Integer status = po.getStatus();
        if(Objects.equals(status,AirPlanEnum.已关闭.code())){
            throw new BaseBusinessException(BaseBusinessError.FORBIDDEN.getCode(),"飞行计划已失效");
        }
        if(Objects.equals(status,AirPlanEnum.已完成.code())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new WebResponse(403502,"飞行计划已结束"));
        }
        if(!(Objects.equals(status,AirPlanEnum.执行中.code())||Objects.equals(status,AirPlanEnum.计划超时.code()))) throw new BaseBusinessException(BaseBusinessError.FORBIDDEN.getCode(),"该状态无法结束计划");
        po.setFinishTime(new Date());
        po.setStatus(AirPlanEnum.已完成.code());
        save(po);
        return ResponseEntity.ok(new WebResponse());
    }

    @Override
    @Transactional
    public ResponseEntity<WebResponse> revokeAirPlan(String flycode) {
        AirPlanPO po = findOne(Example.of(new AirPlanPO(flycode)));
        assertNotNull(po);
        Integer status = po.getStatus();
        if(Objects.equals(status,AirPlanEnum.已关闭.code())){
            throw new BaseBusinessException(BaseBusinessError.FORBIDDEN.getCode(),"飞行计划已失效");
        }
        if(Objects.equals(status,AirPlanEnum.撤销申请.code())||Objects.equals(status,AirPlanEnum.执行中.code())
                ||Objects.equals(status,AirPlanEnum.已完成.code())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new WebResponse(403503,"飞行计划无法撤消"));
        }
        po.setStatus(AirPlanEnum.撤销申请.code());
        save(po);
        return ResponseEntity.ok(new WebResponse());
    }

    @Override
    public Pager queryAirplansByPage(Pager pager,BaseSessionUser user,Integer... status) {
        StringBuffer hql = new StringBuffer("FROM MyAirPlan a WHERE 1=1");
        List<Object> params = new ArrayList<>();
        hql.append(" and a.userId = ?");
        params.add(user.getUserId());
        if(isValidArray(status)){
            hql.append(" and a.status in ?");
            params.add(Arrays.asList(status));
        }
        hql.append(" order by a.createTime desc");
        return findByPage(hql.toString(),pager,params);
    }

    @Override
    @Transactional
    public void addAirPlanTemp(String param, BaseSessionUser user) throws Exception {
        AirPlanTempParam p = objectMapper.readValue(param, AirPlanTempParam.class);
        ValidateUtil.validate(p);
        AirPlanTempPO po = new AirPlanTempPO();
        po.setName(p.getName());
        po.setDepict(p.getDepict());
        po.setType(p.getType());
        po.setPlanType(p.getPlanType());
        po.setUserType(user.getType());
        po.setUserId(user.getUserId());
        po.setCreateTime(new Date());
        po.setPlanes(objectMapper.writeValueAsString(p.getPlanes()));//使用机型
        List<AirspaceVo> airInfo = p.getAirInfo();
        po.setAirInfo(objectMapper.writeValueAsString(airInfo));//空域信息
        po.setLandingInfo(objectMapper.writeValueAsString(p.getLandingInfo()));//起降场信息
        po.setContactInfo(objectMapper.writeValueAsString(p.getContactInfo()));//联系人信息
        po.setRemark(p.getRemark());//备注
        po.setWeatherCondition(p.getWeatherCondition());//天气条件
        airPlanTempRepository.save(po);
    }

    @Override
    public List queryAirplanTemps(BaseSessionUser user) {
        StringBuffer hql = new StringBuffer("FROM AirPlanTemps a WHERE 1=1 ");
        List<Object> params = new ArrayList<>();
        hql.append(" and a.userId = ?");
        params.add(user.getUserId());
        hql.append(" order by a.createTime desc");
        return findAll(hql.toString(),params);
    }

    @Override
    public AirPlanTempPO queryAirPlanTempById(String id) {
        return airPlanTempRepository.findOne(id);
    }

    @Override
    public ResponseEntity<WebResponse> getAirPlanCertificate(String code) throws IOException {
        AirPlanPO po = findOne(Example.of(new AirPlanPO(code)));
        if(null==po) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new WebResponse(403504,"无此飞行计划"));
        if(!Arrays.asList(AirPlanEnum.执行动态.status()).contains(po.getStatus())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new WebResponse(403505,"飞行计划目前状态无法查看凭证"));
        }
        Map<String,Object> map = new HashMap<>(3);
        map.put("type",po.getType());
        List<AirspaceVo> spaces =objectMapper.readValue(po.getAirInfo(), new TypeReference<List<AirspaceVo>>() {});
        map.put("location",spaces.get(0).getLocation());
        map.put("token", Jwts.builder().setSubject(code).signWith(SignatureAlgorithm.HS512, Constant.JWTSECRETKEY).
                setExpiration(DateHandler.addMinute(new Date(),10)).compact());
        return ResponseEntity.ok(new WebResponse(map));
    }

    @Override
    public void notifyControlAirPlanStart() throws Exception {
        Date cur = new Date();
        List<AirPlanPO> plans = findAll(HqlHelper.from("AirPlanPO a")
                .and("a.type = ?",AirPlanEnum.管制飞行计划.code())
                .and("a.status=?", AirPlanEnum.审核通过.code())
                .and("a.beginTime >=?",DateHandler.addMinute(cur,60))
                .and("a.beginTime < ?", DateHandler.addMinute(cur,61)), AirPlanPO.class);
        if(isValidCollection(plans)){
            UserWxPO wx;
            for (AirPlanPO plan:plans){
                NotifyHelper.push(new SMSProvider(getPhone(plan.getUserType(),plan.getUserId()), SmsTemplate.飞行计划确认提醒.stemp(),plan.getNumber()));
                wx = userWxRepository.findOne(Example.of(new UserWxPO(plan.getUserId())));
                if(null!=wx&&null!=wx.getId()){
                    //TODO
//                    NotifyHelper.push(new WechatProvider(wx.getId(), WechatTempType.飞行超时提醒.code(),
//                            plan.getNumber(),DateHandler.dateToString(plan.getEndTime(),"yyyy-MM-dd HH:mm")));
                }
            }
        }
    }

    @Override
    public void notifyAirPlanStart() throws Exception {
        Date cur = new Date();
        List<AirPlanPO> plans = findAll(HqlHelper.from("AirPlanPO a")
                .and("a.type in ?", Arrays.asList(new Integer[]{AirPlanEnum.一类飞行计划.code(), AirPlanEnum.二类飞行计划.code()}))
                .and("a.status = ?", AirPlanEnum.审核通过.code())
                .and("a.beginTime >= ?",DateHandler.addMinute(cur,30))
                .and("a.beginTime < ?", DateHandler.addMinute(cur,31)), AirPlanPO.class);
        if(isValidCollection(plans)){
            for (AirPlanPO plan:plans){
                UserWxPO wx = userWxRepository.findOne(Example.of(new UserWxPO(plan.getUserId())));
                if(null!=wx&&null!=wx.getId()){
                    NotifyHelper.push(new WechatProvider(wx.getId(), WechatTempType.飞行动态上报提醒.code(),
                            plan.getNumber(),DateHandler.dateToString(plan.getBeginTime(),"yyyy-MM-dd HH:mm")));
                }
            }
        }
    }

    @Override
    public void processUnStartAirPlan() throws Exception {
        List<AirPlanPO> plans = findAll(HqlHelper.from("AirPlanPO a")
                .and("a.status = ?", AirPlanEnum.审核通过.code())
                .and("a.endTime <?", DateHandler.addMinute(new Date(),-30)), AirPlanPO.class);
        if(isValidCollection(plans)){
            for (AirPlanPO plan:plans){
                plan.setStatus(AirPlanEnum.已关闭.code());
                save(plan);
            }
        }
    }

    @Override
    public void notifyAirPlanOverTime() throws Exception {
        List<AirPlanPO> plans = findAll(HqlHelper.from("AirPlanPO a")
                .and("a.status = ?", AirPlanEnum.执行中.code())
                .and("a.endTime <?", new Date())
                .and("a.endTime >=?",DateHandler.addMinute(new Date(),-1))
                , AirPlanPO.class);
        if(isValidCollection(plans)){
            UserWxPO wx;
            for (AirPlanPO plan:plans){
                if(Objects.equals(plan.getType(),AirPlanEnum.管制飞行计划.code())){
                    NotifyHelper.push(new SMSProvider(getPhone(plan.getUserType(),plan.getUserId()), SmsTemplate.飞行超时.stemp(),plan.getNumber()));
                }
                wx = userWxRepository.findOne(Example.of(new UserWxPO(plan.getUserId())));
                if(null!=wx&&null!=wx.getId()){
                    NotifyHelper.push(new WechatProvider(wx.getId(), WechatTempType.飞行超时提醒.code(),
                            plan.getNumber(),DateHandler.dateToString(plan.getEndTime(),"yyyy-MM-dd HH:mm")));
                }
            }
        }
    }

    @Override
    public void updateAirPlanOverTime() throws Exception {
        List<AirPlanPO> plans = findAll(HqlHelper.from("AirPlanPO a")
                .and("a.status = ?", AirPlanEnum.执行中.code())
                .and("a.endTime <?", DateHandler.addMinute(new Date(),-30))
                , AirPlanPO.class);
        if(isValidCollection(plans)){
            for (AirPlanPO plan:plans) {
                plan.setStatus(AirPlanEnum.计划超时.code());
                save(plan);
            }
        }
    }

    private String getPhone(Integer userType, String userId) {
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

    @Override
    public void userFlyCount() throws Exception {
        log.info("============ 个人用户飞行统计 任务开始============");
        List<UserPersonalPO> ps = userPersonalRepository.findAll();
        if(isValidCollection(ps)){
            for (UserPersonalPO up:ps){
                saveUserFlyCount(up.getId());
            }
        }
        log.info("============ 个人用户飞行统计 任务结束============");

        log.info("============ 企业用户飞行统计 任务开始============");
        List<UserCompanyPO> cs = userCompanyRepository.findAll();
        if(isValidCollection(cs)){
            for (UserCompanyPO uc:cs){
                saveUserFlyCount(uc.getId());
            }
        }
        log.info("============ 企业用户飞行统计 任务结束============");
    }

    private void saveUserFlyCount(String userId) {
        Query query = entityManager.createNativeQuery(new StringBuffer("SELECT ")
                .append("ap.user_id,")
                .append("round(SUM(TIMESTAMPDIFF(SECOND,ap.start_time,IFNULL(ap.finish_time,ap.end_time))/3600)) AS fly_time,")
                .append("COUNT(id) AS fly_count,")
                .append("SUM(CASE WHEN (ap.status=5) OR (ap.finish_time>DATE_ADD(ap.end_time,INTERVAL 30 MINUTE)) THEN 1 ELSE 0 END) AS out_count ")
                .append("FROM tb_air_plan ap ")
                .append("WHERE ap.user_id=?0 ")
                .append("AND ap.status in(4,5)")
                .toString(),FlyCountVo.class);
        query.setParameter(0, userId);
        FlyCountVo vo = (FlyCountVo)query.getResultList().get(0);
        if(null!=vo) {
            FlyCount flyCount = flyCountRepository.findOne(Example.of(new FlyCount(userId)));
            if(null==flyCount)flyCount=new FlyCount(userId);
            flyCount.setFlyCount(vo.getFlyCount());
            flyCount.setFlyTime(vo.getFlyTime());
            flyCount.setOutCount(vo.getOutCount());
            flyCountRepository.save(flyCount);
        }
    }

    @Override
    public void autoCloseUnAuditedPlan() throws Exception {
        List<AirPlanPO> plans = findAll(HqlHelper.from("AirPlanPO a")
                .and("a.status=?", AirPlanEnum.审核中.code())
                .and("a.endTime <=?",new Date())
                , AirPlanPO.class);
        if(isValidCollection(plans)){
            for (AirPlanPO plan:plans){
                plan.setStatus(AirPlanEnum.已关闭.code());
                save(plan);
            }
        }
    }

    @Override
    public void autoAuditContolAndFirstTypePlan() throws Exception {
        List<AirPlanPO> all = findAll(HqlHelper.from("AirPlanPO a")
                .and("a.type in ?", Arrays.asList(new Integer[]{AirPlanEnum.一类飞行计划.code(), AirPlanEnum.管制飞行计划.code()}))
                .and("a.status=?", AirPlanEnum.审核中.code())
                .and("a.beginTime<?", DateHandler.getDateTimePoint(DateHandler.addDay(new Date(),1),24, 0))
                .and("a.beginTime>?", DateHandler.getDateTimePoint(DateHandler.addDay(new Date(),1),0, 0)), AirPlanPO.class);
        if(isValidCollection(all)){
            UserWxPO wx;
            for (AirPlanPO a:all){
                a.setStatus(AirPlanEnum.审核通过.code());
                save(a);
                //微信推送
                wx = userWxRepository.findOne(Example.of(new UserWxPO(a.getUserId())));
                if(null!=wx&&null!=wx.getId()){
                    NotifyHelper.push(new WechatProvider(wx.getId(),WechatTempType.飞行计划审核通过.code(),a.getNumber()));
                }
                //短信推送
                if(Objects.equals(a.getType(),AirPlanEnum.管制飞行计划.code())){
                    NotifyHelper.push(new SMSProvider(getPhone(a.getUserType(), a.getUserId()),SmsTemplate.申请通过.stemp(),a.getNumber()));
                }
            }
        }
    }

    @Override
    public void autoAuditPlan() throws Exception {
        List<AirPlanPO> all = findAll(HqlHelper.from("AirPlanPO a")
                .and("a.type in ?", Arrays.asList(new Integer[]{AirPlanEnum.一类飞行计划.code(), AirPlanEnum.管制飞行计划.code()}))
                .and("a.status=?", AirPlanEnum.审核中.code())
                .and("a.createTime<?", DateHandler.getTodayTimePoint(21, 0))
                .and("a.createTime>?", DateHandler.getTodayTimePoint(0, 0)), AirPlanPO.class);
        if(isValidCollection(all)){
            UserWxPO wx;
            for (AirPlanPO a:all){
                a.setStatus(AirPlanEnum.未通过审核.code());
                a.setReason("与其他飞行活动有冲突");
                save(a);
                //微信推送
                wx = userWxRepository.findOne(Example.of(new UserWxPO(a.getUserId())));
                if(null!=wx&&null!=wx.getId()){
                    NotifyHelper.push(new WechatProvider(wx.getId(),WechatTempType.飞行计划审核不通过.code(),a.getNumber()));
                }
                //短信推送
                if(Objects.equals(a.getType(),AirPlanEnum.管制飞行计划.code())){
                    NotifyHelper.push(new SMSProvider(getPhone(a.getUserType(), a.getUserId()),SmsTemplate.申请不通过.stemp(),a.getNumber()));
                }
            }
        }
    }

    @Override
    public void autoAuditSecondTypePlan() throws Exception {
        Date cur = new Date();
        List<AirPlanPO> plans = findAll(HqlHelper.from("AirPlanPO a")
                .and("a.type = ?", AirPlanEnum.二类飞行计划.code())
                .and("a.status=?", AirPlanEnum.审核中.code())
                .and("a.beginTime<=?",DateHandler.addMinute(cur,45))
                .and("a.beginTime>?",DateHandler.addMinute(cur,30)),AirPlanPO.class);
        if(isValidCollection(plans)){
            UserWxPO wx;
            for (AirPlanPO plan:plans){
                plan.setStatus(AirPlanEnum.审核通过.code());
                save(plan);
                //微信推送
                wx = userWxRepository.findOne(Example.of(new UserWxPO(plan.getUserId())));
                if(null!=wx&&null!=wx.getId()){
                    NotifyHelper.push(new WechatProvider(wx.getId(),WechatTempType.飞行计划审核通过.code(),plan.getNumber()));
                }
            }
        }
    }
}
