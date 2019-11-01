package artie.sensor.keyboardmouse;

import artie.sensor.impl.ArtieClientSensorImpl;
import artie.sensor.keyboardmouse.listeners.KeyboardListener;
import artie.sensor.keyboardmouse.listeners.MouseListener;

public class KeyboardMouseSensor extends ArtieClientSensorImpl{

	//Listeners
	private KeyboardListener keyboardListener;
	private MouseListener mouseListener;
	
	/**
	 * Constructor
	 */
	public KeyboardMouseSensor(){
		this.keyboardListener = new KeyboardListener();
		this.mouseListener = new MouseListener();
	}
	
	@Override
	public void start() {
		this.keyboardListener.RegisterNativeHook();
		this.mouseListener.RegisterNativeHook();
	}
	
	@Override
	public void stop(){
		this.keyboardListener.UnregisterNativeHook();
		this.mouseListener.UnregisterNativeHook();
	}

}
