package jfilter.controller;

import com.jfilter.components.DynamicSessionFilter;
import com.jfilter.filter.DynamicFilter;
import com.jfilter.filter.FilterFields;
import jfilter.support.dto.User;
import jfilter.support.mock.MockUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;


@RequestMapping("/dynamic-session-filter/users")
@RestController
public class UserRestController {

    /**
     * With using session dynamic filter
     *
     * Get user by id
     * Link: http://localhost:8080/dynamic-session-filter/users/100
     *
     * Result:
     * {"firstName":"Jane","lastName":"Doe","dateCreated":"2019-08-02 09:36"}
     *
     * @param id number of mock user
     * @return serialized User object
     */
    @DynamicFilter(DynamicSessionFilter.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(HttpSession session, @PathVariable(name = "id") Integer id) {

        session.setAttribute(DynamicSessionFilter.ATTRIBUTE_FILTER_FIELDS,
                FilterFields.getFieldsBy(Arrays.asList("id", "password", "email", "address")));

        return MockUtils.buildMockUser(id);
    }


}
