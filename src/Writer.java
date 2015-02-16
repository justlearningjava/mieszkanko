import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Writer {
	
	
	public void WriteSettings(Liczniki zapisz) {
		
		try
		{
			
		String filename = "C:/Users/kkaranko/Desktop/temp/mieszkanko/settings.txt";
		String save = "" + zapisz.getCenaCiepla() + "\t" + zapisz.getCenaZimna() + "\t" + zapisz.getInternet();
		FileWriter fw = new FileWriter(filename, false);
		fw.write(save);
		fw.close();
	
	  }
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}

	}
	
	public void WriteMeters(Liczniki wez) {
		
		try
		{
			
		String filename = "C:/Users/kkaranko/Desktop/temp/mieszkanko/meters.txt";
		File plik = new File(filename);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date data = new Date();
		String sData = dateFormat.format(data).toString();
				
		
				
		String save = sData  + "\t" + wez.getLicznikCiepla() + "\t" + wez.getLicznikZimna() + "\t" + wez.getCenaPrad() + "\t" + wez.getCenaGaz() + "\t" + System.lineSeparator() ;
		FileWriter fw = new FileWriter(plik, true);
		fw.write(save);
		fw.close();
	
	  }
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}

	}
	}


