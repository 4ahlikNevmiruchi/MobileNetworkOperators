package com.ideaprojects.mno.parser;

import com.ideaprojects.mno.model.*;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.*;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.ideaprojects.mno.util.Log;

public class SaxParser {
    private static final Logger LOG = Log.getLogger(SaxParser.class);

    public static List<Tariff> parse(File xmlFile) throws Exception {
        SAXParserFactory f = SAXParserFactory.newInstance();
        f.setNamespaceAware(true);
        SAXParser parser = f.newSAXParser();
        SaxHandler h = new SaxHandler();
        parser.parse(xmlFile, h);
        return h.getTariffs();
    }

    private static class SaxHandler extends DefaultHandler {
        private List<Tariff> tariffs = new ArrayList<>();
        private Tariff current;
        private Parameter curParam;
        private CallPrices curCP;
        private StringBuilder sb = new StringBuilder();
        private String currentElement;

        public List<Tariff> getTariffs(){ return tariffs; }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            sb.setLength(0);
            currentElement = localName.isEmpty()? qName: localName;
            if(currentElement.equals("tariff")){
                current = new Tariff(attributes.getValue("id"));
            } else if(currentElement.equals("parameter")){
                curParam = new Parameter();
                curParam.setNameAttr(attributes.getValue("name"));
            } else if(currentElement.equals("callPrices")){
                curCP = new CallPrices();
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            sb.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            String el = localName.isEmpty()? qName: localName;
            String text = sb.toString().trim();
            try {
                switch(el) {
                    case "tariff":
                        tariffs.add(current); current = null; break;
                    case "name": if(curParam==null && curCP==null) current.setName(text); break;
                    case "operatorName": current.setOperatorName(text); break;
                    case "payroll": current.setPayroll(new BigDecimal(text)); break;
                    case "insideNetwork": curCP.setInsideNetwork(new BigDecimal(text)); break;
                    case "outsideNetwork": curCP.setOutsideNetwork(new BigDecimal(text)); break;
                    case "landline": curCP.setLandline(new BigDecimal(text)); break;
                    case "callPrices": current.setCallPrices(curCP); curCP=null; break;
                    case "smsPrice": current.setSmsPrice(new BigDecimal(text)); break;
                    case "favoriteNumberCount": curParam.setFavoriteNumberCount(Integer.valueOf(text)); break;
                    case "tariffization": curParam.setTariffization(text); break;
                    case "activationFee": curParam.setActivationFee(new BigDecimal(text)); break;
                    case "parameter": current.addParameter(curParam); curParam = null; break;
                    default: break;
                }
            } catch (Exception e){
                LOG.log(Level.WARNING, "SAX parsing issue at element '" + el + "' with text '" + text + "': " + e.getMessage(), e);
            }
            sb.setLength(0);
        }
    }
}
