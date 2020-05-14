import java.awt.*;
import java.util.*;
 
public class Ponto extends Figura
{
    protected int x, y;
    protected Color cor = contorno; // para não usar "contorno" no ponto

    public Ponto (int x, int y)
    {
        this (x, y, Color.BLACK);
    }
	  
    public Ponto (int x, int y, Color cor)
    {
        super (cor);

        this.x = x;
        this.y = y;
    }

    public Ponto (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        this.x = Integer.parseInt(quebrador.nextToken());
        this.y = Integer.parseInt(quebrador.nextToken());

        this.cor = new Color (Integer.parseInt(quebrador.nextToken()),   // R
                              Integer.parseInt(quebrador.nextToken()),   // G
                              Integer.parseInt(quebrador.nextToken()));  // B
    }

    // setters
    public void setX (int x)
    {
        this.x = x;
    }
	  
    public void setY (int y)
    {
        this.y = y;
    }
	  
    // getters
    public int getX ()
    {
        return this.x;
    }
	  
    public int getY ()
    {
    	return this.y;
    }
	  
    public void torneSeVisivel (Graphics g)
    {
    	g.setColor (this.cor);
    	g.drawLine (this.x,this.y,this.x + 1,this.y);
        g.drawLine (this.x,this.y,this.x,this.y + 1);
        g.drawLine (this.x,this.y,this.x - 1,this.y);
        g.drawLine (this.x,this.y,this.x,this.y - 1);
    }

    public String toString()
    {
        return "p:" +
               this.x +
               ":" +
               this.y +
               ":" +
               this.getCor().getRed() + 
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue();
    }
}