package com.qvik.repository;



import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qvik.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
	
	@Query("FROM Log WHERE createdOn < :createdOn and deleted is false ")
	List<Log> getLogsByCreatedOnAndDeleted(Timestamp createdOn);
	
	@Query("FROM Log WHERE deleted is false ")
	List<Log> getLogsByDeleted();
}
