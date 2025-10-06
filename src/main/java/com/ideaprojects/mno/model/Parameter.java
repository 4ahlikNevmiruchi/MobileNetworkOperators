package com.ideaprojects.mno.model;

import java.math.BigDecimal;

public class Parameter {
    private String nameAttr;
    private Integer favoriteNumberCount;
    private String tariffization;
    private BigDecimal activationFee;

    public String getNameAttr() {
        return nameAttr;
    }

    public void setNameAttr(String nameAttr) {
        this.nameAttr = nameAttr;
    }

    public Integer getFavoriteNumberCount() {
        return favoriteNumberCount;
    }

    public void setFavoriteNumberCount(Integer favoriteNumberCount) {
        this.favoriteNumberCount = favoriteNumberCount;
    }

    public String getTariffization() {
        return tariffization;
    }

    public void setTariffization(String tariffization) {
        this.tariffization = tariffization;
    }

    public BigDecimal getActivationFee() {
        return activationFee;
    }

    public void setActivationFee(BigDecimal activationFee) {
        this.activationFee = activationFee;
    }

    @Override
    public String toString() {
        return "Parameter{" + nameAttr + "," + favoriteNumberCount + "," + tariffization + "," + activationFee + '}';
    }
}
