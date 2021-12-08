package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.font.Rectangle;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Sphere.TextureMode;
import com.jme3.scene.shape.Cylinder;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.util.SkyFactory;

public class Main extends SimpleApplication {

  public static void main(String args[]) {
    Main app = new Main();
    app.start();
  }

  private static BulletAppState bulletAppState;
  public static void setBulletAppState(BulletAppState newbulletAppState){bulletAppState = newbulletAppState;}
  public static BulletAppState getbulletAppState(){return bulletAppState;}
  
  private static int gravity = 10;
  public static void setGravity(int newGravity){gravity = newGravity;}
  public static int getGravity(){return gravity;}
  
  private static Material stone_mat;
   public static Material getStoneMat(){return stone_mat;}
  
  private static Material floor_mat;
    public static Material getfloor_mat(){return floor_mat;}
    
  private static Material cannon_mat;
   public static Material getcannon_mat(){ return cannon_mat;}

  private static RigidBodyControl ballPhy;
  public static RigidBodyControl getBallPhy(){ return ballPhy;}
  public static void setBallPhy(RigidBodyControl ball){ballPhy = ball;}
  
  private static final Sphere   sphere;
  public static Sphere getSphere(){return sphere;}

  private static RigidBodyControl      floorPhy;
  public static RigidBodyControl getFloorPhy(){return floorPhy;}
  public static void setFloorPhy(RigidBodyControl floorP){floorPhy = floorP;}
    
  private static final Box floor;
   public static Box getFloor(){return floor;}
   
  private static final Cylinder cannon;
   public static Cylinder getCannon(){return cannon;}
  private BitmapText txt; 

  static {
    
    sphere = new Sphere(32, 32, 0.4f, true, false);
    sphere.setTextureMode(TextureMode.Projected);
    
    floor = new Box(20, 0.1f, 100f);
    floor.scaleTextureCoordinates(new Vector2f(50, 10));
    
    cannon = new Cylinder(20, 50, 1, 2, true);
    cannon.scaleTextureCoordinates(new Vector2f(10, 20));
  }

  @Override
  public void simpleInitApp() {
    
    bulletAppState = new BulletAppState();
    stateManager.attach(bulletAppState);

    cam.setLocation(new Vector3f(0, 4f, 6f));
    cam.lookAt(new Vector3f(2, 2, 0), Vector3f.UNIT_Y);
    rootNode.attachChild(SkyFactory.createSky(assetManager, "Textures/BrightSky.dds", SkyFactory.EnvMapType.CubeMap));
    inputManager.addMapping("Shoot", new KeyTrigger(KeyInput.KEY_SPACE));
    inputManager.addMapping("IncreaseGravity", new KeyTrigger(KeyInput.KEY_O));
    inputManager.addMapping("DecreaseGravity", new KeyTrigger(KeyInput.KEY_L));
    inputManager.addListener(actionListener, "Shoot", "IncreaseGravity", "DecreaseGravity");
    
    initMaterials();
    rootNode.attachChild(new Floor().initFloor());
    rootNode.attachChild(new Cannon().initCannon());
    rootNode.attachChild(initTextGravity());
  }

  private final ActionListener actionListener = new ActionListener() {
    @Override
    public void onAction(String name, boolean keyPressed, float tpf) {
        if (!keyPressed)
            return;
        switch (name) {
            case "Shoot":
                rootNode.attachChild(new Ball().makeCannonBall());
                break;
            case "IncreaseGravity":
                gravity += 1;
                break;
            case "DecreaseGravity":
                gravity -= 1;
                break;
        }
        txt.setText("Gravity: " + gravity + "m/s^2");
    }
  };

  public void initMaterials() {

    stone_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    TextureKey key2 = new TextureKey("Textures/Terrain/Rock/Rock.PNG");
    key2.setGenerateMips(true);
    Texture tex2 = assetManager.loadTexture(key2);
    stone_mat.setTexture("ColorMap", tex2);

    floor_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    TextureKey key3 = new TextureKey("Textures/Terrain/splat/grass.jpg");
    key3.setGenerateMips(true);
    Texture tex3 = assetManager.loadTexture(key3);
    tex3.setWrap(WrapMode.Repeat);
    floor_mat.setTexture("ColorMap", tex3);
    
    cannon_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    TextureKey key4 = new TextureKey("Textures/Terrain/splat/dirt.jpg");
    key4.setGenerateMips(true);
    Texture tex4 = assetManager.loadTexture(key4);
    tex4.setWrap(WrapMode.Repeat);
    cannon_mat.setTexture("ColorMap", tex4);
  }
  
   public BitmapText initTextGravity(){
       BitmapFont fnt = assetManager.loadFont("Interface/Fonts/Default.fnt");
        txt = new BitmapText(fnt, false);
        txt.setBox(new Rectangle(-80, 30, 600, 300));
        txt.setQueueBucket(RenderQueue.Bucket.Transparent);
        txt.setSize( 11f );
        txt.setLocalTranslation(0, 20, -100);
        txt.setText("Gravity: " + gravity + "m/s^2");
        return txt;
   }

  
   
}
