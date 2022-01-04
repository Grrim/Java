package pl.grim;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        Computation computation = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Co chcesz zrobić?");
        System.out.println("1. Dodawanie");
        System.out.println("2. Odejmowanie");
        System.out.println("3. Mnożenie");
        System.out.println("4. Dzielenie");

        int choose = scanner.nextInt();

        switch (choose) {
             case 1 -> {
                computation = new Addition();
            }
            case 2 -> {
                computation = new Subtraction();
            }
            case 3 -> {
                 computation = new Multiplication();
            }
            case 4 -> {
                computation = new Division();
            }
            default -> {
                System.out.println("Błąd! Nie ma takiej opcji");
            }
        }

        double argument1 = main.getArgument();
        double argument2 = main.getArgument();

        double result = computation.compute(argument1, argument2);
        System.out.println("Wynik: " + result);
    }

    private boolean shouldMultiply() {
        System.out.println("Co chcesz zrobić");
        System.out.println("M - mnożenie / D - dodawanie");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String wybor = scanner.nextLine();
            System.out.println(wybor);
            if (wybor.equals("M")) {
                return true;
            } else if (wybor.equals("D")) {
                return false;
            } else {
                System.out.println("Błąd! Spróbuj ponownie");
            }
        }
    }

    private double getArgument() {
        System.out.println("Podaj liczbę: ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        return number;
    }
}
