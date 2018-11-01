/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots;

import static devbots.ArenaController.MAP;
import static devbots.Global.ANIM_INC;
import java.awt.Point;


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
        if (MAP[startLoc.x][startLoc.y] == bot)
        {
            MAP[startLoc.x][startLoc.y] = null;
        }
        Point loc = bot.getLocationBlock();
        MAP[loc.x][loc.y] = bot;
    }
}
