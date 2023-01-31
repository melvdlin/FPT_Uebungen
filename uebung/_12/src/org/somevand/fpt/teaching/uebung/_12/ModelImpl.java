package org.somevand.fpt.teaching.uebung._12;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelImpl implements Model {

    private String leftOperand, rightOperand;
    private Operation operation;
    private final StringProperty result = new SimpleStringProperty("");

    @Override
    public void setLeftOperand(String operand) {
        this.leftOperand = operand;
    }

    @Override
    public void setRightOperand(String operand) {
        this.rightOperand = operand;
    }

    @Override
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void setResult(String result) {
        this.result.set(result);
    }

    @Override
    public String getLeftOperand() {
        return leftOperand;
    }

    @Override
    public String getRightOperand() {
        return rightOperand;
    }

    @Override
    public Operation getOperation() {
        return operation;
    }

    @Override
    public StringProperty getResultProperty() {
        return result;
    }
}
