package com.yoti.robohoover.processor;

import java.util.ListIterator;

import com.yoti.robohoover.model.Coordinates;
import com.yoti.robohoover.model.Solution;

public class RoboHoover {

    private RoomMap map = null;
    private Coordinates coords = null;
    private int dirtsCleaned = 0;
    private Coordinates previousPosition = null;
    
    public RoboHoover(RoomMap map, Coordinates coords) {
        this.map = map;
        this.coords = coords;
        previousPosition = new Coordinates(coords);
    }
    
    private void move(char direction) {
        
        getPreviousPosition().setLocation(coords);
        
        switch (direction) {
            case 'N':
                coords.y++;
                break;
            case 'S':
                coords.y--;
                break;
            case 'E':
                coords.x++;
                break;                
            case 'W':
                coords.x--;
                break;
            default:
                break;
        }
        
        if (coords.isValid()) {
            doClean(coords);
        } else {
            coords.setLocation(getPreviousPosition());
        }
        
    }

    public Solution clean(HooverInstructions drivingInstructions) {
        
        Solution solution = null;
        
        if (coords.isValid()) {
            doClean(coords);
        }
        
        for (ListIterator<Character> iter = drivingInstructions.listIterator();
             iter.hasNext(); ) {
            Character direction = iter.next();
            move(direction);
        }

        solution = new Solution(coords, dirtsCleaned);
        
        return solution;
    }
    
    private void doClean(Coordinates coords) {
        if (map.isDirty(coords)) {
            dirtsCleaned ++;
        }
    }

    public Coordinates getPreviousPosition() {
        return previousPosition;
    }
    public void setPreviousPosition(Coordinates previousPosition) {
        this.previousPosition = previousPosition;
    }
}