package com.bank;


import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MoneyControllerTest {

    final Gson gson = new Gson();

    @Autowired
    private MockMvc mvc;

    @Test
    public void getWithDrawAmount() throws Exception {
        mvc.perform(post("/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(578))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Notes 1x500, 1x50, Coins 1x20, 1x5, 1x2, 1x1")));

        mvc.perform(post("/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(""))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Amount withdrawn value should be above 0")));

        mvc.perform(post("/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson("dfasdfsf"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
