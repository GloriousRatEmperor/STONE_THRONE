package Multiplayer;

import org.joml.Vector2f;

import java.util.List;

public class ServerData {
    public int intValue;
    public float time;
    public String name;
    public int getIntValue(){
        return intValue;
    }
    public String getName(){
        return name;
    }
    public void setIntValue(int newval){
        intValue=newval;
    }
    public void setName(String newval){ name =newval;}
    public List<Integer> GameObjects;
    public List<Float> position;

    public List<Integer> getGameObjects() {
        return GameObjects;
    }
    public void setGameObjects(List<Integer> newval) {
        GameObjects=newval;
    }
    public void setPos(List<Float> pos){position=pos;}
    public List<Float> getPos(){return position;}
}
