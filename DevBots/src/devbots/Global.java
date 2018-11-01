/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots;

/**
 *
 * @author Raymond
 */
public class Global {
    
    public static final int MAP_W = 1024;
    public static final int MAP_H = 1024;
    public static final double BLOCK_SZ = 32.0;
    public static final int ANIM_INC = (int)(BLOCK_SZ/1);
    public static final int W_BLOCKS = MAP_W / (int)BLOCK_SZ;
    public static final int H_BLOCKS = MAP_H / (int)BLOCK_SZ;
    
    public static final int MAX_ACTIONS = 3;
    public static final int RECHARGE_ACTIONS = 3;
    public static final int TURN_ACTIONS = 2;
    public static final int MOVE_ACTIONS = 2;
    public static final int ROCKET_ACTIONS = 2;
    public static final int BOMB_ACTIONS = 2;
    public static final int LASER_ACTIONS = 1;
    public static final int LOOK_ACTIONS = 1;
    public static final int SCAN_ACTIONS = 1;
    
    public static final int SYNTH_FUEL = 1;
    public static final int TURN_FUEL = 2;
    public static final int MOVE_FUEL = 3;
    public static final int ROCKET_FUEL = 1;
    public static final int BOMB_FUEL = 1;
    public static final int LASER_FUEL = 3;
    public static final int LOOK_FUEL = 1;
    public static final int SCAN_FUEL = 1;
    
    public static final long MAX_STEP_TIME = 20; // 20=normal, 80=slow
    public static final long MAX_TURN_MS = 10;
    
    public static final int MAX_FUEL = 999;
    public static final int MAX_ROCKETS = 6;
    public static final int MAX_BOMBS = 3;
    
    public static final int VISION_RANGE = 7;
    public static final int SCAN_SZ = 3;
    
    public static final int TREASURE_UNITS = 10;
    public static final int FUELTANK_UNITS = 100;
    public static final int ROCKETS_UNITS = 2;
    public static final int BOMBS_UNITS = 1;
    
    public static boolean isInBounds(int x, int y)
    {
        return x >= 0 && x < W_BLOCKS &&
               y >= 0 && y < H_BLOCKS;
    }
}
