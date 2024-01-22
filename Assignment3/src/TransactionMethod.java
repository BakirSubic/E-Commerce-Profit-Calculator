interface TransactionMethod {
    double calculateTransactionFee(double amount);
}

class WalletTransaction implements TransactionMethod {
    @Override
    public double calculateTransactionFee(double amount) {
        return 0.0;
    }
}

class CardTransaction implements TransactionMethod {
    private static final double cardFee = 5.0;

    @Override
    public double calculateTransactionFee(double amount) {
        return amount * (cardFee / 100.0);
    }
}

class VisaTransaction implements TransactionMethod {
    private static final double visaFee = 2.0;

    @Override
    public double calculateTransactionFee(double amount) {
        return amount * (visaFee / 100.0);
    }
}

class MastercardTransaction implements TransactionMethod {
    private static final double mastercardFee = 3.0;

    @Override
    public double calculateTransactionFee(double amount) {
        return amount * (mastercardFee / 100.0);
    }
}

class OtherTransaction implements TransactionMethod {
    private static final double otherTransactionFee = 10.0;

    @Override
    public double calculateTransactionFee(double amount) {
        return amount * (otherTransactionFee / 100.0);
    }
}

class TransactionContext {
    private TransactionMethod paymentStrategy;

    public TransactionContext(TransactionMethod paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public double getTransactionFee(double amount) {
        double transactionFee = paymentStrategy.calculateTransactionFee(amount);

        return transactionFee;
    }
}
