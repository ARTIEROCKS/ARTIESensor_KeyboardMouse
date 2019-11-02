package artie.sensor.keyboardmouse;

import java.util.HashMap;
import java.util.Map;

import artie.sensor.impl.ArtieClientSensorImpl;
import artie.sensor.keyboardmouse.enums.ConfigurationEnum;
import artie.sensor.keyboardmouse.listeners.KeyboardListener;
import artie.sensor.keyboardmouse.listeners.MouseListener;
import artie.sensor.keyboardmouse.listeners.MouseMotionListener;


public class KeyboardMouseSensor extends ArtieClientSensorImpl{

	//Listeners
	private KeyboardListener keyboardListener;
	private MouseListener mouseListener;
	private MouseMotionListener mouseMotionListener;
	
	private boolean keyboardListenerIsActive = false;
	private boolean mouseListenerIsActive = false;
	private boolean mouseMotionListenerIsActive = false;
	
	
	/**
	 * Constructor
	 */
	public KeyboardMouseSensor(){
		
		//Instantiaite the configuration
		this.configuration = new HashMap<String, String>();
		this.configuration.put(ConfigurationEnum.KEYBOARD_LISTENER_ACTIVE.toString(), "1");
		this.configuration.put(ConfigurationEnum.MOUSE_LISTENER_ACTIVE.toString(), "1");
		this.configuration.put(ConfigurationEnum.MOUSE_MOTION_LISTENER_ACTIVE.toString(), "1");
		
		this.keyboardListener = new KeyboardListener();
		this.mouseListener = new MouseListener();
	}
	
	/**
	 * Parameterized constructor
	 * @param configuration
	 */
	public KeyboardMouseSensor(Map<String, String> configuration){
		this.keyboardListener = new KeyboardListener();
		this.mouseListener = new MouseListener();
		this.mouseMotionListener = new MouseMotionListener();
		this.configuration = configuration;
	}
	
	@Override
	public void start() {
		
		//If the keyboard listener is active by configuration
		if(Boolean.parseBoolean(this.configuration.get(ConfigurationEnum.KEYBOARD_LISTENER_ACTIVE.toString()))){
			this.keyboardListener.RegisterNativeHook();
			this.keyboardListenerIsActive = true;
		}
		//If the mouse listener is active by configuration
		if(Boolean.parseBoolean(this.configuration.get(ConfigurationEnum.MOUSE_LISTENER_ACTIVE.toString()))){
			this.mouseListener.RegisterNativeHook();
			this.mouseListenerIsActive = true;
		}
		//If the mouse motion listener is active by configuration
		if(Boolean.parseBoolean(this.configuration.get(ConfigurationEnum.MOUSE_LISTENER_ACTIVE.toString()))){
			this.mouseMotionListener.RegisterNativeHook();
			this.mouseMotionListenerIsActive = true;
		}	
	}
	
	@Override
	public void stop(){
		
		//If the keyboard listener is active
		if(this.keyboardListenerIsActive){
			this.keyboardListener.UnregisterNativeHook();
			this.keyboardListenerIsActive = false;
		}
		//If the mouse listener is active
		if(this.mouseListenerIsActive){
			this.mouseListener.UnregisterNativeHook();
			this.mouseListenerIsActive = false;
		}
		//If the mouse motion listener is active
		if(this.mouseMotionListenerIsActive){
			this.mouseMotionListener.UnregisterNativeHook();
			this.mouseMotionListenerIsActive = false;
		}
	}

}
