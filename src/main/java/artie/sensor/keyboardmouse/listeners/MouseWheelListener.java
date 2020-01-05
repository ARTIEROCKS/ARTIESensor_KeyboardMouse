package artie.sensor.keyboardmouse.listeners;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import artie.sensor.common.dto.SensorObject;
import artie.sensor.keyboardmouse.enums.EventEnum;
import artie.sensor.keyboardmouse.events.MouseWheelEvent;

@Component
public class MouseWheelListener implements NativeMouseWheelListener {

	//Attributes
	private List<SensorObject> mouseWheelEvents = new ArrayList<SensorObject>();
	private Logger logger = LoggerFactory.getLogger(MouseWheelListener.class);
	
	//Properties
	public List<SensorObject> getMouseWheelEvents() {
		return mouseWheelEvents;
	}
	public SensorObject getMouseWheelEvent(int element) {
		return this.mouseWheelEvents.get(element);
	}
	public void setMouseWheelEvents(List<SensorObject> mouseWheelEvents) {
		this.mouseWheelEvents = mouseWheelEvents;
	}

	@Override
	public void nativeMouseWheelMoved(NativeMouseWheelEvent nmwe) {
		Object data = new MouseWheelEvent(EventEnum.MOUSE_WHEEL.toString(), nmwe.getScrollAmount(), nmwe.getWheelRotation(), nmwe.getScrollType());
		SensorObject sensorObject = new SensorObject(new Date(), data, "keyboardmouse");
		this.mouseWheelEvents.add(sensorObject);
	}
	
	
	/**
     * Register the native hook
     */
    public void registerNativeHook(){
        try{
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeMouseWheelListener(this);
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
            GlobalScreen.removeNativeMouseWheelListener(this);
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
    	this.mouseWheelEvents.clear();
    }

}
