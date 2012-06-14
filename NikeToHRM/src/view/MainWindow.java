package view;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import controller.HRMBuilder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainWindow extends javax.swing.JFrame {

	private static final long serialVersionUID = 3172688540921699213L;
	private HRMBuilder hrmBuilder;
	private FileChooser fileChooser;
	private hrmBuildedDialog buildedDialog;
	private JLabel intervalLabel;
	private JLabel sModeLabel;
	private JLabel maxBpmLabel;
	private JLabel weightLabel;
	private JLabel durationLabel;
	private JLabel startTimeLabel;
	private JLabel dateLabel;
	private JLabel jLabel7;
	private JLabel jLabel5;
	private JLabel readedFromLabel;
	private JProgressBar progressBar;
	private JButton browseOutput;
	private JTextField outputEntry;
	private JLabel jLabel6;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JLabel dataLabel;
	private JButton buildHrmButton;

	public MainWindow(HRMBuilder hrmBuilder) {
		super();
		this.hrmBuilder = hrmBuilder;
		this.fileChooser = new FileChooser(this);
		this.buildedDialog = new hrmBuildedDialog();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			pack();
			this.setSize(501, 349);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setTitle("Nike+ data to HRM converter (Lukas Köhler)");
		{
			buildHrmButton = new JButton();
			getContentPane().add(buildHrmButton);
			buildHrmButton.setText("Build HRM file");
			buildHrmButton.setBounds(262, 277, 229, 22);
			buildHrmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					buildHrmButtonActionPerformed(evt);
				}
			});
		}
		{
			dataLabel = new JLabel();
			getContentPane().add(dataLabel);
			dataLabel.setText("Readed Data:");
			dataLabel.setBounds(12, 57, 111, 15);
		}
		{
			jLabel1 = new JLabel();
			getContentPane().add(jLabel1);
			jLabel1.setText("Date:");
			jLabel1.setBounds(12, 84, 44, 15);
		}
		{
			jLabel2 = new JLabel();
			getContentPane().add(jLabel2);
			jLabel2.setText("Start Time:");
			jLabel2.setBounds(12, 105, 82, 15);
		}
		{
			jLabel3 = new JLabel();
			getContentPane().add(jLabel3);
			jLabel3.setText("Duration:");
			jLabel3.setBounds(12, 126, 82, 15);
		}
		{
			jLabel4 = new JLabel();
			getContentPane().add(jLabel4);
			jLabel4.setText("Weight:");
			jLabel4.setBounds(12, 147, 82, 15);
		}
		{
			jLabel5 = new JLabel();
			getContentPane().add(jLabel5);
			jLabel5.setText("Maximum BPM:");
			jLabel5.setBounds(12, 168, 111, 15);
		}
		{
			jLabel6 = new JLabel();
			getContentPane().add(jLabel6);
			jLabel6.setText("S Mode:");
			jLabel6.setBounds(12, 189, 87, 15);
		}
		{
			jLabel7 = new JLabel();
			getContentPane().add(jLabel7);
			jLabel7.setText("Interval: ");
			jLabel7.setBounds(12, 210, 64, 15);
		}
		{
			dateLabel = new JLabel();
			getContentPane().add(dateLabel);
			dateLabel.setText("- - -");
			dateLabel.setBounds(140, 84, 209, 15);
		}
		{
			startTimeLabel = new JLabel();
			getContentPane().add(startTimeLabel);
			startTimeLabel.setText("- - -");
			startTimeLabel.setBounds(140, 105, 209, 15);
		}
		{
			durationLabel = new JLabel();
			getContentPane().add(durationLabel);
			durationLabel.setText("- - -");
			durationLabel.setBounds(140, 126, 209, 15);
		}
		{
			weightLabel = new JLabel();
			getContentPane().add(weightLabel);
			weightLabel.setText("- - -");
			weightLabel.setBounds(140, 147, 209, 15);
		}
		{
			maxBpmLabel = new JLabel();
			getContentPane().add(maxBpmLabel);
			maxBpmLabel.setText("- - -");
			maxBpmLabel.setBounds(141, 168, 209, 15);
		}
		{
			sModeLabel = new JLabel();
			getContentPane().add(sModeLabel);
			sModeLabel.setText("- - -");
			sModeLabel.setBounds(141, 189, 209, 15);
		}
		{
			intervalLabel = new JLabel();
			getContentPane().add(intervalLabel);
			intervalLabel.setText("- - -");
			intervalLabel.setBounds(141, 210, 209, 15);
		}
		{
			readedFromLabel = new JLabel();
			getContentPane().add(readedFromLabel);
			readedFromLabel.setText("- - -");
			readedFromLabel.setBounds(140, 57, 209, 15);
		}
		{
			outputEntry = new JTextField();
			getContentPane().add(outputEntry);
			outputEntry.setBounds(12, 244, 238, 22);
			outputEntry.setEditable(false);
			outputEntry.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.LOWERED));
		}
		{
			browseOutput = new JButton();
			getContentPane().add(browseOutput);
			browseOutput.setText("Browse output file");
			browseOutput.setBounds(262, 244, 229, 22);
		}
		{
			progressBar = new JProgressBar();
			getContentPane().add(progressBar);
			progressBar.setBounds(12, 278, 238, 21);
		}
	}

	public void setOutputFile(String file) {
//		this.outputEntry.setText(file);
	}

