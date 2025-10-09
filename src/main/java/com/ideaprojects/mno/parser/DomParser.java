package com.ideaprojects.mno.parser;

import com.ideaprojects.mno.model.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DomParser {
    private static final String NS = "http://com.ideaprojects.mno";

    public static List<Tariff> parse(File xmlFile) throws Exception {
        List<Tariff> list = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);
        NodeList tariffs = doc.getElementsByTagNameNS(NS, "tariff");
        for (int i=0;i<tariffs.getLength();i++){
            Element tEl = (Element) tariffs.item(i);
            Tariff t = new Tariff(tEl.getAttribute("id"));
            t.setName(getTextNS(tEl, "name"));
            t.setOperatorName(getTextNS(tEl, "operatorName"));
            t.setPayroll(new BigDecimal(getTextNS(tEl, "payroll")));
            CallPrices cp = new CallPrices();
            Element cpEl = (Element) tEl.getElementsByTagNameNS(NS, "callPrices").item(0);
            cp.setInsideNetwork(new BigDecimal(getTextNS(cpEl, "insideNetwork")));
            cp.setOutsideNetwork(new BigDecimal(getTextNS(cpEl, "outsideNetwork")));
            cp.setLandline(new BigDecimal(getTextNS(cpEl, "landline")));
            t.setCallPrices(cp);
            t.setSmsPrice(new BigDecimal(getTextNS(tEl, "smsPrice")));
            // parameters
            NodeList params = tEl.getElementsByTagNameNS(NS, "parameter");
            for (int j=0;j<params.getLength();j++){
                Element pEl = (Element) params.item(j);
                Parameter p = new Parameter();
                p.setNameAttr(pEl.getAttribute("name"));
                String fav = getTextNS(pEl, "favoriteNumberCount");
                if (fav!=null && !fav.isEmpty()) p.setFavoriteNumberCount(Integer.valueOf(fav));
                p.setTariffization(getTextNS(pEl, "tariffization"));
                p.setActivationFee(new BigDecimal(getTextNS(pEl, "activationFee")));
                t.addParameter(p);
            }
            list.add(t);
        }
        return list;
    }

    private static String getTextNS(Element parent, String localName){
        NodeList nl = parent.getElementsByTagNameNS(NS, localName);
        if(nl==null || nl.getLength()==0) return "";
        return nl.item(0).getTextContent().trim();
    }
}
