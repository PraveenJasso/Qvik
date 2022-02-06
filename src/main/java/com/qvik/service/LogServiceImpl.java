package com.qvik.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qvik.dto.LogDTO;
import com.qvik.model.Log;
import com.qvik.repository.LogRepository;

import ma.glasnost.orika.MapperFacade;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogRepository logRepository;
	@Autowired
	private transient MapperFacade mapperFacade;
	@Value("${spring.log.max.age}")
	private int maxAge;

	@Override
	public void addLogMessage(LogDTO logDTO) {
		Log log = mapperFacade.map(logDTO, Log.class);
		Random random = new Random();
		log.setId(random.nextInt(50));
		log.setCreatedOn(new Timestamp(System.currentTimeMillis()));
		log.setMaxAge(maxAge);
		logRepository.save(log);
	}

	@Override
	public List<LogDTO> getAllLogs() {
		List<Log> logs = logRepository.getLogsByDeleted();
		List<LogDTO> result = new ArrayList<>();
		if (logs == null || logs.size() <= 0) {
			return result;
		}
		result = logs.stream().map(log -> new ObjectMapper().convertValue(log, LogDTO.class))
				.collect(Collectors.toList());
		return result;
	}
}
