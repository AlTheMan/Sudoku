package se.kth.AlgotVREmilW.labb4.model;


import java.io.*;
import java.util.List;

public class SaveAndLoadFile {

    /**
     * Call this method before the application exits, to store the users and projects,
     * in serialized form.
     */
    public static void serializeToFile(File file, int[][][] data) throws IOException {
        // ...
        ObjectOutputStream output = null;

        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            output = new ObjectOutputStream(fileOut);
            output.writeObject(data);
        }
        finally {
            if (output != null) output.close();
        }

        // and then, make sure the file always get closed
    }

    /**
     * Call this method at startup of the application, to deserialize the users and
     * from file the specified file.
     */

    public static int[][][] deSerializeFromFile(File file) throws IOException, ClassNotFoundException {
        // ...
        int[][][] gameState;
        ObjectInputStream input = null;
        try {
            FileInputStream fileIn = new FileInputStream(file);
            input = new ObjectInputStream(fileIn);
            gameState= (int[][][]) input.readObject();
            System.out.println("test");

        }
        finally {
            if (input != null) input.close();
        }
        return gameState;

        // and then, make sure the file always get closed
    }
}
