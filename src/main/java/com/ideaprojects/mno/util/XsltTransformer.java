package com.ideaprojects.mno.util;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XsltTransformer {

    private static final Logger LOG = Log.getLogger(XsltTransformer.class);

    public static void transform(String xmlPath, String xsltPath, String outputPath) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            StreamSource xsltSource = new StreamSource(new File(xsltPath));
            Transformer transformer = factory.newTransformer(xsltSource);

            StreamSource xmlSource = new StreamSource(new File(xmlPath));
            StreamResult result = new StreamResult(new File(outputPath));

            transformer.transform(xmlSource, result);
            LOG.info("XSLT transformation completed successfully. Output saved to: " + outputPath);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "XSLT transformation failed: " + e.getMessage(), e);
        }
    }
}