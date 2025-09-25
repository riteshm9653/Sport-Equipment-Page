package com.fsd1.group_project.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
    // Handle Angular trail application
    registry.addResourceHandler("/trail/**")
        .addResourceLocations("classpath:/static/")
        .resourceChain(true)
        .addResolver(new PathResourceResolver() {
          @Override
          protected Resource getResource(@NonNull String resourcePath, @NonNull Resource location) throws IOException {
            Resource requestedResource = location.createRelative(resourcePath);

            // If the resource exists, return it
            if (requestedResource.exists() && requestedResource.isReadable()) {
              return requestedResource;
            }

            // For Angular SPA routing, return index.html for non-existent resources
            return new ClassPathResource("/static/index.html");
          }
        });

    // Handle equipment path - also serve Angular app (since it's built with /trail/
    // base href)
    registry.addResourceHandler("/equipment/**")
        .addResourceLocations("classpath:/static/")
        .resourceChain(true)
        .addResolver(new PathResourceResolver() {
          @Override
          protected Resource getResource(@NonNull String resourcePath, @NonNull Resource location) throws IOException {
            Resource requestedResource = location.createRelative(resourcePath);

            // If the resource exists, return it
            if (requestedResource.exists() && requestedResource.isReadable()) {
              return requestedResource;
            }

            // For Angular SPA routing, return index.html for non-existent resources
            return new ClassPathResource("/static/index.html");
          }
        });

    // Handle other static resources
    registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/static/", "classpath:/public/")
        .resourceChain(true)
        .addResolver(new PathResourceResolver() {
          @Override
          protected Resource getResource(@NonNull String resourcePath, @NonNull Resource location) throws IOException {
            Resource requestedResource = location.createRelative(resourcePath);

            // If the resource exists, return it
            if (requestedResource.exists() && requestedResource.isReadable()) {
              return requestedResource;
            }

            // If it's an API request, don't redirect
            if (resourcePath.startsWith("api/")) {
              return null;
            }

            // If it's a trail request, don't interfere
            if (resourcePath.startsWith("trail/")) {
              return null;
            }

            return null;
          }
        });
  }

  @Override
  public void addViewControllers(@NonNull ViewControllerRegistry registry) {
    // Handle trail routing - serve index.html for all trail routes
    registry.addViewController("/trail/**").setViewName("forward:/static/index.html");
    // Also handle the direct trail route
    registry.addViewController("/trail/").setViewName("forward:/static/index.html");

    // Handle equipment routing - serve Angular app directly since it will be built
    // with /equipment/ base href
    registry.addViewController("/equipment/").setViewName("forward:/static/index.html");
    registry.addViewController("/equipment/**").setViewName("forward:/static/index.html");
  }
}
