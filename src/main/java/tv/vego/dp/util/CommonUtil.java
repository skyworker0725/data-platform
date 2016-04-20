package tv.vego.dp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 常用工具
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/1/15 14:03
 */
public class CommonUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 将指定字符串写入指定路径文件
     *
     * @param filePath 路径文件
     * @param content  文件内容
     *
     * @return 写入的文件引用
     */
    public static File stringToFile(String filePath, String content)
    {
        BufferedWriter out = null;

        File f = new File(filePath);
        try
        {
            if (!f.exists())
            {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(f);
            out = new BufferedWriter(fw);
            out.write(content, 0, content.length() - 1);
        }
        catch (IOException e)
        {
            LOGGER.error("文件写入出错", e);

            throw new RuntimeException(e);
        }
        finally
        {
            if (null != out)
            {
                try
                {
                    out.close();
                }
                catch (IOException ignore)
                {
                }
            }
        }

        return f;
    }
}
