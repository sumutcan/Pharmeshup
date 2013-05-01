package main.gui;

import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.TextArea;

import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

import main.classes.entities.Link;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class pnlGeneral extends JPanel {
	
	JTextPane txtPaneDBPedia;
	JList listRelatedPages;
	JTextPane txtPaneDrugbank;
	JList lstSynonyms;
	public pnlGeneral() {
		setSize(new Dimension(770,760));
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(5);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setLeftComponent(splitPane_1);
		
		JPanel panel = new JPanel();
		splitPane_1.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("Description");
		label.setForeground(UIManager.getColor("OptionPane.warningDialog.titlePane.foreground"));
		label.setFont(new Font("Ubuntu Medium", Font.BOLD, 15));
		panel.add(label, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		splitPane_1.setRightComponent(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JSplitPane splitPane_8 = new JSplitPane();
		splitPane_8.setDividerSize(3);
		panel_1.add(splitPane_8);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		splitPane_8.setLeftComponent(scrollPane);
		
	    txtPaneDBPedia = new JTextPane();
	    txtPaneDBPedia.setEditable(false);
		scrollPane.setViewportView(txtPaneDBPedia);
		
		JLabel lblDbpedia = new JLabel("DBPedia");
		lblDbpedia.setForeground(UIManager.getColor("OptionPane.warningDialog.titlePane.foreground"));
		lblDbpedia.setFont(new Font("Ubuntu", Font.BOLD, 13));
		scrollPane.setColumnHeaderView(lblDbpedia);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		splitPane_8.setRightComponent(scrollPane_1);
		
		txtPaneDrugbank = new JTextPane();
		txtPaneDrugbank.setEditable(false);
		scrollPane_1.setViewportView(txtPaneDrugbank);
		
		JLabel lblDrugbank = new JLabel("Drugbank");
		lblDrugbank.setForeground(UIManager.getColor("OptionPane.warningDialog.titlePane.foreground"));
		lblDrugbank.setFont(new Font("Ubuntu", Font.BOLD, 13));
		scrollPane_1.setColumnHeaderView(lblDrugbank);
		splitPane_8.setDividerLocation(380);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		panel_1.add(separator);
		splitPane_1.setDividerLocation(30);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_2);
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setDividerSize(5);
		splitPane_2.setLeftComponent(splitPane_3);
		
		JSplitPane splitPane_4 = new JSplitPane();
		splitPane_4.setDividerSize(2);
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setLeftComponent(splitPane_4);
		
		JPanel panel_2 = new JPanel();
		panel_2.setFont(new Font("Ubuntu Medium", Font.BOLD, 12));
		splitPane_4.setLeftComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblRelatedPages = new JLabel("Related Pages");
		lblRelatedPages.setFont(new Font("Ubuntu", Font.BOLD, 13));
		lblRelatedPages.setForeground(UIManager.getColor("OptionPane.warningDialog.titlePane.foreground"));
		panel_2.add(lblRelatedPages, BorderLayout.WEST);
		
	    listRelatedPages = new JList();
	    listRelatedPages.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    	
				if (e.getClickCount() == 2)
				{
					
					try {
						Desktop.getDesktop().browse(((Link)listRelatedPages.getSelectedValue()).getURL());
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
	    	
	    	}
	    });
		listRelatedPages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		splitPane_4.setRightComponent(listRelatedPages);
		splitPane_4.setDividerLocation(20);
		
		JSplitPane splitPane_5 = new JSplitPane();
		splitPane_5.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_5.setDividerSize(2);
		splitPane_3.setRightComponent(splitPane_5);
		
		JPanel panel_3 = new JPanel();
		panel_3.setFont(new Font("Ubuntu Medium", Font.BOLD, 12));
		splitPane_5.setLeftComponent(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSynonyms = new JLabel("Synonyms");
		lblSynonyms.setFont(new Font("Ubuntu", Font.BOLD, 13));
		lblSynonyms.setForeground(UIManager.getColor("OptionPane.warningDialog.titlePane.foreground"));
		panel_3.add(lblSynonyms, BorderLayout.WEST);
		
		lstSynonyms = new JList();
		splitPane_5.setRightComponent(lstSynonyms);
		splitPane_5.setDividerLocation(20);
		splitPane_3.setDividerLocation(380);
		
		JSplitPane splitPane_6 = new JSplitPane();
		splitPane_6.setDividerSize(5);
		splitPane_6.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_2.setRightComponent(splitPane_6);
		
		JSplitPane splitPane_7 = new JSplitPane();
		splitPane_7.setDividerSize(5);
		splitPane_6.setRightComponent(splitPane_7);
		
		JList list_1 = new JList();
		splitPane_7.setLeftComponent(list_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		splitPane_7.setRightComponent(scrollPane_2);
		splitPane_7.setDividerLocation(200);
		
		JPanel panel_4 = new JPanel();
		splitPane_6.setLeftComponent(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JLabel lblFdaProuductLabel = new JLabel("FDA Prouduct Label Data");
		lblFdaProuductLabel.setFont(new Font("Ubuntu Medium", Font.BOLD, 15));
		lblFdaProuductLabel.setForeground(UIManager.getColor("OptionPane.warningDialog.titlePane.foreground"));
		panel_4.add(lblFdaProuductLabel);
		splitPane_6.setDividerLocation(30);
		splitPane_2.setDividerLocation(90);
		splitPane.setDividerLocation(200);
	}
	
	public void setTxtPaneDBPedia(String text)
	{
		txtPaneDBPedia.setText(text);
	}

}
