public class PaymentFactory {
    public static TransactionMethod createPaymentStrategy(String paymentType) {

        if(paymentType.equalsIgnoreCase("wallet")) return new WalletTransaction();
        else if (paymentType.equalsIgnoreCase("bankcard")) return new CardTransaction();
        else if (paymentType.equalsIgnoreCase("visa")) return new VisaTransaction();
        else if (paymentType.equalsIgnoreCase("mastercard")) return new MastercardTransaction();
        else return new OtherTransaction();
    }
}