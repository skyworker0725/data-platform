package tv.vego.dp.controller.system;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Condition;
import tv.vego.dp.model.SystemDict;
import tv.vego.dp.service.SystemDictService;

import java.util.List;

/**
 * 系统字典服务
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/3/3 12:30
 */
@Controller
@RequestMapping(value = "/sys/dict")
public class DictController
{
    @Autowired
    private SystemDictService systemDictService;

    @RequestMapping(value = "/get/{dictType}")
    @ResponseBody
    public List<SystemDict> getDictsByType(@PathVariable(value = "dictType") String dictType
            , @RequestParam(value = "dictName", required = false) String dictName)
    {
        Condition cond = new Condition(SystemDict.class);
        Condition.Criteria criteria = cond.createCriteria();
        criteria.andEqualTo("dictType", dictType);
        if (StringUtils.isNotEmpty(dictName))
        {
            criteria.andLike("dictName", "%" + dictName + "%");
        }

        cond.setOrderByClause("displayOrder, CONVERT(dictName USING gbk)");

        return systemDictService.selectByExample(cond);
    }
}
