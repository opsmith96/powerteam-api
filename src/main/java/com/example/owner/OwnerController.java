package com.example.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class OwnerController {

  @Value("${spring.datasource.url}")
  public String dbUrl;

  @Autowired
  public DataSource dataSource;

  @CrossOrigin(origins = "*")
  @PostMapping("/owners")
  @ResponseBody
  public String createOwner(@RequestBody Owner input) {
    boolean auto_increment = true;
    int i = 0;
    while (auto_increment) {
      try (Connection connection = dataSource.getConnection()) {
        String query = " insert into owner (owner_id, person_id) values (?, ?)";

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
  @GetMapping("/owners/{id}")
  @ResponseBody
  String getOwner(@PathVariable("id") int id) {
    try (Connection connection = dataSource.getConnection()) {
      String query = "SELECT * FROM owner WHERE owner_id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);
      ResultSet rs = preparedStatement.executeQuery();
      ArrayList<Owner> output = new ArrayList<>();
      while (rs.next()) {

        int owner_id = rs.getInt("owner_id");
        int person_id = rs.getInt("person_id");

        output.add(new Owner(owner_id, person_id));

      }

      Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
      String prettyJson = prettyGson.toJson(output);

      return prettyJson;
    } catch (Exception e) {
      return e.toString();
    }
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/owners")
  @ResponseBody
  String getOwners() {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      String query = "SELECT * FROM owner";
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<Owner> output = new ArrayList<>();
      while (rs.next()) {
        int owner_id = rs.getInt("owner_id");
        int person_id = rs.getInt("person_id");
        output.add(new Owner(owner_id, person_id));

      }

      Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
      String prettyJson = prettyGson.toJson(output);

      return prettyJson;
    } catch (Exception e) {
      return "error";
    }
  }
}
