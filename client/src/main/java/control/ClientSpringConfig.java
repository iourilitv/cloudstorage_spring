package control;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import utils.FileManager;
import utils.FileUtils;
import utils.HashUtils;
import utils.ItemUtils;

@Configuration
@ComponentScan
public class ClientSpringConfig {

    @Bean
    public CloudStorageClient cloudStorageClient() {
        return new CloudStorageClient(fileUtils(), itemUtils(), propertiesHandler());
    }

    @Bean
    public FileUtils fileUtils() {
        return new FileUtils(hashUtils());
    }

    @Bean
    public ItemUtils itemUtils() {
        return new ItemUtils();
    }

    @Bean
    public PropertiesHandler propertiesHandler() {
        return new PropertiesHandler(fileManager());
    }

    @Bean
    public HashUtils hashUtils() {
        return new HashUtils();
    }

    @Bean
    public FileManager fileManager() {
        return new FileManager();
    }

}
