package com.yoti.robohoover.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yoti.robohoover.model.HooverInputModel;
import com.yoti.robohoover.model.HvooerOutpoutModel;
import com.yoti.robohoover.model.Solution;
import com.yoti.robohoover.processor.HooverProcessor;
import com.yoti.robohoover.processor.RoboHoover;

@RestController
public class HooverController {
	private static final Logger LOGGER = LogManager.getLogger(HooverController.class);
	 
    @Autowired
    private HooverProcessor hvProcessor;
    
    @RequestMapping(value = "/clean", method = RequestMethod.POST)
    @ResponseBody
    public HvooerOutpoutModel cleanPatches(@RequestBody HooverInputModel input) throws Exception{
    	LOGGER.info("Inside cleanPatches Request is :"+input.toString());
    	getHvProcessor().processRequest(input);
        
        RoboHoover roboticHoover = new RoboHoover(getHvProcessor().getRoomMap(), getHvProcessor().getPosition());
        Solution solution = roboticHoover.clean(getHvProcessor().getHooveringInstructions());
        return new HvooerOutpoutModel(solution);
    }

	public HooverProcessor getHvProcessor() {
		return hvProcessor;
	}

	public void setHvProcessor(HooverProcessor hvProcessor) {
		this.hvProcessor = hvProcessor;
	}

}
