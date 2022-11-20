package com.yoti.robohoover.model;

import java.awt.Point;

import com.yoti.robohoover.processor.HooverValidation;

public class Room extends Point implements HooverValidation {

    public Room(Point position) {
        super(position);
    }

     public Room(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isValid() {
        boolean isValid = true;
        if (x < 0 ||  y < 0) {
            isValid = false;
        }
        return isValid;
    }
}
