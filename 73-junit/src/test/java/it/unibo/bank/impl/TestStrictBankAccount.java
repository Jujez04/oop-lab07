package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test class for the {@link StrictBankAccount} class.
 */
class TestStrictBankAccount {

    private static final int USR_ID = 1;

    private static double STD_BALANCE = 100;

    // Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;

    /**
     * Prepare the tests.
     */
    @BeforeEach
    public void setUp() {
        this.mRossi = new AccountHolder("Mario", "Rossi", USR_ID);
        this.bankAccount = new StrictBankAccount(mRossi, STD_BALANCE);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    public void testInitialization() {
        assertEquals(STD_BALANCE, this.bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactionsCount());
        assertEquals(mRossi, this.bankAccount.getAccountHolder());
        assertEquals(USR_ID, this.bankAccount.getAccountHolder().getUserID());
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
     */
    @Test
    public void testManagementFees() {
        this.bankAccount.deposit(mRossi.getUserID(), STD_BALANCE);
        assertEquals(1, this.bankAccount.getTransactionsCount());
        this.bankAccount.chargeManagementFees(USR_ID);
        assertEquals(STD_BALANCE + STD_BALANCE - StrictBankAccount.TRANSACTION_FEE - StrictBankAccount.MANAGEMENT_FEE, this.bankAccount.getBalance());    
        assertEquals(0, this.bankAccount.getTransactionsCount());
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    public void testNegativeWithdraw() {
        try {
            this.bankAccount.withdraw(mRossi.getUserID(), -12345.6789);
            fail("Expected an IllegalArgumentException but that is not true in the implementation");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot withdraw a negative amount", e.getMessage());
        }
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    public void testWithdrawingTooMuch() {
        try {
            this.bankAccount.withdraw(mRossi.getUserID(), (STD_BALANCE * STD_BALANCE));
            fail("Expected an IllegalArgumentException but that is not true in the implementation");
        } catch (IllegalArgumentException e) {
            assertEquals("Insufficient balance", e.getMessage());
        }
    }
}
