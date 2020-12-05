package GUI;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * The ResourceManager class provides a file system for saving and loading
 * Game objects.
 * @author CandNo: 198702
 * @version 04/12/20
 */
public class ResourceManager {

    /**
     * The save method saves Game objects to the file system.
     * @param data the Game to be saved.
     * @param fileName the desired name of the save file.
     * @throws Exception
     */
    public static void save(Game data, String fileName) throws Exception {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            os.writeObject(data);
        } catch (Exception error) {
        }
    }

    /**
     * The load method loads and returns Game objects from the file
     * system.
     * @param fileName the name of the save file to be loaded.
     * @return the Game object.
     * @throws Exception
     */
    public static Game load(String fileName) throws Exception {
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
            Game data = (Game) is.readObject();
            return data;
        } catch (Exception error) {
            return null;
        }
    }
}