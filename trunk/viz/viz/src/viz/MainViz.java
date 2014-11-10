package viz;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Spatial;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.util.BufferUtils;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;

import viz.components.Layout;
import viz.examples.*;

/**
 * How to create custom meshes by specifying vertices
 * We render the mesh in three different ways, once with a solid blue color,
 * once with vertex colors, and once with a wireframe material.
 * @author KayTrance
 */
public class MainViz extends SimpleApplication {

    static AppSettings settings = new AppSettings(true);
    
    Mesh m = new Mesh();
    Geometry coloredMesh;
    Boolean isRunning=true;
    

    public static void main(String[] args){
       // AppSettings settings = new AppSettings(true);
        settings.setResolution(640, 480);
        settings.setTitle("Test");
        settings.setFrameRate(60);
        MainViz app = new MainViz();
        app.setSettings(settings);
        app.setShowSettings(false);
        app.start();
    }

    @Override
    public void simpleInitApp() {
      
        work();
        
    }
    
//    @Override
//    public void simpleUpdate(float tpf)  {
//        float a=1;
//     
//        //coloredMesh.scale(a+tpf/50);
//
//    }
    
    public void work(){
        

        //Layout la = new Ex1().create();
        Layout la = new Ex2().create();
        
        //System.out.print("la.colorArray:"+la.colorArray+"\n");
        //System.out.print("la.vert:"+la.vert+"\n");
        //System.out.print("la.texCoord:"+la.texCoord+"\n");
//        for (int i=0; i<la.drawIndex.length; i++){
//            System.out.print("la.drawIndex:"+la.drawIndex[i]+"\n");
//        }
 
        // Indexes. We define the order in which mesh should be constructed
        //int [] indexes = {2,0,1,1,3,2};
        
        // Setting buffers  // what is this?
        m.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(la.vert));
        m.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(la.texCoord));
        m.setBuffer(Type.Index, 3, BufferUtils.createIntBuffer(la.drawIndex));
        m.updateBound();

        coloredMesh = new Geometry ("ColoredMesh", m);
        
        Material matVC = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matVC.setBoolean("VertexColor", true);
              
        for(int i = 0; i < la.colorArray.length; i++){
            System.out.print("la.colorIndex :"+i + " value: "+la.colorArray[i]+"\n");
        }        

        m.setBuffer(Type.Color, 4, la.colorArray);
        coloredMesh.setMaterial(matVC);
        // move mesh a bit so that it doesn't intersect with the first one
        //coloredMesh.setLocalTranslation(4, 0, 0);
        rootNode.attachChild(coloredMesh);
        
        
        
     // Create a wall with a simple texture from test_data
        Box box = new Box(2.5f,2.5f,1.0f);
        Spatial wall = new Geometry("Box", box );
        Material mat_brick = new Material( 
            assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat_brick.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
        mat_brick.setTexture("ColorMap", assetManager.loadTexture("Textures/caustics.jpg"));
        wall.setMaterial(mat_brick);
        wall.setLocalTranslation(2.0f,-2.5f,0.0f);
        rootNode.attachChild(wall);
        
        Geometry wfGeom = new Geometry("wireframeGeometry", m);
        Material matWireframe = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matWireframe.setColor("Color", ColorRGBA.Green);
        matWireframe.getAdditionalRenderState().setWireframe(true);
        wfGeom.setMaterial(matWireframe);
        //wfGeom.setLocalTranslation(4, 4, 0);
        rootNode.attachChild(wfGeom);
        
        //matVC.getAdditionalRenderState().setWireframe(true);
        
        initKeys();      
        
        
        
    }
    
     /** Custom Keybinding: Map named actions to inputs. */
  private void initKeys() {
    // You can map one or several inputs to one named action
    inputManager.addMapping("Pause",  new KeyTrigger(KeyInput.KEY_P));
    inputManager.addMapping("Increase",  new KeyTrigger(KeyInput.KEY_J));
    inputManager.addMapping("Decrease",  new KeyTrigger(KeyInput.KEY_K));

    // Add the names to the action listener.
    inputManager.addListener(actionListener,"Pause");
    inputManager.addListener(analogListener,"Increase", "Decrease");
 
  }
 
  private ActionListener actionListener = new ActionListener() {
    public void onAction(String name, boolean keyPressed, float tpf) {
      if (name.equals("Pause") && !keyPressed) {
        isRunning = !isRunning;
        System.out.println("P pressed");
      }
    }
  };
 
  private AnalogListener analogListener = new AnalogListener() {
    public void onAnalog(String name, float value, float tpf) {
      if (isRunning) {
        if (name.equals("Increase")) {
          coloredMesh.scale(1f+value);
          Vector3f v = coloredMesh.getLocalTranslation();
          coloredMesh.setLocalTranslation(v.x + value*speed, v.y, v.z);
          settings.setFrameRate(3000);

        }
        if (name.equals("Decrease")) {
          coloredMesh.scale(1f-value);
          settings.setFrameRate(3);

        }

      } else {
        System.out.println("Press P to unpause.");
      }
    }
  };
}