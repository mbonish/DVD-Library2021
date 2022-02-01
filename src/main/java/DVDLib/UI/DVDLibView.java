package DVDLib.UI;

import DVDLib.dto.DVD;

import java.util.List;

public class DVDLibView {

    private UserIO io;
    public DVDLibView(UserIO io){
        this.io = io;

    }
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("-1 List DVDs");
        io.print("-2 Create new DVD");
        io.print("-3 View a DVD");
        io.print("-4 Edit DVD");
        io.print("-5 Remove DVD");
        io.print("-6 EXIT");

        return io.readInt("Please select from the above choices.", 1, 6);

    }

    public DVD getNewDVDInfo() {

        String title = io.readString("Please enter the title.");
        String releaseDate = io.readString("Please enter the release date.");
        String MPAARating = io.readString("Please enter the MPAARating");
        String director = io.readString("Enter the director.");
        String studio = io.readString("Enter the Studio");
        String userRating = io.readString("What is your rating of the movie.");

        int DVDId = 1;

        DVD newDVD = new DVD(DVDId);
        newDVD.setTitle(title);
        newDVD.setReleaseDate(releaseDate);
        newDVD.setMPAARating(MPAARating);
        newDVD.setDirector(director);
        newDVD.setStudio(studio);
        newDVD.setUserRating(userRating);
        return newDVD;
    }

    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString("DVD was created successfully. \n Hit enter to continue.");
    }

    public void displayDVDList(List<DVD> DVDList) {
        for (DVD currentDVD : DVDList) {
            String DVDinfo = String.format("#%s: %s %s %s %s %s %s",
                    currentDVD.getDVDId(),
                    currentDVD.getTitle(),
                    currentDVD.getReleaseDate(),
                    currentDVD.getMPAARating(),
                    currentDVD.getDirector(),
                    currentDVD.getStudio(),
                    currentDVD.getUserRating());
            io.print(DVDinfo);
        }
        io.readString("Please hit enter to continue,");
    }

    public void displayAllBanner() {
        io.print("=== Display all DVDs ===");
    }

    public void displayDisplayDVDBanner() {
        io.print("=== Display DVD ===");
    }

    public int getDVDIdChoice() {
        return io.readInt("Please enter the DVD id.", 1, 1000);
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print("Id: " + dvd.getDVDId());
            io.print("Title: " + dvd.getTitle());
            io.print("Release date: " + dvd.getReleaseDate());
            io.print("MMPARating: " + dvd.getMPAARating());
            io.print("Director: " + dvd.getDirector());
            io.print("Studio: " + dvd.getStudio());
            io.print("User Rating: " + dvd.getUserRating());
        } else {
            io.print("No such DVD in library");
        }
        io.readString("Hit enter to continue.");
    }

    public void displayRemoveDVDBanner() {
        io.print("===  Remove DVD ===");
    }

    public void displayRemoveResult(DVD dvdRecord) {
        if (dvdRecord != null) {
            io.print("DVD was successfully removed.");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Hit enter to continue.");
    }

    public void displayEditDVDBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displayEditedDVD(DVD dvd) {
        int choice = 1;
        if (dvd != null) {
            io.print("Id: " + dvd.getDVDId());
            io.print("Title: " + dvd.getTitle());
            io.print("Release date: " + dvd.getReleaseDate());
            io.print("MMPARating: " + dvd.getMPAARating());
            io.print("Director: " + dvd.getDirector());
            io.print("Studio: " + dvd.getStudio());
            io.print("User Rating: " + dvd.getUserRating());
            choice = io.readInt("Is this the DVD you would " +
                    "like to edit? (1 = yes) (2 = no)", 1, 2);
        } else {
            io.print("No such DVD in library");
            io.readString("Hit enter to continue.");
        }

        if (choice == 1) {
            io.print("Id:" + dvd.getDVDId() + " ===This value can NOT be changed=== \n");

            //title
            io.print("Title: " + dvd.getTitle());
            String newTitle = io.readString(" Enter new title or hit enter to continue.");
            if (newTitle != null) {
                dvd.setTitle(newTitle);
            } else io.print(dvd.getTitle() + "has not been changed.");

            //release date
            io.print("Release date: " + dvd.getReleaseDate());
            String newReleaseDate = io.readString("Enter " +
                    "new release date or hit enter to continue.");
            if (newReleaseDate != null) {
                dvd.setReleaseDate(newReleaseDate);
            } else io.print(dvd.getReleaseDate() + "has not been changed.");
        }


        io.print("MMPARating: " + dvd.getMPAARating());
        String newMMPARAting = io.readString("Enter new MMPA rating or hit enter " +
                "to continue");
        if (newMMPARAting != null) {
            dvd.getMPAARating();
        } else io.print(dvd.getMPAARating() + "has not been changed.");

        //Director
        io.print("Director: " + dvd.getDirector());
        String newDirector = io.readString("Enter new director or " +
                " hit enter to continue.");
        if (newDirector != null) {
            dvd.setDirector(newDirector);
        } else io.print(dvd.getDirector() + "has not been changed.");

        //Studio
        io.print("Studio: " + dvd.getStudio());
        String newStudio = io.readString("Enter new studio or " +
                "hit enter to continue.");
        if (newStudio != null) {
            dvd.setStudio(newStudio);
        } else io.print(dvd.getStudio() + " has not been changed");

        //User Rating
        io.print("User Rating: " + dvd.getUserRating());
        String newUserRating = io.readString(" Enter new user rating or " +
                "hit enter to continue.");
        if (newUserRating != null) {
            dvd.setUserRating(newUserRating);
        } else io.print(dvd.getUserRating() + " has not been changed.");

        io.print("Hit enter to continue");
    }

    public void displayExitBanner(){
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner(){
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
