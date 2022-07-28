package comp3350.ims.objects;

import java.util.*;

public class ItemType implements Comparable < ItemType > {
    public static final int MIN_QUANTITY = 10;

    private int ID;
    private String name;
    private float price;
    private int quantity;
    private String location;
    private String date;
    private String category;
    private ArrayList < Item > items;
    private static int itemTypeID = 0;
    private boolean needsRefill;

    public ItemType() {
        ID = itemTypeID++;
        name = "";
        price = 0;
        quantity = 0;
        location = "";
        date = "";
        category = "";
        items = new ArrayList < > ();
    } //Base constructor

    public ItemType(String name, float price, String location, String date, String category) {
        ID = itemTypeID++;
        this.name = name;
        this.price = price;
        this.location = location;
        this.date = date;
        this.category = category;
        this.quantity = 0;
        items = new ArrayList < Item > ();
    }

    public void addItem(Item item) {
        items.add(item);
        quantity++;
        checkRefill();
    }

    public void removeItem(int index) {
        if (!items.isEmpty() && index >= 0 && index < items.size()) {
            items.remove(index);
            quantity--;
        }
        checkRefill();
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (o instanceof ItemType) {
            ItemType item = (ItemType) o;
            if ((this.name).equals(item.getName())) {
                isEqual = true;
            }
        }
        return isEqual;
    }

    @Override
    public int compareTo(ItemType o) {
        if (this.quantity == o.getQuantity())
            return 0;
        else if (this.quantity > o.getQuantity())
            return 1;
        else
            return -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float p) {
        this.price = p;
    }

    public Item getItem(int index) {
        if (!items.isEmpty()) {
            return items.get(index);
        } else {
            return null;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSize() {
        return items.size();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String c) {
        this.category = c;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public boolean needsRefill() {
        checkRefill();
        return needsRefill;
    }

    public void setNeedsRefill(boolean needsRefill) {
        this.needsRefill = needsRefill;
    }

    private void checkRefill() {
        this.needsRefill = this.quantity <= MIN_QUANTITY;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
        if (id > itemTypeID) {
            itemTypeID = id + 1;
        }
    }

}