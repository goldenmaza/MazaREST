package org.hellstrand.mazarest.get;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import org.hellstrand.mazarest.model.AccountData;

import java.util.List;

/**
 * @author (Mats Richard Hellstrand)
 * @version (10th of November, 2018)
 */
public class AccountSummary extends ServerResource {
    @Get
    // Return the GET request with JSON data...
    public Representation getAccountTable() throws JSONException {
    	List<JSONObject> bankAccounts = AccountData.getAllBankAccounts();
    	JSONArray jsonArray = new JSONArray(bankAccounts);
        return new JsonRepresentation(jsonArray);
    }
}
