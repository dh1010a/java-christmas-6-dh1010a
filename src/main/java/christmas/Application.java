package christmas;

import christmas.domain.EventPlanner;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        EventPlanner eventPlanner = new EventPlanner(new InputView(), new OutputView());
        eventPlanner.startEventPlan();
    }
}
