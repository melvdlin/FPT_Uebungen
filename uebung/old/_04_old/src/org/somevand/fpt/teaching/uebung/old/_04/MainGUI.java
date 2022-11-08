package org.somevand.fpt.teaching.uebung.old._04;

import org.somevand.fpt.teaching.uebung.old._04.impl.gui.GuiGameControllerImpl;
import org.somevand.fpt.teaching.uebung.old._04.impl.gui.GuiImpl;

public class MainGUI {
    public static void main(String[] args) {
        GuiImpl.launch(new GuiGameControllerImpl());
    }
}
