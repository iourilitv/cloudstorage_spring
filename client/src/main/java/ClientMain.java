import control.SpringFXMLLoader;
import javafx.GUIController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class of client cloudStorage applet.
 */
public class ClientMain extends Application {
//    Источники способа подключения JavaFX и Spring:
//    1. https://habr.com/ru/post/203960/ Не работает.
//    2. https://habr.com/ru/post/348850/ Попробовать

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        GUIController controller = (GUIController) SpringFXMLLoader.load("/MainClient.fxml");

        primaryStage.setOnCloseRequest(event -> {
            controller.dispose();//dispose - располагать, размещать
            //сворачиваем окно
            Platform.exit();
            //указываем системе, что выход без ошибки
            System.exit(0);
        });

        primaryStage.setTitle("The Cloud Storage by LYS");
        Scene scene = new Scene((Parent) controller.getView(), 1024, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}