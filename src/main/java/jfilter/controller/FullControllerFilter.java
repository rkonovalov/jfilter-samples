package jfilter.controller;

import com.jfilter.filter.FileFilterSetting;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jfilter.support.dto.Address;
import jfilter.support.dto.User;
import jfilter.support.mock.MockUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

//Define filtering to all methods in controller
@FileFilterSetting(fileName = "src/main/resources/full-controller-config.xml")
@Api(tags = {"full-controller-filter"})
@RequestMapping("/full-controller-filter/users")
@RestController
public class FullControllerFilter {
    private static final String SESSION_ATTRIBUTE = "role";

    /**
     * Get user details by role using full-controller-config.xml config
     * <p>
     * Link: http://localhost:8080/full-controller-filter/users/1?role=admin
     * Result:
     * {"id":1,"password":"Encrypted password",
     * "address":{"id":4,"country":{"id":1,"code":"GBR","name":"United Kingdom"},"city":{"id":2,"name":"London"},
     * "street":{"id":3,"streetName":"Bourbon Street","streetNumber":15}}}
     * <p>
     * Link: http://localhost:8080/full-controller-filter/users/1?role=customer
     * Result:
     * {"id":1,"email":"jane-doe@gmail.com"}
     *
     * @param id      number of mock user
     * @param role    string value requester role
     * @param session {@link HttpSession}
     * @return serialized User object
     */
    @ApiOperation(value = "", notes = "Get user details by role using full-controller-config.xml config")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserDetailsByRole(@ApiIgnore HttpSession session, @ApiParam(defaultValue = "1") @PathVariable(name = "id") Integer id,
                                     @ApiParam(allowableValues = "admin, customer") @RequestParam(name = "role") String role) {

        //Set role from request in session attribute
        //And session filter will select necessary SessionStrategy by depending SESSION_ATTRIBUTE
        session.setAttribute(SESSION_ATTRIBUTE, role);
        return MockUtils.buildMockUser(id);
    }

    /**
     * Get user address details by role using full-controller-config.xml config
     * <p>
     * Link: http://localhost:8080/full-controller-filter/users/1/address?role=admin
     * Result:
     * {"id":4,"country":{"id":1,"code":"GBR","name":"United Kingdom"},"city":{"id":2,"name":"London"},
     * "street":{"id":3,"streetName":"Bourbon Street","streetNumber":15}}
     * <p>
     * Link: http://localhost:8080/full-controller-filter/users/1/address?role=customer
     * Result:
     * {"id":4}
     * <p>
     * @param id      number of mock user
     * @param role    string value requester role
     * @param session {@link HttpSession}
     * @return serialized Address object
     */
    @ApiOperation(value = "", notes = "Get user address details by role using full-controller-config.xml config")
    @RequestMapping(value = "/{id}/address", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Address getUserAddressByRole(@ApiIgnore HttpSession session, @ApiParam(defaultValue = "1") @PathVariable(name = "id") Integer id,
                                        @ApiParam(allowableValues = "admin, customer") @RequestParam(name = "role") String role) {

        //Set role from request in session attribute
        //And session filter will select necessary SessionStrategy by depending SESSION_ATTRIBUTE
        session.setAttribute(SESSION_ATTRIBUTE, role);
        return MockUtils.buildMockUser(id).getAddress();
    }

}
