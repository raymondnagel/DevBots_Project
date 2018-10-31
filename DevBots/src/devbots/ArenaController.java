/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots;

import static devbots.Bot.ACTION_QUEUE;
import static devbots.Bot.BOTS;
import static devbots.Global.ANIM_INC;
import static devbots.Global.H_BLOCKS;
import static devbots.Global.MAX_STEP_TIME;
import static devbots.Global.W_BLOCKS;
import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;
import javax.script.ScriptException;

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
    private Thread mainLoopThread = null;     
    private int turn = 1;
    private boolean exit = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int NUM_TEST_BOTS = 1;
        
        //for (int y = 1; y <= NUM_TEST_BOTS; y++)
        //    addBot(Bot.createBot(), 15, 10 + (y*2) );
        
        addBot(new File("robots/raybot"), 15, 10 );
        
        makeMap(0);
    }    
    
    private void makeMap(int numWalls)
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
    }
    
    private void addWall(int x, int y)
    {
        Wall w = new Wall();
        MAP[x][y] = w;
        w.setLocationBlock(x, y);
        pnArenaPane.getChildren().add(w);
    }
    
    private void addBot(File botFile, int x, int y)
    {
        try {
            
            // Create the bot from file:
            Bot bot = Bot.createBot(botFile);
            
            // Add the bot:
            BOTS.add(bot);
            bot.setLocationBlock(x, y);
            pnArenaPane.getChildren().add(bot);
            
            // Add an info panel for the bot:
            BotPanel panel = new BotPanel(bot);
            vboxBots.getChildren().add(panel);
            
        } catch (ScriptException ex) {
            System.err.println(ex);
        }
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
        for (int i = 0; i < ANIM_INC; i++)
        {
            long startTime = System.currentTimeMillis();
            
            // Begin with the last action, using a decrement, so items can be removed without causing problems:
            for (int a = ACTION_QUEUE.size()-1; a >= 0; a--)
            {                                
                ACTION_QUEUE.get(a).doInc();
                if (ACTION_QUEUE.get(a).isDone())
                {
                    ACTION_QUEUE.remove(a);
                }
            }
            
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime < MAX_STEP_TIME)
                delay(MAX_STEP_TIME - elapsedTime);
        }
        
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
