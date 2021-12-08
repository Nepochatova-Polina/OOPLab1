/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.scene.Geometry;

/**
 *
 * @author polina
 */
public class Cannon {
   public  Geometry initCannon(){
    Geometry cannonGeo = new Geometry("Cannon", Main.getCannon());
    cannonGeo.setMaterial(Main.getcannon_mat());
    cannonGeo.setLocalTranslation(-10, 0.5f, 0);
    cannonGeo.rotate(0.4f, 0f, 0);
    return cannonGeo;
  }
}
