/**
 * This class is part of the Programming the Internet of Things project.
 * 
 * It is provided as a simple shell to guide the student and assist with
 * implementation for the Programming the Internet of Things exercises,
 * and designed to be modified by the student as needed.
 */ 

package programmingtheiot.gda.system;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.logging.Logger;
import java.util.logging.Level;

import programmingtheiot.common.ConfigConst;
import programmingtheiot.common.ConfigConst;

/**
 * Shell representation of class for student implementation.
 * 
 */

/**
 * Represents a system memory utilization task that extends the BaseSystemUtilTask class.
 */
public class SystemMemUtilTask extends BaseSystemUtilTask
{

	private static final Logger _Logger =
			Logger.getLogger(SystemCpuUtilTask.class.getName());

	MemoryUsage memUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
	double memUtil = ((double) memUsage.getUsed() / (double) memUsage.getMax()) * 100.0d;
	// constructors
	
	/**
	 * Default constructor. Initializes the SystemMemUtilTask with default values.
	 */
	public SystemMemUtilTask()
	{

		super(ConfigConst.NOT_SET, ConfigConst.DEFAULT_TYPE_ID);
	}
	
	
	// public methods
	/**
	 * Retrieves the current memory utilization as telemetry value.
	 *
	 * @return The memory utilization as a float value.
	 */
	@Override
	public float getTelemetryValue()
	{

		MemoryUsage memUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
		double memUsed = (double) memUsage.getUsed();
		double memMax  = (double) memUsage.getMax();


		_Logger.fine("Mem used: " + memUsed + "; Mem Max: " + memMax);

		double memUtil = (memUsed / memMax) * 100.0d;

		return (float) memUtil;
	}
	
}
