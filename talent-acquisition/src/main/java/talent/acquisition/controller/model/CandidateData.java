package talent.acquisition.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import talent.acquisition.entity.Candidate;


	@Data
	@NoArgsConstructor
public class CandidateData {
		private Long candidateId;
		private String candidateName;
		private String candidateEmail;
		
		public CandidateData(Candidate candidate) {
			candidateId = candidate.getCandidateId();
			candidateName = candidate.getCandidateName();
			candidateEmail = candidate.getCandidateEmail();
		}
}
