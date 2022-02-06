package com.qvik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qvik.dto.LogDTO;
import com.qvik.service.LogService;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/")
public class LogController {

	@Autowired
	LogService logService;

	@GetMapping
	public ResponseEntity<List<LogDTO>> getAllLogs() throws Exception {
		List<LogDTO> logs = this.logService.getAllLogs();
		return new ResponseEntity<List<LogDTO>>(logs, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<HttpStatus> saveLog(@RequestBody LogDTO logDTO) throws Exception {
		this.logService.addLogMessage(logDTO);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
