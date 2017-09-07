package com.hfocean.uavportal.web.geetest.sdk;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;


public class GeetestHelper{

	
	public static void geetestGet(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(), 
				GeetestConfig.isnewfailback());

		String resStr = "{}";
		
		//进行验证预处理
		int gtServerStatus = gtSdk.preProcess(GeetestConfig.SESSION_KEY);
		
		//将服务器状态设置到session中
		request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
		//将userid设置到session中
		request.getSession().setAttribute(GeetestConfig.SESSION_KEY, GeetestConfig.SESSION_KEY);
		
		resStr = gtSdk.getResponseStr();

		PrintWriter out = response.getWriter();
		out.println(resStr);
	}
	
	private static final String NULL_MSG = "极验验证不通过，请重新验证";
	
	public static void geetestPost(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(), 
				GeetestConfig.isnewfailback());
			
		String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
		String validate = request.getParameter(GeetestLib.fn_geetest_validate);
		String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);
		
		//从session中获取gt-server状态
		Integer attribute = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
		if(attribute==null)throw new BaseBusinessException(BaseBusinessError.PARAMETER_ERROR.getCode() , NULL_MSG);
		int gt_server_status_code = attribute;
		
		//从session中获取userid
		String userid = (String)request.getSession().getAttribute(GeetestConfig.SESSION_KEY);
		
		int gtResult = 0;

		if (gt_server_status_code == 1) {
			//gt-server正常，向gt-server进行二次验证
				
			gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, userid);
		} else {
			// gt-server非正常情况下，进行failback模式验证
				
			System.out.println("failback:use your own server captcha validate");
			gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
		}

		if (gtResult != 1) {
			// 验证失败
			throw new BaseBusinessException(BaseBusinessError.CODE_VERIFY_ERROR.getCode(),BaseBusinessError.CODE_VERIFY_ERROR.getMessage());
		}
	}

}
