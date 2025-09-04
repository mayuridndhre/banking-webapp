package com.bank.dao;
import com.bank.model.Transaction;
import java.sql.*;
import java.util.*;

public class TransactionDAO {
  public void record(long accountId, String type, java.math.BigDecimal amount, String desc, Connection conn) throws Exception {
    String sql = "INSERT INTO transactions (account_id,type,amount,description) VALUES (?,?,?,?)";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setLong(1, accountId);
      ps.setString(2, type);
      ps.setBigDecimal(3, amount);
      ps.setString(4, desc);
      ps.executeUpdate();
    }
  }

  public List<Transaction> listForAccount(long accountId) throws Exception {
    String sql = "SELECT id, account_id, type, amount, description, created_at FROM transactions WHERE account_id=? ORDER BY created_at DESC";
    List<Transaction> out = new ArrayList<>();
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setLong(1, accountId);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          Transaction t = new Transaction();
          t.setId(rs.getLong("id"));
          t.setAccountId(rs.getLong("account_id"));
          t.setType(rs.getString("type"));
          t.setAmount(rs.getBigDecimal("amount"));
          t.setDescription(rs.getString("description"));
          t.setCreatedAt(rs.getTimestamp("created_at"));
          out.add(t);
        }
      }
    }
    return out;
  }
}
