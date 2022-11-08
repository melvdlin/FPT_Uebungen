package org.somevand.fpt.teaching.uebung.old._04.impl.serde;

import java.io.IOException;

public interface Serde<T> {
    void serialize(T obj, String filename) throws IOException;
    T deserialize(String filename) throws IOException, ClassNotFoundException, ClassCastException;
}
