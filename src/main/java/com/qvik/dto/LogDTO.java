package com.qvik.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class LogDTO {

	private int id;
	private String name;
	private int maxAge;
	private int logId;
	private String message;
	private boolean deleted;
	private Timestamp createdOn;
	
}
