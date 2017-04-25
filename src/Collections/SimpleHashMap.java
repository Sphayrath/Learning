package Collections;

import net.mindview.util.Countries;

import java.util.*;

/**
 * Created by Vlad on 06.05.2016.
 */

public class SimpleHashMap<K,V> extends AbstractMap<K,V> {
    static final int SIZE = 997;
    int actualSize=0;
    LinkedList<MapEntry<K,V>>[] buckets = new LinkedList[SIZE];

    public V put (K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null)
            buckets[index] = new LinkedList<>();
        LinkedList<MapEntry<K,V>> bucket = buckets[index];
        MapEntry<K,V> pair = new MapEntry<>(key, value);
        boolean found = false;
        ListIterator<MapEntry<K,V>> it = bucket.listIterator();

        while (it.hasNext()) {
            MapEntry<K,V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair);
                found=true;
                break;
            }
        }

        if (!found) {
            buckets[index].add(pair);
            actualSize++;
        }
        return oldValue;
    }

    public V get (Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) return null;
        for (MapEntry<K,V> iPair : buckets[index])
            if (iPair.getKey().equals(key))
                return iPair.getValue();
        return null;
    }

    public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> set = new HashSet<>();

        for (LinkedList<MapEntry<K,V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry<K,V> mpair : bucket)
                set.add(mpair);
        }
        return set;
    }

    @Override
    public void clear() {
        for (int i=0; i<SIZE; i++)
            buckets[i] = null;
        actualSize = 0;
    }

    @Override
    public V remove(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        V value = null;

        if (buckets[index] == null) return null;
        ListIterator<MapEntry<K,V>> it = buckets[index].listIterator();
        while (it.hasNext())
            if (it.next().getKey().equals(key)) {
                it.previous();
                value = it.next().getValue();
                it.previous();
                it.remove();
                actualSize--;
                break;
            }
        return value;
    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public boolean isEmpty() {
        return actualSize == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();

        for (LinkedList<MapEntry<K,V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry<K,V> mpair : bucket)
                set.add(mpair.getKey());
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();

        for (LinkedList<MapEntry<K,V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry<K,V> mpair : bucket)
                values.add(mpair.getValue());
        }
        return values;
    }

    public static void main (String[] args) {
        SimpleHashMap<String,String> m = new SimpleHashMap<String, String>();
        m.putAll(Countries.capitals(10));
        System.out.println(m);
        System.out.println(m.get("CAMEROON"));
        System.out.println(m.containsKey("CHAD"));
        m.remove("BOTSWANA");
        System.out.println(m);
        System.out.println(m.size());
        System.out.println(m.isEmpty());
        System.out.println(m.keySet());
        System.out.println(m.values());
        System.out.println(m.containsValue("Luanda"));
        m.clear();
        System.out.println(m);
        System.out.println(m.isEmpty());
    }
}
