package com.ideaprojects.mno.parser;

import com.ideaprojects.mno.model.*;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StaxParser {
    public static List<Tariff> parse(File xmlFile) throws Exception {
        List<Tariff> list = new ArrayList<>();
        XMLInputFactory f = XMLInputFactory.newInstance();
        try (FileInputStream in = new FileInputStream(xmlFile)) {
            XMLEventReader r = f.createXMLEventReader(in);
            Tariff current = null;
            Parameter param = null;
            CallPrices cp = null;
            String curElement = null;
            while (r.hasNext()) {
                XMLEvent ev = r.nextEvent();
                if (ev.isStartElement()) {
                    String name = ev.asStartElement().getName().getLocalPart();
                    curElement = name;
                    if ("tariff".equals(name)) {
                        current = new Tariff(ev.asStartElement().getAttributeByName(new javax.xml.namespace.QName("id")).getValue());
                    } else if ("parameter".equals(name)) {
                        param = new Parameter();
                        Attribute a = ev.asStartElement().getAttributeByName(new javax.xml.namespace.QName("name"));
                        if (a!=null) param.setNameAttr(a.getValue());
                    } else if ("callPrices".equals(name)) {
                        cp = new CallPrices();
                    }
                } else if (ev.isCharacters()) {
                    String text = ev.asCharacters().getData().trim();
                    if (text.isEmpty()) continue;
                    switch (curElement) {
                        case "name": if(param==null && cp==null) current.setName(text); break;
                        case "operatorName": current.setOperatorName(text); break;
                        case "payroll": current.setPayroll(new BigDecimal(text)); break;
                        case "insideNetwork": cp.setInsideNetwork(new BigDecimal(text)); break;
                        case "outsideNetwork": cp.setOutsideNetwork(new BigDecimal(text)); break;
                        case "landline": cp.setLandline(new BigDecimal(text)); break;
                        case "smsPrice": current.setSmsPrice(new BigDecimal(text)); break;
                        case "favoriteNumberCount": param.setFavoriteNumberCount(Integer.valueOf(text)); break;
                        case "tariffization": param.setTariffization(text); break;
                        case "activationFee": param.setActivationFee(new BigDecimal(text)); break;
                        default: break;
                    }
                } else if (ev.isEndElement()) {
                    String name = ev.asEndElement().getName().getLocalPart();
                    if ("parameter".equals(name)) {
                        current.addParameter(param); param = null;
                    } else if ("callPrices".equals(name)) {
                        current.setCallPrices(cp); cp = null;
                    } else if ("tariff".equals(name)) {
                        list.add(current); current = null;
                    }
                    curElement = null;
                }
            }
        }
        return list;
    }
}
