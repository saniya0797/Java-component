/**
 * This class is part of the Programming the Internet of Things project.
 * 
 * It is provided as a simple shell to guide the student and assist with
 * implementation for the Programming the Internet of Things exercises,
 * and designed to be modified by the student as needed.
 */ 

package programmingtheiot.data;

import java.io.Serializable;

import programmingtheiot.common.ConfigConst;


public class ActuatorData extends BaseIotData implements Serializable
{
	// static
	
	
	// private var's
	private int     command      = ConfigConst.DEFAULT_COMMAND;
	private float   value        = ConfigConst.DEFAULT_VAL;
	private boolean isResponse   = false;
	private String  stateData    = "";
	
    
    
	// constructors
	
	/**
	 * Default constructor..
	 * 
	 */
	public ActuatorData()
	{
		super();
	}
	
	
	// public methods
	/**
	 * Gets the command associated with the actuator data.
	 *
	 * @return The command value.
	 */
	public int getCommand()
	{
		return this.command;
	}
	
	/**
	 * Gets the state data associated with the actuator data.
	 *
	 * @return The state data as a string.
	 */
	public String getStateData()
	{
		return this.stateData;
	}
	
	/**
	 * Gets the value associated with the actuator data.
	 *
	 * @return The value as a float.
	 */
	public float getValue()
	{
		return this.value;
	}
	
	/**
	 * Checks if the actuator data is marked as a response.
	 *
	 * @return True if it's a response, false otherwise.
	 */
	public boolean isResponseFlagEnabled()
	{
		return this.isResponse;
	}
	
	/**
	 * Marks the actuator data as a response.
	 */
	public void setAsResponse()
	{
		updateTimeStamp();
		this.isResponse = true;
	}
	
	
	/**
	 * Sets the command for the actuator data.
	 *
	 * @param command The command value to set.
	 */
	public void setCommand(int command)
	{
		updateTimeStamp();
		this.command = command;
	}
	
	/**
	 * Sets the state data for the actuator data.
	 *
	 * @param stateData The state data as a string.
	 */
	public void setStateData(String stateData)
	{
		updateTimeStamp();
		
		if (stateData != null) {
			this.stateData = stateData;
		}
	}
	
	/**
	 * Sets the value for the actuator data.
	 *
	 * @param val The value to set.
	 */
	public void setValue(float val)
	{
		updateTimeStamp();
		this.value = val;
	}
	
	
	// protected methods
	/**
	 * Handles the update of actuator data based on another BaseIotData object.
	 *
	 * @param data The BaseIotData object to update from.
	 */
	
	protected void handleUpdateData(BaseIotData data)
	{
		if (data instanceof ActuatorData) {
			ActuatorData aData = (ActuatorData) data;
			this.setCommand(aData.getCommand());
			this.setValue(aData.getValue());
			this.setStateData(aData.getStateData());
			
			if (aData.isResponseFlagEnabled()) {
				this.isResponse = true;
			}
		}
	}
}
	

