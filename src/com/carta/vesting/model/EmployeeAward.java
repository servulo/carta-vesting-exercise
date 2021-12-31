package com.carta.vesting.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeAward {

	private String employeeId;
	private String employeeName;
	private String awardId;

	@Override
	public int hashCode() {
		return Objects.hash(awardId, employeeId, employeeName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeAward other = (EmployeeAward) obj;
		return Objects.equals(awardId, other.awardId) && Objects.equals(employeeId, other.employeeId)
				&& Objects.equals(employeeName, other.employeeName);
	}

	@Override
	public String toString() {
		return employeeId + ", " + employeeName + ", " + awardId;
	}

}