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

	private String duration;
	private String maxBPM;
	private String date;
	private String startTime;
	private String sMode;
	private String interval;

	public HRMBuilder() {
		
	}
	

	public boolean readData(String input) {
		return false;
	}

//	public boolean buildHrmFile(String outputFile) {
//		if (this.dataReaded == false) {
//			System.out.println("No data readed! ! !");
//			return false;
//		}
//		StringBuilder hrmBuilder = new StringBuilder();
//
//		hrmBuilder.append("[Params]\n" + "Version=107\n" + "Monitor=1\n"
//				+ "SMode="
//				+ this.sMode
//				+ "\n"
//				+ "Date="
//				+ this.date
//				+ "\n"
//				+ "StartTime="
//				+ this.startTime
//				+ "\n"
//				+ "Length="
//				+ this.duration
//				+ "\n"
//				+ "Interval="
//				+ this.interval
//				+ "\n"
//				+ "Upper1=0\n"
//				+ "Lower1=0\n"
//				+ "Upper2=0\n"
//				+ "Lower2=0\n"
//				+ "Upper3=0\n"
//				+ "Lower3=0\n"
//				+ "Timer1=00:00:00.0\n"
//				+ "Timer2=00:00:00.0\n"
//				+ "Timer3=00:00:00.0\n"
//				+ "ActiveLimit=0\n"
//				+ "MaxHR="
//				+ this.maxBPM
//				+ "\n"
//				+ "RestHR=0\n"
//				+ "StartDelay=0\n"
//				+ "VO2max=0\n"
//				+ "Weight=0"
//				+ "\n\n"
//				+ "[Note]\n\n"
//				+ "[IntTimes]\n\n"
//				+ "[IntNotes]\n\n"
//				+ "[ExtraData]\n\n"
//				+ "[Summary-123]\n\n"
//				+ "[Summary-TH]\n\n"
//				+ "[HRZones]\n\n"
//				+ "[SwapTimes]\n\n"
//				+ "[Trip]\n\n"
//				+ "[HRData]\n");
//
//		if (this.mode == 1) {
//			for (int i = 0; i != this.HRValues.size(); i++) {
//				hrmBuilder.append(this.HRValues.get(i) + "\t"
//						+ this.distanceValues.get(i) + "\n");
//			}
//		} else {
//			for (int i = 0; i != this.HRValues.size(); i++) {
//				hrmBuilder.append(this.HRValues.get(i) + "\n");
//			}
//		}
//
//		FileWriter fstream;
//		try {
//			if (outputFile == this.STANDARD_OUTPUT_FILE) {
//				fstream = new FileWriter("output.hrm");
//			} else {
//				fstream = new FileWriter(outputFile);
//			}
//			BufferedWriter out = new BufferedWriter(fstream);
//			out.write(hrmBuilder.toString());
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return true;
//	}
}
