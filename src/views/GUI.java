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
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

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
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Choice;
import java.awt.Checkbox;

public class GUI extends JFrame {

	private String csvFolderAbsPath;
	private String lat, lon, startTime, endTime, mac;
	private FileChooser csvFolder;
	private FileChooser SaveCSV;
	private FileChooser SaveKML;
	private ArrayList<String> listOfFiles;
	private ArrayList<filter> listOfFilters;
	public static  ListOfWifiRows mergedList ;
	private ListOfWifiRows filteredList;
	private FolderWatcher folderWatcher;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel pnlOptions;
	private JMenuBar menuBar;
	private JMenu mnFile,mnAlgo;
	private JMenuItem mntmOpenFolder;
	private JTextArea txtConsole;
	private JTextField txtMacAddress;
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
	private JMenuItem mntmOpenMergedCsv,mntmSaveCsv,mntmSaveKml,mntmNL,mntmCL;
	private JPanel pnlBlank;
	private JPanel pnlMainCards;
	private JPanel pnlFilters;
	private JMenuItem mntmFilters;
	private JPanel pnlAlgo1;
	private JTextField txtMacAlgo1;
	private JPanel pnlMainBlank;
	private JSlider slNumOfScans;
	private JButton btRun;
	private JPanel pnlAlgo2;
	private JLabel lblCalculateClientsLocation;
	private JLabel lblClientsId;
	private JTextField txtIDAlgo2;
	private JLabel lblMac_1;
	private JLabel lblMac_2;
	private JLabel lblMac_3;
	private JTextField txtMac1;
	private JTextField txtMac2;
	private JTextField txtMac3;
	private JLabel lblSignal;
	private JLabel label;
	private JLabel label_1;
	private JTextField txtSignal1;
	private JTextField txtSignal2;
	private JTextField txtSignal3;
	private JSlider Algo2slider;
	private JButton btnDisableFilters;
	private JMenuItem mntmDeliteData;
	private JCheckBox chckbxNot;
	private JCheckBox chckbxOr;
	private JCheckBox chckbxAnd;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JRadioButton radioButton_2;
	private JMenuItem mntmProperties;


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

