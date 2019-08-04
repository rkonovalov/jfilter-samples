package jfilter;


import com.jfilter.EnableJsonFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJsonFilter
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
