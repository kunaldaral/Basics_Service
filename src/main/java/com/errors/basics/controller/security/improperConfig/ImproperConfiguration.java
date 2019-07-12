package com.errors.basics.controller.security.improperConfig;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class ImproperConfiguration {

    public class AllHosts implements HostnameVerifier {
        public boolean verify(final String hostname, final SSLSession session) {
            return true;
        }
    }

    public String checkXEEAttack() throws TransformerConfigurationException, ParserConfigurationException,
            SAXException {

        //XXE_XMLREADER
        //Generally vulnerable code
        XMLReader reader = XMLReaderFactory.createXMLReader();
//        reader.setContentHandler(customHandler);
//        reader.parse(new InputSource(inputStream));

        //purposely vulnerable code
        XMLReader readerP = XMLReaderFactory.createXMLReader();
        reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false); //purposely made false
        reader.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false); //purposely made false
//        readerP.setContentHandler(customHandler);

        //XXE_DOCUMENT
        //Generally vulnerable code
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        //purposely vulnerable code
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false); //purposely set to false
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false); //purposely set to false
        DocumentBuilder dbP = dbf.newDocumentBuilder();

//        Document doc = dbP.parse(input);

        //XXE_DTD_TRANSFORM_FACTORY & XXE_XSLT_TRANSFORM_FACTORY
        // Generally vulnerable code
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
//        transformer.transform(input, result);

        //purposely vulnerable code
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, ""); //should be "all" - purposely made empty to deny access
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, ""); //should be "all" - purposely made empty to deny access

        Transformer transformerP = factory.newTransformer();
        transformerP.setOutputProperty(OutputKeys.INDENT, "no"); //purposely made no

//        transformer.transform(input, result);

        return "";
    }

}
