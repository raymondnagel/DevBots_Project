/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author raymond.nagel
 */
public final class BotPanel extends VBox {
    
    private final Label lblBotName = new Label();    
    
    private final HBox hboxFuel = new HBox();
    private final HBox hboxRockets = new HBox();
    private final HBox hboxBombs = new HBox();
    private final HBox hboxRocketCount = new HBox();
    private final HBox hboxBombCount = new HBox();
    
    private Bot bot = null;       
    
    static
    {
        // TODO: Init static icons for rockets/bombs
    }
    
    public BotPanel(Bot bot)
    {
        this.bot = bot;
        
        // Name label
        lblBotName.setText(this.bot.getName());
        lblBotName.setGraphic(new ImageView(bot.getImage()));
        lblBotName.setStyle("-fx-font-weight: bold;");   
        lblBotName.setPadding(new Insets(0, 0, 10, 0));
        
        // Fuel
        Label fuelLabel = new Label("Fuel:");
        fuelLabel.setPrefWidth(60.0);
        ProgressBar fuelBar = new ProgressBar(1.0);
        hboxFuel.getChildren().addAll(fuelLabel, fuelBar);
        
        // Rockets
        Label rocketsLabel = new Label("Rockets:");
        rocketsLabel.setPrefWidth(60.0);
        hboxRockets.getChildren().addAll(rocketsLabel, hboxRocketCount);
        
        // Bombs
        Label bombsLabel = new Label("Bombs:");
        bombsLabel.setPrefWidth(60.0);                       
        hboxBombs.getChildren().addAll(bombsLabel, hboxBombCount);
        
        // Panel
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setAlignment(Pos.CENTER);  
        this.setPadding(new Insets(20.0));        
        this.getChildren().addAll(lblBotName, hboxFuel, hboxRockets, hboxBombs);
    }
}
