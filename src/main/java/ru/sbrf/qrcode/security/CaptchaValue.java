package ru.sbrf.qrcode.security;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sbt-litvinov-ay on 19.02.14.
 */

public class CaptchaValue {

    private static final String SPLIT_SYMBOL = "$";
    private static final String SPLIT_SYMBOL_REGEX = "\\"+SPLIT_SYMBOL;
    private static final int CLEAR_TEXT_POSITION = 0;
    private static final int VALID_UNTIL_POSITION = 1;

    private String clearText;
    private DateTime validUntil;

    public static CaptchaValue fromStringValue(String value){
       CaptchaValue captchaValue = new CaptchaValue();
       String[] values = value.split(SPLIT_SYMBOL_REGEX);
       captchaValue.setClearText(values[CLEAR_TEXT_POSITION]);
       captchaValue.setValidUntil(DateTime.parse(values[VALID_UNTIL_POSITION]));
       return captchaValue;
    }

    public String getClearText() {
        return clearText;
    }

    public void setClearText(String clearText) {
        this.clearText = clearText;
    }

    public DateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(DateTime validUntil) {
        this.validUntil = validUntil;
    }

    public String toStringValue(){
        String[] values = new String[2];
        values[CLEAR_TEXT_POSITION] = getClearText();
        values[VALID_UNTIL_POSITION] = getValidUntil().toString();
        return StringUtils.join(values, SPLIT_SYMBOL);
    }

    @Override
    public String toString() {
        return "CaptchaValue{" +
                "clearText='" + clearText + '\'' +
                ", validUntil=" + validUntil +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CaptchaValue that = (CaptchaValue) o;

        if (!clearText.equals(that.clearText)) return false;
        if (!validUntil.equals(that.validUntil)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clearText.hashCode();
        result = 31 * result + validUntil.hashCode();
        return result;
    }
}
