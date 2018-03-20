package com.bank;

import java.math.BigDecimal;

/**
 * Created by ugi on 3/19/2018.
 */
public enum Denomination {

    THOUSAND(BigDecimal.valueOf(1000)),
    FIVE_HUNDRED(BigDecimal.valueOf(500)),
    TWO_HUNDRED(BigDecimal.valueOf(200)),
    HUNDRED(BigDecimal.valueOf(100)),
    FIFTY(BigDecimal.valueOf(50)),
    TWENTY(BigDecimal.valueOf(20)),
    TEN(BigDecimal.valueOf(10)),
    FIVE(BigDecimal.valueOf(5)),
    TWO(BigDecimal.valueOf(2)),
    ONE(BigDecimal.valueOf(1));

    public BigDecimal value;

    Denomination(BigDecimal value) {
        this.value = value;
    }
}
