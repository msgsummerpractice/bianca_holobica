package com.example.demo.repository;
import java.util.List;


import com.example.demo.model.User;


public interface IUserRepository {
    public List<User> findAll();


}
