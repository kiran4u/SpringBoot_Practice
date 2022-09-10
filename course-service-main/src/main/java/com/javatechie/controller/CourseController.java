package com.javatechie.controller;

import com.javatechie.dto.CourseRequestDTO;
import com.javatechie.dto.CourseResponseDTO;
import com.javatechie.dto.ServiceResponse;
import com.javatechie.service.CourseService;
import com.javatechie.util.AppUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    Logger log = LoggerFactory.getLogger(CourseController.class);

    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ServiceResponse<CourseResponseDTO> addCourse(@RequestBody @Valid CourseRequestDTO courseRequestDTO) {
        log.info("CourseController::addCourse Request Payload: {}", AppUtils.convertObjectToJson(courseRequestDTO));
        ServiceResponse<CourseResponseDTO> serviceResponse = new ServiceResponse<>();
        CourseResponseDTO newCourse = courseService.onboardNewCourse(courseRequestDTO);
        serviceResponse.setResponse(newCourse);
        serviceResponse.setStatus(HttpStatus.CREATED);
        log.info("CourseController::addCourse response: {}", AppUtils.convertObjectToJson(serviceResponse));
        return serviceResponse;
    }


    @GetMapping
    public ServiceResponse<List<CourseResponseDTO>> findAllCourses(){
        return new ServiceResponse<>(courseService.viewAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/search/path/{courseId}")
    public ServiceResponse<CourseResponseDTO> findCourse(@PathVariable Integer courseId){
        return new ServiceResponse<>(courseService.findByCourseId(courseId), HttpStatus.OK);
    }

    @Operation(summary = "Find Course By CourseId Using Request Param")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseResponseDTO.class)),
                    }),
            @ApiResponse(responseCode = "400", description = " course not found")

    })
    @GetMapping("/search/request")
    public ServiceResponse<CourseResponseDTO> findByCourseUsingRequestParam(@RequestParam(required = false) Integer courseId){
        return new ServiceResponse<>(courseService.findByCourseId(courseId), HttpStatus.OK);
    }

    @Operation(summary = "Delete Course By CourseId")
    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable int courseId){
        log.info("CourseController::deleteCourse deleting a course with id {}", courseId);
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    //assignment
    @Operation(summary = "Update Course By CourseId")
    @PutMapping("/{courseId}")
    public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable int courseId, @RequestBody CourseRequestDTO course){
      log.info("CourseController::updateCourse Request Payload : {} and {}", AppUtils.convertObjectToJson(course), courseId);
       CourseResponseDTO courseResponseDTO = courseService.updateCourse(courseId, course);
       ServiceResponse<CourseResponseDTO> response = new ServiceResponse<>(courseResponseDTO, HttpStatus.OK);
      return response;
    }

    @GetMapping("/count")
    public ResponseEntity<?> getCountByCourseType(){
        return new ResponseEntity<>(courseService.countCourseType(), HttpStatus.OK);
    }

    @GetMapping("/log")
    public String loggingLevel() {
        log.trace("trace message");
        log.debug("debug message");
        log.info("info message");
        log.warn("warn message");
        log.error("error message");
        return "log printed to console";
    }


}
