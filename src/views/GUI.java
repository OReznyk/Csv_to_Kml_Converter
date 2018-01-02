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

import Tools.Filters;
import Tools.Kml;
import Tools.ReaderWriter;
import Tools.RowOfWifiPoints;

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
	private JButton btOpencsvFolder;
	private JTextArea txtConsole;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField txtMacAddress;
	private JPanel pnlFilters;
	private JPanel pnlCards;



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
		setBounds(100, 100, 676, 481);

		csvFolder = new FileChooser();



		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmOpenFolder = new JMenuItem("Open folder");

		mnFile.add(mntmOpenFolder);
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
								.addComponent(rdbtnCalculateNetworksLocation))
							.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
							.addComponent(pnlCards, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
							.addGap(28))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(rdbtnCalculateClientsLocation)
							.addContainerGap(431, Short.MAX_VALUE))))
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
						.addComponent(pnlCards, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
					.addGap(78)
					.addComponent(scrConsole, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btOpencsvFolder)
					.addContainerGap())
		);
		pnlCards.setLayout(new CardLayout(0, 0));
		
		pnlFilters = new JPanel();
		pnlCards.add(pnlFilters, "Filters");
		JCheckBox chckbxTime = new JCheckBox("Time");
		
		JCheckBox chckbxGPS = new JCheckBox("GPS Coordinates");
		
		JCheckBox chckbxID = new JCheckBox("Device ID");
		
				JLabel lblKmlFileName = new JLabel("Kml file name:");
		
				txtKmlName = new JTextField();
				txtKmlName.setColumns(10);
		GroupLayout gl_pnlFilters = new GroupLayout(pnlFilters);
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
		pnlFilters.setLayout(gl_pnlFilters);
		
		Panel pnlFirstAlgo = new Panel();
		pnlCards.add(pnlFirstAlgo, "firstalgo");
		
		JLabel lblMacAddress = new JLabel("MAC Address:");
		
		txtMacAddress = new JTextField();
		txtMacAddress.setColumns(10);
		
		JLabel lblNumberOfScans = new JLabel("Number of scans: ");
		
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
		
		Panel pnlScndAlgo = new Panel();
		pnlCards.add(pnlScndAlgo, "secondalgo");
		
//		JCheckBox chckbxTime_1 = new JCheckBox("Time");
//		buttonGroup_1.add(chckbxTime_1);
//		
//		JCheckBox chckbxGpsCoordinates_1 = new JCheckBox("GPS Coordinates");
//		buttonGroup_1.add(chckbxGpsCoordinates_1);
//		
//		JCheckBox chckbxId = new JCheckBox("ID");
//		buttonGroup_1.add(chckbxId);
//		pnlFilters.setLayout(new CardLayout(0, 0));
//		pnlFilters.add(chckbxTime_1, "name_582980140823735");
//		pnlFilters.add(chckbxGpsCoordinates_1, "name_582980179151222");
//		pnlFilters.add(chckbxId, "name_582980216506265");
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
					listOfFiles = ReaderWriter.getAllcsvFilesFromFolder(csvFolderAbsPath);
					try {
						listToPrint = ReaderWriter.notSortedFileToArrayListOfTenMostPowerfulWifiPoints(listOfFiles);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					try {
						ReaderWriter.WriterToCsv(listToPrint, csvFolderAbsPath.concat(".csv"));
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
				filters();
			}
		});

		rdbtnCalculateNetworksLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstAlgo();
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
	}

	private void firstAlgo()
	{

	}
}
