Java program to translate an alignment of cDNA sequence (in FASTA format) to amino acid sequences (still in FASTA format) from a given point (both translating and trimming, and inserting each file separately into an assigned folder). 

Consists of:
- DnaReader.java: main file, takes files in, gets sequences, passes to TranscribeTranslate function
- TranscribeTranslate.java: function that transcribes and then translates the sequences, in this case from DNA to RNA then RNA to amino acids using the standard codon chart

To use:

- first HAVE ALL SEQUENCES ALIGNED and each in its own fasta file
- then find where protein you want to translate starts on all sequences
   - enter that value into TranscribeTranslate.java file (location labeled with a note)
   - enter the folder where aligned sequences are saved, and the folder where translated sequences will be saved to, in DnaReader.java file (again, locations labeled with a note)
- run
