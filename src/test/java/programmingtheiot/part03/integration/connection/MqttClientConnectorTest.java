/**
 * 
 * This class is part of the Programming the Internet of Things
 * project, and is available via the MIT License, which can be
 * found in the LICENSE file at the top level of this repository.
 * 
 * Copyright (c) 2020 by Andrew D. King
 */ 

package programmingtheiot.part03.integration.connection;

import static org.junit.Assert.*;

import java.util.logging.Logger;
 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
 
import programmingtheiot.common.ConfigConst;
import programmingtheiot.common.ConfigUtil;
import programmingtheiot.common.IDataMessageListener;
import programmingtheiot.common.ResourceNameEnum;
import programmingtheiot.data.ActuatorData;
import programmingtheiot.data.DataUtil;
import programmingtheiot.gda.connection.*;
/**
* Integration test class for MqttClientConnector.
* This class tests the functionality of MQTT connectivity,
* including connection, publishing, subscribing, and disconnection.
*/
public class MqttClientConnectorTest
{
	// static
	private static final Logger _Logger =
		Logger.getLogger(MqttClientConnectorTest.class.getName());

	// Member variables for MQTT client
	private MqttClientConnector mqttClient = null;

	// test setup methods
	/**
	 * Sets up test resources prior to each test.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		this.mqttClient = new MqttClientConnector();
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
	 * Test method for {@link programmingtheiot.gda.connection.MqttClientConnector#connectClient()}.
	 */
	 @Test
	public void testConnectAndDisconnect()
	// Test for connecting and disconnecting the MQTT client
	{
		int delay = ConfigUtil.getInstance().getInteger(ConfigConst.MQTT_GATEWAY_SERVICE, ConfigConst.KEEP_ALIVE_KEY, ConfigConst.DEFAULT_KEEP_ALIVE);
		assertTrue(this.mqttClient.connectClient());
		try {
			Thread.sleep(30000);
		} catch (Exception e) {
			// ignore
		}
		assertFalse(this.mqttClient.connectClient());
		try {
			Thread.sleep(delay * 1000 + 5000);
		} catch (Exception e) {
			// ignore
		}
		assertTrue(this.mqttClient.disconnectClient());
		assertFalse(this.mqttClient.disconnectClient());
	}
	/**
	 * Test method for {@link programmingtheiot.gda.connection.MqttClientConnector#publishMessage(programmingtheiot.common.ResourceNameEnum, java.lang.String, int)}.
	 */
	 @Test
	public void testPublishAndSubscribe()
	// Test for publishing and subscribing to topics using MQTT client

