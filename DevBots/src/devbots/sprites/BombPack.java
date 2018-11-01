/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.sprites;

import static devbots.Global.BOMBS_UNITS;
import javafx.scene.image.Image;

/**
 *
 * @author raymond.nagel
 */

public class BombPack extends Consumable {

    private static final Image IMAGE;
    static
    {
        IMAGE = new Image("devbots/resources/bombs.png");
    }
    
    public BombPack() {
        super("bomb pack", IMAGE);
    }
       
    @Override
    public void doEffect(Bot bot) {
        bot.setBombs(bot.getBombs() + BOMBS_UNITS);
    }

    @Override
    public char getVisionCode() {
        return 'r';
    }
    
}
