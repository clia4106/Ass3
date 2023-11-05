package invaders.entities;

import invaders.GameObject;
import invaders.engine.GameEngine;
import invaders.factory.CannonballFactory;
import invaders.logic.Damagable;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;

import javafx.scene.image.Image;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 玩家对象
 */
public class Player extends BaseEntity implements Moveable, Damagable , GameObject {

    /**
     * 剩余生命提示
     */
    private TextPrompt livesPrompt;

    /**
     * 当前剩余生命
     */
    private int lives = 3;

    /**
     * 上一次射击时间
     */
    Date lastShootTime = null ;

    /**
     * move speed
     */
    private double moveSpeed;

    /**
     * score
     */
    private int score;

    public Player(Vector2D position, int lives, double moveSpeed, TextPrompt livesPrompt){
        super(new String[]{NormalCannonball.class.getName(),LaserCannonball.class.getName()}, position, 25, 30,new Image(new File("src/main/resources/player.png").toURI().toString(), 25, 30, true, true));
        this.moveSpeed = moveSpeed;
        this.lives = lives;
        this.livesPrompt = livesPrompt;
    }

    @Override
    public void takeDamage(double amount) {
        this.lives -= amount;
        this.livesPrompt.setImageKey(String.valueOf(this.lives));
    }

    @Override
    public double getHealth() {
        return this.lives;
    }

    @Override
    public boolean isAlive() {
        return this.lives > 0;
    }

    @Override
    public void up() {
        return;
    }

    @Override
    public void down() {
        return;
    }

    @Override
    public void left() {
        this.getPosition().setX(this.getPosition().getX() - moveSpeed);
    }

    @Override
    public void right() {
        this.getPosition().setX(this.getPosition().getX() + moveSpeed);
    }

    public boolean shoot(List<Renderable> renderables, List<GameObject> gameobjects){
        boolean isShoot = false;
        if(lastShootTime == null || (new Date().getTime() - lastShootTime.getTime() > 1000)){
            lastShootTime = new Date();
            isShoot = true;
            Cannonball cannonball = CannonballFactory.createCannonball("player", this.getPosition().getX() + (this.getWidth() / 2), this.getPosition().getY() - 10);
            renderables.add(cannonball);
            gameobjects.add(cannonball);
        }
        return isShoot;
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND;
    }

    @Override
    public List<Renderable> update(GameEngine model, List<EntityView> entityViews) {
        if(model.isLeft()){
            this.left();
        }
        if(model.isRight()){
            this.right();
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
        this.takeDamage(1);
    }

    public int getLives() {
        return lives;
    }

    @Override
    public Renderable clone() {
       Player player = new Player(new Vector2D(this.getPosition().getX(), this.getPosition().getY()),this.getLives(),this.getMoveSpeed(),new TextPrompt(this.livesPrompt));
       if(this.getLastShootTime() != null){
           player.setLastShootTime(new Date(this.getLastShootTime().getTime()));
       }
       player.setScore(this.getScore());
       return player;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public Date getLastShootTime() {
        return lastShootTime;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public TextPrompt getLivesPrompt() {
        return livesPrompt;
    }

    public void setLastShootTime(Date lastShootTime) {
        this.lastShootTime = lastShootTime;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
