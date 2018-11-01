/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.sprites;

import static devbots.Global.ROCKETS_UNITS;
import javafx.scene.image.Image;

/**
 *
 * @author raymond.nagel
 */

public class RocketPack extends Consumable {

    private static final Image IMAGE;
    static
    {
        IMAGE = new Image("devbots/resources/rockets.png");
    }
    
    public RocketPack() {
        super("rocket pack", IMAGE);
    }
       
    @Override
    public void doEffect(Bot bot) {
        bot.setRockets(bot.getRockets() + ROCKETS_UNITS);
    }

    @Override
    public char getVisionCode() {
        return 'r';
    }
    
}
