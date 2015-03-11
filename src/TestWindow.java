import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.io.IOException;


public class TestWindow {

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
	public JFrame frame;
	public JTextField txtCiepla;
	public JTextField txtZimna;
	public JTextField txtCieplaInput;
	public JTextField txtZimnaInput;
	public JTextField txtGazInput;
	public JTextField txtPrdInput;
	public JTextField txtNet;
	public JPanel pnlMain;
	double bLicznikCiepla;
	double bLicznikZimna;
	double bCenaGaz;
	double bCenaPrad;
	JTextArea txtWynik;

	Liczniki liczniki = new Liczniki(this);

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
		frame.setBounds(100, 100, 522, 378);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		pnlMain = new JPanel();
		frame.getContentPane().add(pnlMain);
		pnlMain.setLayout(null);
		
		JLabel lblLicznikCiepla = new JLabel("Licznik - woda ciep\u0142a");
		lblLicznikCiepla.setToolTipText("Stan licznika ciep\u0142ej wody");
		lblLicznikCiepla.setFont(new Font("Arial", Font.BOLD, 14));
		lblLicznikCiepla.setBounds(32, 35, 151, 24);
		pnlMain.add(lblLicznikCiepla);
		
		JLabel lblLicznikZimna = new JLabel("Licznik - woda zimna");
		lblLicznikZimna.setToolTipText("Stan licznika zimnej wody");
		lblLicznikZimna.setFont(new Font("Arial", Font.BOLD, 14));
		lblLicznikZimna.setBounds(32, 70, 151, 24);
		pnlMain.add(lblLicznikZimna);
		
		JLabel lblGaz = new JLabel("Op\u0142aty za gaz");
		lblGaz.setToolTipText("Kwota z faktury za gaz");
		lblGaz.setFont(new Font("Arial", Font.BOLD, 14));
		lblGaz.setBounds(32, 105, 151, 24);
		pnlMain.add(lblGaz);
		
		JLabel lblPrad = new JLabel("Op\u0142aty za pr\u0105d");
		lblPrad.setToolTipText("Kwota z faktury za pr\u0105d");
		lblPrad.setFont(new Font("Arial", Font.BOLD, 14));
		lblPrad.setBounds(32, 140, 151, 24);
		pnlMain.add(lblPrad);
		
		txtWynik = new JTextArea();
		txtWynik.setFont(new Font("Arial", Font.PLAIN, 12));
		txtWynik.setText("Wpisz odczyty licznik\u00F3w i warto\u015B\u0107 faktur.");
		txtWynik.setBounds(32, 206, 308, 111);
		pnlMain.add(txtWynik);
		
