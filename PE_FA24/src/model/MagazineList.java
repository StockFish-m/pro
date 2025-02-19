package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public final class MagazineList {

    private ArrayList<Magazine> magazine;

    public MagazineList() {
        this.magazine = new ArrayList<>();
        loadData("magazines_input.txt");
    }

    public void printMagazines() {
        for (Magazine m : magazine) {
            System.out.println(m);
        }
    }

    public void loadData(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] b = line.split("\\|");
                if (b.length == 5) {

                    try {
                        String title = b[0].trim();
                        String publisher = b[1].trim();
                        String publishDate = b[2].trim();
                        int issueNumber = Integer.parseInt(b[3].trim());
                        int pages = Integer.parseInt(b[4].trim());

                        magazine.add(new Magazine(title, publisher, publishDate, issueNumber, pages));
                    } catch (ParseException ex) {
                        System.err.println("Error loading data: " + ex.getMessage());
                    }
                }
            }
        } catch (IOException ex) {
            System.err.println("Error reading file: " + ex.getMessage());
        }
    }

    public boolean addMagazine(String title, String publisher, String publishDate, int issueNumber, int pages) {

        try {
            magazine.add(new Magazine(title, publisher, publishDate, issueNumber, pages));
            return true;
        } catch (ParseException ex) {
            System.out.println("Error adding magazine: " + ex.getMessage());
            return false;
        }
    }

    public void sortMagazine(Comparator<Magazine> comparator) {
        magazine.sort(comparator);
    }

    public ArrayList<Magazine> searchMagazine(Predicate<Magazine> predicate) {
        ArrayList<Magazine> results = new ArrayList();
        for (Magazine b : magazine) {
            if (predicate.test(b)) {
                results.add(b);
            }
        }
        return results;
    }

}
