library(readr)
library(stringr)
library(seqinr)

#enter location of file below as the working directory, and the file name below that
#dataset used was the SARS-CoV-2 proteins datasets from https://www.ncbi.nlm.nih.gov/datasets/
setwd("C:/Users/koprekj/OneDrive - Milwaukee School of Engineering/Senior Year!/Senior Design (Shared)/Code/ncbi_dataset/data")
firstFile <- file("AllSequences.fasta", "r")

#initialize empty dataframe to put information from file
InitialSequences <- data.frame(Protein = character(),
                               Origin = character(),
                               Sequence = character(),
                               Location = character(),
                               ID = character(), stringsAsFactors=FALSE)

#iterate through each line of the file (consisting of one line of information and one line of the full sequence)
grabNextLine <- FALSE
rowNum <- 1
while (TRUE) {
  line <- readLines(theFile, n=1)
  if (length(line) == 0) {
    break
  }
  #simple progress update, knowing how many sequences were in the file included and that there are 2 lines per sequence
  if (rowNum%%1000 == 0){
    print(rowNum)
  }
  #if the previous (information) line was what we're looking for...
  if (grabNextLine){
    #see if there are too many unknowns, and if not
    if(!(grepl("XXXX", line, fixed = TRUE))){
      #fill in sequence in dataframe, and increment rownumber (if too many unknowns, we will refill the same row with the next potential sequence)
      InitialSequences[rowNum, 3] <- line
      rowNum <- rowNum+1
    }
  }
  #if the sequence is E, M, N, or S, add information (should be the protein, its location of origin, position in genome, and ID) to dataframe
  if (grepl("envelope", line, fixed = TRUE) | 
      grepl("membrane", line, fixed = TRUE) | 
      grepl("nucleocapsid", line, fixed = TRUE) | 
      grepl("surface", line, fixed = TRUE)){
    #toggle that the next line (the sequence belonging to this data) should be investigated further and potentially added to dataframe
    grabNextLine <- TRUE
    InitialSequences[rowNum, 1] <- str_match(line, "\\s(.+?)\\[")[, 2]
    InitialSequences[rowNum, 2] <- str_match(line, "\\/(.+)\\]")[, 2]
    InitialSequences[rowNum, 4] <- str_match(line, ":(.+?)\\s")[, 2]
    InitialSequences[rowNum, 5] <- str_match(line, ">(.+?):")[, 2]
  }
  else {
    grabNextLine <- FALSE
  }
  #print("One line done!")
}
print("Done!")

#writes table of gathered data as tab-delimited text file
write.table(InitialSequences, file = "MENSProteins.txt", sep = "\t",
            row.names = FALSE, col.names = TRUE, quote = FALSE)

#after writing table, we changed scope and only wanted sequences with less than 1% unknowns
Narrowed <- InitialSequences[!(str_count(InitialSequences$Sequence, "N") > (.01*str_length(InitialSequences$Sequence))),]

#and onlyN proteins
Nproteins <- Narrowed[Narrowed$Protein == "nucleocapsid phosphoprotein ", ]

#the data was still too big though, so we took a random sample of 1,999 sequences
set.seed(123)
Nproteins <- data.frame(Nproteins)
Nproteins <- Nproteins[sample(nrow(Nproteins), 1999),]

#and printed them to a FASTA file
NproteinsFasta <- write.fasta(sequences=as.list(Nproteins$Sequence), names=paste(Nproteins$ID, Nproteins$Protein, "/", Nproteins$Origin, "/", sep = ""), file.out="NProteinsToAlign2.fasta")
