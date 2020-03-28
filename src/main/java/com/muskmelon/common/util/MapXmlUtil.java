package com.muskmelon.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author muskmelon
 * @description map与xml格式转换工具
 * @date 2020-3-28 17:04
 * @since 1.0
 */
public class MapXmlUtil {

    private static final Logger logger = LoggerFactory.getLogger(MapXmlUtil.class);

    /**
     * map数据转xml格式数据
     *
     * @param data map数据
     * @return xml数据
     * @throws Exception
     */
    public static String mapToXml(Map<String, String> data) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element root = document.createElement("xml");
        document.appendChild(root);
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String value = entry.getValue();
            if (null == value) {
                value = "";
            }
            Element field = document.createElement(entry.getKey());
            field.appendChild(document.createTextNode(value.trim()));
            root.appendChild(field);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString();
        try {
            writer.close();
        } catch (Exception e) {
            logger.error("writer close error", e);
        }
        return output;
    }

    public static Map<String, String> xmlToMap(String xmlData) throws Exception {
        try {
            Map<String, String> map = new HashMap<>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream inputStream = new ByteArrayInputStream(xmlData.getBytes("UTF-8"));
            Document document = documentBuilder.parse(inputStream);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    map.put(element.getNodeName(), element.getTextContent());
                }
            }
            inputStream.close();
            return map;
        } catch (Exception e) {
            logger.error("Invalid XML , can not convert to map. xmlContent:{}", xmlData, e);
            throw e;
        }
    }
}
