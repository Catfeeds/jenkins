package com.hfocean.uavportal.core.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.IOUtils;
import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class OssService {

	private final Logger log = LoggerFactory.getLogger(OssService.class);

	//存储路径前缀
	private String prefix = "pub/attachment/";

	@Value("${oss.endpoint}")
	private String endpoint;

	@Value("${oss.accessId}")
	private String accessId;

	@Value("${oss.accessKey}")
	private String accessKey;

	@Value("${oss.bucket}")
	private String bucket;

    /**
     * 是否符合上传文件类型
     * @param fileName
     */
    public void allowIf(String fileName){
        if (null==suffixFileName(fileName))
            throw new BaseBusinessException(BaseBusinessError.PARAMETER_ERROR.getCode(),String.format("不支持文件:%s 类型上传", fileName));

    }

    /**
     * 上传文件并返回结果
      * @param is
     * @param fileName
     * @return
     */
	public Map<String,Object> simpleUpload(InputStream is, String fileName){
        Map<String, Object> result = new LinkedHashMap<>(3);
        String randomFileName = upload(is, suffix(fileName));
        result.put("url",randomFileName);//相对路径
        result.put("fullUrl",getOssUrl()+randomFileName);//全路径
        result.put("srcName",fileName);//原文件名称
		return result;
	}

    /**
     * 上传文件并返回相对路径文件名
     * @param is
     * @param suffix 文件名后缀
     * @return
     */
	public String upload(InputStream is,String suffix){
		String finalFileName = null;
		try {
			OSSClient ossClient = new OSSClient(endpoint,accessId,accessKey);
            finalFileName = randomFileName() + suffix;
			ossClient.putObject(bucket, finalFileName, is);
			// 关闭client
			ossClient.shutdown();
			is.close();
		} catch (Exception e) {
			log.error("oss文件上传异常",e);
		} finally {
			IOUtils.safeClose(is);
		}
		return finalFileName;
	}

	/**
	 * 存储路径前缀+时间戳+uuid文件名(无后缀)
	 * @return
	 */
	private String randomFileName() {
		return randomPath() + uuidFileName();
	}

    /**
     * 以uuid为文件名
     * @return
     */
	public static String uuidFileName(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

    /**
     * 存储路径前缀+时间戳
     * @return
     */
	public String randomPath(){
		return prefix+new SimpleDateFormat("yyyy/MM/dd").format(new Date())+"/";
	}

    /**
     * 获取文件后缀
     */
    public String suffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".")).trim();
    }

	/**
	 * 获取文件后缀
	 * @param fileName
	 * @return null 系统不支持此后缀
	 */
	public static String suffixFileName(String fileName){
		try{
			int suffIndex = fileName.lastIndexOf(".");
			if(-1!=suffIndex){
				String suffix = fileName.substring(suffIndex+1).trim();//后缀
				if(suffix.length()<=1) return null;
				return FileTypeEnum.valueOf(suffix.toUpperCase()).type();
			}else{
				return null;
			}
		}catch(Exception e){
			return null;
		}
	}

	public String getOssUrl(){
		return "http://"+bucket+"."+endpoint+"/";
	}

}
