package control;

import io.netty.channel.ChannelHandlerContext;
import jdbc.MySQLConnect;
import jdbc.UsersAuthController;
import netty.NettyServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import security.SecureHasher;
import utils.FileManager;
import utils.FileUtils;
import utils.HashUtils;
import utils.ItemUtils;

import java.sql.Connection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan //по умолчанию директория корневая, но можно указать, например @ComponentScan("ru.folder")
public class ServerSpringConfig {

    //    @Bean
//    public CloudStorageServer cloudStorageServer() {
//        return new CloudStorageServer(
//                usersAuthController(), fileUtils(), itemUtils(),
//                propertiesHandler());
//    }
//    @Bean //TODO разобраться с передачей объекта CloudStorageServer в объект NettyServer.
//    public CloudStorageServer cloudStorageServer() {
//        return new CloudStorageServer(
//                usersAuthController(), fileUtils(), itemUtils(),
//                propertiesHandler(), nettyServer());
//    }
    @Bean //TODO разобраться с передачей объекта CloudStorageServer в объект NettyServer.
    public CloudStorageServer cloudStorageServer(
            UsersAuthController usersAuthController, FileUtils fileUtils,
            ItemUtils itemUtils, PropertiesHandler propertiesHandler, NettyServer nettyServer) {
        return new CloudStorageServer(
                usersAuthController, fileUtils, itemUtils,
                propertiesHandler, nettyServer);
    }

//    @Bean
//    public UsersAuthController usersAuthController() {
//        return new UsersAuthController(secureHasher(), authorizedUsers(), connection());
//    }
    @Bean
    public UsersAuthController usersAuthController(SecureHasher secureHasher,
           Map<String, ChannelHandlerContext> authorizedUsers, Connection connection) {
        return new UsersAuthController(secureHasher, authorizedUsers, connection);
    }

//    @Bean
//    public FileUtils fileUtils() {
//        return new FileUtils(hashUtils());
//    }
    @Bean
    public FileUtils fileUtils(HashUtils hashUtils) {
        return new FileUtils(hashUtils);
    }

    @Bean
    public ItemUtils itemUtils() {
        return new ItemUtils();
    }

    @Bean
    public PropertiesHandler propertiesHandler(FileManager fileManager) {
        return new PropertiesHandler(fileManager);
    }

    @Bean //TODO разобраться с передачей объекта CloudStorageServer в объект NettyServer.
    public NettyServer nettyServer() {
        return new NettyServer();
    }
//    public NettyServer nettyServer(CloudStorageServer storageServer) {
//        return new NettyServer(storageServer);
//    }

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
    public Connection connection() {
        return new MySQLConnect().connect();
    }

}
