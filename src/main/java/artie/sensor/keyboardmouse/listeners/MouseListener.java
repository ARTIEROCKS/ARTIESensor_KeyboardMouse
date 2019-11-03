package artie.sensor.keyboardmouse.listeners;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;
import org.springframework.stereotype.Component;

import artie.sensor.common.dto.SensorObject;
import artie.sensor.keyboardmouse.enums.EventEnum;
import artie.sensor.keyboardmouse.events.MouseEvent;

@Component
public class MouseListener implements NativeMouseListener {
	
	//Attributes
	private List<SensorObject> mouseEvents = new ArrayList<SensorObject>();

	//Properties
	public List<SensorObject> getMouseEvents() {
		return mouseEvents;
	}
	public void setMouseEvents(List<SensorObject> mouseEvents) {
		this.mouseEvents = mouseEvents;
	}
	

	@Override
	public void nativeMouseClicked(NativeMouseEvent nme) {
		Object data = new MouseEvent(EventEnum.KEY_PRESSED.toString(), nme.getButton(), nme.getClickCount(), nme.getX(), nme.getY());
		SensorObject sensorObject = new SensorObject(new Date(), data);
		this.mouseEvents.add(sensorObject);
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent nme) {
		Object data = new MouseEvent(EventEnum.KEY_PRESSED.toString(), nme.getButton(), nme.getClickCount(), nme.getX(), nme.getY());
		SensorObject sensorObject = new SensorObject(new Date(), data);
		this.mouseEvents.add(sensorObject);
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent nme) {
		Object data = new MouseEvent(EventEnum.KEY_PRESSED.toString(), nme.getButton(), nme.getClickCount(), nme.getX(), nme.getY());
		SensorObject sensorObject = new SensorObject(new Date(), data);
		this.mouseEvents.add(sensorObject);
	}

	/**
     * Register the native hook
     */
    public void RegisterNativeHook(){
        try{
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeMouseListener(this);
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
            GlobalScreen.removeNativeMouseListener(this);
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
    	this.mouseEvents.clear();
    }
	
}
