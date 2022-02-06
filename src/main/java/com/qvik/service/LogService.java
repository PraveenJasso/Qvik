package com.qvik.service;

import java.util.List;

import com.qvik.dto.LogDTO;

public interface LogService {
	
	void addLogMessage(LogDTO log);
	List<LogDTO> getAllLogs();
}
