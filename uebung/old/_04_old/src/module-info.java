module _4 {
    requires javafx.base;
    requires javafx.controls;
    requires java.desktop;

    opens org.somevand.fpt.teaching.uebung.old._04.gui to javafx.graphics;
    opens org.somevand.fpt.teaching.uebung.old._04.impl.gui to javafx.graphics;

    exports org.somevand.fpt.teaching.uebung.old._04.impl.serde.beans;
    exports org.somevand.fpt.teaching.uebung.old._04.impl.serde.records;
}