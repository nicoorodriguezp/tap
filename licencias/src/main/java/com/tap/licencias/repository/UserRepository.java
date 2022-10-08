package com.tap.licencias.repository;

import com.tap.licencias.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {
    @Query(value = "SELECT u FROM User u")
    ArrayList<User> getUsers();
}
