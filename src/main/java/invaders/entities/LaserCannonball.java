package invaders.entities;

import invaders.engine.GameEngine;
import invaders.factory.CannonballFactory;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;

import java.io.File;
import java.util.List;

/**
 * Slow cannonball implementation
 */
public class LaserCannonball extends BaseEntity implements Cannonball {
    public LaserCannonball(Vector2D position) {
        super(new String[]{PlayerCannonball.class.getName(),Bunker.class.getName(),Player.class.getName()}, position, 4, 10, new Image(new File("src/main/resources/enemyCannonball.png").toURI().toString(), 4, 10, true, true));
    }


    @Override
    public int getSpeed() {
        return baseSpeed*2;
    }

    @Override
    public void up() {
        return;
    }

    @Override
    public void down() {
        this.getPosition().setY(this.getPosition().getY() + getSpeed());
    }

    @Override
    public void left() {
        return;
    }

    @Override
    public void right() {
        return;
    }

    @Override
    public List<Renderable> update(GameEngine model, List<EntityView> entityViews) {
        this.down();
        if(this.getPosition().getY() >= model.getWindowHeight()-this.getHeight()){
            EntityView thisView = findEntityViewByRenderable(entityViews);
            thisView.markForDelete();
            model.getRenderables().remove(this);
            model.currentEnemyCannonballCountDown();
        }
        Renderable renderable = checkIsColliding(model.getRenderables());
        if(renderable != null){
            this.dealColliding(model,entityViews,renderable);
            renderable.dealColliding(model,entityViews,this);
        }
        return null;
    }

    @Override
    public void dealColliding(GameEngine model, List<EntityView> entityViews,Renderable renderable) {
        EntityView entityView = findEntityViewByRenderable(entityViews);
        entityView.markForDelete();
        model.getRenderables().remove(this);
        model.currentEnemyCannonballCountDown();
        if(renderable instanceof PlayerCannonball){
            model.getPlayer().addScore(2);
        }
    }

    @Override
    public Renderable clone() {
        Cannonball cannonball = CannonballFactory.createCannonball("fast_straight", this.getPosition().getX(), this.getPosition().getY());
        return cannonball;
    }
}
