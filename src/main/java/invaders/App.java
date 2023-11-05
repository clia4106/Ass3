package invaders;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Start application main class
 */
public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start application
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage primaryStage) {

        HomePage homepage = HomePage.getHomepage();
        homepage.run(primaryStage);
        primaryStage.setTitle("Space Invaders");
        primaryStage.show();

    }
}
