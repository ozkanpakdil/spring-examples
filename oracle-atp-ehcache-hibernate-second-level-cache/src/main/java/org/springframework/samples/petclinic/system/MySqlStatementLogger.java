package org.springframework.samples.petclinic.system;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.hibernate.engine.jdbc.spi.SqlStatementLogger;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class MySqlStatementLogger {
//	@Around("callAt( org.hibernate.engine.jdbc.spi.SqlStatementLogger.logStatement() )")
	public void logStatement(String statement) {
		// for now just assume a DML log for formatting
//		logStatement(statement, FormatStyle.BASIC.getFormatter());
		System.out.println("hello");
	}

	@Around("execution(* org.hibernate.engine.jdbc.spi.SqlStatementLogger.*(..))")
	public Object invoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("aspecct");
		return null;
	}

	public void logSlowQuery(String sql, long startTimeNanos) {
		if (startTimeNanos <= 0) {
			throw new IllegalArgumentException("startTimeNanos [" + startTimeNanos + "] should be greater than 0!");
		}

		long queryExecutionMillis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTimeNanos);

		String logData = "SlowQuery: " + queryExecutionMillis + " milliseconds. SQL: '" + sql + "'";
		log.info(logData);
	}
}
