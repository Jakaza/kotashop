package za.ac.tut.kotashop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
@Configuration
public class WebMvcLocation implements WebMvcConfigurer {

    private final Path uploadsLocation;

    public WebMvcLocation(StorageProperty storageProperty) {
        this.uploadsLocation = Path.of(storageProperty.getUploadsLocation());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadsLocationAbsolutePath = this.uploadsLocation.toFile().getAbsolutePath();
        System.out.println(uploadsLocationAbsolutePath);
        registry.addResourceHandler("/uploads/products/**").addResourceLocations("file:/" + uploadsLocationAbsolutePath + "/");
    }
}
