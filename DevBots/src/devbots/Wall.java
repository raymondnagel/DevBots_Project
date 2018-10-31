/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots;

import static devbots.Global.BLOCK_SZ;
import javafx.scene.image.Image;

/**
 *
 * @author Raymond
 */
public class Wall extends Sprite {

    private static final Image DEFAULT_IMAGE;
    static
    {
        DEFAULT_IMAGE = new Image("devbots/resources/wall.png");
    }
    
    public Wall()
    {
        this.setImage(DEFAULT_IMAGE);
    }
    
    public void setLocationBlock(int x, int y)
    {
        this.relocate(x * BLOCK_SZ, y * BLOCK_SZ);
    }

    @Override
    public char getVisionCode() {
        return 'W';
    }
    
}
