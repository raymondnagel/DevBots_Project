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
public abstract class TimedAction {
    
    private int maxIncs = 0;
    private int incs = 0;

    public TimedAction(int maxIncs) {
        this.maxIncs = maxIncs;
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
    
    public boolean isDone()
    {
        return incs >= maxIncs;
    }
    
    protected abstract void _inc();
    
    protected abstract void _onFinish();
    
}
