package com.train.mp.util;

import com.train.mp.enums.GenderEnum;
import com.train.mp.support.QqwException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author Jim Clark
 * @version 1.0
 * create on  2019/11/19 0019 13:56
 */
public class ExcelUtil {

    /**
     * 单sheet最大行数
     */
    public static final int MAX_ROW = 2;

    /**
     * 导出单sheet excel 表格
     *
     * @param response   response
     * @param sheetName  sheet名称
     * @param fileName   文件名称
     * @param topic      标题
     * @param heads      表头
     * @param objs       对象集合
     * @param columnName 字段名
     */
    public static void exportExcelOneSheet(HttpServletResponse response, String sheetName, String fileName, String topic, List<String> heads, List<?> objs, List<String> columnName) {
        HSSFWorkbook wb = new HSSFWorkbook(); //创建HSSFWorkbook对象(excel的文档对象)
        List<List<?>> lists = splitList(objs, MAX_ROW);
        for (int i = 0; i < lists.size(); i++) {
            HSSFSheet sheet = wb.createSheet(sheetName + (i + 1));//建立新的sheet对象（excel的表单）
            writeData(sheet, topic, heads, lists.get(i), columnName);
        }
        //输出Excel文件
        try {
            fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
            OutputStream output = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
            response.setContentType("application/msexcel");
            wb.write(output);
            output.close();
        } catch (Exception e) {
            throw new QqwException("导出excel错误!");
        }

    }


    /**
     * 拆分集合
     *
     * @param source 源数据
     * @param size   固定大小
     * @param <?>
     * @return
     */
    public static List<List<?>> splitList(List<?> source, int size) {
        if (Objects.isNull(source)) throw new QqwException("没有数据!");
        int sourceSize = source.size();//原数据长度
        List<List<?>> result = new ArrayList<>();
        if (sourceSize < size) {
            result.add(source);
            return result;
        }
        int tempNum = sourceSize / size;
        int modelNum = sourceSize % size;
        for (int i = 1; i < tempNum + 1; i++) {
            List<?> sub = source.subList(i * size - size, size * i);
            result.add(sub);
        }
        if (modelNum > 0) {
            List<?> sub = source.subList(tempNum * size, modelNum);
            result.add(sub);
        }
        return result;
    }

    /**
     * 写数据
     *
     * @param sheet      sheet
     * @param topic      标题
     * @param heads      表头
     * @param objs       对象集合
     * @param columnName 字段名
     */
    private static void writeData(HSSFSheet sheet, String topic, List<String> heads, List<?> objs, List<String> columnName) {
        HSSFRow row1 = sheet.createRow(0);//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFCell cell = row1.createCell(0);//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        cell.setCellValue(topic);//设置单元格内容
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        HSSFRow row2 = sheet.createRow(1);//在sheet里创建第二行
        //创建单元格并设置单元格内容 表头
        int maxIndex = heads.size();
        for (int i = 0; i < maxIndex; i++) {
            row2.createCell(i).setCellValue(heads.get(i));
        }
        //表值
        for (int i = 2; i < objs.size() + 2; i++) {
            HSSFRow row3 = sheet.createRow(i);
            Object o = objs.get(i - 2);
            for (int m = 0; m < maxIndex; m++) {
                try {
                    Method method = o.getClass().getMethod("get" + columnName.get(m));
                    Object value = method.invoke(o);
                    String strResult = Objects.isNull(value) ? "" :
                            value instanceof LocalDateTime ?
                                    ((LocalDateTime) value).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : String.valueOf(value);
                    row3.createCell(m).setCellValue(strResult);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new QqwException("导出excel错误!");
                }
            }
        }
    }

    /**
     * 获取数据
     *
     * @param returnMap  数据
     * @param clazz      类型
     * @param workbook   工作簿
     * @param num        sheet的下标
     * @param names      对应的字段名称
     * @param startIndex 开始下标 开始条数
     * @return
     * @throws Exception
     */
    public static int getExcelList(Map<String, Object> returnMap, Class<?> clazz, Workbook workbook, int num, List<String> names, int startIndex) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<Object> list = new ArrayList<>();
        map.put("lists", list);
        map.put("flag", false);
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(num);
        int endIndex = sheet.getLastRowNum();//结束条数
        /**获取封装数据**/
        for (int i = startIndex; i <= endIndex; i++) {
            Object ob = returnMap.get(String.valueOf(i));
            if (null == ob) {
                ob = clazz.newInstance();
                returnMap.put(String.valueOf(i), ob);
            }
            org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
            if (null == row) break;
            /**进行封装**/
            for (int j = 0; j < names.size(); j++) {
                org.apache.poi.ss.usermodel.Cell cell = row.getCell(j);
                Field field = clazz.getDeclaredField(names.get(j));
                Class<?> classType = field.getType();
                String name=names.get(j);
                Method method = clazz.getMethod("set" + name.substring(0, 1).toUpperCase()+name.substring(1), classType);
                method.invoke(ob, convert(cell, classType));
            }
        }
        return endIndex;
    }

    /**
     * 类型转换器,自定义扩展
     *
     * @param clazz clazz
     * @return
     */
    public static Object convert(Cell cell, Class<?> clazz) throws Exception {
        String str = "";
        if (Objects.isNull(cell)) return str;
        str = switchCellType(cell);
        //类型转换
        if (Objects.nonNull(clazz)) {
            String clazzname = clazz.toString();
            if (clazzname.equals("class java.lang.Integer"))
                return StringUtils.hasText(str) ? Integer.parseInt(str) : null;
            if (clazzname.equals("com.train.mp.enums.GenderEnum"))
                return StringUtils.hasText(str) ? GenderEnum.getValueByType(str) : 1;
        }
        return str;
    }


    /**
     * 字段类型
     *
     * @param cell cell
     * @return String
     * @throws Exception
     */
    public static String switchCellType(Cell cell) throws Exception {
        switch (cell.getCellType()) {
            case NUMERIC: // 数字
                return (int) cell.getNumericCellValue() + "";
            case STRING: // 字符串
                return cell.getStringCellValue();
            case BOOLEAN: // Boolean
                return cell.getBooleanCellValue() + "";
            case FORMULA: // 公式
                return cell.getCellFormula();
            case BLANK: // 空值
                return null;
            case ERROR: // 故障
                return null;
            default:
                return null;
        }
    }

}
