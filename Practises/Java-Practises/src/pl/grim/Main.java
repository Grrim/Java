package pl.grim;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static void FizzBuzz() {
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else {
                System.out.println(i);
            }
        }
    }

    static void FindRandomNumber() {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;
        Scanner scanner = new Scanner(System.in);

        boolean wasNumberGuessed = false;

        while (!wasNumberGuessed) {
            System.out.println("Podaj liczbę: ");
            int number = scanner.nextInt();
            if (number == numberToGuess) {
                wasNumberGuessed = true;
                System.out.println("Brawo! Poprawnie zgadłeś liczbę");
            } else if (number > numberToGuess) {
                System.out.println("Zgaduj dalej! Liczba jest za duża");
            } else {
                System.out.println("Zgaduj dalej! Liczba jest za mała");
            }
        }
    }

    static void ListOfGuests() {
        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;

        Party party = new Party();

        while (shouldContinue) {
            System.out.println("Wybierz opcję");
            System.out.println("1. Wyświetl gości");
            System.out.println("2. Dodaj gościa");
            System.out.println("3. Wyświetl potrawy");
            System.out.println("4. Znajdź po numerze");
            System.out.println("5. Wyjście");

            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1 -> party.displayGuests();
                case 2 -> party.addGuest();
                case 3 -> party.displayMeals();
                case 4 -> party.displayGuestByPhoneNumber();
                case 5 -> shouldContinue = false;
                default -> System.out.println("Błędny numer!");
            }
        }
    }

    public static void main(String[] args) {
        boolean shouldContinue = true;

        System.out.println("Wybierz program: ");
        System.out.println("1. FizzBuzz");
        System.out.println("2. Zgadnij liczbę");
        System.out.println("3. Lista gości");
        System.out.println("4. Wyjście");

        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();

        while (shouldContinue) {
            switch (userChoice) {
                case 1 -> {
                    FizzBuzz();
                    shouldContinue=false;
                }
                case 2 -> {
                    FindRandomNumber();
                    shouldContinue=false;
                }
                case 3 -> {
                    ListOfGuests();
                    shouldContinue=false;
                }
                case 4 -> shouldContinue = false;
                default -> System.out.println("Błędny numer!");
            }
        }
    }
}
