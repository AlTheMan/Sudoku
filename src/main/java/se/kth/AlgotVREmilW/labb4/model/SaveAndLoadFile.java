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

        }
        finally {
            if (input != null) input.close();
        }
        return gameState;


        // and then, make sure the file always get closed
    }

/*
    public void openAndReadFile() {

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            String path = file.getPath();
            fileInfoLabel.setText(path);

            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader(path));
                String line = in.readLine();
                while (line != null) {
                    textArea.appendText(line + "\n");
                    line = in.readLine();
                }
            } catch (IOException ie) {
                textArea.appendText("Unable to read file.");
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                }
            }
        }
    }
*/
}
