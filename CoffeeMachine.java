package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public static int cups;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Machine machine = new Machine(400, 540, 120, 550, 9);
        while (!machine.exit) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String action = s.nextLine();
            System.out.println();
            machine.menu(action);
        }
    }
}

class Machine {
    boolean endOfIngredients;
    boolean exit;
    int water;
    int milk;
    int beans;
    int money;
    int cups;

    public Machine(int water, int milk, int beans, int money, int cups) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.money = money;
        this.cups = cups;
    }

    public void makeEspresso() {
        final int needWater = 250;
        final int needBeans = 16;
        final int needMilk = 0;
        final int needMoney = 4;
        final int needCups = 1;
        checkIngredients(needWater, needMilk, needBeans, needCups);
        if (!endOfIngredients) {
            water -= needWater;
            beans -= needBeans;
            cups -= needCups;
            money += needMoney;
        }
    }

    public void makeLatte() {
        final int needWater = 350;
        final int needBeans = 20;
        final int needMilk = 75;
        final int needMoney = 7;
        final int needCups = 1;
        checkIngredients(needWater, needMilk, needBeans, needCups);
        if (!endOfIngredients) {
            water -= needWater;
            beans -= needBeans;
            milk -= needMilk;
            cups -= needCups;
            money += needMoney;
        }
    }

    public void makeCappuccino() {
        final int needWater = 200;
        final int needBeans = 12;
        final int needMilk = 100;
        final int needMoney = 6;
        final int needCups = 1;
        checkIngredients(needWater, needMilk, needBeans, needCups);
        if (!endOfIngredients) {
            water -= needWater;
            beans -= needBeans;
            milk -= needMilk;
            cups -= needCups;
            money += needMoney;
        }
    }

    public void getInfo() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n%d ml of milk\n", water, milk);
        System.out.printf("%d g of coffee beans\n%d disposable cups\n", beans, cups);
        System.out.printf("$%d of money\n\n", money);
    }

    public void fillMachine() {
        Scanner s = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        water += s.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        milk += s.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        beans += s.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        cups += s.nextInt();
    }

    public void takeMoney() {
        System.out.printf("I gave you $%d\n\n", money);
        money = 0;
    }

    public void checkIngredients(int water,int milk, int beans,int cups) {
        if (this.water - water < 0) {
            endOfIngredients = true;
            System.out.println("Sorry, not enough water!\n");
            return;
        } else if (this.milk - milk < 0) {
            endOfIngredients = true;
            System.out.println("Sorry, not enough milk!\n");
            return;
        } else if (this.beans - beans < 0) {
            endOfIngredients = true;
            System.out.println("Sorry, not enough beans!\n");
            return;
        } else if (this.cups - cups <= 0) {
            endOfIngredients = true;
            System.out.println("Sorry, not enough cups!\n");
            return;
        } else {
            endOfIngredients = false;
            System.out.println("I have enough resources, making you a coffee!\n");
        }
    }

    public void menu(String action) {
        switch (action) {
            case "buy":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                Scanner s = new Scanner(System.in);
                CoffeeMenu[] menu = CoffeeMenu.values();
                int choose;
                try {
                    choose = s.nextInt() - 1;
                } catch (Exception e) {
                    System.out.println();
                    break;
                }
                switch (menu[choose]) {
                    case ESPRESSO:
                        makeEspresso();
                        break;
                    case LATTE:
                        makeLatte();
                        break;
                    case CAPPUCCINO:
                        makeCappuccino();
                        break;
                }
                break;
            case "fill":
                fillMachine();
                break;
            case "take":
                takeMoney();
                break;
            case "remaining":
                getInfo();
                break;
            case "exit":
                exit = true;
                break;
        }
    }
}
enum CoffeeMenu {
    ESPRESSO, LATTE, CAPPUCCINO
}

