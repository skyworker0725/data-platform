package tv.vego.dp.service;

import com.github.pagehelper.PageInfo;
import tv.vego.dp.model.Country;
import tv.vego.dp.model.CountryQueryModel;

import java.util.List;

/**
 * @since 2015-09-19 17:17
 */
public interface CountryService extends IService<Country> {

    /**
     * 根据条件分页查询
     *
     * @param country
     * @param page
     * @param rows
     * @return
     */
    List<Country> selectByCountry(Country country, int page, int rows);

    /**
     * 分页查询,分页插件4.0.3版本特性演示
     *
     * @param queryModel
     * @return
     */
    PageInfo<Country> selectByCountryQueryModel(CountryQueryModel queryModel);

}
