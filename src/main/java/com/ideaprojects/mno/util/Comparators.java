package com.ideaprojects.mno.util;

import com.ideaprojects.mno.model.Tariff;

import java.util.Comparator;
import java.math.BigDecimal;

public class Comparators {
    public static Comparator<Tariff> byPayrollAsc() {
        return Comparator.comparing(t -> t.getPayroll() == null ? BigDecimal.ZERO : t.getPayroll());
    }
    public static Comparator<Tariff> byName() {
        return Comparator.comparing(t -> t.getName() == null ? "" : t.getName());
    }
    public static Comparator<Tariff> byOperatorThenPayroll() {
        return Comparator.comparing(Tariff::getOperatorName, Comparator.nullsFirst(String::compareTo))
                .thenComparing(byPayrollAsc());
    }
}
