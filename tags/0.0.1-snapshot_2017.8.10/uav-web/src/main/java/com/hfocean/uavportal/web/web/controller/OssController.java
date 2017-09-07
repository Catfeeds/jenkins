package com.hfocean.uavportal.web.web.controller;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.util.VerdictUtil;
import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.oss.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;


@Controller
public class OssController {
	
    @Autowired
    private OssService ossService;

    /**
     * 文件上传
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/oss/uploadFile")
    public WebResponse uploadFile(MultipartHttpServletRequest request) throws Exception{
        List<Map<String,Object>> list = new CopyOnWriteArrayList<>();
        MultiValueMap<String,MultipartFile> filesMap = request.getMultiFileMap();
        if(null==filesMap||filesMap.size()==0)throw new BaseBusinessException(BaseBusinessError.PARAMETER_ERROR.getCode(),"请选择文件上传");
        for (String name : filesMap.keySet()) {
            List<MultipartFile> files = filesMap.get(name);
            for (MultipartFile file : files) {
                //取得当前上传文件的文件名称
                String fileName = file.getOriginalFilename();
                VerdictUtil.assertNotBlank(fileName);
                //是否符合上传文件类型
                ossService.allowIf(fileName);
                //上传文件并返回结果添加到结果集
                list.add(ossService.simpleUpload(file.getInputStream(),fileName));
            }
        }
        if(list.size()<=0){
            throw new BaseBusinessException(BaseBusinessError.PARAMETER_ERROR.getCode(),"无文件上传");
        }else{
            return new WebResponse(list);
        }
    }

    /**
     * 单文件上传
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/single/upload")
    public WebResponse uploadFile(MultipartFile file) throws Exception{
        if(null==file)throw new BaseBusinessException(BaseBusinessError.PARAMETER_ERROR.getCode(),"请选择文件上传");
        //取得当前上传文件的文件名称
        String fileName = file.getOriginalFilename();
        VerdictUtil.assertNotBlank(fileName);
        //是否符合上传文件类型
        ossService.allowIf(fileName);
        Map<String, Object> result;
        try {
            result = ossService.simpleUpload(file.getInputStream(), fileName);
        } catch (Exception e) {
            throw new BaseBusinessException(BaseBusinessError.PARAMETER_ERROR.getCode(),"上传文件失败");
        }
        return new WebResponse(result);
    }
}
