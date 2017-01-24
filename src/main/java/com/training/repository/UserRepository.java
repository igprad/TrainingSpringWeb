/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.repository;

import com.training.model.User;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ALz
 */
public interface UserRepository extends JpaRepository<User, Serializable>{
    
}
