package artie.sensor.keyboardmouse.events;

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
	 * Default constructor
	 */
	public KeyboardEvent(){}
	
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

