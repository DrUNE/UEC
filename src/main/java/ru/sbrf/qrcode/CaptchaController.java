package ru.sbrf.qrcode;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;
import org.joda.time.DateTime;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.sbrf.qrcode.security.CaptchaValue;
import ru.sbrf.qrcode.security.SecureBase64;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by sbt-litvinov-ay on 11.02.14.
 */
@Controller
public class CaptchaController {

    private static final int COOKIE_MAX_AGE_SEC = 60 * 10;
    public static final String COOKIE_NAME = "Captcha";

    @RequestMapping(value = "/captcha.jpg", method = RequestMethod.GET, headers = "Accept=image/jpeg", produces = "image/jpg")
    public HttpEntity<byte[]> getCaptcha(HttpServletResponse response) {
        try {
            // Switch off disk based caching.
            ImageIO.setUseCache(false);

            Properties props = new Properties();
            props.put(Constants.KAPTCHA_BORDER, "yes");
            props.put("kaptcha.textproducer.font.color", "black");
            props.put("kaptcha.textproducer.char.space", "3");
            props.put(Constants.KAPTCHA_IMAGE_WIDTH, "156");
            props.put(Constants.KAPTCHA_IMAGE_HEIGHT, "65");


            Config config = new Config(props);
            Producer captchaProducer = config.getProducerImpl();


            // create the text for the image
            String capText = captchaProducer.createText();
            BufferedImage img = captchaProducer.createImage(capText);

            // Create a byte array output stream.
            ByteArrayOutputStream bao = new ByteArrayOutputStream();

            // Write to output stream
            ImageIO.write(img, "jpg", bao);

            byte[] imgBytes = bao.toByteArray();

            HttpHeaders httpheaders = new HttpHeaders();
            //httpheaders.setCacheControl("no-store, no-cache");
            //httpheaders.setContentType(MediaType.IMAGE_JPEG);
            //httpheaders.setContentLength(imgBytes.length);

            setCaptchaCookieTo(response, capText);

            return new HttpEntity<byte[]>(imgBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCaptchaCookieTo(HttpServletResponse response, String captchaText){
        CaptchaValue captchaValue = new CaptchaValue();
        captchaValue.setClearText(captchaText);
        captchaValue.setValidUntil(DateTime.now().plusSeconds(COOKIE_MAX_AGE_SEC));

        Cookie cookie = new Cookie(COOKIE_NAME, SecureBase64.encode(captchaValue.toStringValue()));
        cookie.setMaxAge(COOKIE_MAX_AGE_SEC);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
    }
}
