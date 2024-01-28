/**
 * This class is part of the Programming the Internet of Things project.
 * 
 * It is provided as a simple shell to guide the student and assist with
 * implementation for the Programming the Internet of Things exercises,
 * and designed to be modified by the student as needed.
 */ 

 package programmingtheiot.gda.system;
 import java.util.logging.Logger;
 import java.util.logging.Level;
 import java.util.concurrent.Executors;
 import java.util.concurrent.ScheduledExecutorService;
 import java.util.concurrent.ScheduledFuture;
 import java.util.concurrent.TimeUnit;
 import java.util.concurrent.Executors;
 import java.util.concurrent.ScheduledExecutorService;
 import java.util.concurrent.ScheduledFuture;
 import java.util.concurrent.TimeUnit;
 
 import programmingtheiot.common.ConfigConst;
 import programmingtheiot.common.ConfigUtil;
 import programmingtheiot.common.IDataMessageListener;
 import programmingtheiot.common.ResourceNameEnum;
 import programmingtheiot.data.SystemPerformanceData;
 
 /**
  * Manages system performance tasks, such as CPU and memory utilization.
  */
 public class SystemPerformanceManager
 {
	 // Logger for logging messages related to this class
	 private static final Logger _Logger = Logger.getLogger(SystemPerformanceManager.class.getName());
 
	 // private variables
	 private String locationID = ConfigConst.NOT_SET;
	 private IDataMessageListener dataMsgListener = null;
 
	 // Polling rate for telemetry data retrieval
	 private int pollRate = ConfigConst.DEFAULT_POLL_CYCLES;
 
	 // ScheduledExecutorService for scheduling periodic tasks
	 private ScheduledExecutorService schedExecSvc = null;
 
	 // System CPU utilization task
	 private SystemCpuUtilTask sysCpuUtilTask = null;
 
	 // System memory utilization task
	 private SystemMemUtilTask sysMemUtilTask = null;
 
	 // Runnable task to be executed periodically
	 private Runnable taskRunner = null;
 
	 // Flag to track whether the manager is started
	 private boolean isStarted = false;
 
	 // constructors
 
	 /**
	  * Default constructor. Initializes the SystemPerformanceManager with default values.
	  */
	 public SystemPerformanceManager()
	 {
		 // Retrieve polling rate from configuration, use default if not available or invalid
		 this.pollRate = ConfigUtil.getInstance().getInteger(
				 ConfigConst.GATEWAY_DEVICE, ConfigConst.POLL_CYCLES_KEY, ConfigConst.DEFAULT_POLL_CYCLES);
 
		 // Set default polling rate if the retrieved value is invalid
		 if (this.pollRate <= 0) {
			 this.pollRate = ConfigConst.DEFAULT_POLL_CYCLES;
		 }
 
		 // Initialize ScheduledExecutorService with a single-threaded pool
		 this.schedExecSvc   = Executors.newScheduledThreadPool(1);
 
		 // Create instances of system utilization tasks
		 this.sysCpuUtilTask = new SystemCpuUtilTask();
		 this.sysMemUtilTask = new SystemMemUtilTask();
 
		 // Define the task runner as a lambda expression
		 this.taskRunner = () -> {
			 this.handleTelemetry();
		 };
		 this.locationID =ConfigUtil.getInstance().getProperty(
		 ConfigConst.GATEWAY_DEVICE, ConfigConst.LOCATION_ID_PROP, ConfigConst.NOT_SET);
		 
	 }
 
 
	 // public methods
 
	 /**
	  * Handles telemetry by retrieving and logging CPU and memory utilization.
	  */
	 public void handleTelemetry()
	 {
		 // Retrieve CPU and memory utilization values
		 float cpuUtil = this.sysCpuUtilTask.getTelemetryValue();
		 float memUtil = this.sysMemUtilTask.getTelemetryValue();
 
		 // Log CPU and memory utilization
		 _Logger.info("CPU utilization: " + cpuUtil + ", Mem utilization: " + memUtil);
		 SystemPerformanceData spd = new SystemPerformanceData();
		 spd.setLocationID(this.locationID);
		 spd.setCpuUtilization(cpuUtil);
		 spd.setMemoryUtilization(memUtil);
		 
		 if (this.dataMsgListener != null) {
			 this.dataMsgListener.handleSystemPerformanceMessage(
				 ResourceNameEnum.GDA_SYSTEM_PERF_MSG_RESOURCE, spd);
		 }
	 }
 
	 /**
	  * Sets the data message listener (not implemented in this version).
	  *
	  * @param listener The data message listener to be set.
	  */
	 public void setDataMessageListener(IDataMessageListener listener)
	 {
		 if (listener != null) {
			 this.dataMsgListener = listener;
		 }
	 }
 
	 /**
	  * Starts the SystemPerformanceManager if it is not already started.
	  *
	  * @return True if the manager is started successfully, false otherwise.
	  */
	 public boolean startManager()
	 {
		 if (!this.isStarted) {
			 _Logger.info("SystemPerformanceManager is starting...");
 
			 // Schedule the taskRunner at a fixed rate with the specified polling interval
			 ScheduledFuture<?> futureTask =
					 this.schedExecSvc.scheduleAtFixedRate(this.taskRunner, 1L, this.pollRate, TimeUnit.SECONDS);
 
			 this.isStarted = true;
		 } else {
			 _Logger.info("SystemPerformanceManager is already started.");
		 }
 
		 return this.isStarted;
	 }
 
	 /**
	  * Stops the SystemPerformanceManager and shuts down the ScheduledExecutorService.
	  *
	  * @return True if the manager is stopped successfully, false otherwise.
	  */
	 public boolean stopManager()
	 {
		 // Shutdown the ScheduledExecutorService
		 this.schedExecSvc.shutdown();
		 this.isStarted = false;
 
		 _Logger.info("SystemPerformanceManager is stopped.");
 
		 return true;
	 }
 
 }