package DVDLib;

import DVDLib.UI.DVDLibView;
import DVDLib.UI.UserIO;
import DVDLib.UI.UserIOConsoleImpl;
import DVDLib.controller.DVDLibController;
import DVDLib.dao.DVDLibDao;
import DVDLib.dao.DVDLibFileDaoImpl;

public class App {
    public static void main(String[] args) {
        UserIO myIO = new UserIOConsoleImpl();
        DVDLibView myView = new DVDLibView(myIO);
        DVDLibDao myDao = new DVDLibFileDaoImpl();
        DVDLibController controller = new DVDLibController(
                myDao, myView);
        controller.run();
         }
    }

