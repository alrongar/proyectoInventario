package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.Color;
import javax.swing.JTable;

public class ProductosList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField buscadorText;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductosList frame = new ProductosList();
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
	public ProductosList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.scrollbar);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 0, 575, 41);
		contentPane.add(panel);
		panel.setLayout(null);
		
		buscadorText = new JTextField();
		buscadorText.setForeground(SystemColor.scrollbar);
		buscadorText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buscadorText.setText("buscar");
		buscadorText.setBounds(197, 21, 161, 20);
		panel.add(buscadorText);
		buscadorText.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(SystemColor.scrollbar);
		panel_1.setBounds(-14, 303, 603, 51);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton BackBtn = new JButton("Back");
		BackBtn.setBackground(new Color(255, 224, 223));
		BackBtn.setBounds(456, 10, 109, 31);
		panel_1.add(BackBtn);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 39, 575, 266);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		table = new JTable();
		table.setBounds(12, 12, 551, 242);
		panel_2.add(table);
		BackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}
