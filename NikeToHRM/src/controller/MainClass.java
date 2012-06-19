package controller;

import view.MainWindow;

public class MainClass {
	public static void main(String[] args) {
		HRMBuilder hrmBuilder = new HRMBuilder();
		hrmBuilder.getDataFromFile("./new_file_with_gps.html");
		hrmBuilder.findTargetWords();
		hrmBuilder.buildGpxFile();
	}
}
