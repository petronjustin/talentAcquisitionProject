package talent.acquisition.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import talent.acquisition.entity.Candidate;

public interface CandidateDao extends JpaRepository<Candidate, Long> {

	Optional<Candidate> findByCandidateEmail(String candidateEmail);

//	Set<Candidate> findAllByCandidateIn(Set<CandidateResponse> candidates);



}
