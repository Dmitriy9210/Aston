package Lesson1;

public class MyHashMap<K, V> {


    private int SIZE = 16;
    private Entry<K, V>[] table;

    public MyHashMap() {
        table = new Entry[SIZE];
    }

    // put
    public void put(K key, V value) {
        int index = Math.abs(key.hashCode()) % SIZE;

        if (table[index] == null) {
            table[index] = new Entry<>(key, value);
            return;
        }

        Entry<K, V> current = table[index];
        while (true) {
            if (current.key.equals(key)) {
                current.value = value; // обновляем
                return;
            }
            if (current.next == null) {
                current.next = new Entry<>(key, value); // добавляем в конец
                return;
            }
            current = current.next;
        }
    }

    // get
    public V get(K key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        Entry<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    // remove
    public void remove(K key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        Entry<K, V> current = table[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }
}