package sample.classes.threads;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import sample.classes.threads.tasks.FillBasketTask;
import sample.controllers.SceneController;

import java.util.concurrent.CountDownLatch;
// TODO receive ObservableLists to baskets and groups, threads teams and put them in baskets, ?then return?
/**
 * Main class for drawing logic and more.
 */
public class Draw extends Service {
    CountDownLatch fillBasketsLatch = new CountDownLatch(SceneController.NUMBER_OF_BASKETS);
    private FillBasketTask fillBasketTask;

    public Draw() {
    }

    public void startDraw() {
        createTask();
    }

    // task for assigning teams to baskets

    // task for putting teams to groups

    // task that handles ui events - moving teams

    @Override
    protected Task<Void> createTask() {
        // lambda expression, which returns Task<Void> object and overrides call method
        return new Task<Void>() {
            @Override
            protected Void call() {
                Platform.runLater(() -> {
                });
                // losowanie i zapelnianie grup (glowna petla for)
                return null;
            }
        };
    }

    public void updateGUI() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // TODO GUI operations here
            }
        });
    }
}