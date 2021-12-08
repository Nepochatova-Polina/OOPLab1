package mygame;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Geometry;

public class Floor {
     public Geometry initFloor() {
    Geometry floor_geo = new Geometry("Floor", Main.getFloor());
    floor_geo.setMaterial(Main.getfloor_mat());
    floor_geo.setLocalTranslation(0, -0.1f, 0);
    
    Main.setFloorPhy( new RigidBodyControl(0.0f));
    floor_geo.addControl(Main.getFloorPhy());
    Main.getbulletAppState().getPhysicsSpace().add(Main.getFloorPhy());
    return floor_geo;
  }
    
}
