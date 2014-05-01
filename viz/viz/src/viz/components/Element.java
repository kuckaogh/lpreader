package viz.components;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

public class Element {
    
    //public ArrayList<Point> p;
    public Point[] p;
    public Vector3f[] vertices;
    public Vector2f[] texCoord;
    
    public Element(int vertNumber) {
        //p = new ArrayList<Point>();
        p = new Point[vertNumber];
        vertices = new Vector3f[vertNumber];
        texCoord = new Vector2f[vertNumber];
        
        for (int i = 0; i<vertNumber; i++){
            p[i] = new Point();
        }
        
        //set color coord // valid for 3 and 4 vertices
        for (int i = 0; i<vertNumber; i++){
            p[i].tx = i%2; p[i].ty = i/2;            
            //System.out.print(i+" "+p[i].tx+" "+p[i].ty+"\n");
        }
           
    }
    
    public Vector3f[] getVertices(){
               
        for (int i=0; i<p.length; i++) {
            vertices[i] = new Vector3f(p[i].x, p[i].y, 0);      
        }
        return vertices;
    }
    
     public Vector2f[] getTexCoord(){
               
        for (int i=0; i<p.length; i++) {
            texCoord[i] = new Vector2f(p[i].tx, p[i].ty);      
        }
        return texCoord;
    }   
}
