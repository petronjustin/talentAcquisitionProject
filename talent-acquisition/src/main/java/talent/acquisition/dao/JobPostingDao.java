package talent.acquisition.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import talent.acquisition.entity.JobPosting;

public interface JobPostingDao extends JpaRepository<JobPosting, Long> {

}
