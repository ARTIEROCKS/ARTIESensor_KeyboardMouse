package artie.sensor.keyboardmouse;

import artie.sensor.impl.ArtieClientSensorImpl;
import artie.sensor.keyboardmouse.listeners.KeyboardListener;

public class KeyboardMouseSensor extends ArtieClientSensorImpl{

	//Listeners
	private KeyboardListener keyboardListener;
	
	/**
	 * Constructor
	 */
	public KeyboardMouseSensor(){
		this.keyboardListener = new KeyboardListener();
	}
	
	@Override
	public void start() {
		this.keyboardListener.RegisterNativeHook();
	}
	
	@Override
	public void stop(){
		this.keyboardListener.UnregisterNativeHook();
	}

}
