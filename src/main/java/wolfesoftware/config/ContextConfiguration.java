package wolfesoftware.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "wolfesoftware")
@EnableAspectJAutoProxy
public class ContextConfiguration {

}
