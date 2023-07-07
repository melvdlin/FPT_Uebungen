package org.somevand.fpt.teaching.libraryproject;

public record ISBN(int EAN, int group, int publisher, int title) {
    public ISBN {
        if (EAN < 0) {
            EAN = 978;
        }
        if (EAN != 978 && EAN != 979) {
            throw new IllegalArgumentException("EAN must be either 978 or 979");
        }
        if (group < 0 || publisher < 0 || title < 0) {
            throw new IllegalArgumentException("ISBN components must not be negative");
        }
        if (countDigits(group) > 5) {
            throw new IllegalArgumentException("group may have at most 5 digits");
        }
        if (countDigits(publisher) > 5) {
            throw new IllegalArgumentException("group may have at most 5 digits");
        }
        if (countDigits(EAN) + countDigits(group) + countDigits(publisher) +
                countDigits(title) != 12) {
            throw new IllegalArgumentException("ISBN digits before check digit must add up to 13");
        }
    }

    private static int countDigits(int n) {
        return (int) (Math.log10(Math.abs(n))) + 1;
    }

    public int checkDigit() {
        var digits = String
                .format("%d%d%d%d", EAN, group, publisher, title)
                .chars()
                .map(chr -> Integer.parseInt(String.valueOf((char) chr)))
                .toArray();

        int weight = 3;
        int weightedDigitSum = 0;
        for (int digit : digits) {
            weightedDigitSum += digit * weight;
            weight = 4 - weight;
        }
        return weightedDigitSum % 10;
    }

    @Override
    public String toString() {
        return String.format("%d-%d-%d-%d-%d", EAN, group, publisher, title, checkDigit());
    }
}
