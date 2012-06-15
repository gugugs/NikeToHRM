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
	private HRMBuilder hrmBuilder;
	private JTextField fileInputField;
	private FileChooser fileChooser;
	private JLabel intervalLabel;
	private JLabel sModeLabel;
	private JLabel maxBpmLabel;
	private JLabel durationLabel;
	private JLabel startTimeLabel;
	private JLabel dateLabel;
	private JLabel jLabel7;
	private JLabel jLabel5;
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

	public MainWindow(HRMBuilder hrmBuilder) {
		super();
		this.hrmBuilder = hrmBuilder;
		this.fileChooser = new FileChooser(this);
		this.dialog = new Dialog();
		this.dialog.setVisible(false);
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			pack();
			this.setSize(468, 299);
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
			buildHrmButton.setBounds(274, 237, 176, 22);
			buildHrmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					buildHrmButtonActionPerformed(evt);
				}
			});
		}
		{
			jLabel1 = new JLabel();
			getContentPane().add(jLabel1);
			jLabel1.setText("Date:");
			jLabel1.setBounds(12, 92, 44, 15);
		}
		{
			jLabel2 = new JLabel();
			getContentPane().add(jLabel2);
			jLabel2.setText("Start Time:");
			jLabel2.setBounds(12, 113, 82, 15);
		}
		{
			jLabel3 = new JLabel();
			getContentPane().add(jLabel3);
			jLabel3.setText("Duration:");
			jLabel3.setBounds(12, 134, 82, 15);
		}
		{
			jLabel5 = new JLabel();
			getContentPane().add(jLabel5);
			jLabel5.setText("Maximum BPM:");
			jLabel5.setBounds(12, 155, 111, 15);
		}
		{
			jLabel6 = new JLabel();
			getContentPane().add(jLabel6);
			jLabel6.setText("S Mode:");
			jLabel6.setBounds(12, 176, 87, 15);
		}
		{
			jLabel7 = new JLabel();
			getContentPane().add(jLabel7);
			jLabel7.setText("Interval: ");
			jLabel7.setBounds(12, 197, 64, 15);
		}
		{
			dateLabel = new JLabel();
			getContentPane().add(dateLabel);
			dateLabel.setText("- - -");
			dateLabel.setBounds(140, 92, 209, 15);
		}
		{
			startTimeLabel = new JLabel();
			getContentPane().add(startTimeLabel);
			startTimeLabel.setText("- - -");
			startTimeLabel.setBounds(140, 113, 209, 15);
		}
		{
			durationLabel = new JLabel();
			getContentPane().add(durationLabel);
			durationLabel.setText("- - -");
			durationLabel.setBounds(140, 134, 209, 15);
		}
		{
			maxBpmLabel = new JLabel();
			getContentPane().add(maxBpmLabel);
			maxBpmLabel.setText("- - -");
			maxBpmLabel.setBounds(141, 155, 209, 15);
		}
		{
			sModeLabel = new JLabel();
			getContentPane().add(sModeLabel);
			sModeLabel.setText("- - -");
			sModeLabel.setBounds(141, 176, 209, 15);
		}
		{
			intervalLabel = new JLabel();
			getContentPane().add(intervalLabel);
			intervalLabel.setText("- - -");
			intervalLabel.setBounds(141, 197, 209, 15);
		}
		{
			fileOutputField = new JTextField();
			getContentPane().add(fileOutputField);
			fileOutputField.setBounds(12, 54, 250, 22);
			fileOutputField.setEditable(false);
			fileOutputField.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.LOWERED));
		}
		{
			browseOutputButton = new JButton();
			getContentPane().add(browseOutputButton);
			browseOutputButton.setText("Browse output file");
			browseOutputButton.setBounds(274, 56, 176, 20);
			browseOutputButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					browseOutputButtonActionPerformed(evt);
				}
			});
		}
		{
			progressBar = new JProgressBar();
			getContentPane().add(progressBar);
			progressBar.setBounds(12, 237, 250, 21);
		}
		{
			fileInputField = new JTextField();
			getContentPane().add(fileInputField);
			fileInputField.setBounds(12, 12, 250, 22);
			fileInputField.setEditable(false);
			fileInputField.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.LOWERED));
		}
		{
			browseInputButton = new JButton();
			getContentPane().add(browseInputButton);
			browseInputButton.setText("Browse input file");
			browseInputButton.setBounds(274, 12, 176, 22);
			browseInputButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					browseInputButtonActionPerformed(evt);
				}
			});
		}
	}

	private void setLabels() {
		// this.readedFromLabel.setText("ERROR!");
		// this.dateLabel.setText("- - -");
		// this.startTimeLabel.setText("- - -");
		// this.durationLabel.setText("- - -");
		// this.weightLabel.setText("- - -");
		// this.maxBpmLabel.setText("- - -");
		// this.sModeLabel.setText("- - -");
		// this.intervalLabel.setText("- - -");

		this.dateLabel.setText(this.hrmBuilder.getEditEntrys().getDate());
		this.startTimeLabel.setText(this.hrmBuilder.getEditEntrys()
				.getStartTime());
		this.durationLabel.setText(this.hrmBuilder.getEditEntrys()
				.getDuration());
		this.maxBpmLabel.setText(this.hrmBuilder.getEditEntrys().getMaxBPM());
		this.sModeLabel.setText(this.hrmBuilder.getEditEntrys().getsMode());
		this.intervalLabel.setText(this.hrmBuilder.getEditEntrys()
				.getInterval());
	}

	public void setInputFile(String file) {
		this.fileInputField.setText(file);
		this.progressBar.setIndeterminate(true);
		this.setEnabled(false);

		new Thread(new Runnable() {

			@Override
			public void run() {
				hrmBuilder.getDataFromFile(fileInputField.getText());
				hrmBuilder.findTargetWords();
				setLabels();
				progressBar.setIndeterminate(false);
				setEnabled(true);
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
		this.setEnabled(false);

		new Thread(new Runnable() {

			@Override
			public void run() {
				if (fileOutputField.getText().equals("")) {
					hrmBuilder.buildHrmFile();
				} else {
					if (fileOutputField.getText().toCharArray()[fileOutputField
							.getText().length() - 1] != 'm'
							|| fileOutputField.getText().toCharArray()[fileOutputField
									.getText().length() - 2] != 'r'
							|| fileOutputField.getText().toCharArray()[fileOutputField
									.getText().length() - 3] != 'h') {
						StringBuilder outputFileBuilder = new StringBuilder();
						outputFileBuilder.append(fileOutputField.getText()
								+ ".hrm");
						
						hrmBuilder.buildHrmFile(outputFileBuilder.toString());
					} else {
						hrmBuilder.buildHrmFile(fileOutputField.getText());
					}	
				}
				dialog.setText("HRM file successfully builded !");
				dialog.setTitle("Success");
				dialog.setVisible(true);
				progressBar.setIndeterminate(false);
				setEnabled(true);
			}
		}).start();
	}

}
