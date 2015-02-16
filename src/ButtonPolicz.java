import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;


public class ButtonPolicz {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JButton btnPolicz = new JButton("Policz");
		btnPolicz.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e)  {
			Liczniki wez = new Liczniki();
			Writer zapisz = new Writer();
			double bLicznikCiepla;
			double bLicznikZimna;
			double bCenaGaz;
			double bCenaPrad;
			
			
			try {
				lblWynik.setText("");
				bLicznikCiepla = Double.parseDouble(txtCieplaInput.getText());
				wez.setLicznikCiepla(bLicznikCiepla);
				bLicznikZimna = Double.parseDouble(txtZimnaInput.getText());
				wez.setLicznikZimna(bLicznikZimna);
				bCenaGaz =  Double.parseDouble(txtGazInput.getText());
				wez.setCenaGaz(bCenaGaz);
				bCenaPrad = Double.parseDouble(txtPrdInput.getText());
				wez.setCenaPrad(bCenaPrad);
				wez.laduj();
			
				try {
					lblWynik.setText("" + wez.getWynik());
					zapisz.WriteMeters(wez);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					lblWynik.setText("Błąd odczytu plików");
				}
				
				}
			
			catch(NumberFormatException n) {
				lblWynik.setText("Wpisz poprawne wartości");
			}
			
			
								
				
			}
		});
		
		btnPolicz.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPolicz.setBounds(10, 106, 223, 38);
	}

}
