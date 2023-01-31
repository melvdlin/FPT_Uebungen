package org.somevand.fpt.teaching.uebung._12;

import javafx.beans.property.StringProperty;

public interface Model {
    void setLeftOperand(String operand);
    void setRightOperand(String operand);
    void setOperation(Operation operation);
    void setResult(String result);
    String getLeftOperand();
    String getRightOperand();
    Operation getOperation();
    StringProperty getResultProperty();
}
