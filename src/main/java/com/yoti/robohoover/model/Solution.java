package com.yoti.robohoover.model;

public class Solution {

    private Coordinates coords= null;
    private int dirtsCleaned = 0;
    
    public Solution(Coordinates coords, int dirtsCleaned) {
        this.coords = coords;
        this.dirtsCleaned = dirtsCleaned;
    }

	public Coordinates getCoords() {
		return coords;
	}

	public void setCoords(Coordinates coords) {
		this.coords = coords;
	}

	public int getDirtsCleaned() {
		return dirtsCleaned;
	}

	public void setDirtsCleaned(int dirtsCleaned) {
		this.dirtsCleaned = dirtsCleaned;
	}
   
    
}
