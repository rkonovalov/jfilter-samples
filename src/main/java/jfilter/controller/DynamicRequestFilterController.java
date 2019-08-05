package jfilter.controller;

import com.jfilter.components.DynamicSessionFilter;
import com.jfilter.filter.DynamicFilter;
import com.jfilter.filter.FilterFields;
import com.jfilter.util.FilterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jfilter.support.dto.User;
import jfilter.support.mock.MockUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Api(tags = {"dynamic-request-filter"})
@RequestMapping("/dynamic-request-filter/users")
@RestController
public class DynamicRequestFilterController {

    /**
     * Filtering using DynamicSessionFilter
     * <p>
     * Passing filter properties through request attributes
     *
     * <p>
     * Get user by id
     * Link: http://localhost:8080/dynamic-request-filter/users/100
     * <p>
     * Result:
     * {"firstName":"Jane","lastName":"Doe","dateCreated":"2019-08-02 09:36"}
     *
     * @param id number of mock user
     * @return serialized User object
     */
    @ApiOperation(value = "", notes = "Filtering using DynamicSessionFilter and request")
    @DynamicFilter(DynamicSessionFilter.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getDynamicRequestFilteredUserById(@ApiIgnore HttpServletRequest request,
                                                  @ApiParam(defaultValue = "1", required = true) @PathVariable(name = "id") Integer id) {
        FilterUtil.useFilter(request, FilterFields.getFieldsBy(Arrays.asList("id", "password", "email", "address")));
        return MockUtils.buildMockUser(id);
    }


}
