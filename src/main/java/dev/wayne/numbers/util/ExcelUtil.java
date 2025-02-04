package dev.wayne.numbers.util;

import dev.wayne.numbers.exception.ApiException;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class ExcelUtil {

    /**
     * Этот метод предназначен только под формат задания.
     * То есть чтению подлежит только первый столбец и ячейки должны содержать только целые числа.
     *
     * @param path Полный путь к файлу к примеру C:\numbers\src\main\resources\example.xlsx
     * @return Список целых чисел из файла
     */
    public static List<Integer> readExcel(String path) {
        try (InputStream file = new FileInputStream(path); Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            List<Integer> numbers = new LinkedList<>();
            sheet.forEach(row -> {
                numbers.add((int) Math.round(row.getCell(0).getNumericCellValue()));
            });
            return numbers;
        } catch (FileNotFoundException e) {
            throw new ApiException("Файл не найден", HttpServletResponse.SC_NOT_FOUND);
        } catch (IOException e) {
            throw new ApiException("Ошибка чтения файла", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IllegalStateException e) {
            throw new ApiException("Формат ячейки не соответствует целочисленному", HttpServletResponse.SC_CONFLICT);
        }
    }
}
