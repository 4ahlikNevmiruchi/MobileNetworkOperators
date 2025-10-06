package com.ideaprojects.mno;

import com.ideaprojects.mno.model.Tariff;
import com.ideaprojects.mno.parser.DomParser;
import com.ideaprojects.mno.parser.SaxParser;
import com.ideaprojects.mno.parser.StaxParser;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/main/resources/tariffs.xml");

            System.out.println("=== Testing DOM Parser ===");
            List<Tariff> domTariffs = DomParser.parse(xmlFile);
            domTariffs.forEach(System.out::println);

            System.out.println("\n=== Testing SAX Parser ===");
            List<Tariff> saxTariffs = SaxParser.parse(xmlFile);
            saxTariffs.forEach(System.out::println);

            System.out.println("\n=== Testing StAX Parser ===");
            List<Tariff> staxTariffs = StaxParser.parse(xmlFile);
            staxTariffs.forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}