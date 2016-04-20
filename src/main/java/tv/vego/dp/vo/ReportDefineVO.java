package tv.vego.dp.vo;

import tv.vego.dp.model.QueryWidgetDefine;
import tv.vego.dp.model.ReportColumnDefine;
import tv.vego.dp.model.ReportDefine;

import java.util.List;

/**
 * 页面报表定义值对象
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/17 15:48
 */
public class ReportDefineVO extends ReportDefine
{
    private List<ReportColumnDefine> columns;

    public List<ReportColumnDefine> getColumns()
    {
        return columns;
    }

    public void setColumns(List<ReportColumnDefine> columns)
    {
        this.columns = columns;
    }
}
