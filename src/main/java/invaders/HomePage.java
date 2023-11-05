package invaders;

import invaders.command.CommandContext;
import invaders.command.CommandImpl;
import invaders.command.Operation;
import invaders.engine.GameEngine;
import invaders.engine.GameWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Select Game Difficulty Page Object
 */
public class HomePage {


    /**
     * Home simple interest object
     */
    public static HomePage _HOMEPAGE = new HomePage();

    /**
     * Home page object
     */
    private HomePage(){}


    /**
     * Get the simple interest object on the home page
     * @return Return to home page simple interest object
     */
    public static HomePage getHomepage(){
        return _HOMEPAGE;
    }

    /**
     * Draw home page content
     * @param stage On which stage object to display
     */
    public void run (Stage stage){
        Pane homePane = new Pane();
        Button easy = new Button("easy");
        easy.setLayoutX(100);
        easy.setLayoutY(350);
        easy.setPrefWidth(100);
        easy.setPrefHeight(50);

        easy.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                GameEngine model = new GameEngine("src/main/resources/config_easy.json");
                GameWindow window = new GameWindow(model, model.getWindowWidth(), model.getWindowHeight());
                CommandContext.getInstance().init(window,model,new CommandImpl(new Operation()));
                window.run();
                stage.setScene(window.getScene());
            }
        });
        homePane.getChildren().add(easy);


        Button medium = new Button("medium");
        medium.setLayoutX(250);
        medium.setLayoutY(350);
        medium.setPrefWidth(100);
        medium.setPrefHeight(50);
        medium.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                GameEngine model = new GameEngine("src/main/resources/config_medium.json");
                GameWindow window = new GameWindow(model, model.getWindowWidth(), model.getWindowHeight());
                CommandContext.getInstance().init(window,model,new CommandImpl(new Operation()));
                window.run();
                stage.setScene(window.getScene());
            }
        });
        homePane.getChildren().add(medium);

        Button hard = new Button("hard");

        hard.setLayoutX(400);
        hard.setLayoutY(350);
        hard.setPrefWidth(100);
        hard.setPrefHeight(50);
        hard.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                GameEngine model = new GameEngine("src/main/resources/config_hard.json");
                GameWindow window = new GameWindow(model, model.getWindowWidth(), model.getWindowHeight());
                CommandContext.getInstance().init(window,model,new CommandImpl(new Operation()));
                window.run();
                stage.setScene(window.getScene());
            }
        });
        homePane.getChildren().add(hard);
        Scene indexScene = new Scene(homePane,600,800);
        stage.setScene(indexScene);
    }


}
