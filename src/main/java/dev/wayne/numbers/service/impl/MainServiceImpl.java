package dev.wayne.numbers.service.impl;

import dev.wayne.numbers.exception.ApiException;
import dev.wayne.numbers.service.MainService;
import dev.wayne.numbers.util.ExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    @Override
    public Integer findNMax(String path, Integer N) {
        if ((path == null) || path.isBlank()) {
            throw new ApiException("Путь к файлу не задан", HttpServletResponse.SC_BAD_REQUEST);
        }
        if (N == null) {
            throw new ApiException("Номер элемента не задан", HttpServletResponse.SC_BAD_REQUEST);
        }
        if (N < 0) {
            throw new ApiException("Номер элемента задан неверно", HttpServletResponse.SC_BAD_REQUEST);
        }
        List<Integer> numbers = ExcelUtil.readExcel(path);
        if (N > numbers.size()) {
            throw new ApiException("Номер элемента выходит за пределы длины списка элементов. Длина списка: " + numbers.size(), HttpServletResponse.SC_BAD_REQUEST);
        }
        quickSort(numbers, 0, numbers.size() - 1, false);
        return numbers.get(N - 1);
    }

    private void quickSort(List<Integer> numbers, int lowerBound, int upperBound, boolean asc) {
        int i = lowerBound;
        int j = upperBound;
        Integer middle = numbers.get((i + j) / 2);

        while (i <= j) {
            while (asc
                    ? (numbers.get(i) < middle)
                    : (numbers.get(i) > middle)) {
                i++;
            }
            while (asc
                    ? numbers.get(j) > middle
                    : numbers.get(j) < middle) {
                j--;
            }

            if (i <= j) {
                if (i < j) {
                    Integer temp = numbers.get(i);
                    numbers.set(i, numbers.get(j));
                    numbers.set(j, temp);
                }
                i++;
                j--;
            }
        }
        if (i < upperBound) {
            quickSort(numbers, i, upperBound, asc);
        }
        if (lowerBound < j) {
            quickSort(numbers, lowerBound, j, asc);
        }
    }
}
