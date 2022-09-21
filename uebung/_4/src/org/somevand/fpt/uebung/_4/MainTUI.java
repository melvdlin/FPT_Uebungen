package org.somevand.fpt.uebung._4;

import org.somevand.fpt.uebung._4.data.Ware;
import org.somevand.fpt.uebung._4.data.basic.GameState;
import org.somevand.fpt.uebung._4.data.basic.Market;
import org.somevand.fpt.uebung._4.data.basic.Player;
import org.somevand.fpt.uebung._4.data.serde.BinarySerde;

import java.util.List;
import java.util.Map;

public class MainTUI {

    public static void main(String[] args) {
        Ware petrol = new Ware("Petrol", 5, 1, true);
        Ware tobacco = new Ware("Tobacco", 50, 1, false);
        Ware cheese = new Ware("Cheese", 15, 2, false);
        Ware lumber = new Ware("Lumber", 70, 4, false);
        var wares = List.of(petrol, tobacco, cheese, lumber);

        Market dinslaken = new Market("Dinslaken", Map.of(
                petrol, 3,
                tobacco, 2,
                cheese, 1,
                lumber, 1
        ), 30);
        Market oberhausen = new Market("Oberhausen", Map.of(
                petrol, 1,
                tobacco, 2,
                cheese, 3,
                lumber, 4
        ), 35);
        Market duisburg = new Market("Duisburg", Map.of(
                petrol, 2,
                tobacco, 1,
                cheese, 2,
                lumber, 2
        ), 50);
        Market essen = new Market("Essen", Map.of(
                petrol, 3,
                tobacco, 4,
                cheese, 1,
                lumber, 1
        ), 50);

        var mdm = Map.of(
                dinslaken,  Map.of(dinslaken, 0, oberhausen, 1, duisburg, 3, essen, 4),
                oberhausen, Map.of(dinslaken, 1, oberhausen, 0, duisburg, 2, essen, 2),
                duisburg,   Map.of(dinslaken, 3, oberhausen, 2, duisburg, 0, essen, 1),
                essen,      Map.of(dinslaken, 4, oberhausen, 2, duisburg, 1, essen, 0)
        );

        Player player = new Player(30, 200, wares);

        GameState gameState = new GameState(wares, mdm, player, dinslaken, 1000);
        GameController controller = new GameController(new BinarySerde<>(), gameState);
        controller.run();
    }
}
