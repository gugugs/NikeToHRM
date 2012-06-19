package view;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import controller.HRMBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	public final int WRONG_FILE = 1133;
	public final int WRONG_DATA = 2233;
	public final int BUILD_HRM = 3333;
	private HRMBuilder hrmBuilder;
	private JButton buildGpxButton;
	private JTextField fileInputField;
	private FileChooser fileChooser;
	private JLabel sModeLabel;
	private JLabel durationLabel;
	private JLabel startTimeLabel;
	private JLabel dateLabel;
	private JButton browseInputButton;
	private JProgressBar progressBar;
	private JButton browseOutputButton;
	private JTextField fileOutputField;
	private JLabel jLabel6;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JButton buildHrmButton;
	private Dialog dialog;
	private String inputFile;

	public MainWindow(HRMBuilder hrmBuilder) {
		super();
		this.hrmBuilder = hrmBuilder;
		this.fileChooser = new FileChooser(this);
		this.dialog = new Dialog(this);
		this.dialog.setVisible(false);
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			pack();
			this.setSize(519, 228);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setTitle("Nike+ data to HRM/GPX converter");
		{
			buildHrmButton = new JButton();
			getContentPane().add(buildHrmButton);
			buildHrmButton.setText("Build HRM file");
			buildHrmButton.setBounds(328, 78, 177, 22);
			buildHrmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					buildHrmButtonActionPerformed(evt);
				}
			});
		}
		{
			jLabel1 = new JLabel();
			getContentPane().add(jLabel1);
			jLabel1.setText("Date");
			jLabel1.setBounds(12, 109, 149, 15);
		}
		{
			jLabel2 = new JLabel();
			getContentPane().add(jLabel2);
			jLabel2.setText("Start Time:");
			jLabel2.setBounds(12, 130, 149, 15);
		}
		{
			jLabel3 = new JLabel();
			getContentPane().add(jLabel3);
			jLabel3.setText("Duration:");
			jLabel3.setBounds(12, 151, 149, 15);
		}
		{
			jLabel6 = new JLabel();
			getContentPane().add(jLabel6);
			jLabel6.setText("Mode:");
			jLabel6.setBounds(12, 88, 149, 15);
		}
		{
			dateLabel = new JLabel();
			getContentPane().add(dateLabel);
			dateLabel.setText("- - -");
			dateLabel.setBounds(99, 109, 207, 15);
		}
		{
			startTimeLabel = new JLabel();
			getContentPane().add(startTimeLabel);
			startTimeLabel.setText("- - -");
			startTimeLabel.setBounds(100, 130, 207, 15);
		}
		{
			durationLabel = new JLabel();
			getContentPane().add(durationLabel);
			durationLabel.setText("- - -");
			durationLabel.setBounds(100, 151, 207, 15);
		}
		{
			sModeLabel = new JLabel();
			getContentPane().add(sModeLabel);
			sModeLabel.setText("- - -");
			sModeLabel.setBounds(99, 88, 207, 15);
		}
		{
			fileOutputField = new JTextField();
			getContentPane().add(fileOutputField);
			fileOutputField.setBounds(12, 46, 305, 22);
			fileOutputField.setEditable(false);
			fileOutputField.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.LOWERED));
		}
		{
			browseOutputButton = new JButton();
			getContentPane().add(browseOutputButton);
			browseOutputButton.setText("Browse output file");
			browseOutputButton.setBounds(329, 47, 176, 20);
			browseOutputButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					browseOutputButtonActionPerformed(evt);
				}
			});
		}
		{
			progressBar = new JProgressBar();
			getContentPane().add(progressBar);
			progressBar.setBounds(328, 166, 176, 22);
		}
		{
			fileInputField = new JTextField();
			getContentPane().add(fileInputField);
			fileInputField.setBounds(12, 12, 305, 22);
			fileInputField.setEditable(false);
			fileInputField.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.LOWERED));
		}
		{
			browseInputButton = new JButton();
			getContentPane().add(browseInputButton);
			browseInputButton.setText("Browse input file");
			browseInputButton.setBounds(329, 12, 176, 22);
			browseInputButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					browseInputButtonActionPerformed(evt);
				}
			});
		}
		{
			buildGpxButton = new JButton();
			getContentPane().add(buildGpxButton);
			buildGpxButton.setText("Build GPX file");
			buildGpxButton.setBounds(328, 111, 176, 22);
			buildGpxButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					buildGpxButtonActionPerformed(evt);
				}
			});
		}

		this.buildHrmButton.setEnabled(false);
		this.browseOutputButton.setEnabled(false);
		this.buildGpxButton.setEnabled(false);
	}

	public JButton getBrowseInputButton() {
		return this.browseInputButton;
	}

	public void deactivate() {
		this.browseInputButton.setEnabled(false);
		this.browseOutputButton.setEnabled(false);
		this.buildHrmButton.setEnabled(false);
		this.buildGpxButton.setEnabled(false);
	}

	public void activate() {
		this.browseInputButton.setEnabled(true);
		this.browseOutputButton.setEnabled(true);
		this.buildHrmButton.setEnabled(true);
		this.buildGpxButton.setEnabled(true);
	}

	private void setLabels(int status) {
		if (status == this.BUILD_HRM) {
			this.dateLabel.setText(this.hrmBuilder.getEditEntrys().getDate()
					.subSequence(6, 8)
					+ "-"
					+ this.hrmBuilder.getEditEntrys().getDate()
							.subSequence(4, 6)
					+ "-"
					+ this.hrmBuilder.getEditEntrys().getDate()
							.subSequence(0, 4));
			this.startTimeLabel.setText(this.hrmBuilder
					.getEditEntrys()
					.getStartTime()
					.substring(
							0,
							this.hrmBuilder.getEditEntrys().getStartTime()
									.length() - 2));
			this.durationLabel.setText(this.hrmBuilder
					.getEditEntrys()
					.getDuration()
					.substring(
							0,
							this.hrmBuilder.getEditEntrys().getStartTime()
									.length() - 2));

			if (this.hrmBuilder.getGpsStatus()) {
				if (this.hrmBuilder.getHeartRateStatus()) {
					this.sModeLabel.setText("GPS and Heartrate");
				} else {
					this.sModeLabel.setText("Only GPS");
				}
			} else {
				if (this.hrmBuilder.getDistanceStatus()) {
					this.sModeLabel.setText("Heartrate and distance");
				} else {
					this.sModeLabel.setText("Only Heartrate");
				}
			}

			this.activate();
			if (this.hrmBuilder.getGpsStatus()) {
				this.buildGpxButton.setEnabled(true);
			} else {
				this.buildGpxButton.setEnabled(false);
			}
		} else {
			this.dateLabel.setText("- - -");
			this.startTimeLabel.setText("- - -");
			this.durationLabel.setText("- - -");
			sModeLabel.setText("- - -");
			this.deactivate();
			if (status == this.WRONG_DATA) {
				this.dialog.setMode(this.dialog.DATA_ERROR);
			} else if (status == this.WRONG_FILE) {
				this.dialog.setMode(this.dialog.FILE_ERROR);
			}
			this.dialog.setVisible(true);
		}
	}

	public void setInputFile(String file) {
		this.inputFile = file;
		this.deactivate();
		this.progressBar.setIndeterminate(true);

		new Thread(new Runnable() {

			@Override
			public void run() {
				if (hrmBuilder.getDataFromFile(inputFile)) {
					if (hrmBuilder.findTargetWords()) {
						fileInputField.setText(inputFile);
						setLabels(BUILD_HRM);
					} else {
						fileInputField.setText("");
						setLabels(WRONG_DATA);
					}
				} else {
					fileInputField.setText("");
					setLabels(WRONG_FILE);
				}
				progressBar.setIndeterminate(false);
			}
		}).start();
	}

	public void setOutputFile(String file) {
		this.fileOutputField.setText(file);
	}

	private void browseInputButtonActionPerformed(ActionEvent evt) {
		this.fileChooser.setMode(this.fileChooser.INPUT_MODE);
		this.fileChooser.setVisible(true);
	}

	private void browseOutputButtonActionPerformed(ActionEvent evt) {
		this.fileChooser.setMode(this.fileChooser.OUTPUT_MODE);
		this.fileChooser.setVisible(true);
	}

	private void buildHrmButtonActionPerformed(ActionEvent evt) {
		this.progressBar.setIndeterminate(true);
		this.deactivate();

		new Thread(new Runnable() {

			@Override
			public void run() {
				if (fileOutputField.getText().equals("")) {
					hrmBuilder.buildHrmFile();
				} else {
					hrmBuilder.buildHrmFile(fileOutputField.getText());
				}
				dialog.setMode(dialog.HRM_BUILDED);
				dialog.setVisible(true);
				progressBar.setIndeterminate(false);
			}
		}).start();
	}
	
	private void buildGpxButtonActionPerformed(ActionEvent evt) {
		this.progressBar.setIndeterminate(true);
		this.deactivate();

		new Thread(new Runnable() {

			@Override
			public void run() {
				if (fileOutputField.getText().equals("")) {
					hrmBuilder.buildGpxFile();
				} else {
					hrmBuilder.buildGpxFile(fileOutputField.getText());
				}
				dialog.setMode(dialog.GPX_BUILDED);
				dialog.setVisible(true);
				progressBar.setIndeterminate(false);
			}
		}).start();
	}

}
