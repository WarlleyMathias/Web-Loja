package com.weboloja.webloja.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
@Configuration
public class MvcConfig implements WebMvcConfigurer {
 
 
    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        exposeDirectory("user-photos", registry);
    }
     
    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
         
        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
         
        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/"+ uploadPath + "/");
    }
}