/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots;

import static devbots.ArenaController.MAP;
import javafx.scene.image.Image;
import static devbots.Global.BLOCK_SZ;
import static devbots.Global.MAX_ACTIONS;
import static devbots.Global.MAX_ROCKETS;
import static devbots.Global.MAX_BOMBS;
import static devbots.Global.MAX_FUEL;
import static devbots.Global.MAX_TURN_MS;
import static devbots.Global.LOOK_ACTIONS;
import static devbots.Global.SCAN_ACTIONS;
import static devbots.Global.MOVE_ACTIONS;
import static devbots.Global.TURN_ACTIONS;
import static devbots.Global.VISION_RANGE;
import static devbots.Global.SCAN_SZ;
import duct.DuctContext;
import duct.DuctTools;
import java.awt.Point;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.script.ScriptException;

/**
 *
 * @author Raymond
 */
public class Bot extends Sprite {
    
    public static final ArrayList<TimedAction> ACTION_QUEUE = new ArrayList<>();
    public static final ArrayList<Bot> BOTS = new ArrayList<>();

    private static Image DEFAULT_IMAGE;
    static
    {
        DEFAULT_IMAGE = new Image("devbots/resources/bot.png");
    }
    
    private String name = null;
    private String program = null;
    private DuctContext duct = null;
    private BotControl controller = null;    
    private AbsDir faceDir = AbsDir.N;
    private Integer sightDistance = null;
    private Character sightObject = null;
    private Character[][] scanObjects = null;
    private int actions = 0;
    private int fuel = MAX_FUEL;
    private int rockets = MAX_ROCKETS;
    private int bombs = MAX_BOMBS;
    
    
    private Bot() {
        this.setImage(DEFAULT_IMAGE);
        this.duct = new DuctContext();
    }
    
    public static Bot createBot(File botDir) throws ScriptException
    {
        Bot bot = new Bot();
        bot.controller = new BotControl(bot);
        bot.duct.registerDuctClass(DuctTools.class);
        bot.duct.registerClass(Global.class);
        bot.duct.registerClass(AbsDir.class);
        bot.duct.registerClass(RelDir.class);
        bot.duct.registerObject("bot", bot.controller);  
        
        bot.name = botDir.getName();
        
        File iconFile = new File(botDir, "icon.png");
        if (iconFile.exists())
        {
            try {
                bot.setImage(new Image(iconFile.toURI().toURL().toString()));
            } catch (MalformedURLException ex) {
                System.err.print(ex);
            }
        }
        File codeFile = new File(botDir, "program.js");
        if (codeFile.exists())
        {
            bot.program = ReadWriteTextFile.getContents(codeFile);
            bot.duct.evaluateJavascriptAndThrow(bot.program);
        }
        
        return bot;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public DuctContext getDuct()
    {
        return this.duct;
    }
    
    public BotControl getController()
    {
        return this.controller;
    }
    
    public boolean runProgram()
    {
        long time = System.currentTimeMillis();
        duct.evaluateJavascript("takeTurn();");
        time = System.currentTimeMillis() - time;
        if (time > MAX_TURN_MS)
        {
            System.out.println("Timeout: " + name);
            return false;
        }
        return true;
    }
    
    public void setLocationBlock(int x, int y)
    {
        this.relocate(x * BLOCK_SZ, y * BLOCK_SZ);
    }
    
    public void setLocationBlock(Point blockPoint)
    {
        setLocationBlock(blockPoint.x, blockPoint.y);
    }
    
    public Point getLocationBlock()
    {
        return new Point((int)(getLayoutX() / BLOCK_SZ), (int)(getLayoutY() / BLOCK_SZ));
    }
    
    public void rechargeTurnActions()
    {
        this.actions = MAX_ACTIONS;
    }
    
    private boolean spendActions(int numActions)
    {
        if (numActions <= actions)
        {
            actions -= numActions;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private boolean isBlocked(AbsDir d)
    {
        Point p = getLocationBlock();
        p.x += d.getX();
        p.y += d.getY();
        
        if (Global.isInBounds(p.x, p.y) && MAP[p.x][p.y] == null)
            return false;
        else
            return true;
    }
    
    public AbsDir getFaceDir()
    {
        return this.faceDir;
    }
    
    public void setFaceDir(AbsDir d)
    {
        this.faceDir = d;
    }
    
    public Character getSightObject()
    {
        return this.sightObject;
    }
    
    public Integer getSightDistance()
    {
        return this.sightDistance;
    }
    
    public Character[][] getScanObjects()
    {
        return this.scanObjects;
    }
    
    public boolean look()
    {
        if (spendActions(LOOK_ACTIONS))
        {
            Point p = this.getLocationBlock();
            for (int v = 1; v <= VISION_RANGE; v++)
            {
                p.x += faceDir.getX();
                p.y += faceDir.getY();
                if (MAP[p.x][p.y] != null)
                {
                    Object obj = MAP[p.x][p.y];
                    if (obj instanceof Sprite)
                    {
                        this.sightObject = ((Sprite)obj).getVisionCode();
                        this.sightDistance = v;
                        return true;
                    }
                }
            }
            this.sightObject = null;
            this.sightDistance = null;
            return true;
        }
        else
            return false;
    }
    
    public boolean scan()
    {
        if (spendActions(SCAN_ACTIONS))
        {
            Point p = this.getLocationBlock();
            int c = SCAN_SZ/2;
            scanObjects = new Character[SCAN_SZ][SCAN_SZ];            
            scanObjects[c][c] = this.getVisionCode();
            for (int d = 0; d < AbsDir.values().length; d++)
            {
                AbsDir sd = AbsDir.values()[d];
                int x = p.x + sd.getX();
                int y = p.y + sd.getY();
                if (!Global.isInBounds(x, y))
                    scanObjects[c+sd.getX()][c+sd.getY()] = null;
                else
                {
                    if (MAP[x][y] != null)
                    {
                        Object obj = MAP[x][y];
                        if (obj instanceof Sprite)
                        {
                            scanObjects[c+sd.getX()][c+sd.getY()] = ((Sprite)obj).getVisionCode();
                        }
                    }
                    else
                        scanObjects[c+sd.getX()][c+sd.getY()] = ' ';
                }
            }
            return true;
        }
        else
            return false;
    }
    
    public boolean move(AbsDir d)
    {
        if ( (!isBlocked(d)) && spendActions(MOVE_ACTIONS) )
        {
            ACTION_QUEUE.add(new BotMove(this, d));
            return true;
        }
        else
            return false;
    }
    
    public boolean turn(AbsDir d)
    {       
        if (spendActions(TURN_ACTIONS))
        {            
            ACTION_QUEUE.add(new BotTurn(this, d));
            return true;
        }
        else
            return false;
    }
    
    public boolean turnCw()
    {        
        return turn(AbsDir.toCw(faceDir));        
    }
    
    public boolean turnCcw()
    {
        return turn(AbsDir.toCcw(faceDir));
    }
    
    @Override
    public char getVisionCode() {
        return 'B';
    }
}
