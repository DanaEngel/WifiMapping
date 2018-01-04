package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import projectMap.WifiMapping;

public class GuiApp {
	private WifiMapping wifiMapping;
	private String path = "";
	private JTextField txtData1;

	public GuiApp() {
		wifiMapping = new WifiMapping();
	}

	public void buildPanel() {
		JFrame frame = new JFrame("Wifi Mapping");
		frame.setSize(600, 600);
		frame.setLocation(350, 20);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 0, 0));
		panel.setSize(200, 600);

		txtData1 = new JTextField();
		txtData1.setPreferredSize(new Dimension(180, 80));

		JButton btnDir = new JButton("Add Dir");
		btnDir.setPreferredSize(new Dimension(180, 30));
		btnDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				path = openBrowserAndGetPath(1);
				txtData1.setText("Selected directory path: " + path);
			}
		});

		JButton btnCsv = new JButton("Add CSV");
		btnCsv.setPreferredSize(new Dimension(180, 30));
		btnCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				path = openBrowserAndGetPath(0);
				txtData1.setText("Selected CSV path: " + path);
			}
		});

		JButton btnClear = new JButton("Clear Data");
		btnClear.setPreferredSize(new Dimension(180, 30));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				path = "Please choose path";
				txtData1.setText(path);
			}
		});
		
		JButton btnSave = new JButton("Save2 CSV");
		btnSave.setPreferredSize(new Dimension(180, 30));

		JButton btnSaveKml = new JButton("Save2 KML");
		btnSaveKml.setPreferredSize(new Dimension(180, 30));

		panel.add(Box.createRigidArea(new Dimension(100, 20)));
		panel.add(btnCsv);
		panel.add(Box.createRigidArea(new Dimension(100, 20)));
		panel.add(btnDir);
		panel.add(Box.createRigidArea(new Dimension(100, 20)));
		panel.add(btnClear);
		panel.add(Box.createRigidArea(new Dimension(100, 20)));
		panel.add(btnSave);
		panel.add(Box.createRigidArea(new Dimension(100, 20)));
		panel.add(btnSaveKml);
		panel.add(Box.createRigidArea(new Dimension(100, 20)));
		panel.add(txtData1);

		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(0, 255, 0));
		panel2.setSize(200, 600);
		JButton btnAdr = new JButton("Adr");
		panel2.add(btnAdr);

		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(0, 0, 255));
		panel3.setSize(200, 600);
		JButton btnA = new JButton("A");
		panel3.add(btnA);

		panel.setLocation(0, 0);
		panel.setLocation(200, 0);//
		panel.setLocation(400, 0);

		frame.add(panel);
		frame.add(panel2);
		frame.add(panel3);
		frame.setVisible(true);
	}

	private String openBrowserAndGetPath(int mode) {
		String resultPath = "";
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("C:\\"));
		chooser.setDialogTitle("Choose Directory");
		if (mode == 0) {
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		} else {
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			resultPath = chooser.getSelectedFile().getAbsolutePath();
		}
		return resultPath;
	}
}
