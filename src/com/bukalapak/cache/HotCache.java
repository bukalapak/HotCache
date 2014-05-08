package com.bukalapak.cache;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by xinuc on 5/8/14.
 */
public class HotCache <E> {
    private final LinkedList <String> list;
    private final HashMap <String, E> map;
    private int capacity;
    private int count;

    public HotCache(int capacity) {
        this.list = new LinkedList<String>();
        this.map = new HashMap<String, E>();
        this.capacity = capacity;
        this.count = 0;
    }

    public E fetch(String key, Callable closure) {
        E value = this.read(key);
        if (value != null) {
            return value;
        }
        value = (E) closure.call();
        this.write(key, value);
        return value;
    }

    public synchronized void write(String key, E value) {

        if (count >= capacity) {
            this.truncate();
        }

        E oldValue = map.get(key);
        this.map.put(key, value);
        if(oldValue == null) {
            this.list.addFirst(key);
            this.count++;
        } else {
            this.promote(key);
        }
    }

    public E read(String key) {
        E value = map.get(key);
        if (value != null) {
            this.promote(key);
        }
        return value;
    }

    public synchronized E delete(String key) {
        E value = map.get(key);
        if (value != null) {
            this.map.remove(key);
            this.list.remove(key);
            this.count--;
        }
        return value;
    }

    private void promote(String key) {
        synchronized (this.list) {
            this.list.remove(key);
            this.list.addFirst(key);
        }
    }

    private synchronized void truncate() {
        String key = this.list.pollLast();
        if(key != null) {
            this.map.remove(key);
            this.count--;
        }
    }

    public String inspect() {
        return "list: " + this.list + "\nmap: " + this.map + "\ncount: " + this.count + "\n======================";
    }
}
