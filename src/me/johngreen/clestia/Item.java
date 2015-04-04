package me.johngreen.clestia;

import me.johngreen.clestia.Values.Items;

/**
 * Created by johngreen on 02/04/15.
 */
public class Item {
    private Items item;
    private int amount;
    public Item(Items item, int amount){
        this.item = item;
        this.amount = amount;
    }
    public Items getItem(){
        return item;
    }
    public int getAmount(){
        return amount;
    }
}
