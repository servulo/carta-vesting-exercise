package com.carta.vesting.handler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.carta.vesting.model.EmployeeAward;
import com.carta.vesting.model.VestEvent;

import lombok.Getter;

@Getter
public class AwardHandler {

	private Map<EmployeeAward, BigDecimal> awards = new HashMap<>();

	public Map<EmployeeAward, BigDecimal> getAwards() {
		return awards;
	}

	public void process(List<VestEvent> eventsList, LocalDate targetDate) {
		
		eventsList.stream().forEach(event -> {
			
			if (!event.getDate().isAfter(targetDate)) {
				if (awards.containsKey(event.getEmployeeAward())) {
					BigDecimal value = awards.get(event.getEmployeeAward());
					awards.put(event.getEmployeeAward(),
							   event.getType().equalsIgnoreCase("CANCEL") 
							   		? value.subtract(event.getQuantity())
							   		: event.getQuantity().add(value));
				} else {
					awards.put(event.getEmployeeAward(), event.getQuantity());
				}
			} else {
				if (!awards.containsKey(event.getEmployeeAward())) {
					awards.put(event.getEmployeeAward(), BigDecimal.ZERO);
				}
			}
		});

	}

	public void print() {
		
		awards.entrySet()
		    .stream()
			.sorted((e1, e2) -> e1.getKey().getEmployeeId().compareTo(e2.getKey().getEmployeeId()))
			.sorted((e1, e2) -> e1.getKey().getAwardId().compareTo(e2.getKey().getAwardId()))
			.forEach(e -> System.out.println(e.getKey() + ", " + e.getValue()));
		
	}

}
