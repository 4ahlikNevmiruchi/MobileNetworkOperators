package com.ideaprojects.mno.model;

import java.math.BigDecimal;
import java.util.Objects;

public class CallPrices {
    private BigDecimal insideNetwork;
    private BigDecimal outsideNetwork;
    private BigDecimal landline;

    public CallPrices() {}

    public CallPrices(BigDecimal insideNetwork, BigDecimal outsideNetwork, BigDecimal landline) {
        this.insideNetwork = insideNetwork;
        this.outsideNetwork = outsideNetwork;
        this.landline = landline;
    }

    public BigDecimal getInsideNetwork() {
        return insideNetwork;
    }

    public void setInsideNetwork(BigDecimal insideNetwork) {
        this.insideNetwork = insideNetwork;
    }

    public BigDecimal getOutsideNetwork() {
        return outsideNetwork;
    }

    public void setOutsideNetwork(BigDecimal outsideNetwork) {
        this.outsideNetwork = outsideNetwork;
    }

    public BigDecimal getLandline() {
        return landline;
    }

    public void setLandline(BigDecimal landline) {
        this.landline = landline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallPrices that = (CallPrices) o;
        return compareBD(insideNetwork, that.insideNetwork) &&
                compareBD(outsideNetwork, that.outsideNetwork) &&
                compareBD(landline, that.landline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(normalize(insideNetwork), normalize(outsideNetwork), normalize(landline));
    }

    private static boolean compareBD(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.compareTo(b) == 0;
    }

    private static BigDecimal normalize(BigDecimal v) {
        return v == null ? null : v.stripTrailingZeros();
    }

    @Override
    public String toString() {
        return "CallPrices{" + insideNetwork + "," + outsideNetwork + "," + landline + '}';
    }
}
