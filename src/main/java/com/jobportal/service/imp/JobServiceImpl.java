package com.jobportal.service.imp;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.jobportal.dto.ApplicationDto;
import com.jobportal.dto.JobDto;
import com.jobportal.dto.UserDto;
import com.jobportal.entity.Application;
import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.mapper.ApplicationMapper;
import com.jobportal.mapper.JobMapper;
import com.jobportal.mapper.UserMapper;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.JobService;

@Service
public class JobServiceImpl implements JobService{
	
	private UserRepository userRepository;
	private JobRepository jobRepository;
	private ApplicationRepository applicationRepository;
	
	public JobServiceImpl(UserRepository userRepository, JobRepository jobRepository,
			ApplicationRepository applicationRepository) {
		super();
		this.userRepository = userRepository;
		this.jobRepository = jobRepository;
		this.applicationRepository = applicationRepository;
	}
	 // ================= USER =================
@Override
	public UserDto createUser(UserDto userDto) {
		User user=UserMapper.toEntity(userDto);
		userRepository.save(user);
		return UserMapper.toDto(user);
			
	}
@Override	
	public List<UserDto> getUsers(){
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
@Override
public UserDto getUserById(Long id) {
	User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
	return UserMapper.toDto(user);
	
//	Optional<User> optionalUser = userRepository.findById(id);
//
//	if(optionalUser.isPresent()) {
//	    User user = optionalUser.get(); 
//	    return UserMapper.toDto(user);
//	} else {
//	    throw new ResourceNotFoundException("User not found");
//	}
}
@Override
public UserDto updateUser(Long id, UserDto userDto) {
	User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
	 user.setName(userDto.getName());
	 user.setEmail(userDto.getEmail());
	 userRepository.save(user);
	return UserMapper.toDto(user);
}
@Override
public String deleteUser(Long id){
	User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
	userRepository.delete(user);
	return "User is successfully removed";
}

@Override  
public List<UserDto> getUserByName(String name) {
	List<User> user = userRepository.findByName(name);
	return user.stream().map(UserMapper::toDto).collect(Collectors.toList());
	
	
//	List<User> users = userRepository.findByName(name);
//
//	List<UserDTO> dtoList = new ArrayList<>();
//  
//	for(int i = 0; i < users.size(); i++) {
//	    User u = users.get(i);
//	    dtoList.add(UserMapper.toDto(u));
//	}
//
//	return dtoList;
}
//================= JOB =================
@Override
	public JobDto createJob(JobDto jobDto) {
		Job job = JobMapper.toEntity(jobDto);
		jobRepository.save(job);
		return JobMapper.toDto(job);
		
	}
@Override
	 public List<JobDto> getJobs(){
		 return  jobRepository.findAll().stream()
				 .map(JobMapper::toDto)
				 .collect(Collectors.toList()); 
	 }

@Override
public JobDto getJobById(Long id) {
	 Job job = jobRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	
	return JobMapper.toDto(job);
}
@Override
public JobDto updateJob(Long id, JobDto jobDto) {
	Job job = jobRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
	job.setTitle(jobDto.getTitle());
	job.setDescription(jobDto.getDescription());
	job.setSalary(jobDto.getSalary());
	jobRepository.save(job);
	return JobMapper.toDto(job);
}
@Override
public String deleteJob(Long id) {
	jobRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
	jobRepository.deleteById(id);
	return "Job is successfully deleted";
}
@Override
public List<JobDto> getJobsByTitle(String title) {
	List<Job> list = jobRepository.getJobsByTitle(title);
	return list.stream().map(JobMapper::toDto).collect(Collectors.toList());
}
@Override
public List<JobDto> getJobsBySalaryGreaterThan(Double salary) {
	List<Job> list = jobRepository.getJobsBySalaryGreaterThan(salary);
	
	return list.stream().map(JobMapper::toDto).collect(Collectors.toList());
}
// ================= APPLICATION =================
@Override 
	public ApplicationDto applyJob(ApplicationDto applicationDto) {
		User user = userRepository.findById(applicationDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Job job = jobRepository.findById(applicationDto.getJobId()).orElseThrow(() -> new ResourceNotFoundException("Job not found"));

		Application app = new Application();
		app.setStatus(applicationDto.getStatus());
		app.setUser(user);
		app.setJob(job);
		 
		applicationRepository.save(app);

		return ApplicationMapper.toDto(app);
		 
	 }
@Override
public List<ApplicationDto> getAllApplications() {
	return applicationRepository.findAll()
			.stream().map(ApplicationMapper::toDto)
			.collect(Collectors.toList());

}
@Override
public ApplicationDto getApplicationById(Long id) {
	Application applications = applicationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	return ApplicationMapper.toDto(applications);
}
@Override
public ApplicationDto updateApplicationStatus(Long id, String status) {
	Application applications = applicationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	applications.setStatus(status);
	 Application updatedApplication = applicationRepository.save(applications);
	return  ApplicationMapper.toDto(updatedApplication);
}
@Override
public String deleteApplication(Long id) {
	Application applications = applicationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	applicationRepository.delete(applications);
	return "Applications Job is successfully deleted";
}

@Override
public List<ApplicationDto> getApplicationsByUserId(Long userId) {
	List<Application> applications = applicationRepository.getApplicationsByUserId(userId);
	return applications.stream().map(ApplicationMapper::toDto).collect(Collectors.toList());
}
@Override
public List<ApplicationDto> getApplicationsByJobId(Long jobId) {
	List<Application> applications = applicationRepository.getApplicationsByJobId(jobId);
	
	return applications.stream().map(ApplicationMapper::toDto).collect(Collectors.toList());
}



	

}
