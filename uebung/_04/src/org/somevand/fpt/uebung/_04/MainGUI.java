package org.somevand.fpt.uebung._04;

import org.somevand.fpt.uebung._04.impl.gui.GuiGameControllerImpl;
import org.somevand.fpt.uebung._04.impl.gui.GuiImpl;

public class MainGUI {
    public static void main(String[] args) {
        GuiImpl.launch(new GuiGameControllerImpl());
    }
}
