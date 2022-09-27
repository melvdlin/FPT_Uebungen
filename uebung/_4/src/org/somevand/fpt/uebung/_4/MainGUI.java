package org.somevand.fpt.uebung._4;

import org.somevand.fpt.uebung._4.impl.gui.GuiGameControllerImpl;
import org.somevand.fpt.uebung._4.impl.gui.GuiImpl;

public class MainGUI {
    public static void main(String[] args) {
        GuiImpl.launch(new GuiGameControllerImpl());
    }
}
