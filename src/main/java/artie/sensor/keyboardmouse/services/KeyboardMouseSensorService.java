package artie.sensor.keyboardmouse.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import artie.sensor.common.dto.SensorObject;
import artie.sensor.common.services.ArtieClientSensorImpl;
import artie.sensor.keyboardmouse.enums.ConfigurationEnum;
import artie.sensor.keyboardmouse.listeners.KeyboardListener;
import artie.sensor.keyboardmouse.listeners.MouseListener;
import artie.sensor.keyboardmouse.listeners.MouseMotionListener;
import artie.sensor.keyboardmouse.listeners.MouseWheelListener;

@Service
public class KeyboardMouseSensorService extends ArtieClientSensorImpl{

	//Configuration
	@Value("${artie.sensor.keyboardmouse.file.name}")
	private String fileName;
	
	@Value("${artie.sensor.keyboardmouse.file.registration}")
	private String fileRegistration;
	
	@Value("${artie.sensor.keyboardmouse.listener.keyboard.active}")
	private String listenerKeyboardActive;
	
	@Value("${artie.sensor.keyboardmouse.listener.mouse.active}")
	private String listenerMouseActive;
	
	@Value("${artie.sensor.keyboardmouse.listener.mouse-motion.active}")
	private String listenerMouseMotionActive;
	
	@Value("${artie.sensor.keyboardmouse.listener.mouse-wheel.active}")
	private String listenerMouseWheelActive;
	
	@Value("${artie.sensor.keyboardmouse.name}")
	private String paramName;
	
	@Value("${artie.sensor.keyboardmouse.version}")
	private String paramVersion;
	
	@Value("${artie.sensor.keyboardmouse.author}")
	private String paramAuthor;
	
	
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
	private void sensorInformation(){
		this.name = this.paramName;
		this.version = this.paramVersion;
		this.author = this.paramVersion;
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
			
			int elements = this.keyboardListener.getKeyboardEvents().size();
			for(int i=0; i<elements; i++) {
				this.addSensorObject(this.keyboardListener.getKeyboardEvent(i));
			}
			
			this.keyboardListener.clearEvents();
		}
		//Getting the information from the mouse listener
		if(mouseListenerIsActive){
			
			int elements = this.mouseListener.getMouseEvents().size();
			for(int i=0; i<elements; i++) {
				this.addSensorObject(this.mouseListener.getMouseEvent(i));
			}
			
			this.mouseListener.clearEvents();
		}
		//Getting the information from the mouse motion listener
		if(mouseMotionListenerIsActive){
			
			int elements = this.mouseMotionListener.getMouseMotionEvents().size();
			for(int i=0; i<elements; i++) {
				this.addSensorObject(this.mouseMotionListener.getMouseMotionEvent(i));
			}
			
			this.mouseMotionListener.clearEvents();
		}
		//Getting the information from the mouse wheel listener
		if(mouseWheelListenerIsActive){
			
			int elements = this.mouseWheelListener.getMouseWheelEvents().size();
			for(int i=0; i<elements; i++) {
				this.addSensorObject(this.mouseWheelListener.getMouseWheelEvent(i));
			}
			
			this.mouseWheelListener.clearEvents();
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
		
		this.sensorInformation();
		
		//Instantiate the configuration
		this.configuration.putIfAbsent(ConfigurationEnum.KEYBOARD_LISTENER_ACTIVE.toString(), this.listenerKeyboardActive);
		this.configuration.putIfAbsent(ConfigurationEnum.MOUSE_LISTENER_ACTIVE.toString(), this.listenerMouseActive);
		this.configuration.putIfAbsent(ConfigurationEnum.MOUSE_MOTION_LISTENER_ACTIVE.toString(), this.listenerMouseMotionActive);
		this.configuration.putIfAbsent(ConfigurationEnum.MOUSE_WHEEL_LISTENER_ACTIVE.toString(), this.listenerMouseWheelActive);
		
		//Replacing the already existing configuration
	 	this.configuration.replace(artie.sensor.common.enums.ConfigurationEnum.SENSOR_FILE_FILENAME.toString(), this.fileName);
	 	
	 	this.isAlive = true;
	}
	
	
	@Override
	public void start() {
		
		//If the keyboard listener is active by configuration
		if(Boolean.parseBoolean(this.configuration.get(ConfigurationEnum.KEYBOARD_LISTENER_ACTIVE.toString()))){
			this.keyboardListener.registerNativeHook();
			this.keyboardListenerIsActive = true;
		}
		//If the mouse listener is active by configuration
		if(Boolean.parseBoolean(this.configuration.get(ConfigurationEnum.MOUSE_LISTENER_ACTIVE.toString()))){
			this.mouseListener.registerNativeHook();
			this.mouseListenerIsActive = true;
		}
		//If the mouse motion listener is active by configuration
		if(Boolean.parseBoolean(this.configuration.get(ConfigurationEnum.MOUSE_LISTENER_ACTIVE.toString()))){
			this.mouseMotionListener.registerNativeHook();
			this.mouseMotionListenerIsActive = true;
		}
		//If the mouse wheel listener is active by configuration
		if(Boolean.parseBoolean(this.configuration.get(ConfigurationEnum.MOUSE_WHEEL_LISTENER_ACTIVE.toString()))){
			this.mouseWheelListener.registerNativeHook();
			this.mouseWheelListenerIsActive = true;
		}
	}
	
	@Override
	public void stop(){
		
		//If the keyboard listener is active
		if(this.keyboardListenerIsActive){
			this.keyboardListener.unregisterNativeHook();
			this.keyboardListenerIsActive = false;
		}
		//If the mouse listener is active
		if(this.mouseListenerIsActive){
			this.mouseListener.unregisterNativeHook();
			this.mouseListenerIsActive = false;
		}
		//If the mouse motion listener is active
		if(this.mouseMotionListenerIsActive){
			this.mouseMotionListener.unregisterNativeHook();
			this.mouseMotionListenerIsActive = false;
		}
		//If the mouse wheel listener is active
		if(this.mouseWheelListenerIsActive){
			this.mouseWheelListener.unregisterNativeHook();
			this.mouseWheelListenerIsActive = false;
		}
	}
	
}
