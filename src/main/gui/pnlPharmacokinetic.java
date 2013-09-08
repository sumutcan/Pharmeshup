package main.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextField;

import main.classes.DrugSearchController;
import main.classes.dataaccess.LinkedDataAccessFactory;
import main.classes.entities.Enzyme;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class pnlPharmacokinetic extends JPanel {

	/**
	 * Create the panel.
	 */
	
	final JTable dataTable;
	private JTextField txtName;
	private JTextField txtCellular;
	private JTextField txtReaction;
	private JTextField txtMolecularWeight;
	private JTextField txtTheoPi;
	private JTextField txtSpecificFunc;
	public JList lstEnzymes;
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
		headerTable.setValueAt("Molecular Weight (Mono)", 4, 0);
		headerTable.setValueAt("Molecular Weight (AVG)", 5, 0);
		headerTable.setValueAt("Halflife", 6, 0);
		
		dataTable.setTableHeader(null);
		dataTable.setRowHeight(50);
		
		scrollPane.setViewportView(dataTable);
	    scrollPane.setRowHeaderView(headerTable);
		splitPane.setLeftComponent(scrollPane);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);
		
		JPanel panel = new JPanel();
		splitPane_1.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblEnzymes = new JLabel("Enzymes");
		panel.add(lblEnzymes, BorderLayout.WEST);
		lblEnzymes.setForeground(UIManager.getColor("OptionPane.warningDialog.titlePane.foreground"));
		lblEnzymes.setFont(new Font("Ubuntu Medium", Font.BOLD, 15));
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_1.setRightComponent(splitPane_2);
		
		JPanel panel_1 = new JPanel();
		splitPane_2.setLeftComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lstEnzymes = new JList();
		lstEnzymes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && lstEnzymes.getSelectedValue() instanceof Enzyme)
				{

					try {
						Enzyme selectedEnzyme = (Enzyme)lstEnzymes.getSelectedValue();
						DrugSearchController controller = new DrugSearchController();
						controller.getEnzymeData(selectedEnzyme);
						
						txtCellular.setText(selectedEnzyme.getCellularLocation());
						txtMolecularWeight.setText(selectedEnzyme.getMolecularWeight());
						txtName.setText(selectedEnzyme.getName());
						txtReaction.setText(selectedEnzyme.getReaction());
						txtSpecificFunc.setText(selectedEnzyme.getSpecificFunction());
						txtTheoPi.setText(selectedEnzyme.getTheoreticalPi());
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			}
		});
		panel_1.add(lstEnzymes, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		splitPane_2.setRightComponent(panel_2);
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblNewLabel = new JLabel("Name:");
		panel_2.add(lblNewLabel, "2, 2, left, default");
		
		txtName = new JTextField();
		txtName.setEditable(false);
		panel_2.add(txtName, "3, 2, 4, 1, fill, default");
		txtName.setColumns(10);
		
		JLabel lblCellularLocation = new JLabel("Cellular Location:");
		panel_2.add(lblCellularLocation, "2, 4, left, default");
		
		txtCellular = new JTextField();
		txtCellular.setEditable(false);
		panel_2.add(txtCellular, "3, 4, 4, 1, fill, default");
		txtCellular.setColumns(10);
		
		JLabel lblReaction = new JLabel("Reaction:");
		panel_2.add(lblReaction, "2, 6");
		
		txtReaction = new JTextField();
		txtReaction.setEditable(false);
		panel_2.add(txtReaction, "3, 6, 4, 1, fill, default");
		txtReaction.setColumns(10);
		
		JLabel lblSpecificFunction = new JLabel("Specific Function:");
		panel_2.add(lblSpecificFunction, "2, 8");
		
		txtSpecificFunc = new JTextField();
		txtSpecificFunc.setEditable(false);
		panel_2.add(txtSpecificFunc, "3, 8, 4, 1, fill, default");
		txtSpecificFunc.setColumns(10);
		
		JLabel lblMolecularWeight = new JLabel("Molecular Weight:");
		panel_2.add(lblMolecularWeight, "2, 10");
		
		txtMolecularWeight = new JTextField();
		txtMolecularWeight.setEditable(false);
		panel_2.add(txtMolecularWeight, "6, 10, fill, default");
		txtMolecularWeight.setColumns(10);
		
		JLabel lblTheoriticalPi = new JLabel("Theoritical Pi:");
		panel_2.add(lblTheoriticalPi, "8, 10, right, default");
		
		txtTheoPi = new JTextField();
		txtTheoPi.setEditable(false);
		panel_2.add(txtTheoPi, "10, 10, fill, default");
		txtTheoPi.setColumns(10);
		
		JLabel lblArticles = new JLabel("Articles:");
		lblArticles.setFont(new Font("Dialog", Font.BOLD, 14));
		panel_2.add(lblArticles, "2, 12");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, "2, 14, 9, 1, fill, fill");
		
		JList lstArticles = new JList();
		scrollPane_1.setViewportView(lstArticles);
		splitPane_2.setDividerLocation(160);
		
		
	}

}

