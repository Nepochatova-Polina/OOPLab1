/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author polina
 */
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
public class Ball{
    
     public  Geometry makeCannonBall() {
    Geometry ball_geo = new Geometry("cannon ball", Main.getSphere());
    ball_geo.setMaterial(Main.getStoneMat());
    ball_geo.setLocalTranslation(new Vector3f(-10, 0, 0));
    Main.setBallPhy(new RigidBodyControl(1f));
    ball_geo.addControl(Main.getBallPhy());
    Main.getbulletAppState().getPhysicsSpace().add(Main.getBallPhy());
    Main.getBallPhy().setLinearVelocity(new Vector3f(0, 0.4f, -1).mult(20));
    Main.getBallPhy().setGravity(new Vector3f(0, -Main.getGravity(), 0));
    return ball_geo;
  }
}
