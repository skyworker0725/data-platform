package tv.vego.dp.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.Map;

/**
 * 根据模板和数据生成输出
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/1/13 17:57
 */
public class FreeMarkerUtil
{
    /**
     * @param templatePath 模板文件存放路径
     * @param templateName 模板文件名称
     * @param root         模板参数
     *
     * @return 模板解析后的结果
     */
    public static String generateResult(String templatePath, String templateName, Map<String, Object> root) throws Exception
    {
        Configuration config = new Configuration(Configuration.VERSION_2_3_23);

        //设置要解析的模板所在的目录，并加载模板文件
//        config.setClassForTemplateLoading(FreeMarkerUtil.class, templatePath);
        config.setClassLoaderForTemplateLoading(FreeMarkerUtil.class.getClassLoader(), templatePath);

        //设置包装器，并将对象包装为数据模型
        config.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));

        //获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
        //否则会出现乱码
        Template template = config.getTemplate(templateName, "UTF-8");
        //合并数据模型与模板
        StringWriter resultWriter = new StringWriter();
        template.process(root, resultWriter);

        return resultWriter.toString();
    }
}
