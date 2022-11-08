package org.somevand.fpt.teaching.uebung.old._04.impl.serde;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class BeanSerde<T extends Serializable> implements Serde<T> {

    private final Class<T> theClass;

    public BeanSerde(Class<T> theClass) {
        this.theClass = theClass;
    }

    @Override
    public void serialize(T obj, String filename) throws IOException {
        if (filename == null) throw new NullPointerException("filename must not be null");
        try (var fos = new FileOutputStream(filename);
             var enc = new XMLEncoder(fos)) {
            enc.writeObject(obj);
        }
    }

    @Override
    public T deserialize(String filename) throws IOException, ClassCastException {
        if (filename == null) throw new NullPointerException("filename must not be null");
        try (var fis = new FileInputStream(filename);
             var dec = new XMLDecoder(fis)) {
            return theClass.cast(dec.readObject());
        }
    }
}
