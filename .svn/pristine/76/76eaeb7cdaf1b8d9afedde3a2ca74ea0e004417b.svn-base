package com.hfocean.uavportal.console.airapply.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfocean.common.excel.XSSFExcelTool;
import com.hfocean.common.util.AppContextHelper;
import com.hfocean.common.util.DateHandler;
import static com.hfocean.common.util.VerdictUtil.*;
import com.hfocean.uavportal.console.airapply.service.AirApplyService;
import com.hfocean.uavportal.console.airapply.vo.AirApplyExcel;
import com.hfocean.uavportal.console.airapply.vo.AirApplyPageParam;
import com.hfocean.uavportal.console.airapply.vo.AirApplys;
import com.hfocean.uavportal.core.airapply.enumerate.AirApplyEnum;
import com.hfocean.uavportal.core.airapply.po.AirApplyPO;
import com.hfocean.uavportal.core.airapply.vo.AirspaceVo;
import com.hfocean.uavportal.core.airapply.vo.PlaneInfo;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.hql.HqlHelper;
import com.hfocean.uavportal.core.common.hql.impl.HqlMan;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.util.*;

/**
* 
* @author Leslie.Lam
* @create 2017-06-22 16:02
**/
@Service("consoleAirApplyService")
public class AirApplyServiceImpl extends BaseServiceImpl<AirApplyPO,String> implements AirApplyService {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Pager queryAirApplyByPage(AirApplyPageParam param, Pager pager) throws Exception {
        HqlMan hql = HqlHelper.from("AirApplys a");
        if(gtZero(param.getPeriod())){
            hql.and("a.createTime >= ?",DateHandler.addDay(new Date(),-param.getPeriod()));
        }
        if(isNotBlank(param.getType())){
            hql.and("a.planType like ?",param.getType()+"%");
        }
        if(isNotBlank(param.getLanding())){
            hql.and("a.landingInfo like ?","%"+param.getLanding()+"%");
        }
        if(isNotBlank(param.getAir())){
            hql.and("a.airInfo like ?","%"+param.getAir()+"%");
        }
        if(isNotBlank(param.getTerritorial())){
            hql.and("a.airInfo like ?","%"+param.getTerritorial()+"%");
        }
        if(isNotBlank(param.getCode())){
            hql.and("a.number like ?","%"+param.getCode()+"%");
        }
        if(isNotBlank(param.getUnit())){
            hql.and("a.userName like ?","%"+param.getUnit()+"%");
        }
        if(isNotBlank(param.getStartDate())){
            hql.and("a.beginTime >= ?",DateHandler.parse(param.getStartDate(),"yyyy-MM-dd"));
        }
        if(isNotBlank(param.getEndDate())){
            hql.and("a.beginTime <= ?",DateHandler.parse(param.getEndDate(),"yyyy-MM-dd"));
        }
        if(null!=param.getStatus()){
            hql.and("a.status = ?",param.getStatus());
        }
        hql.orderBy("a.createTime desc");
        return findByPage(hql,pager);
    }

    private List<Object> getAirApplyHql(AirApplyPageParam param, StringBuilder hql) throws Exception {
        List<Object> params = new ArrayList<>();
        if(gtZero(param.getPeriod())){
            hql.append(" and a.createTime >= ?");
            params.add(DateHandler.addDay(new Date(),-param.getPeriod()));
        }
        if(isNotBlank(param.getType())){
            hql.append(" and a.planType like ?");
            params.add(param.getType()+"%");
        }
        if(isNotBlank(param.getLanding())){
            hql.append(" and a.landingInfo like ?");
            params.add("%"+param.getLanding()+"%");
        }
        if(isNotBlank(param.getAir())){
            hql.append(" and a.airInfo like ?");
            params.add("%"+param.getAir()+"%");
        }
        if(isNotBlank(param.getTerritorial())){
            hql.append(" and a.airInfo like ?");
            params.add("%"+param.getTerritorial()+"%");
        }
        if(isNotBlank(param.getCode())){
            hql.append(" and a.number like ?");
            params.add("%"+param.getCode()+"%");
        }
        if(isNotBlank(param.getUnit())){
            hql.append(" and a.userName like ?");
            params.add("%"+param.getUnit()+"%");
        }
        if(isNotBlank(param.getStartDate())){
            hql.append(" and a.beginTime >= ?");
            params.add(DateHandler.parse(param.getStartDate(),"yyyy-MM-dd"));
        }
        if(isNotBlank(param.getEndDate())){
            hql.append(" and a.beginTime <= ?");
            params.add(DateHandler.parse(param.getEndDate(),"yyyy-MM-dd"));
        }
        if(null!=param.getStatus()){
            hql.append(" and a.status = ?");
            params.add(param.getStatus());
        }
        hql.append(" order by a.createTime desc");
        return params;
    }

