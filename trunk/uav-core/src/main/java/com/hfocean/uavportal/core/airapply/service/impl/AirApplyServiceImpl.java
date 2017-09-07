package com.hfocean.uavportal.core.airapply.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.util.DateHandler;
import com.hfocean.common.util.ValidateUtil;
import com.hfocean.common.util.VerdictUtil;
import com.hfocean.uavportal.core.airapply.enumerate.AirApplyEnum;
import com.hfocean.uavportal.core.airapply.po.AirApplyLandingRefPO;
import com.hfocean.uavportal.core.airapply.po.AirApplyPO;
import com.hfocean.uavportal.core.airapply.repository.AirApplyLandingRefRepository;
import com.hfocean.uavportal.core.airapply.service.AirApplyService;
import com.hfocean.uavportal.core.airapply.vo.AirApplyParam;
import com.hfocean.uavportal.core.airapply.vo.LandingInfo;
import com.hfocean.uavportal.core.airapply.vo.LandingVo;
import com.hfocean.uavportal.core.airapply.vo.MyAirApply;
import com.hfocean.uavportal.core.attach.po.Attach;
import com.hfocean.uavportal.core.attach.repository.AttachRepository;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import com.hfocean.uavportal.core.user.session.BaseSessionUser;
import com.hfocean.uavportal.core.util.NumberGenerator;
import com.mysql.jdbc.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 实现空域申请业务
 *
 * @author Leslie.Lam
 * @create 2017-06-20 15:46
 **/
@Service
public class AirApplyServiceImpl extends BaseServiceImpl<AirApplyPO,String> implements AirApplyService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Resource
    private AttachRepository attachRepository;

    @Resource
    private AirApplyLandingRefRepository airApplyLandingRefRepository;

    @Override
    @Transactional
    public void addAirApply(String param,BaseSessionUser user) throws Exception{
        AirApplyParam p = objectMapper.readValue(param, AirApplyParam.class);
        ValidateUtil.validate(p);
        AirApplyPO po = new AirApplyPO();
        Date cur = new Date();
        Date beginTime = DateHandler.parse(p.getBeginTime(), "yyyy-MM-dd");//开始时间
        Date endTime = DateHandler.parse(p.getEndTime(), "yyyy-MM-dd");//结束时间
        if(DateHandler.compare(beginTime,cur)==-1)throw new BaseBusinessException(BaseBusinessError.FORBIDDEN.getCode(),"计划开始时间不能小于当前时间");
        if(DateHandler.compare(endTime,DateHandler.addMonth(beginTime,6))==1){
            throw new BaseBusinessException(BaseBusinessError.FORBIDDEN.getCode(),
                    "计划结束时间不能超过开始时间6个月");
        }
        po.setNumber(NumberGenerator.getAirApplyNo());
        po.setPlanType(p.getPlanType());
        po.setUserType(user.getType());
        po.setUserId(user.getUserId());
        po.setCreateTime(cur);
        po.setUserName(user.getUserName());
        po.setBeginTime(beginTime);
        po.setEndTime(endTime);
        po.setPlanes(objectMapper.writeValueAsString(p.getPlanes()));//使用机型
        po.setAirInfo(objectMapper.writeValueAsString(p.getAirInfo()));//空域信息
        po.setContatInfo(objectMapper.writeValueAsString(p.getContactInfo()));//联系人信息
        LandingInfo landingInfo = p.getLandingInfo();
        po.setLandingInfo(objectMapper.writeValueAsString(landingInfo));//起降场信息
        po.setFlyRule(p.getFlyRule());//飞行规则
        po.setRemark(p.getRemark());//备注
        po.setStatus(AirApplyEnum.审核中.code());
        save(po);
        AirApplyParam.AttachInfo attachInfo = p.getAttachInfo();//附件信息
        Attach attach = new Attach();
        attach.setMasterId(po.getId());
        attach.setName(attachInfo.getName());
        attach.setUrl(attachInfo.getUrl());
        attach.setUploadTime(cur);
        attachRepository.save(attach);
        //维护空域申请和起降场关系
        LandingVo master = landingInfo.getMaster();
        if(null!=master.getId())airApplyLandingRefRepository.save(new AirApplyLandingRefPO(po.getId(),master.getId()));
        LandingVo slave = landingInfo.getSlave();
        if(null!=slave&&null!=slave.getId())airApplyLandingRefRepository.save(new AirApplyLandingRefPO(po.getId(),slave.getId()));
    }

    @Override
    public List<MyAirApply> queryAirApplys(Integer period, String type, BaseSessionUser user) throws Exception {
        StringBuffer hql = new StringBuffer("FROM MyAirApply a WHERE 1=1");
        List<Object> params = new ArrayList<>();
        hql.append(" and a.userId = ?");
        params.add(user.getUserId());
        if(VerdictUtil.gtOrEtoZero(period)){
            hql.append(" and a.createTime >= ?");
            params.add(DateHandler.addDay(new Date(),-period));
        }
        if(!StringUtils.isNullOrEmpty(type)) {
            hql.append(" and a.planType like ?");
            params.add(type+"%");
        }
        hql.append(" order by a.createTime desc");
        return findAll(hql.toString(),params);
    }

    @Override
    @Transactional
    public void revokeAirApply(String aircode) throws Exception {
        AirApplyPO po = findOne(Example.of(new AirApplyPO(aircode)));
        VerdictUtil.assertNotNull(po);
        if(!Objects.equals(po.getStatus(),AirApplyEnum.审核中.code()))
            throw new BaseBusinessException(BaseBusinessError.FORBIDDEN);
        po.setStatus(AirApplyEnum.撤销申请.code());
        save(po);
    }
}
