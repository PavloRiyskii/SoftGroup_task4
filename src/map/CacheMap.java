package map;

import java.util.*;

/**
 * @author pavlo716@gmail.com
 * @version 1.0
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

    /**
     *
     * @return number of element in the map
     */
    @Override
    public int size() {
        return elements.size();
    }

    /**
     *
     * @return true if map is empty and false if not
     */
    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     *
     * @param key
     * @return true if there are pair vith such key
     */
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

    /**
     *
     * @param value
     * @return true if there are pair vith such value
     */
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

    /**
     *
     * @param key
     * @return the value from map vith such key or null if there are no such key
     */
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


    /**
     *
     * @param key the value of key
     * @param value the value of value
     */
    @Override
    public Object put(Object key, Object value) {
        if(key == null) {
            throw new NullPointerException();
        }
        MapElement elem = new MapElement(key, value);
        Thread t = new Thread(elem);
        t.start();
        this.elements.add(elem);
        return elem;
    }

    /**
     *
     * @param key the value of key
     * @param value the value of value
     * @param lifeTime the evalue of life time of the pair
     * @return
     */
    public Object put(Object key, Object value, int lifeTime) {
        MapElement elem = new MapElement(key, value, lifeTime);
        Thread t = new Thread(elem);
        t.start();
        this.elements.add(elem);
        return elem;
    }


    /**
     *
     * @param key remove the pair vith such key
     * @return the value from removed pair
     */
    @Override
    public Object remove(Object key) {
        Object elem =  get(key);
        this.elements.remove(key);
        return elem;
    }


    /**
     *
     * @param m the method for inserting the map of value into that map
     */
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

    /**
     * clean the whole map
     */
    @Override
    public void clear() {
        this.elements = new ArrayList<>();
    }

    /**
     *
     * @return HashSet of keys
     */
    @Override
    public Set keySet() {
        Set keys = new HashSet();
        ListIterator<MapElement> iter = this.elements.listIterator();
        while(iter.hasNext()){
            keys.add(iter.next().getKey());
        }
        return keys;
    }

    /**
     *
     * @return the collection of values
     */
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

    /**
     * The method for removing the old object from map
     */
    public abstract void removeOld();

    /**
     *
     * @return the list of Entry
     */
    protected List<MapElement> getElementsList() {
        return this.elements;
    }

}
