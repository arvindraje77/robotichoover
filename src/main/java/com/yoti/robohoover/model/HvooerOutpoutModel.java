package com.yoti.robohoover.model;

public class HvooerOutpoutModel {
    
    private int [] coords ;
    private int patches ;
    
    public HvooerOutpoutModel(Solution solution){
        int x = (int) solution.getCoords().getX();
        int y = (int) solution.getCoords().getY();
        coords = new int[]{x,y};
        patches = solution.getDirtsCleaned();
    }
    public int[] getCoords() {
        return coords;
    }
	public int getPatches() {
		return patches;
	}
	public void setPatches(int patches) {
		this.patches = patches;
	}
	public void setCoords(int[] coords) {
		this.coords = coords;
	}

}
