package sample.classes.draw;

import java.util.concurrent.atomic.AtomicInteger;

public class DrawLogic {
    private AtomicInteger i = new AtomicInteger(0);
    private AtomicInteger j = new AtomicInteger(0);

  /*  public void startDraw() {
        // main loop
        for ( ; i.get() < Draw.baskets.length; i.addAndGet(1)) {
            for ( ; j.get() < Draw.groups.length; j.addAndGet(1)) {
            }
        }
    }
*/
    public int getI() {
        return i.get();
    }

    public int getJ() {
        return j.get();
    }
}
