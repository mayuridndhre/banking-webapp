package com.bank.dao;
import com.bank.model.Customer;
import java.sql.*;

public class CustomerDAO {

  public Customer create(Customer c) throws Exception {
    String sql = "INSERT INTO customers (first_name,last_name,email) VALUES (?,?,?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      ps.setString(1, c.getFirstName());
      ps.setString(2, c.getLastName());
      ps.setString(3, c.getEmail());
      ps.executeUpdate();
      try (ResultSet rs = ps.getGeneratedKeys()) {
        if (rs.next()) c.setId(rs.getLong(1));
      }
      return c;
    }
  }

  public Customer findById(long id) throws Exception {
    String sql = "SELECT id, first_name, last_name, email FROM customers WHERE id=?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setLong(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          Customer c = new Customer();
          c.setId(rs.getLong("id"));
          c.setFirstName(rs.getString("first_name"));
          c.setLastName(rs.getString("last_name"));
          c.setEmail(rs.getString("email"));
          return c;
        }
        return null;
      }
    }
  }

  public Customer findByEmail(String email) throws Exception {
    String sql = "SELECT id, first_name, last_name, email FROM customers WHERE email=?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, email);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          Customer c = new Customer();
          c.setId(rs.getLong("id"));
          c.setFirstName(rs.getString("first_name"));
          c.setLastName(rs.getString("last_name"));
          c.setEmail(rs.getString("email"));
          return c;
        }
        return null;
      }
    }
  }
}
