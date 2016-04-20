package tv.vego.dp.util.coder;

import java.security.MessageDigest;

/**
 * MD5加解密工具
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/17 9:47
 */
public class MD5Util
{
    /***
     * MD5加密 生成32位md5码
     *
     * @param inStr 待加密字符串
     *
     * @return 返回32位md5码
     */
    public static String encode(String inStr) throws Exception
    {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes)
        {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16)
            {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }
}
