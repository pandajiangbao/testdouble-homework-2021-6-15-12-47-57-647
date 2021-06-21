package com.tw.banking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Panda
 * @date 6/22/2021
 */
class AccountTest {

    @Test
    void should_addDepositTransaction_with_amount_1_when_call_deposit_given_amount_1() {
        //should
        TransactionRepository spyTransactionRepository = mock(TransactionRepository.class);
        Account account = new Account(spyTransactionRepository, mock(Printer.class));

        //when
        account.deposit(1);

        //then
        verify(spyTransactionRepository, times(1)).addDeposit(1);
    }

    @Test
    void should_addWithdrawTransaction_with_amount_1_when_call_withdraw_given_amount_1() {
        //should
        TransactionRepository spyTransactionRepository = mock(TransactionRepository.class);
        Account account = new Account(spyTransactionRepository, mock(Printer.class));

        //when
        account.withdraw(1);

        //then
        verify(spyTransactionRepository, times(1)).addWithdraw(1);
    }

    @Test
    void should_print_transactions_when_call_printStatement() {
        //should
        TransactionRepository stubTransactionRepository = mock(TransactionRepository.class);
        Printer spyPrinter = mock(Printer.class);
        Account account = new Account(stubTransactionRepository, spyPrinter);
        List<Transaction> stubTransactions = new ArrayList<>();
        when(stubTransactionRepository.allTransactions()).thenReturn(stubTransactions);

        //when
        account.printStatement();

        //then
        verify(spyPrinter, times(1)).print(stubTransactions);
    }
}
