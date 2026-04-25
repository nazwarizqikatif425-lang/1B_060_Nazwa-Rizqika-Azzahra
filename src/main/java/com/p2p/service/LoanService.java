package com.p2p.service;

import java.math.BigDecimal;

import com.p2p.domain.Borrower;
import com.p2p.domain.Loan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoanService {

    private static final Logger logger = LogManager.getLogger(LoanService.class);

    public Loan createLoan(Borrower borrower, BigDecimal amount) {
        logger.info("Memulai proses pengajuan loan");
        //====================================
        // VALIDASI UTAMA (delegasi ke domain)
        //====================================
        validateBorrower(borrower);

        //=========================
        // VALIDASI AMOUNT (TC-02)
        //=========================
        // Jika amount 0 atau negatif, proses harus dihentikan
        validateAmount(amount);

        // ===========================
        // CREATE LOAN (domain object)
        // ===========================
        Loan loan = new Loan();

        //===================================
        // BUSSINESS ACTION (domain behavior)
        //===================================
        // Jika credit score tinggi → APPROVED
        // Jika tidak → REJECTED
        if (borrower.getCreditScore() >= 600) {
            loan.approve();
            logger.info("Loan APPROVED, credit score: {}", borrower.getCreditScore());
        } else {
            loan.reject();
            logger.info("Loan REJECTED, credit score: {}", borrower.getCreditScore());
        }

        return loan;
    }

    //===========================
    // PRIVATE VALIDATION METHODE
    //===========================
    private void validateBorrower(Borrower borrower) {
        if (!borrower.canApplyLoan()) {
            logger.warn("Loan ditolak, borrower belum terverifikasi");
            throw new IllegalArgumentException("Borrower not verified");
        }
    }

    private void validateAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            logger.warn("Loan ditolak, amount tidak valid: {}", amount);
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
    }
}
