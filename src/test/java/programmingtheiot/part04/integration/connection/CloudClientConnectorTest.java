/**
* 
* This class is part of the Programming the Internet of Things
* project, and is available via the MIT License, which can be
* found in the LICENSE file at the top level of this repository.
* 
* Copyright (c) 2020 by Andrew D. King
*/
package programmingtheiot.part04.integration.connection;
import static org.junit.Assert.*;
import java.util.List;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import programmingtheiot.common.ConfigConst;
import programmingtheiot.common.DefaultDataMessageListener;
import programmingtheiot.common.ResourceNameEnum;
import programmingtheiot.data.ActuatorData;
import programmingtheiot.data.DataUtil;
import programmingtheiot.data.SensorData;
import programmingtheiot.data.SystemPerformanceData;
import programmingtheiot.gda.app.DeviceDataManager;
import programmingtheiot.gda.connection.*;
/**
* This test case class contains very basic integration tests for
* CloudClientConnector. It should not be considered complete,
* but serve as a starting point for the student implementing
* additional functionality within their Programming the IoT
* environment.
*
*/
public class CloudClientConnectorTest
{
	// static
	private static final Logger _Logger =
		Logger.getLogger(CloudClientConnectorTest.class.getName());
	// member var's
	private List<ICloudClient> cloudClientList = null;
	private ICloudClient cloudClient = null;
	// test setup methods
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		this.cloudClient = new CloudClientConnector();
	}
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}
	// test methods
	/**
	 * Test method for {@link programmingtheiot.gda.connection.UbidotsMqttCloudClientConnector#connectClient()}.
	 */
	@Test
	public void testCloudClientConnectAndDisconnect() {
	    this.cloudClient.setDataMessageListener(new DefaultDataMessageListener());
	    try {
	        //assertTrue(this.cloudClient.connectClient());
	    	this.cloudClient.connectClient();
	        _Logger.info("Connected to the cloud.");
	        try {
	            // Sleep for a minute or so...
	            Thread.sleep(60000L);
	        } catch (InterruptedException e) {
	            // Ignore interrupted exception
	            _Logger.warning("Sleep interrupted: " + e.getMessage());
	        }
	        //assertTrue(this.cloudClient.disconnectClient());
	        this.cloudClient.disconnectClient();
	        _Logger.info("Disconnected from the cloud.");
	    } catch (Exception e) {
	        _Logger.warning("Exception during cloud client test: " + e.getMessage());
	        e.printStackTrace();
	        fail("Test failed: " + e.getMessage());
	    }
	    _Logger.info("Test complete.");
	}
	/**
	 * Test method
	 */
	@Test
	public void testIntegratedCloudClientConnectAndDisconnect()
	{
		DeviceDataManager ddm = new DeviceDataManager();
		ddm.startManager();
		try {
			// sleep for a minute or so...
			Thread.sleep(60000L);
		} catch (Exception e) {
			// ignore
		}
		ddm.stopManager();
		_Logger.info("Test complete.");
	}
	/**
	 * Test method for {@link programmingtheiot.gda.connection.UbidotsMqttCloudClientConnector#publishMessage(programmingtheiot.common.ResourceNameEnum, java.lang.String, int)}.
	 */
	@Test
	public void testPublishAndSubscribe()
	{
		//this.cloudClient.setDataMessageListener(new DefaultDataMessageListener());
		//assertTrue(this.cloudClient.connectClient());
		this.cloudClient.connectClient();
		SensorData sensorData = new SensorData();
		sensorData.setName(ConfigConst.TEMP_SENSOR_NAME);
		sensorData.setValue(92.0f);
		sensorData.setName(ConfigConst.PRESSURE_SENSOR_NAME);
		sensorData.setValue(400.0f);
		SystemPerformanceData sysPerfData = new SystemPerformanceData();
		sysPerfData.setCpuUtilization(34.7f);
		sysPerfData.setMemoryUtilization(39.8f);
		//assertTrue(this.cloudClient.subscribeToCloudEvents(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE));
		this.cloudClient.subscribeToCloudEvents(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE);
		try {
			// sleep for a few seconds...
			Thread.sleep(5000L);
		} catch (Exception e) {
			// ignore
		}
		//assertTrue(this.cloudClient.sendEdgeDataToCloud(ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, sensorData));
		//assertTrue(this.cloudClient.sendEdgeDataToCloud(ResourceNameEnum.CDA_SYSTEM_PERF_MSG_RESOURCE, sysPerfData));
		this.cloudClient.sendEdgeDataToCloud(ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, sensorData);
		try {
			// sleep for half a minute or so...
			Thread.sleep(30000L);
		} catch (Exception e) {
			// ignore
		}
		this.cloudClient.sendEdgeDataToCloud(ResourceNameEnum.CDA_SYSTEM_PERF_MSG_RESOURCE, sysPerfData);
		try {
			// sleep for half a minute or so...
			Thread.sleep(30000L);
		} catch (Exception e) {
			// ignore
		}
		//assertTrue(this.cloudClient.unsubscribeFromCloudEvents(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE));
		this.cloudClient.unsubscribeFromCloudEvents(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE);
		try {
			// sleep for a minute or so...
			Thread.sleep(50000L);
		} catch (Exception e) {
			// ignore
		}
		//assertTrue(this.cloudClient.disconnectClient());
		this.cloudClient.disconnectClient();
	}
	@Test
	public void testPublishAndSubscribeLED()
	{
		//this.cloudClient.setDataMessageListener(new DefaultDataMessageListener());
		//assertTrue(this.cloudClient.connectClient());
		this.cloudClient.connectClient();
		ActuatorData ad = new ActuatorData();
        ad.setName(ConfigConst.LED_ACTUATOR_NAME);
        ad.setLocationID("constraineddevice001");
        ad.setTypeID(ConfigConst.LED_ACTUATOR_TYPE);
        // Nominal value tests
        this.cloudClient.subscribeToCloudEvents(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE);
		try {
			// sleep for a few seconds...
			Thread.sleep(5000L);
		} catch (Exception e) {
			// ignore
		}
        ad.setValue(28);
        this.cloudClient.sendEdgeDataToCloud(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE, ad);
        waitForSeconds(2);
        ad.setValue(0);
        this.cloudClient.sendEdgeDataToCloud(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE, ad);
        waitForSeconds(2);
        ad.setValue(1);
        this.cloudClient.sendEdgeDataToCloud(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE, ad);
        waitForSeconds(2);
        /*
		SensorData sensorData = new SensorData();
		sensorData.setName(ConfigConst.TEMP_SENSOR_NAME);
		sensorData.setValue(92.0f);
		SystemPerformanceData sysPerfData = new SystemPerformanceData();
		sysPerfData.setCpuUtilization(34.7f);
		sysPerfData.setMemoryUtilization(39.8f);
		//assertTrue(this.cloudClient.subscribeToCloudEvents(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE));
		this.cloudClient.subscribeToCloudEvents(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE);
		try {
			// sleep for a few seconds...
			Thread.sleep(5000L);
		} catch (Exception e) {
			// ignore
		}
		//assertTrue(this.cloudClient.sendEdgeDataToCloud(ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, sensorData));
		//assertTrue(this.cloudClient.sendEdgeDataToCloud(ResourceNameEnum.CDA_SYSTEM_PERF_MSG_RESOURCE, sysPerfData));
		this.cloudClient.sendEdgeDataToCloud(ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, sensorData);
		try {
			// sleep for half a minute or so...
			Thread.sleep(30000L);
		} catch (Exception e) {
			// ignore
		}
		this.cloudClient.sendEdgeDataToCloud(ResourceNameEnum.CDA_SYSTEM_PERF_MSG_RESOURCE, sysPerfData);
		try {
			// sleep for half a minute or so...
			Thread.sleep(30000L);
		} catch (Exception e) {
			// ignore
		}
		//assertTrue(this.cloudClient.unsubscribeFromCloudEvents(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE));
		this.cloudClient.unsubscribeFromCloudEvents(ResourceNameEnum.CDA_ACTUATOR_CMD_RESOURCE);
		try {
			// sleep for a minute or so...
			Thread.sleep(50000L);
		} catch (Exception e) {
			// ignore
		}
		*/
		//assertTrue(this.cloudClient.disconnectClient());
		this.cloudClient.disconnectClient();
	}
	private void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}