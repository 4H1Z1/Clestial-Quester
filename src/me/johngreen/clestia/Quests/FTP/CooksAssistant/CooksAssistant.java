package me.johngreen.clestia.Quests.FTP.CooksAssistant;

import me.johngreen.clestia.Clestia;
import me.johngreen.clestia.Item;
import me.johngreen.clestia.Path;
import me.johngreen.clestia.Quests.QuestBase;
import me.johngreen.clestia.Values.Items;
import me.johngreen.clestia.Values.NPCs;
import me.johngreen.clestia.Values.Quests;
import me.johngreen.clestia.Values.Widgets;
import org.powerbot.script.Area;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by johngreen on 31/03/2015.
 */
public class CooksAssistant extends QuestBase {
    //required items: Bucket, Emptie Pot & Wheat
    private Long startTime;
    private int idleTime;

    Area kitchen = new Area(new Tile(3205,3212,0),new Tile(3211,3217,0));

    public CooksAssistant(ClientContext ctx,Clestia clestia){
        super(6, Quests.COOKS_ASSISTANT, "Cook's Assistant", ctx, clestia);
        this.startTime = System.currentTimeMillis();
        this.idleTime = clestia.getMath().randInt(1500, 4000);
    }
    @Override
    public boolean activate() {
        return true;
    }

    @Override
    public void execute() {
        if(System.currentTimeMillis()>(startTime+idleTime)) {
            this.idleTime = clestia.getMath().randInt(3500, 7000);

            if(Quests.COOKS_ASSISTANT.started(ctx)){
                //started
            }else{
                //not
                if(!Items.Pot.hasItem(ctx)){
                    setStatusMessage("Picking up pot");
                    Items.Pot.pickup(ctx);
                }else{
                    if(Widgets.NPC_Chat_Window_Widget.inView(ctx)){
                        setStatusMessage("Chatting With Cook");
                        ctx.widgets.widget(Widgets.NPC_Chat_Window_Widget.getID()).component(Widgets.NPC_Chat_Window_Widget_Button.getID()).interact("");
                    }else  if(Widgets.Player_Chat_Window_Widget.inView(ctx)){
                        setStatusMessage("Chatting With Cook");
                        ctx.widgets.widget(Widgets.Player_Chat_Window_Widget.getID()).component(Widgets.Player_Chat_Window_Widget_Button.getID()).interact("");
                    }else if(Widgets.Chat_Option_Window_Widget_Select.inView(ctx)){
                        setStatusMessage("Chatting With Cook");

                    }else{
                        setStatusMessage("Interacting with Cook");
                        NPCs.Cook.interact(ctx);
                    }
                }
            }

            this.startTime = System.currentTimeMillis();
        }

    }

    @Override
    public ArrayList<Item> getRequiredItems() {
        return null;
    }

    @Override
    public void addToPannel(JPanel panel) {
    }

    @Override
    public boolean shouldEnableStartButton() {
        return true;//!Quests.COOKS_ASSISTANT.started(ctx)&&kitchen.contains(ctx.players.local());
    }
}
