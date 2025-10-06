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
}