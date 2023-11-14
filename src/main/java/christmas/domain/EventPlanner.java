package christmas.domain;

import christmas.domain.orders.Orders;
import christmas.domain.visitDate.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class EventPlanner {
    private InputView inputView;
    private OutputView outputView;

    public EventPlanner(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startEventPlan() {
        VisitDate visitDate = getVisitDateFromCustomer();
        Orders orders = getOrderFromCustomer();
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
