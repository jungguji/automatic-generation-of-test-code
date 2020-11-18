package com.junggu.automaticgenerationoftestcode.domain.convert.domain;

import lombok.Getter;

@Getter
public enum BracketType {
    BIG("[", "]"),
    MEDIUM("{", "}");

    private String left;
    private String right;

    BracketType(String left, String right) {
        this.left = left;
        this.right = right;
    }
}