	{
		int qos = 0;
		int delay = ConfigUtil.getInstance().getInteger(ConfigConst.MQTT_GATEWAY_SERVICE, ConfigConst.KEEP_ALIVE_KEY, ConfigConst.DEFAULT_KEEP_ALIVE);
		assertTrue(this.mqttClient.connectClient());
		assertTrue(this.mqttClient.subscribeToTopic(ResourceNameEnum.GDA_MGMT_STATUS_MSG_RESOURCE, qos));
		assertTrue(this.mqttClient.subscribeToTopic(ResourceNameEnum.CDA_ACTUATOR_RESPONSE_RESOURCE, qos));
		assertTrue(this.mqttClient.subscribeToTopic(ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, qos));
		assertTrue(this.mqttClient.subscribeToTopic(ResourceNameEnum.CDA_SYSTEM_PERF_MSG_RESOURCE, qos));
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// ignore
		}
		assertTrue(this.mqttClient.publishMessage(ResourceNameEnum.GDA_MGMT_STATUS_MSG_RESOURCE, "TEST: This is the GDA message payload 1.", qos));
		assertTrue(this.mqttClient.publishMessage(ResourceNameEnum.GDA_MGMT_STATUS_MSG_RESOURCE, "TEST: This is the GDA message payload 2.", qos));
		assertTrue(this.mqttClient.publishMessage(ResourceNameEnum.GDA_MGMT_STATUS_MSG_RESOURCE, "TEST: This is the GDA message payload 3.", qos));
		try {
			Thread.sleep(25000);
		} catch (Exception e) {
			// ignore
		}
		assertTrue(this.mqttClient.unsubscribeFromTopic(ResourceNameEnum.GDA_MGMT_STATUS_MSG_RESOURCE));
		assertTrue(this.mqttClient.unsubscribeFromTopic(ResourceNameEnum.CDA_ACTUATOR_RESPONSE_RESOURCE));
		assertTrue(this.mqttClient.unsubscribeFromTopic(ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE));
		assertTrue(this.mqttClient.unsubscribeFromTopic(ResourceNameEnum.CDA_SYSTEM_PERF_MSG_RESOURCE));
 
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// ignore
		}
 
		try {
			Thread.sleep(delay * 1000);
		} catch (Exception e) {
			// ignore
		}
		assertTrue(this.mqttClient.disconnectClient());
	}
	/**
	 * Test method for {@link programmingtheiot.gda.connection.MqttClientConnector#publishMessage(programmingtheiot.common.ResourceNameEnum, java.lang.String, int)}.
	 */
	@Test
	public void testPublishAndSubscribeTwoClients()
	// Test for publishing and subscribing with two separate MQTT clients
	{
		int qos = 0;
		IDataMessageListener listener = new MqttPublishDataMessageListener(ResourceNameEnum.GDA_MGMT_STATUS_CMD_RESOURCE, true);
		this.mqttClient.setDataMessageListener(listener);
		assertTrue(this.mqttClient.connectClient());
		assertTrue(this.mqttClient.subscribeToTopic(ResourceNameEnum.GDA_MGMT_STATUS_MSG_RESOURCE, qos));
		assertTrue(this.mqttClient.subscribeToTopic(ResourceNameEnum.GDA_MGMT_STATUS_CMD_RESOURCE, 0));
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// ignore
		}
		assertTrue(this.mqttClient.publishMessage(ResourceNameEnum.GDA_MGMT_STATUS_MSG_RESOURCE, "TEST: This is the GDA message payload 1.", qos));
		assertTrue(this.mqttClient.publishMessage(ResourceNameEnum.GDA_MGMT_STATUS_MSG_RESOURCE, "TEST: This is the GDA message payload 2.", qos));
		assertTrue(this.mqttClient.publishMessage(ResourceNameEnum.GDA_MGMT_STATUS_MSG_RESOURCE, "TEST: This is the GDA message payload 3.", qos));
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// ignore
		}
		assertTrue(this.mqttClient.unsubscribeFromTopic(ResourceNameEnum.GDA_MGMT_STATUS_MSG_RESOURCE));
		assertTrue(this.mqttClient.unsubscribeFromTopic(ResourceNameEnum.GDA_MGMT_STATUS_CMD_RESOURCE));
 
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// ignore
		}
 
		assertTrue(this.mqttClient.disconnectClient());
	}
	/**
	 * Test method for {@link programmingtheiot.gda.connection.MqttClientConnector#publishMessage(programmingtheiot.common.ResourceNameEnum, java.lang.String, int)}.
	 */
	@Test
	public void testIntegrateWithCdaPublishCdaCmdTopic()
	// Test for integration with CDA by publishing to a CDA command topic
	{
		int qos = 1;
		assertTrue(this.mqttClient.connectClient());
		assertTrue(this.mqttClient.publishMessage(ResourceNameEnum.CDA_MGMT_STATUS_CMD_RESOURCE, "TEST: This is the CDA command payload.", qos));
		try {
			Thread.sleep(60000);
		} catch (Exception e) {
			// ignore
		}
		assertTrue(this.mqttClient.disconnectClient());
	}
	/**
	 * Test method for {@link programmingtheiot.gda.connection.MqttClientConnector#publishMessage(programmingtheiot.common.ResourceNameEnum, java.lang.String, int)}.
	 */
	// @Test
	public void testIntegrateWithCdaSubscribeCdaMgmtTopic()
	   // Test for integration with CDA by subscribing to a CDA management topic
	{
		int qos = 1;
		int delay = ConfigUtil.getInstance().getInteger(ConfigConst.MQTT_GATEWAY_SERVICE, ConfigConst.KEEP_ALIVE_KEY, ConfigConst.DEFAULT_KEEP_ALIVE);
		assertTrue(this.mqttClient.connectClient());
		// assertTrue(this.mqttClient.subscribeToTopic(ResourceNameEnum.CDA_MGMT_STATUS_MSG_RESOURCE, qos));
		try {
			Thread.sleep(delay * 1000);
		} catch (Exception e) {
			// ignore
		}
		assertTrue(this.mqttClient.unsubscribeFromTopic(ResourceNameEnum.CDA_MGMT_STATUS_MSG_RESOURCE));
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// ignore
		}
		assertTrue(this.mqttClient.disconnectClient());
	}
	 @Test
	public void testActuatorCommandResponseSubscription()
	{
		int qos = 0;
		assertTrue(this.mqttClient.connectClient());
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// ignore
		}
		ActuatorData ad = new ActuatorData();
		ad.setValue((float) 12.3);
		ad.setAsResponse();
		String adJson = DataUtil.getInstance().actuatorDataToJson(ad);
		assertTrue(this.mqttClient.publishMessage(ResourceNameEnum.CDA_ACTUATOR_RESPONSE_RESOURCE, adJson, qos));
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// ignore
		}
		assertTrue(this.mqttClient.disconnectClient());
	}

}