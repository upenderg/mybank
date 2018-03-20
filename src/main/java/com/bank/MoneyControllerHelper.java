package com.bank;

import com.google.common.collect.ImmutableMap;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.MessageFormat;
import java.util.*;

/***
 * Helper class to provide some utility methods
 */
class MoneyControllerHelper {

    private static final ImmutableMap<String, Integer> translations = ImmutableMap.<String, Integer>builder()
            .put("THOUSAND", 1000)
            .put("FIVE_HUNDRED", 500)
            .put("TWO_HUNDRED", 200)
            .put("HUNDRED", 100)
            .put("FIFTY", 50)
            .put("TWENTY", 20)
            .put("TEN", 10)
            .put("FIVE", 5)
            .put("TWO", 2)
            .put("ONE", 1)
            .build();


    /***
     * Provides denomination of the amount to be withdrawn
     * @param amountToWithDrawn - Amount to be withdraw
     * @return - Returns a map of denomination and count of them
     */
    static NavigableMap<Denomination, Integer> getDenomination(long amountToWithDrawn) {
        BigDecimal bigDecimal = BigDecimal.valueOf(amountToWithDrawn);
        final NavigableMap<Denomination, Integer> denominationIntegerMap = new TreeMap<>();
        //Go through all the available denominations
        for (Denomination denomination : Denomination.values()) {
            //Get the array of both divideToIntegralValue and remainder
            final BigDecimal[] values = bigDecimal.divideAndRemainder(denomination.value, MathContext.DECIMAL32);
            if (!values[0].equals(BigDecimal.valueOf(0.0)) && !values[1].equals(BigDecimal.valueOf(0.0)) && values[0].intValue() != 0) {
                denominationIntegerMap.put(denomination, values[0].intValue());
                bigDecimal = values[1];
            }
        }
        return denominationIntegerMap;
    }


    /***
     * This Method converts the denomiantion map to human readable text. 'Notes 1x500, 1x50, Coins 1x20, 1x5, 1x2, 1x1'
     * @param denominationIntegerMap - Denomination Integer Map
     * @return - Provides the formatted text
     */
    static String formatOutPut(final NavigableMap<Denomination, Integer> denominationIntegerMap) {
        final StringBuilder response = new StringBuilder();
        final StringBuilder notes = new StringBuilder("Notes ");
        final StringBuilder coins = new StringBuilder("Coins ");

        for (Denomination denomination : denominationIntegerMap.keySet()) {
            //If the denomination value is above add to notes StringBuilder. else add to Coins StringBuilder
            if (denomination.value.longValue() >= 50) {
                doFormat(denominationIntegerMap, notes, denomination);
            } else {
                doFormat(denominationIntegerMap, coins, denomination);
            }
        }
        return response.append(notes).append(coins).toString();

    }

    private static void doFormat(NavigableMap<Denomination, Integer> denominationIntegerMap, StringBuilder response, Denomination denomination) {
        if (denominationIntegerMap.get(denomination) > 0) {
            final Integer denominationKey = translations.get(denomination.name());
            response.append(MessageFormat.format("{0}x{1}", denominationIntegerMap.get(denomination), denominationKey));
            final long lastKey = denominationIntegerMap.lastEntry().getKey().value.longValue();
            //not to add extra comma in the end
            if (lastKey != denominationKey) {
                response.append(", ");
            }
        }
    }
}
