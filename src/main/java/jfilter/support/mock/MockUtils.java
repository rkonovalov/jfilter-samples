package jfilter.support.mock;


import java.time.LocalDateTime;
import jfilter.support.dto.*;

public class MockUtils {

    public static User buildMockUser(Integer id) {
        return new User().setId(id)
                .setFirstName("Jane")
                .setLastName("Doe")
                .setEmail("jane-doe@gmail.com")
                .setPassword("Encrypted password")
                .setDateCreated(LocalDateTime.now())
                .setAddress(buildMockAddress(4));
    }

    public static Street buildMockStreet(Integer id) {
        return new Street().setId(id)
                .setStreetNumber(15)
                .setStreetName("Bourbon Street");
    }

    public static City buildMockCity(Integer id) {
        return new City().setId(id)
                .setName("London");
    }

    public static Country buildMockCountry(Integer id) {
        return new Country().setId(id)
                .setCode("GBR")
                .setName("United Kingdom");
    }

    public static Address buildMockAddress(Integer id) {
        return new Address().setId(id)
                .setCountry(buildMockCountry(1))
                .setCity(buildMockCity(2))
                .setStreet(buildMockStreet(3));
    }

}
