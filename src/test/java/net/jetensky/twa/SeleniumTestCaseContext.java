package net.jetensky.twa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SeleniumTestCaseContext.class)
public class SeleniumTestCaseContext {

    @Bean
    public WebDriverWrapper webDriverWrapper() {
        return new WebDriverWrapper();
    }
}