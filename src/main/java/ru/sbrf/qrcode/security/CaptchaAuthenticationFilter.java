package ru.sbrf.qrcode.security;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.joda.time.DateTime;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.util.Assert;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * Created by sbt-litvinov-ay on 18.02.14.
 */
public class CaptchaAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
    private final static Cache<String, CaptchaValue> captchas = CacheBuilder.newBuilder()
            .expireAfterWrite(20, TimeUnit.MINUTES)
            .build();

    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "Captcha");
        if (cookie == null) {
            throw new PreAuthenticatedCredentialsNotFoundException("Missing Captcha");
        }
        CaptchaValue captchaValue = decode(cookie);
        validate(captchaValue, captchaValue.getClearText());
        return "anonymous";
    }

    private void validate(CaptchaValue captchaValue, String userInput) {
        if (!captchaValue.getClearText().equals(userInput)){
            throw new PreAuthenticatedCredentialsNotFoundException("Captcha invalid by wrong input.");
        }

        if (captchaValue.getValidUntil().isBefore(DateTime.now())){
            throw new PreAuthenticatedCredentialsNotFoundException("Captcha invalid by timeout.");
        }

        if (captchas.getIfPresent(captchaValue.getClearText()) != null){
            throw new PreAuthenticatedCredentialsNotFoundException("Captcha already been used.");
        } else {
            captchas.put(captchaValue.getClearText(), captchaValue);
        }
    }

    private CaptchaValue decode(Cookie cookie) {
        Assert.notNull(cookie);
        String cookieValue = cookie.getValue();
        Assert.notNull(cookieValue);
        return CaptchaValue.fromStringValue(SecureBase64.decode(cookieValue));
    }

    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }
}
