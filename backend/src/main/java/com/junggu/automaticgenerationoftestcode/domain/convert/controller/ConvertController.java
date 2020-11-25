package com.junggu.automaticgenerationoftestcode.domain.convert.controller;

import com.junggu.automaticgenerationoftestcode.domain.convert.domain.Convert;
import com.junggu.automaticgenerationoftestcode.domain.convert.dto.RequestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConvertController {

    @GetMapping(value = "/convert", produces = "application/json")
    public String convert(@RequestBody RequestDTO requestDTO) {
        String type = requestDTO.getType();
        int dimension = requestDTO.getDimension();
        String value = requestDTO.getValue();

        Convert convert = new Convert(type, dimension);
        String result = convert.convertContent(value);

        return convert.prefix() + result + convert.suffix();
    }
}
