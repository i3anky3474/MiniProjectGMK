package com.gmk.miniproject.app.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ReaderXML {

	private final String PATH_SETTING = "https://www.google.co.th";

	public String readXmlTimestampFromServer() throws IOException {
		// Read XML From URL
		Document document = getDocmentFromURL(PATH_SETTING);

		// Read Timestamp
		NodeList nodeListTimestamp = document.getElementsByTagName("timestamp");
		if (nodeListTimestamp != null && nodeListTimestamp.getLength() > 0) {
			Node nodeTimeStamp = nodeListTimestamp.item(0);
			String timeStamp = nodeTimeStamp.getTextContent().toString();
			return timeStamp;
		} else {
			return "";
		}
	}

	private Document getDocmentFromURL(String URL)
			throws MalformedURLException, IOException {
		// Default
		Document document = null;
		URL url = new URL(URL);
		URLConnection connection = url.openConnection();
		connection.setConnectTimeout(30000);
		try {
			
		connection.connect();
		InputStream in = connection.getInputStream();

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		builderFactory.setNamespaceAware(true);

		DocumentBuilder builder;
		
			builder = builderFactory.newDocumentBuilder();
			document = builder.parse(in);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}

	public static String getStringFromNode(Node root) throws IOException {

		StringBuilder result = new StringBuilder();

		if (root.getNodeType() == 3)
			result.append(root.getNodeValue());
		else {
			if (root.getNodeType() != 9) {
				StringBuffer attrs = new StringBuffer();
				for (int k = 0; k < root.getAttributes().getLength(); ++k) {
					attrs.append(" ")
							.append(root.getAttributes().item(k).getNodeName())
							.append("=\"")
							.append(root.getAttributes().item(k).getNodeValue())
							.append("\" ");
				}
				result.append("<").append(root.getNodeName()).append(" ")
						.append(attrs).append(">");
			} else {
				result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			}

			NodeList nodes = root.getChildNodes();
			for (int i = 0, j = nodes.getLength(); i < j; i++) {
				Node node = nodes.item(i);
				result.append(getStringFromNode(node));
			}

			if (root.getNodeType() != 9) {
				result.append("</").append(root.getNodeName()).append(">");
			}
		}
		return result.toString();
	}
}
