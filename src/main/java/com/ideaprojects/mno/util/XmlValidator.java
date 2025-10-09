package com.ideaprojects.mno.util;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XmlValidator {

    private static final Logger LOG = Log.getLogger(XmlValidator.class);

    public static boolean validate(String xmlPath, String xsdPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
            LOG.info("XML is valid according to XSD.");
            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Validation failed: " + e.getMessage(), e);
            return false;
        }
    }
}
