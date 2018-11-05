package com.springbootorm.api.coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoachService {
    @Autowired
    private CoachRepository coachRepository;

    // Create
    public void addCoach(Coach coach) {
        coachRepository.save(coach);
    }

    // Read all
    public List<Coach> getAllCoachs(String active_flag) {
        List<Coach> allCoachs = new ArrayList<>();
        List<Coach> activeCoachs = new ArrayList<>();
        List<Coach> inActiveCoachs = new ArrayList<>();

        coachRepository.findAll().forEach(allCoachs::add);
        for (Coach coach : allCoachs) {
            if (coach.getActivity()) {
                activeCoachs.add(coach);
            } else {
                inActiveCoachs.add(coach);
            }
        }
        if (active_flag.equals("active")) {
            return activeCoachs;
        } else if (active_flag.equals("inactive")) {
            return inActiveCoachs;
        } else {
            return allCoachs;
        }
    }

    // Read one
    public ArrayList<Coach> getCoach(Integer id) {
        ArrayList<Coach> coachList = new ArrayList<>();
        coachList.add(coachRepository.findById(id).get());
        return coachList;
    }

    // Update
    public void updateCoach(Coach coach, Integer id) {
        Coach dbCoach = coachRepository.findById(id).get();
        if (coach.getPerson_id() != null && !coach.getPerson_id().equals("")) {
            dbCoach.setPerson_id(coach.getPerson_id());
        }
        coachRepository.save(dbCoach);
    }

    // Delete
    public void deleteCoach(Integer id) {
        Coach coachToDelete = coachRepository.findById(id).get();
        coachToDelete.setActivity(false);
        updateCoach(coachToDelete, id);
    }

    // Check if exists
    public boolean checkIfExists(Integer id) {
        return coachRepository.existsById(id);
    }
}
