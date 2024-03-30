package programmingtheiot.gda.connection.handlers;

import java.util.logging.Logger;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

import programmingtheiot.common.ConfigConst;
import programmingtheiot.common.ConfigUtil;
import programmingtheiot.common.IActuatorDataListener;
import programmingtheiot.common.IDataMessageListener;
import programmingtheiot.common.ResourceNameEnum;
import programmingtheiot.data.ActuatorData;
import programmingtheiot.data.DataUtil;

public class GetActuatorCommandResourceHandler extends CoapResource implements IActuatorDataListener {

    private static final Logger _Logger = Logger.getLogger(GetActuatorCommandResourceHandler.class.getName());

    private ActuatorData actuatorData = null;

    /**
     * Constructor for GetActuatorCommandResourceHandler.
     * 
     * @param resourceName Name of the resource.
     */
    public GetActuatorCommandResourceHandler(String resourceName) {
        super(resourceName);
        super.setObservable(true);
        // TODO Auto-generated constructor stub
    }

     /**
     * Handles updates to actuator data.
     * 
     * @param data Actuator data to be updated.
     * @return True if the update was successful, otherwise false.
     */
    @Override
    public boolean onActuatorDataUpdate(ActuatorData data) {
        // TODO Auto-generated method stub
        if (data != null && this.actuatorData != null) {
            this.actuatorData.updateData(data);

            // notify all connected clients
            super.changed();

            _Logger.fine("Actuator data updated for URI: " + super.getURI() + ": Data value = "
                    + this.actuatorData.getValue());

            return true;
        }

        return false;
    }

    /**
     * Handles GET requests.
     * 
     * @param context CoapExchange context for the GET request.
     */
    @Override
    public void handleGET(CoapExchange context) {
        // TODO: validate 'context'

        // accept the request
        context.accept();
        if (this.actuatorData == null) {
            this.actuatorData = new ActuatorData();
        }
        _Logger.fine("HandleGET is Successfull");

        // TODO: convert the locally stored ActuatorData to JSON using DataUtil
        String jsonData = DataUtil.getInstance().actuatorDataToJson(this.actuatorData);

        // TODO: generate a response message, set the content type, and set the response
        // code

        // send an appropriate response
        context.respond(ResponseCode.CONTENT, jsonData);
    }

    /**
     * Handles PUT requests.
     * 
     * @param context CoapExchange context for the PUT request.
     */
    @Override
    public void handlePUT(CoapExchange context) {
        // TODO: validate 'context'

        // accept the request
        context.accept();
        _Logger.fine("Handleput is Successfull");

        // TODO: convert the locally stored ActuatorData to JSON using DataUtil
        String jsonData = DataUtil.getInstance().actuatorDataToJson(this.actuatorData);

        // TODO: generate a response message, set the content type, and set the response
        // code

        // send an appropriate response
        context.respond(ResponseCode.CHANGED, jsonData);
    }

    /**
     * Handles POST requests.
     * 
     * @param context CoapExchange context for the POST request.
     */
    @Override
    public void handlePOST(CoapExchange context) {
        // TODO: validate 'context'

        // accept the request
        context.accept();
        _Logger.fine("HandlePost is Successfull");

        // TODO: convert the locally stored ActuatorData to JSON using DataUtil
        String jsonData = DataUtil.getInstance().actuatorDataToJson(this.actuatorData);

        // TODO: generate a response message, set the content type, and set the response
        // code

        // send an appropriate response
        context.respond(ResponseCode.CREATED, jsonData);
    }

    /**
     * Handles DELETE requests.
     * 
     * @param context CoapExchange context for the DELETE request.
     */
    @Override
    public void handleDELETE(CoapExchange context) {
        // TODO: validate 'context'

        // accept the request
        context.accept();
        _Logger.fine("HandleDelete is Successfull");

        // TODO: convert the locally stored ActuatorData to JSON using DataUtil
        String jsonData = DataUtil.getInstance().actuatorDataToJson(this.actuatorData);

        // TODO: generate a response message, set the content type, and set the response
        // code

        // send an appropriate response
        context.respond(ResponseCode.DELETED, jsonData);
    }

}
