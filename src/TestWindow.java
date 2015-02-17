import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;


public class TestWindow {

	public JFrame frame;
	public JTextField txtCiepla;
	public JTextField txtZimna;
	public JTextField txtCieplaInput;
	public JTextField txtZimnaInput;
	public JTextField txtGazInput;
	public JTextField txtPrdInput;
	public JTextField txtNet;
	public JLabel lblWynik;
	public JPanel pnlMain;
	JLabel lblBlad;
	Liczniki liczniki = new Liczniki(this);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestWindow window = new TestWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 396, 261);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		pnlMain = new JPanel();
		frame.getContentPane().add(pnlMain);
		pnlMain.setLayout(null);
		
		txtCieplaInput = new JTextField();
		txtCieplaInput.setText("Woda ciep\u0142a");
		txtCieplaInput.setBounds(10, 33, 86, 20);
		pnlMain.add(txtCieplaInput, "cell 0 0,grow");
		txtCieplaInput.setColumns(10);
		
		JLabel label = new JLabel("");
		pnlMain.add(label, "cell 1 0,grow");
		
		JLabel lblWpiszZuycie = new JLabel("Wpisz stan licznik\u00F3w:");
		lblWpiszZuycie.setBounds(10, 8, 127, 14);
		pnlMain.add(lblWpiszZuycie, "cell 2 0,grow");
		
		JLabel label_1 = new JLabel("");
		pnlMain.add(label_1, "cell 3 0,grow");
		
		txtZimnaInput = new JTextField();
		txtZimnaInput.setText("Woda zimna");
		txtZimnaInput.setColumns(10);
		txtZimnaInput.setBounds(10, 59, 86, 20);
		pnlMain.add(txtZimnaInput, "cell 4 0,grow");
		
		JLabel label_2 = new JLabel("");
		pnlMain.add(label_2, "cell 5 0,grow");
		
		JLabel label_3 = new JLabel("");
		pnlMain.add(label_3, "cell 6 0,grow");
		
		JLabel lblWpiszWartoFaktur = new JLabel("Wpisz warto\u015B\u0107 faktur:");
		lblWpiszWartoFaktur.setBounds(213, 8, 127, 14);
		pnlMain.add(lblWpiszWartoFaktur);
		
		txtGazInput = new JTextField();
		txtGazInput.setText("Gaz");
		txtGazInput.setColumns(10);
		txtGazInput.setBounds(213, 33, 86, 20);
		pnlMain.add(txtGazInput, "cell 1 1,grow");
		
		JButton btnUstaw = new JButton("Ustawienia");
		btnUstaw.setBounds(254, 167, 116, 38);
		pnlMain.add(btnUstaw);
		btnUstaw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				liczniki.laduj();
				frame.getContentPane().add(PanelUstaw());
				frame.getContentPane().remove(pnlMain);
				txtZimna.setText("" + liczniki.getCenaZimna());
				txtCiepla.setText("" + liczniki.getCenaCiepla());
				txtNet.setText("" + liczniki.getInternet());
								
				
			}
		});

		
		txtPrdInput = new JTextField();
		txtPrdInput.setText("Pr\u0105d");
		txtPrdInput.setColumns(10);
		txtPrdInput.setBounds(213, 59, 86, 20);
		pnlMain.add(txtPrdInput, "cell 3 1,grow");
		
		
		JButton btnPolicz = new JButton("Policz");
		btnPolicz.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPolicz.setBounds(10, 106, 100, 20);
		pnlMain.add(btnPolicz, "cell 5 1,grow");
		btnPolicz.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e)  {
			Writer zapisz = new Writer();
			double bLicznikCiepla;
			double bLicznikZimna;
			double bCenaGaz;
			double bCenaPrad;
			
			
			try {
				lblWynik.setText("");
				bLicznikCiepla = Double.parseDouble(txtCieplaInput.getText());
				liczniki.setLicznikCiepla(bLicznikCiepla);
				bLicznikZimna = Double.parseDouble(txtZimnaInput.getText());
				liczniki.setLicznikZimna(bLicznikZimna);
				bCenaGaz =  Double.parseDouble(txtGazInput.getText());
				liczniki.setCenaGaz(bCenaGaz);
				bCenaPrad = Double.parseDouble(txtPrdInput.getText());
				liczniki.setCenaPrad(bCenaPrad);
				
				try {
					liczniki.laduj();
					liczniki.licz();
					lblWynik.setText("" + liczniki.getWynik());
					zapisz.WriteMeters(liczniki);
				} 
				catch (IOException e1) {
					e1.printStackTrace();
					//lblWynik.setText("aua");
				}
				
				}
			
			catch(NumberFormatException n) {
				lblWynik.setText("Wpisz poprawne wartości");
			}
			

								
				
			}
		});
		
		lblWynik = new JLabel("");
		lblWynik.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWynik.setBounds(10, 167, 205, 38);
		pnlMain.add(lblWynik);
		
		JButton btnZapiszW = new JButton("Zapisz");
		btnZapiszW.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnZapiszW.setBounds(180, 106, 100, 20);
		pnlMain.add(btnZapiszW);
		
			
	}
		
		
	JPanel PanelUstaw() {
		
		pnlMain.setVisible(false);
		JPanel pnlSet = new JPanel();
		pnlSet.setLayout(null);
		pnlSet.setVisible(true);
	
		JLabel lblCiepla = new JLabel("Ciep\u0142a woda:");
		lblCiepla.setBounds(10, 8, 127, 14);
		pnlSet.add(lblCiepla);
		
		txtCiepla = new JTextField();
		txtCiepla.setBounds(91, 36, 65, 14);
		pnlSet.add(txtCiepla);
		txtCiepla.setColumns(10);
		
		
		JLabel lblZimna = new JLabel("Zimna woda:");
		lblZimna.setBounds(10, 66, 113, 14);
		pnlSet.add(lblZimna);
		
		txtZimna = new JTextField();
		txtZimna.setColumns(10);
		txtZimna.setBounds(91, 66, 65, 14);
		
		pnlSet.add(txtZimna);
		
		JLabel lblCeny = new JLabel("Ceny");
		lblCeny.setBounds(110, 11, 46, 14);
		pnlSet.add(lblCeny);
		
		JLabel lblNet = new JLabel("Internet");
		lblNet.setBounds(10, 96, 113, 14);
		pnlSet.add(lblNet);
		
		txtNet = new JTextField();
		txtNet.setColumns(10);
		txtNet.setBounds(91, 96, 65, 14);
		
		pnlSet.add(txtNet);
		
		JButton btnZapisz = new JButton("Zapisz");
		btnZapisz.setBounds(181, 139, 79, 23);
		pnlSet.add(btnZapisz);
		
		JButton btnAnuluj = new JButton("Powrót");
		btnAnuluj.setBounds(89, 139, 79, 23);
		pnlSet.add(btnAnuluj);
		
		lblBlad = new JLabel();
		pnlSet.add(lblBlad);
		lblBlad.setBounds(213, 8, 180, 14);
		lblBlad.setVisible(false);
		
	
		
		btnAnuluj.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				pnlSet.setVisible(false);
				frame.getContentPane().add(pnlMain);
				pnlMain.setVisible(true);
				frame.getContentPane().remove(pnlSet);
				lblWynik.setText("");
					
				
			}
			
		});
		// 		
		
		btnZapisz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double bCenaZimna;
				double bCenaCiepla;
				double bInternet;
				Writer save = new Writer();
				try {
				
				bCenaZimna = Double.parseDouble(txtZimna.getText());
				liczniki.setCenaZimna(bCenaZimna);
				bCenaCiepla = Double.parseDouble(txtCiepla.getText());
				liczniki.setCenaCiepla(bCenaCiepla);
				bInternet = Double.parseDouble(txtNet.getText());
				liczniki.setInternet(bInternet);
				lblBlad.setVisible(false);
				save.WriteSettings(liczniki);
				lblBlad.setText("Ustawienia zapisane");
				lblBlad.setVisible(true);
				
				
				}
				catch (Exception e2) {
					lblBlad.setText("Wpisz poprawne wartości");
					lblBlad.setVisible(true);
				}
			}
		});		
		return pnlSet;	
	
		
	}
}
