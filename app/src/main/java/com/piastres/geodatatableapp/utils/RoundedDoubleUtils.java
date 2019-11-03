package com.piastres.geodatatableapp.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundedDoubleUtils {
    public static double getRoundedDouble(double value) {
        BigDecimal convertedToBigDecimal = new BigDecimal(Double.toString(value));
        convertedToBigDecimal = convertedToBigDecimal.setScale(4,
                RoundingMode.HALF_UP);

        return convertedToBigDecimal.doubleValue();
    }
}
