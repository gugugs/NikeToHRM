package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class HRMBuilder {

	private ArrayList<Character> inputString;
	private HashMap<String, TargetWord> targetWords;
	private EditEntrys editEntry;
	private boolean heartRateData;
	private boolean distanceData;
	private boolean gpsData;

	public HRMBuilder() {
		this.inputString = new ArrayList<Character>();
		this.targetWords = new HashMap<String, TargetWord>();

		this.heartRateData = false;
		this.distanceData = false;
		this.gpsData = false;

		this.targetWords.put("activityType", new TargetWord("activityType"));
		this.targetWords.put("startTimeUtc", new TargetWord("startTimeUtc"));
		this.targetWords.put("duration", new TargetWord("duration"));
		this.targetWords.put("maximumHeartRate", new TargetWord(
				"maximumHeartRate"));
		this.targetWords
				.put("intervalMetric", new TargetWord("intervalMetric"));
		this.targetWords.put("distance",
				new TargetWord("\"type\":\"DISTANCE\""));
		this.targetWords.put("heartrate", new TargetWord(
				"\"type\":\"HEARTRATE\""));
		this.targetWords.put("waypoints", new TargetWord("\"waypoints\""));

		this.editEntry = new EditEntrys();
	}

	public boolean getDataFromFile(String filePath) {
		this.inputString = new ArrayList<Character>();
		try {
			if (!(filePath.toCharArray()[filePath.length() - 1] == 'l'
					&& filePath.toCharArray()[filePath.length() - 2] == 'm'
					&& filePath.toCharArray()[filePath.length() - 3] == 't' && filePath
						.toCharArray()[filePath.length() - 4] == 'h')) {

				if (!(filePath.toCharArray()[filePath.length() - 1] == 'm'
						&& filePath.toCharArray()[filePath.length() - 2] == 't' && filePath
							.toCharArray()[filePath.length() - 3] == 'h')) {
					return false;
				}
			}
			File inputFile = new File(filePath);
			FileInputStream FIStream = new FileInputStream(inputFile);
			BufferedReader BReader = new BufferedReader(new InputStreamReader(
					FIStream));

			String strLine;
			char[] strLineArray;
			while ((strLine = BReader.readLine()) != null) {
				strLineArray = strLine.toCharArray();
				for (int counter = 0; counter < strLine.length(); counter++) {
					inputString.add(strLineArray[counter]);
				}
			}
			strLine = null;
			FIStream.close();
			this.editEntry.setInputString(this.inputString);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean findTargetWords() {
		int possibleCounter = 0;
		Character currentCharacter;
		boolean wordComplete;
		this.heartRateData = false;
		this.gpsData = false;
		this.distanceData = false;
		for (int counter = 0; counter < inputString.size(); counter++) {
			currentCharacter = inputString.get(counter);

			for (Entry<String, TargetWord> element : targetWords.entrySet()) {
				if (element.getValue().getCurrentRightLetter() == currentCharacter
						&& element.getValue().getFindStatus() == false) {
					wordComplete = element.getValue()
							.increaseCurrentRightLetterNumber();
					if (wordComplete) {
						element.getValue().setStartPosition(
								counter - element.getValue().getWord().length()
										+ 1);
						element.getValue().setEndPosition(counter);

						if (element.getKey().equals("activityType")) {
							possibleCounter++;
							editEntry.activityType(element.getValue());
						} else if (element.getKey().equals("startTimeUtc")) {
							possibleCounter++;
							editEntry.startTimeUtc(element.getValue());
						} else if (element.getKey().equals("duration")) {
							possibleCounter++;
							editEntry.duration(element.getValue());
						} else if (element.getKey().equals("maximumHeartRate")) {
							editEntry.maximumHeartRate(element.getValue());
						} else if (element.getKey().equals("intervalMetric")) {
							possibleCounter++;
							editEntry.intervalMetric(element.getValue());
						} else if (element.getKey().equals("distance")) {
							editEntry.distance(element.getValue());
							this.distanceData = true;
						} else if (element.getKey().equals("heartrate")) {
							this.heartRateData = true;
							editEntry.heartrate(element.getValue());
						} else if (element.getKey().equals("waypoints")) {
							this.gpsData = true;
							editEntry.waypoints(element.getValue());
						}

					}
				} else {
					element.getValue().reset();
				}
			}
		}

		for (Entry<String, TargetWord> element : targetWords.entrySet()) {
			element.getValue().reset();
			element.getValue().setFindStatus(false);
		}

		if (possibleCounter > 3
				&& (this.heartRateData == true || this.gpsData == true)) {
			return true;
		}
		return false;
	}

	public EditEntrys getEditEntrys() {
		return this.editEntry;
	}

	public void buildHrmFile(String outputFile) {
		this.buildInternHrmFile(outputFile);
	}

	public void buildHrmFile() {
		this.buildInternHrmFile(null);
	}

	public void buildGpxFile(String outputFile) {
		this.buildInternGpxFile(outputFile);
	}

	public void buildGpxFile() {
		this.buildInternGpxFile(null);
	}

	public boolean buildInternHrmFile(String outputFile) {
		StringBuffer hrmBuilder = new StringBuffer();

		hrmBuilder.append("[Params]\n" + "Version=107\n" + "Monitor=1\n"
				+ "SMode="
				+ this.editEntry.getsMode()
				+ "\n"
				+ "Date="
				+ this.editEntry.getDate()
				+ "\n"
				+ "StartTime="
				+ this.editEntry.getStartTime()
				+ "\n"
				+ "Length="
				+ this.editEntry.getDuration()
				+ "\n"
				+ "Interval="
				+ this.editEntry.getInterval()
				+ "\n"
				+ "Upper1=0\n"
				+ "Lower1=0\n"
				+ "Upper2=0\n"
				+ "Lower2=0\n"
				+ "Upper3=0\n"
				+ "Lower3=0\n"
				+ "Timer1=00:00:00.0\n"
				+ "Timer2=00:00:00.0\n"
				+ "Timer3=00:00:00.0\n"
				+ "ActiveLimit=0\n"
				+ "MaxHR="
				+ this.editEntry.getMaxBPM()
				+ "\n"
				+ "RestHR=0\n"
				+ "StartDelay=0\n"
				+ "VO2max=0\n"
				+ "Weight=0"
				+ "\n\n"
				+ "[Note]\n\n"
				+ "[IntTimes]\n\n"
				+ "[IntNotes]\n\n"
				+ "[ExtraData]\n\n"
				+ "[Summary-123]\n\n"
				+ "[Summary-TH]\n\n"
				+ "[HRZones]\n\n"
				+ "[SwapTimes]\n\n"
				+ "[Trip]\n\n"
				+ "[HRData]\n");

		if (this.distanceData == true) {
			for (int i = 0; i != this.editEntry.getHeartRateValues().size(); i++) {
				hrmBuilder.append(this.editEntry.getHeartRateValues().get(i)
						+ "\t" + this.editEntry.getDistanceValues().get(i)
						+ "\n");
			}
		} else {
			for (int i = 0; i != this.editEntry.getHeartRateValues().size(); i++) {
				hrmBuilder.append(this.editEntry.getHeartRateValues().get(i)
						+ "\n");
			}
		}

		FileWriter fstream;
		try {
			if (outputFile == null) {
				fstream = new FileWriter("output.hrm");
			} else {
				if (outputFile.toCharArray()[outputFile.length() - 1] != 'm'
						|| outputFile.toCharArray()[outputFile.length() - 2] != 'r'
						|| outputFile.toCharArray()[outputFile.length() - 3] != 'h') {
					StringBuilder outputFileBuilder = new StringBuilder();
					outputFileBuilder.append(outputFile + ".hrm");

					fstream = new FileWriter(outputFileBuilder.toString());
				} else {
					fstream = new FileWriter(outputFile);
				}
			}
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(hrmBuilder.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean buildInternGpxFile(String outputFile) {
		StringBuffer hrmBuilder = new StringBuffer();
		int heartRateCounter1 = 0;
		int heartRateCounter2 = 0;

		hrmBuilder
				.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
						+ "<gpx xmlns=\"http://www.topografix.com/GPX/1/1\" "
						+ "creator=\"NikeToHRM by Lukas Koehler\" version=\"1.1\" "
						+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
						+ "xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 "
						+ "http://www.topografix.com/GPX/1/1/gpx.xsd\" "
						+ "xmlns:gpxx=\"http://www.gps-freeware.de/xmlschema/TrackPointExtension/v1\">\n"
						+ "<trk>\n" + "<name>NAME</name>\n" + "<trkseg>\n");

		for (int counter = 0; counter < this.editEntry.getSpeed().size(); counter++) {
			hrmBuilder.append("<trkpt lat=\""
					+ this.editEntry.getLat().get(counter) + "\" lon=\""
					+ this.editEntry.getLon().get(counter) + "\">\n" + "<ele>"
					+ this.editEntry.getEle().get(counter) + "</ele>\n"
					+ "<time>" + this.editEntry.getTime().get(counter)
					+ "</time>\n" + "<extensions>\n"
					+ "<gpxx:TrackPointExtension>\n");
			// + "<gpxx:speed>"
			// + this.editEntry.getSpeed().get(counter)
			// + "</gpxx:speed>\n");

			if (this.heartRateData == true) {
				hrmBuilder.append("<gpxx:hr>"
						+ this.editEntry.getHeartRateValues().get(
								heartRateCounter1) + "</gpxx:hr>\n");
				if (heartRateCounter1 < this.editEntry.getHeartRateValues()
						.size() - 1) {
					heartRateCounter2++;
					if (heartRateCounter2 == 10) {
						heartRateCounter1++;
						heartRateCounter2 = 0;
					}
				}
			}

			hrmBuilder.append("</gpxx:TrackPointExtension>\n"
					+ "</extensions>\n" + "</trkpt>\n");
		}

		hrmBuilder.append("</trkseg>\n" + "</trk>\n" + "</gpx>\n");

		FileWriter fstream;
		try {
			if (outputFile == null) {
				fstream = new FileWriter("output.gpx");
			} else {
				if (outputFile.toCharArray()[outputFile.length() - 1] != 'x'
						|| outputFile.toCharArray()[outputFile.length() - 2] != 'p'
						|| outputFile.toCharArray()[outputFile.length() - 3] != 'g') {
					StringBuilder outputFileBuilder = new StringBuilder();
					outputFileBuilder.append(outputFile + ".gpx");

					fstream = new FileWriter(outputFileBuilder.toString());
				} else {
					fstream = new FileWriter(outputFile);
				}
			}
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(hrmBuilder.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean getGpsStatus() {
		return this.gpsData;
	}

	public boolean getHeartRateStatus() {
		return this.heartRateData;
	}

	public boolean getDistanceStatus() {
		return this.distanceData;
	}
}
