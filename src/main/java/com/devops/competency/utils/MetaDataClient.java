package com.devops.competency.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.devops.competency.metadata.MetaData;

@Component
public class MetaDataClient {

	private Logger logger = LoggerFactory.getLogger(MetaDataClient.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${devops.metadat_aapp.url}")
	private String metadataUri;

	@SuppressWarnings("unchecked")
	public List<MetaData> getMetadata() {
		List<MetaData> metadata = new ArrayList<MetaData>();
		try {
			metadata = restTemplate.getForObject(metadataUri, ArrayList.class);
		} catch (Exception e) {
			logger.error("error while fetching metadata details :: ", e);
		}
		return metadata;
	}
}
