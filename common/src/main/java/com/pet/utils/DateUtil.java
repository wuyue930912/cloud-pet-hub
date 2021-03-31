package com.pet.utils;

import com.pet.constant.SymbolConstant;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateUtil {
    private DateUtil() {
    }

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String date2Str(Date date) {
        return format.format(date);
    }

    public Date str2Date(String str) {
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            log.error("parse error because: [{}]", e.getMessage());
        }
        return date;
    }

    public String getDate(Date date) {
        return format.format(date).split(SymbolConstant.SPACE)[0];
    }

    public String getTime(Date date) {
        return format.format(date).split(SymbolConstant.SPACE)[1];
    }

}
