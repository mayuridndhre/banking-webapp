package com.bank.servlet;

import com.bank.dao.AccountDAO;
import com.bank.dao.TransactionDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;

public class DepositServlet extends HttpServlet {
    private AccountDAO accountDAO = new AccountDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long accountId = Long.parseLong(req.getParameter("accountId"));
            BigDecimal amount = new BigDecimal(req.getParameter("amount"));

            try (Connection conn = com.bank.dao.DBConnection.getConnection()) {
                conn.setAutoCommit(false);

                accountDAO.updateBalance(accountId, amount, conn); // balance वाढवणे
                transactionDAO.record(accountId, "DEPOSIT", amount, "Deposit", conn);

                conn.commit();
            }

            req.setAttribute("message", "✅ Deposit successful!");
            req.getRequestDispatcher("success.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("error", "❌ Error in deposit: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
