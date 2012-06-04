package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;

public class HRMBuilder {

	private boolean dataReaded;

	private String weight;
	private String duration;
	private String maxBPM;
	private String date;
	private String startTime;
	private int mode;
	private String sMode;
	private String interval;

	private LinkedList<String> rawDistanceValues;
	private LinkedList<String> distanceValues;
	private LinkedList<String> HRValues;

	private HashMap<String, String> returnData;

	public final int FROM_INTERNET = 133;
	public final int FROM_FILE = 132;
	public final String STANDARD_OUTPUT_FILE = null;

	public HRMBuilder() {
		this.dataReaded = false;
	}

	public HashMap<String, String> getReadedData() {
		return returnData;
	}

	public boolean readData(int mode, String input) {
		if (input == null || input.equals("")) {
			System.out.println("No INPUT ! ! !");
			this.dataReaded = false;
			return false;
		}

		try {

			BufferedReader bReader;

			if (mode == this.FROM_INTERNET) {
				boolean userIdReaded = false;
				boolean recorded = false;
				StringBuilder urlBuilder = new StringBuilder();
				StringBuilder userID = new StringBuilder();
				StringBuilder runID = new StringBuilder();
				for (Character element : input.toCharArray()) {
					if (Character.isDigit(element)) {
						recorded = true;
						if (userIdReaded == false) {
							userID.append(element);
						} else {
							runID.append(element);
						}
					} else {
						if (recorded == true) {
							recorded = false;
							userIdReaded = true;
						}
					}
				}

				urlBuilder
						.append("http://nikerunning.nike.com/nikeplus/v1/services/app/get_run.jsp?id="
								+ runID.toString()
								+ "&userID="
								+ userID.toString());

				URL nikePlus = new URL(urlBuilder.toString());
				bReader = new BufferedReader(new InputStreamReader(
						nikePlus.openStream()));

			} else {
				FileInputStream fiStream;
				DataInputStream diStream;

				fiStream = new FileInputStream(input);
				diStream = new DataInputStream(fiStream);
				bReader = new BufferedReader(new InputStreamReader(diStream));
			}
			String bReaderLine = bReader.readLine();
			int startIndex = -1;
			int endIndex = -1;
			StringBuilder targetBuilder = new StringBuilder();
			StringBuilder valueBuilder = new StringBuilder();
			boolean record = false;
			boolean durationBuilded = false;
			boolean maximumBPMBuilded = false;
			boolean possibleFile = false;

			for (int i = 0; i != bReaderLine.length(); i++) {
				if (startIndex == -1
						&& (bReaderLine.charAt(i) == ' ' || bReaderLine
								.charAt(i) == '<')) {
					record = false;
					startIndex = i;
				} else if (startIndex != -1
						&& (bReaderLine.charAt(i) == '='
								|| bReaderLine.charAt(i) == ' ' || bReaderLine
								.charAt(i) == '>')) {
					endIndex = i;
				}

				if (record == true) {
					valueBuilder.append(bReaderLine.charAt(i));
				}

				if (startIndex != -1 && endIndex != -1) {
					for (int k = startIndex + 1; k != endIndex; k++) {
						targetBuilder.append(bReaderLine.charAt(k));
					}

					if (targetBuilder.toString().equals("weight")
							|| targetBuilder.toString().equals("startTime")) {
						record = true;
					}

					else if (targetBuilder.toString().equals("plusService")) {
						possibleFile = true;
					}

					else if (targetBuilder.toString().equals("duration")
							&& durationBuilded == false) {
						record = true;
					}

					else if (targetBuilder.toString().equals("bpm")
							&& maximumBPMBuilded == false) {
						record = true;
					}

					else if (targetBuilder.toString().equals("/weight")) {

						this.weight = new String(valueBuilder.toString()
								.toCharArray(), 0, 2);
						valueBuilder.setLength(0);

						System.out.println("Weight: " + this.weight);

					} else if (durationBuilded == false
							&& targetBuilder.toString().equals("/duration")) {

						int value = Integer.valueOf(valueBuilder.toString());
						int hour = value / 3600000;
						int minutes = (value - hour * 3600000) / 60000;
						int seconds = (value - hour * 3600000 - minutes * 60000) / 1000;

						String sHour = String.valueOf(hour);
						String sMinutes = String.valueOf(minutes);
						String sSeconds = String.valueOf(seconds);

						StringBuilder durationBuilder = new StringBuilder();

						if (sHour.length() == 1) {
							durationBuilder.append("0" + sHour);
						} else {
							durationBuilder.append(sHour);
						}
						durationBuilder.append(":");
						if (sMinutes.length() == 1) {
							durationBuilder.append("0" + sMinutes);
						} else {
							durationBuilder.append(sMinutes);
						}
						durationBuilder.append(":");
						if (sSeconds.length() == 1) {
							durationBuilder.append("0" + sSeconds);
						} else {
							durationBuilder.append(sSeconds);
						}
						durationBuilder.append(".0");

						this.duration = durationBuilder.toString();
						durationBuilded = true;
						valueBuilder.setLength(0);

						System.out.println("Duration: " + this.duration);

					}

					else if (maximumBPMBuilded == false
							&& targetBuilder.toString().equals("/bpm")) {

						this.maxBPM = new String(valueBuilder.toString());
						maximumBPMBuilded = true;
						valueBuilder.setLength(0);

						System.out.println("Maximum BPM: " + this.maxBPM);
					}

					else if (targetBuilder.toString().equals("/startTime")) {

						String year = new String(valueBuilder.toString()
								.toCharArray(), 0, 4);
						String month = new String(valueBuilder.toString()
								.toCharArray(), 5, 2);
						String day = new String(valueBuilder.toString()
								.toCharArray(), 8, 2);
						String hour = new String(valueBuilder.toString()
								.toCharArray(), 11, 2);
						String minute = new String(valueBuilder.toString()
								.toCharArray(), 14, 2);
						String second = new String(valueBuilder.toString()
								.toCharArray(), 17, 2);

						StringBuilder startTimeBuilder = new StringBuilder();

						startTimeBuilder.append(year + month + day);
						this.date = startTimeBuilder.toString();
						startTimeBuilder.setLength(0);
						startTimeBuilder.append(hour + ":" + minute + ":"
								+ second + ".0");
						this.startTime = startTimeBuilder.toString();

						valueBuilder.setLength(0);

						System.out.println("Date: " + this.date);
						System.out.println("Start time: " + this.startTime);
					}

					else if (targetBuilder.toString().equals("workoutType")) {

						StringBuilder sModeBuilder = new StringBuilder();
						for (int j = i + 2; bReaderLine.charAt(j) != '\"'; j++) {
							sModeBuilder.append(bReaderLine.charAt(j));
						}
						if (sModeBuilder.toString().equals("standard")) {
							this.sMode = "100000000";
							this.mode = 1;
						} else {
							this.sMode = "000000000";
							this.mode = 0;
						}

						valueBuilder.setLength(0);

						System.out.println("S Mode: " + this.sMode);
					}

					else if (targetBuilder.toString().equals("intervalValue")) {

						StringBuilder intervalBuilder = new StringBuilder();
						for (int j = i + 2; bReaderLine.charAt(j) != '\"'; j++) {
							intervalBuilder.append(bReaderLine.charAt(j));
						}
						this.interval = intervalBuilder.toString();

						valueBuilder.setLength(0);

						System.out.println("Interval: " + this.interval);
					}

					targetBuilder.setLength(0);
					startIndex = endIndex = -1;

				}
			}

			if (possibleFile == false) {
				System.out.print("NO possible FILE ! ! !\n");
				return false;
			}

			this.returnData = new HashMap<String, String>();
			returnData.put("date", this.date);
			returnData.put("startTime", this.startTime);
			returnData.put("duration", this.duration);
			returnData.put("weight", this.weight);
			returnData.put("maxBPM", this.maxBPM);
			returnData.put("sMode", this.sMode);
			returnData.put("interval", this.interval);

			System.out.print("----------------------------------\n");

			this.rawDistanceValues = new LinkedList<String>();
			StringBuilder distanceValue = new StringBuilder();

			this.HRValues = new LinkedList<String>();
			StringBuilder HRValue = new StringBuilder();

			boolean stopLoop = false;

			if (this.mode == 1) {
				while (true) {
					bReaderLine = bReader.readLine();
					for (int i = 0; i != bReaderLine.length(); i++) {
						if (bReaderLine.charAt(i) == '<') {
							stopLoop = true;
							this.rawDistanceValues
									.add(distanceValue.toString());
							break;
						}
						if (bReaderLine.charAt(i) == '.'
								|| Character.isDigit(bReaderLine.charAt(i))) {
							distanceValue.append(bReaderLine.charAt(i));
						} else if (bReaderLine.charAt(i) == ',') {
							this.rawDistanceValues
									.add(distanceValue.toString());
							distanceValue.setLength(0);
						}
					}

					if (stopLoop == true)
						break;
				}

				this.distanceValues = new LinkedList<String>();
				this.distanceValues.add(String.valueOf(Math.round(Double.valueOf(this.rawDistanceValues.get(0))*3600)));

				for (int i = 1; i < this.rawDistanceValues.size(); i++) {
					this.distanceValues
							.add(String.valueOf(Math.round((Double
									.valueOf(this.rawDistanceValues.get(i)) - Double
									.valueOf(this.rawDistanceValues.get(i - 1))) * 3600)));
				}
			}
			

			stopLoop = false;
			while (true) {
				bReaderLine = bReader.readLine();
				for (int i = 0; i != bReaderLine.length(); i++) {
					if (bReaderLine.charAt(i) == '<') {
						stopLoop = true;
						this.HRValues.add(HRValue.toString());
						break;
					}
					if (Character.isDigit(bReaderLine.charAt(i))) {
						HRValue.append(bReaderLine.charAt(i));
					} else if (bReaderLine.charAt(i) == ',') {
						this.HRValues.add(HRValue.toString());
						HRValue.setLength(0);
					}
				}

				if (stopLoop == true)
					break;
			}

			int firstPossible = 0;
			int lastPossible = 0;
			int faktor = 0;
			for (int i = 0; i < this.HRValues.size(); i++) {
				if (Integer.valueOf(this.HRValues.get(i)) == 0) {
					firstPossible = i - 1;
					for (int k = i; Integer.valueOf(this.HRValues.get(k)) == 0; k++) {
						lastPossible = k;
					}
					lastPossible++;

					faktor = ((Integer.valueOf(this.HRValues.get(lastPossible))) - (Integer
							.valueOf(this.HRValues.get(firstPossible))))
							/ (lastPossible - firstPossible);

					int counter = 1;
					for (int j = i; Integer.valueOf(this.HRValues.get(j)) == 0; j++) {
						this.HRValues.set(j, String.valueOf(Integer
								.valueOf(this.HRValues.get(firstPossible))
								+ (faktor * counter)));
						counter++;
					}

					i = lastPossible;
				}
			}

			bReader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.dataReaded = true;
		return true;
	}

	public boolean buildHrmFile(String outputFile) {
		if (this.dataReaded == false) {
			System.out.println("No data readed! ! !");
			return false;
		}
		StringBuilder hrmBuilder = new StringBuilder();

		hrmBuilder.append("[Params]\n" + "Version=107\n" + "Monitor=1\n"
				+ "SMode="
				+ this.sMode
				+ "\n"
				+ "Date="
				+ this.date
				+ "\n"
				+ "StartTime="
				+ this.startTime
				+ "\n"
				+ "Length="
				+ this.duration
				+ "\n"
				+ "Interval="
				+ this.interval
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
				+ this.maxBPM
				+ "\n"
				+ "RestHR=0\n"
				+ "StartDelay=0\n"
				+ "VO2max=0\n"
				+ "Weight="
				+ this.weight
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

		if (this.mode == 1) {
			for (int i = 0; i != this.HRValues.size(); i++) {
				hrmBuilder.append(this.HRValues.get(i) + "\t"
						+ this.distanceValues.get(i) + "\n");
			}
		} else {
			for (int i = 0; i != this.HRValues.size(); i++) {
				hrmBuilder.append(this.HRValues.get(i) + "\n");
			}
		}

		FileWriter fstream;
		try {
			if (outputFile == this.STANDARD_OUTPUT_FILE) {
				fstream = new FileWriter("output.hrm");
			} else {
				fstream = new FileWriter(outputFile);
			}
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(hrmBuilder.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}
}
