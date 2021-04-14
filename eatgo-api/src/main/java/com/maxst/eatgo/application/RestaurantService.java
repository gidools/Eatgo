package com.maxst.eatgo.application;

import com.maxst.eatgo.domain.MenuItem;
import com.maxst.eatgo.domain.MenuItemRepository;
import com.maxst.eatgo.domain.Restaurant;
import com.maxst.eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    @Autowired
    RestaurantService(RestaurantRepository restaurantRepository,
                      MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id);
        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);
        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }
}
