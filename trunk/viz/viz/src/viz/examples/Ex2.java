package viz.examples;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import java.util.HashMap;
import java.util.Map;
import viz.components.Layout;

public class Ex2 {
    
    public Ex2() {
   
    }
    
    public Layout create() {
        
        Layout la = new Layout(4);
        la.elementMap.put(0, new int[]{0,1,2});
        la.elementMap.put(1, new int[]{1,3,2});
        
        la.colorMap.get(0).b = .5f;
        la.vert[1].x=5 ; la.colorMap.get(1).g=0.6f; 
        la.vert[2].y=3 ; la.colorMap.get(2).r=0.9f; 
        la.vert[3].x=4 ;la.vert[3].y=4 ; la.colorMap.get(3).b=0.9f; 
        
        la.packing();
        
        return la;
        
    }
 
     

}
