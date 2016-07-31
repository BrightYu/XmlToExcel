/**
 * Copyright (C) 2016 The yuhaiyang Android Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yuhaiyang.xmltoexcel.utils;


import com.yuhaiyang.xmltoexcel.Cistern;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Xml解析工具
 */
public class XmlUtils {
    public static final String EXTRA_NODE_NAME = "xliff:g";
    public static final String PLACE_HOLDER = "####";

    public static List<Cistern> parse(String path) throws Exception {
        List<Cistern> cisternList = new ArrayList<>();
        InputStream input = new FileInputStream(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(input);
        Element root = doc.getDocumentElement();
        NodeList items = root.getElementsByTagName("string");
        if (items == null) {
            System.out.printf("XmlUtils,  items is null");
            return cisternList;
        }

        for (int i = 0; i < items.getLength(); i++) {
            Node item = items.item(i);
            String name = item.getAttributes().getNamedItem("name").getNodeValue();
            Cistern cistern = new Cistern(name);
            NodeList properties = item.getChildNodes();
            for (int j = 0; j < properties.getLength(); j++) {
                Node node = properties.item(j);
                String nodeName = node.getNodeName();
                System.out.println("nodeName = [" + nodeName + "]");
                String value;
                if (EXTRA_NODE_NAME.equals(nodeName)) {
                    value = PLACE_HOLDER;
                } else {
                    value = node.getTextContent();
                }
                cistern.addValue(value);
            }
            cisternList.add(cistern);
        }
        return cisternList;
    }
}
