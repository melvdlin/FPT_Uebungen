package org.somevand.fpt.teaching.uebung._12;

public class ControllerImpl implements Controller {

    private final Model model;
    private final View view;

    public ControllerImpl(Model model, View view) {
        this.model = model;
        this.view  = view;
        model.getResultProperty().addListener((observable, oldValue, newValue) -> view.getTextField().setText(newValue));
    }

    @Override
    public void submitAdd(String summand) {
        model.setLeftOperand(summand);
        model.setOperation(Operation.ADD);
    }

    @Override
    public void submitSubtract(String minuend) {
        model.setLeftOperand(minuend);
        model.setOperation(Operation.SUBTRACT);
    }

    @Override
    public void submitMultiply(String factor) {
        model.setLeftOperand(factor);
        model.setOperation(Operation.MULTIPLY);
    }

    @Override
    public void submitDivide(String dividend) {
        model.setLeftOperand(dividend);
        model.setOperation(Operation.DIVIDE);
    }

    @Override
    public void calculateResult(String rightOperand) {
        model.setRightOperand(rightOperand);
        String result = "Invalid operand!";
        try {
            int leftOperandValue = Integer.parseInt(model.getLeftOperand());
            int rightOperandValue = Integer.parseInt(model.getRightOperand());
            int resultValue = switch (model.getOperation()) {
                case ADD -> leftOperandValue + rightOperandValue;
                case SUBTRACT -> leftOperandValue - rightOperandValue;
                case MULTIPLY -> leftOperandValue * rightOperandValue;
                case DIVIDE -> leftOperandValue / rightOperandValue;
            };
            result = String.valueOf(resultValue);
        } catch (NumberFormatException | ArithmeticException e) {

        }

        model.setResult(result);
    }
}
