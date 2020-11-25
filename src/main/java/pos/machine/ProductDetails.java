package pos.machine;

public class ProductDetails {
    private ItemInfo item;
    private int count;
    private int subtotal;

    public ProductDetails(ItemInfo item, int count) {
        this.item = item;
        this.count = count;
        this.subtotal = item.getPrice() * count;
    }

    public ItemInfo getItem(){
        return item;
    }

    public void setItem(ItemInfo item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSubtotal() {
        return subtotal;
    }
}
