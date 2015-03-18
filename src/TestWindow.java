import javax.swing.JFrame;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.swing.text.DefaultCaret;

import java.awt.Font;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.stream.StreamSupport;


public class TestWindow {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		TestWindow window = new TestWindow();
		window.frame.setVisible(true);

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
	JTextArea txtSettings;
	private boolean metersLoaded = false;
	private boolean policzono = false;

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
		//txtWynik.setText("Wpisz odczyty licznik\u00F3w i warto\u015B\u0107 faktur.");
		//txtWynik.setBounds(32, 206, 308, 111);
		txtWynik.setLineWrap(true);
		txtWynik.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(txtWynik);
		scrollPane.setBounds(32, 206, 308, 111);
		
		pnlMain.add(scrollPane);


		//pnlMain.add(txtWynik);

		txtCieplaInput = new JTextField();
		//txtCieplaInput.setText("Woda ciep\u0142a");
		txtCieplaInput.setBounds(240, 36, 100, 24);
		pnlMain.add(txtCieplaInput);
		txtCieplaInput.setColumns(10);
		txtCieplaInput.addFocusListener(new FocusListener(){

			@Override
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
		pnlMain.add(txtZimnaInput);
		txtZimnaInput.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {

				txtZimnaInput.selectAll();
				//txtWynik.setText("Licznik zimnej wody");

			}
			@Override
			public void focusLost(FocusEvent e) {
				//txtWynik.setText("");

			}});


		txtGazInput = new JTextField();
		txtGazInput.setColumns(10);
		txtGazInput.setBounds(240, 105, 100, 24);
		pnlMain.add(txtGazInput);

