package com.p2p;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.p2p.domain.Borrower;
import com.p2p.domain.Loan;
import com.p2p.service.LoanService;

public class LoanServiceTest {

    @Test
    void shouldRejectLoanWhenBorrowerNotVerified() {
        // SCENARIO:
        // Borrower tidak terverifikasi (KYC = false)
        // Ketika borrower mengajukan pinjaman
        // Maka sistem harus menolak dengan melempar exception

        // Arrange (Initial Condition)
        // Borrower belum lolos proses KYC
        Borrower borrower = new Borrower(false, 700);

        // Service untuk pengajuan loan
        LoanService loanService = new LoanService();

        // Jumlah pinjaman valid
        BigDecimal amount = BigDecimal.valueOf(1000);

        // Act & Assert
        // Borrower mencoba mengajukan loan, sistem harus throw exception
        assertThrows(IllegalArgumentException.class, () -> {
            loanService.createLoan(borrower, amount);
        });
    }

    @Test
    void shouldRejectLoanWhenAmountIsZeroOrNegative() {
        // SCENARIO:
        // Borrower sudah terverifikasi (KYC = true)
        // Ketika borrower mengajukan pinjaman dengan amount tidak valid
        // Maka sistem harus menolak dengan melempar exception

        // Arrange (Initial Condition)
        // Borrower sudah lolos proses KYC
        Borrower borrower = new Borrower(true, 700);

        // Service untuk pengajuan loan
        LoanService loanService = new LoanService();

        // Amount tidak valid (0 atau negatif)
        BigDecimal amount = BigDecimal.valueOf(0);

        // Act & Assert
        // Sistem harus throw exception karena amount tidak valid
        assertThrows(IllegalArgumentException.class, () -> {
        loanService.createLoan(borrower, amount);
        });
    }

    @Test
    void shouldApproveLoanWhenCreditScoreHigh() {
        // SCENARIO:
        // Borrower sudah terverifikasi (KYC = true)
        // Credit score tinggi (>= 600)
        // Maka sistem harus menyetujui loan dengan status APPROVED

        // Arrange (Initial Condition)
        // Borrower verified dengan credit score tinggi
        Borrower borrower = new Borrower(true, 700);

        // Service untuk pengajuan loan
        LoanService loanService = new LoanService();

        // Amount valid
        BigDecimal amount = BigDecimal.valueOf(1000);

        // Act (Action)
        // Borrower mengajukan loan
        Loan loan = loanService.createLoan(borrower, amount);

        // Assert (Expected Result)
        // Status loan harus APPROVED
        assertEquals(Loan.Status.APPROVED, loan.getStatus());
    }

    @Test
    void shouldRejectLoanWhenCreditScoreLow() {
        // SCENARIO:
        // Borrower sudah terverifikasi (KYC = true)
        // Credit score rendah (< 600)
        // Maka sistem harus menolak loan dengan status REJECTED

        // Arrange (Initial Condition)
        // Borrower verified dengan credit score rendah
        Borrower borrower = new Borrower(true, 500);

        // Service untuk pengajuan loan
        LoanService loanService = new LoanService();

        // Amount valid
        BigDecimal amount = BigDecimal.valueOf(1000);

        // Act (Action)
        // Borrower mengajukan loan
        Loan loan = loanService.createLoan(borrower, amount);

        // Assert (Expected Result)
        // Status loan harus REJECTED
        assertEquals(Loan.Status.REJECTED, loan.getStatus());
    }
}