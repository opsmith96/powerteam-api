package com.springbootorm.api.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
public class LocationController {
    @Autowired
    private LocationService locationService;

    // Request: Create
    @RequestMapping(method = RequestMethod.POST, value = "/locations")
    public void createPerson(@RequestBody Location location) {
        int i = 0;
        System.out.println("checkIfExists: " + locationService.checkIfExists(i));
        while (locationService.checkIfExists(i)) {
            i++;
            System.out.println(i);
            System.out.println(locationService.checkIfExists(i));
        }
        location.setLocation_id(i);
        locationService.addLocation(location);
    }

    // Request: Read all
    @RequestMapping("/locations")
    public List<Location> getAllLocations() {
        return locationService.getAllLocations("all");
    }

    // Request: Read all
    @RequestMapping(method = RequestMethod.GET, value = "/locationes_filtered/{active_flag}")
    public List<Location> getAllLocations(@PathVariable String active_flag) {
        return locationService.getAllLocations(active_flag);
    }

    // Search
    @RequestMapping("/search_locations/{word}")
    public List<Location> getLocationByWord(@PathVariable String word) {
        return locationService.getLocationByWord(word);
    }

    // Request: Read one by id
    @RequestMapping("/locations/{id}")
    public ArrayList<Location> readLocation(@PathVariable Integer id) {
        return locationService.getLocation(id);
    }

    // Request: Update
    @RequestMapping(method = RequestMethod.PUT, value = "/locations/{id}")
    public void updateLocation(@RequestBody Location location, @PathVariable Integer id) {
        locationService.updateLocation(location, id);
    }

    // Request: Delete by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/locations/{id}")
    public void deleteLocation(@PathVariable Integer id) {
        locationService.deleteLocation(id);
    }

}
