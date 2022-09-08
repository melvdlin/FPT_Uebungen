package org.somevand.fpt.uebung._4.tui;

import org.somevand.fpt.uebung._4.data.Ware;
import org.somevand.fpt.uebung._4.exceptions.UnknownMarketException;
import org.somevand.fpt.uebung._4.exceptions.UnknownWareException;

import java.util.Scanner;

public class TuiImpl implements TUI {

    private static final class StringHelper {
        public static final int genericStringColWidth = 30;
        public static final int genericIntColWidth = 20;
        public static final int nameColWidth = genericStringColWidth;
        public static final int priceColWidth = genericIntColWidth;
        public static final int sizeColWidth = genericIntColWidth;
        public static final int countColWidth = genericIntColWidth;
        public static final int balanceWidth = genericIntColWidth;
        public static final int fuelWidth = genericIntColWidth;
        public static final int destinationColWidth = genericStringColWidth;
        public static final int distanceColWidth = genericIntColWidth;

        public static final int totalInnerWidth = nameColWidth + 1 + priceColWidth + 1 + sizeColWidth + 1 + countColWidth;
        public static final int totalOuterWidth = totalInnerWidth + 4;

        public static final int totalMiscPlayerInfoWidth = genericStringColWidth + 1 + genericIntColWidth;

        public static final char horizontalSeparator = '-';
        public static final char verticalSeparator = '|';
        public static final char cornerChar = '+';

        public static final String tagTitleMarketInventory = "Stock";
        public static final String tagTitlePlayerInventory = "Your cargo hold";
        public static final String tagTitleTravelChart = "Travel info";
        public static final String tagName = "[name]";
        public static final String tagPrice = "[price/unit]";
        public static final String tagSize = "[capacity/unit]";
        public static final String tagCount = "[available]";
        public static final String tagCapacity = "Cargo Capacity";
        public static final String tagBalance = "Balance";
        public static final String tagCurrency = "c";
        public static final String tagFuel = "Available Fuel";
        public static final String tagDestination = "[destination]";
        public static final String tagDistance = "[distance]";

        public static final String fMarketInventoryTitleRow = "%s " + tagTitleMarketInventory;
        public static final String fPlayerInventoryTitleRow = tagTitlePlayerInventory;
        public static final String fTravelInfoTitleRow = tagTitleTravelChart;

        public static final String fInventoryTopRow =
                "%-" + nameColWidth + "." + nameColWidth + "s" + " " +
                "%" + priceColWidth + "." + priceColWidth + "s" + " " +
                "%" + sizeColWidth + "." + sizeColWidth + "s" + " " +
                "%" + countColWidth + "." + countColWidth + "s";

        public static final String fInventoryRow =
                "%-" + nameColWidth + "." + nameColWidth + "s" + " " +
                "%" + priceColWidth + "d" + " " +
                "%" + sizeColWidth + "d" + " " +
                "%" + countColWidth + "d";

        public static final String fCapacityRowL =
                tagCapacity + ":";
        public static final String getfCapacityRowR =
                 "%d/%d";

        public static final String fBalanceRowL =
                tagBalance + ":";
        public static final String fBalanceRowR =
                "%" + balanceWidth + "d" + tagCurrency;

        public static final String fFuelRowL =
                tagFuel + ":";
        public static final String fFuelRowR =
                "%" + fuelWidth + "d";

        public static final String fTravelInfoTopRow =
                "%-" + destinationColWidth + "." + destinationColWidth + "s" + " " +
                "%" + distanceColWidth + "." + destinationColWidth + "s";

        public static final String fTravelInfoRow =
                "%-" + destinationColWidth + "." + destinationColWidth + "s" + " " +
                "%" + distanceColWidth + "d";

        public static final String horizontalSeparatorString =
                cornerChar + String.valueOf(horizontalSeparator).repeat(totalOuterWidth - 2) + cornerChar;

        public static final String fFrame =
                verticalSeparator + " %-" + totalInnerWidth + "." + totalInnerWidth + "s " + verticalSeparator;

        public static String frame(String toFrame) {
            return fFrame.formatted(toFrame);
        }

        public static String center(String toCenter, int width) {
            return center(toCenter, width, ' ');
        }

        public static String center(String toCenter, int width, char padding) {
            if (width < toCenter.length()) return toCenter.substring(0, width);
            int diff = width - toCenter.length();
            return
                    String.valueOf(padding).repeat(diff / 2) +
                    toCenter +
                    String.valueOf(padding).repeat(diff / 2 + diff % 2);
        }

        public static String alignLeftRight(String left, String right, int width) {
            return alignLeftRight(left, right, width, ' ');
        }

        public static String alignLeftRight(String left, String right, int width, char padding) {
            int diff = width - left.length() - right.length();
            String retVal = left + (diff < 0 ? "" : String.valueOf(padding).repeat(diff)) + right;
            return retVal.substring(0, width);
        }
    }

    private DisplayableGameState state;
    private final Scanner s = new Scanner(System.in);

    @Override
    public void setState(DisplayableGameState state) {
        this.state = state;
    }

    @Override
    public String getInput(String prompt) {
        System.out.print(prompt);
        return getInput();
    }

    @Override
    public String getInput() {
        return s.nextLine();
    }

    @Override
    public String getInputln(String prompt) {
        System.out.println(prompt);
        return getInput();
    }

    @Override
    public String getInputln() {
        return getInputln("");
    }

