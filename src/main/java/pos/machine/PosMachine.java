package pos.machine;

import java.util.*;

public class PosMachine {
    private List<ItemInfo> itemInfos = ItemDataLoader.loadAllItemInfos();

    public String printReceipt(List<String> barcodes) {
       String formattedReceipt = decodeItems(barcodes);
       return formattedReceipt;
    }

    public String decodeItems(List<String> barcodes) {
        List<String> itemNames = new ArrayList<>();

        for (int infoItems = 0; infoItems <= itemInfos.size()-1; infoItems++) {
            for (int barcodeItems = 0; barcodeItems <= barcodes.size()-1; barcodeItems++) {
                if (itemInfos.get(infoItems).getBarcode().equals(barcodes.get(barcodeItems))) {
                    itemNames.add(itemInfos.get(infoItems).getName());
                }
            }
        }
        return combineItems(itemNames);
    }

    public String combineItems(List<String> itemNames) {
        Set<String> hashSet = new HashSet<String>(itemNames);
        List<Integer> frequencyOfItems = new ArrayList<>();

        for (String str : hashSet)
            frequencyOfItems.add(Collections.frequency(itemNames, str));

        return String.valueOf(calculateItems(frequencyOfItems));
    }

    public String calculateItems(List<Integer> frequencyOfItems) {
        List<Integer> productOfItems = new ArrayList<>();
        int product = 0;

        for (int items = 0; items <= frequencyOfItems.size()-1; items++) {
            product = itemInfos.get(items).getPrice() * frequencyOfItems.get(items);
            productOfItems.add(product);
        }
        return String.valueOf(provideReceipt(productOfItems, frequencyOfItems));
    }
}
