/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crsclient.data;

import com.crsclient.model.CourseRegistration;
import java.util.List;

/**
 *
 * @author Me
 */
public interface CourseRegistrationDao { 
    List<CourseRegistration> findAll();
    CourseRegistration findID(Integer regID);
    void save(CourseRegistration courseregistration);
    void update(CourseRegistration courseregistration);
    void delete(Integer regID);
    int RegIDExists(Integer regID);
    int CourseCodeExists(String coursecode);
   
    
    //methods to consume REST API
    List<CourseRegistration> findAll_API();
    void delete_API(Integer regID);
}
