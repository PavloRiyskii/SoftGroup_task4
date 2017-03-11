import java.util.*;

/**
 * Created by pavlo on 09.03.17.
 */
public abstract class CacheMap implements Map {
    private List<MapElement> elements;
    private Remover remover;

    public CacheMap() {
        this.elements = new ArrayList<>();
        this.remover = new Remover(this);
        Thread t = new Thread(remover);
        t.start();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        ListIterator<MapElement> iter = elements.listIterator();
        while(iter.hasNext()) {
            MapElement elem = iter.next();
            if(elem.getKey() == key) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        ListIterator<MapElement> iter = elements.listIterator();
        while(iter.hasNext()) {
            MapElement elem = iter.next();
            if(elem.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        ListIterator<MapElement> iter = elements.listIterator();
        while(iter.hasNext()) {
            MapElement elem = iter.next();
            if(elem.getKey() == key) {
                return elem.getValue();
            }
        }
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        MapElement elem = new MapElement(key, value);
        Thread t = new Thread(elem);
        t.start();
        this.elements.add(elem);
        return elem;
    }

    public Object put(Object key, Object value, int lifeTime) {
        MapElement elem = new MapElement(key, value, lifeTime);
        Thread t = new Thread(elem);
        t.start();
        this.elements.add(elem);
        return elem;
    }



    @Override
    public Object remove(Object key) {
        Object elem =  get(key);
        this.elements.remove(key);
        return elem;
    }

    @Override
    public void putAll(Map m) {
        Set set = m.keySet();
        Iterator iter = set.iterator();
        while(iter.hasNext()) {
            Object key = iter.next();
            Object value = m.get(key);
            MapElement elem = new MapElement(key, value);
            this.elements.add(elem);
            Thread t = new Thread(elem);
            t.start();
        }
    }

    @Override
    public void clear() {
        this.elements = new ArrayList<>();
    }

    @Override
    public Set keySet() {
        Set keys = new HashSet();
        ListIterator<MapElement> iter = this.elements.listIterator();
        while(iter.hasNext()){
            keys.add(iter.next().getKey());
        }
        return keys;
    }

    @Override
    public Collection values() {
        Collection colection = new ArrayList();
        ListIterator<MapElement> iter = elements.listIterator();
        while(iter.hasNext()) {
            colection.add(iter.next().getValue());
        }
        return colection;
    }

    @Override
    public Set<Entry> entrySet() {
        Set<Entry> set = new HashSet<>();
        ListIterator<MapElement> iter = this.elements.listIterator();
        while(iter.hasNext()) {
            MapElement elem = iter.next();
            set.add(elem);
        }
        return set;
    }

    public abstract void removeOld();

    protected List<MapElement> getElementsList() {
        return this.elements;
    }

}
