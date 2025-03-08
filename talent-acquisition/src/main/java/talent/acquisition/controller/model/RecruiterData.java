package talent.acquisition.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import talent.acquisition.entity.Candidate;
import talent.acquisition.entity.JobPosting;
import talent.acquisition.entity.Recruiter;

@Data
@NoArgsConstructor
public class RecruiterData {
	

	private Long recruiterId;
	private String recruiterName;
	private String recruiterEmail;
	private String hiringRegion;
	private Set<JobPostingResponse> jobPostings = new HashSet<>();
	
	public RecruiterData(Recruiter recruiter) {
		recruiterId = recruiter.getRecruiterId();
		recruiterName = recruiter.getRecruiterName();
		recruiterEmail = recruiter.getRecruiterEmail();
		hiringRegion = recruiter.getHiringRegion();
		
		for(JobPosting jobPosting : recruiter.getJobPostings()) {
			jobPostings.add(new JobPostingResponse(jobPosting));
		}
		
	}

	
	@Data
	@NoArgsConstructor
	static class JobPostingResponse {
		private Long jobId;
		private String jobTitle;
		private String jobDepartment;
		private String jobLocation;
		private String jobDescription;
		private Set<String> candidates = new HashSet<>();
		
		JobPostingResponse(JobPosting jobPosting) {
			jobId = jobPosting.getJobId();
			jobTitle = jobPosting.getJobTitle();
			jobDepartment = jobPosting.getJobDepartment();
			jobLocation = jobPosting.getJobLocation();
			jobDescription = jobPosting.getJobDescription();
			
			for(Candidate candidate : jobPosting.getCandidates()) {
				candidates.add(candidate.getCandidateName());
			}
		}
	}
}
