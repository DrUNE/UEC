package ru.sbrf.qrcode.security;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;

/**
 * Created by sbt-litvinov-ay on 19.02.14.
 */
public class SecureBase64 {
    public static final Charset CHARSET_ENCODING = Charset.forName("UTF-8");

    public static String encode(String value){
        return Base64.encodeBase64URLSafeString(value.getBytes(CHARSET_ENCODING));
    }

    public static String decode(String base64value){
        return new String(Base64.decodeBase64(base64value.getBytes(CHARSET_ENCODING)), CHARSET_ENCODING);
    }
}
