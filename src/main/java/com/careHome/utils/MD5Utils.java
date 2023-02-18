package com.careHome.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {

    private static char[] arr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    //开始加密
    public static String encrypt(String pwd) {
        String encryptPwd = pwdSalt(pwd);
        return encryptPwd;
    }

    //加盐加密
    private static String pwdSalt(String pwd) {
        //随机取得盐值
        String salt = randomSalt();
        //System.out.println("oldSalt:" + salt);
        //将盐值加到密码后面
        String hexpass = pwd + salt;
        //定义一个随机数作为加密次数
        int randomCount = (int) (Math.random() * 90 + 10);
        //System.out.println("oldRandomCount:" + randomCount);
        //开始加密
        for (int i = 0; i < randomCount; i++) {
            hexpass = DigestUtils.md5Hex(hexpass);
        }
        //hexpass就是加盐后随机加密次数的结果
        //将盐值拼接到后面
        hexpass += salt;
        //将次数拼接到后面
        hexpass = randomCount + hexpass;
        return hexpass;
    }

    //随机盐值
    private static String randomSalt() {
        char[] randarr = new char[2];
        for (int i = 0; i < randarr.length; i++) {
            int ranIndex = (int) (Math.random() * arr.length);
            randarr[i] = arr[ranIndex];
        }
        return new String(randarr);

    }

    //开始解密
    public static String decrypt(String decryptpwd, String newPassword) {
        String newEncryptPwd = pwdWithoutSalt(decryptpwd, newPassword);
        return newEncryptPwd;
    }

    private static String pwdWithoutSalt(String decryptpwd, String newPassword) {
        //System.out.println("原加密字符串：" + decryptpwd);
        //截取最后两位
        String salt = decryptpwd.substring(decryptpwd.length() - 2, decryptpwd.length());
        // System.out.println("newSalt:" + salt);
        //原字符串截掉盐值
        //String withoutSalt = decryptpwd.substring(0, decryptpwd.length() - 2);
        //截取前两位
        int randomCount = Integer.parseInt(decryptpwd.substring(0, 2));
        //原字符串截掉随机次数
        //String withoutRandomCount = decryptpwd.substring(2, decryptpwd.length() - 2);
        //System.out.println("截取后：" + withoutRandomCount);
        //System.out.println("newRandomCount:" + randomCount);
        newPassword = newPassword + salt;
        for (int i = 0; i < randomCount; i++) {
            newPassword = DigestUtils.md5Hex(newPassword);
        }
        newPassword = newPassword + salt;
        newPassword = randomCount + newPassword;
        return newPassword;
    }

//    public static void main(String[] args) {
//        String pwd = "Aa700824.";
//        String encryptPwd = encrypt(pwd);
//        System.out.println(encryptPwd);
//        String pwd1 = "Aa700824.";
//        String newEncryptPwd = decrypt(encryptPwd, pwd1);
//        System.out.println(newEncryptPwd);
//        if (encryptPwd.equals(newEncryptPwd)) {
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }
//    }

}
