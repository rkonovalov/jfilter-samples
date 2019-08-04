package jfilter.controller;

import com.jfilter.filter.FieldFilterSetting;
import com.jfilter.filter.FilterBehaviour;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jfilter.support.dto.*;
import jfilter.support.mock.MockUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"simple-filter"})
@RequestMapping("/simple-filter/users")
@RestController
public class SimpleFilterController {

    /**
     * Get full user details without any filtration
     * <p>
     * Get user by id
     * Link: http://localhost:8080/simple-filter/users/100
     * <p>
     * Result:
     * {"id":100,"firstName":"Jane","lastName":"Doe","email":"jane-doe@gmail.com","password":"Encrypted password",
     * "address":{"id":4,"country":{"id":1,"code":"GBR","name":"United Kingdom"},"city":{"id":2,"name":"London"},
     * "street":{"id":3,"streetName":"Bourbon Street","streetNumber":15}},"dateCreated":"2019-08-01 17:31"}
     *
     * @param id number of mock user
     * @return serialized User object
     */
    @ApiOperation(value = "", notes = "Get full user details without any filtration")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@ApiParam(defaultValue = "1") @PathVariable(name = "id") Integer id) {
        return MockUtils.buildMockUser(id);
    }

    /**
     * Get user details except "id", "password" and "address" fields
     * <p>
     * Get user contact information by id
     * Link: http://localhost:8080/simple-filter/users/100/contact
     * <p>
     * Result:
     *{"firstName":"Jane","lastName":"Doe","email":"jane-doe@gmail.com","dateCreated":"2019-08-01 17:30"}
     *
     * @param id number of mock user
     * @return serialized User object
     */
    @ApiOperation(value = "", notes = "Get user details except \"id\", \"password\" and \"address\" fields")
    @FieldFilterSetting(fields = {"id", "password", "address"})
    @RequestMapping(value = "/{id}/contact", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserContactById(@ApiParam(defaultValue = "1") @PathVariable(name = "id") Integer id) {
        return MockUtils.buildMockUser(id);
    }


    /**
     * Keep "id" and "password" fields from filtration
     * <p>
     * Get user password information by id
     * Link: http://localhost:8080/simple-filter/users/100/password
     * <p>
     * Result:
     * {"id":100,"password":"Encrypted password"}
     *
     * @param id number of mock user
     * @return serialized User object
     */
    @ApiOperation(value = "", notes = "Keep \"id\" and \"password\" fields from filtration")
    @FieldFilterSetting(fields = {"id", "password"}, behaviour = FilterBehaviour.KEEP_FIELDS)
    @RequestMapping(value = "/{id}/password", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserPasswordById(@ApiParam(defaultValue = "1") @PathVariable(name = "id") Integer id) {
        return MockUtils.buildMockUser(id);
    }

    /**
     * Keep "id" fields of nested classes
     * <p>
     * Get user password information by id
     * Link: http://localhost:8080/simple-filter/users/100/ids
     * <p>
     * Result:
     * {"id":100,"address":{"id":4,"country":{"id":1},"city":{"id":2},"street":{"id":3}}}
     *
     * @param id number of mock user
     * @return serialized User object
     */
    @ApiOperation(value = "", notes = "Keep \"id\" fields of nested classes")
    @FieldFilterSetting(className = User.class, fields = {"id", "address"}, behaviour = FilterBehaviour.KEEP_FIELDS)
    @FieldFilterSetting(className = Address.class, fields = {"id", "country", "city", "street"}, behaviour = FilterBehaviour.KEEP_FIELDS)
    @FieldFilterSetting(className = Country.class, fields = {"id"}, behaviour = FilterBehaviour.KEEP_FIELDS)
    @FieldFilterSetting(className = City.class, fields = {"id"}, behaviour = FilterBehaviour.KEEP_FIELDS)
    @FieldFilterSetting(className = Street.class, fields = {"id"}, behaviour = FilterBehaviour.KEEP_FIELDS)
    @RequestMapping(value = "/{id}/ids", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserIdsById(@ApiParam(defaultValue = "1") @PathVariable(name = "id") Integer id) {
        return MockUtils.buildMockUser(id);
    }
}
