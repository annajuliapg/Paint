import java.awt.*;
import java.util.*;
import javax.swing.JOptionPane;
 
public class Circulo extends Figura
{
    protected Ponto centro;
    protected int raio;
    
    // "cor" é a cor do contorno
    
    public Circulo (int x, int y, int raio)
    {
        this (x, y, raio, Color.BLACK, Color.BLACK);
    }
	
    public Circulo (int x, int y, int raio, Color cor, Color preenchimento)
    {
        super(cor, preenchimento);
        
        this.centro = new Ponto (x,y);
        this.raio = raio;
    }

    public Circulo (String s)
    {
        try
        {
            StringTokenizer quebrador = new StringTokenizer(s,":");

            quebrador.nextToken();

            int   x  = Integer.parseInt(quebrador.nextToken());
            int   y  = Integer.parseInt(quebrador.nextToken());

            int   raio  = Math.abs(Integer.parseInt(quebrador.nextToken()));

            Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                                   Integer.parseInt(quebrador.nextToken()),  // G
                                   Integer.parseInt(quebrador.nextToken())); // B

            Color preenchimento = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                                             Integer.parseInt(quebrador.nextToken()),  // G
                                             Integer.parseInt(quebrador.nextToken()),  // B
                                             Integer.parseInt(quebrador.nextToken())); // Alpha

            this.centro  = new Ponto (x,y);
            this.raio  = raio;

            this.cor = cor;
            this.preenchimento = preenchimento;
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
    public void setCentro (int x, int y)
    {
        this.centro = new Ponto (x,y);
    }

    public void setRaio (int raio)
    {
        this.raio = raio;
    }

    // getters
    public Ponto getCentro ()
    {
        return this.centro;
    }

    public int getRaio ()
    {
        return this.raio;
    }

    public void torneSeVisivel (Graphics g)
    {
        g.setColor(this.preenchimento);
        
        g.fillOval (this.centro.getX() - this.raio, this.centro.getY()-this.raio,   //preenchendo o círculo
                   2*this.raio, 2*this.raio);
        
        g.setColor(this.cor);
        
        g.drawOval(this.centro.getX() - this.raio, this.centro.getY()-this.raio,   // centro
                   2*this.raio, 2*this.raio);                                      // diametro  
    }

    public String toString()
    {
        return "c:" +
               this.centro.getX() +
               ":" +
               this.centro.getY() +
               ":" +
               this.raio +
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
               this.getPreenchimento().getBlue() +
               ":" +
               this.getPreenchimento().getAlpha();
    }
}
