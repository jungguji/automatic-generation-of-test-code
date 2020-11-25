package com.junggu.automaticgenerationoftestcode.domain.convert.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestDTO {
    private String type;
    private int dimension;
    private String value;

    @Builder
    public RequestDTO(String type, int dimension, String value) {
        this.type = type;
        this.dimension = dimension;
        this.value = value;
    }
}
