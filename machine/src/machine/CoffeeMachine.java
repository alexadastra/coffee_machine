package machine;
import java.util.Scanner;
public class CoffeeMachine {
    private static CoffeeMaker machine = new CoffeeMaker(120, 400, 540, 9, 550);
    /*
    public CoffeeMachine() {
        this.machine = new CoffeeMachine(120, 400, 540, 9, 550);
    }
    */
    private enum states {
        DEFINE_COMMAND,
        DRINK_INPUT,
        DEFINE_DRINK,
        MAKING_DRINK,
        WATER_INPUT,
        BEANS_INPUT,
        MILK_INPUT,
        CUPS_INPUT,
        TAKING_MONEY
    }

    private static states currentState = states.DEFINE_COMMAND;

    private static boolean parseCommand(String command) {
        switch (currentState) {
            case DEFINE_COMMAND:
                switch (command){
                    case "remaining":
                        // printing resources with special function
                        machine.printMachineParameters();
                        break;
                    case "buy":
                        CoffeeMaker.demandDrink();
                        currentState = states.DEFINE_DRINK;
                        break;
                    case "fill":
                        CoffeeMaker.demandWater();
                        currentState = states.WATER_INPUT;
                        break;
                    case "take":
                        machine.takeMoney();
                        break;
                    case "exit":
                        return false;
                    default:
                        System.out.println("Error! Unidentified command!");
                        break;
                }
                break;
            case DRINK_INPUT:
                CoffeeMaker.demandDrink();
                currentState = states.DEFINE_DRINK;
                break;
            case DEFINE_DRINK:
                if (machine.defineDrink(command)) {
                    currentState = states.MAKING_DRINK;
                } else {
                    currentState = states.DEFINE_COMMAND;
                }
                break;
            case MAKING_DRINK:
                machine.makingCoffee();
                currentState = states.DEFINE_COMMAND;
                break;
            case WATER_INPUT:
                machine.waterNeeded = Integer.parseInt(command);
                CoffeeMaker.demandBeans();
                currentState = states.BEANS_INPUT;
                break;
            case BEANS_INPUT:
                machine.beansNeeded = Integer.parseInt(command);
                CoffeeMaker.demandMilk();
                currentState = states.MILK_INPUT;
                break;
            case MILK_INPUT:
                machine.milkNeeded = Integer.parseInt(command);
                CoffeeMaker.demandCups();
                currentState = states.CUPS_INPUT;
                break;
            case CUPS_INPUT:
                machine.cupsNeeded = Integer.parseInt(command);
                machine.fillWithResources();
                currentState = states.DEFINE_COMMAND;
                break;
            case TAKING_MONEY:
                machine.takeMoney();
                currentState = states.DEFINE_COMMAND;
                break;
            default:
                System.out.println("Error!");
                break;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCoffeeMachine;
        do {
            continueCoffeeMachine = parseCommand(scanner.nextLine());
        } while(continueCoffeeMachine);
    }
}

/*
public static class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine machine = new CoffeeMachine(120, 400, 540, 9, 550);
        boolean continueCoffeeMachine = true;
        do {
            continueCoffeeMachine = machine.parseCommand(scanner.nextLine());
        } while(continueCoffeeMachine);
    }
}
*/
/*
System.out.println("Write how many ml of water the coffee machine has: ");
int waterAmountTotal = scanner.nextInt();
int waterForCups = waterAmountTotal / waterAmountPerCup
System.out.println("Write how many ml of milk the coffee machine has: ");
int milkAmountTotal = scanner.nextInt();
int milkForCups = milkAmountTotal / milkAmountPerCup
System.out.println("Write how many grams of coffee beans the coffee machine has: ");
int beansAmountTotal = scanner.nextInt();
int beansForCup = beansAmountTotal / beansAmountPerCup
int maxNumberOfCups = waterForCups;
if (milkForCups < maxNumberOfCups) {
    maxNumberOfCups = milkForCups;
}
if (beansForCup < maxNumberOfCups) {
    maxNumberOfCups = beansForCup;
}
System.out.println("Write how many cups of coffee you will need: ");
int numberOfCups = scanner.nextInt()
if (maxNumberOfCups >= numberOfCups) {
    System.out.print("Yes, I can make that amount of coffee");
    if (maxNumberOfCups > numberOfCups) {
        System.out.println(" (and even " + (maxNumberOfCups - numberOfCups) + " more than that)");
    }
    else {
        System.out.println();
    }
} else {
    System.out.println("No, I can make only " + maxNumberOfCups + " cup(s) of coffee");
}
*
/*
System.out.println("For " + numberOfCups + " cups of coffee you will need:");
System.out.println(numberOfCups * waterAmountPerCup + " ml of water");
System.out.println(numberOfCups * milkAmountPerCup + " ml of milk");
System.out.println(numberOfCups * beansAmountPerCup + " g of coffee beans");
*/

/*
System.out.println("Starting to make a coffee");
System.out.println("Grinding coffee beans");
System.out.println("Boiling water");
System.out.println("Mixing boiled water with crushed coffee beans");
System.out.println("Pouring coffee into the cup");
System.out.println("Pouring some milk into the cup");
System.out.println("Coffee is ready!");
*/