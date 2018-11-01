/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.sprites;

import javafx.scene.image.Image;

/**
 *
 * @author raymond.nagel
 */
public abstract class Consumable extends Traversable {

    private String name = null;
    
    public Consumable(String name, Image icon) {
        this.name = name;
        setImage(icon);
    }

    public String getName()
    {
        return this.name;
    }
    
    public abstract void doEffect(Bot bot);
}
