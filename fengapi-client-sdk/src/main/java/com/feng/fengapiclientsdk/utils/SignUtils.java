package com.feng.fengapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import org.apache.commons.lang3.StringUtils;

public class SignUtils {

    public static String getSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        if (StringUtils.isBlank(body)) {
            body = "";
        }
        String content = body.toString() + "." + secretKey;
        return md5.digestHex(content);
    }
}
