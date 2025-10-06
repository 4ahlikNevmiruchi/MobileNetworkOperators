package com.ideaprojects.mno.parser;

import com.ideaprojects.mno.model.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DomParser {
    public static List<Tariff> parse(File xmlFile) throws Exception {
        List<Tariff> list = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);
        NodeList tariffs = doc.getElementsByTagNameNS("http://com.ideaprojects.mno","tariff");
        for (int i=0;i<tariffs.getLength();i++){
            Element tEl = (Element) tariffs.item(i);
            Tariff t = new Tariff(tEl.getAttribute("id"));
            t.setName(getText(tEl,"name"));
            t.setOperatorName(getText(tEl,"operatorName"));
            t.setPayroll(new BigDecimal(getText(tEl,"payroll")));
            CallPrices cp = new CallPrices();
            Element cpEl = (Element) tEl.getElementsByTagName("callPrices").item(0);
            cp.setInsideNetwork(new BigDecimal(getText(cpEl,"insideNetwork")));
            cp.setOutsideNetwork(new BigDecimal(getText(cpEl,"outsideNetwork")));
            cp.setLandline(new BigDecimal(getText(cpEl,"landline")));
            t.setCallPrices(cp);
            t.setSmsPrice(new BigDecimal(getText(tEl,"smsPrice")));
            // parameters
            NodeList params = tEl.getElementsByTagName("parameter");
            for (int j=0;j<params.getLength();j++){
                Element pEl = (Element) params.item(j);
                Parameter p = new Parameter();
                p.setNameAttr(pEl.getAttribute("name"));
                String fav = getText(pEl,"favoriteNumberCount");
                if (fav!=null && !fav.isEmpty()) p.setFavoriteNumberCount(Integer.valueOf(fav));
                p.setTariffization(getText(pEl,"tariffization"));
                p.setActivationFee(new BigDecimal(getText(pEl,"activationFee")));
                t.addParameter(p);
            }
            list.add(t);
        }
        return list;
    }

    private static String getText(Element parent, String tagName){
        NodeList nl = parent.getElementsByTagName(tagName);
        if(nl==null || nl.getLength()==0) return "";
        return nl.item(0).getTextContent().trim();
    }
}
