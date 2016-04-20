package tv.vego.dp.controller.report;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;
import tv.vego.dp.model.CombReportDefine;
import tv.vego.dp.model.CombReportDetail;
import tv.vego.dp.model.QueryWidgetDefine;
import tv.vego.dp.service.CombReportDefineService;
import tv.vego.dp.service.CombReportDetailService;
import tv.vego.dp.service.QueryWidgetDefineService;
import tv.vego.dp.vo.CombReportDefineVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合报表
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/29 10:56
 */
@Controller
@RequestMapping(value = "/mng/comb")
public class CombReportController
{
    @Autowired
    private CombReportDefineService combReportDefineService;

    @Autowired
    private CombReportDetailService combReportDetailService;

    @Autowired
    private QueryWidgetDefineService queryWidgetDefineService;

    @RequestMapping(value = "/detail/{combId}")
    @ResponseBody
    public CombReportDefineVO getCombReportDetail(@PathVariable(value = "combId") Long combId)
    {
        CombReportDefineVO combReportDefineVO = new CombReportDefineVO();

        //获取组合报表信息
        CombReportDefine combReportDefine = combReportDefineService.selectByKey(combId);
        BeanUtils.copyProperties(combReportDefine, combReportDefineVO);

        //获取组合报表详情
        Example example = new Example(CombReportDetail.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("combId", combId);
        example.setOrderByClause("displayOrder");
        combReportDefineVO.setCombDetails(combReportDetailService.selectByExample(example));

        //获取组合报表查询控件信息
        List<QueryWidgetDefine> queryWidgetDefines = queryWidgetDefineService.selectReportUseWidgets(combId);
        if (CollectionUtils.isNotEmpty(queryWidgetDefines))
        {
            List<JSONObject> queryWidgetObjects = new ArrayList<>(queryWidgetDefines.size());

            for (QueryWidgetDefine queryWidgetDefine : queryWidgetDefines)
            {
                JSONObject jsonObject = JSON.parseObject(queryWidgetDefine.getExtAttrs());

                JSONObject queryWidgetObject = (JSONObject) JSON.toJSON(queryWidgetDefine);
                for (String widgetFiledKey : queryWidgetObject.keySet())
                {
                    if ("extAttrs".equals(widgetFiledKey))
                    {
                        continue;
                    }

                    jsonObject.put(widgetFiledKey, queryWidgetObject.get(widgetFiledKey));
                }

                queryWidgetObjects.add(jsonObject);
            }

            combReportDefineVO.setQueryWidgets(queryWidgetObjects);
        }

        return combReportDefineVO;
    }
}
