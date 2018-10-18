package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
  public String updatePerson(@RequestBody Person input) {
    boolean auto_increment = true;
    int i = 0;
    while (auto_increment) {
      try (Connection connection = dataSource.getConnection()) {
        String query = " insert into person (person_id, first_name, last_name, date_of_birth, address_id) values (?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setInt(1, i);
        preparedStmt.setString(2, input.getPersonFirstName());
        preparedStmt.setString(3, input.getPersonLastName());
        preparedStmt.setDate(4, input.getPersonDateOfBirth());
        preparedStmt.setInt(5, input.getPersonAdressId());

        preparedStmt.execute();
        auto_increment = false;
      } catch (Exception e) {
        i++;
      }
    }
    return "Created!";
  }

  @DeleteMapping("persons/{id}")
  public String delete(@PathVariable("id") int person_id) {
    try (Connection connection = dataSource.getConnection()) {

      String deleteSQL = "UPDATE person SET first_name = Undefined, last_name  = Undefined, date_of_birth = 0000-00-00 WHERE person_id = ? ";
      PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
      preparedStatement.setInt(1, person_id);
      preparedStatement.executeUpdate();


    return"Deleted!"; 
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
