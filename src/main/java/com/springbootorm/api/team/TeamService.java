package com.springbootorm.api.team;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.springbootorm.api.association.Association;
import com.springbootorm.api.association.AssociationRepository;
import com.springbootorm.api.association.AssociationService;
import com.springbootorm.api.coach.Coach;
import com.springbootorm.api.coach.CoachRepository;
import com.springbootorm.api.location.Location;
import com.springbootorm.api.location.LocationRepository;
import com.springbootorm.api.person.Person;
import com.springbootorm.api.person.PersonRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private AssociationRepository associationRepository;
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private LocationRepository locationRepository;

    public void addTeam(Team team) {
        teamRepository.save(team);
    }

    // Read all
    public List<Team> getAllTeams(String active_flag) {
        List<Team> allTeams = new ArrayList<>();
        List<Team> activeTeams = new ArrayList<>();
        List<Team> inActiveTeams = new ArrayList<>();

        teamRepository.findAll().forEach(allTeams::add);
        for (Team team : allTeams) {
            if (team.getActivity()) {
                activeTeams.add(team);
            } else {
                inActiveTeams.add(team);
            }
        }
        if (active_flag.equals("active")) {
            return activeTeams;
        } else if (active_flag.equals("inactive")) {
            return inActiveTeams;
        } else {
            return allTeams;
        }
    }

    // Read one
    public ArrayList<Team> getTeam(Integer id) {
        ArrayList<Team> teamList = new ArrayList<>();
        teamList.add(teamRepository.findById(id).get());
        return teamList;
    }

    // Update
    public void updateTeam(Team team, Integer id) {
        Team dbTeam = teamRepository.findById(id).get();
        if (team.getAssociation_id() != null && !team.getAssociation_id().equals("")) {
            dbTeam.setAssociation_id(team.getAssociation_id());
        }
        if (team.getCoach_id() != null && !team.getCoach_id().equals("")) {
            dbTeam.setCoach_id(team.getCoach_id());
        }
        if (team.geOwner_id() != null && !team.geOwner_id().equals("")) {
            dbTeam.setOwner_id(team.geOwner_id());
        }
        if (team.getLocation_id() != null && !team.getLocation_id().equals("")) {
            dbTeam.setLocation_id(team.getLocation_id());
        }
        if (team.getName() != null && !team.getName().equals("")) {
            dbTeam.setName(team.getName());
        }

        teamRepository.save(dbTeam);
    }

    public void deleteTeam(Integer id) {
        Team teamToDelete = teamRepository.findById(id).get();
        teamToDelete.setActivity(false);
        updateTeam(teamToDelete, id);
    }

    // Check if exists
    public boolean checkIfExists(Integer id) {
        return teamRepository.existsById(id);
    }

    // Search
    public List<Team> getTeamsByWord(String word) {
        List<Team> teams = new ArrayList<>();
        List<Team> wordTeams = new ArrayList<>();

        teamRepository.findAll().forEach(teams::add);

        for (Team team : teams) {
            if (team.getName().contains(word)) {
                wordTeams.add(team);
            }
        }
        return wordTeams;
    }

    //Get all teams and make new json with location name, coach name and association name:
    public JSONArray getAllTeamsForUser() {
        JSONArray jsonArray = new JSONArray();

        List<Team> teams = new ArrayList<>();

        teamRepository.findAll().forEach(teams::add);
        for (Team team : teams) {
            if (team.getActivity()) {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("team",team.getName());

                int association_id = team.getAssociation_id();
                jsonObject.put("association",associationRepository.findById(association_id).get().getName());

                int location_id = team.getLocation_id();
                jsonObject.put("location",locationRepository.findById(location_id).get().getName());

                int coach_id = team.getCoach_id();
                Coach coach = coachRepository.findById(coach_id).get();

                int person_id = coach.getPerson_id();
                jsonObject.put("coach",personRepository.findById(person_id).get().getFirst_name() + " " + personRepository.findById(person_id).get().getLast_name());

                jsonArray.add(jsonObject);

            }
        }
        return jsonArray;
    }


}
