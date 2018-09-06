package com.devops.competency.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devops.competency.entity.JobFrequency;
import com.devops.competency.entity.JobFrequencyCompositeKey;

public interface JobFrequencyRepository extends JpaRepository<JobFrequency, JobFrequencyCompositeKey> {

	@Query(value = "SELECT j from JOB_FREQUENCY j where j.jobFrequencyCompositeKey.jobId = :jobId")
	public List<JobFrequency> getJobDetails(@Param("jobId") String jobId);

	@Query(value = "SELECT SUM(frequencyPerDay) from JOB_FREQUENCY j where j.jobFrequencyCompositeKey.jobId = :jobId and  j.jobFrequencyCompositeKey.jobDate >:yearBackDate")
	public Integer getJobDetailsForYears(@Param("jobId") String jobId, @Param("yearBackDate") Date yearBackDate);
}
