package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
public class PersonController {

    @Value("${spring.datasource.url}")
    public String dbUrl;
  
    @Autowired
    public DataSource dataSource;

    @PostMapping("/persons")
    @ResponseBody
    public String updatePerson(@RequestBody Person input){
        try (Connection connection = dataSource.getConnection()) {
          String query = " insert into person (first_name, last_name, date_of_birth, address_id) values (?, ?, ?, ?)";
    
          PreparedStatement preparedStmt = connection.prepareStatement(query);
          preparedStmt.setString (1, input.getPersonFirstName());
          preparedStmt.setString (2, input.getPersonLastName());
          preparedStmt.setDate (3, input.getPersonDateOfBirth());
          preparedStmt.setInt (4, input.getPersonAdressId());
    
          preparedStmt.execute();

            return "Created!";
          } catch (Exception e) {
            return e.toString();
          }
    }

@GetMapping("/persons")
@ResponseBody
String persons(Map<Person, Object> model) {
  try (Connection connection = dataSource.getConnection()) {
    Statement stmt = connection.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM person");
    ArrayList<Person> output = new ArrayList<>();
    while (rs.next()) {

      int person_id = rs.getInt("person_id");
      String first_name = rs.getString("first_name");
      String last_name = rs.getString("last_name");
      Date date_of_birth = rs.getDate("date_of_birth");
      int address_id = rs.getInt("address_id");

      output.add(new Person(person_id, first_name, last_name, date_of_birth, address_id));

  }

      Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
      String prettyJson = prettyGson.toJson(output);
      
    return prettyJson;
  } catch (Exception e) {
    return "error";
  }
}
}

