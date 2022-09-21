package org.somevand.fpt.uebung._4.gui;

import javafx.util.Callback;
import org.somevand.fpt.uebung._4.data.Ware;

public interface GUI {
    void setState(GuiDisplayableGameState state);
    void show();
    void hide();
    void update();
    void showPopup(String title, String msg);
    void showPopupSync(String title, String msg);

    void addOnMarketClickedListener(Callback<? extends GuiDisplayableMarket, Void> listener);
    void removeOnMarketClickedListener(Callback<? extends GuiDisplayableMarket, Void> listener);
    void addOnMarketWareClickedListener(Callback<Ware, Void> listener);
    void removeOnMarketWareClickedListener(Callback<Ware, Void> listener);
    void addOnPlayerWareClickedListener(Callback<Ware, Void> listener);
    void removeOnPlayerWareClickedListener(Callback<Ware, Void> listener);
    void addOnSaveButtonClickedListener(Callback<String, Void> listener);
    void removeOnSaveButtonClickedListener(Callback<String, Void> listener);
    void addOnLoadButtonClickedListener(Callback<String, Void> listener);
    void removeOnLoadButtonClickedListener(Callback<String, Void> listener);
    void addOnExitButtonClickedListener(Callback<Void, Void> listener);
    void removeOnExitButtonClickedListener(Callback<Void, Void> listener);
}
