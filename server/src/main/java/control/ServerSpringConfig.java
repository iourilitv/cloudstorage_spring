package control;

import io.netty.channel.ChannelHandlerContext;
import jdbc.UsersAuthController;
import netty.NettyServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import security.SecureHasher;
import utils.FileManager;
import utils.FileUtils;
import utils.HashUtils;
import utils.ItemUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@ComponentScan //по умолчанию директория корневая, но можно указать, например @ComponentScan("ru.folder")
public class ServerSpringConfig {

    @Bean
    public CloudStorageServer cloudStorageServer() throws SQLException {
        return new CloudStorageServer(
                usersAuthController(), fileUtils(), itemUtils(),
                propertiesHandler(), nettyServer());
    }

    @Bean
    public UsersAuthController usersAuthController() throws SQLException {
        return new UsersAuthController(secureHasher(), authorizedUsers(), dataSource());
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
    public NettyServer nettyServer() {
        return new NettyServer();
    }

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
    public Map<String, ChannelHandlerContext> authorizedUsers() {
        return Collections.synchronizedMap(new HashMap<>());
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(jdbcUrl());
        ds.setUsername("root");
        ds.setPassword("mysql!1qwertY");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        Properties properties = ds.getConnectionProperties();
        if(properties == null) {
            properties = new Properties();
            properties.setProperty("MaxPooledStatements", "250");
        }
        return ds;
    }

    @Bean
    public String jdbcUrl() {
        return "jdbc:mysql://localhost:3306/cloudstoragedb?serverTimezone=Europe/Moscow";
    }
}
