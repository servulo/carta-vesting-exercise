package com.carta.vesting;

import java.time.LocalDate;

import com.carta.vesting.handler.AwardHandler;
import com.carta.vesting.handler.EventHandler;

public class VestingScheduler {

	public static void init(String fileName, LocalDate targetDate, int precision) {
		
		EventHandler eventHandler = new EventHandler();
		
		eventHandler.load(fileName, precision);
		
		AwardHandler awardHandler = new AwardHandler();
		
		awardHandler.process(eventHandler.getEventsList(), targetDate);
		
		awardHandler.print();
		
	}

}