package tv.vego.dp.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tv.vego.dp.controller.BaseController;
import tv.vego.dp.service.util.ReportGetter;
import tv.vego.dp.util.ParamConstant;
import tv.vego.dp.vo.ReportDefineVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用数据获取
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2015/12/17 10:22
 */
@Controller
@RequestMapping(value = "/general/report")
public class GeneralDataController extends BaseController
{
    @RequestMapping(value = "/data/{reportId}")
    @ResponseBody
    public Object getReportData(@PathVariable(value = "reportId") Long reportId
            , @RequestParam(value = ParamConstant.PAGE_NUM, required = false) Integer pageNum
            , @RequestParam(value = ParamConstant.PAGE_SIZE, required = false) Integer pageSize
            , HttpServletRequest request) throws Exception
    {
        return ReportGetter.getData(String.valueOf(reportId), getQueryParams(request), pageNum, pageSize);
    }

    @RequestMapping(value = "/meta/{reportId}")
    @ResponseBody
    public ReportDefineVO getReportMeta(@PathVariable(value = "reportId") Long reportId)
    {
        return ReportGetter.getMeta(String.valueOf(reportId));
    }
}
