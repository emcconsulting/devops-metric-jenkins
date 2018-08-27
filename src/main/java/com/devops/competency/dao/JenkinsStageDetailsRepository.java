package com.devops.competency.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devops.competency.entity.JenkinsCompoiteKey;
import com.devops.competency.entity.JenkinsStageDetails;

public interface JenkinsStageDetailsRepository extends JpaRepository<JenkinsStageDetails, JenkinsCompoiteKey> {

	String query = "SELECT Job_id, job_name, count(job_run_date) as frequency_perday, DATE(job_run_date) as job_date FROM "
			+ "devops_statics_jenkins.jenkins_job_details where DATE(job_run_date) = DATE(:date) GROUP BY DATE(job_run_date),job_id;";

	@Query(value = query, nativeQuery = true)
	List<Object> getJobFrequency(@Param("date") Date date);
}
