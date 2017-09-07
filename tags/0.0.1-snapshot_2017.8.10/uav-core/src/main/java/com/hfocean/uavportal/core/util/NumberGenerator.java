package com.hfocean.uavportal.core.util;

import com.hfocean.common.util.AppContextHelper;
import com.hfocean.uavportal.core.serial.po.SerialNumber;
import com.hfocean.uavportal.core.serial.repository.SerialNumberRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

/**
 * 编号生成器
 *
 * @author Leslie.Lam
 * @create 2017-06-21 15:41
 **/
public class NumberGenerator {

    private static String AIR_APPLY ="air_apply";//空域申请流水号名

    private static String AIR_PLAN ="air_plan";//飞行计划流水号名

    private static final SerialNumberRepository serialNumberRepository= AppContextHelper.getBean(SerialNumberRepository.class);

    public static String getAirApplyNo(){
        return new StringBuffer().append("KY")
                .append( new SimpleDateFormat("yyMMdd", Locale.CHINESE).format(Calendar.getInstance().getTime()))
                .append(getMaxSerialNo(AIR_APPLY,3)).toString();
    }

    /**
     * 如2017年6月12日广州大学城一类报告空域的计划编号为170612gzA01001,即70612表示日期，GZ代表区域，A代表一类报告空域，01表示一类报告空域编号，001表示计划编号
     * 如2017年6月12日广州动物园二类报告空域的计划编号为170612GZB01001,即170612表示日期，GZ代表区域，B代表二类报告空域，01表示二类报告空域编号，001表示计划编号
     * 如2017年6月12日广州管制空域的计划编号为170612GZC001,即170612表示日期，GZ代表广州地区，C代表管制空域，001表示计划编号
     */
    public static String getAirPlanNo(Integer type,String area,String airSpaceNo){
        return new StringBuffer()
                .append( new SimpleDateFormat("yyMMdd", Locale.CHINESE).format(Calendar.getInstance().getTime()))
                .append(area)
                .append(AirPlanType.getNoByCode(type))//
                .append(Objects.equals(type,AirPlanType.管制飞行计划.code())?"":airSpaceNo)
                .append(getMaxSerialNo(AIR_PLAN,3)).toString();
    }

    private  static String getMaxSerialNo(String serialName,Integer length) {
        String serialNoStr=null;
        SerialNumber serialNumber = serialNumberRepository.findByserialName(serialName);
        if(serialNumber!=null){
            Integer serialNo = serialNumber.getSerialNo();
            if(serialNo!=null){
                serialNoStr = getEnoughStr(serialNo, length);
            }
            serialNumber.setSerialNo(++serialNo);
            serialNumberRepository.save(serialNumber);
        }
        return serialNoStr;
    }

    private static String getEnoughStr(Integer number,Integer length){
        return String.format(new StringBuffer().append("%0").append(length).append('d').toString(), number);
    }

    public enum AirPlanType{

        管制飞行计划(0,"C"),一类飞行计划(1,"A"),二类飞行计划(2,"B");

        private Integer code;

        private String number;

        public static String getNoByCode(Integer code){
            for (AirPlanType value: values()){
                if(Objects.equals(code,value.code())){
                    if(Objects.equals(code,value.code()))return value.number();
                }
            }
            return null;
        }

        AirPlanType(Integer code, String number) {
            this.code = code;
            this.number = number;
        }

        public String number() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public Integer code() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }
}
