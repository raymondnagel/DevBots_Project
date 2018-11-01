/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.sprites;

import static devbots.Global.FUELTANK_UNITS;
import javafx.scene.image.Image;

/**
 *
 * @author raymond.nagel
 */

public class FuelTank extends Consumable {

    private static final Image IMAGE;
    static
    {
        IMAGE = new Image("devbots/resources/fuel.png");
    }
    
    public FuelTank() {
        super("fuel tank", IMAGE);
    }
       
    @Override
    public void doEffect(Bot bot) {
        bot.setFuel(bot.getFuel() + FUELTANK_UNITS);
    }

    @Override
    public char getVisionCode() {
        return 'f';
    }
    
}
