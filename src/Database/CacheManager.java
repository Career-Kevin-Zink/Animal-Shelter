package Database;

import Application.Animal;
import Application.Kennel;
import java.util.HashMap;

/**
 * Not entirely sure how/if I want to implement the cache system, so for now it's just a separate class
 *
 * Usually when you're working with a database, it's web based, and multiple people are more than likely
 * attempting to access the same data concurrently. So what I've done, and seen done, previously is instead
 * of having each user make a new connection to the database, run a query, then close the connection, we have
 * the application run an initial "loadEverything" sort of method which goes through every table in the database
 * and creates a copy in memory of each element. That way when users want information from the database, you can
 * search through the cache without having to go through the whole handshake of querying the database each time.
 * So the only time the program actually has to talk to the database is when saving new objects, which should make
 * every 'query' much faster for the user.
 */
public class CacheManager {

    public static Cache<Animal> Animals = new Cache<Animal>();
    public static Cache<Kennel> Kennels;

    public static class Cache<T> {
        public HashMap<Integer, T> Dictionary = new HashMap<Integer, T>();

        public void Add(T obj) {
            Dictionary.put(obj.hashCode(), obj);
        }
    }

    public static void debug() {
        for(Animal animal : Animals.Dictionary.values()) {
            System.out.println(animal.getName());
        }
    }
}