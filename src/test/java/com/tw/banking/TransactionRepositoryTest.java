package com.tw.banking;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Panda
 * @date 6/22/2021
 */
class TransactionRepositoryTest {
    @Test
    void should_cannot_change_returned_transactions_when_call_allTransactions() {
        Clock dummyClock = mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(dummyClock);

        List<Transaction> transactions = transactionRepository.allTransactions();
        assertThrows(UnsupportedOperationException.class, () -> {
            transactions.add(new Transaction("21/06/2021", 1));
        });
    }

    @Test
    void should_add_one_transaction_with_currentDate_and_amount_when_call_addDeposit_given_amount() {
        Clock stubClock = mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(stubClock);
        when(stubClock.todayAsString()).thenReturn("21/06/2021");

        transactionRepository.addDeposit(1);

        List<Transaction> result = transactionRepository.allTransactions();
        assertEquals("21/06/2021", result.get(0).date());
        assertEquals(1, result.get(0).amount());
    }

    @Test
    void should_add_one_transaction_with_currentDate_and_minus_amount_when_call_addWithdraw_given_amount() {
        Clock stubClock = mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(stubClock);
        when(stubClock.todayAsString()).thenReturn("21/06/2021");

        transactionRepository.addWithdraw(1);

        List<Transaction> result = transactionRepository.allTransactions();
        assertEquals("21/06/2021", result.get(0).date());
        assertEquals(-1, result.get(0).amount());
    }
}
