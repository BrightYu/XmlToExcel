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

import com.yuhaiyang.xmltoexcel.model.Cistern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel 工具类
 */
public class ExcelUtils {

    public static List<Cistern> parse(String path) throws Exception {
        List<Cistern> cisternList = new ArrayList<>();
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));
        //2.得到Excel工作簿对象
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        //3.得到Excel工作表对象
        HSSFSheet sheet = wb.getSheetAt(0);
        //总行数
        int trLength = sheet.getLastRowNum();
        //4.得到Excel工作表的行
        for (int i = 1; i < trLength; i++) {
            HSSFRow row = sheet.getRow(i);
            HSSFCell idCell = row.getCell(0);
            Cistern cistern = new Cistern(idCell.getStringCellValue());
            for (int j = 1; j < row.getLastCellNum(); j++) {
                HSSFCell cell = row.getCell(j);
                cistern.addValue(cell.getStringCellValue());
            }
            cisternList.add(cistern);
        }
        return cisternList;
    }

    public static String create(List<Cistern> dates, String path) throws Exception {
        if (dates == null || dates.isEmpty()) {
            System.out.println("ExcelUtils, data is empty");
            throw new Exception("datas is empty");
        }

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = workbook.createSheet("源语言");
        sheet.setDefaultRowHeightInPoints(20);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle contentStyle = getContentStyle(workbook);
        int rowIndex = 1;
        int size = 0;
        for (Cistern cistern : dates) {
            HSSFRow contentRow = sheet.createRow(rowIndex);
            List<String> values = cistern.getValues();
            HSSFCell idCell = contentRow.createCell(0);
            idCell.setCellValue(cistern.name);
            idCell.setCellStyle(contentStyle);
            int _size = values.size();
            size = Math.max(size, _size);
            for (int i = 1; i <= _size; i++) {
                HSSFCell content = contentRow.createCell(i);
                String value = values.get(i - 1);
                content.setCellValue(value);
                content.setCellStyle(contentStyle);
            }
            rowIndex++;
        }

        addHeader(workbook, sheet, row, size);

        String result = StringUtils.plusString(path, File.separator, "languages.xls");
        FileOutputStream outputStream = new FileOutputStream(result);
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        return result;
    }


    private static void addHeader(HSSFWorkbook workbook, HSSFSheet sheet, HSSFRow row, int valueSize) {
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle headerStyle = getHeaderStyle(workbook);

        HSSFCell header = row.createCell(0);
        header.setCellValue("ID");
        header.setCellStyle(headerStyle);

        for (int i = 1; i <= valueSize; i++) {
            header = row.createCell(i);
            header.setCellValue("VALUES");
            header.setCellStyle(headerStyle);
        }

        sheet.addMergedRegion(new CellRangeAddress(
                0, // 起始行
                0, // 结束行
                1, // 其实列
                valueSize  // 结束列
        ));
    }

    private static HSSFCellStyle getHeaderStyle(HSSFWorkbook workbook) {
        final short borderSize = CellStyle.BORDER_MEDIUM;
        final short borderColor = IndexedColors.BLACK.getIndex();
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);

        style.setBorderBottom(borderSize);
        style.setBottomBorderColor(borderColor);
        style.setBorderTop(borderSize);
        style.setTopBorderColor(borderColor);
        style.setBorderLeft(borderSize);
        style.setLeftBorderColor(borderColor);
        style.setBorderRight(borderSize);
        style.setRightBorderColor(borderColor);

        return style;
    }


    private static HSSFCellStyle getContentStyle(HSSFWorkbook workbook) {
        final short borderSize = CellStyle.BORDER_THIN;
        final short borderColor = IndexedColors.BLACK.getIndex();
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);

        style.setBorderBottom(borderSize);
        style.setBottomBorderColor(borderColor);
        style.setBorderTop(borderSize);
        style.setTopBorderColor(borderColor);
        style.setBorderLeft(borderSize);
        style.setLeftBorderColor(borderColor);
        style.setBorderRight(borderSize);
        style.setRightBorderColor(borderColor);

        return style;
    }
}
