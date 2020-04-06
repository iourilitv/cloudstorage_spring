package control;

import jdbc.UsersAuthController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import utils.FileUtils;
import utils.ItemUtils;

@Configuration
@ComponentScan //по умолчанию директория корневая, но можно указать, например @ComponentScan("ru.folder")
public class ServerSpringConfig {

    @Bean
    public UsersAuthController usersAuthController() {
        return new UsersAuthController();
    }

    @Bean
    public PropertiesHandler propertiesHandler() {
        return new PropertiesHandler();
    }

    @Bean
    public FileUtils fileUtils() {
        return new FileUtils();
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
