package com.ideaprojects.mno.util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class XmlValidatorTest {

    @Test
    void validatesValidXmlAgainstXsd() {
        String xml = "src/main/resources/tariffs.xml";
        String xsd = "src/main/resources/tariffs.xsd";
        assertTrue(XmlValidator.validate(xml, xsd));
    }

    @Test
    void invalidXmlReturnsFalse() throws Exception {
        // create temp invalid xml (negative money violates minInclusive)
        Path temp = Files.createTempFile("invalid-tariffs", ".xml");
        try (FileWriter w = new FileWriter(temp.toFile())) {
            w.write("""
                    <?xml version=\"1.0\" encoding=\"UTF-8\"?>
                    <Tariff xmlns=\"http://com.ideaprojects.mno\">
                        <tariff id=\"t1\">
                            <name>Bad</name>
                            <operatorName>Op</operatorName>
                            <payroll>-1.00</payroll>
                            <callPrices>
                                <insideNetwork>0.10</insideNetwork>
                                <outsideNetwork>0.10</outsideNetwork>
                                <landline>0.10</landline>
                            </callPrices>
                            <smsPrice>0.10</smsPrice>
                            <parameters>
                                <parameter name=\"p\">
                                    <tariffization>per-minute</tariffization>
                                    <activationFee>0.00</activationFee>
                                </parameter>
                            </parameters>
                        </tariff>
                    </Tariff>
                    """);
        }
        assertFalse(XmlValidator.validate(temp.toString(), "src/main/resources/tariffs.xsd"));
        new File(temp.toString()).delete();
    }
}
