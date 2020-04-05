package control;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerSpringConfig {

    @Bean
    public CloudStorageServer cloudStorageServer() {
        return new CloudStorageServer();
    }


}
