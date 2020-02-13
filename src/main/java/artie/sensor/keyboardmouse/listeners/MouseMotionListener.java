package artie.sensor.keyboardmouse.listeners;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseMotionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import artie.sensor.common.dto.SensorObject;
import artie.sensor.keyboardmouse.enums.EventEnum;
import artie.sensor.keyboardmouse.events.MouseMotionEvent;

@Component
public class MouseMotionListener implements NativeMouseMotionListener {
	
	//Attributes
	private List<SensorObject> mouseMotionEvents = new ArrayList<SensorObject>();
	private Logger logger = LoggerFactory.getLogger(MouseMotionListener.class);
	
	@Value("${artie.sensor.keyboardmouse.listener.datalimit}")
	private long dataLimit;
	
	//Properties
	public List<SensorObject> getMouseMotionEvents() {
		return mouseMotionEvents;
	}
	public SensorObject getMouseMotionEvent(int element) {
		return this.mouseMotionEvents.get(element);
	}
	public void setMouseMotionEvents(List<SensorObject> mouseMotionEvents) {
		this.mouseMotionEvents = mouseMotionEvents;
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent nme) {
		Object data = new MouseMotionEvent(EventEnum.MOUSE_MOVED.toString(), nme.getX(), nme.getY());
		SensorObject sensorObject = new SensorObject(new Date(), data, "keyboardmouse");
		if(this.mouseMotionEvents.size() >= dataLimit) {
			this.mouseMotionEvents.remove(0);
		}
		this.mouseMotionEvents.add(sensorObject);
		System.out.println("mouseMotionEvents: " + this.mouseMotionEvents.size());
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent nme) {
		Object data = new MouseMotionEvent(EventEnum.MOUSE_DRAGGED.toString(), nme.getX(), nme.getY());
		SensorObject sensorObject = new SensorObject(new Date(), data, "keyboardmouse");
		if(this.mouseMotionEvents.size() >= dataLimit) {
			this.mouseMotionEvents.remove(0);
		}
		this.mouseMotionEvents.add(sensorObject);
		System.out.println("mouseMotionEvents: " + this.mouseMotionEvents.size());
	}
	
	/**
     * Register the native hook
     */
    public void registerNativeHook(){
        try{
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeMouseMotionListener(this);
        }catch(NativeHookException ex){
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            this.logger.error(ex.getMessage());
        }
    }
    
    /**
     * Unregister the native hook
     */
    public void unregisterNativeHook(){
        try{
            GlobalScreen.removeNativeMouseMotionListener(this);
            GlobalScreen.unregisterNativeHook();
        }catch(NativeHookException ex){
            System.err.println("There was a problem unregistering the native hook.");
            System.err.println(ex.getMessage());
            this.logger.error(ex.getMessage());
        }   
    }
    
    /**
     * Function to clear all the events and free the memory
     */
    public void clearEvents(){
    	this.mouseMotionEvents.clear();
    }

}
