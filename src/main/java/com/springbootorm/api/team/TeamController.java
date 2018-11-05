package com.springbootorm.api.team;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
public class TeamController {
    @Autowired
    private TeamService teamService;

    // Request: Create
    @RequestMapping(method = RequestMethod.POST, value = "/teams")
    public void createPerson(@RequestBody Team team) {
        int i = 0;
        System.out.println("checkIfExists: " + teamService.checkIfExists(i));
        while (teamService.checkIfExists(i)) {
            i++;
            System.out.println(i);
            System.out.println(teamService.checkIfExists(i));
        }
        team.setTeam_id(i);
        teamService.addTeam(team);
    }

    // Request: Read all
    @RequestMapping(method = RequestMethod.GET, value = "/teams_filtered/{active_flag}")
    public List<Team> getAllTeams(@PathVariable String active_flag) {
        return teamService.getAllTeams(active_flag);
    }

    // Request: Read all - filtered for user page
    @RequestMapping(method = RequestMethod.GET, value = "/teamsForUser")
    public JSONArray getAllTeamsForUserPage() {
        return teamService.getAllTeamsForUser();
    }

    // Request: Read one - filtered for user page
    @RequestMapping(method = RequestMethod.GET, value = "/teamsForUser/{name}")
    public JSONObject getTeamForUserPage(@PathVariable String name) {
        JSONArray teamJsonArray = getAllTeamsForUserPage();
        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < teamJsonArray.size(); i++) {
            if (teamJsonArray.get(i).toString().contains(name)) {
                return (JSONObject) teamJsonArray.get(i);
            }
        }
        return jsonObject;
    }

    // Request: Read all
    @RequestMapping("/teams")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams("all");
    }

    // Request: Read one by id
    @RequestMapping("/teams/{id}")
    public ArrayList<Team> readTeam(@PathVariable Integer id) {
        return teamService.getTeam(id);
    }

    // Request: Update
    @RequestMapping(method = RequestMethod.PUT, value = "/teams/{id}")
    public void updateTeam(@RequestBody Team team, @PathVariable Integer id) {
        teamService.updateTeam(team, id);
    }

    // Request: Delete by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/teams/{id}")
    public void deleteTeam(@PathVariable Integer id) {
        teamService.deleteTeam(id);
    }

    // Search
    @RequestMapping("/search_teams/{word}")
    public List<Team> getPersonByWord(@PathVariable String word) {
        return teamService.getTeamsByWord(word);
    }

}
