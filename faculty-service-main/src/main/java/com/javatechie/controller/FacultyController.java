package com.javatechie.controller;

import com.javatechie.dto.CourseRequestDTO;
import com.javatechie.dto.CourseResponseDTO;
import com.javatechie.dto.ServiceResponse;
import com.javatechie.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty-service")
public class FacultyController {

    @Autowired
    private FacultyService service;

    @PostMapping("/addNewCourse")
    public ServiceResponse<CourseResponseDTO> addNewCourse(@RequestBody CourseRequestDTO courseRequestDTO) {
        return service.addNewCourseToDashboard(courseRequestDTO);
    }

    @GetMapping("/allCourses")
    public ServiceResponse<List<CourseResponseDTO>> viewAllCourses() {
        return service.fetchAllCourses();
    }

    @GetMapping("/getCourse/{id}")
    public ServiceResponse<CourseResponseDTO> getCourseById(@PathVariable Integer id) {
        return service.findCourseById(id);
    }

    @GetMapping("/getCourse/request")
    public ServiceResponse<CourseResponseDTO> getCourseByIdRequestParam(@RequestParam(required = false) Integer courseId) {
        return service.findCourseByIdUsingRequestParam(courseId);
    }

    @PutMapping("/updateCourse/{courseId}")
    public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable int courseId, @RequestBody CourseRequestDTO requestDTO) {
        service.updateCourseInDashboard(courseId, requestDTO);
        return service.findCourseById(courseId);
    }

    @DeleteMapping("/delete/{courseId}")
    public String deleteCourse(@PathVariable int courseId) {
        service.deleteCourseFromDashboard(courseId);
        return "course deleted successfully with id " + courseId;
    }
}
