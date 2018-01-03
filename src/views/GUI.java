package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import java.awt.Color;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import Filters.Filters;
import Tools.Kml;
import Tools.ReaderFromCsv;
import Tools.WriteTOcsv;
import WifiPoint.RowOfWifiPoints;

import javax.swing.border.MatteBorder;
import javax.swing.JInternalFrame;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import java.awt.CardLayout;
import java.awt.Panel;

public class GUI extends JFrame {

	private String csvFolderAbsPath;
	private ArrayList<RowOfWifiPoints>filteredList=new ArrayList<RowOfWifiPoints>();
	private String lat, lon;
	private FileChooser csvFolder;
	private ArrayList<String>listOfFiles;
	private ArrayList<RowOfWifiPoints>listToPrint;

	private JPanel contentPane;
	private JButton btnNewButton;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel pnlOptions;
	private JTextField txtKmlName;
	private JRadioButton rdbCreatekmlFile;
	private JRadioButton rdbtnCalculateNetworksLocation;
	private JRadioButton rdbtnCalculateClientsLocation;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpenFolder;
	private JMenuItem mntmOpenFile,mntmSaveAs,mntmDelete;
	private JButton btOpencsvFolder;
	private JTextArea txtConsole;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField txtMacAddress;
	private JPanel pnlFilter;
	private JPanel pnlCards;
	private JCheckBox chckbxTime;
	private JCheckBox chckbxGPS;
	private JCheckBox chckbxID;
	private JLabel lblTimeFormat;
	private JLabel lblStart;
	private JTextField txtStart;
	private JLabel lblEnd;
	private JTextField txtEnd;
	private JLabel lblLat, lblLon;
	private JTextField txtLat, txtLon;
	private JLabel lblTempStart;
	private JLabel lblTempEnd;
	private JTextField txtTempStart;
	private JTextField txtTempEnd;
	private CardLayout cardPanel;
	private Panel pnlFirstAlgo;
	private Panel pnlScndAlgo;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					GUI frame = new GUI();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI()
	{
		initComponents();
		createEvents();	
	}

