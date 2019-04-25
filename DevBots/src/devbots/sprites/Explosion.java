/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.sprites;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 *
 * @author raymond.nagel
 */
public class Explosion extends Anim
{

    private static Image[] frames;

    static
    {
        frames = new Image[0];
        for (int f = 0; f < frames.length; f++)
        {
            frames[f] = new Image("devbots/resources/exp_" + f + ".png");
        }
    }

    public Explosion()
    {
        super(50, frames);
    }

    @Override
    public void bindTo(Sprite sprite)
    {
        this.xProperty().bind(sprite.xProperty());
        this.yProperty().bind(sprite.yProperty());
        ((Pane)sprite.getParent()).getChildren().add(this);
        start();
    }

}
