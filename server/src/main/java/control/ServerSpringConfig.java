package control;

import jdbc.UsersAuthController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import security.SecureHasher;
import utils.FileManager;
import utils.FileUtils;
import utils.HashUtils;
import utils.ItemUtils;

@Configuration
@ComponentScan //по умолчанию директория корневая, но можно указать, например @ComponentScan("ru.folder")
public class ServerSpringConfig {

    @Bean
    public SecureHasher secureHasher() {
        return new SecureHasher();
    }

    @Bean
    public HashUtils hashUtils() {
        return new HashUtils();
    }

    @Bean
    public FileManager fileManager() {
        return new FileManager();
    }

    @Bean
    public UsersAuthController usersAuthController() {
        return new UsersAuthController(secureHasher());
    }

    @Bean
    public PropertiesHandler propertiesHandler() {
        return new PropertiesHandler(fileManager());
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
    public CloudStorageServer cloudStorageServer() {
        return new CloudStorageServer(
                usersAuthController(), fileUtils(), itemUtils(), propertiesHandler());
    }

}
