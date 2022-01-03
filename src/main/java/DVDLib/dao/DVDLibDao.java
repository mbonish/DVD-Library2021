package DVDLib.dao;

import DVDLib.dto.DVD;

import java.util.List;

public interface DVDLibDao {

    DVD addDVD(DVD dvd);

    List<DVD> getAllDVDs();

    DVD getDVD(int DVDId);

    DVD editDVD(int DVDId);

    DVD removeDVD(int DVDId);


}
