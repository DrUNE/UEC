package ru.sbrf.qrcode;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.servlet.KaptchaExtend;
import com.google.code.kaptcha.util.Config;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by sbt-litvinov-ay on 11.02.14.
 */
@Controller
public class KaptchaController {

//    @RequestMapping(value = "/captcha.jpg", method = RequestMethod.GET)
//    public void captcha(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.captcha(req, resp);
//    }

    @RequestMapping(value = "/captcha.jpg", method = RequestMethod.GET, headers = "Accept=image/jpeg", produces = "image/jpg")
    public HttpEntity<byte[]> getCaptcha2() {
        try {
            Properties props = new Properties();

            Producer kaptchaProducer = null;

            String sessionKeyValue = null;

            String sessionKeyDateValue = null;
            // Switch off disk based caching.
            ImageIO.setUseCache(false);

            props.put("kaptcha.border", "no");
            props.put("kaptcha.textproducer.font.color", "black");
            props.put("kaptcha.textproducer.char.space", "5");

            Config config = new Config(props);
            kaptchaProducer = config.getProducerImpl();
            sessionKeyValue = config.getSessionKey();
            sessionKeyDateValue = config.getSessionDate();

            // create the text for the image
            String capText = kaptchaProducer.createText();
            BufferedImage img = kaptchaProducer.createImage(capText);

            // Create a byte array output stream.
            ByteArrayOutputStream bao = new ByteArrayOutputStream();

            // Write to output stream
            ImageIO.write(img, "jpg", bao);

            byte[] imgBytes = bao.toByteArray();

            HttpHeaders httpheaders = new HttpHeaders();
            //httpheaders.setCacheControl("no-store, no-cache");
            //httpheaders.setContentType(MediaType.IMAGE_JPEG);
            //httpheaders.setContentLength(imgBytes.length);
            return new HttpEntity<byte[]>(imgBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @RequestMapping(value = "/register", method = RequestMethod.GET)
//    public ModelAndView registerGet(@RequestParam(value = "error", required = false) boolean failed,
//                                    HttpServletRequest request) {
//        ModelAndView model = new ModelAndView("register-get");
//
//        //
//        // model MUST contain HTML with <img src="/captcha.jpg" /> tag
//        //
//
//        return model;
//    }
//
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ModelAndView registerPost(@RequestParam(value = "email", required = true) String email,
//                                     @RequestParam(value = "password", required = true) String password, HttpServletRequest request) {
//        ModelAndView model = new ModelAndView("register-post");
//
//        if (email.isEmpty())
//            throw new RuntimeException("email empty");
//
//        if (password.isEmpty())
//            throw new RuntimeException("empty password");
//
//        String captcha = request.getParameter("captcha");
//
//        if (!captcha.equals(getGeneratedKey(request)))
//            throw new RuntimeException("bad captcha");
//
//        //
//        // eveyting is ok. proceed with your user registration / login process.
//        //
//
//        return model;
//    }

}
