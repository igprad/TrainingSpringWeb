/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
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
public interface UserRepository extends JpaRepository<User, Serializable> {

  @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username) and u.password = :password")
  public User authLogin(@Param("username") String username, @Param("password") String password);

  public List<User> findByUsernameOrPasswordOrEmailContaining(String keyword, String keyword2,
      String keyword3);

  public List<User> findAllByOrderByIdDesc();

  @Transactional // digunakan untuk mengatasi masalah error yaitu dengan support rollback
  @Modifying // Indicates a method should be regarded as modifying query.
  @Query("UPDATE User u" + " set u.username= :username," + " u.password= :password,"
      + " u.email= :email " + " where u.id = :id")
  public void updateUserByID(@Param("id") Integer id, @Param("username") String username,
      @Param("password") String password, @Param("email") String email);


}
