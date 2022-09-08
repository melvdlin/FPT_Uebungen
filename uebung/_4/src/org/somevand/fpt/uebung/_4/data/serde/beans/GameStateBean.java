package org.somevand.fpt.uebung._4.data.serde.beans;

import org.somevand.fpt.uebung._4.data.Ware;

import java.io.Serializable;

public class GameStateBean implements Serializable {
    private Ware[] wares;
    private MarketBean[] markets;
    private PlayerBean player;
    private int[] marketDistanceMatrix;
    private int currentMarket;
    private int winBalance;

    public GameStateBean() {

    }

    public GameStateBean(
            Ware[] wares,
            MarketBean[] markets,
            PlayerBean player,
            int[] marketDistanceMatrix,
            int currentMarket,
            int winBalance) {
        this.wares = wares;
        this.markets = markets;
        this.player = player;
        this.marketDistanceMatrix = marketDistanceMatrix;
        this.currentMarket = currentMarket;
        this.winBalance = winBalance;
    }

    public Ware[] getWares() {
        return wares;
    }

    public void setWares(Ware[] wares) {
        this.wares = wares;
    }

    public MarketBean[] getMarkets() {
        return markets;
    }

    public void setMarkets(MarketBean[] markets) {
        this.markets = markets;
    }

    public PlayerBean getPlayer() {
        return player;
    }

    public void setPlayer(PlayerBean player) {
        this.player = player;
    }

    public int[] getMarketDistanceMatrix() {
        return marketDistanceMatrix;
    }

    public void setMarketDistanceMatrix(int[] marketDistanceMatrix) {
        this.marketDistanceMatrix = marketDistanceMatrix;
    }

    public int getCurrentMarket() {
        return currentMarket;
    }

    public void setCurrentMarket(int currentMarket) {
        this.currentMarket = currentMarket;
    }

    public int getWinBalance() {
        return winBalance;
    }

    public void setWinBalance(int winBalance) {
        this.winBalance = winBalance;
    }
}
