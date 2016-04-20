package tv.vego.dp.vo;

import com.alibaba.fastjson.JSONObject;
import tv.vego.dp.model.CombReportDefine;
import tv.vego.dp.model.CombReportDetail;
import tv.vego.dp.model.QueryWidgetDefine;

import java.util.List;

/**
 * 组合报表详情
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/29 11:22
 */
public class CombReportDefineVO extends CombReportDefine
{
    private List<CombReportDetail> combDetails;

    private List<JSONObject> queryWidgets;

    public List<CombReportDetail> getCombDetails()
    {
        return combDetails;
    }

    public void setCombDetails(List<CombReportDetail> combDetails)
    {
        this.combDetails = combDetails;
    }

    public List<JSONObject> getQueryWidgets()
    {
        return queryWidgets;
    }

    public void setQueryWidgets(List<JSONObject> queryWidgets)
    {
        this.queryWidgets = queryWidgets;
    }
}
