package com.ideaprojects.mno.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class XsltTransformerTest {

    private final String xml = "src/main/resources/tariffs.xml";
    private final String xslt = "src/main/resources/transform.xslt";
    private final String out = "target/tariffs-grouped-test.xml";

    @AfterEach
    void cleanup(){
        new File(out).delete();
    }

    @Test
    void transformCreatesOutputFileWithExpectedFragments() throws Exception {
        XsltTransformer.transform(xml, xslt, out);
        File f = new File(out);
        assertTrue(f.exists());
        String content = java.nio.file.Files.readString(f.toPath());
        assertTrue(content.contains("GroupedTariffs"));
        assertTrue(content.contains("Operator"));
        assertTrue(content.contains("MobiUA"));
        assertTrue(content.contains("ProTalk"));
    }
}
