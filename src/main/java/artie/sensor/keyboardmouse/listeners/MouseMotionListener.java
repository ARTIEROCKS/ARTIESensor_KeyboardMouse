package artie.sensor.keyboardmouse.listeners;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseMotionListener;
import org.springframework.stereotype.Component;

import artie.sensor.common.dto.SensorObject;
import artie.sensor.keyboardmouse.enums.EventEnum;
import artie.sensor.keyboardmouse.events.MouseMotionEvent;

@Component
public class MouseMotionListener implements NativeMouseMotionListener {
	
	//Attributes
	private List<SensorObject> mouseMotionEvents = new ArrayList<SensorObject>();
	
	//Properties
	public List<SensorObject> getMouseMotionEvents() {
		return mouseMotionEvents;
	}
	public void setMouseMotionEvents(List<SensorObject> mouseMotionEvents) {
		this.mouseMotionEvents = mouseMotionEvents;
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent nme) {
		Object data = new MouseMotionEvent(EventEnum.MOUSE_MOVED.toString(), nme.getX(), nme.getY());
		SensorObject sensorObject = new SensorObject(new Date(), data);
		this.mouseMotionEvents.add(sensorObject);
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent nme) {
		Object data = new MouseMotionEvent(EventEnum.MOUSE_DRAGGED.toString(), nme.getX(), nme.getY());
		SensorObject sensorObject = new SensorObject(new Date(), data);
		this.mouseMotionEvents.add(sensorObject);
	}
	
	/**
     * Register the native hook
     */
    public void RegisterNativeHook(){
        try{
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeMouseMotionListener(this);
        }catch(NativeHookException ex){
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Unregister the native hook
     */
    public void UnregisterNativeHook(){
        try{
            GlobalScreen.removeNativeMouseMotionListener(this);
            GlobalScreen.unregisterNativeHook();
        }catch(NativeHookException ex){
            System.err.println("There was a problem unregistering the native hook.");
            System.err.println(ex.getMessage());
        }   
    }
    
    /**
     * Function to clear all the events and free the memory
     */
    public void ClearEvents(){
    	this.mouseMotionEvents.clear();
    }

}
