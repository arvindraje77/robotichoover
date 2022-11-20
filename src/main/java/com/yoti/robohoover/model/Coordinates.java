package com.yoti.robohoover.model;

import java.awt.Point;

import com.yoti.robohoover.processor.HooverValidation;


public class Coordinates extends Point implements HooverValidation {

    private Room dimensions = null;

    public Coordinates(Coordinates position) {
        super(position);
    }
    
    public Coordinates(int x, int y, Room dimensions) {
        super(x, y);
        this.dimensions = dimensions;
    }

    public Coordinates(Point point, Room dimensions) {
        this(point.x, point.y, dimensions);
    }

    @Override
    public boolean isValid() {
        boolean isValid = false;
        
        if (dimensions != null && 
            x >= 0 && x < dimensions.x &&
            y>= 0 && y < dimensions.y) {
            return true;
        }
        return isValid;
    }
    
}
