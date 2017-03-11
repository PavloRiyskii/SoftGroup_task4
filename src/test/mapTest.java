package test;

import static org.junit.Assert.*;

import map.CacheMap;
import map.CacheMapImpl;
import org.junit.Before;
import org.junit.Test;
/**
 * Created by pavlo on 11.03.17.
 */
public class mapTest {

    private CacheMap map;

    @Test
    public void testAdd() {
        map.put(new Object(), 5);
        assertNotEquals("The map is empty",0 , map.size());
    }

    @Test(expected = NullPointerException.class)
    public void testAddNull() {
        map.put(null, 8);
    }


    @Test
    public void testRemove() {
        Object o = new Object();
        map.put(o, 6);
        assertEquals(6, map.remove(o));
    }

    @Test
    public void testGet() {
        Object o = new Object();
        map.put(o, 6);
        assertEquals(6, map.get(o));
    }

    @Before
    public void beforeTest() {
        map = new CacheMapImpl();
    }

}
