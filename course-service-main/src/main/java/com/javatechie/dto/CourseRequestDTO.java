package com.javatechie.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javatechie.annotation.CourseTypeValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDTO {

    @NotBlank(message = "Course Name can not be BLANK or EMPTY")
    private String name;
    @NotEmpty(message = "Trainer Name can not be empty")
    private String trainerName;
    @NotNull(message = "duration is required")
    private String duration; // days
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Past(message = "start date should be after")
    private Date startDate;
    @CourseTypeValidation
    private String courseType; //Live OR Recording
    @Min(value = 1500, message = "course price can't be less than 1500")
    @Max(value = 5000, message = "course price can't be greater than 5000")
    private double fees;
    private boolean isCertificateAvailable;
    @NotEmpty(message = "Description must be present")
    @Length(min = 5, max =20)
    private String description;
    @Email(message = "Invalid Email Id")
    private String emailId;
    @Pattern(regexp = "^[0-9]{10}$")
    private String contact;

}
