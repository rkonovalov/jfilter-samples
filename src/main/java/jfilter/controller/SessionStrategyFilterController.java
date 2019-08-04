package jfilter.controller;

import com.jfilter.filter.FieldFilterSetting;
import com.jfilter.filter.FilterBehaviour;
import com.jfilter.filter.SessionStrategy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jfilter.support.dto.User;
import jfilter.support.mock.MockUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

@Api(tags = {"session-controller-filter"})
@RequestMapping("/session-controller-filter/users")
@RestController
public class SessionStrategyFilterController {
    private static final String SESSION_ATTRIBUTE = "ROLE";
    private static final String SESSION_ROLE_ADMIN = "admin";
    private static final String SESSION_ROLE_CUSTOMER = "customer";


    /**
     * Get user details by using multiple SessionStrategy filters
     * <p>
     * Get user password information by id
     * And filter user object fields by role
     * <p>
     * Link: http://localhost:8080/session-controller-filter/users/?role=admin
     * Result:
     * {"id":1,"password":"Encrypted password","address":{"id":4}}
     * <p>
     * Link: http://localhost:8080/session-controller-filter/users/?role=customer
     * Result:
     * {"id":1,"email":"jane-doe@gmail.com"}
     *
     * @param id      number of mock user
     * @param role    string value requester role
     * @param session {@link HttpSession}
     * @return serialized User object
     */
    @ApiOperation(value = "", notes = "Get user details by using multiple SessionStrategy filters")
    @SessionStrategy(attributeName = SESSION_ATTRIBUTE, attributeValue = SESSION_ROLE_ADMIN, ignoreFields = {
            @FieldFilterSetting(fields = {"id", "password", "address"}, behaviour = FilterBehaviour.KEEP_FIELDS)
    })

    @SessionStrategy(attributeName = SESSION_ATTRIBUTE, attributeValue = SESSION_ROLE_CUSTOMER, ignoreFields = {
            @FieldFilterSetting(fields = {"id", "email"}, behaviour = FilterBehaviour.KEEP_FIELDS)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserDetailsByRole(@ApiIgnore HttpSession session, @ApiParam(defaultValue = "1") @PathVariable(name = "id") Integer id,
                                     @ApiParam(allowableValues = "admin, customer") @RequestParam(name = "role") String role) {

        //Set role from request in session attribute
        //And session filter will select necessary SessionStrategy by depending SESSION_ATTRIBUTE
        session.setAttribute(SESSION_ATTRIBUTE, role);
        return MockUtils.buildMockUser(id);
    }

}
