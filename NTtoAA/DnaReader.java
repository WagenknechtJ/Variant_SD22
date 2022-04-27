import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;

public class DnaReader {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();

//PUT THE FOLDER YOU WANT IT TO GRAB ALL FILES FROM HERE - make sure folder only contains files you want translated
		File dir = new File("C:/Users/koprekj/OneDrive - Milwaukee School of Engineering/Senior Year!/Senior Design (Shared)/Data/2.AlignedDNASequences");

		for (File file : dir.listFiles()) {
			String header = "";
			String finalSeq = "";
			String line = "";
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				while ((line = br.readLine()) != null) {
					if (line.charAt(0) != '>') {
						sb.append(line);    } 
					else {
						header = line;
					} }
				br.close();
			} 
			catch (Exception e) {
				System.out.println("Error: provide a fasta file!");
			}
			
			String sequence = sb.toString().toUpperCase();
			sb.delete(0, sb.length());
			int length = sequence.length();
			TranscribeTranslate run = new TranscribeTranslate(sequence, length);
			run.transcribe();
			try {
//PUT LOCATION YOU WANT TO SAVE NEW TRANSLATED FILES TO HERE
				String newLocation = "C:/Users/koprekj/OneDrive - Milwaukee School of Engineering/Senior Year!/Senior Design (Shared)/Data/3.TranslatedProteinSequences/P.".concat(file.getName());
				FileWriter myWriter = new FileWriter(newLocation);
				finalSeq = run.translation();
				myWriter.write("> Protein|"+header.substring(1)+"\n");
				for (int i=0; i<(finalSeq.length()+1); i+=60) {
					if (finalSeq.substring(i).length()<60) {
						myWriter.write(finalSeq.substring(i)+"\n");
					}
					else {
						myWriter.write(finalSeq.substring(i, i+60)+"\n");
					}
				}
			    myWriter.close();
			}
			catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
			System.out.printf(file.getName() + " translated to proteins\n");
			sequence = "";
		}
		System.out.println("done translating all files!");
	}
}