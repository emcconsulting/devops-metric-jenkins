package com.devops.competency.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devops.competency.entity.JobFrequencyCompositeKey;
import com.devops.competency.entity.JobFrequencyEntity;

public interface JobFrequencyRepository extends JpaRepository<JobFrequencyEntity, JobFrequencyCompositeKey> {

}
