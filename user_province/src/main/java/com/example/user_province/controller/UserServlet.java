package com.example.user_province.controller;

import com.example.user_province.model.Province;
import com.example.user_province.model.User;
import com.example.user_province.service.UsersDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {
    UsersDAO usersDAO = new UsersDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "listUser":
                    showSelectAll(request,response);
                    break;
                case "addUser":
                    showAddForm(request, response);
                    break;
                default:
                    showSelectAll(request,response);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/addUser.jsp");
            dispatcher.forward(request, response);
    }

    private void showSelectAll(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = usersDAO.selectAllUser();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/listUser.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addNewUser(request, response);
    }

    private void addNewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int provinceId = Integer.parseInt(request.getParameter("provinceId"));
        Province province = usersDAO.selectProvince(provinceId);
        User newUser = new User(name, province);
        usersDAO.addUser(newUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/addUser.jsp");
        dispatcher.forward(request, response);
    }
}