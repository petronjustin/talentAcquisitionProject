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
	public class JobPostingData {
	private Long jobId;
	private String jobTitle;
	private String jobDepartment;
	private String jobLocation;
	private String jobDescription;
	private JobPostingRecruiter recruiter;
	private Set<CandidateResponse> candidates = new HashSet<>();
	
	
	public JobPostingData(JobPosting jobPosting) {
		jobId = jobPosting.getJobId();
		jobTitle = jobPosting.getJobTitle();
		jobDepartment = jobPosting.getJobDepartment();
		jobLocation = jobPosting.getJobLocation();
		jobDescription = jobPosting.getJobDescription();
		recruiter = new JobPostingRecruiter(jobPosting.getRecruiter());
				
			for(Candidate candidate : jobPosting.getCandidates()) {
			candidates.add(new CandidateResponse(candidate));
		}
	}
	
	
	@Data
	@NoArgsConstructor
	public static class JobPostingRecruiter {
		private Long recruiterId;
		private String recruiterName;
		private String recruiterEmail;
		private String hiringRegion;
		
		public JobPostingRecruiter(Recruiter recruiter) {
			recruiterId = recruiter.getRecruiterId();
			recruiterName = recruiter.getRecruiterName();
			recruiterEmail = recruiter.getRecruiterEmail();
			hiringRegion = recruiter.getHiringRegion();
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class CandidateResponse {
		private Long candidateId;
		private String candidateName;
		
		public CandidateResponse(Candidate candidate) {
			candidateId = candidate.getCandidateId();
			candidateName = candidate.getCandidateName();
		}
	}
}
