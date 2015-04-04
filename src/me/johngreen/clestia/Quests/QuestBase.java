package me.johngreen.clestia.Quests;

import me.johngreen.clestia.Clestia;
import me.johngreen.clestia.Item;
import me.johngreen.clestia.Task.RunTask;
import me.johngreen.clestia.Values.Quests;
import org.powerbot.script.rt6.ClientContext;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by johngreen on 30/03/2015.
 */
public abstract class QuestBase extends RunTask {
    private String name;
    private int id;
    private ArrayList<String> requiredQuests;
    private int startValue,endValue,questID;
    private Quests quest;
    public ClientContext ctx;
    public String statusMessage;
    public QuestBase(int id,Quests quest,String name,ClientContext ctx, Clestia clestia){
        super(ctx,clestia);
        this.ctx = ctx;
        this.quest = quest;
        this.id = id;
        this.name = name;
        this.requiredQuests = new ArrayList<String>();
        this.statusMessage = "Starting: "+name;
    }
    public String getName(){
        return name;
    }
    public boolean hasStarted(){
        return quest.started(ctx);
    }

    public boolean isCompleated(){
        return quest.completed(ctx);
    }

    public abstract ArrayList<Item> getRequiredItems();

    public boolean isMembersQuest(){
        return clestia.manager.getQuestDefinitionLoader().load(id).members;
    }

    public int getRequiredQuestPoints(){
        return clestia.manager.getQuestDefinitionLoader().load(id).questPointRequirement;
    }

    public Quests getQuest(){
        return quest;
    }

    public void setStatusMessage(String message){
        this.statusMessage = message;
    }

    public ArrayList<String> getRequiredQuests(){
        return requiredQuests;
    }
    public void paint(Graphics g){
        g.setColor(Color.lightGray);
        g.fillRect(10, 10, 300, 110);

        g.setColor(Color.GRAY);
        g.fillRect(15, 25, 290, 80);
        g.drawString(getName(), 10, 22);
        g.drawString("By:John Green", 10, 117);

        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Time Running:"+clestia.getMath().getTime(),15,35);
        g.drawString("Status:" + statusMessage, 15, 45);


        Point point = ctx.mouse.getLocation();
        g.setColor(Color.CYAN);
        g.drawLine(point.x - 7, point.y, point.x + 7, point.y);
        g.drawLine(point.x, point.y - 7, point.x, point.y + 7);
    }
    public abstract void addToPannel(JPanel panel);
    public abstract boolean shouldEnableStartButton();
    public int getID(){
        return id;
    }
}
