/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.repository;

import com.training.model.User;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ALz
 */
public interface UserRepository extends JpaRepository<User, Serializable>{
    @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username) and u.password = :password")
    public User authLogin(
            @Param("username")String username,
            @Param("password")String password
    );
    
//    @Query(value="SELECT u FROM User u WHERE "
//            + "u.id::TEXT LIKE '%:keyword%' or "
//            + "u.username::TEXT LIKE '%:keyword%' or "
//            + "u.password::TEXT LIKE '%:keyword%' or "
//            + "u.email::TEXT LIKE '%:keyword%' ",nativeQuery = true)
//    public List<User> searchByKeyword(@Param("keyword")String keyword);
    
    public List<User> 
        findByUsernameOrPasswordOrEmailContaining(String keyword,String keyword2,String keyword3);
    
    @Transactional
    @Modifying
    @Query("UPDATE User u"
            + " set u.username= :username,"
            + " u.password= :password,"
            + " u.email= :email "
            + " where u.id = :id")
    public void updateUserByID(
            @Param("id")Integer id,
            @Param("username")String username,
            @Param("password")String password,
            @Param("email")String email
    );
    
    
}