		txtCieplaInput = new JTextField();
		//txtCieplaInput.setText("Woda ciep\u0142a");
		txtCieplaInput.setBounds(240, 36, 100, 24);
		pnlMain.add(txtCieplaInput);
		txtCieplaInput.setColumns(10);
		txtCieplaInput.addFocusListener(new FocusListener(){
			
			public void focusGained(FocusEvent e) {
				
				txtCieplaInput.selectAll();
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
				
		
		
		txtZimnaInput = new JTextField();
		txtZimnaInput.setText("Woda zimna");
		txtZimnaInput.setColumns(10);
		txtZimnaInput.setBounds(240, 70, 100, 24);
		pnlMain.add(txtZimnaInput, "cell 4 0,grow");
		txtZimnaInput.addFocusListener(new FocusListener(){
			
			public void focusGained(FocusEvent e) {
				
				txtZimnaInput.selectAll();
				//txtWynik.setText("Licznik zimnej wody");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				//txtWynik.setText("");
				
			}});
	
		
		txtGazInput = new JTextField();
		txtGazInput.setText("Gaz");
		txtGazInput.setColumns(10);
		txtGazInput.setBounds(240, 105, 100, 24);
		pnlMain.add(txtGazInput);
		
		txtGazInput.addFocusListener(new FocusListener(){
			
			public void focusGained(FocusEvent e) {
				
				txtGazInput.selectAll();
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
		JButton btnUstaw = new JButton("Ustawienia");
		btnUstaw.setBounds(383, 283, 100, 30);
		pnlMain.add(btnUstaw);
		btnUstaw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				liczniki.ladujUstawienia();
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
		txtPrdInput.setBounds(240, 140, 100, 24);
		pnlMain.add(txtPrdInput);
		
		txtPrdInput.addFocusListener(new FocusListener(){
			
			public void focusGained(FocusEvent e) {
				
				txtPrdInput.selectAll();
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
		
		JButton btnPolicz = new JButton("Policz");
		btnPolicz.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPolicz.setBounds(383, 242, 100, 30);
		pnlMain.add(btnPolicz);
		btnPolicz.addActionListener(new ActionListener() {	
				public void actionPerformed(ActionEvent e)  {
					
				
			
			try {
				txtWynik.setText("");
				bLicznikCiepla = Double.parseDouble(txtCieplaInput.getText());
				liczniki.setLicznikCiepla(bLicznikCiepla);
				bLicznikZimna = Double.parseDouble(txtZimnaInput.getText());
				liczniki.setLicznikZimna(bLicznikZimna);
				bCenaGaz =  Double.parseDouble(txtGazInput.getText());
				liczniki.setCenaGaz(bCenaGaz);
				bCenaPrad = Double.parseDouble(txtPrdInput.getText());
				liczniki.setCenaPrad(bCenaPrad);
				
				try {
					liczniki.ladujUstawienia();
					liczniki.ladujLiczniki();
					liczniki.licz();
					txtWynik.setText("Rachunki na osobę " + liczniki.getWynik());
					txtWynik.setLineWrap(true);
					txtWynik.setWrapStyleWord(true);
				} 
			
				catch (IOException e1) {
					txtWynik.setText("Błąd odczytu pliku");
					
					//e1.printStackTrace();
					//txtWynik.setText("aua");
				}
				
			}
			
			catch(NumberFormatException n) {
				txtWynik.setText("Wpisz poprawne wartości");
				}
			}
		
				
				
			
		});
		
		
//		txtWynik = new JLabel("");
//		txtWynik.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		txtWynik.setBounds(10, 167, 205, 38);
//		pnlMain.add(txtWynik);
		
		JButton btnZapiszW = new JButton("Zapisz");
		btnZapiszW.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnZapiszW.setBounds(383, 201, 100, 30);
		pnlMain.add(btnZapiszW);
		
		btnZapiszW.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Writer zapisz = new Writer();
								
				try {
					txtWynik.setText("");
					bLicznikCiepla = Double.parseDouble(txtCieplaInput.getText());
					liczniki.setLicznikCiepla(bLicznikCiepla);
					bLicznikZimna = Double.parseDouble(txtZimnaInput.getText());
					liczniki.setLicznikZimna(bLicznikZimna);
					bCenaGaz =  Double.parseDouble(txtGazInput.getText());
					liczniki.setCenaGaz(bCenaGaz);
					bCenaPrad = Double.parseDouble(txtPrdInput.getText());
					liczniki.setCenaPrad(bCenaPrad);
					
						try {
							zapisz.WriteMeters(liczniki);
							txtWynik.setText("Liczniki zapisane");
						}
						catch (IOException e1) {
							txtWynik.setText("Błąd odczytu pliku");
							
							//e1.printStackTrace();
							//txtWynik.setText("aua");
						}
				}
					
					
					catch(NumberFormatException n) {
						txtWynik.setText("Wpisz poprawne wartości");
						}
				}
			
		});
			
		
	}
	

		
		
	JPanel PanelUstaw() {
		Writer save = new Writer();
		pnlMain.setVisible(false);
		JPanel pnlSet = new JPanel();
		pnlSet.setLayout(null);
		pnlSet.setVisible(true);
	
		JLabel lblCiepla = new JLabel("Ciep\u0142a woda:");
		lblCiepla.setFont(new Font("Arial", Font.BOLD, 14));
		lblCiepla.setBounds(32, 35, 151, 24);
		pnlSet.add(lblCiepla);
		
		txtCiepla = new JTextField();
		txtCiepla.setBounds(180, 36, 100, 24);
		pnlSet.add(txtCiepla);
		txtCiepla.setColumns(10);
		
		
		JLabel lblZimna = new JLabel("Zimna woda:");
		lblZimna.setFont(new Font("Arial", Font.BOLD, 14));
		lblZimna.setBounds(32, 70, 151, 24);
		pnlSet.add(lblZimna);
		
		txtZimna = new JTextField();
		txtZimna.setColumns(10);
		txtZimna.setBounds(180, 70, 100, 24);
		
		pnlSet.add(txtZimna);
		
	
		JLabel lblNet = new JLabel("Internet");
		lblNet.setFont(new Font("Arial", Font.BOLD, 14));
		lblNet.setBounds(32, 105, 151, 24);
		pnlSet.add(lblNet);
		
		txtNet = new JTextField();
		txtNet.setColumns(10);
		txtNet.setBounds(180, 105, 100, 24);
		pnlSet.add(txtNet);
		
		JTextField txtFoldUst = new JTextField();
		txtFoldUst.setColumns(10);
		txtFoldUst.setBounds(180, 140, 100, 24);
		txtFoldUst.setText(save.getPath());
		pnlSet.add(txtFoldUst);
		
		JLabel lblFoldUst = new JLabel("Lokalizacja plików");
		lblFoldUst.setFont(new Font("Arial", Font.BOLD, 14));
		lblFoldUst.setBounds(32, 140, 151, 24);
		pnlSet.add(lblFoldUst);
		
		JButton btnZapisz = new JButton("Zapisz");
		btnZapisz.setBounds(383, 201, 100, 30);
		pnlSet.add(btnZapisz);
		
		JButton btnAnuluj = new JButton("Powrót");
		btnAnuluj.setBounds(383, 283, 100, 30);
		pnlSet.add(btnAnuluj);
			
		txtWynik = new JTextArea();
		txtWynik.setFont(new Font("Arial", Font.PLAIN, 12));
		txtWynik.setBounds(32, 206, 308, 111);
		pnlSet.add(txtWynik);
	
		
		btnAnuluj.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				pnlSet.setVisible(false);
				frame.getContentPane().add(pnlMain);
				pnlMain.setVisible(true);
				frame.getContentPane().remove(pnlSet);
				txtWynik.setText("");
					
				
			}
			
		});
		// 		
		
		btnZapisz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double bCenaZimna;
				double bCenaCiepla;
				double bInternet;
				
				try {
				
				bCenaZimna = Double.parseDouble(txtZimna.getText());
				liczniki.setCenaZimna(bCenaZimna);
				bCenaCiepla = Double.parseDouble(txtCiepla.getText());
				liczniki.setCenaCiepla(bCenaCiepla);
				bInternet = Double.parseDouble(txtNet.getText());
				liczniki.setInternet(bInternet);
				txtWynik.setVisible(false);
				
				
				
				}
				catch (Exception e2) {
					txtWynik.setText("Wpisz poprawne wartości");
					txtWynik.setVisible(true);
				}
				
					try {
						save.WriteSettings(liczniki);
						txtWynik.setText("Ustawienia zapisane");
						txtWynik.setVisible(true);
					} catch (IOException noSettings) {
						txtWynik.setText("Brak wskazanego folderu");
						txtWynik.setVisible(true);
					}
					
				
				
				
			}
		});		
		
		JButton btnWskazFolder = new JButton("Folder");
		btnWskazFolder.setBounds(383, 242, 100, 30);
		pnlSet.add(btnWskazFolder);
		
		btnWskazFolder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				save.WskazFolder(pnlSet);
				txtFoldUst.setText(save.getPath());
				
				
			}
		});
		
		return pnlSet;	
	
		
	}
}
