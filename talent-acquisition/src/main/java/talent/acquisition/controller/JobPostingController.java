package talent.acquisition.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import talent.acquisition.controller.model.CandidateData;
import talent.acquisition.controller.model.JobPostingData;
import talent.acquisition.controller.model.RecruiterData;
import talent.acquisition.service.JobPostingService;

@RestController
@RequestMapping("/job_posting")
@Slf4j
public class JobPostingController {
	
	@Autowired
	private JobPostingService jobPostingService;
	
	@PostMapping("/recruiter")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RecruiterData insertRecruiter(@RequestBody RecruiterData recruiterData) {
		log.info("Creating recruiter {}", recruiterData);
		return jobPostingService.saveRecruiter(recruiterData);
	}
	
	@PutMapping("/recruiter/{recruiterId}")
	public RecruiterData updateRecruiter(@PathVariable Long recruiterId, 
			@RequestBody RecruiterData recruiterData) {
		recruiterData.setRecruiterId(recruiterId);
		log.info("Updating recruiter {}", recruiterData);
		return jobPostingService.saveRecruiter(recruiterData);
	}
	
	
	@GetMapping("/recruiter")
	public List<RecruiterData> retrieveAllRecruiters() {
		log.info("Retrieve all recruiters called.");
		return jobPostingService.retrieveAllRecruiters();
		
	}
	
	
	@GetMapping("/recruiter/{recruiterId}")
	public RecruiterData retrieveRecruiterById(@PathVariable Long recruiterId) {
		log.info("Retrieving recruiter with ID=" + recruiterId);
		return jobPostingService.retrieveRecruiterById(recruiterId);
	}
	
	@DeleteMapping("/recruiter")
	public void deleteAllRecruiters() {
		log.info("Attempting to delete all recruiters.");
		throw new UnsupportedOperationException("Deleting all recruiters is not allowed.");
	}
	
	@DeleteMapping("/recruiter/{recruiterId}")
	public Map<String, String> deleteRecruiterById(@PathVariable Long recruiterId) {
		log.info("Deleting recruiter with ID=", recruiterId);
		
		jobPostingService.deleteRecruiterById(recruiterId);
		
		return Map.of("message", "Deletion of recruiter with ID=" + recruiterId + " was successful.");
	}
	
	@PostMapping("/recruiter/{recruiterId}/jobposting")
	@ResponseStatus(code = HttpStatus.CREATED)
	public JobPostingData saveJobPosting(@PathVariable Long recruiterId,
			@RequestBody JobPostingData jobPostingData) {
		log.info("Creating job posting {} for recruiter with ID={}",
				jobPostingData, recruiterId);
		return jobPostingService.saveJobPosting(recruiterId, jobPostingData);
	}
	
	@PostMapping("/jobposting/{jobId}/candidate")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CandidateData saveCandidate(@PathVariable Long jobId,
			@RequestBody CandidateData candidateData) {
		log.info("Create candidate {} for job posting with ID={}", candidateData, jobId);
		return jobPostingService.saveCandidate(candidateData, jobId);
	}
	
}
