/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.sprites;

import javafx.scene.image.Image;
import static devbots.Global.TREASURE_UNITS;

/**
 *
 * @author raymond.nagel
 */

public class Treasure extends Consumable {

    private static final Image IMAGE;
    static
    {
        IMAGE = new Image("devbots/resources/treasure.png");
    }
    
    public Treasure() {
        super("treasure", IMAGE);
    }
       
    @Override
    public void doEffect(Bot bot) {
        bot.setScore(bot.getScore() + TREASURE_UNITS);
    }

    @Override
    public char getVisionCode() {
        return 't';
    }
    
}
