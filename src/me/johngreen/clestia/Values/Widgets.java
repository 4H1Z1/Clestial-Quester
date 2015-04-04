package me.johngreen.clestia.Values;

import org.powerbot.script.rt6.ClientContext;

/**
 * Created by johngreen on 01/04/15.
 */
public enum Widgets {
    //Windows
    Player_Chat_Window_Widget(1191),
    NPC_Chat_Window_Widget(1184),
    Chat_Option_Window_Widget_Select(1188),
    Quest_Window_Widget(1500),

    //Buttons
    Player_Chat_Window_Widget_Button(7),
    NPC_Chat_Window_Widget_Button(11),
    Accept_Quest_Window_Widget_Button(402),

    //Quest Buttons
    Quest_Button_Cook_Whats_Wrong(12),
    Qyest_Button_Millie(3)
    ;
    private int id;
    private Widgets(int id){
     this.id=id;
    }
    public int getID(){
        return id;
    }
    public boolean inView(ClientContext ctx){
        return ctx.widgets.widget(id).valid();
    }
}
