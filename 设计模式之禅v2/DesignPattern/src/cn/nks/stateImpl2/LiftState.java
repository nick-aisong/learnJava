package cn.nks.stateImpl2;

/**
 * Created by NKS on 2017/9/24.
 */
public abstract class LiftState {

    protected Context context;

    public void setContext(Context _context){
        this.context = _context;
    }

    public abstract void open();

    public abstract void close();

    public abstract void run();

    public abstract void stop();

}
