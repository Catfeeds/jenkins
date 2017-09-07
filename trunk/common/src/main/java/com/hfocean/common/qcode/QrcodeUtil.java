package com.hfocean.common.qcode;

import com.hfocean.common.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
* 
* @author Leslie.Lam
* @create 2017-06-25 11:29
**/
public class QrcodeUtil {

    public static void createQrcodeByStr(String content,OutputStream os) throws IOException {
        InputStream is = QrcodeHandler.createQRcode(content);
        int len;
        byte[] b = new byte[1024];
        while((len = is.read(b))!=-1){
            os.write(b, 0, len);
        }
        os.flush();
        IOUtils.close(os);
        IOUtils.close(is);
    }
}
