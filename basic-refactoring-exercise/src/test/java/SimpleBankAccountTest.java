import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private static final int MARIO_ROSSI_ID = 1;
    private static final int WRONG_USER_ID = 2;
    private static final int INITIAL_BALANCE = 0;
    private static final int DEPOSIT_AMOUNT = 100;
    private static final int WITHDRAW_AMOUNT = 70;

    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach() {
        accountHolder = new AccountHolder("Mario", "Rossi", MARIO_ROSSI_ID);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    private void deposit(final int id) {
        bankAccount.deposit(id, DEPOSIT_AMOUNT);
    }

    private void depositAndWithdraw(final int depositAccountID, final int withdrawAccountID) {
        deposit(depositAccountID);
        bankAccount.withdraw(withdrawAccountID, WITHDRAW_AMOUNT);
    }

    @Test
    void testDeposit() {
        deposit(accountHolder.id());
        assertEquals(INITIAL_BALANCE + DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        final int depositAmountWrongAccount = 50;
        deposit(accountHolder.id());
        bankAccount.deposit(WRONG_USER_ID, depositAmountWrongAccount);
        assertEquals(INITIAL_BALANCE + DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        depositAndWithdraw(accountHolder.id(), accountHolder.id());
        assertEquals(INITIAL_BALANCE + DEPOSIT_AMOUNT - WITHDRAW_AMOUNT - SimpleBankAccount.WITHDRAWAL_FEE, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        depositAndWithdraw(accountHolder.id(), WRONG_USER_ID);
        assertEquals(INITIAL_BALANCE + DEPOSIT_AMOUNT, bankAccount.getBalance());
    }
}
