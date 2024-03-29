package programmingtheiot.gda.connection.handlers;

import java.util.logging.Logger;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

import programmingtheiot.common.ConfigConst;
import programmingtheiot.common.ConfigUtil;
import programmingtheiot.common.IDataMessageListener;
import programmingtheiot.common.ResourceNameEnum;
import programmingtheiot.data.DataUtil;
import programmingtheiot.data.SensorData;
import programmingtheiot.data.SystemPerformanceData;

public class UpdateTelemetryResourceHandler extends CoapResource {

    public UpdateTelemetryResourceHandler(String resourceName) {
        super(resourceName);
        //TODO Auto-generated constructor stub
    }

	private SensorData sensorData = null;
    private IDataMessageListener dataMsgListener = null;
    private static final Logger _Logger =
		Logger.getLogger(UpdateTelemetryResourceHandler.class.getName());

    public void setDataMessageListener(IDataMessageListener listener)   
    {
	if (listener != null) {
		this.dataMsgListener = listener;
	}
    }
    @Override
	public void handlePUT(CoapExchange context)
	{
		ResponseCode code = ResponseCode.NOT_ACCEPTABLE;
		
		context.accept();
		
		if (this.dataMsgListener != null) {
			try {
				String jsonData = new String(context.getRequestPayload());
				
				SensorData sensorData =
					DataUtil.getInstance().jsonToSensorData(jsonData);
				
				// TODO: Choose the following (but keep it idempotent!) 
				//   1) Check MID to see if it’s repeated for some reason
				//      - optional, as the underlying lib should handle this
				//   2) Cache the previous update – is the PAYLOAD repeated?
				//   2) Delegate the data check to this.dataMsgListener
				
				this.dataMsgListener.handleSensorMessage(
					ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, sensorData);
				
				code = ResponseCode.CHANGED;
			} catch (Exception e) {
				_Logger.warning(
					"Failed to handle PUT request. Message: " +
						e.getMessage());
				
				code = ResponseCode.BAD_REQUEST;
			}
		} else {
			_Logger.info(
				"No callback listener for request. Ignoring PUT.");
			
			code = ResponseCode.CONTINUE;
		}
		
		String msg =
			"Update sensor data request handled: " + super.getName();
		
		context.respond(code, msg);
	}
	@Override
	public void handleGET(CoapExchange context)
	{
		ResponseCode code = ResponseCode.NOT_ACCEPTABLE;
		
		context.accept();
		
		if (this.dataMsgListener != null) {
			try {
				String jsonData = "";
				
				jsonData  =
					DataUtil.getInstance().sensorDataToJson(this.sensorData);
				
				// TODO: Choose the following (but keep it idempotent!) 
				//   1) Check MID to see if it’s repeated for some reason
				//      - optional, as the underlying lib should handle this
				//   2) Cache the previous update – is the PAYLOAD repeated?
				//   2) Delegate the data check to this.dataMsgListener
				
				this.dataMsgListener.handleSensorMessage(
					ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, sensorData);
				
				code = ResponseCode.CONTENT;
			} catch (Exception e) {
				_Logger.warning(
					"Failed to handle PUT request. Message: " +
						e.getMessage());
				
				code = ResponseCode.BAD_REQUEST;
			}
		} else {
			_Logger.info(
				"No callback listener for request. Ignoring PUT.");
			
			code = ResponseCode.CONTINUE;
		}
		
		String msg =
			"Update sensor  data request handled: " + super.getName();
		
		context.respond(code, msg);
	}

	@Override
	public void handlePOST(CoapExchange context)
	{
		ResponseCode code = ResponseCode.NOT_ACCEPTABLE;
		
		context.accept();
		
		if (this.dataMsgListener != null) {
			try {
				String jsonData = "";
				
				jsonData  =
					DataUtil.getInstance().sensorDataToJson(this.sensorData);
				
				
				// TODO: Choose the following (but keep it idempotent!) 
				//   1) Check MID to see if it’s repeated for some reason
				//      - optional, as the underlying lib should handle this
				//   2) Cache the previous update – is the PAYLOAD repeated?
				//   2) Delegate the data check to this.dataMsgListener
				
				this.dataMsgListener.handleSensorMessage(
					ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, sensorData);
				
				code = ResponseCode.CREATED;
			} catch (Exception e) {
				_Logger.warning(
					"Failed to handle POST request. Message: " +
						e.getMessage());
				
				code = ResponseCode.BAD_REQUEST;
			}
		} else {
			_Logger.info(
				"No callback listener for request. Ignoring POST.");
			
			code = ResponseCode.CONTINUE;
		}
		
		String msg =
			"Update Sensor data request handled: " + super.getName();
		
		context.respond(code, msg);
	}
	@Override
	public void handleDELETE(CoapExchange context)
	{
		ResponseCode code = ResponseCode.NOT_ACCEPTABLE;
		
		context.accept();
		
		if (this.dataMsgListener != null) {
			try {
				String jsonData = "";
				
				jsonData  =
					DataUtil.getInstance().sensorDataToJson(this.sensorData);
				
				
				// TODO: Choose the following (but keep it idempotent!) 
				//   1) Check MID to see if it’s repeated for some reason
				//      - optional, as the underlying lib should handle this
				//   2) Cache the previous update – is the PAYLOAD repeated?
				//   2) Delegate the data check to this.dataMsgListener
				
				this.dataMsgListener.handleSensorMessage(
					ResourceNameEnum.CDA_SENSOR_MSG_RESOURCE, sensorData);
				
				code = ResponseCode.DELETED;
			} catch (Exception e) {
				_Logger.warning(
					"Failed to handle DELETE request. Message: " +
						e.getMessage());
				
				code = ResponseCode.BAD_REQUEST;
			}
		} else {
			_Logger.info(
				"No callback listener for request. Ignoring DELETE.");
			
			code = ResponseCode.CONTINUE;
		}
		
		String msg =
			"Update sensor data request handled: " + super.getName();
		
		context.respond(code, msg);
	}

}
