package gp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/materialImg/**")
                .addResourceLocations("file:///"+System.getProperty("user.dir") + "/src/main/resources/static/materialImg/");

        registry.addResourceHandler("/materialDeImg/**")
                .addResourceLocations("file:///"+System.getProperty("user.dir") + "/src/main/resources/static/materialDeImg/");


    }
}
