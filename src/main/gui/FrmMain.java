package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTabbedPane;

public class FrmMain extends JFrame {
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
					UIManager.put("Panel.background", Color.RED);
					FrmMain frame = new FrmMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmMain() {
		setTitle("Pharmashup");
		setSize(new Dimension(1024, 768));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(5);
	
		getContentPane().add(splitPane);
		
		JSplitPane paneLeft = new JSplitPane();
		paneLeft.setName("");
		paneLeft.setMinimumSize(new Dimension(254, 760));
		paneLeft.setMaximumSize(new Dimension(254, 760));
		paneLeft.setDividerSize(5);
		paneLeft.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setLeftComponent(paneLeft);
		
		JSplitPane paneLeftBottom = new JSplitPane();
		paneLeftBottom.setOrientation(JSplitPane.VERTICAL_SPLIT);
		paneLeft.setRightComponent(paneLeftBottom);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(UIManager.getColor("MenuBar.borderColor"));
		scrollPane.setMinimumSize(new Dimension(22, 180));
		paneLeftBottom.setLeftComponent(scrollPane);
		
		JLabel lblResults = new JLabel("Results");
		scrollPane.setColumnHeaderView(lblResults);
		
		JList listSearchResults = new JList();
		scrollPane.setViewportView(listSearchResults);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		paneLeftBottom.setRightComponent(scrollPane_1);
		
		JLabel lblRecentSearches = new JLabel("Recent Searches");
		scrollPane_1.setColumnHeaderView(lblRecentSearches);
		
		JList listRecentSearches = new JList();
		scrollPane_1.setViewportView(listRecentSearches);
		paneLeftBottom.setDividerLocation(340);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("Separator.shadow"));
		paneLeft.setLeftComponent(panel_1);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		txtSearch = new JTextField();
		txtSearch.putClientProperty("JTextField.variant", "search");
		txtSearch.putClientProperty("JTextField.Search.Prompt", "prompt");
		txtSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				if (txtSearch.getText().compareTo("Search...") == 0)
//					txtSearch.setText("");
			}
		});

		
	
		txtSearch.setPreferredSize(new Dimension(10, 25));
		panel_1.add(txtSearch, "2, 2, 11, 1, fill, fill");
		txtSearch.setColumns(10);
		paneLeft.setDividerLocation(40);
		
		JSplitPane paneRight = new JSplitPane();
		paneRight.setBackground(UIManager.getColor("MenuBar.borderColor"));
		paneRight.setDividerSize(5);
		paneRight.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(paneRight);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBackground(UIManager.getColor("Separator.shadow"));
		paneRight.setRightComponent(scrollPane_2);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("General Info", null);
		tabbedPane.addTab("Pharmacokinetics", null);
		tabbedPane.addTab("Pharmacodynamics", null);
		tabbedPane.addTab("Clinical Trials", null);
		scrollPane_2.setViewportView(tabbedPane);
		
		
		JPanel panel = new JPanel();
		paneRight.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLbldrugname = new JLabel("lblDrugName");
		lblLbldrugname.setForeground(SystemColor.activeCaption);
		lblLbldrugname.setBackground(UIManager.getColor("MenuBar.borderColor"));
		lblLbldrugname.setFont(new Font("Ubuntu Medium", Font.BOLD, 20));
		panel.add(lblLbldrugname, BorderLayout.CENTER);
		paneRight.setDividerLocation(40);
		//setBounds(100, 100, 682, 537);
	}

}
