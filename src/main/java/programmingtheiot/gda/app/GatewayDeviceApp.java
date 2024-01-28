/**
 * This class is part of the Programming the Internet of Things
 * project, and is available via the MIT License, which can be
 * found in the LICENSE file at the top level of this repository.
 * 
 * You may find it more helpful to your design to adjust the
 * functionality, constants and interfaces (if there are any)
 * provided within in order to meet the needs of your specific
 * Programming the Internet of Things project.
 */ 

package programmingtheiot.gda.app;

import java.util.logging.Level;
import java.util.logging.Logger;
import programmingtheiot.gda.system.SystemPerformanceManager;
/**
 * Main GDA application.
 */
public class GatewayDeviceApp
{
	// static

	// Logger for logging messages related to this class
	private static final Logger _Logger =
			Logger.getLogger(GatewayDeviceApp.class.getName());

	// Default test runtime in milliseconds
	public static final long DEFAULT_TEST_RUNTIME = 60000L;

	// private var's
	private SystemPerformanceManager sysPerfMgr = null;


	// constructors

	/**
	 * Constructor.
	 *
	 * @param args The command line arguments.
	 */
	public GatewayDeviceApp(String[] args)
	{
		super();

		_Logger.info("Initializing GDA...");
		this.sysPerfMgr = new SystemPerformanceManager();

		parseArgs(args);
	}


	// static

	/**
	 * Main application entry point.
	 *
	 * @param args The command line arguments.
	 */
	public static void main(String[] args)
	{
		// Create an instance of GatewayDeviceApp
		GatewayDeviceApp gwApp = new GatewayDeviceApp(args);

		// Start the application
		gwApp.startApp();

		try {
			// Sleep for the default test runtime
			Thread.sleep(DEFAULT_TEST_RUNTIME);
		} catch (InterruptedException e) {
			// ignore
		}

		// Stop the application with exit code 0
		gwApp.stopApp(0);
	}


	// public methods

	/**
	 * Initializes and starts the application.
	 */
	public void startApp()
	{
		_Logger.info("Starting GDA...");

		try {
			// Start the SystemPerformanceManager
			if (this.sysPerfMgr.startManager()) {
				_Logger.info("GDA started successfully.");
			} else {
				_Logger.warning("Failed to start system performance manager!");

				// Stop the application with exit code -1
				stopApp(-1);
			}
		} catch (Exception e) {
			// Log and stop the application with exit code -1
			_Logger.log(Level.SEVERE, "Failed to start GDA. Exiting.", e);
			stopApp(-1);
		}
	}

	/**
	 * Stops the application.
	 *
	 * @param code The exit code to pass to {@link System.exit()}.
	 */
	public void stopApp(int code)
	{
		_Logger.info("Stopping GDA...");

		try {
			// Stop the SystemPerformanceManager
			if (this.sysPerfMgr.stopManager()) {
				_Logger.log(Level.INFO, "GDA stopped successfully with exit code {0}.", code);
			} else {
				_Logger.warning("Failed to stop system performance manager!");
			}

		} catch (Exception e) {
			// Log the error
			_Logger.log(Level.SEVERE, "Failed to cleanly stop GDA. Exiting.", e);
		}

		// Exit the application with the specified exit code
		System.exit(code);
	}


	// private methods

	/**
	 * Load the config file.
	 *
	 * NOTE: This will be added later.
	 *
	 * @param configFile The name of the config file to load.
	 */
	private void initConfig(String configFile)
	{
		_Logger.log(Level.INFO, "Attempting to load configuration: {0}", (configFile != null ? configFile : "Default."));

		// TODO: Your code here
	}

	/**
	 * Parse any arguments passed in on app startup.
	 * <p>
	 * This method should be written to check if any valid command line args are provided,
	 * including the name of the config file. Once parsed, call {@link #initConfig(String)}
	 * with the name of the config file, or null if the default should be used.
	 * <p>
	 * If any command line args conflict with the config file, the config file
	 * in-memory content should be overridden with the command line argument(s).
	 *
	 * @param args The non-null and non-empty args array.
	 */
	private void parseArgs(String[] args)
	{
		String configFile = null;
		if (args != null) {
			_Logger.log(Level.INFO, "Parsing {0} command line args.", args.length);

			for (String arg : args) {
				if (arg != null) {
					arg = arg.trim();

					// TODO: Your code here
				}
			}
		} else {
			_Logger.info("No command line args to parse.");
		}

		// Initialize the configuration with the parsed config file name
		initConfig(configFile);
	}

}