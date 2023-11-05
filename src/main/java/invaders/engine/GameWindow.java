package invaders.engine;

import java.util.List;
import java.util.ArrayList;

import invaders.entities.EntityViewImpl;
import invaders.entities.SpaceBackground;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import invaders.entities.EntityView;
import invaders.rendering.Renderable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * Render all renderable game objects to the page
 */
public class GameWindow {
    /**
     * Game window width
     */
	private final int width;
    /**
     * Game window height
     */
    private final int height;
    /**
     * Page scene object
     */
	private Scene scene;
    /**
     * Page layout object
     */
    private Pane pane;
    /**
     * game engine object
     */
    private GameEngine model;
    /**
     * Collection of content objects on all pages
     */
    private List<EntityView> entityViews;
    /**
     * game background object
     */
    private Renderable background;

    private double xViewportOffset = 0.0;
    private double yViewportOffset = 0.0;
    private static final double VIEWPORT_MARGIN = 280.0;

    /**
     * Game duration text prompt
     */
    private Text timestampText;

    /**
     * Player Score Tips
     */
    private Text scoreText;

    /**
     * Create game rendering objects
     * @param model game engine object
     * @param width window width
     * @param height window height
     */
	public GameWindow(GameEngine model, int width, int height){
		this.width = width;
        this.height = height;
        this.model = model;
        pane = new Pane();
        scene = new Scene(pane, width, height);
        this.background = new SpaceBackground(model, pane);

        KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler(this.model,this);

        scene.setOnKeyPressed(keyboardInputHandler::handlePressed);
        scene.setOnKeyReleased(keyboardInputHandler::handleReleased);

        entityViews = new ArrayList<EntityView>();
        Font font = new Font(20);
        Color color = Color.WHITE;
        this.timestampText = new Text("times: 1:00");
        timestampText.setFont(font);
        timestampText.setFill(color);
        timestampText.setX(60);
        timestampText.setY(30);
        pane.getChildren().add(timestampText);
        this.scoreText = new Text("score:15");
        scoreText.setFont(font);
        scoreText.setFill(color);
        scoreText.setX(300);
        scoreText.setY(30);
        pane.getChildren().add(scoreText);
    }

    /**
     * Start the game and create a scheduled refresh
     */
	public void run() {
         Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17), t -> this.draw()));

         timeline.setCycleCount(Timeline.INDEFINITE);
         timeline.play();
    }

    /**
     * Draw game content for each frame and update data
     */
    private void draw(){
        model.update(width,height,entityViews);
        int times = model.getTimes();
        int seconds = times*17/1000;
        String text = "times "+seconds/60+":"+(seconds%60 < 10 ? "0"+seconds%60 : seconds%60);
        timestampText.setText(text);
        scoreText.setText("score : "+ model.getPlayer().getScore());
        List<Renderable> renderables = model.getRenderables();
        for (Renderable entity : renderables) {
            boolean notFound = true;
            for (EntityView view : entityViews) {
                if (view.matchesEntity(entity)) {
                    notFound = false;
                    view.update(xViewportOffset, yViewportOffset);
                    break;
                }
            }
            if (notFound) {
                EntityView entityView = new EntityViewImpl(entity);
                entityViews.add(entityView);
                pane.getChildren().add(entityView.getNode());
            }
        }

        for (EntityView entityView : entityViews) {
            if (entityView.isMarkedForDelete()) {
                Node node = entityView.getNode();
                pane.getChildren().remove(node);
                Renderable entity = entityView.getEntity();
                model.getRenderables().remove(entity);
                model.getGameObjects().remove(entity);
            }
        }
        entityViews.removeIf(EntityView::isMarkedForDelete);
    }

    /**
     * Get scene object
     * @return scene object
     */
	public Scene getScene() {
        return scene;
    }

    /**
     * Get the collection of content objects on all pages
     * @return Collection of content objects on all pages
     */
    public List<EntityView> getEntityViews() {
        return entityViews;
    }

    /**
     * Clear the collection of content objects on all pages
     */
    public void removeAllEntityView(){
        for (int i = 0; i < this.entityViews.size(); i++) {
            EntityView view = this.entityViews.get(i);
            pane.getChildren().remove(view.getNode());
        }
        this.entityViews.clear();
    }
}
