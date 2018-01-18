package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import estimatingLocation.EstimatedMac;
import projectMap.ReadToKml;
import projectMap.WifiMapping;
import projectMap.WriteKml;

public class GuiApp {
	private WifiMapping wifiMapping;
	private ReadToKml readKml;
	private WriteKml writeKml;
	private String path = "";
	private JTextField txtData;
	private JTextField txtInput;
	private JFrame frame;
	private JButton btnDir, btnCsv, btnClear, btnSave, btnSaveKml;
	private JButton filT, filP, filId, unFilt, saveFilTocsv;
	private JButton algo1, algo2;
	private JButton btnSubmit;
	private JLabel lblInput;
	private int dirOrScv;
	private String csvFilePath = "";
	private String kmlPath = "";
	private String input;
	private int filtState;
	private int whichAlgo;

	public GuiApp() {
		wifiMapping = new WifiMapping();
	}

	public void buildPanel() {
		frame = new JFrame("Wifi Mapping");
		frame.setSize(600, 600);
		frame.setLocation(350, 20);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		buildBottomPanel();
		buildLeftPanel();
		buildRigthPanel();
		buildCenterPanel();
		frame.setVisible(true);
	}

	private void buildLeftPanel() {
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(211, 211, 211));
		leftPanel.setSize(200, 400);

