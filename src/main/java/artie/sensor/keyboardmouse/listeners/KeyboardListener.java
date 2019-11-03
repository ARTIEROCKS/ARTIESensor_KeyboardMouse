package artie.sensor.keyboardmouse.listeners;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.springframework.stereotype.Component;

import artie.sensor.SensorObject;
import artie.sensor.keyboardmouse.enums.EventEnum;
import artie.sensor.keyboardmouse.events.KeyboardEvent;

@Component
public class KeyboardListener implements NativeKeyListener {
	
	//Attributes
	private List<SensorObject> keyboardEvents = new ArrayList<SensorObject>();

	//Properties
	public List<SensorObject> getKeyboardEvents(){
		return this.keyboardEvents;
	}
	public void setKeyboardEvents(List<SensorObject> keyboardEvents){
		this.keyboardEvents = keyboardEvents;
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent nke) {
		Object data = new KeyboardEvent(EventEnum.KEY_PRESSED.toString(), nke.getKeyCode());
		SensorObject sensorObject = new SensorObject(new Date(), data);
		this.keyboardEvents.add(sensorObject);
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent nke) {
		Object data = new KeyboardEvent(EventEnum.KEY_RELEASED.toString(), nke.getKeyCode());
		SensorObject sensorObject = new SensorObject(new Date(), data);
		this.keyboardEvents.add(sensorObject);
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent nke) {
		//Key Typed and key pressed and released are more or less the same		
	}
	
	/**
     * Function to register the native hook
     */
    public void RegisterNativeHook(){
        
        try{
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(this);
        }catch(NativeHookException ex){
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Function to delete the listener and unregister the native hook
     */
    public void UnregisterNativeHook(){
        
        try{
            GlobalScreen.removeNativeKeyListener(this);
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
    	this.keyboardEvents.clear();
    }
}
