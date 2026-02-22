package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.entity.*;
@Repository
public interface JobRepository extends JpaRepository<Job, Long>{
	List<Job> getJobsByTitle(String title);
	List<Job> getJobsBySalaryGreaterThan(Double salary);
}
