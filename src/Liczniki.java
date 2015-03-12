import java.io.IOException;


public class Liczniki {

	private double licznikZimna;
	private double licznikCiepla;
	private double cenaGaz;
	private double cenaPrad;
	private double wynik;
	private double cenaZimna;
	private double cenaCiepla;
	private double internet;
	private double pCiepla;
	private double pZimna;
	private TestWindow window;

	Liczniki(TestWindow window) {
		this.window = window;
	}

	public void setpCiepla(double pCiepla) {
		this.pCiepla = pCiepla;
	}
	public void setpZimna(double pZimna) {
		this.pZimna = pZimna;
	}


	public double getCenaZimna() {
		return cenaZimna;
	}
	public void setCenaZimna(double cenaZimna) {
		this.cenaZimna = cenaZimna;
	}
	public double getCenaCiepla() {
		return cenaCiepla;
	}
	public void setCenaCiepla(double cenaCiepla) {
		this.cenaCiepla = cenaCiepla;
	}
	public double getInternet() {
		return internet;
	}
	public void setInternet(double internet) {
		this.internet = internet;
	}
	public double getCenaGaz() {
		return cenaGaz;
	}
	public void setCenaGaz(double cenaGaz) {
		this.cenaGaz = cenaGaz;
	}
	public double getCenaPrad() {
		return cenaPrad;
	}
	public void setCenaPrad(double cenaPrad) {
		this.cenaPrad = cenaPrad;
	}
	public double getLicznikZimna() {

		return licznikZimna;
	}
	public void setLicznikZimna(double licznikZimna) {

		this.licznikZimna = licznikZimna;
	}
	public double getLicznikCiepla() {
		return licznikCiepla;
	}
	public void setLicznikCiepla(double licznikCiepla) {
		this.licznikCiepla = licznikCiepla;
	}

	public double getWynik() throws IOException {
		if (wynik >= 0) {
			return wynik;
		}
		else throw new IOException();
	}

	public void ladujLiczniki() {

		try {
			LoadFiles czytajLiczniki = new LoadFiles(this);
			czytajLiczniki.LadujOdczyty();

		}

		catch (IOException e) {

			window.txtWynik.setText("Brak pliku z odczytami");

		}
	}


	public void ladujUstawienia() throws IOException {

		try {
			LoadFiles czytajUstawienia = new LoadFiles(this);
			czytajUstawienia.LadujUstawienia();
		} 

		catch (IOException e)	{
			throw e;
		}




	}

	public void licz() throws Exception {

		try{
			if ((pCiepla < licznikCiepla) || (pZimna < licznikZimna)) { 
				wynik = (cenaGaz + cenaPrad + (licznikCiepla - pCiepla)*cenaCiepla   + (licznikZimna- pZimna)*cenaZimna + internet ) / 3;
				//window.lblWynik.setText("" + ( );
			
			}
			else {
				throw new Exception("Coœ siê musi staæ"); 
			}
		}
		catch(Exception wieksze) {
			throw wieksze;			
		}
	}

}