		mergedList = new ListOfWifiRows();
		filteredList = new ListOfWifiRows();
		listOfFilters=new ArrayList<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50,50, 1408, 905);

		csvFolder = new FileChooser();



		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 182, 193));
		setJMenuBar(menuBar);

		mnFile = new JMenu(" File ");
		mnFile.setForeground(Color.BLACK);
		mnFile.setFont(new Font("Monotype Corsiva", Font.PLAIN, 45));
		menuBar.add(mnFile);


		mntmOpenFolder = new JMenuItem("Open folder / csv file");
		mntmOpenFolder.setFont(new Font("Monotype Corsiva", Font.PLAIN, 42));
		mnFile.add(mntmOpenFolder);

		mntmOpenMergedCsv = new JMenuItem("Open merged csv file");
		mntmOpenMergedCsv.setFont(new Font("Monotype Corsiva", Font.PLAIN, 42));
		mnFile.add(mntmOpenMergedCsv);

		mntmSaveCsv = new JMenuItem("Save as csv");
		mntmSaveCsv.setFont(new Font("Monotype Corsiva", Font.PLAIN, 42));
		mnFile.add(mntmSaveCsv);

		mntmSaveKml = new JMenuItem("Save as kml");
		mntmSaveKml.setFont(new Font("Monotype Corsiva", Font.PLAIN, 42));
		mnFile.add(mntmSaveKml);

		mntmDeliteData = new JMenuItem("Delite data");
		mntmDeliteData.setFont(new Font("Monotype Corsiva", Font.PLAIN, 42));
		mnFile.add(mntmDeliteData);
		
		mntmProperties = new JMenuItem("Properties");
		
		mntmProperties.setFont(new Font("Monotype Corsiva", Font.PLAIN, 42));
		mnFile.add(mntmProperties);


		mnAlgo = new JMenu(" Algo ");
		mnAlgo.setForeground(Color.BLACK);
		mnAlgo.setFont(new Font("Monotype Corsiva", Font.PLAIN, 45));
		menuBar.add(mnAlgo);

		mntmFilters = new JMenuItem("Filters");

		mntmFilters.setFont(new Font("Monotype Corsiva", Font.PLAIN, 45));

		mnAlgo.add(mntmFilters);

		mntmNL = new JMenuItem("Network's location");

		mntmNL.setFont(new Font("Monotype Corsiva", Font.PLAIN, 42));
		mnAlgo.add(mntmNL);

		mntmCL = new JMenuItem("Client's location");

		mntmCL.setFont(new Font("Monotype Corsiva", Font.PLAIN, 42));
		mnAlgo.add(mntmCL);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);


		pnlOptions = new JPanel();
		pnlOptions.setBounds(40, 43, 203, 52);
		pnlOptions.setBorder(new TitledBorder(null, "Options:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));

		JScrollPane scrConsole = new JScrollPane();
		scrConsole.setBounds(40, 443, 1290, 213);
		scrConsole.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrConsole.setBackground(Color.GRAY);


		txtConsole = new JTextArea();
		txtConsole.setFont(new Font("Goudy Old Style", Font.PLAIN, 16));
		txtConsole.setTabSize(10);
		txtConsole.setEditable(false);
		scrConsole.setViewportView(txtConsole);

		MessageConsole mc = new MessageConsole(txtConsole);
		mc.redirectOut();
		mc.redirectErr(Color.RED, null);

		btRun = new JButton("Run");
		btRun.setBounds(1092, 99, 151, 53);
		btRun.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		btRun.setBackground(new Color(255, 192, 203));
		contentPane.setLayout(null);
		contentPane.add(scrConsole);
		contentPane.add(btRun);

		pnlMainCards = new JPanel();
		pnlMainCards.setBounds(40, 11, 768, 407);
		contentPane.add(pnlMainCards);
		pnlMainCards.setLayout(new CardLayout(0, 0));

		pnlFilters = new JPanel();
		pnlFilters.setBackground(new Color(240, 248, 255));
		pnlMainCards.add(pnlFilters, "pnlFilters");

		JLabel lblFilters = DefaultComponentFactory.getInstance().createTitle("Filters:");
		lblFilters.setBounds(35, 32, 116, 52);
		lblFilters.setFont(new Font("Monotype Corsiva", Font.PLAIN, 45));

		chckbxGPS = new JCheckBox("GPS Coordinates");
		chckbxGPS.setBounds(204, 105, 280, 53);
		chckbxGPS.setBackground(new Color(240, 248, 255));
		chckbxGPS.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		chckbxTime = new JCheckBox("Time");
		chckbxTime.setBounds(35, 105, 131, 53);
		chckbxTime.setBackground(new Color(240, 248, 255));
		chckbxTime.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));


		chckbxID = new JCheckBox("Device ID");
		chckbxID.setBounds(511, 105, 195, 53);
		chckbxID.setBackground(new Color(240, 248, 255));
		chckbxID.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));



		pnlAddOptions = new JPanel();
		pnlAddOptions.setBounds(35, 168, 722, 215);
		pnlAddOptions.setBackground(new Color(240, 248, 255));

		pnlAddOptions.setLayout(new CardLayout(0, 0));

		pnlBlank = new JPanel();
		pnlBlank.setBackground(new Color(240, 248, 255));
		pnlAddOptions.add(pnlBlank, "blank");
		pnlBlank.setLayout(null);

		pnlTime = new JPanel();
		pnlTime.setBackground(new Color(240, 248, 255));
		pnlAddOptions.add(pnlTime, "Time");

		lblStartTime = new JLabel("Start time:");
		lblStartTime.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtStartTime = new JTextField();
		txtStartTime.setColumns(10);

		lblEndTime = new JLabel("End time: ");
		lblEndTime.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtEndTime = new JTextField();
		txtEndTime.setColumns(10);

		lblTimeFormat = new JLabel("Format: yyyy-mm-dd hh:mm:ss");
		lblTimeFormat.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		pnlGPS = new JPanel();
		pnlGPS.setBackground(new Color(240, 248, 255));
		pnlAddOptions.add(pnlGPS, "GPS");

		lblLat = new JLabel("Latitude: ");
		lblLat.setBounds(16, 54, 134, 42);
		lblLat.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		lblLat.setVisible(true);

		txtLat = new JTextField();
		txtLat.setBounds(160, 44, 161, 51);
		txtLat.setVisible(true);
		txtLat.setColumns(10);

		lblLon = new JLabel("Longtitude: ");
		lblLon.setBounds(356, 54, 165, 42);
		lblLon.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		lblLon.setVisible(true);

		txtLon = new JTextField();
		txtLon.setBounds(522, 54, 161, 51);
		txtLon.setVisible(true);
		txtLon.setColumns(10);

		lblLatitude = new JLabel("Latitude:");
		lblLatitude.setBounds(16, 144, 134, 42);
		lblLatitude.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtLat2 = new JTextField();
		txtLat2.setBounds(160, 134, 161, 51);
		txtLat2.setColumns(10);

		lblLongtitude = new JLabel("Longtitude:");
		lblLongtitude.setBounds(356, 144, 181, 42);
		lblLongtitude.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtLon2 = new JTextField();
		txtLon2.setBounds(522, 146, 161, 51);
		txtLon2.setColumns(10);

		pnlID = new JPanel();
		pnlID.setBackground(new Color(240, 248, 255));
		pnlAddOptions.add(pnlID, "ID");

		JLabel lblID = new JLabel("ID: ");
		lblID.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtID = new JTextField();
		txtID.setColumns(10);

		JPanel pnlGPSTime = new JPanel();
		pnlGPSTime.setBackground(new Color(240, 248, 255));
		pnlAddOptions.add(pnlGPSTime, "GPSTime");

		lblStartTimeExtra = new JLabel("Start Time: ");
		lblStartTimeExtra.setBounds(2, 118, 158, 42);
		lblStartTimeExtra.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtStartTimeExtra = new JTextField();
		txtStartTimeExtra.setBounds(170, 133, 154, 20);
		txtStartTimeExtra.setColumns(10);

		txtEndTimeExtra = new JTextField();
		txtEndTimeExtra.setBounds(521, 133, 190, 20);
		txtEndTimeExtra.setText("");
		txtEndTimeExtra.setColumns(10);

		lblLatExtra = new JLabel("Latitude:");
		lblLatExtra.setBounds(26, 22, 126, 42);
		lblLatExtra.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtLatExtra = new JTextField();
		txtLatExtra.setBounds(170, 38, 155, 20);
		txtLatExtra.setColumns(10);

		txtLonExtra = new JTextField();
		txtLonExtra.setBounds(521, 38, 190, 20);
		txtLonExtra.setColumns(10);

		lblLat2Extra = new JLabel("Latitude:");
		lblLat2Extra.setBounds(26, 70, 134, 42);
		lblLat2Extra.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtLat2Extra = new JTextField();
		txtLat2Extra.setBounds(170, 85, 154, 20);
		txtLat2Extra.setColumns(10);

		lblLongtitude_1 = new JLabel("Longtitude:");
		lblLongtitude_1.setBounds(359, 70, 158, 42);
		lblLongtitude_1.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtLon2Extra = new JTextField();
		txtLon2Extra.setBounds(521, 85, 190, 20);
		txtLon2Extra.setColumns(10);

		pnlGPSID = new JPanel();
		pnlGPSID.setBackground(new Color(240, 248, 255));
		pnlAddOptions.add(pnlGPSID, "GPSID");

		lblLatExtra2 = new JLabel("Latitude: ");
		lblLatExtra2.setBounds(12, 20, 134, 42);
		lblLatExtra2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtLatExtra2 = new JTextField();
		txtLatExtra2.setBounds(161, 21, 163, 28);
		txtLatExtra2.setColumns(10);

		lblLonExtra2 = new JLabel("Longtitude: ");
		lblLonExtra2.setBounds(343, 20, 165, 42);
		lblLonExtra2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		lblIDExtra = new JLabel("ID: ");
		lblIDExtra.setBounds(89, 118, 58, 42);
		lblIDExtra.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtIDExtra = new JTextField();
		txtIDExtra.setBounds(161, 121, 163, 24);
		txtIDExtra.setColumns(10);

		JLabel lblLatitudeExtra2 = new JLabel("Latitude:");
		lblLatitudeExtra2.setBounds(12, 70, 126, 42);
		lblLatitudeExtra2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtLat2Extra2 = new JTextField();
		txtLat2Extra2.setBounds(161, 72, 163, 27);
		txtLat2Extra2.setColumns(10);

		JLabel lblLongtitudeExtra2 = new JLabel("Longtitude:");
		lblLongtitudeExtra2.setBounds(343, 70, 157, 42);
		lblLongtitudeExtra2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		pnlTimeID = new JPanel();
		pnlTimeID.setBackground(new Color(240, 248, 255));
		pnlAddOptions.add(pnlTimeID, "TimeID");

		lblStartTimeExtra2 = new JLabel("Start Time: ");
		lblStartTimeExtra2.setBounds(73, 38, 158, 42);
		lblStartTimeExtra2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtStartTimeExtra2 = new JTextField();
		txtStartTimeExtra2.setBounds(234, 42, 264, 27);
		txtStartTimeExtra2.setColumns(10);

		lblIDExtra2 = new JLabel("ID:");
		lblIDExtra2.setBounds(171, 135, 50, 42);
		lblIDExtra2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtIDExtra2 = new JTextField();
		txtIDExtra2.setBounds(234, 142, 264, 27);
		txtIDExtra2.setColumns(10);

		pnlTimeGPSID = new JPanel();
		pnlTimeGPSID.setBackground(new Color(240, 248, 255));
		pnlAddOptions.add(pnlTimeGPSID, "TimeGPSID");

		lblStartTimeExtra3 = new JLabel("Start Time:");
		lblStartTimeExtra3.setBounds(12, 15, 150, 42);
		lblStartTimeExtra3.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtStartTimeExtra3 = new JTextField();
		txtStartTimeExtra3.setBounds(179, 16, 172, 28);
		txtStartTimeExtra3.setColumns(10);

		lblEndTimeExtra3 = new JLabel("End Time:");
		lblEndTimeExtra3.setBounds(386, 15, 138, 42);
		lblEndTimeExtra3.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		lblLatExtra3 = new JLabel("Latitude: ");
		lblLatExtra3.setBounds(38, 65, 134, 42);
		lblLatExtra3.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtLatExtra3 = new JTextField();
		txtLatExtra3.setBounds(180, 65, 171, 29);
		txtLatExtra3.setColumns(10);

		lblLonExtra3 = new JLabel("Longtitude: ");
		lblLonExtra3.setBounds(366, 64, 165, 42);
		lblLonExtra3.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtLonExtra3 = new JTextField();
		txtLonExtra3.setBounds(531, 68, 186, 29);
		txtLonExtra3.setColumns(10);

		lblIDExtra3 = new JLabel("ID:");
		lblIDExtra3.setBounds(109, 172, 50, 42);
		lblIDExtra3.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtIDExtra3 = new JTextField();
		txtIDExtra3.setBounds(181, 173, 171, 29);
		txtIDExtra3.setColumns(10);

		JLabel lblLatitude_1 = new JLabel("Latitude:");
		lblLatitude_1.setBounds(38, 118, 126, 42);
		lblLatitude_1.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtLat2Extra3 = new JTextField();
		txtLat2Extra3.setBounds(180, 117, 171, 30);
		txtLat2Extra3.setColumns(10);

		JLabel lblLongtitude_2 = new JLabel("Longtitude:");
		lblLongtitude_2.setBounds(366, 117, 181, 42);
		lblLongtitude_2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtLon2Extra3 = new JTextField();
		txtLon2Extra3.setBounds(530, 117, 187, 30);
		txtLon2Extra3.setColumns(10);

	
		lblTimeFormat.setEnabled(true);
		GroupLayout gl_pnlTime = new GroupLayout(pnlTime);
		gl_pnlTime.setHorizontalGroup(
				gl_pnlTime.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlTime.createSequentialGroup()
						.addGroup(gl_pnlTime.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnlTime.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblTimeFormat, GroupLayout.PREFERRED_SIZE, 451, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlTime.createSequentialGroup()
										.addGap(84)
										.addGroup(gl_pnlTime.createParallelGroup(Alignment.LEADING)
												.addComponent(lblStartTime)
												.addComponent(lblEndTime))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_pnlTime.createParallelGroup(Alignment.LEADING)
												.addComponent(txtEndTime, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
												.addComponent(txtStartTime, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))))
						.addGap(98))
				);
		gl_pnlTime.setVerticalGroup(
				gl_pnlTime.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTime.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblTimeFormat)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_pnlTime.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStartTime)
								.addComponent(txtStartTime, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_pnlTime.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEndTime)
								.addComponent(txtEndTime, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(143, Short.MAX_VALUE))
				);
		pnlTime.setLayout(gl_pnlTime);
		pnlGPS.setLayout(null);
		pnlGPS.add(lblLat);
		pnlGPS.add(lblLatitude);
		pnlGPS.add(txtLat);
		pnlGPS.add(lblLon);
		pnlGPS.add(txtLon);
		pnlGPS.add(txtLat2);
		pnlGPS.add(lblLongtitude);
		pnlGPS.add(txtLon2);


		GroupLayout gl_pnlID = new GroupLayout(pnlID);
		gl_pnlID.setHorizontalGroup(
				gl_pnlID.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_pnlID.createSequentialGroup()
						.addGap(167)
						.addComponent(lblID)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtID, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(241, Short.MAX_VALUE))
				);
		gl_pnlID.setVerticalGroup(
				gl_pnlID.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlID.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_pnlID.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtID, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblID))
						.addContainerGap(135, Short.MAX_VALUE))
				);
		pnlID.setLayout(gl_pnlID);



		lblLonExtra = new JLabel("Longtitude:");
		lblLonExtra.setBounds(360, 22, 157, 42);
		lblLonExtra.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		lblEndTimeExtra = new JLabel("End Time: ");
		lblEndTimeExtra.setBounds(371, 118, 146, 42);
		lblEndTimeExtra.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		pnlGPSTime.setLayout(null);
		pnlGPSTime.add(lblLat2Extra);
		pnlGPSTime.add(lblLatExtra);
		pnlGPSTime.add(txtLat2Extra);
		pnlGPSTime.add(lblLongtitude_1);
		pnlGPSTime.add(txtLatExtra);
		pnlGPSTime.add(lblLonExtra);
		pnlGPSTime.add(txtLonExtra);
		pnlGPSTime.add(txtLon2Extra);
		pnlGPSTime.add(lblStartTimeExtra);
		pnlGPSTime.add(txtStartTimeExtra);
		pnlGPSTime.add(lblEndTimeExtra);
		pnlGPSTime.add(txtEndTimeExtra);
		pnlGPSID.setLayout(null);
		pnlGPSID.add(lblIDExtra);
		pnlGPSID.add(lblLatExtra2);
		pnlGPSID.add(txtIDExtra);
		pnlGPSID.add(lblLongtitudeExtra2);
		pnlGPSID.add(txtLatExtra2);
		pnlGPSID.add(lblLonExtra2);
		pnlGPSID.add(lblLatitudeExtra2);
		pnlGPSID.add(txtLat2Extra2);

		txtLonExtra2 = new JTextField();
		txtLonExtra2.setBounds(505, 20, 165, 28);
		pnlGPSID.add(txtLonExtra2);
		txtLonExtra2.setColumns(10);

		txtLon2Extra2 = new JTextField();
		txtLon2Extra2.setBounds(505, 70, 165, 28);
		pnlGPSID.add(txtLon2Extra2);
		txtLon2Extra2.setColumns(10);
		pnlTimeID.setLayout(null);
		pnlTimeID.add(lblStartTimeExtra2);
		pnlTimeID.add(lblIDExtra2);
		pnlTimeID.add(txtStartTimeExtra2);
		pnlTimeID.add(txtIDExtra2);

		lblEndTimeExtra2 = new JLabel("End Time: ");
		lblEndTimeExtra2.setBounds(87, 88, 146, 42);
		pnlTimeID.add(lblEndTimeExtra2);
		lblEndTimeExtra2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtEndTimeExtra2 = new JTextField();
		txtEndTimeExtra2.setBounds(234, 90, 264, 27);
		pnlTimeID.add(txtEndTimeExtra2);
		txtEndTimeExtra2.setColumns(10);
		pnlTimeGPSID.setLayout(null);
		pnlTimeGPSID.add(lblIDExtra3);
		pnlTimeGPSID.add(lblLatExtra3);
		pnlTimeGPSID.add(txtLatExtra3);
		pnlTimeGPSID.add(lblLonExtra3);
		pnlTimeGPSID.add(txtIDExtra3);
		pnlTimeGPSID.add(lblLatitude_1);
		pnlTimeGPSID.add(txtLat2Extra3);
		pnlTimeGPSID.add(lblLongtitude_2);
		pnlTimeGPSID.add(lblStartTimeExtra3);
		pnlTimeGPSID.add(txtStartTimeExtra3);
		pnlTimeGPSID.add(lblEndTimeExtra3);
		pnlTimeGPSID.add(txtLon2Extra3);
		pnlTimeGPSID.add(txtLonExtra3);

		txtEndTimeExtra3 = new JTextField();
		txtEndTimeExtra3.setBounds(530, 15, 187, 31);
		pnlTimeGPSID.add(txtEndTimeExtra3);
		txtEndTimeExtra3.setColumns(10);
		pnlFilters.setLayout(null);
		pnlFilters.add(lblFilters);
		pnlFilters.add(pnlAddOptions);
		pnlFilters.add(chckbxTime);
		pnlFilters.add(chckbxGPS);
		pnlFilters.add(chckbxID);
		
		chckbxAnd = new JCheckBox("And");
		chckbxAnd.setEnabled(false);
		
		chckbxAnd.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 37));
		chckbxAnd.setBackground(new Color(240, 248, 255));
		chckbxAnd.setBounds(204, 34, 131, 53);
		pnlFilters.add(chckbxAnd);
		
		chckbxOr = new JCheckBox("OR");
		chckbxOr.setEnabled(false);
		chckbxOr.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 37));
		chckbxOr.setBackground(new Color(240, 248, 255));
		chckbxOr.setBounds(344, 34, 131, 53);
		pnlFilters.add(chckbxOr);
		
		chckbxNot = new JCheckBox("Not");
		chckbxNot.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 37));
		chckbxNot.setBackground(new Color(240, 248, 255));
		chckbxNot.setBounds(470, 34, 131, 53);
		pnlFilters.add(chckbxNot);

		pnlAlgo1 = new JPanel();
		pnlAlgo1.setBackground(new Color(240, 248, 255));
		pnlMainCards.add(pnlAlgo1, "pnlAlgo1");

		JLabel lblCalculateNetworksLocation = new JLabel("Calculate network's location using Algorithm 1:");
		lblCalculateNetworksLocation.setBounds(35, 38, 553, 53);
		lblCalculateNetworksLocation.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));

		JLabel lblMac = new JLabel("MAC: ");
		lblMac.setBounds(183, 124, 75, 35);
		lblMac.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));

		txtMacAlgo1 = new JTextField();
		txtMacAlgo1.setBounds(278, 119, 274, 31);
		txtMacAlgo1.setColumns(10);

		JLabel lblNumberOfScans = new JLabel("Number of scans: ");
		lblNumberOfScans.setBounds(64, 243, 194, 35);
		lblNumberOfScans.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));

		slNumOfScans = new JSlider();
		slNumOfScans.setBounds(278, 167, 274, 111);
		slNumOfScans.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		slNumOfScans.setBackground(new Color(230, 230, 250));
		slNumOfScans.setValue(3);
		slNumOfScans.setMinimum(1);
		slNumOfScans.setMaximum(5);
		slNumOfScans.setMajorTickSpacing(1);
		slNumOfScans.setSnapToTicks(true);
		slNumOfScans.setPaintTicks(true);
		slNumOfScans.setPaintLabels(true);
		pnlAlgo1.setLayout(null);
		pnlAlgo1.add(lblCalculateNetworksLocation);
		pnlAlgo1.add(lblNumberOfScans);
		pnlAlgo1.add(slNumOfScans);
		pnlAlgo1.add(lblMac);
		pnlAlgo1.add(txtMacAlgo1);

		pnlMainBlank = new JPanel();
		pnlMainBlank.setBackground(new Color(240, 248, 255));
		pnlMainBlank.setLayout(null);
		pnlMainCards.add(pnlMainBlank, "pnlMainBlank");
		JLabel label_2 = new JLabel(new ImageIcon("source.gif"));
		label_2.setBounds(105, -27, 540, 450);
		pnlMainBlank.add(label_2);

		pnlAlgo2 = new JPanel();
		pnlAlgo2.setBackground(new Color(240, 248, 255));
		pnlMainCards.add(pnlAlgo2, "pnlAlgo2");

		lblCalculateClientsLocation = new JLabel("Calculate client's location:");
		lblCalculateClientsLocation.setBounds(24, 18, 280, 35);
		lblCalculateClientsLocation.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));

		lblClientsId = new JLabel("Clients ID:");
		lblClientsId.setBounds(24, 154, 121, 35);
		lblClientsId.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));

		txtIDAlgo2 = new JTextField();
		txtIDAlgo2.setBounds(180, 154, 170, 29);
		txtIDAlgo2.setColumns(10);

		lblMac_1 = new JLabel("MAC:");
		lblMac_1.setBounds(73, 199, 83, 42);
		lblMac_1.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));

		lblMac_2 = new JLabel("MAC:");
		lblMac_2.setBounds(73, 254, 83, 42);
		lblMac_2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));

		lblMac_3 = new JLabel("MAC:");
		lblMac_3.setBounds(73, 311, 83, 42);
		lblMac_3.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));

		txtMac1 = new JTextField();
		txtMac1.setBounds(180, 209, 170, 29);
		txtMac1.setColumns(10);

		txtMac2 = new JTextField();
		txtMac2.setEnabled(false);
		txtMac2.setBounds(180, 265, 170, 29);
		txtMac2.setColumns(10);

		txtMac3 = new JTextField();
		txtMac3.setEnabled(false);
		txtMac3.setBounds(180, 322, 170, 29);
		txtMac3.setColumns(10);

		lblSignal = new JLabel("Signal:");
		lblSignal.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));
		lblSignal.setBounds(391, 198, 110, 45);

		label = new JLabel("Signal:");
		label.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));
		label.setBounds(385, 253, 110, 45);

		label_1 = new JLabel("Signal:");
		label_1.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));
		label_1.setBounds(385, 310, 110, 45);

		txtSignal1 = new JTextField();
		txtSignal1.setBounds(498, 209, 200, 29);
		txtSignal1.setColumns(10);

		txtSignal2 = new JTextField();
		txtSignal2.setEnabled(false);
		txtSignal2.setBounds(498, 265, 200, 29);
		txtSignal2.setColumns(10);

		txtSignal3 = new JTextField();
		txtSignal3.setEnabled(false);
		txtSignal3.setBounds(498, 322, 200, 29);
		txtSignal3.setColumns(10);
		pnlAlgo2.setLayout(null);
		pnlAlgo2.add(lblCalculateClientsLocation);
		pnlAlgo2.add(lblMac_1);
		pnlAlgo2.add(lblClientsId);
		pnlAlgo2.add(lblMac_2);
		pnlAlgo2.add(lblMac_3);
		pnlAlgo2.add(txtMac3);
		pnlAlgo2.add(label_1);
		pnlAlgo2.add(txtMac2);
		pnlAlgo2.add(label);
		pnlAlgo2.add(txtMac1);
		pnlAlgo2.add(lblSignal);
		pnlAlgo2.add(txtIDAlgo2);
		pnlAlgo2.add(txtSignal3);
		pnlAlgo2.add(txtSignal2);
		pnlAlgo2.add(txtSignal1);

		JLabel lblNumber = new JLabel("Number of scans: ");
		lblNumber.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));
		lblNumber.setBounds(498, 46, 200, 45);
		pnlAlgo2.add(lblNumber);

		Algo2slider = new JSlider();
		Algo2slider.setBackground(new Color(230, 230, 250));
		Algo2slider.setForeground(new Color(0, 0, 0));
		Algo2slider.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));
		Algo2slider.setPaintLabels(true);
		Algo2slider.setPaintTicks(true);
		Algo2slider.setSnapToTicks(true);
		Algo2slider.setValue(3);
		Algo2slider.setMinimum(1);
		Algo2slider.setMajorTickSpacing(1);
		Algo2slider.setMaximum(5);
		Algo2slider.setBounds(498, 93, 200, 90);
		pnlAlgo2.add(Algo2slider);
		
		radioButton = new JRadioButton("1");
		radioButton.setSelected(true);
		radioButton.setFont(new Font("Monotype Corsiva", Font.PLAIN, 40));
		radioButton.setBackground(new Color(240, 248, 255));
		radioButton.setBounds(180, 76, 71, 50);
		pnlAlgo2.add(radioButton);
		
		radioButton_1 = new JRadioButton("2");
		radioButton_1.setFont(new Font("Monotype Corsiva", Font.PLAIN, 40));
		radioButton_1.setBackground(new Color(240, 248, 255));
		radioButton_1.setBounds(278, 76, 71, 50);
		pnlAlgo2.add(radioButton_1);
		
		radioButton_2 = new JRadioButton("3");
		radioButton_2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 40));
		radioButton_2.setBackground(new Color(240, 248, 255));
		radioButton_2.setBounds(376, 76, 71, 50);
		pnlAlgo2.add(radioButton_2);
		
		JLabel lblMacs = new JLabel("Macs:");
		lblMacs.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));
		lblMacs.setBounds(73, 77, 71, 45);
		pnlAlgo2.add(lblMacs);

		btnDisableFilters = new JButton("Disable filters");
		btnDisableFilters.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		btnDisableFilters.setBackground(new Color(255, 182, 193));
		btnDisableFilters.setBounds(1064, 227, 221, 53);
		contentPane.add(btnDisableFilters);

	}




	//////////////////////////////////////////////////////////////
	// This method contains all of the code for creating events
	//////////////////////////////////////////////////////////////
	private void createEvents()
	{
		CardLayout cardLayout = (CardLayout) pnlMainCards.getLayout();
		cardLayout.show(pnlMainCards, "pnlMainBlank");

		
		mntmOpenFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(csvFolder.fileChooser.showOpenDialog(mntmOpenFolder) == JFileChooser.APPROVE_OPTION) 
				{
					csvFolderAbsPath = csvFolder.fileChooser.getSelectedFile().getAbsolutePath();
					try {
						folderWatcher = new FolderWatcher(csvFolder.fileChooser.getSelectedFile().toPath());
					} catch (Exception e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					Thread folderWatcherThread = new Thread(folderWatcher);
					folderWatcherThread.start();
					File temp = new File(csvFolderAbsPath);
					if(temp.isDirectory())
					{
						listOfFiles = ReaderFromCsv.getAllcsvFilesFromFolder(csvFolderAbsPath);
						try {
							if(mergedList.isEmpty()) mergedList = ReaderFromCsv.notSortedFileToArrayListOfTenMostPowerfulWifiPoints(listOfFiles);
							else{
								ListOfWifiRows tempList= ReaderFromCsv.notSortedFileToArrayListOfTenMostPowerfulWifiPoints(listOfFiles);
								mergedList=Filters.mergeTwoListsByDateAndID(tempList, mergedList);

							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					else {
						try {
							ArrayList<String>f=new ArrayList<>();
							f.add(csvFolderAbsPath);
							mergedList = ReaderFromCsv.notSortedFileToArrayListOfTenMostPowerfulWifiPoints(f);
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
					System.out.println("Saved data to list");
					filteredList=mergedList.copy();
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
						if(mergedList.isEmpty()) mergedList = ReaderFromCsv.readerFromMergedCSVtoList(csvFolderAbsPath);
						else{
							ListOfWifiRows tempList= ReaderFromCsv.readerFromMergedCSVtoList(csvFolderAbsPath);
							mergedList=Filters.mergeTwoListsByDateAndID(tempList, mergedList);
							System.out.println("Saved file to list");
						}

					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}filteredList=mergedList.copy();

			}
		});

		mntmSaveCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mergedList.isEmpty()){
					System.out.println("Don't have data to save");
				}
				else{
					FileChooser saveFile = new FileChooser();
					saveFile.fileChooser.setFileSelectionMode(0);
					if(saveFile.fileChooser.showSaveDialog(getParent()) == JFileChooser.APPROVE_OPTION)
					{
						try {
							if(filteredList.isEmpty()) mergedList.save_to_csv(saveFile.fileChooser.getSelectedFile().getAbsolutePath()+".csv");
							else filteredList.save_to_csv(saveFile.fileChooser.getSelectedFile().getAbsolutePath()+".csv");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});




		mntmSaveKml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mergedList.isEmpty()){
					System.out.println("Don't have data to save");
				}
				else{
					FileChooser saveFile = new FileChooser();
					saveFile.fileChooser.setFileSelectionMode(0);
					if(saveFile.fileChooser.showSaveDialog(getParent()) == JFileChooser.APPROVE_OPTION)
					{
						try {
							Kml.Kml();
							if(filteredList.isEmpty()) Kml.addMarksFromList(Filters.mostPowerfulWifiWithSameMac(mergedList)); 
							else Kml.addMarksFromList(Filters.mostPowerfulWifiWithSameMac(filteredList)); 
							File file=new File(saveFile.fileChooser.getSelectedFile().getAbsolutePath()+".kml");
							Kml.writeFile(file);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		
		mntmProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(filteredList.properties());
				System.out.println("Filters: "+listOfFilters.toString());
			}
		});

		/*****************here you need to work on layout*******************/

		chckbxAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAnd.isSelected()){
					chckbxOr.setEnabled(false);
					chckbxNot.setEnabled(false);
					chckbxOr.setSelected(false);
					chckbxNot.setSelected(false);
				}
				else {
					chckbxNot.setEnabled(true);
					chckbxOr.setEnabled(true);
				}
			}
		});
		chckbxOr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxOr.isSelected()){
					chckbxAnd.setEnabled(false);
					chckbxNot.setEnabled(false);
					chckbxAnd.setSelected(false);
					chckbxNot.setSelected(false);
				}
				else {
					chckbxAnd.setEnabled(true);
					chckbxNot.setEnabled(true);
				}
			}
		});
		chckbxNot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNot.isSelected()){
					chckbxAnd.setEnabled(false);
					chckbxOr.setEnabled(false);
					chckbxAnd.setSelected(false);
					chckbxOr.setSelected(false);
				}
				else {
					chckbxAnd.setEnabled(true);
					chckbxOr.setEnabled(true);
				}
			}
		});
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radioButton.isSelected()) {
					radioButton_1.setSelected(false);
					radioButton_2.setSelected(false);
					txtMac2.setEnabled(false); txtSignal2.setEnabled(false);
					txtMac3.setEnabled(false); txtSignal3.setEnabled(false);
				}
			}
		});
		radioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radioButton_1.isSelected()) {
					radioButton.setSelected(false);
					radioButton_2.setSelected(false);
					txtMac2.setEnabled(true); txtSignal2.setEnabled(true);
					txtMac3.setEnabled(false); txtSignal3.setEnabled(false);
				}
				else {
					radioButton.setSelected(true);
					radioButton_2.setSelected(false);
					txtMac2.setEnabled(false); txtSignal2.setEnabled(false);
				}
			}
		});
		
		radioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radioButton_2.isSelected()) {
					radioButton_1.setSelected(false);
					radioButton.setSelected(false);
					txtMac2.setEnabled(true); txtSignal2.setEnabled(true);
					txtMac3.setEnabled(true); txtSignal3.setEnabled(true);
				}
				else {
					radioButton.setSelected(true);
					radioButton_1.setSelected(false);
					txtMac2.setEnabled(false); txtSignal2.setEnabled(false);
					txtMac3.setEnabled(false); txtSignal3.setEnabled(false);
				}
			}
		});
		
		
		mntmFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout) pnlMainCards.getLayout();
				cardLayout.show(pnlMainCards, "pnlFilters");
			}
		});

		mntmNL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxGPS.setSelected(false);
				chckbxID.setSelected(false);
				chckbxTime.setSelected(false);
				CardLayout cardLayout = (CardLayout) pnlMainCards.getLayout();
				cardLayout.show(pnlMainCards, "pnlAlgo1");
			}
		});
		mntmCL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxGPS.setSelected(false);
				chckbxID.setSelected(false);
				chckbxTime.setSelected(false);
				CardLayout cardLayout = (CardLayout) pnlMainCards.getLayout();
				cardLayout.show(pnlMainCards, "pnlAlgo2");
			}
		});

		chckbxTime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!chckbxTime.isSelected()){
					if(chckbxID.isSelected() && chckbxGPS.isSelected()){
						chckbxOr.setEnabled(true);
						chckbxAnd.setEnabled(true);
					}
					else {
						chckbxOr.setEnabled(false);
						chckbxAnd.setEnabled(false);
						chckbxOr.setSelected(false);chckbxAnd.setSelected(false);
					}
				}
				if(chckbxTime.isSelected())
				{
					if(chckbxGPS.isSelected() || chckbxID.isSelected()){
						chckbxOr.setEnabled(true);
						chckbxAnd.setEnabled(true);
					}
					else {
						chckbxOr.setEnabled(false);
						chckbxAnd.setEnabled(false);
						chckbxOr.setSelected(false);chckbxAnd.setSelected(false);
					}
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
				if(!chckbxGPS.isSelected()){
					if(chckbxTime.isSelected() && chckbxID.isSelected()){
						chckbxOr.setEnabled(true);
						chckbxAnd.setEnabled(true);
					}
					else {
						chckbxOr.setEnabled(false);
						chckbxAnd.setEnabled(false);
						chckbxOr.setSelected(false);chckbxAnd.setSelected(false);
					}
				}
				if(chckbxGPS.isSelected())
				{
					if(chckbxTime.isSelected() || chckbxID.isSelected()){
						chckbxOr.setEnabled(true);
						chckbxAnd.setEnabled(true);
					}
					else {
						chckbxOr.setEnabled(false);
						chckbxAnd.setEnabled(false);
						chckbxOr.setSelected(false);chckbxAnd.setSelected(false);
					}
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "GPS");
					if(chckbxTime.isSelected() && chckbxID.isSelected())
					{	chckbxOr.setEnabled(true);
						cardLayout.show(pnlAddOptions, "TimeGPSID");
					}
					else if(chckbxTime.isSelected())
					{
						chckbxOr.setEnabled(true);
						cardLayout.show(pnlAddOptions, "GPSTime");
					}
					else if(chckbxID.isSelected())
					{
						chckbxOr.setEnabled(true);
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
				if(!chckbxID.isSelected()){
					if(chckbxTime.isSelected() && chckbxGPS.isSelected()){
						chckbxOr.setEnabled(true);
						chckbxAnd.setEnabled(true);
					}
					else {
						chckbxOr.setEnabled(false);
						chckbxAnd.setEnabled(false);
						chckbxOr.setSelected(false);chckbxAnd.setSelected(false);
					}
				}
				
				if(chckbxID.isSelected())
				{
					if(chckbxTime.isSelected() || chckbxGPS.isSelected()){
					chckbxOr.setEnabled(true);
					chckbxAnd.setEnabled(true);
				}
				else {
					chckbxOr.setEnabled(false);
					chckbxAnd.setEnabled(false);
					chckbxOr.setSelected(false);chckbxAnd.setSelected(false);
				}
					CardLayout cardLayout = (CardLayout) pnlAddOptions.getLayout();
					cardLayout.show(pnlAddOptions, "ID");
					if(chckbxGPS.isSelected() && chckbxTime.isSelected())
					{
						cardLayout.show(pnlAddOptions, "TimeGPSID");
					}
					else if(chckbxGPS.isSelected())
					{
						chckbxOr.setEnabled(true);
						cardLayout.show(pnlAddOptions, "GPSID");
					}
					else if(chckbxTime.isSelected())
					{
						chckbxOr.setEnabled(true);
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


		btRun.addActionListener(new ActionListener() {
			int test=0;
			public void actionPerformed(ActionEvent e) {
				if(FolderWatcher.getTest()==1){
					try {
						mergedList = ReaderFromCsv.notSortedFileToArrayListOfTenMostPowerfulWifiPoints(ReaderFromCsv.getAllcsvFilesFromFolder(csvFolderAbsPath));
						filteredList=mergedList.copy();
						System.out.println("Data refreshed");
						FolderWatcher.setTest(0);
						test=1;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(mergedList.isEmpty()){
					System.out.println("Don't have data to work with");
				}

				else{
					if (pnlFilters.isVisible()) {
						filter f=null;
						
						if(chckbxGPS.isSelected() && chckbxID.isSelected() && chckbxTime.isSelected())
						{
							try {
								Coordinates_3D a=new Coordinates_3D(txtLatExtra3.getText(), txtLonExtra3.getText(), "0");
								Coordinates_3D b=new Coordinates_3D(txtLat2Extra3.getText(), txtLon2Extra3.getText(), "0");
								filter coor=new Position_Filter_Rect(a,b);
								filter id=new Id_Filter(txtIDExtra3.getText());
								filter time=new Time_Filter(txtStartTimeExtra3.getText(),txtEndTimeExtra3.getText());
								
								if(chckbxNot.isSelected()){
									filter notC=new Not_Filter(coor);
									filter notT=new Not_Filter(time);
									filter notI=new Not_Filter(id);
									filter f2=new And_Filter(notC,notT);
									f=new And_Filter(notI,f2);
								}
								else if(chckbxOr.isSelected()){
									filter or=new Or_Filter(coor,time);
									f=new Or_Filter(or,id);
									chckbxOr.setEnabled(false);
								}
								else if(chckbxAnd.isSelected()){
									filter f2=new And_Filter(id,time);
									f=new And_Filter(f2,coor);
									chckbxAnd.setEnabled(false);
								}
								
								filteredList.filter(f);
								txtLatExtra3.setText(null); txtLonExtra3.setText(null);
								txtLat2Extra3.setText(null); txtLon2Extra3.setText(null);
								txtStartTimeExtra3.setText(null);txtEndTimeExtra3.setText(null);
								txtIDExtra3.setText(null);

							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						else if(chckbxGPS.isSelected() && chckbxTime.isSelected())
						{
							try {
								Coordinates_3D a=new Coordinates_3D(txtStartTimeExtra.getText(), txtEndTimeExtra.getText(), "0");
								Coordinates_3D b=new Coordinates_3D(txtLat2Extra.getText(), txtLon2Extra.getText(), "0");
								filter time=new Time_Filter(txtStartTimeExtra.getText(),txtEndTimeExtra.getText());
								filter coor=new Position_Filter_Rect(a,b);
								if(chckbxNot.isSelected()){
									filter notC=new Not_Filter(coor);
									filter notT=new Not_Filter(time);
									f=new And_Filter(notC,notT);
								}
								else if(chckbxOr.isSelected()) {
									f=new Or_Filter(coor,time);
									chckbxOr.setEnabled(false);
								}
								else if(chckbxAnd.isSelected()){
									f=new And_Filter(coor,time);	
									chckbxAnd.setEnabled(false);
								}
								filteredList.filter(f);
								txtStartTimeExtra.setText(null);txtEndTimeExtra.setText(null);
								txtLat2Extra.setText(null);txtLon2Extra.setText(null);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					
						
						else if(chckbxGPS.isSelected() && chckbxID.isSelected())
						{
							try {
								Coordinates_3D a=new Coordinates_3D(txtLatExtra2.getText(), txtLonExtra2.getText(), "0");
								Coordinates_3D b=new Coordinates_3D(txtLat2Extra2.getText(), txtLon2Extra2.getText(), "0");
								filter coor=new Position_Filter_Rect(a,b);
								filter id=new Id_Filter(txtIDExtra.getText());
								if(chckbxNot.isSelected()){
									filter notC=new Not_Filter(coor);
									filter notI=new Not_Filter(id);
									f=new And_Filter(notC,notI);
								}
								else if(chckbxOr.isSelected()){
									f=new Or_Filter(coor,id);
									chckbxOr.setEnabled(false);
								}
								else if(chckbxAnd.isSelected()){
									f=new And_Filter(id,coor);
									chckbxAnd.setEnabled(false);
								}
								filteredList.filter(f);
								txtLatExtra2.setText(null); txtLonExtra2.setText(null);
								txtLat2Extra2.setText(null); txtLon2Extra2.setText(null);
								txtIDExtra.setText(null);


							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						else if(chckbxTime.isSelected() && chckbxID.isSelected())
						{
							try {
								filter id=new Id_Filter(txtIDExtra2.getText());
								filter time=new Time_Filter(txtStartTimeExtra2.getText(),txtEndTimeExtra2.getText());
								if(chckbxNot.isSelected()){
									filter notT=new Not_Filter(time);
									filter notI=new Not_Filter(id);
									f=new And_Filter(notT,notI);
									System.out.println(f.toString());
								}
								if(chckbxOr.isSelected()){
									f=new Or_Filter(time,id);
									System.out.println(f.toString());
								}
								if(chckbxAnd.isSelected()){
									
									f=new And_Filter(id,time);
									System.out.println(f.toString());
								}
								filteredList.filter(f);
								txtStartTimeExtra2.setText(null);txtEndTimeExtra2.setText(null);
								txtIDExtra2.setText(null);

							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						else if(chckbxTime.isSelected())
						{
							try {
								filter time=new Time_Filter(txtStartTime.getText(),txtEndTime.getText());
						
								if(chckbxNot.isSelected()) f=new Not_Filter(time);
								else f=time;
								filteredList.filter(f);
								txtStartTime.setText(null);txtEndTime.setText(null);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						else if(chckbxID.isSelected())
						{
							try {
								filter id=new Id_Filter(txtID.getText());
								if(chckbxNot.isSelected()) f=new Not_Filter(id);
								else f=new Id_Filter(txtID.getText());
								filteredList.filter(f);
								txtID.setText(null);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						else 	if(chckbxGPS.isSelected()) 
						{
							try {
								Coordinates_3D a=new Coordinates_3D(txtLat.getText(), txtLon.getText(), "0");
								Coordinates_3D b=new Coordinates_3D(txtLat2.getText(), txtLon2.getText(), "0");
								filter coor=new Position_Filter_Rect(a,b);
								
								if(chckbxNot.isSelected()){
									f=new Not_Filter(coor);
								}
								else f=coor; 
								filteredList.filter(f);
								txtLat.setText(null);txtLon.setText(null);
								txtLat2.setText(null);txtLon2.setText(null);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						/*********************/
						if(f!=null){
							listOfFilters.add(f);
							System.out.println();
							System.out.println("Filter: "+listOfFilters.toString());
						}
						chckbxAnd.setSelected(false);chckbxOr.setSelected(false);chckbxNot.setSelected(false);
						chckbxTime.setSelected(false);chckbxGPS.setSelected(false);chckbxID.setSelected(false);
					}

					else if(pnlAlgo1.isVisible()){
						if(!((mac=txtMacAlgo1.getText()).equals(null))){
							int numOfNet=slNumOfScans.getValue();
							try {
								RowOfWifiPoints a=LocationRevaluation.centerOfRouter1(filteredList, txtMacAlgo1.getText(), numOfNet);
								filteredList=new ListOfWifiRows();
								filteredList.add(a);
								txtMacAlgo1.setText(null);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					if(pnlAlgo2.isVisible()){
						if(!((mac=txtMac1.getText()).equals(null))){
							ArrayList<String>macList=new ArrayList<>();
							ArrayList<Signal>signalList=new ArrayList<>();
							Signal a=null;
							macList.add(txtMac1.getText());
							if(!txtSignal1.getText().equals(null))a=new Signal(txtSignal1.getText());
							else a=new Signal("-120");
							signalList.add(a);
							
							if(radioButton_1.isSelected() && (mac=txtMac2.getText()).equals(null)==false){
								macList.add(txtMac2.getText());
								if(!txtSignal2.getText().equals(null)) a=new Signal(txtSignal2.getText());
								else a=new Signal("-120");
								signalList.add(a);
							}
							if(radioButton_2.isSelected() && (mac=txtMac3.getText()).equals(null)==false){
								macList.add(txtMac3.getText());
								if(!txtSignal3.getText().equals(null)) a=new Signal(txtSignal3.getText());
								else a=new Signal("-120");
								signalList.add(a);
							}
							

							/**/
							int num=Algo2slider.getValue();
							try {
								filter id=new Id_Filter(txtIDAlgo2.getText());
								filteredList.filter(id);
								RowOfWifiPoints r=LocationRevaluation.yourLocation(filteredList, macList, signalList, num);
								filteredList=new ListOfWifiRows();
								filteredList.add(r);


							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							txtMac1.setText(null);txtSignal1.setText(null);txtIDAlgo2.setText(null);
							txtMac2.setText(null);txtSignal2.setText(null);
							txtMac3.setText(null);txtSignal3.setText(null);
						}
						
					}
					System.out.println();
					if(filteredList.isEmpty())System.out.println("Don't have data like this");
					else filteredList.Print();
					
				}
			}
		});
		btnDisableFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!mergedList.isEmpty()){
				filteredList=mergedList.copy();
				System.out.println("All filters disabled");
				listOfFilters=new ArrayList<>();
			}
			}
		});

		mntmDeliteData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filteredList=new ListOfWifiRows();
				mergedList=new ListOfWifiRows();
				System.out.println("All data deleted");
				listOfFilters=new ArrayList<>();
			}
		});
	}
}