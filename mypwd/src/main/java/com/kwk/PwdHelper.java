package com.kwk;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PwdHelper {
    private static char[] charMap = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '*', '&', '#', '[', ']', '{', '}', '_', '='
    };

    public static String getPwd(int len) {
        int i;  //生成的随机数
        int count = 0; //生成的密码的长度

        StringBuffer stringBuffer = new StringBuffer("");
        SecureRandom r = getSecureRandom();
        while (count < len) {
            //生成 0 ~ 密码字典-1之间的随机数
            i = r.nextInt(charMap.length);
            stringBuffer.append(charMap[i]);
            count++;
        }
        return stringBuffer.toString();
    }

    private static SecureRandom getSecureRandom() {
        final long offset = 578331451;  // offset为固定值，避免被猜到种子来源（和密码学中的加salt有点类似）
        long seed = System.currentTimeMillis() + offset;
        SecureRandom secureRandom;

        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(seed);
            return secureRandom;
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException();
        }
    }
}
