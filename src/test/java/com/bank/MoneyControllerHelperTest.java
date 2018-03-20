package com.bank;

import org.junit.Test;

import java.util.NavigableMap;

import static com.bank.MoneyControllerHelper.getDenomination;
import static org.junit.Assert.assertEquals;

/**
 * Created by ugi on 3/19/2018.
 */
public class MoneyControllerHelperTest {

    @Test
    public void withdraw() {
        NavigableMap<Denomination, Integer> denominationIntegerMap = getDenomination(578);
        String response = MoneyControllerHelper.formatOutPut(denominationIntegerMap);
        System.out.println("response = " + response);
        assertEquals(response, "Notes 1x500, 1x50, Coins 1x20, 1x5, 1x2, 1x1");


        denominationIntegerMap = getDenomination(1578);
        response = MoneyControllerHelper.formatOutPut(denominationIntegerMap);
        assertEquals(response, "Notes 1x1,000, 1x500, 1x50, Coins 1x20, 1x5, 1x2, 1x1");
        System.out.println("response = " + response);

        denominationIntegerMap = getDenomination(22214);
        response = MoneyControllerHelper.formatOutPut(denominationIntegerMap);
        System.out.println("response = " + response);
        assertEquals(response, "Notes 22x1,000, 1x200, Coins 1x10, 2x2");
    }

}
