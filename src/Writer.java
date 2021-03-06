import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JPanel;



public class Writer {
	
	private String path = System.getProperty("user.home");
	
	public String getPath() {
		return path;
	}


	public void WskazFolder(JPanel panel) {
		
		JFileChooser chooser = new JFileChooser();	
		chooser.setCurrentDirectory(new java.io.File(System.getProperty("user.home")));
		chooser.setDialogTitle("Wska� �cie�k� zapisu");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
		int returnVal = chooser.showOpenDialog(panel);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        path = chooser.getSelectedFile().getAbsolutePath();
        System.out.println(path);
        
        }
		//return pathUst;
	}
	
	
	public void WriteSettings(Liczniki zapisz) throws IOException {
		
		try
		{
		
		//path = "C:/Users/kkaranko/Desktop/temp/mieszkanko/settings.txt";
		String save = "" + zapisz.getCenaCiepla() + "\t" + zapisz.getCenaZimna() + "\t" + zapisz.getInternet();
		FileWriter fw = new FileWriter(path + "/settings.txt", false);
		fw.write(save);
		fw.close();
	
	  }
		catch(IOException ioe)
		{
		   throw ioe;
			//System.err.println("IOException: " + ioe.getMessage());
		}

	}
	
	public void WriteMeters(Liczniki wez) throws IOException {
		
		try
		{
			
		File plik = new File(path + "/meters.txt" );
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
		    throw ioe;
			//System.err.println("IOException: " + ioe.getMessage());
		}

	}
	}


