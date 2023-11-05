package invaders.entities;

import invaders.GameObject;
import invaders.engine.GameEngine;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import invaders.status.BunkerStatus;
import invaders.status.impl.BunkerStatusOne;
import invaders.status.impl.BunkerStatusTwo;
import invaders.status.impl.BunkerStatusZero;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * cover object
 */
public class Bunker extends BaseEntity implements GameObject {

    /**
     * all status
     */
    List<BunkerStatus> allStatus;
    /**
     * current status
     */
    private BunkerStatus status;
    public Bunker(Vector2D position, double width, double height) {
        super(new String[]{}, position, width, height, null);
        allStatus = new ArrayList<BunkerStatus>();
        allStatus.add(new BunkerStatusZero(position,(int)width,(int)height));
        allStatus.add(new BunkerStatusOne(position,(int)width,(int)height));
        allStatus.add(new BunkerStatusTwo(position,(int)width,(int)height));
        this.status = allStatus.get(0);
    }

    @Override
    public Image getImage() {
        return status.getImage();
    }

    @Override
    public List<Renderable> update(GameEngine model, List<EntityView> entityViews) {

        return null;
    }

    @Override
    public void dealColliding(GameEngine model, List<EntityView> entityViews,Renderable renderable) {
        if(allStatus.indexOf(this.status) >= allStatus.size()-1){
            EntityView entityView = findEntityViewByRenderable(entityViews);
            entityView.markForDelete();
            model.getRenderables().remove(this);
        }else{
            this.status = allStatus.get(allStatus.indexOf(this.status)+1);
        }
    }




    public void setStatus(BunkerStatus status) {
        this.status = status;
    }

    public List<BunkerStatus> getAllStatus() {
        return allStatus;
    }

    public void setAllStatus(List<BunkerStatus> allStatus) {
        this.allStatus = allStatus;
    }

    @Override
    public Bunker clone()  {

        Bunker bunker = new Bunker(new Vector2D(this.getPosition().getX(), this.getPosition().getY()),this.getWidth(),this.getWidth());
        List<BunkerStatus> statuses = this.getAllStatus();
        List<BunkerStatus> currentStatuses = new ArrayList<>();
        for(BunkerStatus status : statuses){
            currentStatuses.add(status);
        }
        bunker.setAllStatus(currentStatuses);
        bunker.setStatus(currentStatuses.get(0));
        return bunker;
    }
}
