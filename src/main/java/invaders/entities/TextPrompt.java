package invaders.entities;

import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 文本提示抽象
 */
public class TextPrompt extends BaseEntity  {

    /**
     * All displayable text prompt mappings
     */
    private Map<String,Image> imageMap = new HashMap<>();
    /**
     * Currently the text prompt key is to be displayed
     */
    private String imageKey ;

    private final String name ;
    public TextPrompt(TextPrompt textPrompt){
        super(new String[]{}, new Vector2D(textPrompt.getPosition().getX(),textPrompt.getPosition().getY()),textPrompt.getWidth(),textPrompt.getHeight(), null);
        this.imageKey = textPrompt.getImageKey();
        this.imageMap = textPrompt.getImageMap();
        this.name = textPrompt.getName();
    }

    public TextPrompt(Map<String,String> textMap, Vector2D location, int type, double width, double height, String name){
        super(new String[]{}, location,width,height, null);
        this.name = name;
        for (String key :textMap.keySet()){
            this.imageKey = key;
            String value = textMap.get(key);
            Image image = new Image(createImage((int) width, (int) height, value, type));
            imageMap.put(key,image);
        }
    }

    private static InputStream createImage(int width, int height, String text, int type){
        try {
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = bufferedImage.getGraphics();
            graphics.setColor(Color.black);
            graphics.fillRect(0,0,(int)width,(int)height);
            graphics.setColor(Color.white);
            Font font = new Font(null, Font.BOLD, 20);
            graphics.setFont(font);
            int x = 30;
            if(type ==1){
                x =  (width-text.length()*font.getSize())/2;
            }else if(type ==2){
                x = width - 30 -text.length()*font.getSize();
            }
            graphics.drawString(text,x,20);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage,"png",bos);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            return bis;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new ByteArrayInputStream(new byte[0]);
    }

    @Override
    public Image getImage() {
        return imageMap.get(this.imageKey);
    }

    @Override
    public Renderable clone() {
        return new TextPrompt(this);
    }

    public Map<String, Image> getImageMap() {
        return imageMap;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public String getName() {
        return name;
    }
}
