import java.util.Map;
import java.util.Map.Entry;
/**
 * Created by pavlo on 09.03.17.
 */
public class MapElement implements Runnable, Entry {

    private Object key;
    private Object value;
    private int time;

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public Object setValue(Object value) {
        this.value = value;
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        MapElement e = (MapElement) o;
        if(e.getValue() == this.value && e.getKey() == this.key && e.getTime() == this.time)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return key.hashCode() + value.hashCode();
    }

    public int getTime() {
        return time;
    }

    public MapElement(Object key, Object value) {
        this.key = key;
        this.value = value;
        this.time = 50000;
    }

    public MapElement(Object key, Object value, int time) {
        this.key = key;
        this.value = value;
        this.time = time;
    }

    /**
     * Method for controll of life circle of the the current object
     */
    @Override
    public void run(){
        while(time > 0) {
            try {
                synchronized (this) {
                    this.wait(10000);
                }
                time--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return;
    }
}
