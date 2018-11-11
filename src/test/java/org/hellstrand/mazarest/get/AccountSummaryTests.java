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
public class AccountSummaryTests {
	private static final int LENGTH = 4;
	
	@Test
	public void getAccountTableTest() throws JSONException {
		AccountSummary accountSummary = new AccountSummary();
		Representation representation = accountSummary.getAccountTable();
		assertThat(representation, is(notNullValue()));
		
		JSONArray jsonArray = ((JsonRepresentation) representation).getJsonArray();
		assertThat(jsonArray, is(notNullValue()));
		
		int length = jsonArray.length();
		assertThat(length, is(LENGTH));
	}
}
