package com.bank.dao;

import com.bank.model.Account;
import java.sql.*;

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
}
