package org.hellstrand.mazarest.model;

import java.math.BigDecimal;
import java.math.MathContext;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;
import org.hellstrand.mazarest.util.BankAccountUtil;

/**
 * @author (Mats Richard Hellstrand)
 * @version (10th of November, 2018)
 */
public class AccountData {
	private AccountData() {}

	// Fetch ALL BankAccounts stored...
	public static List<JSONObject> getAllBankAccounts() throws JSONException {
		List<JSONObject> jsonObjects = new ArrayList<>();

		BankAccount[] accounts = getBankAccounts();
		for (BankAccount account : accounts) {
			jsonObjects.add(BankAccountUtil.convert(account));
		}

		return jsonObjects;
	}

	// Fetch the BankAccounts based on certain filters...
	public static List<JSONObject> getFilteredBankAccounts() throws JSONException {
		List<JSONObject> jsonObjects = new ArrayList<>();

		BankAccount[] accounts = getBankAccounts();
		filterBankAccounts(jsonObjects, accounts);

		return jsonObjects;
	}

	// Fetch Custom BankAccounts stored...
	public static List<JSONObject> getCustomBankAccounts() throws JSONException {
		List<JSONObject> jsonObjects = new ArrayList<>();

		BankAccount[] accounts = getInvalidBankAccounts();
		filterBankAccounts(jsonObjects, accounts);

		return jsonObjects;
	}

	// Fetch BankAccount array for processing...
	private static BankAccount[] getBankAccounts() {
		return new BankAccount[] {
			new BankAccount(1, "1357756", "Personal account", new BigDecimal(1202), false, false),
			new BankAccount(2, "2446987", "Business account", new BigDecimal(34057.00), false, false),
			new BankAccount(3, "9981644", "Credit card", new BigDecimal(-10057.00), true, false),
			new BankAccount(4, "", "Expense claims", new BigDecimal(0), false, true)
		};
	}

	// Fetch Custom BankAccount array for processing...
	private static BankAccount[] getInvalidBankAccounts() {
		return new BankAccount[] {
			new BankAccount(1, "123", "Custom Account A", new BigDecimal(-10), false, false),
			new BankAccount(2, "456", "Custom Account B", new BigDecimal(0), false, true)
		};
	}

	// Filter BankAccount objects based on different criteria...
	private static void filterBankAccounts(List<JSONObject> jsonObjects, BankAccount[] accounts) throws JSONException {
		accounts = Arrays.stream(accounts)
				.filter(a -> !a.isSynthetic())
				.filter(a -> a.getBalance().compareTo(BigDecimal.valueOf(0)) >= 0)
				.toArray(BankAccount[]::new);

		// No point performing the following with less than two BankAccounts...
		if (accounts.length >= 2) {
			// Sort BankAccount objects based on Balance, in descending order...
			Arrays.sort(accounts, ((a, b) -> b.getBalance().compareTo(a.getBalance())));

			// Evaluate if the BankAccount with the largest funds overshadow every other account...
			BigDecimal primary = accounts[0].getBalance();
			BigDecimal summary = Arrays.stream(accounts)
					.map(BankAccount::getBalance)
					.reduce(BigDecimal::add)
					.get()
					.subtract(primary);

			double division = primary.divide(summary, MathContext.DECIMAL128).doubleValue();
			if (division >= 2) {
				accounts = new BankAccount[]{
						accounts[0]
				};
			}
		}

		// Determine if a single BankAccount remain, if so save the ID that specific one...
		if (accounts.length == 1) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", accounts[0].getId());
			jsonObjects.add(jsonObject);
		} else { // Otherwise, convert all BankAccounts to JSONObjects...
			for (BankAccount account : accounts) {
				jsonObjects.add(BankAccountUtil.convert(account));
			}
		}
	}
}
