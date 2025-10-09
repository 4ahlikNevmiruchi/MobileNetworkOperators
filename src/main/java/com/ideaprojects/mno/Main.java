package com.ideaprojects.mno;

import com.ideaprojects.mno.model.*;
import com.ideaprojects.mno.parser.*;
import com.ideaprojects.mno.util.*;

import java.io.File;
import java.util.List;
import java.util.Collections;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOG = Log.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        File xml = new File("src/main/resources/tariffs.xml");
        File xsd = new File("src/main/resources/tariffs.xsd");

        // Keep validation and serious events in logs, but show user-facing output on stdout for clarity
        LOG.info("Validating XML against XSD...");
        XmlValidator.validate(xml.getPath(), xsd.getPath());
        LOG.info("Validation OK.");

        System.out.println("\n=== Parsing with DOM ===");
        List<Tariff> domList = DomParser.parse(xml);
        domList.forEach(t -> System.out.println(t));

        System.out.println("\n=== Parsing with SAX ===");
        List<Tariff> saxList = SaxParser.parse(xml);
        saxList.forEach(t -> System.out.println(t));

        System.out.println("\n=== Parsing with StAX ===");
        List<Tariff> staxList = StaxParser.parse(xml);
        staxList.forEach(t -> System.out.println(t));

        System.out.println("\n=== Sorting DOM list by payroll ascending ===");
        Collections.sort(domList, Comparators.byPayrollAsc());
        domList.forEach(t -> System.out.println(t.getName() + " - " + t.getPayroll()));

        System.out.println("\n=== Sorting SAX list by operator then payroll ===");
        Collections.sort(saxList, Comparators.byOperatorThenPayroll());
        saxList.forEach(t -> System.out.println(t.getOperatorName() + " | " + t.getName() + " - " + t.getPayroll()));

        System.out.println("\n=== XSLT Transformation ===");
        System.out.println("Transforming XML to grouped structure by operator...");
        XsltTransformer.transform(
                "src/main/resources/tariffs.xml",
                "src/main/resources/transform.xslt",
                "src/main/resources/tariffs-grouped.xml"
        );
        System.out.println("Output written to src/main/resources/tariffs-grouped.xml");
    }
}