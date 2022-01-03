package pl.grim;

import java.util.*;

public class Party {
    private List<Guest> quests = new ArrayList<>();
    private Set<String> meals = new HashSet<>();
    private Map<Integer, Guest> phoneToGuest = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    public void addGuest(){
        System.out.println("Podaj imię gościa: ");
        String name = scanner.nextLine();

        System.out.println("Podaj preferowany posiłek: ");
        String meal = scanner.nextLine();

        System.out.println("Podaj numer telefonu: ");
        int phoneNumber = Integer.valueOf(scanner.nextLine());

        boolean isVegan = false;

        while(isVegan == false) {
            System.out.println("Czy weganin? (Y/N): ");
            String isVeganString = scanner.nextLine();

            if (isVeganString.equals("Y")) {
                isVegan = true;
                break;
            } else if (isVeganString.equals("N")) {
                isVegan = false;
                break;
            }
            else{
                System.out.println("");
                System.out.println("Błąd! Wybierz Y albo N");
            }
        }

        Guest guest = new Guest(name, meal, phoneNumber, isVegan);

        meals.add(meal);
        phoneToGuest.put(phoneNumber, guest);
        quests.add(guest);
    }

    public void displayMeals(){
        for (String meal : meals){
            System.out.println(meal);
        }
    }

    public void displayGuestByPhoneNumber(){
        System.out.println("Podaj numer telefonu: ");
        Integer phoneNumber = Integer.valueOf(scanner.nextLine());
        Guest guest = phoneToGuest.get(phoneNumber);

        if (guest == null){
            System.out.println("");
            System.out.println("Nie ma takiego numeru");
            System.out.println("");
        }
        else{
            guest.displayGuestInformation();
        }
    }

    public void displayGuests(){
        for (Guest quest : quests) {
            quest.displayGuestInformation();
            System.out.println("");
        }
    }
}
