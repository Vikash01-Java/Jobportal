package com.jobportal.mapper;

import com.jobportal.dto.JobDto;
import com.jobportal.dto.UserDto;
import com.jobportal.entity.Job;

public class JobMapper {
	
public static Job toEntity(JobDto jobDto) {
	Job job = new Job();
	job.setId(jobDto.getId());
	job.setTitle(jobDto.getTitle());
	job.setDescription(jobDto.getDescription());
	job.setSalary(jobDto.getSalary());
	return job;
	
}
	
public static JobDto toDto(Job job) {
	JobDto jobDto = new JobDto();
	jobDto.setId(job.getId());
	jobDto.setTitle(job.getTitle());
	jobDto.setDescription(job.getDescription());
	jobDto.setSalary(job.getSalary());
	
	return jobDto;
	
	
	
}

}
