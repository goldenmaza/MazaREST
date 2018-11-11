package org.hellstrand.mazarest.util;

import org.hellstrand.mazarest.model.BankAccount;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author (Mats Richard Hellstrand)
 * @version (10th of November, 2018)
 */
public class BankAccountUtil {
    private BankAccountUtil() {}

    // Convert a specific BankAccount object to a JSONObject...
    public static JSONObject convert(BankAccount bankAccount) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", bankAccount.getId());
        jsonObject.put("number", bankAccount.getNumber());
        jsonObject.put("name", bankAccount.getName());
        jsonObject.put("balance", bankAccount.getBalance());
        jsonObject.put("creditcard", bankAccount.isCreditCard());
        jsonObject.put("synthetic", bankAccount.isSynthetic());

        return jsonObject;
    }
}
