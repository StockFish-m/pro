package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.function.Predicate;
import model.Magazine;
import model.MagazineList;
import view.Menu;
import view.Utils;

public class MagazineManagement extends Menu {

    private MagazineList ListMagazine;

    public MagazineManagement() throws ParseException {
        super("Magazines MANAGEMENT SYSTEM", new String[]{
            "Add new Magazine",
            "Display all magazines",
            "Display sorted magazines by number of pages ",
            "Search magazines",
            "Delete magazines",
            "Quit"
        });
        ListMagazine = new MagazineList();

    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1:
                addNewMagazine();
                break;
            case 2:
                DisplayMagazine();
                break;
            case 3:
                sortMagazine();
                DisplayMagazine();
                break;
            case 4:
                searchMagazine();
                break;
            case 5:

                break;
            case 6:
                System.out.println("Exiting the program.");
                System.exit(0);
                break;

        }
    }

    public void DisplayMagazine() {
        ListMagazine.printMagazines();
    }

    private void addNewMagazine() {
        try {

            String title = Utils.getValue("Enter title:");
            String publisher = Utils.getValue("Enter publisher:");
            String publishDate = Utils.formatDate(Utils.getDateValue("Enter publisher date  (dd/MM/yyyy):"));
            int issueNumber = (Utils.getIntValue("Enter  issueNumber:"));
            int pages = (Utils.getIntValue("Enter  pages:"));

            boolean added = ListMagazine.addMagazine(title, publisher, publishDate, issueNumber, pages);
            if (added) {
                System.out.println("Magazine added successfully.");
            } else {
                System.out.println("Failed to add magazine.");
            }
        } catch (Exception e) {
            System.out.println("Error adding bill: " + e.getMessage());
        }
    }

    public void sortMagazine() {
        ListMagazine.sortMagazine((b1, b2) -> Integer.compare(b1.getPages(), b2.getPages()));

    }

    public static void main(String[] args) throws ParseException {
        MagazineManagement management = new MagazineManagement();
        management.run();
    }

    private void searchMagazine() {
        String[] searchOptions = {"Search by title", "Search by publisher", "Search by issue number",};
        Menu searchMenu;
        searchMenu = new Menu("Search magazine", searchOptions) {
            @Override
            public void execute(int option) {
                Predicate<Magazine> predicate;
                switch (option) {
                    case 3:
                        int issueNumber = (Utils.getIntValue("Enter issue number:"));
                        searchResults(predicate = magazine -> magazine.getIssueNumber() == issueNumber);
                        break;
                    case 1:
                        String title = Utils.getValue("Enter title:");
                        searchResults(magazine -> magazine.getTitle().equalsIgnoreCase(title));
                        break;
                    case 2:
                        String publisher = Utils.getValue("Enter publisher:");
                        searchResults(magazine -> magazine.getPublisher().equalsIgnoreCase(publisher));
                        break;
                    default:
                        System.out.println("Invalid option.");
                        return;
                }

            }
        };
        searchMenu.run();

    }

    private void searchResults(Predicate<Magazine> predicate) {
        ArrayList<Magazine> results = ListMagazine.searchMagazine(predicate);
        if (!results.isEmpty()) {
            System.out.println("Search list:");
            for (Magazine b : results) {
                System.out.println(b);
            }
        } else {
            System.out.println("No Found!");
        }
    }

}
