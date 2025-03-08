package talent.acquisition.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import talent.acquisition.entity.Recruiter;

public interface RecruiterDao extends JpaRepository<Recruiter, Long> {

	Optional<Recruiter> findByRecruiterEmail(String recruiterEmail);

}
