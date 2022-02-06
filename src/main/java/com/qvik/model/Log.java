package com.qvik.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Entity
@Table(name = "log")
public class Log {

	@Id
	private int id;
	private String name;
	private int maxAge;
	private int logId;
	private String message;
	private boolean deleted;
	private Timestamp createdOn;
	
}
