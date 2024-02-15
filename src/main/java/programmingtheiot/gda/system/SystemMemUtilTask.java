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
 * 
 * 
 */
/**
 * Represents a system memory utilization task that extends the BaseSystemUtilTask class.
 */
public class SystemMemUtilTask extends BaseSystemUtilTask
{
	// Logger for logging messages related to this class
	private static final Logger _Logger =
			Logger.getLogger(SystemCpuUtilTask.class.getName());

	// Memory usage information retrieved from the MemoryMXBean
	private MemoryUsage memUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();

	// Calculating memory utilization based on used and max memory
	private double memUtil = ((double) memUsage.getUsed() / (double) memUsage.getMax()) * 100.0d;

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
		// Retrieve memory usage information from the MemoryMXBean
		MemoryUsage memUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();

		// Extract used and max memory values
		double memUsed = (double) memUsage.getUsed();
		double memMax  = (double) memUsage.getMax();

		// Log memory used and max values
		_Logger.fine("Mem used: " + memUsed + "; Mem Max: " + memMax);

		// Calculate memory utilization and return as a float
		double memUtil = (memUsed / memMax) * 100.0d;
		return (float) memUtil;
	}

}
