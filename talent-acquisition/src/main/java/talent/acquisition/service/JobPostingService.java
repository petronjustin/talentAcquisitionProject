package talent.acquisition.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import talent.acquisition.controller.model.CandidateData;
import talent.acquisition.controller.model.JobPostingData;
import talent.acquisition.controller.model.RecruiterData;
import talent.acquisition.dao.CandidateDao;
import talent.acquisition.dao.JobPostingDao;
import talent.acquisition.dao.RecruiterDao;
import talent.acquisition.entity.Candidate;
import talent.acquisition.entity.JobPosting;
import talent.acquisition.entity.Recruiter;

@Service
public class JobPostingService {
	@Autowired
	private RecruiterDao recruiterDao;
	
	@Autowired
	private CandidateDao candidateDao;
	
	@Autowired
	private JobPostingDao jobPostingDao;
	
	
	
	@Transactional(readOnly = false)
	public RecruiterData saveRecruiter(RecruiterData recruiterData) {
		Long recruiterId = recruiterData.getRecruiterId();
		Recruiter recruiter = findOrCreateRecruiter(recruiterId, recruiterData.getRecruiterEmail());
		
		setFieldsInRecruiter(recruiter, recruiterData);
		return new RecruiterData(recruiterDao.save(recruiter));
	}

	private void setFieldsInRecruiter(Recruiter recruiter, RecruiterData recruiterData) {
		recruiter.setRecruiterEmail(recruiterData.getRecruiterEmail());
		recruiter.setRecruiterName(recruiterData.getRecruiterName());
		recruiter.setHiringRegion(recruiterData.getHiringRegion());
		
	}

	private Recruiter findOrCreateRecruiter(Long recruiterId, String recruiterEmail) {
		Recruiter recruiter;
		if(Objects.isNull(recruiterId)) {
			
			Optional<Recruiter> opRecruiter = recruiterDao.findByRecruiterEmail(recruiterEmail); 
			
			if(opRecruiter.isPresent()) {
				throw new DuplicateKeyException("Recruiter with email " + recruiterEmail + "already exists.");
			}
			
			
			recruiter = new Recruiter();
		}
		else {
			recruiter = findRecruiterById(recruiterId);
		}
		return recruiter;
	}



	private Recruiter findRecruiterById(Long recruiterId) {
		
		
		return recruiterDao.findById(recruiterId).orElseThrow(() -> new NoSuchElementException
				("Recruiter with ID=" + recruiterId + " was not found.") );
	}
	
	@Transactional(readOnly = true)
	public List<RecruiterData> retrieveAllRecruiters() {
		List<Recruiter> recruiters = recruiterDao.findAll();
		List<RecruiterData> response = new LinkedList<>();
		
		for(Recruiter recruiter : recruiters) {
			response.add(new RecruiterData(recruiter));
		}
		return response;
	}

	public RecruiterData retrieveRecruiterById(Long recruiterId) {
		Recruiter recruiter = findRecruiterById(recruiterId);
		return new RecruiterData(recruiter);
	}

	@Transactional(readOnly = false)
	public void deleteRecruiterById(Long recruiterId) {
		Recruiter recruiter = findRecruiterById(recruiterId);
		recruiterDao.delete(recruiter);
	}
	
	@Transactional(readOnly = false)
	public JobPostingData saveJobPosting(Long recruiterId, JobPostingData jobPostingData) {
		Recruiter recruiter = findRecruiterById(recruiterId);
		
	
		
		JobPosting jobPosting = findOrCreateJobPosting(jobPostingData.getJobId());
		
		setJobPostingFields(jobPosting, jobPostingData);
		
		jobPosting.setRecruiter(recruiter);
		recruiter.getJobPostings().add(jobPosting);
		
		
		JobPosting dbJobPosting = jobPostingDao.save(jobPosting);
		return new JobPostingData(dbJobPosting);
	}

	private void setJobPostingFields(JobPosting jobPosting, JobPostingData jobPostingData) {
		jobPosting.setJobTitle(jobPostingData.getJobTitle());
		jobPosting.setJobDescription(jobPostingData.getJobDescription());
		jobPosting.setJobLocation(jobPostingData.getJobLocation());
		jobPosting.setJobDepartment(jobPostingData.getJobDepartment());
		
		
	}

	private JobPosting findOrCreateJobPosting(Long jobId) {
		JobPosting jobPosting;
		
		if(Objects.isNull(jobId)) {
			jobPosting = new JobPosting();
		}
		else {
			jobPosting = findJobPostingById(jobId);
		}
		
		return jobPosting;
}

	private JobPosting findJobPostingById(Long jobId) {
		return jobPostingDao.findById(jobId).orElseThrow(() -> 
		new NoSuchElementException("Job posting with ID=" + jobId + " does not exist."));
	}
@Transactional(readOnly = false)
	public CandidateData saveCandidate(CandidateData candidateData, Long jobId) {
		
		
			JobPosting jobPosting = findJobPostingById(jobId);
					
			Candidate candidate = findOrCreateCandidate(candidateData.getCandidateId(), candidateData.getCandidateEmail());
			
			
			setCandidateFields(candidate, candidateData);
			
			candidate.getJobPostings().add(jobPosting);
			jobPosting.getCandidates().add(candidate);
			
			
			Candidate dbCandidate = candidateDao.save(candidate);
			return new CandidateData(dbCandidate);
		}

private void setCandidateFields(Candidate candidate, CandidateData candidateData) {
	candidate.setCandidateEmail(candidateData.getCandidateEmail());
	candidate.setCandidateName(candidateData.getCandidateName());
	candidate.setCandidateId(candidateData.getCandidateId());
	
}

private Candidate findOrCreateCandidate(Long candidateId, String candidateEmail) {
	
	Candidate candidate;
	if(Objects.isNull(candidateId)) {
		
		Optional<Candidate> opCandidate = candidateDao.findByCandidateEmail(candidateEmail); 
		
		if(opCandidate.isPresent()) {
			throw new DuplicateKeyException("Candidate with email " + candidateEmail + "already exists.");
		}
		
		
		candidate = new Candidate();
	}
	else {
		candidate = findCandidateById(candidateId);
	}
	return candidate;
}

private Candidate findCandidateById(Long candidateId) {
	return candidateDao.findById(candidateId).orElseThrow(() -> new NoSuchElementException
			("Candidate with ID=" + candidateId + " was not found.") );
}
}

	


	
