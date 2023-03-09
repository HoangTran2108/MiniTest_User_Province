package com.example.user_province.service;

import com.example.user_province.connection.ConnectionUser;
import com.example.user_province.model.Province;
import com.example.user_province.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO implements IUsersDAO{

    private final Connection connection;
    {
        try {
            connection = ConnectionUser.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String query1 = "{CALL select_province(?)}";
    private String query2 = "{CALL select_all_user()}";
    @Override
    public List<User> selectAllUser() {
        List<User> users = new ArrayList<>();
        if(connection != null){
            try {
                CallableStatement callableStatement = connection.prepareCall(query2);
                ResultSet rs = callableStatement.executeQuery();
                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int p_id = rs.getInt("provinceId");
                    Province province = selectProvince(p_id);
                    users.add(new User(id, name, province));
                }
                return users;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public Province selectProvince(int p_id) {
        Province province = null;
        if(connection != null){
            try {
                CallableStatement callableStatement = connection.prepareCall(query1);
                callableStatement.setInt(1, p_id);
                ResultSet rs = callableStatement.executeQuery();
                if(rs.next()){
                    String p_name = rs.getString("p_name");
                    province = new Province(p_id, p_name);
                }
                return province;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

}
