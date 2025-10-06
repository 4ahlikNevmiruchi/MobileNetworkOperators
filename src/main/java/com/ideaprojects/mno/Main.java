package com.ideaprojects.mno;

import com.ideaprojects.mno.model.*;
import com.ideaprojects.mno.parser.*;
import com.ideaprojects.mno.util.*;

import java.io.File;
import java.util.List;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws Exception {

        File xml = new File("src/main/resources/tariffs.xml");
        File xsd = new File("src/main/resources/tariffs.xsd");

        System.out.println("Validating XML against XSD...");
        XmlValidator.validate(xml.getPath(), xsd.getPath());
        System.out.println("Validation OK.");

        System.out.println("\nParsing with DOM:");
        List<Tariff> domList = DomParser.parse(xml);
        domList.forEach(System.out::println);

        System.out.println("\nParsing with SAX:");
        List<Tariff> saxList = SaxParser.parse(xml);
        saxList.forEach(System.out::println);

        System.out.println("\nParsing with StAX:");
        List<Tariff> staxList = StaxParser.parse(xml);
        staxList.forEach(System.out::println);

        System.out.println("\nSorting DOM list by payroll ascending:");
        Collections.sort(domList, Comparators.byPayrollAsc());
        domList.forEach(t -> System.out.println(t.getName() + " - " + t.getPayroll()));

        System.out.println("\nSorting SAX list by operator then payroll:");
        Collections.sort(saxList, Comparators.byOperatorThenPayroll());
        saxList.forEach(t -> System.out.println(t.getOperatorName() + " | " + t.getName() + " - " + t.getPayroll()));

        System.out.println("\n=== XSLT Transformation ===");
        System.out.println("Transforming XML to grouped structure by operator...");
        XsltTransformer.transform(
                "src/main/resources/tariffs.xml",
                "src/main/resources/transform.xslt",
                "src/main/resources/tariffs-grouped.xml"
        );
    }
}