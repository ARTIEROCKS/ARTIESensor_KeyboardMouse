package artie.sensor.keyboardmouse.events;

public class MouseEvent {
	
	//Attributes
	private String mouseEvent;
	private int buttonNumber;
	private int clicks;
	private int x;
	private int y;
	
	//Properties
	public String getMouseEvent() {
		return mouseEvent;
	}
	public void setMouseEvent(String mouseEvent) {
		this.mouseEvent = mouseEvent;
	}
	public int getButtonNumber() {
		return buttonNumber;
	}
	public void setButtonNumber(int buttonNumber) {
		this.buttonNumber = buttonNumber;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
	/**
	 * Default constructor
	 */
	public MouseEvent(){
		
	}
	
	/**
	 * Parameterized constructor
	 * @param mouseEvent
	 * @param buttonNumber
	 * @param clicks
	 * @param x
	 * @param y
	 */
	public MouseEvent(String mouseEvent, int buttonNumber, int clicks, int x, int y) {
		this.mouseEvent = mouseEvent;
		this.buttonNumber = buttonNumber;
		this.clicks = clicks;
		this.x = x;
		this.y = y;
	}
}
