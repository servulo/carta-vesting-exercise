package com.carta.vesting.handler;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.carta.vesting.model.VestEvent;

import lombok.Getter;

@Getter
public class EventHandler {

	private List<VestEvent> eventsList = new ArrayList<>();

	public void load(String fileName, int precision) {
		Pattern pattern = Pattern.compile(",");
		try (Stream<String> stream = Files.lines(Paths.get(fileName)).parallel()) {
			eventsList = stream.map(line -> {
				
				String[] arr = pattern.split(line);
				
				return new VestEvent(arr[0], 
						             arr[1], 
						             arr[2], 
						             arr[3], 
						             LocalDate.parse(arr[4]), 
						             new BigDecimal(arr[5]).setScale(precision, RoundingMode.HALF_DOWN));
				
			}).collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("IO Exception. Please, check the filename.");
		}
	}

}