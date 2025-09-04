package com.bank.dao;

import com.bank.model.Account;
import java.sql.*;
import java.math.BigDecimal;

public class AccountDAO {

    public Account create(Account a) throws Exception {
        String sql = "INSERT INTO accounts (customer_id, type, balance) VALUES (?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, a.getCustomerId());
            ps.setString(2, a.getType());
            ps.setBigDecimal(3, a.getBalance());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) a.setId(rs.getLong(1));
            }
            return a;
        }
    }

    // 🔹 Deposit / Withdraw / Transfer साठी लागणारे methods

    // balance वाढवणे (deposit)
    public void updateBalance(long accountId, BigDecimal amount, Connection conn) throws Exception {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, amount);
            ps.setLong(2, accountId);
            ps.executeUpdate();
        }
    }

    // balance कमी करणे (withdraw → negative amount पास करशील तर चालेल)
    public void deductBalance(long accountId, BigDecimal amount, Connection conn) throws Exception {
        String sql = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, amount);
            ps.setLong(2, accountId);
            ps.executeUpdate();
        }
    }

    // balance मिळवणे
    public BigDecimal getBalance(long accountId, Connection conn) throws Exception {
        String sql = "SELECT balance FROM accounts WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("balance");
                } else {
                    throw new Exception("Account not found with ID: " + accountId);
                }
            }
        }
    }
}
