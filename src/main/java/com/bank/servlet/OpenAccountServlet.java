package com.bank.servlet;

import com.bank.dao.AccountDAO;
import com.bank.model.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

public class OpenAccountServlet extends HttpServlet {
    private AccountDAO accountDAO = new AccountDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long customerId = Long.parseLong(req.getParameter("customerId"));
            String type = req.getParameter("type");

            Account a = new Account();
            a.setCustomerId(customerId);
            a.setType(type);
            a.setBalance(BigDecimal.ZERO);

            accountDAO.create(a);

            req.setAttribute("message", "Account opened successfully! ID = " + a.getId());
            req.getRequestDispatcher("success.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("error", "Error opening account: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
