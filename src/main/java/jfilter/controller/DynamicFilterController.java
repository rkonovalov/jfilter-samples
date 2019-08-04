package jfilter.controller;

import com.jfilter.filter.DynamicFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jfilter.support.dto.User;
import jfilter.support.filter.DemoIdFilter;
import jfilter.support.mock.MockUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"dynamic-filter"})
@RequestMapping("/dynamic-filter/users")
@RestController
public class DynamicFilterController {

    /**
     * Filtering with using custom DemoIdFilter dynamic filter
     * <p>
     * Get user by id
     * Link: http://localhost:8080/dynamic-filter/users/100?someKey=1
     * <p>
     * Result:
     * {"firstName":"Jane","lastName":"Doe","dateCreated":"2019-08-02 09:28"}
     * <p>
     * @param id number of mock user
     * @return serialized User object
     */
    @ApiOperation(value = "", notes = "Filtering with using custom DemoIdFilter dynamic filter")
    @DynamicFilter(DemoIdFilter.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@ApiParam(defaultValue = "1") @PathVariable(name = "id") Integer id,
                            @ApiParam @RequestParam(name = "someKey", required = false) String someKey) {
        return MockUtils.buildMockUser(id);
    }


}
