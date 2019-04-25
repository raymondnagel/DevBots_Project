/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.sprites;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

/**
 *
 * @author raymond.nagel
 */
public abstract class Anim extends Sprite
{
    private final AnimationTimer timer;
    private Integer frameIndex = 0;

    public Anim(long frameMillis, Image[] frames)
    {
        timer = new AnimationTimer()
        {
            @Override
            public void handle(long duration)
            {
                frameIndex++;
                if (frameIndex >= frames.length)
                {
                    flagToRemove();
                    return;
                }
                setImage(frames[frameIndex]);                
            }
        };
    }
    
    public abstract void bindTo(Sprite sprite);
    
    protected void start()
    {        
        timer.start();
    }
    
    
}
