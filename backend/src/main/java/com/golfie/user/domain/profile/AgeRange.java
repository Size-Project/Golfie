package com.golfie.user.domain.profile;

import java.util.Arrays;

public enum AgeRange {
    TEN("10-19"),
    TWENTY("20-29"),
    THIRTY("30-39"),
    FORTY("40-49"),
    FIFTY("50-59"),
    SIXTY("60-69"),
    SEVENTY("70-79"),
    EIGHTY("80-89"),
    NINETY("90-99");

    private final String symbol;

    AgeRange(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static AgeRange getValueBySymbol(String symbol) {
        return Arrays.stream(values())
                .filter(val -> val.getSymbol().equals(symbol))
                .findFirst()
                .get();
    }
}
