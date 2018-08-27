package com.devops.competency.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devops.competency.entity.JenkinsCompoiteKey;
import com.devops.competency.entity.JenkinsStageDetails;

public interface JenkinsStageDetailsRepository extends JpaRepository<JenkinsStageDetails, JenkinsCompoiteKey> {

}
