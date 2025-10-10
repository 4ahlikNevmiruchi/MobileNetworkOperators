package com.ideaprojects.mno;

import com.ideaprojects.mno.model.*;
import com.ideaprojects.mno.parser.*;
import com.ideaprojects.mno.util.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
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
        String groupedPath = "src/main/resources/tariffs-grouped.xml";
        XsltTransformer.transform(
                "src/main/resources/tariffs.xml",
                "src/main/resources/transform.xslt",
                groupedPath
        );
        System.out.println("Output written to " + groupedPath);

        // Showcase: read and print the generated grouped XML
        printFileToStdout(groupedPath);
    }

    /**
     * Reads the given file and prints its contents to stdout with a simple header/footer.
     */
    public static void printFileToStdout(String path) {
        try {
            System.out.println("\n=== Contents of " + path + " ===");
            String content = Files.readString(Path.of(path));
            System.out.println(content);
            System.out.println("=== End of " + path + " ===\n");
        } catch (Exception e) {
            LOG.warning("Failed to read or print file '" + path + "': " + e.getMessage());
        }
    }
}