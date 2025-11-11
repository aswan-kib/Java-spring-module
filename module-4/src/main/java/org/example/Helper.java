package org.example;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author aswankib
 * @since 11/11/25
 */
public class Helper {
    private Helper() {}

    private static final String GREETINGS = "Welcome to the shop";
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String EGG = "Egg";
    private static final String MILK = "Milk";
    private static final String NOODLES = "Noodles";

    public static final String EGG_PURCHASE = "Purchase Egg";
    public static final String MILK_PURCHASE = "Purchase Milk";
    public static final String NOODLES_PURCHASE = "Purchase Noodles";
    public static final String PRINT_CART = "Print Cart";
    public static final String INCREMENT_QUANTITY = "Increment Quantity";
    public static final String DECREMENT_QUANTITY = "Decrement Quantity";
    public static final String PAYABLE_AMOUNT = "Payable Amount";
    public static final String EXIT = "Exit";

    public static final Map<Integer, String> choiceMap;
    private static Map<Integer, CartItem> cartItemMap;

    private static final List<CartItem> DEFAULT_ITEMS = Arrays.asList(
            new CartItem(EGG, 10),
            new CartItem(MILK, 20),
            new CartItem(NOODLES, 20)
    );

    private static final List<String> CHOICES = Arrays.asList(
            EGG_PURCHASE,
            MILK_PURCHASE,
            NOODLES_PURCHASE,
            PRINT_CART,
            INCREMENT_QUANTITY,
            DECREMENT_QUANTITY,
            PAYABLE_AMOUNT,
            EXIT
    );

    private static final List<String> INCREMENT_OPTIONS = Arrays.asList(EGG, MILK, NOODLES, EXIT);
    public static final Map<Integer, String> choiceMapForIncrementOrDecrement;

    static {
        choiceMap = Collections.unmodifiableMap(buildIndexMap(CHOICES));
        choiceMapForIncrementOrDecrement = Collections.unmodifiableMap(buildIndexMap(INCREMENT_OPTIONS));
    }

    /**
     * Build a 1-based index map for the supplied choices.
     */
    private static Map<Integer, String> buildIndexMap(List<String> choices) {
        Map<Integer, String> map = new LinkedHashMap<>();
        AtomicInteger counter = new AtomicInteger(1);
        for (String choice : choices) {
            map.put(counter.getAndIncrement(), choice);
        }
        return map;
    }

    public static void init() {
        System.out.println(GREETINGS);


        AtomicInteger counter = new AtomicInteger(1);
        Map<Integer, CartItem> tempMap = new LinkedHashMap<>();

        for (CartItem item : DEFAULT_ITEMS) {
            tempMap.put(counter.getAndIncrement(), item);
        }

        cartItemMap = Collections.unmodifiableMap(tempMap);
    }

    public static void operate(int selectedItem) {
        CartItem item = cartItemMap.get(selectedItem);

        if (item == null) {
            System.out.println("Invalid selection.");
            return;
        }

        item.incrementQuantity();
        System.out.println(item.getName() + " item added into card");
    }

    public static void printPayableAmount() {
        double totalAmount = printCart();

        System.out.println("Total Payable Amount: " + totalAmount);
    }

    public static double printCart() {
        System.out.println("My Cart items:\n");

        if (cartItemMap == null || cartItemMap.isEmpty()) {
            System.out.println("(cart is empty)");
            return 0.0;
        }

        AtomicInteger count = new AtomicInteger(1);
        double totalAmount = 0;

        for (Map.Entry<Integer, CartItem> entry : cartItemMap.entrySet()) {
            CartItem item = entry.getValue();

            if (item.getQuantity() > 0) {
                System.out.println(String.format("%d. %s", count.getAndIncrement(), item.getNameAndQuantity()));

                totalAmount += item.getTotalAmount();
            }
        }

        if (count.get() == 1) {
            System.out.println("(no items in cart yet)");
        }

        return totalAmount;
    }

    public static void incrementAndDecrement(boolean isIncrement) {
        boolean isExit = false;

        while (!isExit) {
            int selectedItem = getInputFromUserForIncrementOrDecrement();
            String choice = choiceMapForIncrementOrDecrement.get(selectedItem);

            if (choice == null) {
                System.out.println("Please select valid item");
                continue;
            }

            switch (choice) {
                case EGG:
                case MILK:
                case NOODLES:
                    operate(selectedItem, isIncrement);
                    break;
                case EXIT:
                    isExit = true;
                    break;
                default:
                    System.out.println("Please select valid item");
            }
        }
    }

    public static int printItemsGetInput() {
        AtomicInteger count = new AtomicInteger(1);

        System.out.println("\n\nPlease select item to purchase (e.g. 1 or 2)");

        for(String item: CHOICES) {
            String str = String.format("%d. %s", count.getAndIncrement(), item);

            System.out.println(str);
        }

        return getIntFromUser();
    }

    public static int getIntFromUser() {
        if (!SCANNER.hasNextInt()) {
            String invalid = SCANNER.next();
            throw new NumberFormatException("Invalid number: " + invalid);
        }

        return SCANNER.nextInt();
    }

    private static void operate(int selectedItem, boolean isIncrement) {
        CartItem item = cartItemMap.get(selectedItem);

        boolean success = false;

        if (isIncrement) {
            item.incrementQuantity();

            success = true;
        } else {
            if (item.canDecrement()) {
                item.decrementQuantity();

                success = true;
            } else {
                System.out.println("Can't decrement the quantity of " + item.getName());
            }
        }

        if (success) {
            System.out.println(item.getName() + ": " + (isIncrement ? "Added " : "Removed ") + "One item Successfully");
        }
    }

    private static int getInputFromUserForIncrementOrDecrement() {
        System.out.println("Please select the item you want to increment or decrement");
        printItems();

        return getIntFromUser();
    }

    private static void printItems() {
        AtomicInteger count = new AtomicInteger(1);

        for(String item: INCREMENT_OPTIONS) {
            System.out.println(String.format("%d. %s", count.getAndIncrement(), item));
        }
    }
}
