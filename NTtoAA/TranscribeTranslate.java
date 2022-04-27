public class TranscribeTranslate {
	String sequence = "";
	int length = 0;
//PUT POSITION (in sequence itself) THAT PROTEIN STARTS 
	int start1 = 28311; 
	String trans = "";
	String protein = "";
	
	public TranscribeTranslate(String sequence, int length) {
		this.sequence = sequence;
		this.length = length-start1;
	}

	public void newRun() {
		this.trans = "";
		this.protein = "";
	}

	public String transcribe() {
		trans = "";
		String sequenceClip = sequence.substring(start1);
		for(int i = 0; i < length; ++i) {
			char current = sequenceClip.charAt(i);
			switch(current) {
			case 'A': trans = trans.concat("A"); break;
			case 'T': trans = trans.concat("U"); break;
			case 'G': trans = trans.concat("G"); break;
			case 'C': trans = trans.concat("C"); break;
			case 'N': trans = trans.concat("N"); break;
			case '-': trans = trans.concat("-"); break;
			}
		}
//		System.out.printf("transcription is " + trans + "\n");
		return trans;
	}
	
	public String translation() {
		boolean isStop = false;
		protein = "";
		for(int i = 0; i < length; i=i+3) {
			String mRNA = trans.substring(i);
			int rnaLen = mRNA.length();
			if(rnaLen - i < 3) {
				//System.err.println("Loop terminated - end of sequence");
				break;
			}
			if(isStop) {
				break;
			}
			
			String currentCoding = mRNA.substring(i,i+3);
			if (currentCoding.contains("-") || currentCoding.contains("-")) {
				protein.concat("-"); 
			}
			switch(currentCoding) {
			case "UUU": case "UUC": 
				protein = protein.concat("F"); break;
			case "UUA": case "UUG": case "CUU": case "CUC": case "CUA": case "CUG": case "CUN":
				protein = protein.concat("L"); break;
			case "AUU": case "AUC": case "AUA":
				protein = protein.concat("I"); break;
			case "AUG":
				protein = protein.concat("M"); break;
			case "GUU": case "GUC": case "GUA": case "GUG": case "GUN":
				protein = protein.concat("V"); break;
			case "UCU": case "UCC": case "UCA": case "UCG": case "AGU": case "AGC": case "UCN":
				protein = protein.concat("S"); break;
			case "CCU": case "CCC": case "CCA": case "CCG": case "CCN": 
				protein = protein.concat("P"); break;
			case "ACU": case "ACC": case "ACA": case "ACG": case "ACN":
				protein = protein.concat("T"); break;
			case "GCU": case "GCC": case "GCA": case "GCG": case "GCN":
				protein = protein.concat("A"); break;
			case "UAU": case "UAC":
				protein = protein.concat("V"); break;
			case "UAA": case "UAG": case "UGA":
				protein = protein.concat("*"); isStop = true; break;
			case "CAU": case "CAC":
				protein = protein.concat("H"); break;
			case "CAA": case "CAG":
				protein = protein.concat("Q"); break;
			case "AAU": case "AAC":
				protein = protein.concat("N"); break;
			case "AAA": case "AAG":
				protein = protein.concat("K"); break;
			case "GAU": case "GAC":
				protein = protein.concat("D"); break;
			case "GAA": case "GAG":
				protein = protein.concat("E"); break;
			case "UGU": case "UGC":
				protein = protein.concat("C"); break;
			case "UGG":
				protein = protein.concat("W"); break;
			case "CGU": case "CGC": case "CGA": case "CGG": case "AGA": case "AGG": case "CGN":
				protein = protein.concat("R"); break;
			case "GGU": case "GGC": case "GGA": case "GGG": case "GGN":
				protein = protein.concat("G"); break;
			default: protein = protein.concat("X"); break;
			}
		}
//		System.out.printf("final protein is " + protein + "\n");
		return protein;
	}
}
