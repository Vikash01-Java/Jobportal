package com.jobportal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ApplicationDto;
import com.jobportal.dto.JobDto;
import com.jobportal.dto.UserDto;
import com.jobportal.service.JobService;

@RestController
@RequestMapping("/api")
public class JobController {
    
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // ================= USER =================

    @PostMapping("/users")
    public UserDto createUser(@RequestBody UserDto userDto){
        return jobService.createUser(userDto);
    }

    @GetMapping("/users")
    public List<UserDto> getUsers(){
        return jobService.getUsers();
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id){
        return jobService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        return jobService.updateUser(id, userDto);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id){
        return jobService.deleteUser(id);
    }

    @GetMapping("/users/name/{name}")
    public List<UserDto> getUserByName(@PathVariable String name){
        return jobService.getUserByName(name);
    }


    // ================= JOB =================

    @PostMapping("/jobs")
    public JobDto createJob(@RequestBody JobDto jobDto){
        return jobService.createJob(jobDto);
    }

    @GetMapping("/jobs")
    public List<JobDto> getJobs(){
        return jobService.getJobs();
    }

    @GetMapping("/jobs/{id}")
    public JobDto getJobById(@PathVariable Long id){
        return jobService.getJobById(id);
    }

    @PutMapping("/jobs/{id}")
    public JobDto updateJob(@PathVariable Long id, @RequestBody JobDto jobDto){
        return jobService.updateJob(id, jobDto);
    }

    @DeleteMapping("/jobs/{id}")
    public String deleteJob(@PathVariable Long id){
        return jobService.deleteJob(id);
    }

    @GetMapping("/jobs/title/{title}")
    public List<JobDto> getJobsByTitle(@PathVariable String title){
        return jobService.getJobsByTitle(title);
    }

    @GetMapping("/jobs/salary/{salary}")
    public List<JobDto> getJobsBySalaryGreaterThan(@PathVariable Double salary){
        return jobService.getJobsBySalaryGreaterThan(salary);
    }


    // ================= APPLICATION =================

    @PostMapping("/applications")
    public ApplicationDto apply(@RequestBody ApplicationDto applicationDto){
        return jobService.applyJob(applicationDto);
    }

    @GetMapping("/applications")
    public List<ApplicationDto> getAllApplications(){
        return jobService.getAllApplications();
    }

    @GetMapping("/applications/{id}")
    public ApplicationDto getApplicationById(@PathVariable Long id){
        return jobService.getApplicationById(id);
    }

    @PutMapping("/applications/{id}/status")
    public ApplicationDto updateApplicationStatus(@PathVariable Long id, @RequestParam String status){
        return jobService.updateApplicationStatus(id, status);
    }

    @DeleteMapping("/applications/{id}")
    public String deleteApplication(@PathVariable Long id){
        return jobService.deleteApplication(id);
    }

    @GetMapping("/applications/user/{userId}")
    public List<ApplicationDto> getApplicationsByUserId(@PathVariable Long userId){
        return jobService.getApplicationsByUserId(userId);
    }

    @GetMapping("/applications/job/{jobId}")
    public List<ApplicationDto> getApplicationsByJobId(@PathVariable Long jobId){
        return jobService.getApplicationsByJobId(jobId);
    }
}