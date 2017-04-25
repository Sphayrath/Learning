package Collections;


import net.mindview.util.Countries;

import java.util.*;

/**
 * Created by Vlad on 06.05.2016.
 */
public class SlowMap<K,V> extends AbstractMap<K,V> {
    private List<K> keys = new ArrayList<>();
    private List<V> values = new ArrayList<>();

    public V put (K key, V value) {
        V oldValue = get(key);
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else
            values.set(keys.indexOf(key), value);
        return oldValue;
    }

    public V get (Object key) {
        if (!keys.contains(key)) return null;
        return values.get(keys.indexOf(key));
    }

    public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> set = new HashSet<>();
        Iterator<K> ki = keys.iterator();
        Iterator<V> vi = values.iterator();
        while (ki.hasNext())
            set.add(new MapEntry<>(ki.next(), vi.next()));
        return set;
    }

    @Override
    public Set<K> keySet() {
        return new KeySet();
    }

    private final class KeySet extends AbstractSet<K> {
        public Iterator<K> iterator() {
            return keys.iterator();
        }
        public int size() {
            return keys.size();
        }
        public boolean contains(Object o) {
            return containsKey(o);
        }
        public boolean remove(Object o) {
            return SlowMap.this.remove(o) != null;
        }
        public void clear() {
            SlowMap.this.clear();
        }
    }

    @Override
    public V remove(Object key) {
        V oldValue = get(key);
        if (keys.contains(key)) {
            values.remove(keys.indexOf(key));
            keys.remove(key);
        }
        return oldValue;
    }

    @Override
    public void clear() {
        keys.clear();
        values.clear();
    }

    public static void main (String[] args) {
        SlowMap<String,String> m = new SlowMap<String, String>();
        m.putAll(Countries.capitals(10));
        System.out.println(m);
        System.out.println(m.get("CHAD"));
        System.out.println(m.entrySet());
    }
}
