package DVDLib.controller;

import DVDLib.UI.DVDLibView;
import DVDLib.UI.UserIO;
import DVDLib.UI.UserIOConsoleImpl;
import DVDLib.dao.DVDLibDao;
import DVDLib.dao.DVDLibFileDaoImpl;
import DVDLib.dto.DVD;

import java.util.List;

public class DVDLibController {

    private final UserIO io = new UserIOConsoleImpl();
    private final DVDLibView view = new DVDLibView();
    private final DVDLibDao dao = new DVDLibFileDaoImpl();


    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while(keepGoing){
        menuSelection = getMenuSelection();

            switch(menuSelection){
                case 1:
                    listDVDs();
                    break;
                case 2:
                    createDVD();
                    break;
                case 3:
                    viewDVD();
                    break;
                case 4:
                    editDVD();
                    break;
                case 5:
                    removeDVD();
                    break;
                case 6:
                    keepGoing = false;
                    break;
            }
        }
        io.print("Good bye!! :)");
    }

    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }

    private void createDVD(){
        view.displayCreateDVDBanner();
        DVD newDvd = view.getNewDVDInfo();
        dao.addDVD(newDvd);
        view.displayCreateSuccessBanner();
    }

    private void listDVDs(){
        view.displayAllBanner();
        List<DVD> DVDList = dao.getAllDVDs();
        view.displayDVDList(DVDList);
    }

    private void viewDVD(){
        view.displayDisplayDVDBanner();
        int DVDId = view.getDVDIdChoice();
        DVD dvd = dao.getDVD(DVDId);
        view.displayDVD(dvd);
    }
    private void removeDVD(){
        view.displayRemoveDVDBanner();
        int DVDId = view.getDVDIdChoice();
        DVD removedDVD = dao.removeDVD(DVDId);
        view.displayRemoveResult(removedDVD);
    }

    private void editDVD(){
        view.displayEditDVDBanner();
        int DVDId = view.getDVDIdChoice();
        DVD editedDVD = dao.getDVD(DVDId);
        view.displayEditedDVD(editedDVD);
        view.displayDVD(editedDVD);
    }
}
