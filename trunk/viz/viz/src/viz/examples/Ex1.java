package viz.examples;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import java.util.HashMap;
import java.util.Map;
import viz.components.Layout;

public class Ex1 {
    
    public Ex1() {
   
    }
    
    public Layout create() {
        
        Layout la = new Layout(3);
        la.elementMap.put(0, new int[]{0,1,2});
        
        la.colorMap.get(0).b = .5f;
        la.vert[1].x=5 ; la.colorMap.get(1).g=0.6f; 
        la.vert[2].y=3 ; la.colorMap.get(2).r=0.9f; 
        
        la.packing();
        
        return la;
        
    }
 
     

}
