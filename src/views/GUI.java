package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import java.awt.Color;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JInternalFrame;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;

public class GUI extends JFrame {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
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
	private void initComponents()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btOpencsvFolder = new JButton("Open .csv Folder");

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
							.addComponent(scrConsole, GroupLayout.PREFERRED_SIZE, 628, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 628, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addComponent(btOpencsvFolder)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(rdbCreatekmlFile)
									.addComponent(pnlOptions, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
									.addComponent(rdbtnCalculateNetworksLocation))
								.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
								.addComponent(pnlFilter, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(rdbtnCalculateClientsLocation)
								.addContainerGap(398, Short.MAX_VALUE)))
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
					.addComponent(scrConsole, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btOpencsvFolder)
					.addContainerGap())
		);
		
		JTextPane txtConsole = new JTextPane();
		txtConsole.setBackground(Color.GRAY);
		scrConsole.setViewportView(txtConsole);
		
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
}