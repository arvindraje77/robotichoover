package com.yoti.robohoover.processor;

import java.awt.Point;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.yoti.robohoover.model.Coordinates;
import com.yoti.robohoover.model.HooverInputModel;
import com.yoti.robohoover.model.Room;

@Component
public class HooverProcessor {
    private static final Logger LOGGER = LogManager.getLogger(HooverProcessor.class);

    private Room room = null;
    private Coordinates coords = null;
    private RoomMap roomMap = null;
    private HooverInstructions hooverInstructions = null;
    
    public void processRequest(HooverInputModel request) throws Exception{
        LOGGER.debug("Processing request", request);
        room = processRoom(request.getRoomSize());
        coords = verifyPoint(request.getCoords());
        roomMap = new RoomMap(room);
        processDirtPatches(request.getPatches(), roomMap);
        processHooveringInstructions(request.getInstructions());
    }

    private Coordinates verifyPoint(List<Integer> pointList) throws Exception {
        Point point = null;
        
        if (pointList.size() == 2) {
            try {
                int x = pointList.get(0);  
                int y = pointList.get(1);
            
                if (x < 0 || y < 0 ) {
                    throw new Exception("Invalid coordinates");
                }
                    
                point = new Point(x, y);
            } catch (Exception e) {
            	LOGGER.error("Error in validating coordinates ",e);
                throw new Exception(e.getMessage());
            }
        } else {
            throw new Exception("coordinates positions are not matching");
        }
        return new Coordinates(point, room);
    }
   
    public Point processPosition(List<Integer> coords) throws Exception {
        LOGGER.debug("Process position");
        Point position = null;
        try {
            position = verifyPoint(coords);
        } catch (Exception ex) {
            LOGGER.error("The coordinates are not positive numbers", ex);
            throw new Exception("The coordinates are not positive numbers", ex);
        }
        return position;
    }
    
    public Room processRoom(List<Integer> roomSize) throws Exception {
        LOGGER.debug("Processing roomsize");
        Point dimensionsPoint = null;
        try {
            dimensionsPoint = verifyPoint(roomSize);
        } catch (Exception ex) {
            LOGGER.error("Invalid coordinates", ex);
            throw new Exception("Invalid coordinates",ex);
        }
        return new Room(dimensionsPoint);
    }
    
    public void processDirtPatches(List<List<Integer>> patches, RoomMap roomMap) throws Exception {
        LOGGER.debug("Process applying patches");
        Point patchPoint = null;
        try {
            for(int i = 0; i < patches.size(); i ++){
                List<Integer> patch = patches.get(i);
                patchPoint = verifyPoint(patch);
                roomMap.applyDirtPatch(patchPoint);
            }
        } catch (Exception ex) {
            LOGGER.error("Invalid number of dirt coordinates", ex);
            throw new Exception("Invalid number of dirt coordinates",ex);
        }
    }

    public void processHooveringInstructions(String instructions) throws Exception {
        LOGGER.debug("Processing hoovering instructions",instructions);

        hooverInstructions = new HooverInstructions(instructions);
        if (!hooverInstructions.isValid()) {
            LOGGER.error("Invalid hovering instructions");
            throw new Exception();
        }
    }
    
    public Room getRoom() {
        return room;
    }
    
    public Coordinates getPosition() {
        return coords;
    }

    public RoomMap getRoomMap() {
        return roomMap;
    }
    
    public HooverInstructions getHooveringInstructions() {
        return hooverInstructions;
    }
}
