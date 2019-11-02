package artie.sensor.keyboardmouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import artie.sensor.SensorObject;
import artie.sensor.impl.ArtieClientSensorImpl;
import artie.sensor.keyboardmouse.enums.ConfigurationEnum;
import artie.sensor.keyboardmouse.listeners.KeyboardListener;
import artie.sensor.keyboardmouse.listeners.MouseListener;
import artie.sensor.keyboardmouse.listeners.MouseMotionListener;
import artie.sensor.keyboardmouse.listeners.MouseWheelListener;


public class KeyboardMouseSensor extends ArtieClientSensorImpl{

	//Listeners
	private KeyboardListener keyboardListener = new KeyboardListener();
	private MouseListener mouseListener = new MouseListener();
	private MouseMotionListener mouseMotionListener= new MouseMotionListener();
	private MouseWheelListener mouseWheelListener= new MouseWheelListener();
	
	private boolean keyboardListenerIsActive = false;
	private boolean mouseListenerIsActive = false;
	private boolean mouseMotionListenerIsActive = false;
	private boolean mouseWheelListenerIsActive = false;
	
	/**
	 * About the sensor information
	 */
	private void SensorInformation(){
		this.name = "Keyboard and Mouse Listener";
		this.version = "0.0.1";
		this.author = "Luis-Eduardo Imbernón";
	}
	
	/**
	 * Getting the sensor data from the listeners
	 * @return
	 */
	public List<SensorObject> getSensorData(){
		
		//Cleaning all the information stored
		this.sensorData.clear();
		
		//Getting the information from the keyboard listener
		if(keyboardListenerIsActive){
			this.keyboardListener.getKeyboardEvents().forEach(event->this.addSensorObject(event));
			this.keyboardListener.ClearEvents();
		}
		//Getting the information from the mouse listener
		if(mouseListenerIsActive){
			this.mouseListener.getMouseEvents().forEach(event->this.addSensorObject(event));
			this.mouseListener.ClearEvents();
		}
		//Getting the information from the mouse motion listener
		if(mouseMotionListenerIsActive){
			this.mouseMotionListener.getMouseMotionEvents().forEach(event->this.addSensorObject(event));
			this.mouseMotionListener.ClearEvents();
		}
		//Getting the information from the mouse wheel listener
		if(mouseWheelListenerIsActive){
			this.mouseWheelListener.getMouseWheelEvents().forEach(event->this.addSensorObject(event));
			this.mouseMotionListener.ClearEvents();
		}
		
		return this.sensorData;
	}
	
	/**
	 * Constructor
	 */
	public KeyboardMouseSensor(){
		
		this.SensorInformation();
		
		//Instantiate the configuration
		this.configuration = new HashMap<String, String>();
		this.configuration.put(ConfigurationEnum.KEYBOARD_LISTENER_ACTIVE.toString(), "1");
		this.configuration.put(ConfigurationEnum.MOUSE_LISTENER_ACTIVE.toString(), "1");
		this.configuration.put(ConfigurationEnum.MOUSE_MOTION_LISTENER_ACTIVE.toString(), "1");
		this.configuration.put(ConfigurationEnum.MOUSE_WHEEL_LISTENER_ACTIVE.toString(), "1");
	}
	
	/**
	 * Parameterized constructor
	 * @param configuration
	 */
	public KeyboardMouseSensor(Map<String, String> configuration){
		this.SensorInformation();
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
		//If the mouse wheel listener is active by configuration
		if(Boolean.parseBoolean(this.configuration.get(ConfigurationEnum.MOUSE_WHEEL_LISTENER_ACTIVE.toString()))){
			this.mouseWheelListener.RegisterNativeHook();
			this.mouseWheelListenerIsActive = true;
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
		//If the mouse wheel listener is active
		if(this.mouseWheelListenerIsActive){
			this.mouseWheelListener.UnregisterNativeHook();
			this.mouseWheelListenerIsActive = false;
		}
	}

}
