package DVDLib.dao;

import DVDLib.dto.DVD;

import java.io.*;
import java.util.*;

public class DVDLibFileDaoImpl implements DVDLibDao {

    public static final String LIB_FILE = "lib.txt";
    public static final String DELIMITER = "::";

    //Map that holds the values for the DVDs
    private final Map<Integer, DVD> DVDs = new HashMap<>();

    @Override
    public DVD addDVD(DVD dvd) throws DVDLibException {
        loadLib();
        int DVDId = getNextId();
        DVD prevDVD = DVDs.put(DVDId, dvd);
        writeLib();
        return prevDVD;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibException {
        loadLib();
        return new ArrayList<DVD>(DVDs.values());
    }

    @Override
    public DVD getDVD(int DVDId) throws DVDLibException {
        loadLib();
        writeLib();
        return DVDs.get(DVDId);

    }

    @Override
    public DVD editDVD(int DVDId) throws DVDLibException {
        loadLib();
        return DVDs.get(DVDId);
    }

    @Override
    public DVD removeDVD(int DVDId) throws DVDLibException {
        loadLib();
        DVD removedDVD = DVDs.remove(DVDId);
        writeLib();
        return removedDVD;
    }

    //method used to create an id for the library
    private int getNextId() {
        int largestId = 0;
        //use the hash map to get all the keys
        List<Integer> ids = new ArrayList<Integer>(DVDs.keySet());
        for(int currentId: ids)
            if(largestId < currentId) {
                largestId = currentId;
            }
        return largestId+1;

    }

    private DVD unmarshallDVD(String DVDAsText) {
        //split the Values into tokens
        String[] DVDTokens = DVDAsText.split(DELIMITER);
        //get the id from the first token
        String DVDId = DVDTokens[0];
        Integer.parseInt(DVDId);

        //create a new DVD object- use the DVD id in the constructor
        DVD DVDFromFile = new DVD(Integer.parseInt(DVDId));

        DVDFromFile.setTitle(DVDTokens[1]);
        DVDFromFile.setReleaseDate(DVDTokens[2]);
        DVDFromFile.setMPAARating(DVDTokens[3]);
        DVDFromFile.setDirector(DVDTokens[4]);
        DVDFromFile.setStudio(DVDTokens[5]);
        DVDFromFile.setUserRating(DVDTokens[6]);

        return DVDFromFile;
    }

    private void loadLib() throws DVDLibException{
        Scanner scanner;

    try{
        //Create scanner for reading the file

        scanner = new Scanner(
                new BufferedReader(
                        new FileReader(LIB_FILE)));
        }catch (FileNotFoundException e){
            throw new DVDLibException(
                    "Could not load DVD data into memory.",e);
    }
    //hold the most recent line from the file
    String currentLine;

    //hold the most recent DVD unmarshalled
    DVD currentDVD;

    //go through LIB_FILE line by line, decoding each line into a
        // DVD object by calling the UnmarshallDVD method
        //process while we have more lines in the file.
    while (scanner.hasNextLine()){
        //get the next line in the file
        currentLine = scanner.nextLine();

        //unmarshall the line into a DVD
        currentDVD = unmarshallDVD(currentLine);

        //We are going to put the DVD ID as the map key for our student object.
        //Put currentDVD into the map using the dvd id as the key

        DVDs.put(currentDVD.getDVDId(),currentDVD);
         }
    }

    private String marshallDVD(DVD aDVD){
        String DVDAsText = aDVD.getDVDId() + DELIMITER;
        DVDAsText += aDVD.getTitle() + DELIMITER;
        DVDAsText += aDVD.getReleaseDate() + DELIMITER;
        DVDAsText += aDVD.getMPAARating() + DELIMITER;
        DVDAsText += aDVD.getDirector() + DELIMITER;
        DVDAsText += aDVD.getStudio() +DELIMITER;
        DVDAsText += aDVD.getUserRating();

        return DVDAsText;
    }

    private void writeLib() throws DVDLibException{
        PrintWriter out;

        try {
            out = new PrintWriter( new FileWriter(LIB_FILE));
        }catch (IOException e){
            throw new DVDLibException("" +
                    "Could not save DVD data", e);
        }

        String DVDASText;

        List<DVD> DVDList = this.getAllDVDs();
        for( DVD currentDVD: DVDList){
            DVDASText = marshallDVD(currentDVD);
            out.print(DVDASText);
            out.flush();
        }
        out.close();
    }
}
