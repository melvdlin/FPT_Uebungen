package org.somevand.fpt.uebung._4.data.serde;

import java.io.*;

public class BinarySerde<T extends Serializable> implements Serde<T> {

    @Override
    public void serialize(T obj, String filename) throws IOException {
        if (filename == null) throw new NullPointerException("filename must not be null");
        try (var fos = new FileOutputStream(filename);
             var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
        }
    }

    @Override
    public T deserialize(String filename) throws IOException, ClassNotFoundException, ClassCastException {
        try (var fis = new FileInputStream(filename);
             var ois = new ObjectInputStream(fis)) {
            return (T) ois.readObject();
        }
    }
}
