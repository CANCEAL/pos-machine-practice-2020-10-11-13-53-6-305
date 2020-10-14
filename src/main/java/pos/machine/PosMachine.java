package pos.machine;

import java.util.*;

public class PosMachine {
    private List<ItemInfo> itemInfos = ItemDataLoader.loadAllItemInfos();

    public String printReceipt(List<String> barcodes) {
       String formattedReceipt = decodeItems(barcodes);
       return formattedReceipt;
    }
}
