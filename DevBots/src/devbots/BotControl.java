/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots;

import devbots.sprites.Bot;


/**
 * A BotControl is the Bot programmer's interface to his/her Bot.
 * This allows access to only the methods that the programmer should be able to access, rather than giving
 * them direct access to the Bot object, which extends Node.
 * 
 * @author Raymond
 */
public class BotControl {
    
    private Bot bot = null;

    public BotControl(Bot bot) {
        this.bot = bot;
    }
    
    public AbsDir getFaceDir()
    {
        return bot.getFaceDir();
    }
    
    public Character getSightObject()
    {
        return bot.getSightObject();
    }
    
    public Integer getSightDistance()
    {
        return bot.getSightDistance();
    }
    
    public Character[][] getScanObjects()
    {
        return bot.getScanObjects();
    }
    
    public int getActions()
    {
        return bot.getActions();
    }
    
    public int getFuel()
    {
        return bot.getFuel();
    }
    
    public int getRockets()
    {
        return bot.getRockets();
    }
    
    public int getBombs()
    {
        return bot.getBombs();
    }
    
    public int getScore()
    {
        return bot.getScore();
    }
    
    public void say(String text)
    {
        System.out.println("[" + bot.getName() + "]: \"" + text + "\"");
        new SpeechBubble().bindTo(bot);
    }
    
    public boolean scan()
    {
        return bot.scan();
    }
    
    public boolean look()
    {
        return bot.look();
    }        
    
    public boolean move(RelDir d)
    {        
        AbsDir abs = AbsDir.getRelative(bot.getFaceDir(), d);
        return bot.move(abs);
    }
    
    public boolean turnCw()
    {
        return bot.turnCw();
    }
    
    public boolean turnCcw()
    {
        return bot.turnCcw();
    }
}
