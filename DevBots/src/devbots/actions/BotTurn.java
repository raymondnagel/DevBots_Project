/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.actions;

import devbots.AbsDir;
import static devbots.Global.TURN_AP;
import static devbots.Global.TURN_INC;
import devbots.sprites.Bot;

/**
 *
 * @author Raymond
 */
public final class BotTurn extends BotAction
{
    private Bot bot = null;
    private double fromAngle = 0.0;
    private double toAngle = 0.0;
    private double actual = 0.0;
    private double change = 0.0;
    private AbsDir direction = null;

    public BotTurn(Bot bot, AbsDir dir)
    {
        super(TURN_INC, TURN_AP);
        this.bot = bot;
        this.fromAngle = this.bot.getFaceDir().getDegrees();
        this.toAngle = dir.getDegrees();
        this.actual = this.fromAngle;
        this.direction = dir;
        adjustToAngle();
    }

    private void adjustToAngle()
    {
        double diff = toAngle - fromAngle;
        if (Math.abs(diff) > 180)
        {
            if (toAngle >= fromAngle)
            {
                fromAngle += 360.0;
            } else
            {
                toAngle += 360.0;
            }
            diff = toAngle - fromAngle;
        }
        this.change = diff / this.getMaxIncs();
    }

    private void changeAngle()
    {
        actual += change;
        this.bot.setRotate(normalizeAngle(actual));
    }

    @Override
    protected void _inc()
    {
        // Perform the increment:
        changeAngle();
    }

    @Override
    protected void _onFinish()
    {
        // Ensure that we finish precisely toward the given direction:
        this.bot.setRotate(direction.getDegrees());
        this.bot.setFaceDir(direction);
    }

    private double normalizeAngle(double angle)
    {
        return angle % 360;
    }

}
