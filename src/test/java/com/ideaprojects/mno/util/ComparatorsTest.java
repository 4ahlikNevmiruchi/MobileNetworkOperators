package com.ideaprojects.mno.util;

import com.ideaprojects.mno.model.Tariff;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComparatorsTest {

    private Tariff t(String name, String op, String payroll){
        Tariff t = new Tariff();
        t.setName(name);
        t.setOperatorName(op);
        t.setPayroll(payroll==null? null : new BigDecimal(payroll));
        return t;
    }

    @Test
    void byPayrollAscSortsAndHandlesNullAsZero() {
        List<Tariff> list = new ArrayList<>();
        list.add(t("A","O1", null));
        list.add(t("B","O1", "0.10"));
        list.add(t("C","O1", "0.00"));
        Collections.sort(list, Comparators.byPayrollAsc());
        // First two are 0.00 and null (treated as zero) in any order
        java.util.List<BigDecimal> firstTwo = java.util.Arrays.asList(list.get(0).getPayroll(), list.get(1).getPayroll());
        assertTrue(firstTwo.contains(new BigDecimal("0.00")));
        assertTrue(firstTwo.contains(null));
        assertEquals(new BigDecimal("0.10"), list.get(2).getPayroll());
    }

    @Test
    void byNameSortsNullAsEmpty() {
        List<Tariff> list = new ArrayList<>();
        list.add(t(null, "O1", "1.00"));
        list.add(t("B", "O1", "1.00"));
        list.add(t("A", "O1", "1.00"));
        Collections.sort(list, Comparators.byName());
        assertNull(list.get(0).getName());
        assertEquals("A", list.get(1).getName());
        assertEquals("B", list.get(2).getName());
    }

    @Test
    void byOperatorThenPayroll() {
        List<Tariff> list = new ArrayList<>();
        list.add(t("A", null, "0.50"));
        list.add(t("B", "Op1", "0.10"));
        list.add(t("C", "Op1", null));
        list.add(t("D", "Op0", "0.20"));
        Collections.sort(list, Comparators.byOperatorThenPayroll());
        // null operator comes first
        assertNull(list.get(0).getOperatorName());
        // then Op0 then Op1, and within Op1, by payroll asc with null treated as zero
        assertEquals("Op0", list.get(1).getOperatorName());
        assertEquals("Op1", list.get(2).getOperatorName());
        assertEquals("Op1", list.get(3).getOperatorName());
        assertNull(list.get(2).getPayroll());
        assertEquals(new BigDecimal("0.10"), list.get(3).getPayroll());
    }
}
