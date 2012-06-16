package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;

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
public class FileChooser extends javax.swing.JFrame {
	private static final long serialVersionUID = -7789326324349057512L;
	private JFileChooser fileChooser;
	private MainWindow mainWindow;
	public final int OUTPUT_MODE;
	public final int INPUT_MODE;
	private int mode;

	public FileChooser(MainWindow mainWindow) {
		super();
		this.setLocationRelativeTo(null);
		this.mainWindow = mainWindow;
		this.OUTPUT_MODE = 102;
		this.INPUT_MODE = 103;
		this.mode = 102;
		initGUI();
	}
	
	public void setMode(int mode) {
		this.mode = mode;
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Choose file...");
			{
				fileChooser = new JFileChooser();
				getContentPane().add(fileChooser, BorderLayout.CENTER);
				fileChooser.setPreferredSize(new java.awt.Dimension(515, 316));
				fileChooser.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						fileChooserActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(647, 392);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fileChooserActionPerformed(ActionEvent evt) {
		if (evt.getActionCommand().equals("CancelSelection")) {
			this.setVisible(false);
		} else if (evt.getActionCommand().equals("ApproveSelection")) {
			if (this.mode == this.OUTPUT_MODE) {
				this.mainWindow.setOutputFile(this.fileChooser
						.getSelectedFile().toString());
			} else {
				this.mainWindow.setInputFile(this.fileChooser
						.getSelectedFile().toString());
			}

			this.setVisible(false);
		}
	}

}