    @Override
    public void print(String text) {
        System.out.print(text);
    }

    @Override
    public void println(String text) {
        System.out.println(text);
    }

    @Override
    public void println() {
        System.out.println();
    }

    @Override
    public void update() {
        StringBuilder builder = new StringBuilder();
        try {
            appendMarketTitleString(builder);
            appendInventoryString(builder, state.getCurrentMarket()::getLocalPrice, state.getCurrentMarket()::getCount);
            appendPlayerTitleString(builder);
            appendInventoryString(builder, state.getCurrentMarket()::getLocalPrice, state.getPlayer()::getCount);
            appendMiscPlayerInfoString(builder);
            appendTravelInfoTitleString(builder);
            appendTravelInfoChartString(builder);
            appendHorizontalSeparatorString(builder);
        } catch (UnknownWareException | UnknownMarketException e) {
            throw new RuntimeException(e);
        } finally {
            print(builder.toString());
        }
    }

    private void appendMarketTitleString(StringBuilder builder) {
        builder.append(StringHelper.horizontalSeparatorString).append('\n'
        ).append(
                StringHelper.frame(
                        StringHelper.center(
                                StringHelper.fMarketInventoryTitleRow.formatted(
                                        state.getCurrentMarket().getName()
                                ), StringHelper.totalInnerWidth
                        )
                )
        ).append('\n');
    }

    private void appendPlayerTitleString(StringBuilder builder) {

        builder.append(StringHelper.horizontalSeparatorString).append('\n'
        ).append(
                StringHelper.frame(
                        StringHelper.center(
                                StringHelper.fPlayerInventoryTitleRow.formatted(
                                ), StringHelper.totalInnerWidth
                        )
                )
        ).append('\n');
    }

    private void appendTravelInfoTitleString(StringBuilder builder) {
        builder.append(StringHelper.horizontalSeparatorString).append('\n'
        ).append(
                StringHelper.frame(
                        StringHelper.center(
                                StringHelper.fTravelInfoTitleRow.formatted(
                                ), StringHelper.totalInnerWidth
                        )
                )
        ).append('\n');
    }

    private void appendInventoryString(StringBuilder builder, PriceAccessor pa, CountAccessor ca)
            throws UnknownWareException {
        builder.append(StringHelper.horizontalSeparatorString).append('\n'
        ).append(
                StringHelper.frame(
                        StringHelper.fInventoryTopRow.formatted(
                                StringHelper.tagName,
                                StringHelper.tagPrice,
                                StringHelper.tagSize,
                                StringHelper.tagCount
                        )
                )
        ).append('\n'
        ).append(StringHelper.frame("")).append('\n');
        for (var ware : state.getWares()) {
            builder.append(
                    StringHelper.frame(
                            StringHelper.fInventoryRow.formatted(
                                    ware.name(),
                                    pa.getPrice(ware),
                                    ware.size(),
                                    ca.getCount(ware)
                            )
                    )
            ).append('\n');
        }
    }

    private void appendMiscPlayerInfoString(StringBuilder builder) {
        builder.append(StringHelper.horizontalSeparatorString).append('\n'
        ).append(
                StringHelper.frame(
                        StringHelper.alignLeftRight(
                                StringHelper.fBalanceRowL.formatted(),
                                StringHelper.fBalanceRowR.formatted(
                                        state.getPlayer().getBalance()
                                ), StringHelper.totalMiscPlayerInfoWidth + StringHelper.tagCurrency.length()
                        )
                )
        ).append('\n'
        ).append(
                StringHelper.frame(
                        StringHelper.alignLeftRight(
                                StringHelper.fCapacityRowL.formatted(),
                                StringHelper.getfCapacityRowR.formatted(
                                        state.getPlayer().getRemainingCapacity(),
                                        state.getPlayer().getMaxCapacity()
                                ), StringHelper.totalMiscPlayerInfoWidth
                        )

                )
        ).append('\n'
        ).append(
                StringHelper.frame(
                        StringHelper.alignLeftRight(
                                StringHelper.fFuelRowL.formatted(),
                                StringHelper.fFuelRowR.formatted(
                                        state.getPlayer().getFuelReach()
                                ), StringHelper.totalMiscPlayerInfoWidth
                        )
                )
        ).append('\n');
    }

    private void appendTravelInfoChartString(StringBuilder builder)
            throws UnknownMarketException {
        builder.append(StringHelper.horizontalSeparatorString).append('\n').append(
                StringHelper.frame(
                        StringHelper.fTravelInfoTopRow.formatted(
                                StringHelper.tagDestination,
                                StringHelper.tagDistance
                        )
                )
        ).append('\n'
        ).append(StringHelper.frame("")).append('\n'
        );
        for (var market : state.getMarkets()) {
            if (market == state.getCurrentMarket()) continue;
            builder.append(
                    StringHelper.frame(
                            StringHelper.fTravelInfoRow.formatted(
                                    market.getName(),
                                    state.getDistance(state.getCurrentMarket(), market)
                            )
                    )
            ).append('\n');
        }
    }

    private void appendHorizontalSeparatorString(StringBuilder builder) {
        builder.append(StringHelper.horizontalSeparatorString).append('\n');
    }

    @FunctionalInterface
    private interface PriceAccessor {
        int getPrice(Ware ware) throws UnknownWareException;
    }

    @FunctionalInterface
    private interface CountAccessor {
        int getCount(Ware ware) throws UnknownWareException;
    }
}
