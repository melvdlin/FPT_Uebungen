module _4 {
    requires javafx.base;
    requires javafx.controls;
    requires java.desktop;

    opens org.somevand.fpt.uebung._4.gui to javafx.graphics;
    opens org.somevand.fpt.uebung._4.impl.gui to javafx.graphics;

    exports org.somevand.fpt.uebung._4.impl.serde.beans;
    exports org.somevand.fpt.uebung._4.impl.serde.records;
}