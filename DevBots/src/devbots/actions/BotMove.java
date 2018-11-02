/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.actions;

import devbots.AbsDir;
import devbots.sprites.Bot;
import static devbots.ui.ArenaController.MAP;
import static devbots.Global.ANIM_INC;
import devbots.sprites.Consumable;
import java.awt.Point;
import javafx.application.Platform;
import javafx.scene.layout.Pane;


/**
 *
 * @author Raymond
 */
public final class BotMove extends TimedAction{
    private Bot bot = null;
    private AbsDir direction;
    private Point startLoc;

    public BotMove(Bot bot, AbsDir dir) {
        super(ANIM_INC);
        this.bot = bot;
        this.direction = dir;
        
        startLoc = bot.getLocationBlock();        
    }
    
    @Override
    protected void _inc() {
        this.bot.relocate(this.bot.getLayoutX()+direction.getX(), this.bot.getLayoutY()+direction.getY());
    }

    @Override
    protected void _onFinish() {        
        
        // The departed tile should now be null:
        if (MAP[startLoc.x][startLoc.y] == bot)
        {
            MAP[startLoc.x][startLoc.y] = null;
        }
        
        // See if anything is already in the new tile:
        Point loc = bot.getLocationBlock();
        Object obj = MAP[loc.x][loc.y];
        if (obj != null)
        {
            if (obj instanceof Consumable)
            {
                Consumable cons = (Consumable)obj;
                cons.doEffect(bot);
                cons.flagToRemove();
            }
        }
        
        // Set the bot at the new tile:
        MAP[loc.x][loc.y] = bot;
    }
}
