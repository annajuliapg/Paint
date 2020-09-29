import java.awt.*;
import java.util.*;
import javax.swing.JOptionPane;
 
public class Linha extends Figura
{
    protected Ponto p1, p2;
    
    public Linha (int x1, int y1, int x2, int y2)
    {
        this (x1, y1, x2, y2, Color.BLACK);
    }
	
    public Linha (int x1, int y1, int x2, int y2, Color cor)
    {
        super(cor);

        this.p1 = new Ponto (x1,y1,cor);
        this.p2 = new Ponto (x2,y2,cor);
    }

    public Linha (String s)
    {
        try
        {
            StringTokenizer quebrador = new StringTokenizer(s,":");

            quebrador.nextToken();

            int   x1  = Integer.parseInt(quebrador.nextToken());
            int   y1  = Integer.parseInt(quebrador.nextToken());

            int   x2  = Integer.parseInt(quebrador.nextToken());
            int   y2  = Integer.parseInt(quebrador.nextToken());

            Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                                   Integer.parseInt(quebrador.nextToken()),  // G
                                   Integer.parseInt(quebrador.nextToken())); // B

            this.p1  = new Ponto (x1,y1,cor);
            this.p2  = new Ponto (x2,y2,cor);

            this.cor = cor;
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
    public void setP1 (int x, int y)
    {
        this.p1 = new Ponto (x,y,this.getCor());
    }

    public void setP2 (int x, int y)
    {
        this.p2 = new Ponto (x,y,this.getCor());
    }

    // getters
    public Ponto getP1 ()
    {
        return this.p1;
    }

    public Ponto getP2 ()
    {
        return this.p2;
    }

    public void torneSeVisivel (Graphics g)
    {
        g.setColor(this.cor);
        g.drawLine(this.p1.getX(), this.p1.getY(),   // ponto inicial
                   this.p2.getX(), this.p2.getY());  // ponto final
    }

    public String toString()
    {
        return "l:" +
               this.p1.getX() +
               ":" +
               this.p1.getY() +
               ":" +
               this.p2.getX() +
               ":" +
               this.p2.getY() +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue();
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.p1);
        hash = 37 * hash + Objects.hashCode(this.p2);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
            return true;
        
        if (obj == null) 
            return false;
        
        if (getClass() != obj.getClass()) 
            return false;
        
        final Linha other = (Linha) obj;
        
        if (!Objects.equals(this.p1, other.p1)) 
            return false;
        
        if (!Objects.equals(this.p2, other.p2)) 
            return false;
        
        return true;
    }  
}