package artie.sensor.keyboardmouse.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import artie.sensor.keyboardmouse.KeyboardMouseSensor;

@Service
public class KeyboardMouseSensorService {
	
	@Autowired
	private KeyboardMouseSensor keyboardMouseSensor;
	
	@Scheduled(fixedRateString="${artie.sensor.keyboardmouse.rate}")
	public void getData(){
		this.keyboardMouseSensor.getSensorData();
	}
	
	@PostConstruct
	public void init(){
		this.keyboardMouseSensor.replaceConfiguration(artie.sensor.common.enums.ConfigurationEnum.SENSOR_FILE_REGISTRATION.toString(), "true");
		this.keyboardMouseSensor.start();
	}
}
