/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.actions;

import devbots.AbsDir;
import static devbots.Global.MOVE_AP;
import static devbots.Global.MOVE_INC;
import devbots.sprites.Bot;
import static devbots.ui.ArenaController.MAP;
import devbots.sprites.Consumable;
import java.awt.Point;

/**
 *
 * @author Raymond
 */
public final class BotMove extends BotAction
{

    private Bot bot = null;
    private AbsDir direction;
    private Point startLoc;

    public BotMove(Bot bot, AbsDir dir)
    {
        super(MOVE_INC, MOVE_AP);
        this.bot = bot;
        this.direction = dir;

        startLoc = bot.getLocationBlock();
    }

    @Override
    protected void _inc()
    {
        this.bot.relocate(this.bot.getLayoutX() + direction.getX(), this.bot.getLayoutY() + direction.getY());
    }

    @Override
    protected void _onFinish()
    {

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
                Consumable cons = (Consumable) obj;
                cons.doEffect(bot);
                cons.flagToRemove();
            }
        }

        // Set the bot at the new tile:
        MAP[loc.x][loc.y] = bot;
    }
}
