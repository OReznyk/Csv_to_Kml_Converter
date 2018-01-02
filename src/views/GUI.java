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
	private JTextField textField;
	private JRadioButton rdbCreatekmlFile;
	private JRadioButton rdbtnCalculateNetworksLocation;
	private JRadioButton rdbtnCalculateClientsLocation;
	private JPanel pnlFilter;
	private String pnlFilterTitle = "Filters:";
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpenFolder;
	private JButton btOpencsvFolder;
	private JTextArea txtConsole;
	private JTextField txtMac;
	private JSlider slNumOfScans;



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
	@SuppressWarnings("static-access")
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

		pnlFilter = new JPanel();

		pnlFilter.setBorder(new TitledBorder(null, pnlFilterTitle, TitledBorder.LEADING, TitledBorder.TOP, null, null));

		rdbtnCalculateClientsLocation = new JRadioButton("Calculate client's location (Algorithm 2)");
		buttonGroup.add(rdbtnCalculateClientsLocation);

		JScrollPane scrConsole = new JScrollPane();
		scrConsole.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrConsole.setBackground(Color.GRAY);
		
		

		JProgressBar progressBar = new JProgressBar();

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblKmlFileName = new JLabel("Kml file name:");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrConsole, GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
							.addGap(12))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
								.addGap(12))
							.addComponent(btOpencsvFolder)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(rdbCreatekmlFile)
									.addComponent(pnlOptions, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
									.addComponent(rdbtnCalculateNetworksLocation))
								.addPreferredGap(ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
								.addComponent(pnlFilter, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(rdbtnCalculateClientsLocation)
								.addContainerGap(431, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblKmlFileName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(pnlOptions, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbCreatekmlFile)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnCalculateNetworksLocation))
						.addComponent(pnlFilter, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnCalculateClientsLocation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKmlFileName))
					.addGap(15)
					.addComponent(scrConsole, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btOpencsvFolder)
					.addContainerGap())
		);
		
		txtConsole = new JTextArea();
		txtConsole.setEditable(false);
		scrConsole.setViewportView(txtConsole);
		
		MessageConsole mc = new MessageConsole(txtConsole);
		mc.redirectOut();
		mc.redirectErr(Color.RED, null);
		
		JLabel lblMac = new JLabel("MAC: ");
		
		txtMac = new JTextField();
		txtMac.setColumns(10);
		
		JLabel lblNumberOfScans = new JLabel("Number of scans:");
		
		slNumOfScans = new JSlider();
		slNumOfScans.setValue(3);
		slNumOfScans.setToolTipText("");
		slNumOfScans.setPaintTicks(true);
		slNumOfScans.setSnapToTicks(true);
		slNumOfScans.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		slNumOfScans.setMaximum(5);
		slNumOfScans.setMinimum(1);
		
		GroupLayout gl_pnlFilter = new GroupLayout(pnlFilter);
		gl_pnlFilter.setHorizontalGroup(
			gl_pnlFilter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFilter.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlFilter.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlFilter.createSequentialGroup()
							.addComponent(lblMac)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtMac, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlFilter.createSequentialGroup()
							.addComponent(lblNumberOfScans)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(slNumOfScans, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_pnlFilter.setVerticalGroup(
			gl_pnlFilter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFilter.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlFilter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMac)
						.addComponent(txtMac, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlFilter.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNumberOfScans)
						.addComponent(slNumOfScans, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pnlFilter.setLayout(gl_pnlFilter);
		
		

		

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
				pnlFilterTitle = "Filters:";
				pnlFilter.setBorder(new TitledBorder(null, pnlFilterTitle, TitledBorder.LEADING, TitledBorder.TOP, null, null));
				filters();
			}
		});
		
		rdbtnCalculateNetworksLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlFilterTitle = "Choose network:";
				pnlFilter.setBorder(new TitledBorder(null, pnlFilterTitle, TitledBorder.LEADING, TitledBorder.TOP, null, null));
				
				
			}
		});



		if(rdbtnCalculateNetworksLocation.isSelected())
		{
			pnlFilterTitle = "Select a network:";
			pnlFilter.setBorder(new TitledBorder(null, pnlFilterTitle, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		if(rdbtnCalculateClientsLocation.isSelected())
		{
			pnlFilterTitle = "**Enter comb_no_gps_ts#:";
			pnlFilter.setBorder(new TitledBorder(null, pnlFilterTitle, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
	}
	private void filters()
	{
		JCheckBox chckbxTime = new JCheckBox("Time");

		JCheckBox chckbxGpsCoordinates = new JCheckBox("GPS Coordinates");

		JCheckBox chckbxId = new JCheckBox("ID");
		GroupLayout gl_pnlFilter = new GroupLayout(pnlFilter);
		gl_pnlFilter.setHorizontalGroup(
				gl_pnlFilter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFilter.createSequentialGroup()
						.addGroup(gl_pnlFilter.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxTime)
								.addComponent(chckbxGpsCoordinates)
								.addComponent(chckbxId))
						.addContainerGap(174, Short.MAX_VALUE))
				);
		gl_pnlFilter.setVerticalGroup(
				gl_pnlFilter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlFilter.createSequentialGroup()
						.addComponent(chckbxTime)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(chckbxGpsCoordinates)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(chckbxId)
						.addContainerGap(7, Short.MAX_VALUE))
				);
		pnlFilter.setLayout(gl_pnlFilter);
	}
}
