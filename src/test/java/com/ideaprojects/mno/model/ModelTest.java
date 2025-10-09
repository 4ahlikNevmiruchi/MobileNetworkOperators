package com.ideaprojects.mno.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void tariffGettersSettersAndToString() {
        Tariff t = new Tariff("id1");
        t.setName("Plan");
        t.setOperatorName("Op");
        t.setPayroll(new BigDecimal("1.23"));
        CallPrices cp = new CallPrices();
        cp.setInsideNetwork(new BigDecimal("0.1"));
        cp.setOutsideNetwork(new BigDecimal("0.2"));
        cp.setLandline(new BigDecimal("0.3"));
        t.setCallPrices(cp);
        t.setSmsPrice(new BigDecimal("0.05"));
        Parameter p = new Parameter();
        p.setNameAttr("default");
        p.setFavoriteNumberCount(2);
        p.setTariffization("per-minute");
        p.setActivationFee(new BigDecimal("5.00"));
        t.addParameter(p);

        assertEquals("id1", t.getId());
        assertEquals("Plan", t.getName());
        assertEquals("Op", t.getOperatorName());
        assertEquals(new BigDecimal("1.23"), t.getPayroll());
        assertSame(cp, t.getCallPrices());
        assertEquals(new BigDecimal("0.05"), t.getSmsPrice());
        assertEquals(1, t.getParameters().size());
        assertTrue(t.toString().contains("Tariff{"));
    }

    @Test
    void callPricesToStringAndGetters() {
        CallPrices cp = new CallPrices();
        cp.setInsideNetwork(new BigDecimal("0.11"));
        cp.setOutsideNetwork(new BigDecimal("0.22"));
        cp.setLandline(new BigDecimal("0.33"));
        assertEquals(new BigDecimal("0.11"), cp.getInsideNetwork());
        assertEquals(new BigDecimal("0.22"), cp.getOutsideNetwork());
        assertEquals(new BigDecimal("0.33"), cp.getLandline());
        assertTrue(cp.toString().contains("CallPrices{"));
    }

    @Test
    void parameterToStringAndGetters() {
        Parameter p = new Parameter();
        p.setNameAttr("promo");
        p.setFavoriteNumberCount(0);
        p.setTariffization("12-seconds");
        p.setActivationFee(new BigDecimal("0.00"));
        assertEquals("promo", p.getNameAttr());
        assertEquals(0, p.getFavoriteNumberCount());
        assertEquals("12-seconds", p.getTariffization());
        assertEquals(new BigDecimal("0.00"), p.getActivationFee());
        assertTrue(p.toString().contains("Parameter{"));
    }

    @Test
    void tariffSetParametersList() {
        Tariff t = new Tariff();
        Parameter p1 = new Parameter();
        Parameter p2 = new Parameter();
        t.setParameters(List.of(p1, p2));
        assertEquals(2, t.getParameters().size());
    }
}
