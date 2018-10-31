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
public enum RelDir
{
    F("Foreward", 0, -1, 0.0, 0),
    FR("Fwd-right", 1, -1, 45.0, 1),
    R("Right", 1, 0, 90.0, 2),
    BR("Back-right", 1, 1, 135.0, 3),
    B("Backward", 0, 1, 180.0, 4),
    BL("Back-left", -1, 1, 225.0, 5),
    L("Left", -1, 0, 270.0, 6),
    FL("Fwd-left", -1, -1, 315.0, 7);

   private String fullName = null;
   private int x = 0;
   private int y = 0;
   private double degrees = 0.0;
   private int index = 0;

   private RelDir(String fullName, int x, int y, double degrees, int index) {
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
   
   public static RelDir toOpposite(RelDir dir)
   {
        switch (dir)
        {
            case F: return B;
            case FR: return BL;
            case R: return L;
            case BR: return FL;
            case B: return F;
            case BL: return FR;
            case L: return R;
            case FL: return BR;
            default: return null;
        }    
   }
   public static RelDir toCw(RelDir dir)
   {
        switch (dir)
        {
            case F: return FR;
            case FR: return R;
            case R: return BR;
            case BR: return B;
            case B: return BL;
            case BL: return L;
            case L: return FL;
            case FL: return F;
            default: return null;
        }    
   }
   
   public static RelDir toCcw(RelDir dir)
   {
        switch (dir)
        {
            case F: return FL;
            case FR: return F;
            case R: return FR;
            case BR: return R;
            case B: return BR;
            case BL: return B;
            case L: return BL;
            case FL: return L;
            default: return null;
        }    
   }
      
}