package com.lybank.controller.system;

import com.google.zxing.WriterException;
import com.lybank.config.validate.GoogleAuthenticator;
import com.lybank.entity.system.GoogleV;
import com.lybank.service.system.GoogleVService;
import com.lybank.util.QrCodeCreateUtil;
import com.lybank.util.Result;
import com.lybank.util.code.DictCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/googlev")
public class GoogleVController {
    @Autowired
    private GoogleVService gvService;

    @RequestMapping("/add")
    @ResponseBody
    public String addGoogleV(@RequestParam("userName") String userName) {

        String secret = GoogleAuthenticator.generateSecretKey();
        // secret = GoogleAuthenticator.createSecretKey();
        // 把这个qrcode生成二维码，用google身份验证器扫描二维码就能添加成功
        String qrcode = GoogleAuthenticator.getQRBarcode(userName, secret);
        GoogleV gv = new GoogleV();
        gv.setUserName(userName);
        gv.setSecret(secret);
        gv.setQrcodeStr(qrcode);
        gv.setStatus(DictCode.ENABLE);
        gvService.saveGoogleV(gv);
        return "success";
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result updateGoogleV(GoogleV gv) {
        gv = gvService.updateGoogleV(gv);
        return new Result().ResultSuccess().setObject(gv);
    }
    //

    @RequestMapping(value = "/generateImage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getImage(String content, HttpServletResponse response) throws IOException, WriterException {
        if(StringUtils.isNotEmpty(content)){
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            OutputStream out = response.getOutputStream();
            Boolean r = QrCodeCreateUtil.createQrCode(out, content);
            out.flush();
            out.close();
        }
    }
}
