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

public class GUI extends JFrame {

	private String csvFolderAbsPath;
	private ListOfWifiRows filteringList = new ListOfWifiRows();
	private String lat, lon, startTime, endTime, mac;
	private FileChooser csvFolder;
	private FileChooser SaveCSV;
	private FileChooser SaveKML;
	private ArrayList<String> listOfFiles;
	private ListOfWifiRows mergedList = new ListOfWifiRows();
	private ListOfWifiRows filteredList = new ListOfWifiRows();

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel pnlOptions;
	private JMenuBar menuBar;
	private JMenu mnFile,mnAlgo;
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
	private JMenuItem mntmOpenMergedCsv,mntmSaveCsv,mntmSaveKml,mntmNL,mntmCL;
	private JPanel pnlBlank;
	private JPanel algo1;
	private JLabel label;
	private JLabel label_1;
	private JTextField textField;
	private JSlider slider;


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
		setBounds(100,100, 2323, 1301);

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
		

		mnAlgo = new JMenu(" Algo ");
		mnAlgo.setForeground(Color.BLACK);
		mnAlgo.setFont(new Font("Monotype Corsiva", Font.PLAIN, 45));
		menuBar.add(mnAlgo);
		
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


		btRun = new JButton("Run");
		btRun.setBackground(new Color(255, 192, 203));
		btRun.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		btRun.setBounds(331, 1021, 151, 53);


		pnlOptions = new JPanel();
		pnlOptions.setBounds(40, 43, 203, 52);
		pnlOptions.setBorder(new TitledBorder(null, "Options:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));

		JScrollPane scrConsole = new JScrollPane();
		scrConsole.setBounds(40, 576, 2108, 407);
		scrConsole.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrConsole.setBackground(Color.GRAY);


		txtConsole = new JTextArea();
		txtConsole.setFont(new Font("Goudy Old Style", Font.PLAIN, 32));
		txtConsole.setTabSize(10);
		txtConsole.setEditable(false);
		scrConsole.setViewportView(txtConsole);

				MessageConsole mc = new MessageConsole(txtConsole);
				mc.redirectOut();
				mc.redirectErr(Color.RED, null);

		pnlCardsFilters = new JPanel();
		pnlCardsFilters.setBackground(new Color(240, 248, 255));
		pnlCardsFilters.setBounds(40, 180, 768, 358);
		pnlCardsFilters.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlCardsFilters.setLayout(null);
		


		pnlAddOptions = new JPanel();
		pnlAddOptions.setBackground(new Color(240, 248, 255));
		pnlAddOptions.setBounds(861, 126, 1287, 422);
		
		pnlAddOptions.setLayout(new CardLayout(0, 0));
		
		pnlBlank = new JPanel();
		pnlBlank.setBackground(new Color(240, 248, 255));
		pnlAddOptions.add(pnlBlank, "blank");

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
		lblLat.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		lblLat.setVisible(true);

		txtLat = new JTextField();
		txtLat.setVisible(true);
		txtLat.setColumns(10);

		lblLon = new JLabel("Longtitude: ");
		lblLon.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		lblLon.setVisible(true);

		txtLon = new JTextField();
		txtLon.setVisible(true);
		txtLon.setColumns(10);
		
		lblLatitude = new JLabel("Latitude 2:");
		lblLatitude.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		
		txtLat2 = new JTextField();
		txtLat2.setColumns(10);
		
		lblLongtitude = new JLabel("Longtitude 2:");
		lblLongtitude.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		
		txtLon2 = new JTextField();
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
		lblStartTimeExtra.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtStartTimeExtra = new JTextField();
		txtStartTimeExtra.setColumns(10);

		txtEndTimeExtra = new JTextField();
		txtEndTimeExtra.setText("");
		txtEndTimeExtra.setColumns(10);

		lblLatExtra = new JLabel("Latitude:");
		lblLatExtra.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtLatExtra = new JTextField();
		txtLatExtra.setColumns(10);

		txtLonExtra = new JTextField();
		txtLonExtra.setColumns(10);
		
		lblLat2Extra = new JLabel("Latitude 2:");
		lblLat2Extra.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		
		txtLat2Extra = new JTextField();
		txtLat2Extra.setColumns(10);
		
		lblLongtitude_1 = new JLabel("Longtitude 2:");
		lblLongtitude_1.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		
		txtLon2Extra = new JTextField();
		txtLon2Extra.setColumns(10);
		
		pnlGPSID = new JPanel();
		pnlGPSID.setBackground(new Color(240, 248, 255));
		pnlAddOptions.add(pnlGPSID, "GPSID");

		lblLatExtra2 = new JLabel("Latitude: ");
		lblLatExtra2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtLatExtra2 = new JTextField();
		txtLatExtra2.setColumns(10);

		lblLonExtra2 = new JLabel("Longtitude: ");
		lblLonExtra2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtLonExtra2 = new JTextField();
		txtLonExtra2.setColumns(10);

		lblIDExtra = new JLabel("ID: ");
		lblIDExtra.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtIDExtra = new JTextField();
		txtIDExtra.setColumns(10);
		
		JLabel lblLatitudeExtra2 = new JLabel("Latitude 2:");
		lblLatitudeExtra2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		
		txtLat2Extra2 = new JTextField();
		txtLat2Extra2.setColumns(10);
		
		JLabel lblLongtitudeExtra2 = new JLabel("Longtitude 2");
		lblLongtitudeExtra2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		
		txtLon2Extra2 = new JTextField();
		txtLon2Extra2.setColumns(10);
		
		pnlTimeID = new JPanel();
		pnlTimeID.setBackground(new Color(240, 248, 255));
		pnlAddOptions.add(pnlTimeID, "TimeID");

		lblStartTimeExtra2 = new JLabel("Start Time: ");
		lblStartTimeExtra2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtStartTimeExtra2 = new JTextField();
		txtStartTimeExtra2.setColumns(10);

		lblEndTimeExtra2 = new JLabel("End Time: ");
		lblEndTimeExtra2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtEndTimeExtra2 = new JTextField();
		txtEndTimeExtra2.setColumns(10);

		lblIDExtra2 = new JLabel("ID:");
		lblIDExtra2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtIDExtra2 = new JTextField();
		txtIDExtra2.setColumns(10);
		
		pnlTimeGPSID = new JPanel();
		pnlTimeGPSID.setBackground(new Color(240, 248, 255));
		pnlAddOptions.add(pnlTimeGPSID, "TimeGPSID");

		lblStartTimeExtra3 = new JLabel("Start Time:");
		lblStartTimeExtra3.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtStartTimeExtra3 = new JTextField();
		txtStartTimeExtra3.setColumns(10);

		lblEndTimeExtra3 = new JLabel("End Time:");
		lblEndTimeExtra3.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtEndTimeExtra3 = new JTextField();
		txtEndTimeExtra3.setColumns(10);

		lblLatExtra3 = new JLabel("Latitude: ");
		lblLatExtra3.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtLatExtra3 = new JTextField();
		txtLatExtra3.setColumns(10);

		lblLonExtra3 = new JLabel("Longtitude: ");
		lblLonExtra3.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtLonExtra3 = new JTextField();
		txtLonExtra3.setColumns(10);

		lblIDExtra3 = new JLabel("ID:");
		lblIDExtra3.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));

