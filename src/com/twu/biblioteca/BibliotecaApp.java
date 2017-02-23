package com.twu.biblioteca;

public class BibliotecaApp {

    private static Library library = new Library();
    private static UserInput userInput = new UserInput();

    public BibliotecaApp(Library library, UserInput userInput) {
        this.library = library;
        this.userInput = userInput;
    }

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp(new Library(), new UserInput());
        bibliotecaApp.welcomeMessage();
        bibliotecaApp.topMenu();
    }

    public void welcomeMessage() {
        System.out.println("==========================================================");
        System.out.println("==================Welcome to Biblioteca!==================");
        System.out.println("===============The Bangalore Public Library===============");
        System.out.println("==========================================================");
    }

    public void topMenu() {
        displayMenuOptions();
        getUserSelection();
    }

    public void displayMenuOptions() {
        System.out.println("\n\nMAIN MENU");
        System.out.println("---------");
        System.out.println("Please select from the following options (enter number):");
        System.out.println("1. List Books");
        System.out.println("2. List Movies");
        System.out.println("3. Checkout Book");
        System.out.println("4. Checkout Movie");
        System.out.println("5. Return Book");
        System.out.println("6. Return Movie");
        System.out.println("7. Quit");
    }

    private void getUserSelection() {
        switch (userInput.getStringInput()) {
            case "1":
                listBooks();
                break;
            case "2":
                listMovies();
                break;
            case "3":
                checkoutBook();
                break;
            case "4":
                checkoutMovie();
                break;
            case "5":
                returnBook();
                break;
            case "6":
                returnMovie();
                break;
            case "7":
                quit();
                System.exit(0);
            default:
                invalidSelection();;
                getUserSelection();
                break;
        }
        topMenu();
    }

    public void listBooks() {

        System.out.println("\n\nCheck out the list of books currently at Biblioteca:");

        printBookHeaders();

        for (Book book : library.getBooks()) {
            if (book.isAvailable()) {
                printBookInfo(book.getTitle(), book.getAuthor(), book.getYear());
            }
        }
    }

    public void listMovies() {
        System.out.println("\n\nThese are the movies currently available at Biblioteca:");

        printMovieHeaders();

        for (Movie movie : library.getMovies()) {
            if (movie.isAvailable()) {
                printMovieInfo(movie.getName(), movie.getDirector(), movie.getYear(), movie.getRating());
            }
        }
    }

    public void invalidSelection() {
        System.out.println("\n\n\nInvalid selection. Please select a valid option!");
    }

    public void checkoutBook() {
        String bookTitle = promptUserForInput("\nPlease enter the TITLE of the book you wish to checkout. " +
                                                                                 "(For main menu, enter: main menu).");
        if (library.containsBook(bookTitle)) {
            library.removeBook(bookTitle);
            confirmSuccessfulCheckout(bookTitle);
        } else if (bookTitle.contains("main menu")) {
            topMenu();
        } else {
            invalidSelection();
            checkoutBook();
        }
    }

    public void checkoutMovie() {
        String movieName = promptUserForInput("\nPlease enter the NAME of the movie you wish to checkout. " +
                                                                                "(For main menu, enter: main menu).");
        if (library.containsMovie(movieName)) {
            library.removeMovie(movieName);
            confirmSuccessfulCheckout(movieName);
        } else if (movieName.contains("main menu")) {
            topMenu();
        } else {
            invalidSelection();
            checkoutMovie();
        }
    }

    public void returnBook() {
        String bookTitle = promptUserForInput("\nPlease enter the title of the book you wish to return. " +
                                                                            "For main menu, enter: main menu).");
        if (library.bookWaitingToBeReturned(bookTitle)) {
            library.returnBook(bookTitle);
            System.out.println("\nThank you for returning '" + bookTitle + "'.");
        } else if (library.containsBook(bookTitle)) {
            System.out.println("\nThis book is already in the Biblioteca. You must have the wrong library!");
        } else if (bookTitle.contains("main menu")) {
            topMenu();
        } else {
            System.out.println("\nThat is not a valid book to return. Please try again.");
            returnBook();
        }
    }

    public void returnMovie() {
        String movieName = promptUserForInput("\nPlease enter the name of the book you wish to return. " +
                "For main menu, enter: main menu).");

        if (library.movieWaitingToBeReturned(movieName)) {
            library.returnMovie(movieName);
            System.out.println("\nThank you for returning '" + movieName + "'.");
        } else if (library.containsMovie(movieName)) {
            System.out.println("\nThis movie is already in the Biblioteca. You must have the wrong library!");
        } else if (movieName.contains("main menu")) {
            topMenu();
        } else {
            System.out.println("That is not a valid movie to return! Please try again.");
            returnMovie();
        }

    }

    private String promptUserForInput(String prompt) {
        System.out.println(prompt);
        return userInput.getStringInput();
    }

    private void confirmSuccessfulCheckout(String bookTitle) {
        System.out.println("\n\nYou have successfully checked out '" + bookTitle + "'. Thank you. Enjoy your book!");
    }

    private void printBookInfo(String title, String author, String year) {
        System.out.printf("%-20s %-20s %-15s %n", title, author, year);
    }

    private void printMovieInfo(String name, String director, String year, String rating) {
        System.out.printf("%-20s %-20s %s %-15s %n", name, director, year, rating);
    }

    private void printBookHeaders() {
        printBookInfo("TITLE", "AUTHOR", "YEAR");
        printBookInfo("-----", "------", "----");
    }

    private void printMovieHeaders() {
        printMovieInfo("NAME", "DIRECTOR", "YEAR", "RATING");
        printMovieInfo("----", "--------", "----", "------");
    }

    public void quit() {
        System.out.println("Thanks for using Biblioteca. See you next time.");
    }

}
