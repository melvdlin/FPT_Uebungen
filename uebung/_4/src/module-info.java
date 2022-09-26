module _4 {
    requires javafx.base;
    requires javafx.controls;
    requires java.desktop;

    opens org.somevand.fpt.uebung._4.gui to javafx.graphics;
}