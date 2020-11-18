package com.junggu.automaticgenerationoftestcode.domain.convert.domain;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class Convert {
    private static final String STRING = "string";
    private static final String SEPARATOR = "@";
    private static final String COMMA = ",";

    private String type;
    private int dimension;

    public Convert(String type, int dimension) {
        this.type = type;
        this.dimension = dimension;
    }

    public String convertContent(String input) {
        String bracketRemoveString = getRemoveBracket(input);

        String[] contents = bracketRemoveString.split(COMMA);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < contents.length; i++) {
            if (i != 0 && !SEPARATOR.equals(contents[i]) && !SEPARATOR.equals(contents[i - 1])) {
                sb.append(", ");
            }

            sb.append(contents[i].trim());
        }

        return sb.toString().replace(SEPARATOR, "}, {");
    }

    private String getRemoveBracket(String input) {
        return input.replace("], [", ",@,")
                .replace("],[", ",@,")
                .replace(BracketType.BIG.getLeft(), "")
                .replace(BracketType.BIG.getRight(), "")
                .trim();
    }

    public String prefix() {
        StringBuilder sb = new StringBuilder();
        sb.append("new ");
        sb.append(getInstanceType());

        for (int i = 0; i < dimension; i++) {
            sb.append(BracketType.BIG.getLeft())
                    .append(BracketType.BIG.getRight());
        }

        sb.append(" ");

        for (int i = 0; i < dimension; i++) {
            sb.append(BracketType.MEDIUM.getLeft());
        }

        return sb.toString();
    }

    public String suffix() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            sb.append(BracketType.MEDIUM.getRight());
        }

        return sb.toString();
    }

    private String getInstanceType() {
        String returnType = this.type;

        if (STRING.equals(this.type)) {
            returnType = "String";
        }

        return returnType;
    }
}