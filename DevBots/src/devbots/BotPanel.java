/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author raymond.nagel
 */
public class BotPanel extends VBox {
    
    private final Label lblBotName = new Label();
    private final ImageView imvBotIcon = new ImageView();
    
    private Bot bot = null;        
    
    public BotPanel(Bot bot)
    {
        this.bot = bot;
        
        // Name label
        lblBotName.setText(this.bot.getName());
        lblBotName.setStyle("-fx-font-weight: bold;");        
        
        // Icon
        imvBotIcon.setImage(this.bot.getImage());
        
        // Panel
        this.setAlignment(Pos.CENTER);        
        
        this.getChildren().addAll(lblBotName, imvBotIcon);
    }
}
