package interaction;


import model.*;

import java.io.*;

/**
 * Created by Никита on 20.11.2016.
 */
public class Serialization {

    private static String dir = ".\\saves\\";

    public static void save(CellularAutomat ca, String path) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(dir+path);

        ObjectOutputStream  out = new ObjectOutputStream(fileOut);
        out.writeObject(ca);
        out.close();
        fileOut.close();
    }

    public static CellularAutomat load(String path) throws IOException, ClassNotFoundException {
        CellularAutomat  ca = null;
        FileInputStream fileIn = new FileInputStream(dir + path);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        ca = (CellularAutomat)in.readObject();
        in.close();
        fileIn.close();
        return ca;
    }
}
