package com.bank.servlet;

import com.bank.dao.AccountDAO;
import com.bank.dao.TransactionDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;

public class TransferServlet extends HttpServlet {
    private AccountDAO accountDAO = new AccountDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long fromId = Long.parseLong(req.getParameter("fromAccountId"));
            long toId = Long.parseLong(req.getParameter("toAccountId"));
            BigDecimal amount = new BigDecimal(req.getParameter("amount"));

            try (Connection conn = com.bank.dao.DBConnection.getConnection()) {
                conn.setAutoCommit(false);

                BigDecimal fromBalance = accountDAO.getBalance(fromId, conn);
                if (fromBalance.compareTo(amount) < 0) {
                    throw new Exception("Insufficient balance");
                }

                accountDAO.updateBalance(fromId, amount.negate(), conn);
                accountDAO.updateBalance(toId, amount, conn);

                transactionDAO.record(fromId, "TRANSFER_OUT", amount, "Transfer to " + toId, conn);
                transactionDAO.record(toId, "TRANSFER_IN", amount, "Transfer from " + fromId, conn);

                conn.commit();
            }

            req.setAttribute("message", "✅ Transfer successful!");
            req.getRequestDispatcher("success.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("error", "❌ Error in transfer: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
