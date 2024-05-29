package org.mychat.mychatserver.controller;

import org.mychat.mychatserver.entity.Captcha;
import org.mychat.mychatserver.mapper.CaptchaMapper;
import org.mychat.mychatserver.utils.CaptchaUtils;
import org.mychat.mychatserver.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CaptchaController {

    @Autowired
    private EmailUtils emailSender;
    @Autowired
    private CaptchaMapper captchaMapper;

    //发送验证码
    @PostMapping("/sendCaptchaByEmail")
    public Map<String, Object> sendCaptchaByEmail(String email) {
        Map<String, Object> response = new HashMap<>();
        String captcha = CaptchaUtils.generateCaptcha(6); // 生成6位数字验证码
        boolean success = emailSender.sendEmail(email, "验证码", "您的验证码是："+captcha);
        response.put("success", success);
        if (success) {
            // 保存验证码到数据库
            Captcha captchaEntity = new Captcha();
            captchaEntity.setEmail(email);
            captchaEntity.setCode(captcha);
            captchaEntity.setCreatedAt(LocalDateTime.now());
            captchaEntity.setUsed(false);
            captchaMapper.save(captchaEntity);
        }
        return response;
    }

    //匹配验证码
    @PostMapping("/matchCaptcha")
    public Map<String, Object> matchCaptcha(String email, String code) {
        Map<String, Object> response = new HashMap<>();
        boolean success = captchaMapper.isCaptchaMatch(email, code);
        response.put("success", success);
        if (success) {
            captchaMapper.markCaptchaAsUsed(email);
        }
        return response;
    }
}
