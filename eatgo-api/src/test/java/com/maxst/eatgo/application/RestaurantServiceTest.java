package com.maxst.eatgo.application;

import com.maxst.eatgo.domain.MenuItem;
import com.maxst.eatgo.domain.MenuItemRepository;
import com.maxst.eatgo.domain.Restaurant;
import com.maxst.eatgo.domain.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

public class RestaurantServiceTest {

    private RestaurantService sut;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        sut = new RestaurantService(restaurantRepository, menuItemRepository);
    }

    @Test
    public void getRestaurant() {
        // given
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Kimchi"));

        when(restaurantRepository.findById(1004L)).thenReturn(new Restaurant(1004L, "", ""));
        when(menuItemRepository.findAllByRestaurantId(1004L)).thenReturn(menuItems);

        // when
        Restaurant restaurant = sut.getRestaurant(1004L);

        // then
        assertThat(restaurant.getId(), is(1004L));
        assertThat(restaurant.getMenuItems().get(0).getName(), is("Kimchi"));
    }
}