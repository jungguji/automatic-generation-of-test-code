package com.junggu.automaticgenerationoftestcode.domain.convert.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junggu.automaticgenerationoftestcode.domain.convert.dto.RequestDTO;
import org.apache.coyote.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ConvertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void convert() throws Exception {
        //given

        RequestDTO dto = RequestDTO.builder()
                .type("string")
                .dimension(2)
                .value("[[\"qwe\", \"asd\"], [\"asd\", \"asd\"]]")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String test = objectMapper.writeValueAsString(dto);

        final ResultActions action = mockMvc.perform(get("/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(test))
                .andDo(print());

        //then
        MvcResult result = action.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expected = "new String[][] {{\"qwe\", \"asd\"}, {\"asd\", \"asd\"}}";
        assertEquals(expected, result.getResponse().getContentAsString());
    }
}