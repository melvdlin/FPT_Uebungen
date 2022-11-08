package org.somevand.fpt.teaching.uebung.old._05;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Engine implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private double size;
    private ArrayList<Piston> pistonList;

    private Engine() {
        pistonList = new ArrayList<>();
    }

    public Engine(double size, Collection<Piston> pistons) {
        this();
        this.size = size;
        this.pistonList.addAll(pistons);
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public List<Piston> getPistonList() {
        return pistonList;
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeDouble(size);
        oos.writeObject(pistonList);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.size = ois.readDouble();
        this.pistonList = (ArrayList<Piston>) ois.readObject();
    }
}
