import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class LoadFiles  {
	//double[] meters = new double[3];
	int lineNumber = 0;
	private double pZimna;
	private double pCiepla;
	private Liczniki liczniki;
	
	
	public LoadFiles(Liczniki liczniki) throws IOException {
		
		this.liczniki = liczniki;
		
			}
	
	public void LadujUstawienia() throws IOException{
	
	
		liczniki.setCenaCiepla(GetSettings(0)); 
		liczniki.setCenaZimna(GetSettings(1)); 
		liczniki.setInternet(GetSettings(2)); 
		
		}
	
	public void LadujOdczyty() throws IOException {
		
		Podziel(CzytajPlik());
		liczniki.setpZimna(pZimna);
		liczniki.setpCiepla(pCiepla);
	}
	
	private String CzytajPlik() throws IOException  { 
	    
				
				ArrayList<String> czytaj = new ArrayList<String>();
				String przedostatnia;
		        try {
		        	String fileName = "C:/Users/kkaranko/Desktop/temp/mieszkanko/meters.txt";
					
		        	File myFile = new File(fileName);
		        	FileReader fileReader = new FileReader(myFile);
		        	
		        	BufferedReader reader = new BufferedReader(fileReader);
		        	
		        	String line = null;
		        	
		        	while ((line = reader.readLine()) != null) {
		        		
		        		czytaj.add(line);
		        		lineNumber++;
		        	}
		        			        	 
		        reader.close();
		        
		        przedostatnia = czytaj.get(czytaj.size() -1);
		        
		        return przedostatnia;
		        	
		       } 
		        
		       catch (IOException e) {
		        	
		    	   IOException e1 = new IOException();
				throw e1;
		    	   
		        }
	}
		       
	
	
	
	
	private void Podziel(String lineToParse) {
		String[] result = lineToParse.split("\t");
		pCiepla = Double.parseDouble(result[1]);
		pZimna = Double.parseDouble(result[2]);
			
	}
	
	private double GetSettings(int a) throws IOException { 
	    ArrayList<Double> numbers = new ArrayList<>();		
		try {
			for (String line : Files.readAllLines(Paths.get("C:/Users/kkaranko/Desktop/temp/mieszkanko/settings.txt"))) {
			    for (String part : line.split("\t")) {
			        Double i = Double.valueOf(part);
			        numbers.add(i);
			    }
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
		return numbers.get(a);
	}
	
}