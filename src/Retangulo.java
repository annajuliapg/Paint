import java.awt.*;
import java.util.*;

public class Retangulo extends Figura 
{
    protected int x[] = new int[4];
    protected int y[] = new int[4];
	
    public Retangulo (int x1, int y1, int x2, int y2) 
    {
        this (x1, y1, x2, y2, Color.BLACK, Color.WHITE);
    }
	
    public Retangulo (int x1, int y1, int x2, int y2, Color cor) 
    {
        this (x1, y1, x2, y2, Color.BLACK, Color.WHITE);
    }
    
    public Retangulo (int x1, int y1, int x2, int y2, Color cor, Color preenchimento) 
    {
        super(cor, preenchimento);
        
        if(x2 < x1)
        {
            if(y2 < y1) // x e y menores
            {   
                // troca os dois
                
                x[0] = x1;
                y[0] = y2;

                x[1] = x2;
                y[1] = y2;

                x[2] = x2;
                y[2] = y1;

                x[3] = x1;
                y[3] = y1;
                
            }
            else // só o x menor
            {
                // troca o x                           
                                
                x[0] = x1;
                y[0] = y1;

                x[1] = x2;
                y[1] = y1; 

                x[2] = x2;
                y[2] = y2;

                x[3] = x1; 
                y[3] = y2; 
            }                
        }
        else
            if(y2 < y1) // só o y menor
            {
                // troca o y
                
                x[0] = x2;
                y[0] = y2;

                x[1] = x1;
                y[1] = y2;

                x[2] = x1;
                y[2] = y1;

                x[3] = x2;
                y[3] = y1;
            }
            else // nenhum menor
            {
                // mantém normal
                
                x[0] = x2;
                y[0] = y1;

                x[1] = x1;
                y[1] = y1;

                x[2] = x1;
                y[2] = y2;

                x[3] = x2;
                y[3] = y2;
            }
        
    }

    public Retangulo (String s) 
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();
        
        int x[] = new int[4];
        int y[] = new int[4];

        x[0]   = Integer.parseInt(quebrador.nextToken());
        y[0]   = Integer.parseInt(quebrador.nextToken());
        x[1]   = Integer.parseInt(quebrador.nextToken());
        y[1]   = Integer.parseInt(quebrador.nextToken());
        x[2]   = Integer.parseInt(quebrador.nextToken());
        y[2]   = Integer.parseInt(quebrador.nextToken());
        x[3]   = Integer.parseInt(quebrador.nextToken());
        y[3]   = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
        
        Color preenchimento = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),            // G
                               Integer.parseInt(quebrador.nextToken()));           // B
        
        this.x[0] = x[0]; this.x[1] = x[1]; this.x[2] = x[2]; this.x[3] = x[3];
        this.y[0] = y[0]; this.y[1] = y[1]; this.y[2] = y[2]; this.y[3] = y[3];
        
        this.cor            = cor;
        this.preenchimento  = preenchimento;
    }

    public void setP0 (int x, int y) 
    {
        this.x[0] = x;
        this.y[0] = y;
    }
    
    public int getXP0 ()
    {
        return this.x[0];
    }
    
    public int getYP0 ()
    {
        return this.y[0];
    }        

    public void torneSeVisivel (Graphics g) 
    {
        g.setColor (this.preenchimento);
        //preenchendo
        g.fillPolygon (x, y, 4);
        
        g.setColor (this.cor);
        
        g.drawPolygon (x, y, 4);
    }

    public String toString() {
        return "q:" +
               this.x[0] +
               ":" +
               this.y[0] +
               ":" +
               this.x[1] +
               ":" +
               this.y[1] +
               ":" +
               this.x[2] +
               ":" +
               this.y[2] +
               ":" +
               this.x[3] +
               ":" +
               this.y[3] +
               ":" +               
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