package nhom55.hcmuaf.controller.paypal;
import java.util.*;

import com.paypal.api.payments.*;
import com.paypal.base.rest.*;
import nhom55.hcmuaf.beans.Bills;

public class PaymentServices {
    private static final String CLIENT_ID = "AXjNIAvmyN7nqjWNdYrdjoX94D4vcaJ2LWHdXqz8qular76_jCvfH-uOSdCojCK-fgpo920v7bFA3Hm1";
    private static final String CLIENT_SECRET = "EIVotgOAxlAu80qDQnGxTWjKb_MsI0hxy-LsqlAzPWoQepxn_DmODeEDxVjYDIhyP_zmqOBye41xrXS-";
    private static final String MODE = "sandbox";

    public String authorizePayment(Bills bill )
            throws PayPalRESTException {

        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(bill);
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        Payment approvedPayment = requestPayment.create(apiContext);

        return getApprovalLink(approvedPayment);

    }

    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("William")
                .setLastName("Peterson")
                .setEmail("sb-crpg031604936@personal.example.com");


        payer.setPayerInfo(payerInfo);

        return payer;
    }
    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }
        System.out.println(approvalLink);
        return approvalLink;
    }
    private List<Transaction> getTransactionInformation(Bills bill) {
        Details details = new Details();
        details.setShipping(String.format("%.3f", bill.getDeliveryFee() / 25000.0));  // Trả về 1.00
        details.setSubtotal(String.format("%.3f", bill.getTotalPrice() / 25000.0));  // Trả về 4.00

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(String.format("%.3f", (bill.getDeliveryFee()+bill.getTotalPrice()) / 25000.0));
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(bill.getProductList());

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();

        Item item = new Item();
        item.setCurrency("USD");
        item.setName(bill.getProductList());
        item.setPrice(String.format("%.3f", bill.getTotalPrice() / 25000.0));  // Trả về 4.00
        item.setQuantity("1");

        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);

        return listTransaction;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/page/shop/shop-forward");
        redirectUrls.setReturnUrl("http://localhost:8080/paypal/summary-bill-paypal");

        return redirectUrls;
    }
    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }
}
