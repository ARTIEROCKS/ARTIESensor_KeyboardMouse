package artie.sensor.keyboardmouse.events;

public class MouseWheelEvent {
	
	//Attributes
	private String mouseWheelEvent;
	private int scrollAmount;
	private int wheelRotation;
	private int scrollType;
	
	//Properties
	public String getMouseWheelEvent() {
		return mouseWheelEvent;
	}
	public void setMouseWheelEvent(String mouseWheelEvent) {
		this.mouseWheelEvent = mouseWheelEvent;
	}
	
	public int getScrollAmount() {
		return scrollAmount;
	}
	public void setScrollAmount(int scrollAmount) {
		this.scrollAmount = scrollAmount;
	}
	
	public int getWheelRotation() {
		return wheelRotation;
	}
	public void setWheelRotation(int wheelRotation) {
		this.wheelRotation = wheelRotation;
	}
	
	public int getScrollType() {
		return scrollType;
	}
	public void setScrollType(int scrollType) {
		this.scrollType = scrollType;
	}
	
	/**
	 * Default constructor
	 */
	public MouseWheelEvent(){
	}
	
	/**
	 * Parameterized constructor
	 * @param mouseWheelEvent
	 * @param scrollAmount
	 * @param wheelRotation
	 * @param scrollType
	 */
	public MouseWheelEvent(String mouseWheelEvent, int scrollAmount, int wheelRotation, int scrollType){
		this.mouseWheelEvent = mouseWheelEvent;
		this.scrollAmount = scrollAmount;
		this.wheelRotation = wheelRotation;
		this.scrollType = scrollType;
	}
}
