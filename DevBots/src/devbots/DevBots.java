/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Raymond
 */
public class DevBots extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DevBots.class.getResource("ui/Arena.fxml"));
        BorderPane root = loader.<BorderPane>load();

        Scene scene = new Scene(root);

        Pane pnArenaPane = (Pane) root.lookup("#pnArenaPane");

        primaryStage.setTitle("DevBots");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
