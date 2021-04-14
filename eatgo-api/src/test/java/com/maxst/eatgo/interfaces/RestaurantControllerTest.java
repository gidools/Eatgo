package com.maxst.eatgo.interfaces;

import com.maxst.eatgo.application.RestaurantService;
import com.maxst.eatgo.domain.MenuItem;
import com.maxst.eatgo.domain.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean(RestaurantService.class)
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1004L, "Bobzip", "Seoul"));

        when(restaurantService.getRestaurants()).thenReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Bobzip\"")))
                .andExpect(content().string(containsString("\"id\":1004")));
    }

    @Test
    void details() throws Exception {
        Restaurant restaurant = new Restaurant(2L, "Soolzip", "Seoul");
        restaurant.addMenuItem(new MenuItem("Kimchi"));

        when(restaurantService.getRestaurant(2L)).thenReturn(restaurant);

        mvc.perform(get("/restaurants/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Soolzip\"")))
                .andExpect(content().string(containsString("\"id\":2")))
                .andExpect(content().string(containsString("Kimchi")));
    }

    @Test
    void create() throws Exception {
        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Soolzip\", \"address\":\"Seoul\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1234"));

        verify(restaurantService).addRestaurant(any());
    }
}