package com.hfocean.uavportal.core.auth.service.test;

import com.hfocean.uavportal.core.MainTest;
import com.hfocean.uavportal.core.oss.FileTypeEnum;
import com.hfocean.uavportal.core.oss.OssService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * OSS单元测试 类
 * @author Gene
 *
 */
public class OssTest extends MainTest{

	@Autowired
	OssService ossService;
	
	@Test
	public void testUploadFile() throws FileNotFoundException{
		String filePath = "D://312312313.txt";
		File file = new File(filePath);
		FileInputStream fileInputStream = new FileInputStream(file);
		ossService.simpleUpload(fileInputStream,"312312313.txt");
	}
	
}
