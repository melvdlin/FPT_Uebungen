package org.somevand.fpt.teaching.uebung._12;

import javafx.scene.control.TextField;

public interface View {

    void setController(Controller controller);
    Controller getController();
    TextField getTextField();
}
