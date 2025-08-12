package com.debOpsjapp.controller;

import com.debOpsjapp.api.Response.WeatherResponse;
import com.debOpsjapp.entity.User;
import com.debOpsjapp.repo.UserRepo;
import com.debOpsjapp.service.UserService;
import com.debOpsjapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private WeatherService weatherService;

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb= userService.findByUserName(username);
        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUsername(authentication.getName());
        return new ResponseEntity<>("user deleted",HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weather = weatherService.getWeather("Durgapur");

        // Defensive: check for nulls
        if (weather == null || weather.getCurrent() == null || weather.getLocation() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Hi " + authentication.getName() + ", sorry, weather information is currently unavailable.");
        }

        // Prepare weather-message
        String description = weather.getCurrent().getWeatherDescriptions() != null &&
                !weather.getCurrent().getWeatherDescriptions().isEmpty()
                ? weather.getCurrent().getWeatherDescriptions().get(0)
                : "Weather info unavailable";

        String message = String.format(
                "Hi %s! 🌤️\n" +
                        "In Durgapur, at %s:\n" +
                        "- Weather: %s\n" +
                        "- Temperature: %d°C\n" +
                        "- Feels like: %d°C\n" +
                        "- Humidity: %d%%\n" +
                        "Enjoy your day!",
                authentication.getName(),
                weather.getLocation().getLocaltime(),
                description,
                weather.getCurrent().getTemperature(),
                weather.getCurrent().getFeelsLike(),
                weather.getCurrent().getHumidity()
        );

        return ResponseEntity.ok(message);
    }
}
//controller --> service --> repo