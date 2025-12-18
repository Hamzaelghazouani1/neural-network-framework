package ma.enset.glsid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "ma.enset.glsid")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
