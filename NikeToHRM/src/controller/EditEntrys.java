package controller;

import java.util.LinkedList;

public class EditEntrys {

	private String duration;
	private String maxBPM;
	private String date;
	private String startTime;
	private String sMode;
	private String interval;
	private LinkedList<String> distanceValues;
	private LinkedList<String> heartRateValues;
	private LinkedList<Character> inputString;

	public void activityType(TargetWord targetWord) {
		StringBuffer buffer = new StringBuffer();
		for (int counter = targetWord.getEndPosition() + 4; inputString
				.get(counter) != '"'; counter++) {
			buffer.append(inputString.get(counter));
		}
		if (buffer.toString().equals("RUN")) {
			setsMode("100000000");
		} else {
			setsMode("000000000");
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
		setDate(buffer.toString());
		buffer = new StringBuffer();
		for (counter = counter + 1; inputString.get(counter) != '+'; counter++) {
			buffer.append(inputString.get(counter));
		}
		buffer.append(".0");
		setStartTime(buffer.toString());
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
			buffer.append("0" + hours);
		} else {
			buffer.append(hours);
		}
		buffer.append(":");
		if (String.valueOf(minutes).length() == 1) {
			buffer.append("0" + minutes);
		} else {
			buffer.append(minutes);
		}
		buffer.append(":");
		if (String.valueOf(seconds).length() == 1) {
			buffer.append("0" + seconds);
		} else {
			buffer.append(seconds);
		}
		buffer.append(".0");

		this.setDuration(buffer.toString());
	}

	public void maximumHeartRate(TargetWord targetWord) {
		StringBuffer buffer = new StringBuffer();
		for (int counter = targetWord.getEndPosition() + 3; inputString
				.get(counter) != '.'; counter++) {
			buffer.append(inputString.get(counter));
		}

		this.setMaxBPM(buffer.toString());
	}

	public void intervalMetric(TargetWord targetWord) {
		StringBuffer buffer = new StringBuffer();
		for (int counter = targetWord.getEndPosition() + 3; inputString
				.get(counter) != ','; counter++) {
			buffer.append(inputString.get(counter));
		}

		this.setInterval(buffer.toString());
	}

	public void distance(TargetWord targetWord) {
		StringBuffer buffer = new StringBuffer();
		LinkedList<Double> rawData = new LinkedList<Double>();
		int counter;
		for (counter = targetWord.getEndPosition() + 12; inputString
				.get(counter) != ']'; counter++) {
			if (inputString.get(counter) == ',') {
				rawData.add(Double.valueOf(buffer.toString()));
				buffer.setLength(0);
			} else if (inputString.get(counter + 1) == ']') {
				buffer.append(inputString.get(counter));
				rawData.add(Double.valueOf(buffer.toString()));
				buffer.setLength(0);
				break;
			} else {
				buffer.append(inputString.get(counter));
			}
		}

		this.distanceValues = new LinkedList<String>();

		distanceValues
				.add(String.valueOf(Math.round((rawData.get(0) - 0) * 3600)));
		for (counter = 1; counter < rawData.size(); counter++) {
			distanceValues
					.add(String.valueOf(Math.round((rawData.get(counter) - rawData
							.get(counter - 1)) * 3600)));
		}
	}

	public void heartrate(TargetWord targetWord) {
		StringBuffer buffer = new StringBuffer();
		LinkedList<Integer> rawData = new LinkedList<Integer>();
		int counter;
		for (counter = targetWord.getEndPosition() + 12; inputString
				.get(counter) != ']'; counter++) {
			if (inputString.get(counter) == ',') {
				rawData.add(Integer.valueOf(buffer.toString()));
				buffer.setLength(0);
			} else if (inputString.get(counter + 1) == ']') {
				buffer.append(inputString.get(counter));
				rawData.add(Integer.valueOf(buffer.toString()));
				buffer.setLength(0);
				break;
			} else {
				buffer.append(inputString.get(counter));
			}
		}
		
		boolean breakBoolean = false;
		for (counter = 0; rawData.get(counter) == 0; counter++);
		for (; counter+1 < rawData.size(); counter++) {
			if (rawData.get(counter) == 0) {
				int start = counter - 1;
				while (rawData.get(counter) == 0) {
					if (counter+1 == rawData.size()) {
						breakBoolean = true;
						break;
					}
					counter++;
				}
				if (breakBoolean == true) {
					break;
				}
				int end = counter;

				Integer difference = Integer
						.valueOf(String.valueOf(Math.round(Double.valueOf(Double
								.valueOf((rawData.get(end) - rawData.get(start)))
								/ Double.valueOf((end - start))))));

				int tempCounter = 1;
				for (counter = start + 1; counter != end; counter++) {
					rawData.set(counter, rawData.get(start) + (difference*tempCounter));
					tempCounter++;
				}
				
				counter = end;
			}
		}
		
		this.heartRateValues = new LinkedList<String>();
		for (counter = 0; counter < rawData.size(); counter++) {
			heartRateValues.add(String.valueOf(rawData.get(counter)));
		}
	}

	public void setInputString(LinkedList<Character> inputString) {
		this.inputString = inputString;
	}

	public String getMaxBPM() {
		return maxBPM;
	}

	public void setMaxBPM(String maxBPM) {
		this.maxBPM = maxBPM;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getsMode() {
		return sMode;
	}

	public void setsMode(String sMode) {
		this.sMode = sMode;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public LinkedList<String> getDistanceValues() {
		return distanceValues;
	}

	public void setDistanceValues(LinkedList<String> distanceValues) {
		this.distanceValues = distanceValues;
	}

	public LinkedList<String> getHeartRateValues() {
		return heartRateValues;
	}

	public void setHeartRateValues(LinkedList<String> heartRateValues) {
		this.heartRateValues = heartRateValues;
	}

}