		btnDir = new JButton("Add Dir");
		btnDir.setPreferredSize(new Dimension(150, 30));
		btnDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				path = openBrowserAndGetPath(1);
				if (path != "") {
					txtData.setText("Selected directory path:\n" + path);
					btnCsv.setEnabled(false);
					btnDir.setEnabled(false);
					btnClear.setEnabled(true);
					btnSave.setEnabled(true);
					dirOrScv = 2;
				}
			}
		});
		btnCsv = new JButton("Add CSV");
		btnCsv.setPreferredSize(new Dimension(150, 30));
		btnCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				path = openBrowserAndGetPath(0);
				if (path != "") {
					txtData.setText("Selected CSV path: " + path);
					btnDir.setEnabled(false);
					btnCsv.setEnabled(false);
					btnClear.setEnabled(true);
					btnSave.setEnabled(true);
					dirOrScv = 1;
				}
			}
		});
		btnClear = new JButton("Clear Data");
		btnClear.setPreferredSize(new Dimension(150, 30));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				path = "Please choose path";
				txtData.setText(path);
				btnCsv.setEnabled(true);
				btnDir.setEnabled(true);
				btnClear.setEnabled(false);
				btnSave.setEnabled(false);
				btnSaveKml.setEnabled(false);
				filId.setEnabled(false);
				filP.setEnabled(false);
				filT.setEnabled(false);
				algo1.setEnabled(false);
				algo2.setEnabled(false);
				lblInput.setText("Please select what field to filter by");
				File file = new File(csvFilePath);
				try {
					if (file.exists()) {
						Files.delete(file.toPath());
					}
					file = new File(kmlPath);
					if (file.exists()) {
						Files.delete(file.toPath());
					}
				} catch (Exception e2) {
				}
			}
		});
		btnSave = new JButton("Save2 CSV");
		btnSave.setPreferredSize(new Dimension(150, 30));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (dirOrScv == 1) {
					File csvFile = new File(path);
					csvFilePath = csvFile.getAbsolutePath().replace(csvFile.getName(), "Result.csv");
					wifiMapping.readFromFileAndBuildCsv(path, csvFilePath);
				} else if (dirOrScv == 2) {
					csvFilePath = path + "\\Result.csv";
					wifiMapping.readFromFolderAndBuildCsv(path, csvFilePath);
				}
				filId.setEnabled(true);
				filP.setEnabled(true);
				filT.setEnabled(true);
				btnSave.setEnabled(false);
				algo1.setEnabled(true);
				algo2.setEnabled(true);
				txtData.setText("CSV file successfully created at path: " + csvFilePath);
				lblInput.setText("Please select what field to filter by");
			}
		});

		btnSaveKml = new JButton("Save2 KML");
		btnSaveKml.setPreferredSize(new Dimension(150, 30));
		btnSaveKml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File csvFile = new File(csvFilePath);
				readKml = new ReadToKml(csvFile, filtState, input);
				kmlPath = csvFilePath.replace("Result.csv", "Result.kml");
				writeKml = new WriteKml(kmlPath);
				try {
					writeKml.writeToKml(readKml.getFilteredList());
					lblInput.setText("KML file created ar path: " + kmlPath);
					btnSaveKml.setEnabled(false);
					filId.setEnabled(false);
					filP.setEnabled(false);
					filT.setEnabled(false);
					btnClear.setEnabled(true);
					txtInput.setEnabled(false);
					txtInput.setText("");
					btnSubmit.setEnabled(false);
				} catch (Exception ex) {
					lblInput.setText("Error during KML file creation");
				}
			}
		});

		leftPanel.add(Box.createRigidArea(new Dimension(100, 20)));
		leftPanel.add(btnCsv);
		leftPanel.add(Box.createRigidArea(new Dimension(100, 20)));
		leftPanel.add(btnDir);
		leftPanel.add(Box.createRigidArea(new Dimension(100, 20)));
		leftPanel.add(btnClear);
		leftPanel.add(Box.createRigidArea(new Dimension(100, 20)));
		leftPanel.add(btnSave);
		leftPanel.add(Box.createRigidArea(new Dimension(100, 20)));
		leftPanel.add(btnSaveKml);
		leftPanel.add(Box.createRigidArea(new Dimension(100, 20)));
		leftPanel.setLocation(0, 0);
		btnClear.setEnabled(false);
		btnSave.setEnabled(false);
		btnSaveKml.setEnabled(false);
		frame.add(leftPanel);
	}

	private void buildCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(211, 211, 211));
		centerPanel.setSize(200, 400);
		centerPanel.setLayout(null);
		filT = new JButton("Filter by Time");
		filT.setPreferredSize(new Dimension(150, 30));
		filT.setBounds(225, 30, 150, 30);
		filT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtInput.setEnabled(true);
				lblInput.setText("Please enter in following format: yyyy-mm-dd hh:mm:ss");
				filtState = 0;
				txtInput.setEnabled(true);
				btnSubmit.setEnabled(true);
			}
		});
		filP = new JButton("Filter by Place");
		filP.setPreferredSize(new Dimension(150, 30));
		filP.setBounds(225, 90, 150, 30);
		filP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtInput.setEnabled(true);
				lblInput.setText("Please enter Lat, Lon");
				filtState = 2;
				btnSubmit.setEnabled(true);
			}
		});
		filId = new JButton("Filter by ID");
		filId.setPreferredSize(new Dimension(150, 30));
		filId.setBounds(225, 150, 150, 30);
		filId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtInput.setEnabled(true);
				lblInput.setText("Please enter in the Id");
				filtState = 1;
				btnSubmit.setEnabled(true);
			}
		});
		unFilt = new JButton("Unfilter");
		unFilt.setPreferredSize(new Dimension(150, 30));
		unFilt.setBounds(225, 210, 150, 30);
		saveFilTocsv = new JButton("Save filter to CSV");
		saveFilTocsv.setPreferredSize(new Dimension(150, 30));
		saveFilTocsv.setBounds(225, 270, 150, 30);
		centerPanel.add(filT);
		centerPanel.add(filP);
		centerPanel.add(filId);
		centerPanel.add(unFilt);
		centerPanel.add(saveFilTocsv);
		centerPanel.setLocation(200, 0);
		filId.setEnabled(false);
		filP.setEnabled(false);
		filT.setEnabled(false);
		unFilt.setEnabled(false);
		saveFilTocsv.setEnabled(false);
		frame.add(centerPanel);
	}

	private void buildRigthPanel() {
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(211, 211, 211));
		rightPanel.setSize(200, 400);
		algo1 = new JButton("Algorithm 1");
		algo1.setPreferredSize(new Dimension(150, 30));
		algo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				algo2.setEnabled(false);
				whichAlgo = 1;
				btnSubmit.setEnabled(true);
				txtInput.setEnabled(true);
				lblInput.setText("Please enter MAC address");
			}
		});
		algo2 = new JButton("Algorithm 2");
		algo2.setPreferredSize(new Dimension(150, 30));
		algo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				algo1.setEnabled(false);
				whichAlgo = 2;
				btnSubmit.setEnabled(true);
				txtInput.setEnabled(true);
				lblInput.setText("Please enter");
			}
		});
		rightPanel.add(Box.createRigidArea(new Dimension(100, 20)));
		rightPanel.add(algo1);
		rightPanel.add(Box.createRigidArea(new Dimension(100, 20)));
		rightPanel.add(algo2);
		rightPanel.setLocation(400, 0);
		algo1.setEnabled(false);
		algo2.setEnabled(false);
		frame.add(rightPanel);
	}

	private void buildBottomPanel() {
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(211, 211, 211));
		bottomPanel.setSize(600, 200);
		lblInput = new JLabel("");
		lblInput.setPreferredSize(new Dimension(350, 40));
		lblInput.setHorizontalAlignment(JLabel.CENTER);
		txtData = new JTextField();
		txtData.setVisible(true);
		txtData.setPreferredSize(new Dimension(500, 30));
		txtData.setVisible(true);
		txtInput = new JTextField();
		txtInput.setVisible(true);
		txtInput.setEnabled(false);
		txtInput.setPreferredSize(new Dimension(392, 30));
		btnSubmit = new JButton("Submit");
		btnSubmit.setVisible(true);
		btnSubmit.setEnabled(false);
		btnSubmit.setPreferredSize(new Dimension(100, 30));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input = txtInput.getText();
				if (input != "") {
					if (whichAlgo == 1) {
						EstimatedMac estimatedMac = new EstimatedMac(new File(csvFilePath));
						String result = estimatedMac.routerLocationByMac(input);
						txtData.setText(result);
					} else if (whichAlgo == 2) {
						//implement
					} else {
						btnSaveKml.setEnabled(true);
					}
				}
			}
		});
		bottomPanel.setLocation(0, 400);
		bottomPanel.add(lblInput);
		bottomPanel.add(txtInput);
		bottomPanel.add(btnSubmit);
		bottomPanel.add(txtData);
		frame.add(bottomPanel);
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
