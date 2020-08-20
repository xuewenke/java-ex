package com.x.atrs.util.encryption.sm4;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SM4Utils {

    /**
     * 加密密钥长度严格控制16位
     */
    public final static String SM4_SECRET_KEY = "1111111111111111";
    public boolean hexString = false;

    /**
     * 编码格式
     */
    private final static String CHARSET_NAME = "UTF-8";

    /**
     * 明文特殊字符的处理正则
     */
    private static final Pattern PLANTEXT_PATTERN = Pattern.compile("\\s*|\t|\r|\n");


    public SM4Utils() {
    }


    /**
     * ECB 模式加密
     *
     * @param plainText
     * @return
     */
    public String encryptData_ECB(String plainText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            if (hexString) {
                keyBytes = hexStringToBytes(SM4_SECRET_KEY);
            } else {
                keyBytes = SM4_SECRET_KEY.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText.getBytes(CHARSET_NAME));
            String cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Matcher m = PLANTEXT_PATTERN.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ECB 模式解密
     *
     * @param cipherText
     * @return
     */
    public String decryptData_ECB(String cipherText) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            if (hexString) {
                keyBytes = hexStringToBytes(SM4_SECRET_KEY);
            } else {
                keyBytes = SM4_SECRET_KEY.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_ecb(ctx, new BASE64Decoder().decodeBuffer(cipherText));
            return new String(decrypted, CHARSET_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String encryptData_CBC(String plainText, String iv) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = hexStringToBytes(SM4_SECRET_KEY);
                ivBytes = hexStringToBytes(iv);
            } else {
                keyBytes = SM4_SECRET_KEY.getBytes();
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes(CHARSET_NAME));
            String cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Matcher m = PLANTEXT_PATTERN.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decryptData_CBC(String cipherText, String iv) {
        try {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = hexStringToBytes(SM4_SECRET_KEY);
                ivBytes = hexStringToBytes(iv);
            } else {
                keyBytes = SM4_SECRET_KEY.getBytes();
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, new BASE64Decoder().decodeBuffer(cipherText));
            return new String(decrypted, CHARSET_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }

        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


    public static void main(String[] args) {
        String plainText = "abcd";

        SM4Utils sm4 = new SM4Utils();
        sm4.hexString = false;

        System.out.println("ECB模式加密");
        String cipherText = sm4.encryptData_ECB(plainText);
        System.out.println("密文: " + cipherText);
        System.out.println("");

        plainText = sm4.decryptData_ECB(cipherText);
        System.out.println("明文: " + plainText);
        System.out.println("");

        System.out.println("CBC模式加密");
        String iv = "UISwD9fW6cFh9SNS";
        cipherText = sm4.encryptData_CBC(plainText, iv);
        System.out.println("密文: " + cipherText);
        System.out.println("");

        plainText = sm4.decryptData_CBC(cipherText, iv);
        System.out.println("明文: " + plainText);

        System.out.println("CBC模式解密");
        System.out.println("密文：4esGgDn/snKraRDe6uM0jQ==");
        String cipherText2 = "4esGgDn/snKraRDe6uM0jQ==";
        plainText = sm4.decryptData_CBC(cipherText2, iv);
        System.out.println("明文: " + plainText);
    }


}
