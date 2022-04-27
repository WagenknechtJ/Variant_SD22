Hello!

Here lies the code of Team Variant's Senior Design Project in the analysis of SARS-CoV-2 Nucleocapsid Protein. Code in this file was created by me; data used was retreived from GISAID and NCBI databases but is not included due to copyright. 

Included is:
- NTtoAA: Java program to translate an alignment of cDNA sequence (in FASTA format) to amino acid sequences (still in FASTA format) from a given point (both translating and trimming, and inserting each file separately into an assigned folder). Consists of:
  - DnaReader.java: main file, takes files in, gets sequences, passes to TranscribeTranslate function
  - TranscribeTranslate.java: function that 
  - Instructions.txt: describes how to use code (basically, edit code in 3 places to tell folder location of files to translated, files after translation, and at what position of the alignment to translate from)
- SequenceFilter: R program to filter through downloaded NCBI SARS-CoV-2 Dataset and return N-protein and S-protein nucleic acid sequences with no X's (unknowns), and, if wanted, return a random selection of n N-protein sequences, in FASTA format.
- VisualsAndNumbers: Python program (in a notebook) to translate full sequences (in FASTA format) to amino acids, then gather their frequencies from alignment and use that data to visualize variance and calculate WHAT ARE WE GONNA CALCULATE???? (*requires intermediary step of aligning FASTA file within Jalview as I could not figure out the alignment function of BioPython)
