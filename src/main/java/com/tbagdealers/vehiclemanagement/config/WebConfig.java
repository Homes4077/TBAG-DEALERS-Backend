package com.tbagdealers.vehiclemanagement.config; // Corrected package to match your file path structure

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Marks this class as a source of Spring bean definitions
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Maps URL requests starting with "/uploads/**" to the file system path "./uploads/"
        // For example, if your app is at https://your-app.up.railway.app,
        // an image saved at /app/uploads/my_image.jpg will be accessible via
        // https://your-app.up.railway.app/uploads/my_image.jpg
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:./uploads/"); // IMPORTANT: Use "file:./" for relative path
    }
}
