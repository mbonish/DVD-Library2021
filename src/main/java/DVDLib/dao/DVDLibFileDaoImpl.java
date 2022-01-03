package DVDLib.dao;

import DVDLib.dto.DVD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DVDLibFileDaoImpl implements DVDLibDao {


    //Map that holds the values for the DVDs
    private final Map<Integer, DVD> DVDs = new HashMap<>();

    @Override
    public DVD addDVD(DVD dvd) {
       int DVDId=  getNextId();
        DVD prevDVD = DVDs.put(DVDId, dvd);
        return prevDVD;
    }

    @Override
    public List<DVD> getAllDVDs() {
        return new ArrayList<DVD>(DVDs.values());
    }

    @Override
    public DVD getDVD(int DVDId) {
        return DVDs.get(DVDId);
    }

    @Override
    public DVD editDVD(int DVDId) {
        return DVDs.get(DVDId);
    }

    @Override
    public DVD removeDVD(int DVDId) {
        DVD removedDVD = DVDs.remove(DVDId);
        return removedDVD;
    }

    private int getNextId() {
        int largestId = 0;
        //use the hash maps to get all the keys
        List<Integer> ids = new ArrayList<Integer>(DVDs.keySet());
        for(int currentId: ids)
            if(largestId < currentId) {
                largestId = currentId;
            }
        return largestId+1;
    }
}
