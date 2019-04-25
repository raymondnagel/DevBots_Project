/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.actions;

import static devbots.Global.LASER_AP;
import static devbots.Global.LASER_INC;
import devbots.sprites.Bot;
import devbots.sprites.Sprite;
import javafx.geometry.Point2D;

/**
 *
 * @author raymond.nagel
 */
public class BotLaser extends BotAction
{

    private Bot shooter = null;
    private Sprite target = null;
    private Point2D targetPt = null;

    public BotLaser(Bot shooter, Sprite target, Point2D targetPt)
    {
        super(LASER_INC, LASER_AP);
        this.shooter = shooter;
        this.target = target;
        this.targetPt = targetPt;
    }

    @Override
    protected void _inc()
    {
        // TODO:
        // Expand line toward target 1/8th of distance;
        // Make line 1/8th more opaque.
        
        // Another idea would be to show the laser in sections;
        // Front-most section would be opaque, with each trailing
        // section becoming more transparent.
    }

    @Override
    protected void _onFinish()
    {
        // TODO:
        // Remove line; add unsynced explosion at target (if there was a target)
    }


}
