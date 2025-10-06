package com.ideaprojects.mno.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Tariff {
    private String id;
    private String name;
    private String operatorName;
    private BigDecimal payroll;
    private CallPrices callPrices;
    private BigDecimal smsPrice;
    private List<Parameter> parameters = new ArrayList<>();

    // constructors, getters, setters, toString
    public Tariff() {}
    public Tariff(String id) { this.id = id; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getOperatorName() { return operatorName; }
    public void setOperatorName(String operatorName) { this.operatorName = operatorName; }
    public BigDecimal getPayroll() { return payroll; }
    public void setPayroll(BigDecimal payroll) { this.payroll = payroll; }
    public CallPrices getCallPrices() { return callPrices; }
    public void setCallPrices(CallPrices callPrices) { this.callPrices = callPrices; }
    public BigDecimal getSmsPrice() { return smsPrice; }
    public void setSmsPrice(BigDecimal smsPrice) { this.smsPrice = smsPrice; }
    public List<Parameter> getParameters() { return parameters; }
    public void setParameters(List<Parameter> parameters) { this.parameters = parameters; }
    public void addParameter(Parameter p) { this.parameters.add(p); }

    @Override
    public String toString() {
        return "Tariff{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", payroll=" + payroll +
                ", callPrices=" + callPrices +
                ", smsPrice=" + smsPrice +
                ", parameters=" + parameters +
                '}';
    }

    public static class CallPrices {
        private BigDecimal insideNetwork;
        private BigDecimal outsideNetwork;
        private BigDecimal landline;
        // getters/setters/ toString
        public BigDecimal getInsideNetwork() { return insideNetwork; }
        public void setInsideNetwork(BigDecimal insideNetwork) { this.insideNetwork = insideNetwork; }
        public BigDecimal getOutsideNetwork() { return outsideNetwork; }
        public void setOutsideNetwork(BigDecimal outsideNetwork) { this.outsideNetwork = outsideNetwork; }
        public BigDecimal getLandline() { return landline; }
        public void setLandline(BigDecimal landline) { this.landline = landline; }
        @Override
        public String toString() {
            return "CallPrices{" + insideNetwork + "," + outsideNetwork + "," + landline + '}';
        }
    }

    public static class Parameter {
        private String nameAttr;
        private Integer favoriteNumberCount;
        private String tariffization;
        private BigDecimal activationFee;
        // getters/setters/toString
        public String getNameAttr() { return nameAttr; }
        public void setNameAttr(String nameAttr) { this.nameAttr = nameAttr; }
        public Integer getFavoriteNumberCount() { return favoriteNumberCount; }
        public void setFavoriteNumberCount(Integer favoriteNumberCount) { this.favoriteNumberCount = favoriteNumberCount; }
        public String getTariffization() { return tariffization; }
        public void setTariffization(String tariffization) { this.tariffization = tariffization; }
        public BigDecimal getActivationFee() { return activationFee; }
        public void setActivationFee(BigDecimal activationFee) { this.activationFee = activationFee; }
        @Override
        public String toString() {
            return "Parameter{" + nameAttr + "," + favoriteNumberCount + "," + tariffization + "," + activationFee + '}';
        }
    }
}