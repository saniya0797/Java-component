/**
 * This class is part of the Programming the Internet of Things project.
 * 
 * It is provided as a simple shell to guide the student and assist with
 * implementation for the Programming the Internet of Things exercises,
 * and designed to be modified by the student as needed.
 */ 

package programmingtheiot.gda.system;

import java.util.logging.Logger;

import programmingtheiot.common.ConfigConst;

/**
 *
 */
public abstract class BaseSystemUtilTask
{
	// static

	// Logger for logging messages related to this class
	private static final Logger _Logger =
			Logger.getLogger(BaseSystemUtilTask.class.getName());


	// private

	// Name of the system utilization task
	private String name   = ConfigConst.NOT_SET;

	// Type ID of the system utilization task
	private int typeID = ConfigConst.DEFAULT_TYPE_ID;

	// constructors

	/**
	 * Constructor to initialize the BaseSystemUtilTask object with a given name and type ID.
	 *
	 * @param name The name of the system utilization task.
	 * @param typeID The type ID of the system utilization task.
	 */
	public BaseSystemUtilTask(String name, int typeID)
	{
		super();
		// If the name is not null, set it; otherwise, use the default value.
		if (name != null) {
			this.name = name;
		}

		// Set the type ID.
		this.typeID = typeID;
	}


	// public methods

	/**
	 * Gets the name of the system utilization task.
	 *
	 * @return The name of the task.
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Gets the type ID of the system utilization task.
	 *
	 * @return The type ID of the task.
	 */
	public int getTypeID()
	{
		return this.typeID;
	}

	/**
	 * Template method definition. Sub-class will implement this to retrieve
	 * the system utilization measure.
	 *
	 * @return The telemetry value as a float.
	 */
	public abstract float getTelemetryValue();

}