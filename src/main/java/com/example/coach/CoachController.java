package com.example.coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class CoachController {

  @Value("${spring.datasource.url}")
  public String dbUrl;

  @Autowired
  public DataSource dataSource;

  @CrossOrigin(origins = "*")
  @PostMapping("/coaches")
  @ResponseBody
  public String createCoach(@RequestBody Coach input) {
    boolean auto_increment = true;
    int i = 0;
    while (auto_increment) {
      try (Connection connection = dataSource.getConnection()) {
        String query = " insert into Coach (Coach_id, person_id) values (?, ?)";

        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setInt(1, i);
        preparedStmt.setInt(2, input.getPersonID());

        preparedStmt.execute();
        auto_increment = false;
    } catch (Exception e) {
      i++;
    }
  }
  return "Created!";
}
  @CrossOrigin(origins = "*")
  @GetMapping("/Coaches/{id}")
  @ResponseBody
  String getCoach(@PathVariable("id") int id) {
    try (Connection connection = dataSource.getConnection()) {
      String query = "SELECT * FROM Coach WHERE Coach_id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);
      ResultSet rs = preparedStatement.executeQuery();
      ArrayList<Coach> output = new ArrayList<>();
      while (rs.next()) {

        int Coach_id = rs.getInt("Coach_id");
        int person_id = rs.getInt("person_id");

        output.add(new Coach(Coach_id, person_id));

      }

      Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
      String prettyJson = prettyGson.toJson(output);

      return prettyJson;
    } catch (Exception e) {
      return e.toString();
    }
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/Coaches")
  @ResponseBody
  String getCoaches() {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      String query = "SELECT * FROM Coach";
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<Coach> output = new ArrayList<>();
      while (rs.next()) {
        int Coach_id = rs.getInt("Coach_id");
        int person_id = rs.getInt("person_id");
        output.add(new Coach(Coach_id, person_id));

      }

      Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
      String prettyJson = prettyGson.toJson(output);

      return prettyJson;
    } catch (Exception e) {
      return "error";
    }
  }
}
