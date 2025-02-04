package dev.wayne.numbers.service;

import org.springframework.stereotype.Service;

@Service
public interface MainService {

    Integer findNMax(String path, Integer N);
}
