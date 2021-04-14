package com.maxst.eatgo.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RestaurantTest {

    public static final String NAME = "Bobzip";
    public static final String ADDRESS = "Seoul";

    private Restaurant sut;

    @BeforeEach
    public void setup() {
        sut = new Restaurant(1004L, NAME, ADDRESS);
    }

    @Test
    public void creation() {
        // given

        // when

        // then
        assertThat(sut.getName(), is(NAME));
        assertThat(sut.getAddress(), is(ADDRESS));
        assertThat(sut.getId(), is(1004L));
    }

    @Test
    void information() {
        // given

        // when

        // then
        assertThat(sut.getInformation(), is(NAME + " in " + ADDRESS));
    }
}