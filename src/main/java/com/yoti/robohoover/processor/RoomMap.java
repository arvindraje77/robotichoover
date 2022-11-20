package com.yoti.robohoover.processor;

import java.awt.Point;

import com.yoti.robohoover.model.Coordinates;
import com.yoti.robohoover.model.Room;

public class RoomMap implements HooverValidation {

    private PositionOnMap[][] map;
    private Room roomSize = null;
    
    public RoomMap(Room roomSize) {
        this.roomSize = roomSize;
        map = new PositionOnMap[roomSize.x][roomSize.y];
        
        for (int x = 0; x < roomSize.x; x ++) {
            for (int y = 0; y < roomSize.y; y++) {
                map[x][y] = PositionOnMap.CLEAN_POS;
            }
        }
    }
    
    public void applyDirtPatch(Point point)   throws Exception {
        applyDirtPatch(point.x, point.y);
    }
    
    public void applyDirtPatch(int x, int y) throws Exception {
        if (x >= roomSize.x ||
            y >= roomSize.y) {
            throw new Exception();
        }
        map[x][y] = PositionOnMap.DIRTY_POS;
    }
 
    public boolean isDirty(Coordinates coords) {
        boolean isDirty = false;
        
        if (map[coords.x][coords.y] == PositionOnMap.DIRTY_POS) {
            map[coords.x][coords.y] = PositionOnMap.CLEAN_POS;
            isDirty = true;
        }
        return isDirty;
    }
    
    public boolean isDirty(int x, int y) {
        boolean isDirty = false;
        if (map[x][y] == PositionOnMap.DIRTY_POS) {
            isDirty = true;
        }
        return isDirty;
    }
    
    @Override
    public boolean isValid() {
        return roomSize.isValid();
    }
    
    private enum PositionOnMap {
        CLEAN_POS, DIRTY_POS
    }
    
}

