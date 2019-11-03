package artie.sensor.keyboardmouse.events;

public class MouseMotionEvent {
	
	//Attributes
	private String mouseMotionEvent;
	private int x;
	private int y;
	
	//Properties
	public String getMouseMotionEvent() {
		return mouseMotionEvent;
	}
	public void setMouseMotionEvent(String mouseMotionEvent) {
		this.mouseMotionEvent = mouseMotionEvent;
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
	public MouseMotionEvent(){
	}
	
	/**
	 * Parameterized constructor
	 * @param mouseMotionEvent
	 * @param x
	 * @param y
	 */
	public MouseMotionEvent(String mouseMotionEvent, int x, int y){
		this.mouseMotionEvent = mouseMotionEvent;
		this.x = x;
		this.y = y;
	}

}
