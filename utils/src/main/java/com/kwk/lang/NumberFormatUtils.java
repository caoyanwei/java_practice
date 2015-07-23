package com.kwk.lang;

import java.math.RoundingMode;
import java.text.NumberFormat;

public class NumberFormatUtils {
    public static String getAmountStr(long amount) {
        NumberFormat numberFormat = NumberFormat.getIntegerInstance();
        return numberFormat.format(amount);
    }

    public static String getPercentRate(double rate) {
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        numberFormat.setMinimumFractionDigits(1);

        String percentRate = numberFormat.format(rate);
        return percentRate;
    }

    /**
     * @param amount 钱数，以分为单位
     * @return 格式化后的钱数，以元为单位，例如23,32.00
     */
    public static String getMoneyAmountStr(long amount) {
        double money = (double) amount / 100.0;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        numberFormat.setMinimumFractionDigits(2);

        String moneyStr = numberFormat.format(money);
        return moneyStr;
    }

}
