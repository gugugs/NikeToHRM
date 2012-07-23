package controller;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
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
	private ArrayList<Character> inputString;
	private ArrayList<String> lat;
	private ArrayList<String> lon;
	private ArrayList<String> ele;
	private ArrayList<String> speed;
	private ArrayList<String> time;

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
		for (int counter = targetWord.getEndPosition() + 1; inputString
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
		for (counter = 0; rawData.get(counter) == 0; counter++)
			;
		for (; counter + 1 < rawData.size(); counter++) {
			if (rawData.get(counter) == 0) {
				int start = counter - 1;
				while (rawData.get(counter) == 0) {
					if (counter + 1 == rawData.size()) {
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
					rawData.set(counter, rawData.get(start)
							+ (difference * tempCounter));
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

	public void waypoints(TargetWord targetWord) {
		int upCounter = 1;
		StringBuffer sBuffer = new StringBuffer();
		this.lat = new ArrayList<String>();
		this.lon = new ArrayList<String>();
		this.ele = new ArrayList<String>();
		this.speed = new ArrayList<String>();
		this.time = new ArrayList<String>();

		for (int counter = targetWord.getEndPosition(); this.inputString
				.get(counter) != ']'; counter++) {
			Character currentCharacter = this.inputString.get(counter);
			if (Character.isDigit(currentCharacter)
					|| (currentCharacter == '.')) {
				sBuffer.append(currentCharacter);
			} else {
				if (sBuffer.length() != 0) {
					switch (upCounter) {
					case 1:
						this.lat.add(sBuffer.toString());
						upCounter++;
						sBuffer.setLength(0);
						break;
					case 2:
						this.lon.add(sBuffer.toString());
						upCounter++;
						sBuffer.setLength(0);
						break;
					case 3:
						this.ele.add(sBuffer.toString());
						upCounter = 1;
						sBuffer.setLength(0);
						break;
					default:
						break;
					}
				}
			}
		}

		Double lon1, lon2, lat1, lat2, dist, sindLat, sindLon, a, c, dLat, dLon;
		Double earthRadius = 63710.0;
		DecimalFormat dFormat;
		DecimalFormatSymbols dfSymbols;
		String speed;
		this.speed.add("0,00");
		for (int counter = 1; counter < this.lat.size(); counter++) {
			lon1 = Double.valueOf(this.lon.get(counter - 1));
			lon2 = Double.valueOf(this.lon.get(counter));
			lat1 = Double.valueOf(this.lat.get(counter - 1));
			lat2 = Double.valueOf(this.lat.get(counter));
			
		    dLat = lat2-lat1;
		    dLon = lon2-lon1;
		    sindLat = Math.sin(dLat / 2);
		    sindLon = Math.sin(dLon / 2);
		    a = Math.pow(sindLat, 2) + Math.pow(sindLon, 2) * Math.cos(lat1) * Math.cos(lat2);
		    c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		    dist = earthRadius * c;
			
			dfSymbols = DecimalFormatSymbols.getInstance();
			dfSymbols.setDecimalSeparator(',');
			dFormat = new DecimalFormat("0.00", dfSymbols);
			speed = dFormat.format(dist);
			this.speed.add(speed);
		}

		int hours = Integer.valueOf(this.startTime.substring(0, 2)) - 2;
		int minutes = Integer.valueOf(this.startTime.substring(3, 5));
		int seconds = Integer.valueOf(this.startTime.substring(6, 8));
		StringBuffer date = new StringBuffer();
		date.append(this.date.substring(0, 4) + "-"
				+ this.date.subSequence(4, 6) + "-"
				+ this.date.subSequence(6, 8));

		sBuffer = new StringBuffer();

		for (int counter = 0; counter < this.lat.size(); counter++) {
			sBuffer.append(date.toString() + "T");
			if (String.valueOf(hours).length() == 1) {
				sBuffer.append("0" + hours);
			} else {
				sBuffer.append(hours);
			}
			sBuffer.append(":");
			if (String.valueOf(minutes).length() == 1) {
				sBuffer.append("0" + minutes);
			} else {
				sBuffer.append(minutes);
			}
			sBuffer.append(":");
			if (String.valueOf(seconds).length() == 1) {
				sBuffer.append("0" + seconds);
			} else {
				sBuffer.append(seconds);
			}
			sBuffer.append("Z");

			this.time.add(sBuffer.toString());
			sBuffer.setLength(0);

			seconds++;

			if (seconds == 60) {
				seconds = 0;
				minutes++;
				if (minutes == 60) {
					minutes = 0;
					hours++;
				}
			}
		}
	}

	public void setInputString(ArrayList<Character> inputString) {
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

	public ArrayList<String> getLat() {
		return lat;
	}

	public void setLat(ArrayList<String> lat) {
		this.lat = lat;
	}

	public ArrayList<String> getLon() {
		return lon;
	}

	public void setLon(ArrayList<String> lon) {
		this.lon = lon;
	}

	public ArrayList<String> getSpeed() {
		return speed;
	}

	public void setSpeed(ArrayList<String> speed) {
		this.speed = speed;
	}

	public ArrayList<String> getTime() {
		return time;
	}

	public void setTime(ArrayList<String> time) {
		this.time = time;
	}

	public ArrayList<String> getEle() {
		return ele;
	}

	public void setEle(ArrayList<String> ele) {
		this.ele = ele;
	}
}
