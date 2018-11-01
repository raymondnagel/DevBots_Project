/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.ui;

import devbots.sprites.Bot;
import static devbots.Global.MAX_FUEL;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
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
    private final HBox hboxScore = new HBox();
    private final ProgressBar pgbFuel = new ProgressBar(1.0);
    private final IconMeter icmRockets;
    private final IconMeter icmBombs;
    private final Label lblScore = new Label("0");
  
    
    private Bot bot = null;       
    
    public static BotPanel createForBot(Bot bot)
    {
        BotPanel panel = new BotPanel(bot);
        bot.setPanel(panel);
        return panel;
    }
    
    private BotPanel(Bot bot)
    {
        this.bot = bot;
        this.icmRockets = new IconMeter(new Image("devbots/resources/rocket.png"));
        this.icmBombs = new IconMeter(new Image("devbots/resources/bomb.png"));
        
        // Name label
        lblBotName.setText(this.bot.getName());
        lblBotName.setGraphic(new ImageView(bot.getImage()));
        lblBotName.setStyle("-fx-font-weight: bold;");   
        lblBotName.setPadding(new Insets(0, 0, 6, 0));
        
        // Fuel
        Label fuelLabel = new Label("Fuel:");
        fuelLabel.setPrefWidth(60.0);        
        hboxFuel.getChildren().addAll(fuelLabel, pgbFuel);
        
        // Rockets
        Label rocketsLabel = new Label("Rockets:");
        rocketsLabel.setPrefWidth(60.0);
        hboxRockets.getChildren().addAll(rocketsLabel, icmRockets);
        
        // Bombs
        Label bombsLabel = new Label("Bombs:");
        bombsLabel.setPrefWidth(60.0);                       
        hboxBombs.getChildren().addAll(bombsLabel, icmBombs);
        
        // Score
        Label scoreLabel = new Label("Score:");
        scoreLabel.setPrefWidth(60.0);
        scoreLabel.setStyle("-fx-font-weight: bold;");
        lblScore.setStyle("-fx-font-weight: bold;");
        hboxScore.getChildren().addAll(scoreLabel, lblScore);
        
        // Panel
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setAlignment(Pos.CENTER);  
        this.setPadding(new Insets(10.0));        
        this.getChildren().addAll(lblBotName, hboxFuel, hboxRockets, hboxBombs, hboxScore);
    }
    
    public void updateInfo()
    {
        icmRockets.updateCount(bot.getRockets());
        icmBombs.updateCount(bot.getBombs());
        lblScore.setText(bot.getScore()+"");
        
        double pct = ((double)bot.getFuel()) / ((double)MAX_FUEL);
        pgbFuel.setProgress(pct);
    }
}
