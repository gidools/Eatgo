package com.maxst.eatgo.interfaces;

import com.maxst.eatgo.domain.RestaurantRepository;
import com.maxst.eatgo.domain.RestaurantRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @SpyBean(RestaurantRepositoryImpl.class)
    private RestaurantRepository restaurantRepository;

    @Test
    public void list() throws Exception {
        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Bobzip\"")))
                .andExpect(content().string(containsString("\"id\":1004")));
    }

    @Test
    void details() throws Exception {
        mvc.perform(get("/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Bobzip\"")))
                .andExpect(content().string(containsString("\"id\":1")));

        mvc.perform(get("/restaurants/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Soolzip\"")))
                .andExpect(content().string(containsString("\"id\":2")));
    }
}