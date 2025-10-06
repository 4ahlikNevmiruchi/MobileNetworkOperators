package com.ideaprojects.mno.model;

import java.math.BigDecimal;

public class CallPrices {
    private BigDecimal insideNetwork;
    private BigDecimal outsideNetwork;
    private BigDecimal landline;

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
    public String toString() {
        return "CallPrices{" + insideNetwork + "," + outsideNetwork + "," + landline + '}';
    }
}
