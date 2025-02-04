package dev.wayne.numbers.controller.impl;

import dev.wayne.numbers.controller.MainController;
import dev.wayne.numbers.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MainControllerImpl implements MainController {

    private final MainService mainService;

    @Override
    public Integer findNMax(String path, Integer N) {
        return mainService.findNMax(path, N);
    }
}
