import java.util.ConcurrentModificationException;
import java.util.ListIterator;

/**
 * Created by pavlo on 11.03.17.
 */
public class CacheMapImpl extends  CacheMap{

    @Override
    public void removeOld() throws ConcurrentModificationException {
        ListIterator<MapElement> iter = this.getElementsList().listIterator();
        while(iter.hasNext()) {
            MapElement elem = iter.next();
            if(elem.getTime() > 0) {
                this.getElementsList().remove(elem);
            }
        }
    }
}
