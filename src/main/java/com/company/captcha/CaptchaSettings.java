package com.company.captcha;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CaptchaSettings {
    //reCAPTCHA V3
    @Value("6LcOFQAVAAAAABmN69dz66gEsV4t_euVBzoF9VMq")
    private String siteV3;
    @Value("6LcOFQAVAAAAAE0NNoZqOE4kYU_GQbGWzm2x_kf8")
    private String secretV3;
    @Value("https://www.google.com/recaptcha/api/siteverify")
    private String siteVerifyUrl;


    public CaptchaSettings() {
    }


    public String getSiteVerifyUrl() {
        return siteVerifyUrl;
    }

    public void setSiteVerifyUrl(String siteVerifyUrl) {
        this.siteVerifyUrl = siteVerifyUrl;
    }

    public String getSiteV3() {
        return siteV3;
    }

    public void setSiteV3(String siteV3) {
        this.siteV3 = siteV3;
    }

    public String getSecretV3() {
        return secretV3;
    }

    public void setSecretV3(String secretV3) {
        this.secretV3 = secretV3;
    }

}
