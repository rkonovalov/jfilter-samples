package jfilter.controller;

import com.jfilter.filter.FileFilterSetting;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jfilter.support.dto.User;
import jfilter.support.mock.MockUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

@Api(tags = {"xml-configured-filter"})
@RequestMapping("/xml-configured-filter/users")
@RestController
public class XmlConfiguredFilterController {
    private static final String SESSION_ATTRIBUTE = "role";

    /**
     * Get user details by role using xml-config.xml config
     * <p>
     * Link: http://localhost:8080/xml-configured-filter/users/1?role=admin
     * Result:
     * {"id":1,"password":"Encrypted password",
     * "address":{"id":4,"country":{"id":1,"code":"GBR","name":"United Kingdom"},"city":{"id":2,"name":"London"},
     * "street":{"id":3,"streetName":"Bourbon Street","streetNumber":15}}}
     * <p>
     * Link: http://localhost:8080/xml-configured-filter/users/1?role=customer
     * Result:
     * {"id":1,"email":"jane-doe@gmail.com"}
     *
     * @param id      number of mock user
     * @param role    string value requester role
     * @param session {@link HttpSession}
     * @return serialized User object
     */
    @ApiOperation(value = "", notes = "Get user details by role using xml-config.xml config")
    @FileFilterSetting(fileName = "src/main/resources/xml-config.xml")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserDetailsByRole(@ApiIgnore HttpSession session, @ApiParam(defaultValue = "1", required = true)
    @PathVariable(name = "id") Integer id, @ApiParam(allowableValues = "admin, customer") @RequestParam(name = "role") String role) {

        //Set role from request in session attribute
        //And session filter will select necessary SessionStrategy by depending SESSION_ATTRIBUTE
        session.setAttribute(SESSION_ATTRIBUTE, role);
        return MockUtils.buildMockUser(id);
    }

}
