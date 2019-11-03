package artie.sensor.keyboardmouse;

import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import artie.sensor.common.dto.SensorObject;
import artie.sensor.common.services.ArtieClientSensorImpl;
import artie.sensor.keyboardmouse.enums.ConfigurationEnum;
import artie.sensor.keyboardmouse.listeners.KeyboardListener;
import artie.sensor.keyboardmouse.listeners.MouseListener;
import artie.sensor.keyboardmouse.listeners.MouseMotionListener;
import artie.sensor.keyboardmouse.listeners.MouseWheelListener;

@Service
public class KeyboardMouseSensor extends ArtieClientSensorImpl{

	//Listeners
	@Autowired
	private KeyboardListener keyboardListener;
	@Autowired
	private MouseListener mouseListener;
	@Autowired
	private MouseMotionListener mouseMotionListener;
	@Autowired
	private MouseWheelListener mouseWheelListener;
	
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
		
		//If we want to write a JSON file with the data
		if(Boolean.parseBoolean(this.configuration.get(artie.sensor.common.enums.ConfigurationEnum.SENSOR_FILE_REGISTRATION.toString()))){
			this.writeDataToFile();
		}
		
		return this.sensorData;
	}
	
	/**
	 * Constructor
	 */
	@PostConstruct
	public void init(){
		
		this.SensorInformation();
		
		//Instantiate the configuration
		this.configuration.putIfAbsent(ConfigurationEnum.KEYBOARD_LISTENER_ACTIVE.toString(), "true");
		this.configuration.putIfAbsent(ConfigurationEnum.MOUSE_LISTENER_ACTIVE.toString(), "true");
		this.configuration.putIfAbsent(ConfigurationEnum.MOUSE_MOTION_LISTENER_ACTIVE.toString(), "true");
		this.configuration.putIfAbsent(ConfigurationEnum.MOUSE_WHEEL_LISTENER_ACTIVE.toString(), "true");
		
		//Replacing the already existing configuration
	 	this.configuration.replace(artie.sensor.common.enums.ConfigurationEnum.SENSOR_FILE_FILENAME.toString(), "KeyboardMouseSensor.log");
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
