package pos.machine;

import java.util.*;

import static pos.machine.ItemDataLoader.loadAllItemInfos;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return decodeBarcode(barcodes);
    }

    public String decodeBarcode(List<String> barcodes) {
        getItemName(barcodes);
        return getItemName(barcodes);
    }

    public String getItemName(List<String> barcodes) {
        ItemDataLoader itemDataLoader = new ItemDataLoader();
        List<String> itemNames = new ArrayList<>();
        String str = "";

        for (int i = 0; i <= itemDataLoader.loadAllItemInfos().size()-1; i++) {
            for (int j = 0; j <= barcodes.size()-1; j++) {
                if (itemDataLoader.loadAllItemInfos().get(i).getBarcode().equals(barcodes.get(j))) {
                    //str += loadAllItemInfos().get(i).getName() + " ";
                    itemNames.add(loadAllItemInfos().get(i).getName());
                }
            }
        }

        //return String.valueOf(itemNames); [Coca-Cola, Coca-Cola, Coca-Cola, Coca-Cola, Sprite, Sprite, Battery, Battery, Battery]
        return combineItems(itemNames);
    }

    public String combineItems(List<String> itemNames) {
        Set<String> hashSet = new HashSet<String>(itemNames);
        List<Integer> frequencyOfItems = new ArrayList<>();
        List<String> frequencyOfItemNames = new ArrayList<>();

        for (String str : hashSet)
            frequencyOfItems.add(Collections.frequency(itemNames, str));

        for (String str : hashSet)
            frequencyOfItemNames.add(str);

        //return String.valueOf(frequencyOfItemNames); //[Coca-Cola, Sprite, Battery]
        //return String.valueOf(frequencyOfItems); //[4, 2, 3]
        return String.valueOf(calculateItems(frequencyOfItems));

    }

    public String calculateItems(List<Integer> frequencyOfItems) {
        ItemDataLoader itemDataLoader = new ItemDataLoader();
        List<Integer> productOfItems = new ArrayList<>();
        int product = 0;

        for (int i = 0; i <= frequencyOfItems.size()-1; i++) {
            product = itemDataLoader.loadAllItemInfos().get(i).getPrice() * frequencyOfItems.get(i);
            productOfItems.add(product);
        }
        //return Collections.singletonList(calculateTotal(productOfItems));
        //return productOfItems; //[12, 6, 6]
        return String.valueOf(provideReceipt(productOfItems, frequencyOfItems.get(0).toString(), frequencyOfItems.get(1).toString(), frequencyOfItems.get(2).toString()));
    }

//    public Integer calculateTotal(List<Integer> totalOfItems) {
//        int sumOfAmountOfItem = 0;
//
//        for (int i = 0; i <= totalOfItems.size()-1; i++) {
//            sumOfAmountOfItem += totalOfItems.get(i);
//        }
//        return sumOfAmountOfItem; //[24]
//    }

    public String provideReceipt(List<Integer> totalOfItems, String subtotal1, String subtotal2, String subtotal3) {
        PosMachine posMachine = new PosMachine();
        ItemDataLoader itemDataLoader = new ItemDataLoader();

        String header = "***<store earning no money>Receipt***\n";
        String lineSeparator = "----------------------\n";
        String endLine = "**********************";

        String item1 = "Name: " + itemDataLoader.loadAllItemInfos().get(0).getName() + ", Quantity: " + subtotal1 + ", Unit price: " + itemDataLoader.loadAllItemInfos().get(0).getPrice() + " (yuan), Subtotal: " + totalOfItems.get(0) + " (yuan)\n";
        String item2 = "Name: " + itemDataLoader.loadAllItemInfos().get(1).getName() + ", Quantity: " + subtotal2 + ", Unit price: " + itemDataLoader.loadAllItemInfos().get(1).getPrice() + " (yuan), Subtotal: " + totalOfItems.get(1) + " (yuan)\n";
        String item3 = "Name: " + itemDataLoader.loadAllItemInfos().get(2).getName() + ", Quantity: " + subtotal3 + ", Unit price: " + itemDataLoader.loadAllItemInfos().get(2).getPrice() + " (yuan), Subtotal: " + totalOfItems.get(2) + " (yuan)\n";

        String total = "Total: " + (totalOfItems.get(0) + totalOfItems.get(1) + totalOfItems.get(2)) + " (yuan)\n";

        return header + item1 + item2 + item3 + lineSeparator + total + endLine;
    }

}
