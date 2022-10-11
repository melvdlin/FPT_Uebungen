package org.somevand.fpt.uebung._04.gui;

import org.somevand.fpt.uebung._04.data.Ware;

import java.util.function.Consumer;

public interface GUI {
    void setState(GuiDisplayableGameState state);
    void show();
    void hide();
    void exit();
    void showPopup(String title, String msg, Runnable onConfirmedListener);
    void showPopupAndWait(String title, String msg);

    void addOnMarketClickedListener(Consumer<GuiDisplayableMarket> listener);
    void removeOnMarketClickedListener(Consumer<GuiDisplayableMarket> listener);
    void addOnMarketWareClickedListener(Consumer<Ware> listener);
    void removeOnMarketWareClickedListener(Consumer<Ware> listener);
    void addOnPlayerWareClickedListener(Consumer<Ware> listener);
    void removeOnPlayerWareClickedListener(Consumer<Ware> listener);
    void addOnSaveButtonClickedListener(Consumer<String> listener);
    void removeOnSaveButtonClickedListener(Consumer<String> listener);
    void addOnLoadButtonClickedListener(Consumer<String> listener);
    void removeOnLoadButtonClickedListener(Consumer<String> listener);
    void addOnExitButtonClickedListener(Runnable listener);
    void removeOnExitButtonClickedListener(Runnable listener);
}
