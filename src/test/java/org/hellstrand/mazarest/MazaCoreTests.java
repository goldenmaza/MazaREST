package org.hellstrand.mazarest;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import org.restlet.Client;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Method;
import org.restlet.data.Protocol;

/**
 * @author (Mats Richard Hellstrand)
 * @version (10th of November, 2018)
 */
public class MazaCoreTests {
	private static final int RESPONSE_STATUS_CODE_200 = 200;
    private static final String CUSTOM_ENDPOINT = "http://127.0.0.1:12345/bankaccounts/custom";
    private static final String CUSTOM_JSON_RESPONSE = "[]";
    private static final String DEFAULT_ENDPOINT = "http://127.0.0.1:12345/bankaccounts/default";
    private static final String DEFAULT_JSON_RESPONSE = "[{\"id\":2}]";
    private static final String SUMMARY_ENDPOINT = "http://127.0.0.1:12345/bankaccounts";
    private static final String SUMMARY_JSON_RESPONSE =
    		"[{\"number\":\"1357756\",\"synthetic\":false,\"balance\":1202,\"name\":\"Personal account\",\"id\":1,\"creditcard\":false},"
    		+ "{\"number\":\"2446987\",\"synthetic\":false,\"balance\":34057,\"name\":\"Business account\",\"id\":2,\"creditcard\":false},"
    		+ "{\"number\":\"9981644\",\"synthetic\":false,\"balance\":-10057,\"name\":\"Credit card\",\"id\":3,\"creditcard\":true},"
    		+ "{\"number\":\"\",\"synthetic\":true,\"balance\":0,\"name\":\"Expense claims\",\"id\":4,\"creditcard\":false}]";
    
    private Client client = null;
    private ChallengeResponse challengeResponse = null;
    
    @Before
    public void setUp() {
	    client = new Client(Protocol.HTTP);
	    challengeResponse = new ChallengeResponse(ChallengeScheme.HTTP_BASIC, "user", "f399b0a660f684b2c5a6b4c054f22d89");
    }
    
	@Test
	public void performDefaultEndpointTest() {
	    Request request = new Request(Method.GET, DEFAULT_ENDPOINT);
	    request.setChallengeResponse(challengeResponse);
	    Response response = client.handle(request);

	    assertThat(response, is(notNullValue()));
	    assertThat(response.getStatus(), is(notNullValue()));
	    assertThat(response.getStatus().getCode(), is(notNullValue()));
	    assertThat(response.getStatus().getCode(), is(RESPONSE_STATUS_CODE_200));
	    assertThat(response.getEntityAsText(), is(DEFAULT_JSON_RESPONSE));
	}
    
	@Test
	public void performSummaryEndpointTest() {
	    Request request = new Request(Method.GET, SUMMARY_ENDPOINT);
	    request.setChallengeResponse(challengeResponse);
	    Response response = client.handle(request);

	    assertThat(response, is(notNullValue()));
	    assertThat(response.getStatus(), is(notNullValue()));
	    assertThat(response.getStatus().getCode(), is(notNullValue()));
	    assertThat(response.getStatus().getCode(), is(RESPONSE_STATUS_CODE_200));
	    assertThat(response.getEntityAsText(), is(SUMMARY_JSON_RESPONSE));
	}
    
	@Test
	public void performCustomEndpointTest() {
	    Request request = new Request(Method.GET, CUSTOM_ENDPOINT);
	    request.setChallengeResponse(challengeResponse);
	    Response response = client.handle(request);

	    assertThat(response, is(notNullValue()));
	    assertThat(response.getStatus(), is(notNullValue()));
	    assertThat(response.getStatus().getCode(), is(notNullValue()));
	    assertThat(response.getStatus().getCode(), is(RESPONSE_STATUS_CODE_200));
	    assertThat(response.getEntityAsText(), is(CUSTOM_JSON_RESPONSE));
	}
}