    @Override
    public void exportAirApplyExcel(AirApplyPageParam param) throws Exception {
        assertNotBlank(param.getMap());
        StringBuilder hql = new StringBuilder("from AirApplys a where 1=1");
        List<Object> params = getAirApplyHql(param, hql);
        List list = findAll(hql.toString(), params);
        List<AirApplyExcel> excels = new ArrayList<>();
        if(isValidCollection(list)){
            List<AirApplys> applys = (ArrayList<AirApplys>)list;
            AirApplyExcel excel;
            for (AirApplys apply:applys){
                excel=new AirApplyExcel();
                String beginTime = DateHandler.dateToString(apply.getBeginTime(),"yyyyMMdd HH:mm");//yyyyMMdd HH:mm
                String endTime = DateHandler.dateToString(apply.getEndTime(),"yyyyMMdd HH:mm");
                if(beginTime.substring(0,8).equals(endTime.substring(0,8))){
                    excel.setPlanTime(beginTime+" 至 "+endTime.substring(9,14));
                }else {
                    excel.setPlanTime(beginTime+" 至 "+endTime);
                }
                excel.setUserName(apply.getUserName());
                excel.setNumber(apply.getNumber());
                excel.setPlanType(apply.getPlanType());
                List<PlaneInfo> planeList = objectMapper.readValue(apply.getPlanes(), new TypeReference<List<PlaneInfo>>() {});
                if(isValidCollection(planeList)){
                    StringBuffer sb=new StringBuffer();
                    for (PlaneInfo planeInfo:planeList){
                        sb.append(planeInfo.getName()).append("、");
                    }
                    excel.setPlanes(sb.substring(0,sb.lastIndexOf("、")));
                }
                List<AirspaceVo> airList = objectMapper.readValue(apply.getAirInfo(), new TypeReference<List<AirspaceVo>>() {});
                AirspaceVo airspaceVo = airList.get(0);
                excel.setSpaceName(airspaceVo.getLocation());
                excel.setAirTerritorial(airspaceVo.getTerritorial());
                excel.setHigh(airspaceVo.getHigh());
                excel.setApplyTime(DateHandler.dateToString(apply.getCreateTime(),"yyyy-MM-dd"));
                excel.setStatus(AirApplyEnum.name(apply.getStatus(),"status"));
                excels.add(excel);
            }
            LinkedHashMap linkedHashMap = objectMapper.readValue(URLDecoder.decode(param.getMap(),"utf-8"), LinkedHashMap.class);
            XSSFExcelTool.exportExcel(linkedHashMap, "sheet1", "空域申请", excels, AppContextHelper.getRequest(), AppContextHelper.getResponse());
        }
    }

    @Override
    @Transactional
    public void auditAirApply(String airplanId,String param) throws Exception {
        AirApplyPO po = findOne(airplanId);
        assertNotNull(po);
        Map<String,Object> map = objectMapper.readValue(param, Map.class);
        String s = map.get("status").toString();
        assertNotNull(s);
        Integer status = Integer.valueOf(s);
        if(1==status){
            po.setStatus(AirApplyEnum.审核通过.code());
        }else {//失败，需要原因
            po.setStatus(AirApplyEnum.未通过审核.code());
            String r = map.get("reason").toString();
            assertNotBlank(r);
            po.setReason(r);
            //TODO 消息推送
        }
        po.setAuditTime(new Date());
        save(po);
    }
}
