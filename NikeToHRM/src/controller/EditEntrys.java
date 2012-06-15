package controller;

import java.util.LinkedList;

public class EditEntrys {

	private String duration;
	private String maxBPM;
	private String date;
	private String startTime;
	private String sMode;
	private String interval;

	private LinkedList<Character> inputString;

	public void activityType(TargetWord targetWord) {
		StringBuffer buffer = new StringBuffer();
		for (int counter = targetWord.getEndPosition() + 4; inputString
				.get(counter) != '"'; counter++) {
			buffer.append(inputString.get(counter));
		}
		if (buffer.toString().equals("RUN")) {
			sMode = "100000000";
		} else {
			sMode = "000000000";
		}
	}

	public void startTimeUtc(TargetWord targetWord) {
		StringBuffer buffer = new StringBuffer();
		int counter;
		for (counter = targetWord.getEndPosition() + 4; inputString
				.get(counter) != 'T'; counter++) {
			if (inputString.get(counter) != '-') {
				buffer.append(inputString.get(counter));
			}
		}
		date = buffer.toString();
		buffer = new StringBuffer();
		for (counter = counter + 1; inputString.get(counter) != '+'; counter++) {
			buffer.append(inputString.get(counter));
		}
		buffer.append(".0");
		startTime = buffer.toString();
	}

	public void duration(TargetWord targetWord) {
		StringBuffer buffer = new StringBuffer();
		for (int counter = targetWord.getEndPosition() + 3; inputString
				.get(counter) != ','; counter++) {
			buffer.append(inputString.get(counter));
		}
		
		Integer value = new Integer(Integer.valueOf(buffer.toString()));
		int hours = value / 3600000;
		int minutes = (value - (hours * 3600000)) / 60000;
		int seconds = (value - (hours * 3600000) - (minutes * 60000)) / 1000;
		
		buffer = new StringBuffer();

		if (String.valueOf(hours).length() == 1) {
			buffer.append("0"+hours);
		} else {
			buffer.append(hours);
		}
		buffer.append(":");
		if (String.valueOf(minutes).length() == 1) {
			buffer.append("0"+minutes);
		} else {
			buffer.append(minutes);
		}
		buffer.append(":");
		if (String.valueOf(seconds).length() == 1) {
			buffer.append("0"+seconds);
		} else {
			buffer.append(seconds);
		}
		buffer.append(".0");
		
		this.duration = buffer.toString();
	}

	public void maximumHeartRate(TargetWord targetWord) {
		StringBuffer buffer = new StringBuffer();
		for (int counter = targetWord.getEndPosition() + 3; inputString
				.get(counter) != '.'; counter++) {
			buffer.append(inputString.get(counter));
		}
		
		this.maxBPM = buffer.toString();
	}

	public void intervalMetric(TargetWord targetWord) {
		StringBuffer buffer = new StringBuffer();
		for (int counter = targetWord.getEndPosition() + 3; inputString
				.get(counter) != ','; counter++) {
			buffer.append(inputString.get(counter));
		}
		
		this.interval = buffer.toString();
	}

	public void distance(TargetWord targetWord) {
		
	}

	public void heartrate(TargetWord targetWord) {

	}

	public void setInputString(LinkedList<Character> inputString) {
		this.inputString = inputString;
	}

}
