package org.somevand.fpt.teaching.uebung._12;

public interface Controller {
    void submitAdd(String summand);
    void submitSubtract(String minuend);
    void submitMultiply(String factor);
    void submitDivide(String dividend);
    void calculateResult(String rightOperand);
}
