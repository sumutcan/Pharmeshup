package main.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class pnlPharmacokinetic extends JPanel {

	/**
	 * Create the panel.
	 */
	
	final JTable dataTable;
	public pnlPharmacokinetic() {
		setSize(new Dimension(770,760));
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMaximumSize(new Dimension(770, 300));
		scrollPane.setMinimumSize(new Dimension(770, 300));
		scrollPane.setSize(new Dimension(770, 300));
		

		dataTable = new JTable(7,1);
		dataTable.setValueAt(0, 0, 0);
		DefaultTableModel model = new DefaultTableModel()
		{
		     private static final long serialVersionUID = 1L;

	            @Override
	            public int getColumnCount() {
	                return 1;
	            }

	            @Override
	            public boolean isCellEditable(int row, int col) {
	                return false;
	            }

	            @Override
	            public int getRowCount() {
	                return dataTable.getRowCount();
	            }

	            @Override
	            public Class<?> getColumnClass(int colNum) {
	                switch (colNum) {
	                    case 0:
	                        return String.class;
	                    default:
	                        return super.getColumnClass(colNum);
	                }
	            }
			
		};
		JTable headerTable = new JTable(model);
		
		headerTable.setCellSelectionEnabled(false);
		headerTable.setRowSelectionAllowed(false);
		headerTable.setFont(new Font("Ubuntu Medium", Font.BOLD, 15));
		headerTable.setForeground(SystemColor.activeCaption);
		headerTable.setRowHeight(50);
		
		headerTable.setValueAt("Absorption", 0, 0);
		headerTable.setValueAt("Clearence", 1, 0);
		headerTable.setValueAt("Route of Elimination", 2, 0);
		headerTable.setValueAt("Volume of Distribution", 3, 0);
		headerTable.setValueAt("Molecule Weight", 4, 0);
		headerTable.setValueAt("Molecule Weight (AVG)", 5, 0);
		headerTable.setValueAt("Halftime", 6, 0);
		
		dataTable.setTableHeader(null);
		dataTable.setRowHeight(50);
		
		scrollPane.setViewportView(dataTable);
	    scrollPane.setRowHeaderView(headerTable);
		splitPane.setLeftComponent(scrollPane);
		splitPane.setDividerLocation(300);
	}

}

