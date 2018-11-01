/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.sprites;

import static devbots.Global.BLOCK_SZ;
import javafx.scene.image.ImageView;

/**
 *
 * @author Raymond
 */
public abstract class Sprite extends ImageView {
    
    private boolean removeFlag = false;
    
    public abstract char getVisionCode();
    
    public void flagToRemove()
    {
        this.removeFlag = true;
    }
    
    public boolean isFlaggedToRemove()
    {
        return this.removeFlag;
    }
    
    public void setLocationBlock(int x, int y)
    {
        this.relocate(x * BLOCK_SZ, y * BLOCK_SZ);
    }
}
