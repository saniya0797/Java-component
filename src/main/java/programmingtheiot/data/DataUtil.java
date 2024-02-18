/**
 * This class is part of the Programming the Internet of Things project.
 * 
 * It is provided as a simple shell to guide the student and assist with
 * implementation for the Programming the Internet of Things exercises,
 * and designed to be modified by the student as needed.
 */ 

package programmingtheiot.data;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;

import com.google.gson.Gson;
import java.util.logging.Logger;
import programmingtheiot.common.ConfigConst;

/**
 * Shell representation of class for student implementation.
 *
 */
public class DataUtil
{
	// static
	private static final Logger _Logger =Logger.getLogger(DataUtil.class.getName());
	private static final DataUtil _Instance = new DataUtil();
	

	/**
	 * Returns the Singleton instance of this class.
	 * 
	 * @return ConfigUtil
	 */
	public static final DataUtil getInstance()
	{
		return _Instance;
	}
	
	
	// private var's
	
	
	// constructors
	
	/**
     * Default constructor (private).
     */
	private DataUtil()
	{
		super();
	}
	
	
	// public methods
	/**
     * Converts an ActuatorData object to JSON format.
     *
     * @param actuatorData The ActuatorData object to convert.
     * @return A JSON-formatted string representing the ActuatorData object.
     */
	public String actuatorDataToJson(ActuatorData actuatorData)
	{
		
		String jsonData = null;
		
		if (actuatorData != null) {
			Gson gson = new Gson();
			jsonData = gson.toJson(actuatorData);
		}
		
		return jsonData;
	}
	
	/**
     * Converts a SensorData object to JSON format.
     *
     * @param sensorData The SensorData object to convert.
     * @return A JSON-formatted string representing the SensorData object.
     */
	public String sensorDataToJson(SensorData sensorData)
	{
		String jsonData = null;
		
		if (sensorData != null) {
			Gson gson = new Gson();
			jsonData = gson.toJson(sensorData);
		}
		
		return jsonData;
	}

	/**
     * Converts a SystemPerformanceData object to JSON format.
     *
     * @param sysPerfData The SystemPerformanceData object to convert.
     * @return A JSON-formatted string representing the SystemPerformanceData object.
     */
	public String systemPerformanceDataToJson(SystemPerformanceData sysPerfData)
	{
		String jsonData = null;
		
		if (sysPerfData != null) {
			Gson gson = new Gson();
			jsonData = gson.toJson(sysPerfData);
		}
		
		return jsonData;
	}
	
    /**
     * Converts a SystemStateData object to JSON format.
     *
     * @param sysStateData The SystemStateData object to convert.
     * @return A JSON-formatted string representing the SystemStateData object.
     */

	public String systemStateDataToJson(SystemStateData sysStateData)
	{
		String jsonData = null;
		
		if (sysStateData != null) {
			Gson gson = new Gson();
			jsonData = gson.toJson(sysStateData);
		}
		
		return jsonData;
	}
	
	 /**
     * Converts a JSON-formatted string to an ActuatorData object.
     *
     * @param jsonData The JSON-formatted string to convert.
     * @return An ActuatorData object parsed from the JSON string.
     */
	public ActuatorData jsonToActuatorData(String jsonData)
	{
		ActuatorData data = null;
		
		if (jsonData != null && jsonData.trim().length() > 0) {
			Gson gson = new Gson();
			data = gson.fromJson(jsonData, ActuatorData.class);
		}
		
		return data;
	
	}
	
	/**
     * Converts a JSON-formatted string to a SensorData object.
     *
     * @param jsonData The JSON-formatted string to convert.
     * @return A SensorData object parsed from the JSON string.
     */
	public SensorData jsonToSensorData(String jsonData)
	{
		SensorData data = null;
		
		if (jsonData != null && jsonData.trim().length() > 0) {
			Gson gson = new Gson();
			data = gson.fromJson(jsonData, SensorData.class);
		}
		
		return data;
	}
	
	/**
     * Converts a JSON-formatted string to a SystemPerformanceData object.
     *
     * @param jsonData The JSON-formatted string to convert.
     * @return A SystemPerformanceData object parsed from the JSON string.
     */
	public SystemPerformanceData jsonToSystemPerformanceData(String jsonData)
	{
		SystemPerformanceData data = null;
		
		if (jsonData != null && jsonData.trim().length() > 0) {
			Gson gson = new Gson();
			data = gson.fromJson(jsonData, SystemPerformanceData.class);
		}
		
		return data;
	}
	
	/**
     * Converts a JSON-formatted string to a SystemStateData object.
     *
     * @param jsonData The JSON-formatted string to convert.
     * @return A SystemStateData object parsed from the JSON string.
     */
	public SystemStateData jsonToSystemStateData(String jsonData)
	{
		SystemStateData data = null;
		
		if (jsonData != null && jsonData.trim().length() > 0) {
			Gson gson = new Gson();
			data = gson.fromJson(jsonData, SystemStateData.class);
		}
		
		return data;
	}
	
}
