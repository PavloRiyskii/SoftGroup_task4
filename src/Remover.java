/**
 * Created by pavlo on 11.03.17.
 */
public class Remover implements Runnable {

    private CacheMap map;

    public Remover(CacheMap map) {
        this.map = map;
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println(1);
                synchronized (this) {
                    this.wait(10000);
                }
                System.out.println(2);
                map.removeOld();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
