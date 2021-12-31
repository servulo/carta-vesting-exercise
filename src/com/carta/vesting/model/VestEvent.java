package com.carta.vesting.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class VestEvent {

	private String type;
	private EmployeeAward employeeAward;
	private LocalDate date;
	private BigDecimal quantity;

	public VestEvent(String type, String employeeName, String employeeId, String awardId, LocalDate date,
			BigDecimal quantity) {
		this.type = type;
		this.employeeAward = new EmployeeAward(employeeName, employeeId, awardId);
		this.date = date;
		this.quantity = quantity;
	}

}