	//////////////////////////////////////////////////////////////
	// This method contains all of the code for creating and
	// initializing components.
	//////////////////////////////////////////////////////////////
	//	@SuppressWarnings("static-access")
	private void initComponents()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 976, 681);

		csvFolder = new FileChooser();


		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmOpenFolder = new JMenuItem("Open folder");
		mntmOpenFile = new JMenuItem("Open merged file");
		mntmSaveAs = new JMenuItem("Save as");
		mntmDelete = new JMenuItem("Delete");

		mnFile.add(mntmOpenFolder);
		mnFile.add(mntmOpenFile);
		mnFile.add(mntmSaveAs);
		mnFile.add(mntmDelete);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);


		btOpencsvFolder = new JButton("run");


		rdbCreatekmlFile = new JRadioButton("Create .kml file from merged .csv");
		buttonGroup.add(rdbCreatekmlFile);
		rdbCreatekmlFile.setSelected(true);

		pnlOptions = new JPanel();
		pnlOptions.setBorder(new TitledBorder(null, "Options:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));

		rdbtnCalculateNetworksLocation = new JRadioButton("Calculate network's location (Algorithm 1)");


		buttonGroup.add(rdbtnCalculateNetworksLocation);

		rdbtnCalculateClientsLocation = new JRadioButton("Calculate client's location (Algorithm 2)");
		buttonGroup.add(rdbtnCalculateClientsLocation);

		JScrollPane scrConsole = new JScrollPane();
		scrConsole.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrConsole.setBackground(Color.GRAY);



		JProgressBar progressBar = new JProgressBar();


		txtConsole = new JTextArea();
		txtConsole.setEditable(false);
		scrConsole.setViewportView(txtConsole);

		MessageConsole mc = new MessageConsole(txtConsole);
		mc.redirectOut();
		mc.redirectErr(Color.RED, null);










		JLabel lblmouseOverFor = new JLabel("(mouse over for description)");
		GroupLayout gl_pnlOptions = new GroupLayout(pnlOptions);
		gl_pnlOptions.setHorizontalGroup(
				gl_pnlOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlOptions.createSequentialGroup()
						.addComponent(lblmouseOverFor)
						.addContainerGap(119, Short.MAX_VALUE))
				);
		gl_pnlOptions.setVerticalGroup(
				gl_pnlOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlOptions.createSequentialGroup()
						.addComponent(lblmouseOverFor)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		pnlCards = new JPanel();
		pnlCards.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		lblTimeFormat = new JLabel("Format: yyyy-mm-dd hh:mm:ss");
		lblTimeFormat.setVisible(false);
		lblTimeFormat.setEnabled(false);

		lblStart = new JLabel("Start time:");
		lblStart.setVisible(false);

		txtStart = new JTextField();
		txtStart.setVisible(false);
		txtStart.setColumns(10);

		lblEnd = new JLabel("End time: ");
		lblEnd.setVisible(false);

		txtEnd = new JTextField();
		txtEnd.setVisible(false);
		txtEnd.setColumns(10);
		
		lblTempStart = new JLabel("New label");
		lblTempStart.setVisible(false);
		
		lblTempEnd = new JLabel("New label");
		lblTempEnd.setVisible(false);
		
		txtTempStart = new JTextField();
		txtTempStart.setVisible(false);
		txtTempStart.setColumns(10);
		
		txtTempEnd = new JTextField();
		txtTempEnd.setVisible(false);
		txtTempEnd.setColumns(10);
		
		pnlCards.setLayout(new CardLayout(0, 0));

		pnlFilter = new JPanel();
		pnlCards.add(pnlFilter, "Filters");
		chckbxTime = new JCheckBox("Time");

		chckbxGPS = new JCheckBox("GPS Coordinates");
		

		chckbxID = new JCheckBox("Device ID");

		JLabel lblKmlFileName = new JLabel("Kml file name:");

		txtKmlName = new JTextField();
		txtKmlName.setColumns(10);
		
		pnlFirstAlgo = new Panel();
		pnlCards.add(pnlFirstAlgo, "firstalgo");

		JLabel lblMacAddress = new JLabel("MAC Address:");

		txtMacAddress = new JTextField();
		txtMacAddress.setColumns(10);

		JLabel lblNumberOfScans = new JLabel("Number of scans: ");
		pnlScndAlgo = new Panel();


		JSlider slNumOfScans = new JSlider();
		slNumOfScans.setPaintTicks(true);
		slNumOfScans.setSnapToTicks(true);
		slNumOfScans.setPaintLabels(true);
		slNumOfScans.setMinorTickSpacing(1);
		slNumOfScans.setValue(3);
		slNumOfScans.setMajorTickSpacing(1);
		slNumOfScans.setMinimum(1);
		slNumOfScans.setMaximum(5);
		slNumOfScans.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	
	//	cardPanel.addLayoutComponent(pnlFilter, "filter");
	//	cardPanel.addLayoutComponent(pnlFirstAlgo, "firstAlgo");
		//cardPanel.addLayoutComponent(pnlScndAlgo, "secondAlgo");
		
		
		
		
//		******************************* Messy code *******************************
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrConsole, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
							.addGap(12))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
							.addGap(12))
						.addComponent(btOpencsvFolder)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbCreatekmlFile)
								.addComponent(pnlOptions, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnCalculateNetworksLocation)
								.addComponent(rdbtnCalculateClientsLocation))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(pnlCards, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
											.addGap(28))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblTimeFormat)
											.addGap(118))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(103)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblStart)
										.addComponent(lblTempStart))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtTempStart)
										.addComponent(txtStart, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblEnd)
										.addComponent(lblTempEnd))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtTempEnd, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
										.addComponent(txtEnd, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
									.addContainerGap())))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(pnlOptions, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbCreatekmlFile)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnCalculateNetworksLocation)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnCalculateClientsLocation))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(pnlCards, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTimeFormat)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStart)
								.addComponent(txtStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEnd)
								.addComponent(txtEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblTempStart)
							.addComponent(txtTempStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblTempEnd)
							.addComponent(txtTempEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(44)
					.addComponent(scrConsole, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btOpencsvFolder)
					.addContainerGap())
		);
		
		GroupLayout gl_pnlFilters = new GroupLayout(pnlFilter);
		gl_pnlFilters.setHorizontalGroup(
				gl_pnlFilters.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFilters.createSequentialGroup()
						.addGap(15)
						.addGroup(gl_pnlFilters.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlFilters.createSequentialGroup()
										.addComponent(lblKmlFileName)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtKmlName, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlFilters.createSequentialGroup()
										.addComponent(chckbxTime)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(chckbxGPS)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(chckbxID)))
						.addGap(19))
				);
		gl_pnlFilters.setVerticalGroup(
				gl_pnlFilters.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFilters.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_pnlFilters.createParallelGroup(Alignment.BASELINE)
								.addComponent(chckbxTime)
								.addComponent(chckbxGPS)
								.addComponent(chckbxID))
						.addGap(18)
						.addGroup(gl_pnlFilters.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblKmlFileName)
								.addComponent(txtKmlName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(31, Short.MAX_VALUE))
				);
		pnlFilter.setLayout(gl_pnlFilters);

		
		GroupLayout gl_pnlFirstAlgo = new GroupLayout(pnlFirstAlgo);
		gl_pnlFirstAlgo.setHorizontalGroup(
				gl_pnlFirstAlgo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFirstAlgo.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_pnlFirstAlgo.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnlFirstAlgo.createSequentialGroup()
										.addComponent(lblMacAddress)
										.addGap(23))
								.addGroup(gl_pnlFirstAlgo.createSequentialGroup()
										.addComponent(lblNumberOfScans)
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_pnlFirstAlgo.createParallelGroup(Alignment.LEADING)
								.addComponent(txtMacAddress, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
								.addComponent(slNumOfScans, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		gl_pnlFirstAlgo.setVerticalGroup(
				gl_pnlFirstAlgo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFirstAlgo.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_pnlFirstAlgo.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMacAddress)
								.addComponent(txtMacAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlFirstAlgo.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlFirstAlgo.createSequentialGroup()
										.addGap(7)
										.addComponent(slNumOfScans, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlFirstAlgo.createSequentialGroup()
										.addGap(18)
										.addComponent(lblNumberOfScans)))
						.addContainerGap())
				);
		pnlFirstAlgo.setLayout(gl_pnlFirstAlgo);

		pnlCards.add(pnlScndAlgo, "secondalgo");
		pnlOptions.setLayout(gl_pnlOptions);
		contentPane.setLayout(gl_contentPane);

	}

	//////////////////////////////////////////////////////////////
	// This method contains all of the code for creating events
	//////////////////////////////////////////////////////////////
	private void createEvents()
	{
		mntmOpenFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(csvFolder.fileChooser.showOpenDialog(mntmOpenFolder) == JFileChooser.APPROVE_OPTION) 
				{
					csvFolderAbsPath = csvFolder.fileChooser.getSelectedFile().getAbsolutePath();
					listOfFiles = ReaderFromCsv.getAllcsvFilesFromFolder(csvFolderAbsPath);
					try {
						listToPrint = ReaderFromCsv.notSortedFileToArrayListOfTenMostPowerfulWifiPoints(listOfFiles);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					try {
						WriteTOcsv.writer(listToPrint, csvFolderAbsPath.concat(".csv"));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		btOpencsvFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filteredList.isEmpty()){
					System.out.println("Not in list");
				}
				filteredList=Filters.mostPowerfulWifiWithSameMac(filteredList);
				Kml.Kml();
				File file=new File(csvFolderAbsPath.replace(".csv", ".kml"));
				Kml.addMarksFromList(filteredList);
				Kml.writeFile(file);
			}
		});

		rdbCreatekmlFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardPanel.show(pnlFilter, "filter");
			}
		});

		rdbtnCalculateNetworksLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardPanel.show(pnlFirstAlgo, "firstAlgo");

			}
		});


		chckbxTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStart.setText("Start time: ");
				lblEnd.setText("End time: ");
				lblTimeFormat.setVisible(chckbxTime.isSelected());
				lblStart.setVisible(chckbxTime.isSelected());
				lblEnd.setVisible(chckbxTime.isSelected());
				txtStart.setVisible(chckbxTime.isSelected());
				txtEnd.setVisible(chckbxTime.isSelected());			
				if(chckbxGPS.isSelected())
				{
					
				}
			}
		});
		
		chckbxGPS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStart.setText("Latitude: ");
				lblEnd.setText("Longtitude: ");
				lblStart.setVisible(chckbxGPS.isSelected());
				lblEnd.setVisible(chckbxGPS.isSelected());
				txtStart.setVisible(chckbxGPS.isSelected());
				txtEnd.setVisible(chckbxGPS.isSelected());	
				txtLat = txtStart;
				txtLon = txtEnd;
				
			}
		});
	
		
		
		//		rdbtnCalculateClientsLocation.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent arg0) {
		//				firstAlgo();
		//				pnlFilterTitle = "Choose network:";
		//				pnlFilter.setBorder(new TitledBorder(null, pnlFilterTitle, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		//
		//			}
		//		});
	}

	private void filters()
	{
//		pnlCards.
	}

	private void firstAlgo()
	{

	}
}
