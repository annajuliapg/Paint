import java.awt.*;
import java.util.*;
import javax.swing.JOptionPane;
 
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
        
        //para ter sempre valores positivos
        this.x = Math.abs (x);
        this.y = Math.abs (y);
    }

    public Ponto (String s)
    {
        try
        {
            StringTokenizer quebrador = new StringTokenizer(s,":");

            quebrador.nextToken();

            this.x = Math.abs(Integer.parseInt(quebrador.nextToken()));
            this.y = Math.abs(Integer.parseInt(quebrador.nextToken()));

            this.cor = new Color (Integer.parseInt(quebrador.nextToken()),   // R
                                  Integer.parseInt(quebrador.nextToken()),   // G
                                  Integer.parseInt(quebrador.nextToken()));  // B
        }
        catch(NumberFormatException i)
        {
            JOptionPane.showMessageDialog (null,
                                               "Os valores no arquivo são inválidos",
                                               "Valores inválidos",
                                               JOptionPane.WARNING_MESSAGE);
        }
        catch (IllegalArgumentException e)
        {
           JOptionPane.showMessageDialog (null,
                                               "Pelo menos um dos valores das cores é inválido",
                                               "Valores inválidos",
                                               JOptionPane.WARNING_MESSAGE);
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