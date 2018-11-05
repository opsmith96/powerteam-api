package com.springbootorm.api.season;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    public void addSeason(Season season) {
        seasonRepository.save(season);
    }

    //Read all
    public List<Season> getAllSeasons(String active_flag){
        List<Season> allSeason = new ArrayList<>();
        List<Season> activeSeason = new ArrayList<>();
        List<Season> inActiveSeason = new ArrayList<>();

        seasonRepository.findAll().forEach(allSeason::add);
        for (Season season:allSeason) {
            if(season.getActivity()){
                activeSeason.add(season);
            }else{
                inActiveSeason.add(season);
            }
        }
        if(active_flag.equals("active")){
            return activeSeason;
        }else if(active_flag.equals("inactive")){
            return inActiveSeason;
        }else {
            return allSeason;
        }
    }

    //Read one
    public ArrayList<Season> getSeason(Integer id){
        ArrayList<Season> seasonList = new ArrayList<>();
        seasonList.add(seasonRepository.findById(id).get());
        return seasonList;
    }

    //Update
    public void updateSeason(Season season, Integer id) {
        season.setSeason_id(id);
        Season dbSeason = seasonRepository.findById(id).get();
        if(season.getStart_date() != null && !season.getStart_date().equals("")){
            dbSeason.setStart_date(season.getStart_date());
        }
        if(season.getEnd_date() != null && !season.getEnd_date().equals("")){
            dbSeason.setEnd_date(season.getEnd_date());
        }
        if(season.getDescription() != null && !season.getDescription().equals("")){
            dbSeason.setDescription(season.getDescription());
        }
        if(season.getName() != null && !season.getName().equals("")){
            dbSeason.setName(season.getName());
        }



        seasonRepository.save(dbSeason);
    }

    public void deleteSeason(Integer id) {
        Season seasonToDelete = seasonRepository.findById(id).get();
        seasonToDelete.setActivity(false);
        updateSeason(seasonToDelete,id);    }

    //Check if exists
    public boolean checkIfExists(Integer id){
        return seasonRepository.existsById(id);
    }
    /*
    public List<Match> getMatchesInSeason(Integer seasonId){
        return seasonRepository.findBySeason_id(seasonId);
    }
    */
}
