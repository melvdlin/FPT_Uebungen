package org.somevand.fpt.teaching.uebung._04.impl.serde.beans;


import java.io.Serializable;

public class GameStateBean implements Serializable {
    private WareBean[] wares;
    private MarketBean[] markets;
    private PlayerBean player;
    private int[] marketDistanceMatrix;
    private int currentMarket;
    private int winBalance;

    public GameStateBean() {

    }

    public GameStateBean(
            WareBean[] wares,
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

    public WareBean[] getWares() {
        return wares;
    }

    public void setWares(WareBean[] wares) {
        this.wares = wares;
    }

    public void setWares(int index, WareBean ware) {
        this.wares[index] = ware;
    }

    public MarketBean[] getMarkets() {
        return markets;
    }

    public void setMarkets(MarketBean[] markets) {
        this.markets = markets;
    }

    public void setMarkets(int index, MarketBean market) {
        this.markets[index] = market;
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

    public void setMarketDistanceMatrix(int index, int distance) {
        this.marketDistanceMatrix[index] = distance;
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
