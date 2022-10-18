package se.kth.AlgotVREmilW.labb4.model;


import java.io.*;
import java.util.List;

class SaveAndLoadFile {

    /**
     * Call this method before the application exits, to store the users and projects,
     * in serialized form.
     */

    static void serializeToFile(File file, SaveState state) throws IOException {
        // ...
        ObjectOutputStream output = null;

        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            output = new ObjectOutputStream(fileOut);
            output.writeObject(state);
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

    static SaveState deSerializeFromFile(File file) throws IOException, ClassNotFoundException {
        // ...
        SaveState state = new SaveState();
        ObjectInputStream input = null;
        try {
            FileInputStream fileIn = new FileInputStream(file);
            input = new ObjectInputStream(fileIn);
            state = (SaveState) input.readObject();
            System.out.println("test");

        }
        finally {
            if (input != null) input.close();
        }
        return state;

        // and then, make sure the file always get closed
    }



}
