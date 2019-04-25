/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.sprites;

import static devbots.Global.BLOCK_SZ;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

/**
 *
 * @author Raymond
 */
public abstract class Sprite extends ImageView {
    
    private boolean removeFlag = false;
    
    public void flagToRemove()
    {
        this.removeFlag = true;
    }
    
    public boolean isFlaggedToRemove()
    {
        return this.removeFlag;
    }
    
    public Point2D getCenterPoint()
    {
        double x = this.getLayoutX() + (this.getFitWidth()*.5);
        double y = this.getLayoutY() + (this.getFitHeight()*.5);
        return new Point2D(x, y);
    }
    
}