		txtGazInput.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {

				txtGazInput.selectAll();

			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}});

		txtPrdInput = new JTextField();
		txtPrdInput.setColumns(10);
		txtPrdInput.setBounds(240, 140, 100, 24);
		pnlMain.add(txtPrdInput);

		txtPrdInput.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {

				txtPrdInput.selectAll();

			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}});

		try {
			liczniki.ladujLiczniki();
			txtWynik.setText("Załadowano odczyty z dnia " + liczniki.getpData() + "." + "\n" + "Wpisz aktualne wartości liczników.");
			txtCieplaInput.setText("" + liczniki.getpCiepla());
			txtZimnaInput.setText("" + liczniki.getpZimna());
			txtPrdInput.setText("" + liczniki.getpPrad());
			txtGazInput.setText("" + liczniki.getpGaz());

		} catch (IOException e2) {
			txtWynik.setText("Brak pliku z historią odczytów");	
		}






		JButton btnUstaw = new JButton("Ustawienia");
		btnUstaw.setBounds(383, 283, 100, 30);
		pnlMain.add(btnUstaw);
		btnUstaw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.getContentPane().add(PanelUstaw());
					frame.getContentPane().remove(pnlMain);
					liczniki.ladujUstawienia();
					txtZimna.setText("" + liczniki.getCenaZimna());
					txtCiepla.setText("" + liczniki.getCenaCiepla());
					txtNet.setText("" + liczniki.getInternet());
					txtSettings.setText("Załadowano ustawienia.");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					txtSettings.setText(
							"Brak pliku z ustawieniami. Wprowadź wartości i zapisz nowe ustawienia."
							);
				}




			}
		});





		JButton btnPolicz = new JButton("Policz");
		btnPolicz.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPolicz.setBounds(383, 201, 100, 30);
		pnlMain.add(btnPolicz);
		btnPolicz.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e)  {



				try {
					czytajPola();

					try {
						liczniki.ladujUstawienia();
						if (metersLoaded == false) {
							liczniki.ladujLiczniki();
							metersLoaded = true;
							txtWynik.setText(liczniki.licz());
						}					

						else {

							txtWynik.setText(liczniki.licz());
						}
						policzono = true;

					} 

					catch (IOException e1) {
						txtWynik.setText("Błąd odczytu pliku");


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

		JButton btnGenMail = new JButton("Generuj maila");
		btnGenMail.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGenMail.setBounds(383, 241, 100, 30);
		pnlMain.add(btnGenMail);

		btnGenMail.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (policzono == true) {
					String mail =("Cześć," + "\n" + "\n"
							+ "Rachunki za miesiąc:" + "\n" 
							+ "Ciepła woda - " + liczniki.getOplatyCiepla() + "\n"
							+ "Zimna woda - " + liczniki.getOplatyZimna() + "\n" 
							+ "Prąd - " + liczniki.getCenaPrad() + "\n"
							+ "Gaz - " + liczniki.getCenaGaz() + "\n"
							+ "Internet - " + liczniki.getInternet() + "\n" + "\n"
							+ "Razem = " + liczniki.getWynik() * 3 + "\n"
							+ "Razem /3 = " + liczniki.getWynik() +"\n" + "\n"
							+ "Pozdrawiam," + "\n" + "Krzysiek"
							);
					
					txtWynik.setText(mail);

					
					
					StringSelection selection = new StringSelection(mail);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(selection, selection);
					
					
					
					Desktop desktop;
					if (Desktop.isDesktopSupported() 
					    && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
						URI mailto = null;
						//URI mailto.
						
						//URIUtil.encodeQuery
						try {
								mailto = new URI("mailto:blazejkarczewski2@gmail.com;grzegorzpuszkar@wp.pl;m_grudzien@o2.pl?subject=Rachunki%20za&body=" + urlEncode(mail));
							} 
							catch (URISyntaxException e1) {
						
								e1.printStackTrace();
							}
							try {
								desktop.mail(mailto);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
					  // TODO fallback to some Runtime.exec(..) voodoo?
					  throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
					}



				}

				else txtWynik.setText("Policz rachunki aby wygenerować maila");
			}
		});

		JButton btnZapiszW = new JButton("Zapisz");
		btnZapiszW.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnZapiszW.setBounds(383, 159, 100, 30);
		pnlMain.add(btnZapiszW);

		btnZapiszW.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Writer zapisz = new Writer();

				try {

					czytajPola();
					if (metersLoaded == false) {
						liczniki.ladujLiczniki();
						metersLoaded = true;
					}
					if (liczniki.isBigger() == true) {
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

					else txtWynik.setText("Bieżące odczyty są niższe od poprzednich! Popraw odczyty i spróbuj ponownie");
				}
				catch (IOException e2) {
					txtWynik.setText("Brak pliku z historią odczytów");	
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
		txtFoldUst.setBounds(180, 140, save.getPath().length() + 100, 24);
		System.out.println(save.getPath().length());
		txtFoldUst.setText(save.getPath());
		txtFoldUst.setEditable(true);
		txtFoldUst.setBackground(null);
		txtFoldUst.setBorder(null);
		txtFoldUst.setEditable(false);
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

		txtSettings = new JTextArea();
		txtSettings.setFont(new Font("Arial", Font.PLAIN, 12));
		txtSettings.setBounds(32, 206, 308, 111);
		txtSettings.setLineWrap(true);
		txtSettings.setWrapStyleWord(true);
		pnlSet.add(txtSettings);


		btnAnuluj.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				pnlSet.setVisible(false);
				frame.getContentPane().add(pnlMain);
				pnlMain.setVisible(true);
				frame.getContentPane().remove(pnlSet);
				//txtWynik.setText("");


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




				}
				catch (Exception e2) {
					txtSettings.setText("Wpisz poprawne wartości");

				}

				try {
					save.WriteSettings(liczniki);
					txtSettings.setText("Ustawienia zapisane");
				} 

				catch (IOException noSettings) {
					txtSettings.setText("Brak wskazanego folderu");
					txtSettings.setVisible(true);
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

	private void czytajPola() {
		txtWynik.setText("");
		bLicznikCiepla = Double.parseDouble(txtCieplaInput.getText());
		liczniki.setLicznikCiepla(bLicznikCiepla);
		bLicznikZimna = Double.parseDouble(txtZimnaInput.getText());
		liczniki.setLicznikZimna(bLicznikZimna);
		bCenaGaz =  Double.parseDouble(txtGazInput.getText());
		liczniki.setCenaGaz(bCenaGaz);
		bCenaPrad = Double.parseDouble(txtPrdInput.getText());
		liczniki.setCenaPrad(bCenaPrad);
	}
	
	private static final String urlEncode(String str) {
	    try {
	        return URLEncoder.encode(str, "UTF-8").replace("+", "%20");
	    } catch (UnsupportedEncodingException e) {
	        throw new RuntimeException(e);
	    }
	}
}