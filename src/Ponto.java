import java.awt.*;
import java.util.*;
 
public class Ponto extends Figura
{
    protected int x, y;

    public Ponto (int x, int y)
    {
        this (x, y, Color.BLACK);
    }
	  
    public Ponto (int x, int y, Color cor)
    {
        super (cor);

        this.x = Math.abs (x);
        this.y = Math.abs (y);
    }

    public Ponto (String s)
    {
        try
        {
            StringTokenizer quebrador = new StringTokenizer(s,":");

            quebrador.nextToken();

            this.x = Integer.parseInt(quebrador.nextToken());
            this.y = Integer.parseInt(quebrador.nextToken());

            this.cor = new Color (Integer.parseInt(quebrador.nextToken()),   // R
                                  Integer.parseInt(quebrador.nextToken()),   // G
                                  Integer.parseInt(quebrador.nextToken()));  // B
        }
        catch(Exception i)
        {
            System.out.println("Valores inválidos");
        }
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
    	g.drawLine (this.x,this.y,this.x,this.y);        
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