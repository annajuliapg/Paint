import java.awt.*;
import java.util.*;
 
public class Poligono extends Figura
{
    protected int x[] = new int[10];
    protected int y[] = new int[10];
   
    protected int numVert;
       
    public Poligono (int x[], int y[], int n)
    {
        this (x, y, n, Color.BLACK, Color.BLACK);
    }
       
    public Poligono (int X[], int Y[], int n, Color cor, Color preenchimento)
    {
        super (cor, preenchimento);
       
        for(int i = 0; i < n; i++)
        {
            x[i] = X[i];
            y[i] = Y[i];
        }
       
        numVert = n; 
    }
 
    public Poligono (String s)
    {
        /*StringTokenizer quebrador = new StringTokenizer(s,":");
 
        quebrador.nextToken();
 
        int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());
 
        int   w   = Integer.parseInt(quebrador.nextToken());
        int   h   = Integer.parseInt(quebrador.nextToken());
       
        //auxiliares
 
        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
         
        Color cor2 = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                                                Integer.parseInt(quebrador.nextToken()),  // G
                                                Integer.parseInt(quebrador.nextToken())); // B
        this.cor      = cor;
        this.preenchimento     = cor2;*/
       
    }
 
    public void torneSeVisivel (Graphics g)
    {
            g.setColor(this.preenchimento);
            
            g.fillPolygon(x, y, numVert);
            
            g.setColor (this.cor);
            
            g.drawPolygon(x, y, numVert);
 
    }
 
    public String toString()
    {
        String vertices = "";
        
        for(int i = 0; i < 10; i++) // não pode ser i < número de vértices, tem que ser i < tamanho do vetor
        {
            vertices = vertices + x[i] + ":" + y[i] + ":";
        }
        
        return "pol:" +
               numVert +
               ":" +               
               vertices +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue() +
                ":" +
               this.getPreenchimento().getRed() +
                ":" +
               this.getPreenchimento().getGreen() +
                ":" +
               this.getPreenchimento().getBlue();
    }   
}
