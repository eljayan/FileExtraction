/**
 * Created by R00715649 on 01-Nov-16.
 *
 * Las aplicaciones con interfaz grafica son diferentes a las de
 * linea de comandos. En una aplicacion de cmd basta con crear un
 * main class y un seudocodigo para ir creando y ejecutando otras
 * classes segun sea necesario.
 *
 * Con una interfaz grafica, la clase principal contiene el diseño
 * solamente, se necesita otra classe llamada por lo general Controlador
 * quien es la unica que accede y modifica los elementos de la GUI. Y
 * una parte logica que es la que hace todo el trabajo de la aplicacion.
 *
 * Muy interesante una vez que queda entendido el funcionamiento general.
 *
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load((getClass().getResource("main.fxml")));

        Scene scene = new Scene(root);

        primaryStage.setTitle("PST File Extraction Utility");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }



}
