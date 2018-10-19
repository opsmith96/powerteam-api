package com.example.person;

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
  public String createPerson(@RequestBody Person input) {
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
  public String deletePerson(@PathVariable("id") int person_id) {
    try (Connection connection = dataSource.getConnection()) {

      String deleteSQL = "UPDATE person SET first_name = 'Undefined', last_name  = 'Undefined', date_of_birth = TO_DATE('00/00/000', 'DD/MM/YYYY') WHERE person_id = ? ";
      PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
      preparedStatement.setInt(1, person_id);
      preparedStatement.executeUpdate();

      return "Deleted!";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @GetMapping("/persons/{id}")
  @ResponseBody
  String getPerson(@PathVariable("id") int id) {
    try (Connection connection = dataSource.getConnection()) {
      String query = "SELECT * FROM person WHERE person_id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);
      ResultSet rs = preparedStatement.executeQuery();
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
      return e.toString();
    }
  }

  @GetMapping("/persons")
  @ResponseBody
  String getPersons() {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      String query = "SELECT * FROM person";
      ResultSet rs = stmt.executeQuery(query);
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
