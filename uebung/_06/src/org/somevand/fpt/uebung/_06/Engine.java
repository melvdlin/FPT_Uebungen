package org.somevand.fpt.uebung._06;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Engine implements Serializable, Cloneable {
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

    public Engine(Engine other) {
        this.size = other.size;
        this.pistonList = new ArrayList<>(other.pistonList);
        for (int i = 0; i < this.pistonList.size(); ++i) {
            this.pistonList.set(i, new Piston(this.pistonList.get(i)));
        }
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

    @Override
    protected Engine clone() throws CloneNotSupportedException {
        var clone = (Engine) super.clone();
        clone.pistonList = (ArrayList<Piston>) clone.pistonList.clone();
        for (int i = 0; i < clone.pistonList.size(); ++i) {
            clone.pistonList.set(i, clone.pistonList.get(i).clone());
        }
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return Double.compare(engine.size, size) == 0 && Objects.equals(pistonList, engine.pistonList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, pistonList);
    }
}
