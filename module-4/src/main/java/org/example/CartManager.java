package org.example;

import static org.example.Helper.*;

public class CartManager {

    public static void main(String[] args) {
        Helper.init();
        boolean isExit = false;

        while (!isExit) {
            int selectedItem = printItemsGetInput();

            switch (choiceMap.get(selectedItem)) {
                case EGG_PURCHASE:
                case MILK_PURCHASE:
                case NOODLES_PURCHASE:
                    operate(selectedItem);
                    break;
                case PRINT_CART:
                    printCart();
                    break;
                case INCREMENT_QUANTITY:
                    incrementAndDecrement(true);
                    break;
                case DECREMENT_QUANTITY:
                    incrementAndDecrement(false);
                    break;
                case PAYABLE_AMOUNT:
                    printPayableAmount();
                    break;
                case EXIT:
                    isExit = true;
                    break;
                default:
                    System.out.println("Please choose valid item");
            }
        }
    }
}