package tv.vego.dp.util;

/**
 * 参数名常量
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/17 14:25
 */
public interface ParamConstant
{
    int RESULT_SUCCESS_CODE = 0;

    String PAGE_NUM = "page";

    String PAGE_SIZE = "rows";

    String PARAM_ARRAY_SUFFIX = "s";

    /**
     * 任务类型：脚本
     */
    Integer QUARTZ_TASK_TYPE_SCRIPT = 1;

    /**
     * 任务类型：JAVA
     */
    Integer QUARTZ_TASK_TYPE_JAVA = 2;

    Integer QUARTZ_TASK_STATUS_INVALID = 0;

    Integer QUARTZ_TASK_STATUS_VALID = 1;

    Integer QUARTZ_TASK_STATUS_DELETED = 2;

    /**
     * 任务参数类型
     */
    String QUARTZ_TASK_PARAM_TYPE_TXT = "txt";

    String QUARTZ_TASK_PARAM_TYPE_GROOVY = "groovy";

    String EXEC_TASK_INFO_KEY = "taskDefineInfo";

    /**
     * 任务失败禁止重试
     */
    Integer TASK_FAILED_RETRY_DISABLE =0;

    /**
     * 任务失败允许重试
     */
    Integer TASK_FAILED_RETRY_ENABLE = 1;

    /**
     * 菜单不是叶子节点
     */
    Integer SYSTEM_MENU_NOT_LEAF = 0;

    /**
     * 菜单是叶子节点
     */
    Integer SYSTEM_MENU_IS_LEAF = 1;
}
