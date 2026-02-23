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

    private final int MARIO_ROSSI_ID = 1;
    private final int WRONG_USER_ID = 2;
    private final int INITIAL_BALANCE = 0;
    private final int DEPOSIT_AMOUNT = 100;
    private final int WITHDRAW_AMOUNT = 70;

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

    private void initialDeposit() {
        bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
    }

    @Test
    void testDeposit() {
        initialDeposit();
        assertEquals(INITIAL_BALANCE + DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        initialDeposit();
        final int DEPOSIT_AMOUNT_WRONG_ACCOUNT = 50;
        bankAccount.deposit(WRONG_USER_ID, DEPOSIT_AMOUNT_WRONG_ACCOUNT);
        assertEquals(INITIAL_BALANCE + DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        initialDeposit();
        bankAccount.withdraw(accountHolder.id(), WITHDRAW_AMOUNT);
        assertEquals(INITIAL_BALANCE + DEPOSIT_AMOUNT - WITHDRAW_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        initialDeposit();
        bankAccount.withdraw(WRONG_USER_ID, WITHDRAW_AMOUNT);
        assertEquals(INITIAL_BALANCE + DEPOSIT_AMOUNT, bankAccount.getBalance());
    }
}
