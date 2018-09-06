package com.devops.competency.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devops.competency.entity.JobMetaData;

public interface JobMetaDataRepository extends JpaRepository<JobMetaData, String> {

}
