package Model;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import java.io.File;

public class Game {
    private Instance instance;
    public Map images;
    public String state;
    public boolean canMove;
    public Model model;


    public Game(Model model) {
        this.model = model;
        images = new HashMap<String, Image>();
        canMove = true;
        state = "menu";
    }

    public Instance getInstance() {
        return instance;
    }

    public void startNewGame() {
        instance = new Instance();
        instance.setCurrentWorld("a");
        instance.setPlayer(new Player("Player", 1, 1));
        model.populateMap();

    }

    public boolean loadSavedGame() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(fileChooser);
        if(result == JFileChooser.APPROVE_OPTION) {
            File selected = fileChooser.getSelectedFile();
            FileInputStream fin = null;
            ObjectInputStream ois = null;
            try {
                fin = new FileInputStream(selected);
                ois = new ObjectInputStream(fin);
                instance = (Instance) ois.readObject();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if(ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            state = "load";
    		model.update();
    		state = "game";
    		model.update();
            return true;
        }
        return false;
    }

    public boolean saveCurrentGame() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showSaveDialog(fileChooser);
        if(result == JFileChooser.APPROVE_OPTION) {
            File target = fileChooser.getSelectedFile();
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try {
                fos = new FileOutputStream(target);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(instance);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if(oos != null) {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        }
        return false;
    }

}
