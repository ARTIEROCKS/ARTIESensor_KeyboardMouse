package artie.sensor.keyboardmouse.events;

import java.util.Date;

import artie.common.enums.EventEnum;
import artie.sensor.SensorObject;

/**
 * @author Luis Eduardo Imbernon Cuadrado
 */
public class KeyboardEvent {
    
	//Attributes
	private String keyEvent;
    private int keyCode;
    
    //Properties
	public String getKeyEvent() {
		return keyEvent;
	}
	public void setKeyEvent(String keyEvent) {
		this.keyEvent = keyEvent;
	}
	public int getKeyCode() {
		return keyCode;
	}
	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}
    
	/**
	 * Parameterized constructor
	 * @param keyEvent
	 * @param keyCode
	 */
	public KeyboardEvent(String keyEvent, int keyCode){
		this.keyEvent = keyEvent;
		this.keyCode = keyCode;
	}
	
    
}

