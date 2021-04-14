package com.maxst.eatgo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RestaurantRepositoryImplTest {

    private RestaurantRepository sut;

    @BeforeEach
    public void setup() {
        sut = new RestaurantRepositoryImpl();
    }

    @Test
    public void save() {
        Restaurant restaurant = new Restaurant("Bopzip", "Busan");

        int oldCount = sut.findAll().size();
        Restaurant saved = sut.save(restaurant);
        int newCount = sut.findAll().size();

        assertThat(saved.getId(), is(1234L));
        assertThat(newCount - oldCount, is(1));
    }
}