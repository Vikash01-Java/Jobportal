package com.jobportal.mapper;

import com.jobportal.dto.ApplicationDto;
import com.jobportal.entity.Application;

public class ApplicationMapper {
	
public static ApplicationDto toDto(Application application) {
	ApplicationDto applicationDto = new ApplicationDto();
	applicationDto.setId(application.getId());
	applicationDto.setStatus(application.getStatus());
	applicationDto.setUserId(application.getUser().getId());
	applicationDto.setJobId(application.getJob().getId());
	return applicationDto;
	
}

}
