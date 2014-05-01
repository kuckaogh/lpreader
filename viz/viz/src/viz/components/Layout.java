package viz.components;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import java.util.HashMap;
import java.util.Map;

public class Layout {
    
    public Vector3f[] vert;
    public Vector2f[] texCoord;
    public int[] drawIndex;
    public float[] colorArray;
    
    public Map<Integer,int[]> elementMap;  // <element id, vert id>
    public Map<Integer,Color> colorMap;   // <point id, color value>
    
    public int totalElementVert;
    
    public Layout(int vertNumber) {

        vert = new Vector3f[vertNumber];
        texCoord = new Vector2f[vertNumber];
        elementMap = new HashMap<Integer, int[]>();
        colorArray = new float[4*vertNumber];
        colorMap = new HashMap<Integer, Color>();
        totalElementVert = 0;
        for (int i=0; i< vertNumber; i++){
            vert[i]= new Vector3f();
            texCoord[i]= new Vector2f();
            colorMap.put(i, new Color());
        }     
        
    }
    
    public void packing () {
        
        assginColorArray();
        assginDrawIndex();
        assginTexCoord();
        
    }
    
    public int[] assginColorArray(){

        for (int i=0; i<vert.length; i++){
            
            int j = i*4;
            
            colorArray[j]   = colorMap.get(i).r;
            colorArray[j+1] = colorMap.get(i).g;
            colorArray[j+2] = colorMap.get(i).b;
            colorArray[j+3] = colorMap.get(i).a;
            
        }
        
    
        return null;
    
    }    
    
    public void findNumberOfElementVert(){
        
        int totalNumber = 0;
         for (int i=0; i<elementMap.size();i++){
             totalNumber = totalNumber + elementMap.get(i).length;
         }       
        totalElementVert = totalNumber;
    }
    
    public void assginDrawIndex(){
    
        //ArrayList<Integer> di = new ArrayList<int> ();
        findNumberOfElementVert();
        
        drawIndex = new int[totalElementVert];
        
        int j = 0;
        System.out.print("elementMap.size()"+elementMap.size()+"\n");
        for (int i=0; i<elementMap.size();i++){
            
            int[] pts = elementMap.get(i);
            
            if (pts.length == 3){
                drawIndex[j++] = pts[0];
                System.out.print("pts[0]:"+pts[0]);
                drawIndex[j++] = pts[1];
                System.out.print("pts[1]:"+pts[1]);
                drawIndex[j++] = pts[2];
            } else {
                System.out.print("Error in Layout.java !\n");
            }
            
        }
        
    
    }
    public Vector2f[] assginTexCoord(){
        
        float max_x = 0;
        float min_x = 0;
        float max_y = 0;
        float min_y = 0;
        
        // find bound
        
        for (int i=0; i<vert.length; i++) {
            
            max_x = Math.max(max_x, vert[i].x);
            max_y = Math.max(max_y, vert[i].y);
            min_x = Math.min(min_x, vert[i].x);
            min_y = Math.min(min_y, vert[i].y);
            
        }
        
        //find latitude & departure
        
        float x_length = max_x - min_x;
        float y_length = max_y - min_y;
        
        
        for (int i=0; i<vert.length; i++) {
            
            texCoord[i].x = (vert[i].x - min_x)/x_length; 
            texCoord[i].y = (vert[i].y - min_y)/y_length; 
            
        }
        
        return texCoord;
    }   
     

}
