package com.springbootorm.api.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
public class ResultController {

    @Autowired
    private ResultService resultService;

    //Request: Create
    @RequestMapping(method = RequestMethod.POST, value = "/results")
    public void createResult(@RequestBody Result result){
        int i = 0;
        System.out.println("checkIfExists: " + resultService.checkIfExists(i));
        while(resultService.checkIfExists(i)){
            i++;
            System.out.println(i);
            System.out.println(resultService.checkIfExists(i));
        }
        result.setResult_id(i);
        resultService.addResult(result);
    }

    //Request: Read all
    @RequestMapping(method = RequestMethod.GET, value = "/results_filtered/{active_flag}")
    public List<Result> getAllResults(@PathVariable String active_flag){
        return resultService.getAllResults(active_flag);
    }

    //Request: Read all
    @RequestMapping("/results")
    public List<Result> getAllResults(){
        return resultService.getAllResults("all");
    }

    //Request: Read one by id
    @RequestMapping("/results/{id}")
    public ArrayList<Result> readResult(@PathVariable Integer id){
        return resultService.getResult(id);
    }

    //Request: Update
    @RequestMapping(method = RequestMethod.PUT, value = "/results/{id}")
    public void updateResult(@RequestBody Result result, @PathVariable Integer id){
        resultService.updateResult(result,id);
    }

    //Request: Delete by id
    @RequestMapping(method = RequestMethod.DELETE, value = "/results/{id}")
    public void deleteResult(@PathVariable Integer id){
        resultService.deleteResult(id);
    }
    
}
