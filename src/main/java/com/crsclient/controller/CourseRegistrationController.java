/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crsclient.controller;


/**
 *
 * @author laud.c.ochei
 */


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.crsclient.model.CourseRegistration;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.crsclient.service.CourseRegistrationService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Laud.Ochei
 */


@Controller
public class CourseRegistrationController {
        @Autowired
        private CourseRegistrationService courseregistrationService;
	
        
        

        @RequestMapping(value="/list/coursereg")
        public String courseList(Model model){
            List<CourseRegistration> listCoursereg = courseregistrationService.findAll();
            model.addAttribute("listCoursereg", listCoursereg);
            return "coursereglist";
        }
    
        
        @RequestMapping("/add/coursereg")
        public String addCourseReg(Model model) {
            CourseRegistration coursereg = new CourseRegistration();
            model.addAttribute("coursereg", coursereg);
            return "addcoursereg";
        }
    
    
        @PostMapping(value = "/add/coursereg")
        public ModelAndView saveCourseReg(@Valid CourseRegistration coursereg, BindingResult bindingResult) {
            ModelAndView modelAndView = new ModelAndView();
            int courseCodeExists = courseregistrationService.CourseCodeExists(coursereg.getCoursecode());
            if (courseCodeExists > 0) {
                bindingResult
                        .rejectValue("coursecode", "error.coursereg",
                                "There is already a course registration for this student");
            }
            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("addcoursereg");
            } else {
                courseregistrationService.save(coursereg);
                modelAndView.addObject("successMessage", "Student has succesfully registered for the course");
                modelAndView.addObject("coursereg", new CourseRegistration());
                modelAndView.setViewName("redirect:/list/coursereg");
            }
            return modelAndView;
        }
    
        
        
        @RequestMapping("/edit/{id}")
        public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
            ModelAndView mav = new ModelAndView("editcoursereg");
            CourseRegistration coursereg = courseregistrationService.findID(id);
            mav.addObject("coursereg", coursereg);
            return mav;
        }
    
    
    
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        public String editStudent(@ModelAttribute("coursereg") CourseRegistration coursereg) {
            courseregistrationService.update(coursereg);
            return "redirect:/list/coursereg";
        }
    
    
        @RequestMapping("/delete/{id}")
        public String deleteStudent(@PathVariable(name = "id") int id) {
            courseregistrationService.delete(id);
            return "redirect:/list/coursereg";
        }
    
    
   @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String showStudent(@PathVariable("id") Integer id, Model model) {
        CourseRegistration coursereg = courseregistrationService.findID(id);
        if (coursereg == null) {
            model.addAttribute("msg", "Course Registration for the Student not found");
        }
        model.addAttribute("coursereg", coursereg);
	return "profile";
    }
    
    
}
