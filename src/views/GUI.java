package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import Filters.*;
import Tools.*;
import WifiData.*;

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
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {

	private String csvFolderAbsPath;
	private ListOfWifiRows filteringList = new ListOfWifiRows();
	private String lat, lon, startTime, endTime, mac;
	private FileChooser csvFolder;
	private FileChooser SaveCSV;
	private FileChooser SaveKML;
	private ArrayList<String> listOfFiles;
	private ListOfWifiRows mergedList;
	private ListOfWifiRows filteredList;

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel pnlOptions;
	private JRadioButton rdbCreatekmlFile;
	private JRadioButton rdbtnCalculateNetworksLocation;
	private JRadioButton rdbtnCalculateClientsLocation;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpenFolder;
	private JButton btRun;
	private JTextArea txtConsole;
	private JTextField txtMacAddress;
	private JPanel pnlFilter;
	private JPanel pnlCardsFilters;
	private JCheckBox chckbxTime;
	private JCheckBox chckbxGPS;
	private JCheckBox chckbxID;
	private JLabel lblTimeFormat;
	private JLabel lblStartTime;
	private JTextField txtStartTime;
	private JLabel lblEndTime;
	private JTextField txtEndTime;
	private JLabel lblLat;
	private JLabel lblLon;
	private JTextField txtLat;
	private JTextField txtLon;
	private Panel pnlFirstAlgo;
	private Panel pnlScndAlgo;
	private JPanel pnlGPS;
	private JPanel pnlID;
	private JTextField txtID;
	private JLabel lblStartTimeExtra;
	private JTextField txtStartTimeExtra;
	private JLabel lblEndTimeExtra;
	private JTextField txtEndTimeExtra;
	private JLabel lblLatExtra;
	private JTextField txtLatExtra;
	private JLabel lblLonExtra;
	private JTextField txtLonExtra;
	private JPanel pnlGPSID;
	private JLabel lblLatExtra2;
	private JTextField txtLatExtra2;
	private JLabel lblLonExtra2;
	private JTextField txtLonExtra2;
	private JLabel lblIDExtra;
	private JTextField txtIDExtra;
	private JPanel pnlTimeID;
	private JLabel lblStartTimeExtra2;
	private JTextField txtStartTimeExtra2;
	private JLabel lblEndTimeExtra2;
	private JTextField txtEndTimeExtra2;
	private JLabel lblIDExtra2;
	private JTextField txtIDExtra2;
	private JPanel pnlTimeGPSID;
	private JLabel lblStartTimeExtra3;
	private JTextField txtStartTimeExtra3;
	private JLabel lblEndTimeExtra3;
	private JTextField txtEndTimeExtra3;
	private JLabel lblLatExtra3;
	private JTextField txtLatExtra3;
	private JLabel lblLonExtra3;
	private JTextField txtLonExtra3;
	private JLabel lblIDExtra3;
	private JTextField txtIDExtra3;
	private JPanel pnlAddOptions;
	private JButton btnCreateMergedCsv;
	private JButton btnCreateKmlFile;
	private JSlider slNumOfScans;
	private JPanel pnlTime;
	private JLabel lblLatitude;
	private JTextField txtLat2;
	private JLabel lblLongtitude;
	private JTextField txtLon2;
	private JLabel lblLat2Extra;
	private JTextField txtLat2Extra;
	private JLabel lblLongtitude_1;
	private JTextField txtLon2Extra;
	private JTextField txtLat2Extra2;
	private JTextField txtLon2Extra2;
	private JTextField txtLat2Extra3;
	private JTextField txtLon2Extra3;
	private JMenuItem mntmOpenMergedCsv;
	private JPanel pnlBlank;
	private JPanel pnlMergedInfo;


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
		setBounds(100, 100, 809, 601);

		csvFolder = new FileChooser();


		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmOpenFolder = new JMenuItem("Open folder / csv file");

		mnFile.add(mntmOpenFolder);
		
		mntmOpenMergedCsv = new JMenuItem("Open merged csv file");
		
		mnFile.add(mntmOpenMergedCsv);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);


		btRun = new JButton("run");


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

		pnlCardsFilters = new JPanel();
		pnlCardsFilters.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		pnlCardsFilters.setLayout(new CardLayout(0, 0));

		pnlFilter = new JPanel();

		pnlCardsFilters.add(pnlFilter, "Filters");
		chckbxTime = new JCheckBox("Time");

		chckbxGPS = new JCheckBox("GPS Coordinates");


		chckbxID = new JCheckBox("Device ID");

		pnlFirstAlgo = new Panel();
		pnlCardsFilters.add(pnlFirstAlgo, "firstAlgo");

		JLabel lblMacAddress = new JLabel("MAC Address:");

		txtMacAddress = new JTextField();
		txtMacAddress.setColumns(10);

		JLabel lblNumberOfScans = new JLabel("Number of scans: ");
		pnlScndAlgo = new Panel();


		slNumOfScans = new JSlider();
		slNumOfScans.setPaintTicks(true);
		slNumOfScans.setSnapToTicks(true);
		slNumOfScans.setPaintLabels(true);
		slNumOfScans.setMinorTickSpacing(1);
		slNumOfScans.setValue(3);
		slNumOfScans.setMajorTickSpacing(1);
		slNumOfScans.setMinimum(1);
		slNumOfScans.setMaximum(5);
		slNumOfScans.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		pnlAddOptions = new JPanel();
		
		pnlAddOptions.setLayout(new CardLayout(0, 0));
		
		pnlBlank = new JPanel();
		pnlAddOptions.add(pnlBlank, "blank");

		pnlTime = new JPanel();
		pnlAddOptions.add(pnlTime, "Time");

		lblStartTime = new JLabel("Start time:");

		txtStartTime = new JTextField();
		txtStartTime.setColumns(10);

		lblEndTime = new JLabel("End time: ");

		txtEndTime = new JTextField();
		txtEndTime.setColumns(10);

		lblTimeFormat = new JLabel("Format: yyyy-mm-dd hh:mm:ss");

		pnlGPS = new JPanel();
		pnlAddOptions.add(pnlGPS, "GPS");

		lblLat = new JLabel("Latitude: ");
		lblLat.setVisible(true);

		txtLat = new JTextField();
		txtLat.setVisible(true);
		txtLat.setColumns(10);

		lblLon = new JLabel("Longtitude: ");
		lblLon.setVisible(true);

		txtLon = new JTextField();
		txtLon.setVisible(true);
		txtLon.setColumns(10);
		
		lblLatitude = new JLabel("Latitude 2:");
		
		txtLat2 = new JTextField();
		txtLat2.setColumns(10);
		
		lblLongtitude = new JLabel("Longtitude 2:");
		
		txtLon2 = new JTextField();
		txtLon2.setColumns(10);

		pnlID = new JPanel();
		pnlAddOptions.add(pnlID, "ID");

		JLabel lblID = new JLabel("ID: ");

		txtID = new JTextField();
		txtID.setColumns(10);
		
		JPanel pnlGPSTime = new JPanel();
		pnlAddOptions.add(pnlGPSTime, "GPSTime");

		lblStartTimeExtra = new JLabel("Start Time: ");

		txtStartTimeExtra = new JTextField();
		txtStartTimeExtra.setColumns(10);

		txtEndTimeExtra = new JTextField();
		txtEndTimeExtra.setText("");
		txtEndTimeExtra.setColumns(10);

		lblLatExtra = new JLabel("Latitude:");

		txtLatExtra = new JTextField();
		txtLatExtra.setColumns(10);

		txtLonExtra = new JTextField();
		txtLonExtra.setColumns(10);
		
		lblLat2Extra = new JLabel("Latitude 2:");
		
		txtLat2Extra = new JTextField();
		txtLat2Extra.setColumns(10);
		
		lblLongtitude_1 = new JLabel("Longtitude 2:");
		
		txtLon2Extra = new JTextField();
		txtLon2Extra.setColumns(10);
		
		pnlGPSID = new JPanel();
		pnlAddOptions.add(pnlGPSID, "GPSID");

		lblLatExtra2 = new JLabel("Latitude: ");

		txtLatExtra2 = new JTextField();
		txtLatExtra2.setColumns(10);

		lblLonExtra2 = new JLabel("Longtitude: ");

		txtLonExtra2 = new JTextField();
		txtLonExtra2.setColumns(10);

		lblIDExtra = new JLabel("ID: ");

		txtIDExtra = new JTextField();
		txtIDExtra.setColumns(10);
		
		JLabel lblLatitudeExtra2 = new JLabel("Latitude 2:");
		
		txtLat2Extra2 = new JTextField();
		txtLat2Extra2.setColumns(10);
		
		JLabel lblLongtitudeExtra2 = new JLabel("Longtitude 2");
		
		txtLon2Extra2 = new JTextField();
		txtLon2Extra2.setColumns(10);
		
		pnlTimeID = new JPanel();
		pnlAddOptions.add(pnlTimeID, "TimeID");

		lblStartTimeExtra2 = new JLabel("Start Time: ");

		txtStartTimeExtra2 = new JTextField();
		txtStartTimeExtra2.setColumns(10);

		lblEndTimeExtra2 = new JLabel("End Time: ");

		txtEndTimeExtra2 = new JTextField();
		txtEndTimeExtra2.setColumns(10);

		lblIDExtra2 = new JLabel("ID:");

		txtIDExtra2 = new JTextField();
		txtIDExtra2.setColumns(10);
		
		pnlTimeGPSID = new JPanel();
		pnlAddOptions.add(pnlTimeGPSID, "TimeGPSID");

		lblStartTimeExtra3 = new JLabel("Start Time:");

		txtStartTimeExtra3 = new JTextField();
		txtStartTimeExtra3.setColumns(10);

		lblEndTimeExtra3 = new JLabel("End Time:");

		txtEndTimeExtra3 = new JTextField();
		txtEndTimeExtra3.setColumns(10);

		lblLatExtra3 = new JLabel("Latitude: ");

		txtLatExtra3 = new JTextField();
		txtLatExtra3.setColumns(10);

		lblLonExtra3 = new JLabel("Longtitude: ");

		txtLonExtra3 = new JTextField();
		txtLonExtra3.setColumns(10);

		lblIDExtra3 = new JLabel("ID:");

		txtIDExtra3 = new JTextField();
		txtIDExtra3.setColumns(10);
		
		JLabel lblLatitude_1 = new JLabel("Latitude 2:");
		
		txtLat2Extra3 = new JTextField();
		txtLat2Extra3.setColumns(10);
		
		JLabel lblLongtitude_2 = new JLabel("Longtitude 2:");
		
		txtLon2Extra3 = new JTextField();
		txtLon2Extra3.setColumns(10);

		btnCreateKmlFile = new JButton("Create kml file");

		btnCreateMergedCsv = new JButton("Create merged csv file");
		
		pnlMergedInfo = new JPanel();


		//		******************************* Messy code *******************************

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btRun)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbCreatekmlFile)
								.addComponent(pnlOptions, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnCalculateNetworksLocation)
								.addComponent(rdbtnCalculateClientsLocation)
								.addComponent(pnlMergedInfo, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE))
							.addGap(118)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(pnlCardsFilters, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
								.addComponent(pnlAddOptions, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrConsole, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE))
					.addGap(47))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(pnlOptions, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbCreatekmlFile)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnCalculateNetworksLocation)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnCalculateClientsLocation)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(pnlMergedInfo, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(pnlCardsFilters, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(pnlAddOptions, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
					.addGap(20)
					.addComponent(scrConsole, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(btRun)
					.addContainerGap())
		);
		
		//lblTimeFormat.setVisible(true);
		lblTimeFormat.setEnabled(true);
		GroupLayout gl_pnlTime = new GroupLayout(pnlTime);
		gl_pnlTime.setHorizontalGroup(
				gl_pnlTime.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTime.createSequentialGroup()
						.addGroup(gl_pnlTime.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlTime.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblStartTime)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtStartTime, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblEndTime)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtEndTime, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlTime.createSequentialGroup()
										.addGap(84)
										.addComponent(lblTimeFormat)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		gl_pnlTime.setVerticalGroup(
				gl_pnlTime.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTime.createSequentialGroup()
						.addComponent(lblTimeFormat)
						.addGap(12)
						.addGroup(gl_pnlTime.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStartTime)
								.addComponent(txtStartTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEndTime)
								.addComponent(txtEndTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(53, Short.MAX_VALUE))
				);
		pnlTime.setLayout(gl_pnlTime);

		GroupLayout gl_pnlGPS = new GroupLayout(pnlGPS);
		gl_pnlGPS.setHorizontalGroup(
			gl_pnlGPS.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlGPS.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlGPS.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLat)
						.addComponent(lblLatitude))
					.addGap(10)
					.addGroup(gl_pnlGPS.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnlGPS.createSequentialGroup()
							.addComponent(txtLat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblLon)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtLon))
						.addGroup(gl_pnlGPS.createSequentialGroup()
							.addComponent(txtLat2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblLongtitude)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtLon2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_pnlGPS.setVerticalGroup(
			gl_pnlGPS.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlGPS.createSequentialGroup()
					.addGroup(gl_pnlGPS.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtLat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLat)
						.addComponent(lblLon)
						.addComponent(txtLon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlGPS.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtLat2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLongtitude)
						.addComponent(txtLon2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLatitude))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		pnlGPS.setLayout(gl_pnlGPS);

		
		GroupLayout gl_pnlID = new GroupLayout(pnlID);
		gl_pnlID.setHorizontalGroup(
				gl_pnlID.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlID.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblID)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(177, Short.MAX_VALUE))
				);
		gl_pnlID.setVerticalGroup(
				gl_pnlID.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlID.createSequentialGroup()
						.addGroup(gl_pnlID.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblID))
						.addContainerGap(73, Short.MAX_VALUE))
				);
		pnlID.setLayout(gl_pnlID);

		
		
				lblLonExtra = new JLabel("Longtitude:");
		
				lblEndTimeExtra = new JLabel("End Time: ");
		GroupLayout gl_pnlGPSTime = new GroupLayout(pnlGPSTime);
		gl_pnlGPSTime.setHorizontalGroup(
			gl_pnlGPSTime.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlGPSTime.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlGPSTime.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLat2Extra)
						.addComponent(lblStartTimeExtra)
						.addComponent(lblLatExtra))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlGPSTime.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnlGPSTime.createSequentialGroup()
							.addComponent(txtLat2Extra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblLongtitude_1))
						.addGroup(gl_pnlGPSTime.createSequentialGroup()
							.addComponent(txtLatExtra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblLonExtra))
						.addGroup(gl_pnlGPSTime.createSequentialGroup()
							.addComponent(txtStartTimeExtra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblEndTimeExtra)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlGPSTime.createParallelGroup(Alignment.LEADING)
						.addComponent(txtEndTimeExtra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLonExtra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLon2Extra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(3, Short.MAX_VALUE))
		);
		gl_pnlGPSTime.setVerticalGroup(
			gl_pnlGPSTime.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlGPSTime.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlGPSTime.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartTimeExtra)
						.addComponent(txtStartTimeExtra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEndTimeExtra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEndTimeExtra))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlGPSTime.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtLatExtra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLatExtra)
						.addComponent(txtLonExtra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLonExtra))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlGPSTime.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLat2Extra)
						.addComponent(txtLat2Extra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLongtitude_1)
						.addComponent(txtLon2Extra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pnlGPSTime.setLayout(gl_pnlGPSTime);

		
		GroupLayout gl_pnlGPSID = new GroupLayout(pnlGPSID);
		gl_pnlGPSID.setHorizontalGroup(
			gl_pnlGPSID.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlGPSID.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlGPSID.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlGPSID.createSequentialGroup()
							.addGroup(gl_pnlGPSID.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblIDExtra)
								.addComponent(lblLatExtra2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_pnlGPSID.createParallelGroup(Alignment.LEADING)
								.addComponent(txtIDExtra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pnlGPSID.createSequentialGroup()
									.addGroup(gl_pnlGPSID.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblLongtitudeExtra2)
										.addGroup(gl_pnlGPSID.createSequentialGroup()
											.addComponent(txtLatExtra2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblLonExtra2)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_pnlGPSID.createParallelGroup(Alignment.LEADING)
										.addComponent(txtLon2Extra2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtLonExtra2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_pnlGPSID.createSequentialGroup()
							.addComponent(lblLatitudeExtra2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtLat2Extra2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_pnlGPSID.setVerticalGroup(
			gl_pnlGPSID.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlGPSID.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlGPSID.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLatExtra2)
						.addComponent(txtLatExtra2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLonExtra2)
						.addComponent(txtLonExtra2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlGPSID.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLatitudeExtra2)
						.addComponent(txtLat2Extra2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLongtitudeExtra2)
						.addComponent(txtLon2Extra2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addGroup(gl_pnlGPSID.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIDExtra)
						.addComponent(txtIDExtra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		pnlGPSID.setLayout(gl_pnlGPSID);

		
		GroupLayout gl_pnlTimeID = new GroupLayout(pnlTimeID);
		gl_pnlTimeID.setHorizontalGroup(
				gl_pnlTimeID.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTimeID.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_pnlTimeID.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblStartTimeExtra2)
								.addComponent(lblIDExtra2))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_pnlTimeID.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlTimeID.createSequentialGroup()
										.addComponent(txtStartTimeExtra2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblEndTimeExtra2)
										.addGap(4)
										.addComponent(txtEndTimeExtra2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtIDExtra2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(11, Short.MAX_VALUE))
				);
		gl_pnlTimeID.setVerticalGroup(
				gl_pnlTimeID.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTimeID.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_pnlTimeID.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStartTimeExtra2)
								.addComponent(txtStartTimeExtra2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEndTimeExtra2)
								.addComponent(txtEndTimeExtra2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_pnlTimeID.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIDExtra2)
								.addComponent(txtIDExtra2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(30, Short.MAX_VALUE))
				);
		pnlTimeID.setLayout(gl_pnlTimeID);

		
		GroupLayout gl_pnlTimeGPSID = new GroupLayout(pnlTimeGPSID);
		gl_pnlTimeGPSID.setHorizontalGroup(
			gl_pnlTimeGPSID.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTimeGPSID.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlTimeGPSID.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTimeGPSID.createSequentialGroup()
							.addComponent(lblStartTimeExtra3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtStartTimeExtra3, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblEndTimeExtra3))
						.addGroup(gl_pnlTimeGPSID.createSequentialGroup()
							.addGroup(gl_pnlTimeGPSID.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblIDExtra3)
								.addComponent(lblLatExtra3))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_pnlTimeGPSID.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlTimeGPSID.createSequentialGroup()
									.addComponent(txtLatExtra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblLonExtra3))
								.addComponent(txtIDExtra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_pnlTimeGPSID.createSequentialGroup()
							.addComponent(lblLatitude_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtLat2Extra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblLongtitude_2)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlTimeGPSID.createParallelGroup(Alignment.LEADING)
						.addComponent(txtLon2Extra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLonExtra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEndTimeExtra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_pnlTimeGPSID.setVerticalGroup(
			gl_pnlTimeGPSID.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTimeGPSID.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlTimeGPSID.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartTimeExtra3)
						.addComponent(lblEndTimeExtra3)
						.addComponent(txtEndTimeExtra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtStartTimeExtra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlTimeGPSID.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLatExtra3)
						.addComponent(txtLatExtra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLonExtra3)
						.addComponent(txtLonExtra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_pnlTimeGPSID.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLatitude_1)
						.addComponent(txtLat2Extra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLongtitude_2)
						.addComponent(txtLon2Extra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlTimeGPSID.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIDExtra3)
						.addComponent(txtIDExtra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		pnlTimeGPSID.setLayout(gl_pnlTimeGPSID);
		

		GroupLayout gl_pnlFilters = new GroupLayout(pnlFilter);
		gl_pnlFilters.setHorizontalGroup(
				gl_pnlFilters.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFilters.createSequentialGroup()
						.addGap(15)
						.addGroup(gl_pnlFilters.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnlFilters.createSequentialGroup()
										.addComponent(btnCreateMergedCsv)
										.addGap(18)
										.addComponent(btnCreateKmlFile))
								.addGroup(gl_pnlFilters.createSequentialGroup()
										.addComponent(chckbxTime)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(chckbxGPS)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(chckbxID)
										.addGap(14)))
						.addContainerGap(52, Short.MAX_VALUE))
				);
		gl_pnlFilters.setVerticalGroup(
				gl_pnlFilters.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFilters.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_pnlFilters.createParallelGroup(Alignment.BASELINE)
								.addComponent(chckbxTime)
								.addComponent(chckbxGPS)
								.addComponent(chckbxID))
						.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
						.addGroup(gl_pnlFilters.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCreateKmlFile)
								.addComponent(btnCreateMergedCsv))
						.addContainerGap())
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
		pnlCardsFilters.add(pnlScndAlgo, "secondAlgo");
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
					File temp = new File(csvFolderAbsPath);
					if(temp.isDirectory())
					{
						listOfFiles = ReaderFromCsv.getAllcsvFilesFromFolder(csvFolderAbsPath);
						try {
							mergedList = ReaderFromCsv.notSortedFileToArrayListOfTenMostPowerfulWifiPoints(listOfFiles);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					else {
						try {
							ArrayList<String>f=new ArrayList<>();
							f.add(csvFolderAbsPath);
							mergedList = ReaderFromCsv.notSortedFileToArrayListOfTenMostPowerfulWifiPoints(f);
							System.out.println("Saved file to list");
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
				}
			}
		});
		
		
		//*********************** need to differentiate from merged file and wigle file before reading and adding to list *************
		mntmOpenMergedCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				csvFolder.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if(csvFolder.fileChooser.showOpenDialog(mntmOpenFolder) == JFileChooser.APPROVE_OPTION) 
				{
					csvFolderAbsPath = csvFolder.fileChooser.getSelectedFile().getAbsolutePath();
					try {
						mergedList = ReaderFromCsv.readerFromMergedCSVtoList(csvFolderAbsPath);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});

		btRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mergedList.isEmpty()){
					System.out.println("Not in list");
				}
				mergedList=Filters.mostPowerfulWifiWithSameMac(mergedList);
				Kml.Kml();
				File file=new File(csvFolderAbsPath+".kml");
				Kml.addMarksFromList(mergedList);
				Kml.writeFile(file);
			}
		});

		rdbCreatekmlFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout) pnlCardsFilters.getLayout();
				cardLayout.show(pnlCardsFilters, "Filters");
			}
		});

		rdbtnCalculateNetworksLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) pnlCardsFilters.getLayout();
				cardLayout.show(pnlCardsFilters, "firstAlgo");

			}
		});


		chckbxTime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckbxTime.isSelected())
				{
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "Time");
					if(chckbxGPS.isSelected() && chckbxID.isSelected())
					{
						cardLayout.show(pnlAddOptions, "TimeGPSID");
					}
					else if(chckbxGPS.isSelected())
					{
						cardLayout.show(pnlAddOptions, "GPSTime");
					}
					else if(chckbxID.isSelected())
					{
						cardLayout.show(pnlAddOptions, "TimeID");
					}
				}
				else if(chckbxGPS.isSelected() && chckbxID.isSelected())
				{
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "GPSID");
				}
				else if(chckbxGPS.isSelected())
				{
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "GPS");
				}
				else if(chckbxID.isSelected())
				{
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "ID");
				}
				else
				{
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "blank");
				}

			}
		});

		chckbxGPS.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckbxGPS.isSelected())
				{
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "GPS");
					if(chckbxTime.isSelected() && chckbxID.isSelected())
					{
						cardLayout.show(pnlAddOptions, "TimeGPSID");
					}
					else if(chckbxTime.isSelected())
					{
						cardLayout.show(pnlAddOptions, "GPSTime");
					}
					else if(chckbxID.isSelected())
					{
						cardLayout.show(pnlAddOptions, "GPSID");
					}
				}
				else if(chckbxTime.isSelected() && chckbxID.isSelected())
				{
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "TimeID");
				}
				else if(chckbxTime.isSelected())
				{
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "Time");
				}
				else if(chckbxID.isSelected())
				{
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "ID");
				}
				else
				{
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "blank");
				}

			}
		});

		chckbxID.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckbxID.isSelected())
				{
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "ID");
					if(chckbxGPS.isSelected() && chckbxTime.isSelected())
					{
						cardLayout.show(pnlAddOptions, "TimeGPSID");
					}
					else if(chckbxGPS.isSelected())
					{
						cardLayout.show(pnlAddOptions, "GPSID");
					}
					else if(chckbxTime.isSelected())
					{
						cardLayout.show(pnlAddOptions, "TimeID");
					}
				}
				else if(chckbxGPS.isSelected() && chckbxTime.isSelected())
				{
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "GPSTime");
				}
				else if(chckbxGPS.isSelected())
				{
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "GPS");
				}
				else if(chckbxTime.isSelected())
				{
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "Time");
				}
				else
				{
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "blank");
				}

			}
		});





		btnCreateMergedCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(chckbxGPS.isSelected()) 
				{
					try {
						Coordinates_3D a=new Coordinates_3D(txtLat.getText(), txtLon.getText(), "0");
						Coordinates_3D b=new Coordinates_3D(txtLat2.getText(), txtLon2.getText(), "0");
						filter coor=new Position_Filter_Rect(a,b);
						filteredList.filter(coor);
						} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else if(chckbxGPS.isSelected() && chckbxTime.isSelected())
				{
					try {
						
						filter time=new Time_Filter(txtStartTimeExtra.getText(),txtEndTimeExtra.getText());
						filteredList.filter(time);
						
						//here you need to change it to rect filter
						filteredList = Filters.filteringByCoordinates(filteredList, txtLatExtra.getText(), txtLonExtra.getText());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else if(chckbxTime.isSelected())
				{
					try {
						ListOfWifiRows filteredList = mergedList.copy();
						filter time=new Time_Filter(txtStartTime.getText(),txtEndTime.getText());
						filteredList.filter(time);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else if(chckbxGPS.isSelected() && chckbxID.isSelected() && chckbxTime.isSelected())
				{
					try {
						filter id=new Id_Filter(txtIDExtra3.getText());
						filter time=new Time_Filter(txtStartTimeExtra3.getText(),txtEndTimeExtra3.getText());
						filter and=new And_Filter(id,time);
						filteredList.filter(and);
						
						//here you need to change it to rect filter
						filteredList = Filters.filteringByCoordinates(filteredList, txtLatExtra3.getText(), txtLonExtra3.getText());
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else if(chckbxGPS.isSelected() && chckbxID.isSelected())
				{
					try {
						filter id=new Id_Filter(txtIDExtra.getText());
						filteredList.filter(id);
						//here you need to change it to rect filter
						filteredList = Filters.filteringByCoordinates(mergedList, txtLatExtra2.getText(), txtLonExtra2.getText());
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else if(chckbxTime.isSelected() && chckbxID.isSelected())
				{
					try {
						ListOfWifiRows filteredList = mergedList.copy();
						filter id=new Id_Filter(txtIDExtra2.getText());
						filter time=new Time_Filter(txtStartTimeExtra2.getText(),txtEndTimeExtra2.getText());
						filter and=new And_Filter(id,time);
						filteredList.filter(and);

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else if(chckbxID.isSelected())
				{
					try {
						filter id=new Id_Filter(txtID.getText());
						filteredList.filter(id);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}


				FileChooser saveFile = new FileChooser();
				saveFile.fileChooser.setFileSelectionMode(0);
				if(saveFile.fileChooser.showSaveDialog(getParent()) == JFileChooser.APPROVE_OPTION)
				{
					try {
						filteredList.save_to_csv(saveFile.fileChooser.getSelectedFile().getAbsolutePath());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		rdbCreatekmlFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

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
}