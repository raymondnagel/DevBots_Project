/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author Raymond
 */
public class IconMeter extends HBox {
    
    private Image icon = null;

    public IconMeter(Image icon) {
        this.icon = icon;
    }
    
    public void updateCount(int count)
    {
        while (count < getChildren().size())
        {
            getChildren().remove(getChildren().size()-1);
        }
        while (count > getChildren().size())
        {
            ImageView iv = new ImageView(icon);
            iv.setFitWidth(16.0);
            iv.setFitHeight(16.0);
            getChildren().add(iv);
        }
    }
}
