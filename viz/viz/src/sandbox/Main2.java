package sandbox;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.system.AppSettings;
import com.jme3.util.BufferUtils;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import viz.components.Element;
import viz.components.Layout;
import viz.components.Point;

/**
 * How to create custom meshes by specifying vertices
 * We render the mesh in three different ways, once with a solid blue color,
 * once with vertex colors, and once with a wireframe material.
 * @author KayTrance
 */
public class Main2 extends SimpleApplication {

    static AppSettings settings = new AppSettings(true);
    
    Mesh m = new Mesh();
    Geometry coloredMesh;
    Boolean isRunning=true;
    

    public static void main(String[] args){
       // AppSettings settings = new AppSettings(true);
        settings.setResolution(640, 480);
        settings.setTitle("Test");
        settings.setFrameRate(60);
        //MyGame app = new MyGame();
        Main2 app = new Main2();
        app.setSettings(settings);
        app.setShowSettings(false);
        app.start();
    }

    @Override
    public void simpleInitApp() {
      
        Layout la = new Layout(3);
        la.elementMap.put(0, new int[]{0,1,2});
        
        la.colorMap.get(0).b = .5f;
        la.vert[1].x=5 ; la.colorMap.get(1).r=0.3f; 
        la.vert[2].y=3 ; la.colorMap.get(2).r=0.9f; 
        
        la.packing();
        
        //System.out.print("la.colorArray:"+la.colorArray+"\n");
        //System.out.print("la.vert:"+la.vert+"\n");
        //System.out.print("la.texCoord:"+la.texCoord+"\n");
        for (int i=0; i<la.drawIndex.length; i++){
            System.out.print("la.drawIndex:"+la.drawIndex[i]+"\n");
        }
        Element e = new Element(3);
        
        e.p[0].b=0.5f;
        e.p[1].x=5; e.p[1].r=0.3f;
        e.p[2].y=3; e.p[2].r=0.9f;

        // Indexes. We define the order in which mesh should be constructed
        //int [] indexes = {2,0,1,1,3,2};
        //int [] indexes = {2,0,1};
        
        // Setting buffers  // what is this?
        m.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(la.vert));
        m.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(la.texCoord));
        m.setBuffer(Type.Index, 3, BufferUtils.createIntBuffer(la.drawIndex));
        //m.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(e.getVertices()));
        //m.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(e.getTexCoord()));
        //m.setBuffer(Type.Index, 3, BufferUtils.createIntBuffer(indexes));
        m.updateBound();

        // *************************************************************************
        // Second mesh uses vertex colors to color each vertex
        // *************************************************************************

        coloredMesh = new Geometry ("ColoredMesh", m);
        
        Material matVC = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matVC.setBoolean("VertexColor", true);

        //We have 4 vertices and 4 color values for each of them.
        //If you have more vertices, you need 'new float[yourVertexCount * 4]' here!
//        float[] colorArray = new float[3*4];
//        int colorIndex = 0;       
//        
//        //Set custom RGBA value for each Vertex. Values range from 0.0f to 1.0f
//        for(int i = 0; i < 3; i++){
//           //System.out.print("red colorIndex before:"+colorIndex);
//            // Red value (is increased by .2 on each next vertex here)
//           colorArray[colorIndex++]= e.p[i].r;
//           //System.out.print("red colorIndex after:"+colorIndex);
//           // Green value (is reduced by .2 on each next vertex)
//           colorArray[colorIndex++]= e.p[i].g;
//           // Blue value (remains the same in our case)
//           colorArray[colorIndex++]= e.p[i].b;
//           // Alpha value (no transparency set here)
//           colorArray[colorIndex++]= e.p[i].a;
//        }
        
        //System.out.print("colorIndex after:"+colorIndex+"\n");
                   
        for(int i = 0; i < la.colorArray.length; i++){
            System.out.print("la.colorIndex :"+i + " value: "+la.colorArray[i]+"\n");
        }        

//        // Red value (is increased by .2 on each next vertex here)
//        colorArray[0] = 0.1f ;
//        // Green value (is reduced by .2 on each next vertex)
//        colorArray[colorIndex++] = 0.9f - (0.2f * i);
//        // Blue value (remains the same in our case)
//        colorArray[colorIndex++] = 0.5f;
//        // Alpha value (no transparency set here)
//        colorArray[colorIndex++] = 1.0f;

        // Set the color buffer
        //m.setBuffer(Type.Color, 4, la.colorArray);
        m.setBuffer(Type.Color, 4, la.colorArray);
        coloredMesh.setMaterial(matVC);
        // move mesh a bit so that it doesn't intersect with the first one
        //coloredMesh.setLocalTranslation(4, 0, 0);
        rootNode.attachChild(coloredMesh);
        initKeys();
        
    }
    
//    @Override
//    public void simpleUpdate(float tpf)  {
//        float a=1;
//     
//        //coloredMesh.scale(a+tpf/50);
//
//    }
    
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