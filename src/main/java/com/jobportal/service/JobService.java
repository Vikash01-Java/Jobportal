package com.jobportal.service;
import java.util.List;

import com.jobportal.dto.*;


public interface JobService {
	 // USER
    UserDto createUser(UserDto userDto);
    List<UserDto> getUsers();
    String deleteUser(Long id);
    UserDto updateUser(Long id, UserDto userDto); 
    UserDto getUserById(Long id);
    List<UserDto> getUserByName(String name);
    
    // USER
    JobDto createJob(JobDto jobDto);
    List<JobDto> getJobs();
    JobDto getJobById(Long id);
    JobDto updateJob(Long id, JobDto jobDto);
    String deleteJob(Long id);
    List<JobDto> getJobsByTitle(String title);
    List<JobDto> getJobsBySalaryGreaterThan(Double salary);

    // APPLICATION
    ApplicationDto applyJob(ApplicationDto applicationdto);
    List<ApplicationDto> getAllApplications();
    ApplicationDto getApplicationById(Long id);
    ApplicationDto updateApplicationStatus(Long id, String status);
    String deleteApplication(Long id);
    List<ApplicationDto> getApplicationsByUserId(Long userId);
    List<ApplicationDto> getApplicationsByJobId(Long jobId);
    


   
    

    
    
    
}
