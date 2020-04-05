import control.CloudStorageServer;
import control.ServerSpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * The main class of server cloudstorage applet.
 */
public class ServerMain {

//    public static void main(String[] args) throws Exception {
//        CloudStorageServer css = new CloudStorageServer();
//        css.initConfiguration();
//        css.run();
//    }
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ServerSpringConfig.class);

        CloudStorageServer css = context.getBean("cloudStorageServer",
                CloudStorageServer.class);
        css.initConfiguration();
        css.run();
    }

}