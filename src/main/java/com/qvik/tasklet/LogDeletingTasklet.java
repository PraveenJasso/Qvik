package com.qvik.tasklet;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.qvik.Application;
import com.qvik.model.Log;
import com.qvik.repository.LogRepository;

public class LogDeletingTasklet implements Tasklet, InitializingBean {
	
	private static final Logger logger = LogManager.getLogger(Application.class.getName());
	
	private int maxAge;

	private LogRepository logRepository;
	
	public LogDeletingTasklet (LogRepository logRepository,int maxAge){
		this.logRepository=logRepository;
		this.maxAge=maxAge;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		logger.info("****** started execute ******* ");
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(new Timestamp(System.currentTimeMillis()));
	    calendar.add(Calendar.SECOND, maxAge);
	    Date date = calendar.getTime();
		Timestamp createdOn = new Timestamp(date.getTime());
		logger.info("logRepository ?????????? " + maxAge);
		List<Log> logs = logRepository.getLogsByCreatedOnAndDeleted(createdOn);
		if(logs != null && logs.size() > 0) {
			logs.forEach(log ->{
				log.setDeleted(true);
			});
			logRepository.saveAll(logs);
		}
		logger.info("****** End execute ******* ");
		return RepeatStatus.FINISHED;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("afterPropertiesSet **************************************** :" + new Date());
		Assert.notNull(maxAge, "maxAge must be set");
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

}
