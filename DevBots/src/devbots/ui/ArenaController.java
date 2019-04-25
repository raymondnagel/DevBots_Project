/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.ui;

import devbots.sprites.Bot;
import devbots.sprites.Wall;
import static devbots.sprites.Bot.BOTS;
import static devbots.Global.H_BLOCKS;
import static devbots.Global.MAX_INC;
import static devbots.Global.MAX_STEP_TIME;
import static devbots.Global.W_BLOCKS;
import devbots.sprites.BombPack;
import devbots.sprites.Consumable;
import devbots.sprites.FuelTank;
import devbots.sprites.RocketPack;
import devbots.sprites.Sprite;
import devbots.sprites.Treasure;
import duct.DuctTools;
import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;
import javax.script.ScriptException;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import static devbots.sprites.Bot.ACTIONS;

/**
 * FXML Controller class
 *
 * @author Raymond
 */
public class ArenaController implements Initializable {

    @FXML
    private Pane pnArenaPane;
    @FXML
    private VBox vboxBots;
    @FXML
    private MenuItem mniStartSim;
        
    public static Object[][] MAP = new Object[W_BLOCKS][H_BLOCKS];
    public static ArenaController Controller = null;
    private Thread mainLoopThread = null;     
    private int turn = 1;
    private boolean exit = false;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Controller = this;
        pnArenaPane.setBackground(new Background(new BackgroundFill(new Color(.615, .547, .482, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));
        makeMap(150, 50, 20, 15, 10);        
        loadBots();        
    }    
    
    private void loadBots()
    {
        File botFolder = new File("robots");
        File[] botFolders = botFolder.listFiles((File file) -> {
            if (file.isDirectory())
            {
                return new File(file, "icon.png").exists() &&
                       new File(file, "program.js").exists();
            }
            else
                return false;
        });
        
        for (File f: botFolders)
            addBot(f);        
        
        for (Bot b: BOTS)
        {
            b.updatePanel();
        }
    }
    
    private void makeMap(int numWalls, int numTreasures, int numFuels, int numRockets, int numBombs)
    {
        for (int x = 0; x < W_BLOCKS; x++)
            for (int y = 0; y < H_BLOCKS; y++)
                MAP[x][y] = null;
        
        // Create top and bottom borders:
        for (int x = 0; x < W_BLOCKS; x++)
        {
            addWall(x, 0);
            addWall(x, H_BLOCKS-1);
        }
        // Create left and right borders:
        for (int y = 1; y < H_BLOCKS-1; y++)
        {
            addWall(0, y);
            addWall(W_BLOCKS-1, y);
        }
        
        // Create random walls:
        Random r = new Random(System.nanoTime());
        for (int w = 0; w < numWalls; w++)
        {
            int x = r.nextInt(W_BLOCKS);
            int y = r.nextInt(H_BLOCKS);
            addWall(x, y);
        }
        
        // Create random treasures:
        for (int c = 0; c < numTreasures; c++)
            addConsumable(new Treasure());
        
        // Create random fuel tanks:
        for (int c = 0; c < numFuels; c++)
            addConsumable(new FuelTank());
        
        // Create random rocket packs:
        for (int c = 0; c < numRockets; c++)
            addConsumable(new RocketPack());
        
        // Create random bomb packs:
        for (int c = 0; c < numBombs; c++)
            addConsumable(new BombPack());
    }
    
    private void addWall(int x, int y)
    {
        Wall w = new Wall();
        MAP[x][y] = w;
        w.setLocationBlock(x, y);
        pnArenaPane.getChildren().add(w);
    }
    
    private void addBot(File botFile)
    {
        try {
            
            // Create the bot from file:
            Bot bot = Bot.createBot(botFile);
            
            // Add the bot:
            BOTS.add(bot);            
            pnArenaPane.getChildren().add(bot);
            
            // Place the bot:
            int x = 0, y = 0;
            do
            {
                x = DuctTools.getRandomInt(0, W_BLOCKS);
                y = DuctTools.getRandomInt(0, H_BLOCKS);
            } while (MAP[x][y] != null);            
            bot.setLocationBlock(x, y);
            
            // Add an info panel for the bot:
            BotPanel panel = BotPanel.createForBot(bot);
            vboxBots.getChildren().add(panel);
            
        } catch (ScriptException ex) {
            System.err.println(ex);
        }
    }
    
    private void addConsumable(Consumable c)
    {        
        int x = DuctTools.getRandomInt(0, W_BLOCKS);
        int y = DuctTools.getRandomInt(0, H_BLOCKS);
        if (MAP[x][y] == null)
        {
            pnArenaPane.getChildren().add(c);
            MAP[x][y] = c;
            c.setLocationBlock(x, y);
        }
    }
    
    public void addLaser(Point2D start, Point2D end)
    {
        Line laser = new Line(start.getX(), start.getY(), end.getX(), end.getY());
        laser.setStrokeWidth(1.5);
        laser.setStroke(Color.GREENYELLOW);
        pnArenaPane.getChildren().add(laser);
    }
    
    @FXML
    private void startSim(ActionEvent event) {
        
        pnArenaPane.getScene().getWindow().setOnHiding((WindowEvent winEvent) -> {
            exit = true;
        });
        
        mainLoopThread = new Thread(() -> {
            while (!exit)
                mainLoop();
        }, "Main Loop Thread");
        
        mainLoopThread.start();
    }
    
    private void mainLoop()
    {
        for (Bot b: BOTS)
        {
            b.rechargeTurnActions();
            b.runProgram();
        }        
        
        // Increment all queued actions at the same time:
        for (int i = 0; i < MAX_INC; i++)
        {
            long startTime = System.currentTimeMillis();
            
            // Begin with the last action, using a decrement, so items can be removed without causing problems:
            for (int a = ACTIONS.size()-1; a >= 0; a--)
            {                                
                ACTIONS.get(a).doInc();
                if (ACTIONS.get(a).isDone())
                {
                    ACTIONS.remove(a);
                }
            }
            
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime < MAX_STEP_TIME)
                delay(MAX_STEP_TIME - elapsedTime);
        }
        
        // Execute all FX stuff at once here:
        Platform.runLater(() -> {
            for (int n = pnArenaPane.getChildren().size() - 1; n >= 0; n--)
            {
                if (pnArenaPane.getChildren().get(n) instanceof Sprite)
                {
                    Sprite s = (Sprite)pnArenaPane.getChildren().get(n);                    
                    if (s instanceof Bot)
                    {
                        ((Bot)s).updatePanel();
                    }                    
                    if (s.isFlaggedToRemove())
                    {
                        pnArenaPane.getChildren().remove(s);                    
                    }
                }
                    
            }
        });
        
        System.out.println("End turn " + turn);
        turn++;
    }
    
    private void delay(long millis)
    {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {}
    }
}
