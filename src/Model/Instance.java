package Model;

import java.io.Serializable;
import java.util.*;

/**
 * Created by ANUBIS on 30/09/2017.
 *
 * The Instance class contains and handles the entities and world states for the game. It is formatted to be lightweight
 * and serializable for the save and load system. The fields are designed to be externally unmodifiable for security.
 * <p>
 * Ideally all of the necessary collections should be put into the entities field through the main file
 * before adding any entities to the game state.
 *
 * @author Matthew Schou
 */

public class Instance implements Serializable {

    private Map<String, Collection> entities;
    private Map<String, World> worldList;
    private Player player;
    private String currentWorld;
    

    public Instance() {
        entities = new HashMap<>();
        worldList = new HashMap<>();
    }

    /**
     * Places a variable type collection into a map using a String key. This allows for different types of objects to be
     * stored in multiple ways.
     *
     * @param key String name for the collection.
     * @param collectionType Collection of any type, such as ArrayList.
     */

    public void putEntityList(String key, Collection collectionType){
        entities.put(key, collectionType);
    }

    /**
     * Places a World object into a map using a String key.
     *
     * @param key String name for the World.
     * @param world World object to be put into the Map.
     */

    public void putWorldList(String key, World world){
        worldList.put(key, world);
    }

    /**
     * Puts a Player object into the player field.
     *
     * @param newPlayer Player object to be set.
     */

    public void setPlayer(Player newPlayer) {
        player = newPlayer;
    }

    /**
     * Takes a String and sets the currentWorld field for reference to the
     * currently active world.
     *
     * @param key Key of the world to reference in the world list.
     */

    public void setCurrentWorld(String key) {
        currentWorld = key;
    }

    /**
     * Retrieves the map of entity collections.
     *
     * @return Map of entity collections.
     */

    public Map getEntities(){
        return entities;
    }

    /**
     * Retrieves the map of game worlds.
     *
     * @return Map of game worlds.
     */

    public Map getWorldCells(){
        return worldList;
    }

    /**
     * Retrieves the current Player object
     *
     * @return Active Player object.
     */

    public Player getPlayer() {
        return player;
    }

    /**
     * Retrieves the String of the current world key.
     *
     * @return Active world key String.
     */

    public String getCurrentWorldName() {
        return currentWorld;
    }

    /**
     * Retrieves the current world from the world list.
     *
     * @return Current world from worldList.
     */

    public World getCurrentWorld() {
        return worldList.get(currentWorld);
    }

}
