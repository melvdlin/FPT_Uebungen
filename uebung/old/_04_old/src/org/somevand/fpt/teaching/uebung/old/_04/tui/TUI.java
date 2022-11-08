package org.somevand.fpt.teaching.uebung.old._04.tui;

public interface TUI {
    void setState(DisplayableGameState state);
    String getInput(String prompt);
    String getInput();
    String getInputln(String prompt);
    String getInputln();
    void print(String text);
    void println(String text);
    void println();
    void update();
}
