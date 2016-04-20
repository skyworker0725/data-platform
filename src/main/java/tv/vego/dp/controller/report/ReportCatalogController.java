package tv.vego.dp.controller.report;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tv.vego.dp.model.CombReportDefine;
import tv.vego.dp.model.ReportCatalog;
import tv.vego.dp.service.CombReportDefineService;
import tv.vego.dp.service.ReportCatalogService;
import tv.vego.dp.util.ParamConstant;
import tv.vego.dp.vo.SystemMenuVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 报表目录
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/1/26 15:12
 */
@Controller
@RequestMapping(value = "/mng/catalog")
public class ReportCatalogController
{
    @Autowired
    private ReportCatalogService reportCatalogService;

    @Autowired
    private CombReportDefineService combReportDefineService;

    @RequestMapping(value = "/tree/get/{parentCatalogId}")
    @ResponseBody
    public List<ReportCatalog> getReportCatalogTree(@PathVariable(value = "parentCatalogId") long parentCatalogId)
    {
        return reportCatalogService.getReportCatalogByParentId(parentCatalogId);
    }

    @RequestMapping(value = "/comb/get/{catalogId}")
    @ResponseBody
    public List<CombReportDefine> getCatalogCombReport(@PathVariable(value = "catalogId") long catalogId)
    {
        return combReportDefineService.getCatalogCombReportByCatalogId(catalogId);
    }

    @RequestMapping(value = "/get/all")
    @ResponseBody
    public List<SystemMenuVO> getCatalogCombReportTree()
    {
        return getMenuInfo(0);
    }

    private List<SystemMenuVO> getMenuInfo(long catalogId)
    {
        List<SystemMenuVO> menuVOs = new ArrayList<>();

        //获取目录信息
        List<ReportCatalog> reportCatalogs = reportCatalogService.getReportCatalogByParentId(catalogId);
        if (CollectionUtils.isNotEmpty(reportCatalogs))
        {
            for (ReportCatalog reportCatalog : reportCatalogs)
            {
                SystemMenuVO systemMenuVO = new SystemMenuVO();

                systemMenuVO.setId("catalog_" + reportCatalog.getCatalogId());
                systemMenuVO.setText(reportCatalog.getCatalogName());
                systemMenuVO.setIsLeaf(ParamConstant.SYSTEM_MENU_NOT_LEAF);

                List<SystemMenuVO> childrenMenus = getMenuInfo(reportCatalog.getCatalogId());
                if (CollectionUtils.isNotEmpty(childrenMenus))
                {
                    systemMenuVO.setChildren(childrenMenus);
                }

                menuVOs.add(systemMenuVO);
            }
        }

        //获取报表信息
        List<CombReportDefine> combReportDefines = combReportDefineService.getCatalogCombReportByCatalogId(catalogId);
        if (CollectionUtils.isNotEmpty(combReportDefines))
        {
            for (CombReportDefine combReportDefine : combReportDefines)
            {
                SystemMenuVO systemMenuVO = new SystemMenuVO();

                systemMenuVO.setId("combReport_" + combReportDefine.getCombId());
                systemMenuVO.setText(combReportDefine.getCombName());
                systemMenuVO.setUrl("/views/show.html?combId=" + combReportDefine.getCombId());
                systemMenuVO.setIsLeaf(ParamConstant.SYSTEM_MENU_IS_LEAF);

                menuVOs.add(systemMenuVO);
            }
        }

        return menuVOs;
    }
}
