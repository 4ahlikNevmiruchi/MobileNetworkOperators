package com.ideaprojects.mno.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class CallPricesTest {

    @Test
    void testConstructorAndGetters() {
        // Use string constructor so scale is deterministic in equals() checks
        CallPrices cp = new CallPrices(new BigDecimal("0.10"),
                new BigDecimal("0.50"),
                new BigDecimal("0.30"));

        assertEquals(0, cp.getInsideNetwork().compareTo(new BigDecimal("0.10")));
        assertEquals(0, cp.getOutsideNetwork().compareTo(new BigDecimal("0.50")));
        assertEquals(0, cp.getLandline().compareTo(new BigDecimal("0.30")));
    }

    @Test
    void testSetters() {
        CallPrices cp = new CallPrices(); // if your class has no no-arg ctor, use the arg ctor instead
        cp.setInsideNetwork(new BigDecimal("0.12"));
        cp.setOutsideNetwork(new BigDecimal("0.55"));
        cp.setLandline(new BigDecimal("0.25"));

        assertEquals(0, cp.getInsideNetwork().compareTo(new BigDecimal("0.12")));
        assertEquals(0, cp.getOutsideNetwork().compareTo(new BigDecimal("0.55")));
        assertEquals(0, cp.getLandline().compareTo(new BigDecimal("0.25")));
    }

    @Test
    void testEqualsAndHashCode() {
        // Use same scale strings so BigDecimal.equals (if used inside equals()) behaves predictably
        CallPrices a = new CallPrices(new BigDecimal("0.10"),
                new BigDecimal("0.20"),
                new BigDecimal("0.30"));
        CallPrices b = new CallPrices(new BigDecimal("0.10"),
                new BigDecimal("0.20"),
                new BigDecimal("0.30"));
        CallPrices c = new CallPrices(new BigDecimal("0.10"),
                new BigDecimal("0.50"),
                new BigDecimal("0.30"));

        assertEquals(a, b, "objects with same BigDecimal values should be equal");
        assertEquals(a.hashCode(), b.hashCode(), "hashCodes should match for equal objects");
        assertNotEquals(a, c, "objects that differ should not be equal");
    }

    @Test
    void testToStringContainsValues() {
        CallPrices cp = new CallPrices(new BigDecimal("0.10"),
                new BigDecimal("0.20"),
                new BigDecimal("0.30"));
        String s = cp.toString();
        assertTrue(s.contains("0.10") || s.contains("0.1"), "toString should contain insideNetwork");
        assertTrue(s.contains("0.20") || s.contains("0.2"), "toString should contain outsideNetwork");
        assertTrue(s.contains("0.30") || s.contains("0.3"), "toString should contain landline");
    }
}