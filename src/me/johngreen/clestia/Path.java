package me.johngreen.clestia;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by johngreen on 02/04/15.
 */
public class Path {

    ArrayList<Area> path;
    ClientContext ctx;
    int tick;
    Random r;

    public Path(ClientContext ctx){
        this.ctx = ctx;
        this.path = new ArrayList<Area>();
        this.tick = 0;
        this.r = new Random();
    }

    public boolean isDone(){
        return path.get(path.size()).contains(ctx.players.local());
    }

    public void addArea(Area a){
        path.add(a);
    }

    private boolean isWalking(){
        return ctx.players.local().inMotion();
    }

    private void next(){
        tick++;
    }

    public void tick(){
        if(!isDone()){
            if(!isWalking()){
                if(path.get(tick).contains(ctx.players.local())){
                    if(path.get(tick+1)!=null){
                        next();
                        Tile t = path.get(tick).getRandomTile();
                        ctx.movement.step(t);
                        if(r.nextInt(4)==0){
                            ctx.camera.turnTo(t);
                        }
                    }else{
                        //Done but has not finished?
                        System.out.println("[Path Finding] Should have finished but has not");
                    }
                }else{
                    //has not made it to area
                }
            }
        }
    }

}
