package org.hellstrand.mazarest.get;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.json.JSONArray;
import org.json.JSONException;

import org.junit.Test;

import org.restlet.representation.Representation;
import org.restlet.ext.json.JsonRepresentation;

/**
 * @author (Mats Richard Hellstrand)
 * @version (10th of November, 2018)
 */
public class AccountCustomTests {
	private static final int LENGTH = 0;
	
	@Test
	public void getAccountTableTest() throws JSONException {
		AccountCustom accountCustom = new AccountCustom();
		Representation representation = accountCustom.getAccountTable();
		assertThat(representation, is(notNullValue()));
		
		JSONArray jsonArray = ((JsonRepresentation) representation).getJsonArray();
		assertThat(jsonArray, is(notNullValue()));
		
		int length = jsonArray.length();
		assertThat(length, is(LENGTH));
	}
}
