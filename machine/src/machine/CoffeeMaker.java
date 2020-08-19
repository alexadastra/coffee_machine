package machine;

public class CoffeeMaker {
    // initialising constants for coffee cups:
    // for all of them we need 1 disposable cup:
    // int coffeeDisposableCup = 1;
    // for espresso
    private final static int waterAmountPerEspressoCup = 250;
    private final static int beansAmountPerEspressoCup = 16;
    private final static int milkAmountPerEspressoCup = 0;
    private final static int espressoCupPrice = 4;
    // for latte
    private final static int waterAmountPerLatteCup = 350;
    private final static int beansAmountPerLatteCup = 20;
    private final static int milkAmountPerLatteCup = 75;
    private final static int latteCupPrice = 7;
    // for cappuccino
    private final static int waterAmountPerCappuccinoCup = 200;
    private final static int beansAmountPerCappuccinoCup = 12;
    private final static int milkAmountPerCappuccinoCup = 100;
    private final static int cappuccinoCupPrise = 6;

    // initialising initial coffee machine parameters
    /*
    private int beansAmount;
    private int waterAmount;
    private int milkAmount;
    private int disposableCupsNumber;
    private int moneyAmount;
    */
    private int beansAmount = 120;
    private int waterAmount = 400;
    private int milkAmount = 540;
    private int disposableCupsNumber = 9;
    private int moneyAmount = 550;
    // variables defining what coffee to make

    public int milkNeeded = 0;
    public int beansNeeded = 0;
    public int waterNeeded = 0;
    public int moneyNeeded = 0;
    public int cupsNeeded = 0;

    // constructor

    public CoffeeMaker(int water, int milk, int beans, int cups, int money) {
        this.beansAmount = beans;
        this.waterAmount = water;
        this.milkAmount = milk;
        this.disposableCupsNumber = cups;
        this.moneyAmount = money;
    }

    static void demandCommand() {
        System.out.println("Write action (buy, fill, take): ");
    }

    void printMachineParameters() {
        System.out.println("The coffee machine has:");
        System.out.println(waterAmount + " of water");
        System.out.println(milkAmount + " of milk");
        System.out.println(beansAmount + " of coffee beans");
        System.out.println(disposableCupsNumber + " of disposable cups");
        System.out.println(moneyAmount + " of money");
    }

    static void demandDrink() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, " +
                "back - to main menu:");
    }

    boolean defineDrink(String coffeeType) {
        switch (coffeeType) {
            case "1":
                waterNeeded = waterAmountPerEspressoCup;
                beansNeeded = beansAmountPerEspressoCup;
                milkNeeded = milkAmountPerEspressoCup;
                moneyNeeded = espressoCupPrice;
                return true;
            case "2":
                waterNeeded = waterAmountPerLatteCup;
                beansNeeded = beansAmountPerLatteCup;
                milkNeeded = milkAmountPerLatteCup;
                moneyNeeded = latteCupPrice;
                return true;
            case "3":
                waterNeeded = waterAmountPerCappuccinoCup;
                beansNeeded = beansAmountPerCappuccinoCup;
                milkNeeded = milkAmountPerCappuccinoCup;
                moneyNeeded = cappuccinoCupPrise;
                return true;
            case "back":
                return false;
            default:
                System.out.println("Error! Unidentified drink!");
                return false;
        }
    }

    boolean checkElementsNeeded() {
        String notEnough = "";
        if (waterNeeded > waterAmount) {
            notEnough = "water";
        } else if (beansNeeded > beansAmount) {
            notEnough = "coffee beans";
        } else if (milkNeeded > milkAmount) {
            notEnough = "milk";
        } else if (1 > disposableCupsNumber) {
            notEnough = "disposable cups";
        }
        if (notEnough.isEmpty()) {
            return true;
        }
        System.out.println("Sorry, not enough ".concat(notEnough).concat("!"));
        return false;
    }

    void makingCoffee() {
        // checking if remaining resources are enough
        if (checkElementsNeeded()) {
            // if enough, making fancy drink
            System.out.println("I have enough resources, making you a coffee!");
            // subtracting resources from the machine
            beansAmount -= beansNeeded;
            waterAmount -= waterNeeded;
            milkAmount -= milkNeeded;
            disposableCupsNumber--;
            moneyAmount += moneyNeeded;
        } // if not, special message from function will be written
    }

    static void demandWater() {
        System.out.println("Write how many ml of water do you want to add:");
    }

    static void demandMilk() {
        System.out.println("Write how many ml of milk do you want to add:");
    }

    static void demandBeans() {
        System.out.println("Write how many grams of coffee beans do you want to add:");
    }

    static void demandCups() {
        System.out.println("Write how many disposable cups of coffee do you want to add:");
    }

    void fillWithResources() {
        waterAmount += waterNeeded;
        beansAmount += beansNeeded;
        milkAmount += milkNeeded;
        disposableCupsNumber += cupsNeeded;
    }

    void takeMoney() {
        System.out.println("I gave you $" + moneyAmount);
        moneyAmount = 0;
    }
}