		txtIDExtra3 = new JTextField();
		txtIDExtra3.setColumns(10);
		
		JLabel lblLatitude_1 = new JLabel("Latitude 2:");
		lblLatitude_1.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		
		txtLat2Extra3 = new JTextField();
		txtLat2Extra3.setColumns(10);
		
		JLabel lblLongtitude_2 = new JLabel("Longtitude 2:");
		lblLongtitude_2.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		
		txtLon2Extra3 = new JTextField();
		txtLon2Extra3.setColumns(10);
		
		//lblTimeFormat.setVisible(true);
		lblTimeFormat.setEnabled(true);
		GroupLayout gl_pnlTime = new GroupLayout(pnlTime);
		gl_pnlTime.setHorizontalGroup(
			gl_pnlTime.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTime.createSequentialGroup()
					.addGap(84)
					.addGroup(gl_pnlTime.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTime.createSequentialGroup()
							.addGap(49)
							.addComponent(lblTimeFormat, GroupLayout.PREFERRED_SIZE, 451, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_pnlTime.createSequentialGroup()
							.addComponent(lblStartTime)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtStartTime, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblEndTime)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtEndTime, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
							.addGap(112))))
		);
		gl_pnlTime.setVerticalGroup(
			gl_pnlTime.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTime.createSequentialGroup()
					.addGap(55)
					.addComponent(lblTimeFormat)
					.addGap(12)
					.addGroup(gl_pnlTime.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartTime)
						.addComponent(txtStartTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEndTime)
						.addComponent(txtEndTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(157, Short.MAX_VALUE))
		);
		pnlTime.setLayout(gl_pnlTime);

		GroupLayout gl_pnlGPS = new GroupLayout(pnlGPS);
		gl_pnlGPS.setHorizontalGroup(
			gl_pnlGPS.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlGPS.createSequentialGroup()
					.addGap(56)
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
					.addContainerGap(223, Short.MAX_VALUE))
		);
		gl_pnlGPS.setVerticalGroup(
			gl_pnlGPS.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlGPS.createSequentialGroup()
					.addGap(45)
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
					.addContainerGap(135, Short.MAX_VALUE))
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
					.addContainerGap(863, Short.MAX_VALUE))
		);
		gl_pnlID.setVerticalGroup(
			gl_pnlID.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlID.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlID.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblID))
					.addContainerGap(332, Short.MAX_VALUE))
		);
		pnlID.setLayout(gl_pnlID);

		
		
				lblLonExtra = new JLabel("Longtitude:");
				lblLonExtra.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
		
				lblEndTimeExtra = new JLabel("End Time: ");
				lblEndTimeExtra.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
				
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
					.addGroup(gl_pnlTimeGPSID.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnlTimeGPSID.createSequentialGroup()
							.addGroup(gl_pnlTimeGPSID.createParallelGroup(Alignment.LEADING)
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
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_pnlTimeGPSID.createSequentialGroup()
							.addComponent(lblStartTimeExtra3)
							.addGap(7)
							.addComponent(txtStartTimeExtra3)
							.addGap(43)
							.addComponent(lblEndTimeExtra3)
							.addGap(56)))
					.addGroup(gl_pnlTimeGPSID.createParallelGroup(Alignment.LEADING)
						.addComponent(txtLon2Extra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLonExtra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEndTimeExtra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(239))
		);
		gl_pnlTimeGPSID.setVerticalGroup(
			gl_pnlTimeGPSID.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTimeGPSID.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlTimeGPSID.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTimeGPSID.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblStartTimeExtra3)
							.addComponent(txtEndTimeExtra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtStartTimeExtra3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblEndTimeExtra3))
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
		
		contentPane.setLayout(null);
		contentPane.add(btRun);
		contentPane.add(pnlCardsFilters);
												
												algo1 = new JPanel();
												algo1.setBackground(new Color(240, 248, 255));
												algo1.setBounds(0, 89, 767, 269);
												pnlCardsFilters.add(algo1);
												
												label = new JLabel("MAC Address:");
												label.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
												
												label_1 = new JLabel("Number of scans: ");
												label_1.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
												
												textField = new JTextField();
												textField.setColumns(10);
												
												slider = new JSlider();
												slider.setForeground(new Color(0, 0, 0));
												slider.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
												slider.setBackground(new Color(255, 192, 203));
												slider.setPaintLabels(true);
												slider.setValue(3);
												slider.setSnapToTicks(true);
												slider.setPaintTicks(true);
												slider.setMinorTickSpacing(1);
												slider.setMinimum(1);
												slider.setMaximum(5);
												slider.setMajorTickSpacing(1);
												slider.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
												GroupLayout gl_panel = new GroupLayout(algo1);
												gl_panel.setHorizontalGroup(
													gl_panel.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel.createSequentialGroup()
															.addContainerGap()
															.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
																.addGroup(gl_panel.createSequentialGroup()
																	.addComponent(label)
																	.addGap(23))
																.addGroup(gl_panel.createSequentialGroup()
																	.addComponent(label_1)
																	.addPreferredGap(ComponentPlacement.RELATED)))
															.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
																.addComponent(slider, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(textField, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
															.addContainerGap(216, Short.MAX_VALUE))
												);
												gl_panel.setVerticalGroup(
													gl_panel.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel.createSequentialGroup()
															.addContainerGap()
															.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																.addComponent(label)
																.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
															.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_panel.createSequentialGroup()
																	.addGap(18)
																	.addComponent(label_1))
																.addGroup(gl_panel.createSequentialGroup()
																	.addGap(7)
																	.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
															.addContainerGap())
												);
												algo1.setLayout(gl_panel);
												
														pnlFilter = new JPanel();
														pnlFilter.setBounds(0, 0, 767, 538);
														pnlCardsFilters.add(pnlFilter);
														pnlFilter.setBackground(new Color(240, 248, 255));
														chckbxTime = new JCheckBox("Time");
														chckbxTime.setBackground(new Color(240, 248, 255));
														chckbxTime.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
														chckbxTime.setBounds(0, 5, 133, 53);
														
																chckbxGPS = new JCheckBox("GPS Coordinates");
																chckbxGPS.setBackground(new Color(240, 248, 255));
																chckbxGPS.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
																chckbxGPS.setBounds(175, 5, 321, 53);
																
																
																		chckbxID = new JCheckBox("Device ID");
																		chckbxID.setBackground(new Color(240, 248, 255));
																		chckbxID.setFont(new Font("Monotype Corsiva", Font.PLAIN, 37));
																		chckbxID.setBounds(523, 5, 209, 53);
																		pnlFilter.setLayout(null);
																		pnlFilter.add(chckbxTime);
																		pnlFilter.add(chckbxGPS);
																		pnlFilter.add(chckbxID);
		contentPane.add(pnlAddOptions);
		contentPane.add(scrConsole);
		
		JLabel lblFilters = DefaultComponentFactory.getInstance().createTitle("Filters:");
		lblFilters.setFont(new Font("Monotype Corsiva", Font.PLAIN, 45));
		lblFilters.setBounds(40, 86, 298, 45);
		contentPane.add(lblFilters);

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
							if(mergedList.isEmpty()) mergedList = ReaderFromCsv.notSortedFileToArrayListOfTenMostPowerfulWifiPoints(listOfFiles);
							else{
								ListOfWifiRows tempList= ReaderFromCsv.notSortedFileToArrayListOfTenMostPowerfulWifiPoints(listOfFiles);
								mergedList=Filters.mergeTwoListsByDateAndID(tempList, mergedList);
								mergedList.Print();
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
						if(mergedList.isEmpty()) mergedList = ReaderFromCsv.readerFromMergedCSVtoList(csvFolderAbsPath);
						else{
							ListOfWifiRows tempList= ReaderFromCsv.readerFromMergedCSVtoList(csvFolderAbsPath);
							mergedList=Filters.mergeTwoListsByDateAndID(tempList, mergedList);
							mergedList.Print();
						}
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
				
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
						if(filteredList.isEmpty()) Kml.addMarksFromList(mergedList); 
						else Kml.addMarksFromList(filteredList); 
						File file=new File(saveFile.fileChooser.getSelectedFile().getAbsolutePath()+".kml");
						Kml.writeFile(file);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				}
			}
		});
		
	/*****************here you need to work on layout*******************/
		
			mntmNL.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
									CardLayout cardLayout = (CardLayout) pnlCardsFilters.getLayout();
									cardLayout.show(pnlCardsFilters, "algo1");
							
				}}
			);



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



		btRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mergedList.isEmpty()){
					System.out.println("Don't have data to work with");
				}
				else{
					filteredList=mergedList.copy();
					
					if((mac=textField.getText())!=null){
					int numOfNet=slider.getValue();
					try {
						RowOfWifiPoints a=LocationRevaluation.centerOfRouter1(filteredList, mac, numOfNet);
						filteredList=new ListOfWifiRows();
						filteredList.add(a);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
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
						Coordinates_3D a=new Coordinates_3D(txtStartTimeExtra.getText(), txtEndTimeExtra.getText(), "0");
						Coordinates_3D b=new Coordinates_3D(txtLat2Extra.getText(), txtLon2Extra.getText(), "0");
						filter time=new Time_Filter(txtStartTimeExtra.getText(),txtEndTimeExtra.getText());
						filter coor=new Position_Filter_Rect(a,b);
						filter and=new And_Filter(coor,time);
						filteredList.filter(and);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else if(chckbxTime.isSelected())
				{
					try {
						filter time=new Time_Filter(txtStartTime.getText(),txtEndTime.getText());
						filteredList.filter(time);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else if(chckbxGPS.isSelected() && chckbxID.isSelected() && chckbxTime.isSelected())
				{
					try {
						Coordinates_3D a=new Coordinates_3D(txtLatExtra3.getText(), txtLonExtra3.getText(), "0");
						Coordinates_3D b=new Coordinates_3D(txtLat2Extra3.getText(), txtLon2Extra3.getText(), "0");
						filter coor=new Position_Filter_Rect(a,b);
						filter id=new Id_Filter(txtIDExtra3.getText());
						filter time=new Time_Filter(txtStartTimeExtra3.getText(),txtEndTimeExtra3.getText());
						filter and=new And_Filter(id,time);
						filter and2=new And_Filter(and,coor);
						filteredList.filter(and2);
						
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
						filter and=new And_Filter(id,coor);
						filteredList.filter(and);

						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else if(chckbxTime.isSelected() && chckbxID.isSelected())
				{
					try {
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
				if(filteredList.isEmpty())System.out.println("Don't have data like this");
				else{System.out.println();
				filteredList.Print();
				}
				}
			}
		});


	
	}
}