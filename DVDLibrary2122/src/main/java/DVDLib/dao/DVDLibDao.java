package DVDLib.dao;

import DVDLib.dto.DVD;

import java.util.List;

public interface DVDLibDao {

    DVD addDVD(DVD dvd) throws DVDLibException;

    List<DVD> getAllDVDs() throws DVDLibException;

    DVD getDVD(int DVDId) throws DVDLibException;

    DVD editDVD(int DVDId) throws DVDLibException;

    DVD removeDVD(int DVDId) throws DVDLibException;


}
