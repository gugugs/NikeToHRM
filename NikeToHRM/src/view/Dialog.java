package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.WindowConstants;

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
public class Dialog extends javax.swing.JFrame {
	private static final long serialVersionUID = -2710328701834244293L;
	private JLabel textLabel;
	private JButton dialogOkButton;
	private MainWindow mainWindow;
	public final int HRM_BUILDED = 434;
	public final int GPX_BUILDED = 455;
	public final int FILE_ERROR = 333;
	public final int DATA_ERROR = 444;
	private int mode;

	public Dialog(MainWindow mainWindow) {
		super();
		mode = 0;
		this.mainWindow = mainWindow;
		initGUI();
	}

	public void setText(String text) {
		this.textLabel.setText(text);
	}

	public void setMode(int mode) {
		this.mode = mode;
		if (mode == this.HRM_BUILDED) {
			this.setText("HRM file successfully builded !");
			this.setTitle("Success");
		} else if (mode == this.FILE_ERROR) {
			this.setText("Wrong file type");
			this.setTitle("Error");
		} else if (mode == this.DATA_ERROR) {
			this.setText("Cannot read Data");
			this.setTitle("Error");
		} else if (mode == this.GPX_BUILDED) {
			this.setText("GPX file successfully builded !");
			this.setTitle("Success");
		}
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			getContentPane().setLayout(null);
			{
				textLabel = new JLabel();
				getContentPane().add(textLabel);
				textLabel.setBounds(59, 37, 241, 15);
			}
			{
				dialogOkButton = new JButton();
				getContentPane().add(dialogOkButton);
				dialogOkButton.setText("OK");
				dialogOkButton.setBounds(125, 84, 75, 22);
				dialogOkButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						dialogOkButtonActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(337, 147);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void dialogOkButtonActionPerformed(ActionEvent evt) {
		if (this.mode == this.HRM_BUILDED || this.mode == this.GPX_BUILDED) {
			mainWindow.activate();
		} else if (mode == this.DATA_ERROR || mode == this.FILE_ERROR) {
			mainWindow.getBrowseInputButton().setEnabled(true);
		}
		this.setVisible(false);
	}

}
