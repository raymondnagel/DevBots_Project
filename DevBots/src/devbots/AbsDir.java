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
public enum AbsDir
{
    N("North", 0, -1, 0.0, 0),
    NE("Northeast", 1, -1, 45.0, 1),
    E("East", 1, 0, 90.0, 2),
    SE("Southeast", 1, 1, 135.0, 3),
    S("South", 0, 1, 180.0, 4),
    SW("Southwest", -1, 1, 225.0, 5),
    W("West", -1, 0, 270.0, 6),
    NW("Northwest", -1, -1, 315.0, 7);

   private String fullName = null;
   private int x = 0;
   private int y = 0;
   private double degrees = 0.0;
   private int index = 0;

   private AbsDir(String fullName, int x, int y, double degrees, int index) {
       this.fullName = fullName;
       this.x = x;
       this.y = y;
       this.degrees = degrees;
       this.index = index;
   }

   public String getFullName()
   {
       return this.fullName;
   }
   
   public int getX()
   {
       return this.x;
   }

   public int getY()
   {
       return this.y;
   }

   public double getDegrees()
   {
       return this.degrees;
   }
   
   public int getIndex()
   {
       return this.index;
   }
   
   public static AbsDir toOpposite(AbsDir dir)
   {
        switch (dir)
        {
            case N: return S;
            case NE: return SW;
            case E: return W;
            case SE: return NW;
            case S: return N;
            case SW: return NE;
            case W: return E;
            case NW: return SE;
            default: return null;
        }    
   }
   public static AbsDir toCw(AbsDir dir)
   {
        switch (dir)
        {
            case N: return NE;
            case NE: return E;
            case E: return SE;
            case SE: return S;
            case S: return SW;
            case SW: return W;
            case W: return NW;
            case NW: return N;
            default: return null;
        }    
   }
   
   public static AbsDir toCcw(AbsDir dir)
   {
        switch (dir)
        {
            case N: return NW;
            case NE: return N;
            case E: return NE;
            case SE: return E;
            case S: return SE;
            case SW: return S;
            case W: return SW;
            case NW: return W;
            default: return null;
        }    
   }
   
   public static AbsDir getRelative(AbsDir abs, RelDir rel)
   {      
       int a = abs.getIndex();
       int r = rel.getIndex();
       
       a += r;
       if (a >= AbsDir.values().length)
           a -= AbsDir.values().length;
       
       return AbsDir.values()[a];
   }
}