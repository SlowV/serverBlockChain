package com.t1708e.blockchain.util;

public class MessageUtil {
    public static String lang_vn = "vn";
    public static String lang_en = "en";
    public static final String NOT_FOUND = "not_found";
    public static final String SUCCESS = "success";
    public static String getMsg(String lang, String key){
        String msg = "";
        if (lang_vn.equals(lang)){
            if (NOT_FOUND.equals(key)){
                msg = "Khong tim thay!";
            }else if(SUCCESS.equals(key)){
                msg = "Thanh cong";
            }
        }else{
            if (NOT_FOUND.equals(key)){
                msg = "Not found!";
            }else if(SUCCESS.equals(key)){
                msg = "success!";
            }
        }
        return msg;
    }
}
