package jfilter.controller;

import com.jfilter.filter.DynamicFilter;
import jfilter.support.filter.DemoIdFilter;
import jfilter.support.dto.*;
import jfilter.support.mock.MockUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/dynamic-filter/users")
@RestController
public class DynamicFilterController {

    /*
     * With using custom DemoIdFilter dynamic filter
     *
     * Get user by id
     * Link: http://localhost:8080/dynamic-filter/users/100?SOME_KEY=1
     *
     * Result:
     * {"firstName":"Jane","lastName":"Doe","dateCreated":"2019-08-02 09:28"}
     *
     * @param id number of mock user
     * @return serialized User object
     */
    @DynamicFilter(DemoIdFilter.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable(name = "id") Integer id) {
        return MockUtils.buildMockUser(id);
    }


}
