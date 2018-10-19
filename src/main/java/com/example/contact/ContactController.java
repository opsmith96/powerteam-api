package com.example.contact;

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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class ContactController {

  @Value("${spring.datasource.url}")
  public String dbUrl;

  @Autowired
  public DataSource dataSource;



  @CrossOrigin(origins = "*")
  @PostMapping("/contacts")
  @ResponseBody
  public String createContact(@RequestBody Contact input) {
      try (Connection connection = dataSource.getConnection()) {
        String query = " insert into contact (contact_id, person_id, contact_type, contact_detail) values (?, ?, ?, ?)";

        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setInt(1, input.getContactID());

        preparedStmt.setInt(2, input.getPersonID());
        preparedStmt.setString(3, input.getContactType());
        preparedStmt.setString(4, input.getContactDetail());

        preparedStmt.execute();
´      } catch (Exception e) {
        return e.toString();
      }
    
    return "Created!";
  }


  @CrossOrigin(origins = "*")
  @DeleteMapping("contacts/{id}")
  public String deleteContacts(@PathVariable("id") int contact_id) {
    try (Connection connection = dataSource.getConnection()) {

      String deleteSQL = "UPDATE contact SET contact_type = 'Undefined', contact_detail = 'Undefined' WHERE contact_id = ? ";
      PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
      preparedStatement.setInt(1, contact_id);
      preparedStatement.executeUpdate();

      return "Deleted!";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/contacts/{id}")
  @ResponseBody
  String getContact(@PathVariable("id") int id) {
    try (Connection connection = dataSource.getConnection()) {
      String query = "SELECT * FROM contact WHERE contact_id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);
      ResultSet rs = preparedStatement.executeQuery();
      ArrayList<Contact> output = new ArrayList<>();
      while (rs.next()) {

        int person_id = rs.getInt("person_id");
        int contact_id = rs.getInt("contact_id");
        String contact_type = rs.getString("contact_type");
        String contact_detail = rs.getString("contact_detail");


        output.add(new Contact(person_id, contact_id, contact_type, contact_detail));

      }

      Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
      String prettyJson = prettyGson.toJson(output);

      return prettyJson;
    } catch (Exception e) {
      return e.toString();
    }
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/contacts")
  @ResponseBody
  String getContacts() {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      String query = "SELECT * FROM contact";
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<Contact> output = new ArrayList<>();
      while (rs.next()) {
        int contact_id = rs.getInt("contact_id");

        int person_id = rs.getInt("person_id");
        String contact_type = rs.getString("contact_type");
        String contact_detail = rs.getString("contact_detail");
  
        output.add(new Contact(contact_id, person_id, contact_type, contact_detail));

      }

      Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
      String prettyJson = prettyGson.toJson(output);

      return prettyJson;
    } catch (Exception e) {
      return "error";
    }
  }
}
