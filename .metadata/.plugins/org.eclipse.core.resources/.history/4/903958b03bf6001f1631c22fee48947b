package talent.acquisition.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class JobPosting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobId;
	
	
	private String jobTitle;
	private String jobDepartment;
	private String jobLocation;
	private String jobDescription;
	
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "job_candidate", joinColumns = @JoinColumn(name = "job_id"),
	inverseJoinColumns = @JoinColumn(name = "candidate_id"))
	private Set<Candidate> candidates = new HashSet<>();
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "recruiter_Id", nullable = false)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Recruiter recruiter;
}
