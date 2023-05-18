package com.lesnikov.utils;

import com.lesnikov.model.*;

import java.time.ZonedDateTime;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputReader {

    private InputReader() {}

    public static String receiveName() {
        for ( ; ; ) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a name: ");
                String name = scanner.nextLine().trim();
                if (name.equals("")) {
                    System.out.println("This value cannot be empty. Try again");
                    continue;
                }
                return name;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be string. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public static Long receiveX() {
        for ( ; ; ) {
            try {
                System.out.print("Enter X coordinate: ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextLong();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a long-type number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    /**
     * Method for receiving y-coordinate of element
     * @return Float y
     */
    public static double receiveY() {
        for ( ; ; ) {
            try {
                System.out.print("Enter Y coordinate: ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextDouble();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a double-type number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public static Coordinates receiveCoordinates() {
        return new Coordinates(receiveX(), receiveY());
    }

    public static ZonedDateTime receiveCreationDate() {
        return ZonedDateTime.now();
    }

    public static Integer receiveOscarsCount() {
        for ( ; ; ) {
            try {
                System.out.print("Enter oscars count. Value must be greater than 0: ");
                Scanner scanner = new Scanner(System.in);
                int height = scanner.nextInt();
                if (height <= 0) {
                    System.out.println("This value must be greater than 0. Try again.");
                    continue;
                }
                return height;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a double-type number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public static double receiveTotalBoxOffice() {
        for ( ; ; ) {
            try {
                System.out.print("Enter total box office. Value should be positive: ");
                Scanner scanner = new Scanner(System.in);
                double totalBoxOffice = scanner.nextDouble();
                if (totalBoxOffice <= 0) {
                    System.out.println("Value should be positive. Try again.");
                    continue;
                }
                return totalBoxOffice;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a double-type number. Try again. ");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public static MovieGenre receiveGenre() {
        for ( ; ; ) {
            try {
                System.out.println("Choose variant of eye color. Enter color or the number corresponding " +
                        "to the desired option.");
                System.out.print("Variants: \n1. DRAMA; \n2. MUSICAL; \n3. THRILLER;\n4. HORROR;\n  5. SCIENCE_FICTION." +
                        "\nEnter your variant here: ");
                Scanner scanner = new Scanner(System.in);
                String genreChoose = scanner.nextLine().toUpperCase(Locale.ROOT);
                switch (genreChoose) {
                    case "1":
                    case "DRAMA":
                        return MovieGenre.DRAMA;
                    case "2":
                    case "MUSICAL":
                        return MovieGenre.MUSICAL;
                    case "3":
                    case "THRILLER":
                        return MovieGenre.THRILLER;
                    case "4":
                    case "HORROR":
                        return MovieGenre.HORROR;
                    case "5":
                    case "SCIENCE_FICTION":
                        return MovieGenre.SCIENCE_FICTION;
                    default:
                        System.out.println("You should to choose the genre from list or it's number. Try again.");
                        break;
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a string with genre or it's number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public static MpaaRating receiveMpaaRating() {
        for ( ; ; ) {
            try {
                System.out.println("Choose variant of eye color. Enter color or the number corresponding " +
                        "to the desired option.");
                System.out.print("Variants: \n1. G; \n2. PG; \n3. R;\n4. NC_17." +
                        "\nEnter your variant here: ");
                Scanner scanner = new Scanner(System.in);
                String ratingChoose = scanner.nextLine().toUpperCase(Locale.ROOT);
                switch (ratingChoose) {
                    case "1":
                    case "G":
                        return MpaaRating.G;
                    case "2":
                    case "PG":
                        return MpaaRating.PG;
                    case "3":
                    case "R":
                        return MpaaRating.R;
                    case "4":
                    case "NC_17":
                        return MpaaRating.NC_17;
                    default:
                        System.out.println("You should to choose the rating from list or it's number. Try again.");
                        break;
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a string with genre name or it's number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public static Float receiveXLocation() {
        for ( ; ; ) {
            try {
                System.out.print("Enter X coordinate of location: ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextFloat();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a float-type number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public static Double receiveYLocation() {
        for ( ; ; ) {
            try {
                System.out.print("Enter Y coordinate of location: ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextDouble();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a double-type number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public static long receiveZLocation() {
        for ( ; ; ) {
            try {
                System.out.print("Enter Z coordinate of location: ");
                Scanner scanner = new Scanner(System.in);
                return scanner.nextLong();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a long-type number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public static String receiveLocationName() {
        for ( ; ; ) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a name of location: ");
                return scanner.nextLine().trim();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be string. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public static Location receiveLocation() {
        return new Location(receiveXLocation(), receiveYLocation(), receiveZLocation(), receiveLocationName());
    }

    public static String receivePersonName() {
        for ( ; ; ) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a name of person: ");
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("String cannot be empty. Try again.");
                    continue;
                }
                return name;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be string. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public static Long receiveHeightOfPerson() {
        for ( ; ; ) {
            try {
                System.out.print("Enter height of person: ");
                Scanner scanner = new Scanner(System.in);
                long height = scanner.nextLong();
                if (height <= 0) {
                    System.out.println("Value must be positive. Try again.");
                    continue;
                }
                return height;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be a long-type positive number. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public static Person receivePerson() {
        return new Person(receivePersonName(), receiveHeightOfPerson(), receiveLocation());
    }

    public static Movie receiveMovie() {
        return new Movie(0L, receiveName(), receiveCoordinates(), receiveCreationDate(),
                receiveOscarsCount(), receiveTotalBoxOffice(), receiveGenre(), receiveMpaaRating(), receivePerson());
    }

}