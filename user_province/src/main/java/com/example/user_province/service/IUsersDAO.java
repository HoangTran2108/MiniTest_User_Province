package com.example.user_province.service;

import com.example.user_province.model.Province;
import com.example.user_province.model.User;

import java.util.List;

public interface IUsersDAO {
    List<User> selectAllUser();
    Province selectProvince(int p_id);
}
