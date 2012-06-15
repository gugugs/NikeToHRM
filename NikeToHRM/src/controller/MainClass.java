package controller;

import view.MainWindow;

public class MainClass {
	public static void main(String[] args) {
		HRMBuilder builder = new HRMBuilder();
		builder.getDataFromFile("./Nike+.html");
		builder.findTargetWords();
	}
}
