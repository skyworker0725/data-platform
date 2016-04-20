package tv.vego.dp.test;

import org.junit.Test;
import tv.vego.dp.util.coder.MD5Util;

/**
 * 加/解密测试
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/17 9:27
 */
public class EncryptoTest
{
    @Test
    public void test() throws Exception
    {
        String output = MD5Util.encode("fdsafFVFDSDSBvfds陆奇城际硅酸枯一工左地开机   SFDS F ");

        System.err.println(output);
    }
}
