package pos.machine;

import java.util.List;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        int totalPrice = 0;
        String receipt = "***<store earning no money>Receipt***\n";
        List<ItemInfo> itemInfo = ItemDataLoader.loadAllItemInfos();
        List<String> itemList = ItemDataLoader.loadBarcodes();
        List<String> shortlistedBarcodes = barcodes.stream().distinct().collect(Collectors.toList());

        for (String shortlistedBarcode : shortlistedBarcodes) {
            ProductDetails productDetails = getProductData(shortlistedBarcode, itemInfo, itemList);
            totalPrice = totalPrice + productDetails.getSubtotal();
            receipt += appendProductDetails(productDetails);
        }
        receipt += "----------------------\n";
        receipt += "Total: " + totalPrice + " (yuan)\n";
        receipt += "**********************";

        return receipt;
    }

    private ProductDetails getProductData(String barcodeList, List<ItemInfo> itemInfo, List<String> itemList) {
        int count = 0;
        for (String barcode : itemList) {
            if (barcode.equals(barcodeList)) {
                count++;
            }
        }
        for (ItemInfo info : itemInfo) {
            if (info.getBarcode() == barcodeList) {
                ProductDetails currentProduct = new ProductDetails(info, count);
                currentProduct.setItem(info);
                currentProduct.setCount(count);
                return currentProduct;
            }
        }
        return null;
    }

    private String appendProductDetails(ProductDetails productDetails) {
        return "Name: " + productDetails.getItem().getName() + ", Quantity: " + productDetails.getCount() + ", Unit price: " + productDetails.getItem().getPrice() + " (yuan), Subtotal: " + productDetails.getSubtotal() + " (yuan)\n";
    }
}
