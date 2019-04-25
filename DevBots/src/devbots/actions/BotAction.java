/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devbots.actions;

/**
 *
 * @author Raymond
 */
public abstract class BotAction
{

    private int maxIncs = 0;
    private int incs = 0;
    private int apCost = 0;

    public BotAction(int maxIncs, int apCost)
    {
        this.maxIncs = maxIncs;
        this.apCost = apCost;
    }

    public void doInc()
    {
        _inc();
        incs++;
        if (isDone())
        {
            _onFinish();
        }
    }

    public int getMaxIncs()
    {
        return this.maxIncs;
    }

    public int getApCost()
    {
        return this.apCost;
    }
    
    public boolean isDone()
    {
        return incs >= maxIncs;
    }

    protected abstract void _inc();

    protected abstract void _onFinish();

}