//	public class readRunnable implements Runnable {
//
//		public void run() {
//
//			boolean possible = hrmBuilder.readData(
//					runIdField.getText());
//			progressBar.setIndeterminate(false);
//			setLabels(possible);
//		}
//	}

	private void readButtonActionPerformed(ActionEvent evt) {
//		this.progressBar.setIndeterminate(true);
//
//		Runnable r = new readRunnable();
//		Thread t = new Thread(r);
//		t.start();
	}

	private void setLabels(boolean possible) {
//		if (possible == false) {
//			this.readedFromLabel.setText("ERROR!");
//			this.dateLabel.setText("- - -");
//			this.startTimeLabel.setText("- - -");
//			this.durationLabel.setText("- - -");
//			this.weightLabel.setText("- - -");
//			this.maxBpmLabel.setText("- - -");
//			this.sModeLabel.setText("- - -");
//			this.intervalLabel.setText("- - -");
//		} else {
//			HashMap<String, String> returnData = this.hrmBuilder
//					.getReadedData();
//			
//			readedFromLabel.setText("Internet");
//			this.dateLabel.setText(returnData.get("date"));
//			this.startTimeLabel.setText(returnData.get("startTime"));
//			this.durationLabel.setText(returnData.get("duration"));
//			this.weightLabel.setText(returnData.get("weight"));
//			this.maxBpmLabel.setText(returnData.get("maxBPM"));
//			this.sModeLabel.setText(returnData.get("sMode"));
//			this.intervalLabel.setText(returnData.get("interval"));
//		}
	}

	private void buildHrmButtonActionPerformed(ActionEvent evt) {
//		boolean status;
//		if (this.outputEntry.getText().equals("")) {
//			status = this.hrmBuilder
//					.buildHrmFile(this.hrmBuilder.STANDARD_OUTPUT_FILE);
//		} else if (this.outputEntry.getText().toCharArray()[this.outputEntry
//				.getText().length() - 1] != 'm'
//				|| this.outputEntry.getText().toCharArray()[this.outputEntry
//						.getText().length() - 2] != 'r'
//				|| this.outputEntry.getText().toCharArray()[this.outputEntry
//						.getText().length() - 3] != 'h') {
//			StringBuilder outputFileBuilder = new StringBuilder();
//			outputFileBuilder.append(this.outputEntry.getText() + ".hrm");
//			status = this.hrmBuilder.buildHrmFile(outputFileBuilder.toString());
//		} else {
//			status = this.hrmBuilder.buildHrmFile(this.outputEntry.getText());
//		}
//		if (status == true) {
//			this.buildedDialog.getLabel().setText(
//					"HRM file successfully builded");
//		} else {
//			this.buildedDialog.getLabel().setText("ERROR");
//		}
//		this.buildedDialog.setVisible(true);
//	}
//
//	private void browseOutputActionPerformed(ActionEvent evt) {
//		this.fileChooser.setVisible(true);
	}

}
