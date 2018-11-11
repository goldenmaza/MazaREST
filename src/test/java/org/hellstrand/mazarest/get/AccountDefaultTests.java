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
public class AccountDefaultTests {
	private static final int LENGTH = 1;
	
	@Test
	public void getAccountTableTest() throws JSONException {
		AccountDefault accountDefault = new AccountDefault();
		Representation representation = accountDefault.getAccountTable();
		assertThat(representation, is(notNullValue()));
		
		JSONArray jsonArray = ((JsonRepresentation) representation).getJsonArray();
		assertThat(jsonArray, is(notNullValue()));
		
		int length = jsonArray.length();
		assertThat(length, is(LENGTH));
	}
}
