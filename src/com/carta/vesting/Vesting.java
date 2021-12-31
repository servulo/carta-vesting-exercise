package com.carta.vesting;

import java.time.LocalDate;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name = "java -jar vesting.jar", version = "Vesting 1.0", mixinStandardHelpOptions = true)
public class Vesting implements Runnable {

	@Parameters(index = "0", arity = "1", description = ".csv file containing the vesting events.", paramLabel = "<File Name>")
	String fileName;

	@Parameters(index = "1", arity = "1", description = "Target date (YYYY-DD-MM).", type = LocalDate.class, paramLabel = "<Target Date>")
	LocalDate targetDate;

	@Parameters(index = "2", arity = "1", description = "Digits of precision (DEFAULT=0).", defaultValue = "0", paramLabel = "<Precision>")
	int precision;

	@Spec
	CommandSpec spec;

	@Override
	public void run() {
		validate();
		VestingScheduler.init(fileName, targetDate, precision);
	}

	public static void main(String[] args) {
		new CommandLine(new Vesting()).execute(args);
	}

	private void validate() {
		if (precision < 0 || precision > 6) {
			throw new ParameterException(spec.commandLine(), "Precision must be between 0 and 6 (DEFAULT=0).");
		}

	}

}
