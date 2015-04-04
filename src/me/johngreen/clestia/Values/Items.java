package me.johngreen.clestia.Values;

import me.johngreen.clestia.Clestia;
import org.powerbot.script.rt6.ClientContext;

/**
 * Created by johngreen on 01/04/15.
 */
public enum Items {
    //Runes
    Water_Rune(555),
    Astrial_Rune(9075),
    Cosmic_Rune(564),
    Air_Rune(556),
    Mud_Rune(4698),
    Pure_Essence(7936),


    //Logs
    Willow_Logs(1519),

    //Generic Items
    Emptie_Bucket(1921),
    Pot(1931),
    //QuestItems
    Top_Quality_Milk(15413),
    Super_Large_Egg(15412),
    Wheat(1947),
    Pot_Of_Flower(15414)

    ;


    private int id;
    private Items(int id){
        this.id=id;
    }
    public int getID(){
        return id;
    }
    public int getStackCount(ClientContext ctx){
        return ctx.backpack.select().id(id).poll().stackSize();
    }
    public int getCount(ClientContext ctx){
        return ctx.backpack.select().id(id).count();
    }
    public boolean hasItem(ClientContext ctx){
        return getCount(ctx)>0;
    }
    public String getName(){
        return Clestia.manager.getItemDefinitionLoader().load(id).name;
    }
    public void pickup(ClientContext ctx){
        ctx.groundItems.select().id(id).nearest().first().poll().click();
    }
}
