package dev.wayne.numbers.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
@Tag(name = "main")
public interface MainController {

    @Operation(
            summary = "Получение N-го максимального числа из файла",
            description = "Получение N-го по максимальности числа из заданного локального файла"
    )
    @GetMapping(path = "/max")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Запрос выполнен успешно"),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_BAD_REQUEST,
                    description = """
                            * Путь к файлу не задан
                            * Номер элемента не задан
                            * Номер элемента задан неверно
                            * Номер элемента выходит за пределы длины списка элементов. Длина списка: {длина}"""),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND, description = "Файл не найден"),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_CONFLICT, description = "Формат ячейки не соответствует целочисленному"),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR, description = "Ошибка чтения файла"),
    })
    Integer findNMax(
            @Parameter(description = "Полный путь к файлу", example = "C:\\numbers\\src\\main\\resources\\example.xlsx", required = true)
            @RequestParam(value = "path") String path,
            @Parameter(description = "Номер элемента по максимальности. Принимает значения от 1 до максимальной длины списка элементов из файла.", example = "1", required = true)
            @RequestParam(value = "N") Integer N);
}
