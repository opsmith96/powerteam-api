package com.springbootorm.api.location;

import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public void addLocation(Location location) {
        locationRepository.save(location);
    }

    // Read all
    public List<Location> getAllLocations(String active_flag) {
        List<Location> allLocations = new ArrayList<>();
        List<Location> activeLocations = new ArrayList<>();
        List<Location> inActiveLocations = new ArrayList<>();

        locationRepository.findAll().forEach(allLocations::add);
        for (Location location : allLocations) {
            if (location.getActivity()) {
                activeLocations.add(location);
            } else {
                inActiveLocations.add(location);
            }
        }
        if (active_flag.equals("active")) {
            return activeLocations;
        } else if (active_flag.equals("inactive")) {
            return inActiveLocations;
        } else {
            return allLocations;
        }
    }

    // Read one
    public ArrayList<Location> getLocation(Integer id) {
        ArrayList<Location> locationList = new ArrayList<>();
        locationList.add(locationRepository.findById(id).get());
        return locationList;
    }

    // Search
    public List<Location> getLocationByWord(String word) {
        List<Location> locations = new ArrayList<>();
        List<Location> wordLocations = new ArrayList<>();

        locationRepository.findAll().forEach(locations::add);

        for (Location location : locations) {
            if (location.getName().contains(word)) {
                wordLocations.add(location);

            }
        }
        return wordLocations;
    }

    // Update
    public void updateLocation(Location location, Integer id) {
        location.setLocation_id(id);
        Location dbLocation = locationRepository.findById(id).get();
        if (location.getAddress_id() != null && !location.getAddress_id().equals("")) {
            dbLocation.setAddress_id(location.getAddress_id());
        }
        if (location.getName() != null && !location.getName().equals("")) {
            dbLocation.setName(location.getName());
        }
        if (location.getDescription() != null && !location.getDescription().equals("")) {
            dbLocation.setDescription(location.getDescription());
        }

        locationRepository.save(dbLocation);
    }

    public void deleteLocation(Integer id) {
        Location locationToDelete = locationRepository.findById(id).get();
        locationToDelete.setActivity(false);
        updateLocation(locationToDelete, id);
    }

    // Check if exists
    public boolean checkIfExists(Integer id) {
        return locationRepository.existsById(id);
    }
}
