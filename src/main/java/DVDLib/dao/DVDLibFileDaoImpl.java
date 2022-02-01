package DVDLib.controller;

import DVDLib.UI.DVDLibView;
import DVDLib.UI.UserIO;
import DVDLib.UI.UserIOConsoleImpl;
import DVDLib.dao.DVDLibDao;
import DVDLib.dao.DVDLibException;
import DVDLib.dto.DVD;

import java.util.List;

public class DVDLibController {

    private final UserIO io = new UserIOConsoleImpl();
    private DVDLibView view;
    private DVDLibDao dao;

    public DVDLibController(DVDLibDao dao, DVDLibView view){
        this.dao = dao;
        this.view = view;
    }


    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
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
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DVDLibException e){
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }

    private void createDVD() throws DVDLibException {
        view.displayCreateDVDBanner();
        DVD newDvd = view.getNewDVDInfo();
        dao.addDVD(newDvd);
        view.displayCreateSuccessBanner();
    }

    private void listDVDs() throws DVDLibException {
        view.displayAllBanner();
        List<DVD> DVDList = dao.getAllDVDs();
        view.displayDVDList(DVDList);
    }

    private void viewDVD() throws DVDLibException {
        view.displayDisplayDVDBanner();
        int DVDId = view.getDVDIdChoice();
        DVD dvd = dao.getDVD(DVDId);
        view.displayDVD(dvd);
    }
    private void removeDVD() throws DVDLibException {
        view.displayRemoveDVDBanner();
        int DVDId = view.getDVDIdChoice();
        DVD removedDVD = dao.removeDVD(DVDId);
        view.displayRemoveResult(removedDVD);
    }

    private void editDVD() throws DVDLibException {
        view.displayEditDVDBanner();
        int DVDId = view.getDVDIdChoice();
        DVD editedDVD = dao.getDVD(DVDId);
        view.displayEditedDVD(editedDVD);
        view.displayDVD(editedDVD);
    }

    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }

    private void exitMessage(){
        view.displayExitBanner();
    }
}
