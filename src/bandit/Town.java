package bandit;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Town extends Player {

  Random rand = new Random(); // RANDOM UTIL
  String saintDenis = "Saint Denis";
  String[] towns = {"Valentine", "Rhodes", "Lagras", saintDenis}; // different towns
  int currentTown = 0;
  boolean freshClothes = false;
  boolean haircut = false;
  boolean maskBought = false;
  int amountOfSyringes = 2;

  public void displayOptions() {

    System.out.println("\n\t----------------------------"); // bandit.Player stats
    System.out.println("\t > You are in " + towns[currentTown]);
    System.out.println("\t > Your health is at " + health);
    System.out.println("\t > You have " + money + " gold");
    System.out.println("\t > You have " + notoriety + "% notoriety");
    System.out.println("\t > You have " + strength + " strength");
    System.out.println("\t----------------------------"); // bandit.Player stats

    System.out.println("\t\nWhat would you like to do?\n"); // Shops
    System.out.println("\t# 1. Browse the shop");
    System.out.println("\t# 2. Enter barbershop");
    System.out.println("\t# 3. Rob a bank");
    System.out.println("\t# 4. Rob someone");
    System.out.println("\t# 5. Browse the stables");
    if (!Objects.equals(towns[currentTown], saintDenis)) {
      System.out.println("\t# 6. Travel to the next town");
    }
    if (towns[currentTown].equals(saintDenis)) {
      System.out.println("\t# 7. Visit harbour\n");
    } else System.out.println("\n");
  }

  public void pickOption() {

    Scanner in = new Scanner(System.in);

    String input = in.nextLine(); // MAIN INPUT

    if (input.equals("1")) { // bandit.Main screen input 1

      System.out.println("\t-- You enter a goods store in town. --\n");
      System.out.println("\tShopkeeper: Welcome, would you like to purchase?\n");

      System.out.println(
          """
              # 1. Revolver - 20 gold
              # 2. Shotgun - 40 gold
              # 3. Mask - 30 gold
              # 4. Clothes - 20 gold
              # 5. Syringe - 15 gold
              # 6. Exit
              """);
      String inputShop = in.nextLine(); //                    GETTING INPUT FROM THE USER

      if (inputShop.equals("1")) { // SHOP INPUT 1

        if (money >= 20) {

          money -= 20;
          System.out.println("\n-- You bought a revolver (strength is now 2) --\n");
          strength = 2;

        } else System.out.println("\nYou cannot afford a revolver");

      } else if (inputShop.equals("2")) { // SHOP INPUT 2

        if (money >= 40) {

          money -= 40;
          System.out.println("\n-- ou bought a shotgun (strength is now 3) --\n");
          strength = 3;

        } else System.out.println("\nYou cannot afford a shotgun");

      } else if (inputShop.equals("3")) {

        if (maskBought) {
          System.out.println("\nSorry, we are all out of masks, try the next town over.");
        } else if (money >= 30) {

          if (!mask) {

            money -= 30;
            System.out.println(
                """
                    -- You have bought a mask
                    (this will hide your identity when commiting crimes)
                    --
                    """);
            mask = true;
            maskBought = true;

          } else System.out.println("\n\tYou already own a mast.\n");

        } else System.out.println("\nYou cannot afford a mask");

      } else if (inputShop.equals("4")) { // SHOP INPUT 3

        if (money >= 10) {

          if (!freshClothes) {

            money -= 20;
            System.out.println(
                """
                    -- You have bought a fresh set of clothes.
                    ( - 20 gold & - 40% notoriety)
                    --
                    """);
            notoriety = notoriety - 40;
            freshClothes = true;

          } else System.out.println("\n\tYou have already bought clothes in this town.\n");

        } else System.out.println("\nYou cannot afford clothes");

      } else if (inputShop.equals("5")) { // SHOP INPUT 2

        if (amountOfSyringes <= 0) {

          System.out.println("\tShopkeeper: There are no syringes left.\n");

        } else if (amountOfSyringes > 0) {

          System.out.println(
              """
                  -- You bought then injected yourself with the dirty syringe.
                  ( - 15 gold & + 10 health)
                  --
                  """);
          amountOfSyringes = amountOfSyringes - 1;
          money = money - 15;
          health = health + 10;
        }
      }

    } else if (input.equals("2")) {

      System.out.println(
          "\n\t-- You enter a Barbershop in town --\n\n\tBarber: Would you like a trim?\n");
      System.out.println("\t# 1. Get a hair cut - 30 gold\n\t# 2. Go back\n");

      String inputBarber = in.nextLine(); //                    GETTING INPUT FROM THE USER

      if (inputBarber.equals("1")) { // Barber shop INPUT

        if (money < 20) {

          System.out.println("Barber: You do not have enough money buster.");

        } else if (!haircut) {

          System.out.println("\tYou have got a hair cut ( - 30 gold & - 60% notoriety)\n");
          notoriety = notoriety - 60;
          money = money - 30;
          haircut = true;

        } else System.out.println("\n\tYour hair is not long enough, try travelling.\n");
      }

    } else if (input.equals("3")) { // bandit.Main screen input 2

      int outcome = rand.nextInt(10);
      int difficulty;

      if (strength == 1) {
        difficulty = 6;
      } else if (strength == 2) {
        difficulty = 7;
      } else {
        difficulty = 9;
      }

      if (outcome >= difficulty) {

        System.out.println(
            """
                You were shot while robbing the bank!
                -- ( - 40 health) --
                -- You lie low for a while --
                """);
        health = health - 40;

      } else if (outcome < difficulty) {

        System.out.println(
            """
                -- You robbed the bank! ( + 60 gold & + 70 notoriety) --
                -- You lie low for a while --
                """);
        money = money + 60;
        if (!mask) {
          notoriety += 70;
        } else
          System.out.println("\n-- You did not gain notoriety as you " + "were wearing a mask --");
        mask = false;
      }

    } else if (input.equals("4")) { // bandit.Main screen input 2

      int outcome = rand.nextInt(10);

      if (outcome >= 8) {

        System.out.println("\t-- You were hurt robbing the someone! ( - 10 health) --\n");
        health = health - 10;

      } else if (outcome < 8) {

        System.out.println("\n-- You robbed someone! ( + 15 gold & + 40 notoriety) --\n");
        money = money + 15;
        if (!mask) {
          notoriety += 40;
        } else System.out.println("\nYou did not gain noteriety as you were wearing a mask");
        mask = false;
      }

    } else if (input.equals("5")) { // bandit.Main screen input 2

      System.out.println("\tStablemaster: Welcome sir would you like to buy a horse?\n");
      System.out.println("\t# 1. Purchase stallion - 50 gold\n\t# 2. Go back\n");

      String inputStable = in.nextLine(); //                    GETTING INPUT FROM THE USER

      if (inputStable.equals("1")) { // HORSE STABLES INPUT

        System.out.println("\t-- You have purchased a stallion! (You are now able to travel) --\n");
        money = money - 50;
        playerOwnsHorse = true;
      }

    } else if (input.equals("6")
        && !Objects.equals(towns[currentTown], saintDenis)) { // bandit.Main screen input 2

      if (playerOwnsHorse) {

        System.out.println(
            """
                          You start your journey to the next town...
                          Your hair grows
                          """);
        currentTown = currentTown + 1;
        freshClothes = false;
        haircut = false;
        maskBought = false;

      } else
        System.out.println(
            "\tYou have to own a horse to travel. " + "(go to the stables to buy one)\n");

    } else if (input.equals("7") && towns[currentTown].equals(saintDenis)) {

      System.out.println("\tSailor: Hello friend\n\n");
      System.out.println("\t# 1. Take me to Mexico (150 gold)\n\t# 2. Leave\n");

      String inputHarbour = in.nextLine();

      if (money >= 150 && inputHarbour.equals("1")) {

        System.out.println(
            "/////////////////////////////////////////////////////////\n\n"
                + "-- Well done you have won the game with a score of "
                + money
                + "! --");
        System.exit(0);
      }

    } else System.out.println("\t\tinput not recognised please type a number you see.\n");

    if (health <= 0) {

      System.out.println("\n\n\n\tYOU ARE DEAD");
      System.exit(0);

    } else if (notoriety >= 100) {

      System.out.println("\n\n\n\tYOU WERE CAUGHT");
      System.exit(0);
    }
  }
}
