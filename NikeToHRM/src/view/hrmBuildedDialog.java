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
public class hrmBuildedDialog extends javax.swing.JFrame {
	private static final long serialVersionUID = -2710328701834244293L;
	private JLabel jLabel1;
	private JButton dialogOkButton;

	public hrmBuildedDialog() {
		super();
		initGUI();
	}

	public JLabel getLabel() {
		return this.jLabel1;
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			getContentPane().setLayout(null);
			this.setTitle("Success");
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("HRM file successfully builded");
				jLabel1.setBounds(48, 12, 241, 15);
			}
			{
				dialogOkButton = new JButton();
				getContentPane().add(dialogOkButton);
				dialogOkButton.setText("OK");
				dialogOkButton.setBounds(111, 41, 75, 22);
				dialogOkButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						dialogOkButtonActionPerformed(evt);
					}
				});
			}
			pack();
			this.setSize(292, 100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void dialogOkButtonActionPerformed(ActionEvent evt) {
		this.setVisible(false);
	}

}
