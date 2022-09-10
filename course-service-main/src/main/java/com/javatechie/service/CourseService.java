package com.javatechie.service;

import com.javatechie.Exception.CourseServiceBusinessException;
import com.javatechie.dao.CourseDao;
import com.javatechie.dto.CourseRequestDTO;
import com.javatechie.dto.CourseResponseDTO;
import com.javatechie.entity.CourseEntity;
import com.javatechie.util.AppUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Slf4j
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    //private Logger log = LoggerFactory.getLogger(CourseService.class);

    //Create Course Object  POST
    public CourseResponseDTO onboardNewCourse(CourseRequestDTO courseRequestDTO) {
        //convert DTO to ENTITY object
        CourseEntity courseEntity = AppUtils.mapDTOToEntity(courseRequestDTO);
        CourseEntity entity = null;
        log.info("CourseService::onboardNewCourse execution started.");
        try{
            entity = courseDao.save(courseEntity);
            log.debug("course entity response from database {} ", AppUtils.convertObjectToJson(entity) );
            CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(entity);
            //convert ENTITY to DTO
            courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
            log.debug("CourseService::onboardNewCourse method response {}", AppUtils.convertObjectToJson(courseResponseDTO) );
            log.info("CourseService::onboardNewCourse execution ended.");
            return courseResponseDTO;
        } catch (Exception exception){
            log.error("CourseService::onboardNewCourse exception occurs while persisting  the course object to DB.");
            throw new CourseServiceBusinessException("Error while saving course");
        }

    }

    //Load all the courses from DB  GET
    public List<CourseResponseDTO> viewAllCourses() {
        Iterable<CourseEntity> courseEntities;
        try{
            courseEntities = courseDao.findAll();
            return StreamSupport.stream(courseEntities.spliterator(), false)
                    .map(AppUtils::mapEntityToDTO)
                    .collect(Collectors.toList());
        } catch (Exception exception){
            throw new CourseServiceBusinessException("Error while fetching list of courses");
        }

    }

    //filter course by course id  GET
    public CourseResponseDTO findByCourseId(Integer courseId) {
        CourseEntity courseEntity = courseDao.findById(courseId).orElseThrow(() -> new CourseServiceBusinessException(courseId +" doesn't exist in system"));
        return AppUtils.mapEntityToDTO(courseEntity);
    }

    //Delete course DELETE
    public void deleteCourse(Integer courseId) {
        log.info("CourseService::deleteCourse execution started.");
        try {
            log.debug("CourseService::deleteCourse for course id  {}", courseId);
            courseDao.deleteById(courseId);
            log.info("CourseService::deleteCourse execution ended.");
        } catch (Exception e) {
            log.error("CourseService::deleteCourse exception occurs while deleting the course object.");
            throw new CourseServiceBusinessException("deleteCourse while fetching list of courses");
        }

    }

     //update the course PUT
     public CourseResponseDTO updateCourse(int courseId, CourseRequestDTO courseRequestDTO) {
         log.info("CourseService::updateCourse  execution started");
         try {
             log.debug("CourseService::updateCourse request payload {} & id {}.", AppUtils.convertObjectToJson(courseRequestDTO), courseId);
             CourseEntity existingCourseEntity = courseDao.findById(courseId).orElseThrow(() -> new RuntimeException("course object not present in db with id " + courseId));
             existingCourseEntity.setName(courseRequestDTO.getName());
             existingCourseEntity.setTrainerName(courseRequestDTO.getTrainerName());
             existingCourseEntity.setDuration(courseRequestDTO.getDuration());
             existingCourseEntity.setStartDate(courseRequestDTO.getStartDate());
             existingCourseEntity.setCourseType(courseRequestDTO.getCourseType());
             existingCourseEntity.setFees(courseRequestDTO.getFees());
             existingCourseEntity.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
             existingCourseEntity.setDescription(courseRequestDTO.getDescription());
             existingCourseEntity.setEmailId(courseRequestDTO.getEmailId());
             existingCourseEntity.setContact(courseRequestDTO.getContact());
             CourseEntity updatedCourseEntity = courseDao.save(existingCourseEntity);
             log.debug("CourseService::updateCourse getting updated course object as {}.", AppUtils.convertObjectToJson(updatedCourseEntity));
             return AppUtils.mapEntityToDTO(updatedCourseEntity);
         } catch (Exception ex) {
             log.error("CourseService::updateCourse  exception occurs while updating the course object {}." , ex.getMessage());
             throw new CourseServiceBusinessException("updatecourse service method failed  " + ex.getMessage());
         }
     }

    public Map<String, Long> countCourseType(){
        Iterable<CourseEntity> courseEntities = courseDao.findAll();
        return StreamSupport.stream(courseEntities.spliterator(), false)
                .collect(Collectors.groupingBy(CourseEntity::getCourseType, Collectors.counting()));

    }
}
