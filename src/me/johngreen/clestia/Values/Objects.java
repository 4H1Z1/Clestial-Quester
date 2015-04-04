package me.johngreen.clestia.Values;

import org.powerbot.script.rt6.ClientContext;

/**
 * Created by johngreen on 01/04/15.
 */
public enum Objects {
    //Cooks Assistant
    Lumbridge_Cow_Gate_Open(45213),
    Lumbridge_Cow_Gate_Closed(45212),

    Lumbridge_Chicken_Gate_Open(45207),
    Lumbridge_Chicken_Gate_Closed(45206),

    Lumbridge_Wheat_Farm_Gate_Closed(45210),
    Lumbridge_Wheat_Farm_Gate_Open(45211),

    Prized_Dary_Cow(47721),

    Wheat_Field(5506),

    Lumbridge_Wheat_Mill_Door_Closed(45964),
    Lumbridge_Wheat_Mill_Door_Open(45965),

    Lumbridge_Wheat_Mill_Ladder_Ground_Floor(35795),
    Lumbridge_Wheat_Mill_Ladder_First_Floor(35796),
    Lumbridge_Wheat_Mill_Ladder_Seccond_Floor(35797),

    Lumbridge_Wheat_Mill_Hopper(2718),
    Lumbridge_Wheat_Mill_Collection(2775),

    ;
    private int id;
    private Objects(int id){
        this.id = id;
    }
    public void interact(ClientContext ctx, String action){
        ctx.objects.select().id(id).nearest().first().poll().click(action);
    }
}
