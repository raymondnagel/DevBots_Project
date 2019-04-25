/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.sprites;

import javafx.scene.image.Image;

/**
 *
 * @author Raymond
 */
public class Wall extends SyncSprite {

    private static final Image DEFAULT_IMAGE;
    static
    {
        DEFAULT_IMAGE = new Image("devbots/resources/wall.png");
    }
    
    public Wall()
    {
        this.setImage(DEFAULT_IMAGE);
    }    

    @Override
    public char getVisionCode() {
        return 'W';
    }
    
}
