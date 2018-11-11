package org.hellstrand.mazarest.model;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.json.JSONException;
import org.json.JSONObject;

import org.junit.Test;
import org.hellstrand.mazarest.util.BankAccountUtil;

/**
 * @author (Mats Richard Hellstrand)
 * @version (10th of November, 2018)
 */
public class BankAccountTests {
	private static final int ID = 1;
	private static final String NUMBER = "123";
	private static final String NAME = "TestAccount";
	private static final BigDecimal BALANCE = BigDecimal.valueOf(12500);
	private static final boolean CREDIT_CARD = false;
	private static final boolean SYNTHETIC = false;

	@Test
	public void createBankAccountTest() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setId(ID);
		bankAccount.setNumber(NUMBER);
		bankAccount.setName(NAME);
		bankAccount.setBalance(BALANCE);
		bankAccount.setCreditCard(CREDIT_CARD);
		bankAccount.setSynthetic(SYNTHETIC);

		assertThat(bankAccount, is(notNullValue()));

		assertThat(bankAccount.getId(), is(notNullValue()));
		assertThat(bankAccount.getId(), is(ID));

		assertThat(bankAccount.getNumber(), is(notNullValue()));
		assertThat(bankAccount.getNumber(), is(NUMBER));

		assertThat(bankAccount.getName(), is(notNullValue()));
		assertThat(bankAccount.getName(), is(NAME));

		assertThat(bankAccount.getBalance(), is(notNullValue()));
		assertThat(bankAccount.getBalance(), is(BALANCE));

		assertThat(bankAccount.isCreditCard(), is(notNullValue()));
		assertThat(bankAccount.isCreditCard(), is(CREDIT_CARD));

		assertThat(bankAccount.isSynthetic(), is(notNullValue()));
		assertThat(bankAccount.isSynthetic(), is(SYNTHETIC));
	}
	
	@Test
	public void convertBankAccountToJSONObjectTest() throws JSONException {
		JSONObject jsonObject = BankAccountUtil.convert(new BankAccount(ID, NUMBER, NAME, BALANCE, CREDIT_CARD, SYNTHETIC));
		
		assertThat(jsonObject, is(notNullValue()));
		assertThat(jsonObject.get("id"), is(ID));
		assertThat(jsonObject.get("number"), is(NUMBER));
		assertThat(jsonObject.get("name"), is(NAME));
		assertThat(jsonObject.get("balance"), is(BALANCE));
		assertThat(jsonObject.get("creditcard"), is(CREDIT_CARD));
		assertThat(jsonObject.get("synthetic"), is(SYNTHETIC));
	}
}
