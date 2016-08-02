/**
 * Copyright (C) 2016 The yuhaiyang Android Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yuhaiyang.xmltoexcel.utils;


import com.yuhaiyang.xmltoexcel.Cistern;
import com.yuhaiyang.xmltoexcel.Configure;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
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

    /**
     * 模版 用来生成数据
     */
    private final static String TEMPLATE = "    <string name=\"{0}\">{1}</dimen>\n";
    private final static String TEMPLATE_CHILD = "<xliff:g id=\"value{0}\">%s</xliff:g>";

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


    public static void create(List<Cistern> dates) throws Exception {
        StringBuilder target = new StringBuilder();
        target.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        target.append(Configure.COPY_RIGHT);
        target.append("<resources xmlns:xliff=\"urn:oasis:names:tc:xliff:document:1.2\">\n");
        for (Cistern cistern : dates) {

            StringBuilder result = new StringBuilder();
            int index = 0;
            for (String value : cistern.getValues()) {
                if (PLACE_HOLDER.equals(value)) {
                    result.append(TEMPLATE_CHILD
                            .replace("{0}", String.valueOf(index))
                            .replace("{1}", "%s"));
                    index++;
                } else {
                    result.append(value);
                }
            }

            target.append(TEMPLATE
                    .replace("{0}", cistern.name)
                    .replace("{1}", result));
        }

        target.append("</resources>");


        // 生成dimen.xml文件
        File targetFile = new File("D:/result_strings.xml");
        // 写入文件
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(targetFile));
            pw.print(target.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
