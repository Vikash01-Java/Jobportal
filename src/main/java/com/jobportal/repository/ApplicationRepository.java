package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.entity.Application;
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>{
	List<Application> getApplicationsByUserId(Long userId);
	List<Application> getApplicationsByJobId(Long jobId);
}
