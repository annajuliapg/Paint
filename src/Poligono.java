import java.awt.*;
import java.util.*;
 
public class Poligono extends Figura
{
    protected int x[] = new int[10];
    protected int y[] = new int[10];
   
    protected int vert;
       
    public Poligono (int x[], int y[], int n)
    {
        this (x, y, n, Color.BLACK, Color.white);
    }
       
    public Poligono (int X[], int Y[], int n, Color cor, Color preenchimento)
    {
        super (cor, preenchimento);
       
        for(int i = 0; i < n; i++)
        {
            x[i] = X[i];
            y[i] = Y[i];
        }
       
        vert = n; 
    }
 
    public Poligono (String p)
    {
        /*StringTokenizer quebrador = new StringTokenizer(p,":");
 
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
            
            g.fillPolygon(x, y, vert);
            
            g.setColor (this.cor);
            
            g.drawPolygon(x, y, vert);
 
    }
 
    public String toString()
    {
        String ponto = "";
        for(int i = 0; i < vert; i++)
        {
            ponto = ponto + x[i] + ":" + y[i] + ":";
        }
        
        return "g:" +
               vert +
               ":" +               
               ponto +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue() +
                ":" +
               this.getPreenchimento().getRed() + //daqui pra baixo pra salvar a cor do preenchimento
                ":" +
               this.getPreenchimento().getGreen() + //sujeito a erros lol
                ":" +
               this.getPreenchimento().getBlue();
    }   
}
