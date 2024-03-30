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
import programmingtheiot.data.SystemPerformanceData;

public class UpdateSystemPerformanceResourceHandler extends CoapResource {

	/**
     * Constructor for UpdateSystemPerformanceResourceHandler.
     * 
     * @param resourceName Name of the resource.
     */

	public UpdateSystemPerformanceResourceHandler(String resourceName) {
		super(resourceName);
		// TODO Auto-generated constructor stub
	}

	private SystemPerformanceData systemPerformanceData = null;
	private IDataMessageListener dataMsgListener = null;
	private static final Logger _Logger = Logger.getLogger(UpdateSystemPerformanceResourceHandler.class.getName());

	/**
     * Sets the data message listener for handling system performance messages.
     * 
     * @param listener Data message listener.
     */
	public void setDataMessageListener(IDataMessageListener listener) {
		if (listener != null) {
			this.dataMsgListener = listener;
		}
	}

	/**
     * Handles PUT requests for updating system performance data.
     * 
     * @param context CoapExchange context for the PUT request.
     */
	@Override
	public void handlePUT(CoapExchange context) {
		ResponseCode code = ResponseCode.NOT_ACCEPTABLE;

		context.accept();

		if (this.dataMsgListener != null) {
			try {
				String jsonData = new String(context.getRequestPayload());

				SystemPerformanceData sysPerfData = DataUtil.getInstance().jsonToSystemPerformanceData(jsonData);

				// TODO: Choose the following (but keep it idempotent!)
				// 1) Check MID to see if it’s repeated for some reason
				// - optional, as the underlying lib should handle this
				// 2) Cache the previous update – is the PAYLOAD repeated?
				// 2) Delegate the data check to this.dataMsgListener

				this.dataMsgListener.handleSystemPerformanceMessage(
						ResourceNameEnum.CDA_SYSTEM_PERF_MSG_RESOURCE, sysPerfData);

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

		String msg = "Update system perf data request handled: " + super.getName();

		context.respond(code, msg);
	}
	/**
     * Handles GET requests for updating system performance data.
     * 
     * @param context CoapExchange context for the GET request.
     */
	@Override
	public void handleGET(CoapExchange context) {
		ResponseCode code = ResponseCode.NOT_ACCEPTABLE;

		context.accept();

		if (this.dataMsgListener != null) {
			try {
				String jsonData = "";

				jsonData = DataUtil.getInstance().systemPerformanceDataToJson(this.systemPerformanceData);

				// TODO: Choose the following (but keep it idempotent!)
				// 1) Check MID to see if it’s repeated for some reason
				// - optional, as the underlying lib should handle this
				// 2) Cache the previous update – is the PAYLOAD repeated?
				// 2) Delegate the data check to this.dataMsgListener

				this.dataMsgListener.handleSystemPerformanceMessage(
						ResourceNameEnum.CDA_SYSTEM_PERF_MSG_RESOURCE, systemPerformanceData);

				code = ResponseCode.CONTENT;
			} catch (Exception e) {
				_Logger.warning(
						"Failed to handle GET request. Message: " +
								e.getMessage());

				code = ResponseCode.BAD_REQUEST;
			}
		} else {
			_Logger.info(
					"No callback listener for request. Ignoring GET.");

			code = ResponseCode.CONTINUE;
		}

		String msg = "Update system perf data request handled: " + super.getName();

		context.respond(code, msg);
	}

	/**
     * Handles POST requests for updating system performance data.
     * 
     * @param context CoapExchange context for the POST request.
     */

	@Override
	public void handlePOST(CoapExchange context) {
		ResponseCode code = ResponseCode.NOT_ACCEPTABLE;

		context.accept();

		if (this.dataMsgListener != null) {
			try {
				String jsonData = new String(context.getRequestPayload());

				SystemPerformanceData sysPerfData = DataUtil.getInstance().jsonToSystemPerformanceData(jsonData);

				// TODO: Choose the following (but keep it idempotent!)
				// 1) Check MID to see if it’s repeated for some reason
				// - optional, as the underlying lib should handle this
				// 2) Cache the previous update – is the PAYLOAD repeated?
				// 2) Delegate the data check to this.dataMsgListener

				this.dataMsgListener.handleSystemPerformanceMessage(
						ResourceNameEnum.CDA_SYSTEM_PERF_MSG_RESOURCE, sysPerfData);

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

		String msg = "Update system perf data request handled: " + super.getName();

		context.respond(code, msg);
	}

	/**
     * Handles DELETE requests for updating system performance data.
     * 
     * @param context CoapExchange context for the DELETE request.
     */

	@Override
	public void handleDELETE(CoapExchange context) {
		ResponseCode code = ResponseCode.NOT_ACCEPTABLE;

		context.accept();

		if (this.dataMsgListener != null) {
			try {
				String jsonData = new String(context.getRequestPayload());

				SystemPerformanceData sysPerfData = DataUtil.getInstance().jsonToSystemPerformanceData(jsonData);

				// TODO: Choose the following (but keep it idempotent!)
				// 1) Check MID to see if it’s repeated for some reason
				// - optional, as the underlying lib should handle this
				// 2) Cache the previous update – is the PAYLOAD repeated?
				// 2) Delegate the data check to this.dataMsgListener

				this.dataMsgListener.handleSystemPerformanceMessage(
						ResourceNameEnum.CDA_SYSTEM_PERF_MSG_RESOURCE, sysPerfData);

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

		String msg = "Update system perf data request handled: " + super.getName();

		context.respond(code, msg);
	}
}
