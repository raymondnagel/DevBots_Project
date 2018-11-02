/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.actions;

import devbots.sprites.Bot;
import devbots.sprites.Sprite;
import javafx.geometry.Point2D;

/**
 *
 * @author raymond.nagel
 */
public class BotLaser extends InstantAction {

    private Bot shooter = null;
    private Sprite target = null;
    private Point2D targetPt = null;
    
    public BotLaser(Bot shooter, Sprite target, Point2D targetPt) {
        this.shooter = shooter;
        this.target = target;
        this.targetPt = targetPt;
    }
        
    @Override
    public void start() {
        Point2D start = shooter.getFirePoint();
        
    }

    @Override
    public void finish() {
        
    }
    
}
