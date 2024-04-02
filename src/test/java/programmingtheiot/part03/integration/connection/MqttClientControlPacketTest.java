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
import programmingtheiot.data.*;
import programmingtheiot.gda.connection.*;

/**
 * This test case class contains very basic integration tests for
 * MqttClientControlPacketTest. It should not be considered complete,
 * but serve as a starting point for the student implementing
 * additional functionality within their Programming the IoT
 * environment.
 *
 */
public class MqttClientControlPacketTest
{
	// static
	
	private static final Logger _Logger =
		Logger.getLogger(MqttClientControlPacketTest.class.getName());
	
	
	// member var's
	
	private MqttClientConnector mqttClient = null;
	
	
	private String testTopic = "testTopic";
    private int qos ; // Change the QoS level as needed

    @Before
    public void setUp() throws Exception
    {
        this.mqttClient = new MqttClientConnector();
    }

    @After
    public void tearDown() throws Exception
    {
        if (mqttClient.isConnected()) {
            mqttClient.disconnectClient();
        }
    }

    @Test
    public void testConnectAndDisconnect()
    {
        // Connect
        assertTrue(mqttClient.connectClient());

        // Disconnect
        assertTrue(mqttClient.disconnectClient());
    }

    @Test
    public void testServerPing()
    {
        // Connect
        assertTrue(mqttClient.connectClient());

        // Server Ping
        // assertTrue(mqttClient.ping());

        // Disconnect
        assertTrue(mqttClient.disconnectClient());
    }

    @Test
    public void testPubSub()
    {
        // Connect
        assertTrue(mqttClient.connectClient());
        int delay = ConfigUtil.getInstance().getInteger(ConfigConst.MQTT_GATEWAY_SERVICE, ConfigConst.KEEP_ALIVE_KEY, ConfigConst.DEFAULT_KEEP_ALIVE);

        // Subscribe
        assertTrue(mqttClient.subscribeToTopic(ResourceNameEnum.CDA_UPDATE_NOTIFICATIONS_RESOURCE, 1));

        // Publish (QoS 1)
        assertTrue(mqttClient.publishMessage(ResourceNameEnum.CDA_UPDATE_NOTIFICATIONS_RESOURCE, "Hello, MQTT!", 1));

        // Unsubscribe
        assertTrue(mqttClient.unsubscribeFromTopic(ResourceNameEnum.CDA_UPDATE_NOTIFICATIONS_RESOURCE));
        try {
            Thread.sleep(delay*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Disconnect
        assertTrue(mqttClient.disconnectClient());
    }

    @Test
    public void testPubSubb()
    {
        // Connect
        assertTrue(mqttClient.connectClient());
        int delay = ConfigUtil.getInstance().getInteger(ConfigConst.MQTT_GATEWAY_SERVICE, ConfigConst.KEEP_ALIVE_KEY, ConfigConst.DEFAULT_KEEP_ALIVE);


        // Subscribe
        assertTrue(mqttClient.subscribeToTopic(ResourceNameEnum.CDA_UPDATE_NOTIFICATIONS_RESOURCE, 2));

        // Publish (QoS 2)
        assertTrue(mqttClient.publishMessage(ResourceNameEnum.CDA_UPDATE_NOTIFICATIONS_RESOURCE, "Hello, MQTT!", 2));

        // Unsubscribe
        assertTrue(mqttClient.unsubscribeFromTopic(ResourceNameEnum.CDA_UPDATE_NOTIFICATIONS_RESOURCE));
        try {
            Thread.sleep(delay*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Disconnect
        assertTrue(mqttClient.disconnectClient());
    }
}
