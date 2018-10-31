/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots;

import static devbots.Global.ANIM_INC;


/**
 *
 * @author Raymond
 */
public final class BotMove extends TimedAction{
    private Bot bot = null;
    private AbsDir direction;

    public BotMove(Bot bot, AbsDir dir) {
        super(ANIM_INC);
        this.bot = bot;
        this.direction = dir;
    }
    
    @Override
    protected void _inc() {
        this.bot.relocate(this.bot.getLayoutX()+direction.getX(), this.bot.getLayoutY()+direction.getY());
    }

    @Override
    protected void _onFinish() {
        
    }
}
