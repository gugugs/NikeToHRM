package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class HRMBuilder {
	
	private File inputFile;
	private FileInputStream FIStream;
	private BufferedReader BReader;
	private int counter;
	private String strLine;
	private char[] strLineArray;
	private LinkedList<Character> inputString;
	private Character currentCharacter;
	private HashMap<String, TargetWord> targetWords;
	private boolean wordComplete;
	private EditEntrys editEntry;

	public HRMBuilder() {
		inputString = new LinkedList<Character>();
		targetWords = new HashMap<String, TargetWord>();
		
		targetWords.put("activityType", new TargetWord("activityType"));
		targetWords.put("startTimeUtc", new TargetWord("startTimeUtc"));
		targetWords.put("duration", new TargetWord("duration"));
		targetWords.put("maximumHeartRate", new TargetWord("maximumHeartRate"));
		targetWords.put("intervalMetric", new TargetWord("intervalMetric"));
		targetWords.put("distance", new TargetWord("\"type\":\"DISTANCE\""));
		targetWords.put("heartrate", new TargetWord("\"type\":\"HEARTRATE\""));
		
		editEntry = new EditEntrys();
	}

	public boolean getDataFromFile(String filePath) {
		try {
			inputFile = new File(filePath);
			FIStream = new FileInputStream(inputFile);
			BReader = new BufferedReader(new InputStreamReader(FIStream));

			while ((strLine = BReader.readLine()) != null) {
				strLineArray = strLine.toCharArray();
				for (counter = 0; counter < strLine.length(); counter++) {
					inputString.add(strLineArray[counter]);
				}
			}
			FIStream.close();
			editEntry.setInputString(inputString);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void findTargetWords() {
		for (counter = 0; counter < inputString.size(); counter++) {
			currentCharacter = inputString.get(counter);
			
			for (Entry<String, TargetWord> element : targetWords.entrySet()) {
				if (element.getValue().getCurrentRightLetter() == currentCharacter && element.getValue().getFindStatus() == false) {
					wordComplete = element.getValue().increaseCurrentRightLetterNumber();
					if (wordComplete) {
						element.getValue().setStartPosition(counter - element.getValue().getWord().length() + 1);
						element.getValue().setEndPosition(counter);
						
						if (element.getKey().equals("activityType")) {
							editEntry.activityType(element.getValue());
						} else if (element.getKey().equals("startTimeUtc")) {
							editEntry.startTimeUtc(element.getValue());
						} else if (element.getKey().equals("duration")) {
							editEntry.duration(element.getValue());
						} else if (element.getKey().equals("maximumHeartRate")) {
							editEntry.maximumHeartRate(element.getValue());
						} else if (element.getKey().equals("intervalMetric")) {
							editEntry.intervalMetric(element.getValue());
						} else if (element.getKey().equals("distance")) {
							editEntry.distance(element.getValue());
						} else if (element.getKey().equals("heartrate")) {
							editEntry.heartrate(element.getValue());
						}
						
					}
				} else {
					element.getValue().reset();
				}
			}
			
		}
	}

	public boolean readData(String input) {
		return false;
	}

	// public boolean buildHrmFile(String outputFile) {
	// if (this.dataReaded == false) {
	// System.out.println("No data readed! ! !");
	// return false;
	// }
	// StringBuilder hrmBuilder = new StringBuilder();
	//
	// hrmBuilder.append("[Params]\n" + "Version=107\n" + "Monitor=1\n"
	// + "SMode="
	// + this.sMode
	// + "\n"
	// + "Date="
	// + this.date
	// + "\n"
	// + "StartTime="
	// + this.startTime
	// + "\n"
	// + "Length="
	// + this.duration
	// + "\n"
	// + "Interval="
	// + this.interval
	// + "\n"
	// + "Upper1=0\n"
	// + "Lower1=0\n"
	// + "Upper2=0\n"
	// + "Lower2=0\n"
	// + "Upper3=0\n"
	// + "Lower3=0\n"
	// + "Timer1=00:00:00.0\n"
	// + "Timer2=00:00:00.0\n"
	// + "Timer3=00:00:00.0\n"
	// + "ActiveLimit=0\n"
	// + "MaxHR="
	// + this.maxBPM
	// + "\n"
	// + "RestHR=0\n"
	// + "StartDelay=0\n"
	// + "VO2max=0\n"
	// + "Weight=0"
	// + "\n\n"
	// + "[Note]\n\n"
	// + "[IntTimes]\n\n"
	// + "[IntNotes]\n\n"
	// + "[ExtraData]\n\n"
	// + "[Summary-123]\n\n"
	// + "[Summary-TH]\n\n"
	// + "[HRZones]\n\n"
	// + "[SwapTimes]\n\n"
	// + "[Trip]\n\n"
	// + "[HRData]\n");
	//
	// if (this.mode == 1) {
	// for (int i = 0; i != this.HRValues.size(); i++) {
	// hrmBuilder.append(this.HRValues.get(i) + "\t"
	// + this.distanceValues.get(i) + "\n");
	// }
	// } else {
	// for (int i = 0; i != this.HRValues.size(); i++) {
	// hrmBuilder.append(this.HRValues.get(i) + "\n");
	// }
	// }
	//
	// FileWriter fstream;
	// try {
	// if (outputFile == this.STANDARD_OUTPUT_FILE) {
	// fstream = new FileWriter("output.hrm");
	// } else {
	// fstream = new FileWriter(outputFile);
	// }
	// BufferedWriter out = new BufferedWriter(fstream);
	// out.write(hrmBuilder.toString());
	// out.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// return true;
	// }
}
