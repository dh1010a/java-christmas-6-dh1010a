package christmas.domain;

import christmas.domain.orders.Orders;
import christmas.domain.receipt.Receipt;
import christmas.domain.visitDate.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class EventPlanner {
    private InputView inputView;
    private OutputView outputView;
    private Orders orders;
    private VisitDate visitDate;

    public EventPlanner(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startEventPlan() {
        visitDate = getVisitDateFromCustomer();
        orders = getOrderFromCustomer();
        showMenuToCustomer();

        Receipt receipt = getReceiptFromCalculator();
        printReceipt(receipt);

        Badge badge = Badge.getBadge(receipt.getIntTotalBenefitPrice());
        outputView.printEventBadge(badge.getTitle());
    }

    private void printReceipt(Receipt receipt) {
        outputView.printTotalOrderPriceBeforeDiscount(receipt.getTotalOrderPrice());
        outputView.printGiftMenu(receipt.getGiftMenu());
        outputView.printBenefitDetails(receipt.getBenefitDetails());
        outputView.printTotalBenefitPrice(receipt.getTotalBenefitPrice());
        outputView.printFinalPaymentPrice(receipt.getFinalPaymentPrice());
    }

    private Receipt getReceiptFromCalculator() {
        DiscountCalculator discountCalculator = new DiscountCalculator(orders, visitDate);
        return discountCalculator.calculateAndPrintReceipt();

    }

    private void showMenuToCustomer() {
        outputView.printAnnounceMessage(visitDate.getFullDate());
        outputView.printOrderMenu(orders.toString());
    }

    private VisitDate getVisitDateFromCustomer() {
        int dateInput = inputView.getVisitDate();

        try {
            return new VisitDate(dateInput);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return getVisitDateFromCustomer();
        }
    }

    private Orders getOrderFromCustomer() {
        List<String> orderContents = inputView.getUserOrderContents();

        try {
            return new Orders(orderContents);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return getOrderFromCustomer();
        }
    }
}
