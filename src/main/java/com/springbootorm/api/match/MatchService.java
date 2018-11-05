package com.springbootorm.api.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    //Create
    public void addMatch(Match match){
        matchRepository.save(match);
    }

    //Read all
    public List<Match> getAllMatchs(String active_flag){
        List<Match> allMatchs = new ArrayList<>();
        List<Match> activeMatchs = new ArrayList<>();
        List<Match> inActiveMatchs = new ArrayList<>();

        matchRepository.findAll().forEach(allMatchs::add);
        for (Match match:allMatchs) {
            if(match.getActivity()){
                activeMatchs.add(match);
            }else{
                inActiveMatchs.add(match);
            }
        }
        if(active_flag.equals("active")){
            return activeMatchs;
        }else if(active_flag.equals("inactive")){
            return inActiveMatchs;
        }else {
            return allMatchs;
        }
    }

    //Read one
    public ArrayList<Match> getMatch(Integer id){
        ArrayList<Match> matchList = new ArrayList<>();
        matchList.add(matchRepository.findById(id).get());
        return matchList;
    }

    //Update
    public void updateMatch(Match match, Integer id) {
        match.setMatch_id(id);
        Match dbMatch = matchRepository.findById(id).get();
        if(match.getMatch_date() != null && !match.getMatch_date().equals("")){
            dbMatch.setMatch_date(match.getMatch_date());
        }
        if(match.getHome_team_id() != null && !match.getHome_team_id().equals("")){
            dbMatch.setHome_team_i(match.getHome_team_id());
        }
        if(match.getAway_team_id() != null && !match.getAway_team_id().equals("")){
            dbMatch.setAway_team_id(match.getAway_team_id());
        }
        if(match.getSeason_id() != null && !match.getSeason_id().equals("")){
            dbMatch.setSeason_id(match.getSeason_id());
        }
        if(match.getLocation_id() != null && !match.getLocation_id().equals("")){
            dbMatch.setLocation_id(match.getLocation_id());
        }

        matchRepository.save(dbMatch);  
      }

    //Delete
    public void deleteMatch(Integer id) {
        Match matchToDelete = matchRepository.findById(id).get();
        matchToDelete.setActivity(false);
        updateMatch(matchToDelete,id);
    }

    //Check if exists
    public boolean checkIfExists(Integer id){
        return matchRepository.existsById(id);
    }

}