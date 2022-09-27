package org.somevand.fpt.uebung._4.impl.serde;

import java.io.*;

public class BinarySerde<T extends Serializable> implements Serde<T> {

    private final Class<T> theClass;

    public BinarySerde(Class<T> theClass) {
        this.theClass = theClass;
    }

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
        if (filename == null) throw new NullPointerException("filename must not be null");
        try (var fis = new FileInputStream(filename);
             var ois = new ObjectInputStream(fis)) {
            return theClass.cast(ois.readObject());
        }
    }
}
