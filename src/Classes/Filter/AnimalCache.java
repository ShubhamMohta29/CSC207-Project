/**
 * AnimalCache: for avoiding repeated api calls, if needed?
 /)/)
 ( . .)
 ( づ♡
 */
package Classes.Filter;

import Classes.Animal;
import java.util.HashMap;
import java.util.Map;

public class AnimalCache {
    private final Map<String, Animal> cache = new HashMap<>();

    public Animal get(String name) {
        return cache.get(name.toLowerCase());
    }

    public void put(String name, Animal animal) {
        cache.put(name.toLowerCase(), animal);
    }

    public void clear() {
        cache.clear();
    }

    public int size() {
        return cache.size();
    }
}
