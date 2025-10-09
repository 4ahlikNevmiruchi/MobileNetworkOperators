package com.ideaprojects.mno;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainIntegrationTest {

    private final String out = "src/main/resources/tariffs-grouped.xml";

    @AfterEach
    void cleanup(){
        new File(out).delete();
    }

    @Test
    void mainRunsWithoutExceptions() throws Exception {
        Main.main(new String[0]);
        assertTrue(new File(out).exists(), "Output file should be created by Main");
    }
}
