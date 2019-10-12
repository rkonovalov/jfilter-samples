package jfilter.controller;

import com.jfilter.filter.FieldFilterSetting;
import io.swagger.annotations.ApiParam;
import jfilter.support.dto.User;
import jfilter.support.mock.MockUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/optional/users")
@RestController
public class OptionalController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @FieldFilterSetting(fields = {"id"})
    public Optional<User> getOptionalUserById(@ApiParam(defaultValue = "1", required = true) @PathVariable(name = "id") Integer id) {
        return Optional.ofNullable((MockUtils.buildMockUser(id)));
    }
}
