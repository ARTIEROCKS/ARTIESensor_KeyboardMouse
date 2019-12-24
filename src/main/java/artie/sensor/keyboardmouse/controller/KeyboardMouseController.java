package artie.sensor.keyboardmouse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import artie.sensor.common.dto.SensorObject;
import artie.sensor.common.interfaces.ArtieClientSensor;
import artie.sensor.keyboardmouse.services.KeyboardMouseSensorService;

@Controller
public class KeyboardMouseController implements ArtieClientSensor {
	
	@Autowired
	private KeyboardMouseSensorService keyboardSensorService;
	
	@GetMapping("/artie/sensor/keyboardmouse/getName")
	@ResponseBody
	public String getName(){
		return this.keyboardSensorService.getName();
	}
	
	@GetMapping("/artie/sensor/keyboardmouse/getVersion")
	@ResponseBody
	public String getVersion(){
		return this.keyboardSensorService.getVersion();
	}
	
	@GetMapping("/artie/sensor/keyboardmouse/getAuthor")
	@ResponseBody
	public String getAuthor(){
		return this.keyboardSensorService.getAuthor();
	}
	
	@GetMapping("/artie/sensor/keyboardmouse/getConfiguration")
	@ResponseBody
	public Map<String, String> getConfiguration(){
		return this.keyboardSensorService.getConfiguration();
	}
	
	@PostMapping(path = "/artie/sensor/keyboardmouse/configuration", consumes = "application/json")
	public void setConfiguration(@RequestBody Map<String, String> configuration){
		this.keyboardSensorService.setConfiguration(configuration);
	}

	@GetMapping("/artie/sensor/keyboardmouse/start")
	@ResponseBody
	public void start(){
		this.keyboardSensorService.start();
	}
	
	@GetMapping("/artie/sensor/keyboardmouse/stop")
	@ResponseBody
	public void stop(){
		this.keyboardSensorService.stop();
	}
	
	@GetMapping("/artie/sensor/keyboardmouse/getSensorData")
	@ResponseBody
	public List<SensorObject> getSensorData(){
		return this.keyboardSensorService.getSensorData();
	}
	
	
	@GetMapping("/artie/sensor/keyboardmouse/sendSensorData")
	@ResponseBody
	public void sendSensorData(){
		this.keyboardSensorService.sendSensorData();
	}
}
