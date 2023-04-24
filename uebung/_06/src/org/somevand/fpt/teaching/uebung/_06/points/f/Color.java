package org.somevand.fpt.teaching.uebung._06.points.f;

class Color implements Cloneable {

    private byte red, green, blue;

    public Color(byte red, byte green, byte blue) {
        this.red   = red;
        this.green = green;
        this.blue  = blue;
    }

    public Color(Color other) {
        this.red   = other.red;
        this.green = other.green;
        this.blue  = other.blue;
    }

    public byte getRed() {
        return red;
    }

    public void setRed(byte red) {
        this.red = red;
    }

    public byte getGreen() {
        return green;
    }

    public void setGreen(byte green) {
        this.green = green;
    }

    public byte getBlue() {
        return blue;
    }

    public void setBlue(byte blue) {
        this.blue = blue;
    }

    @Override
    public boolean equals(Object o) {
        return this
               == o
               || o instanceof Color other
                  && red == other.red
                  && green == other.green
                  && blue == other.blue;
    }

    @Override
    public String toString() {
        return "Color{" +
               "red=" + red +
               ", green=" + green +
               ", blue=" + blue +
               '}';
    }

    @Override
    public Color clone() {
        try {
            Color clone = (Color) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
