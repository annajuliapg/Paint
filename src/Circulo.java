import java.awt.*;
import java.util.*;
 
public class Circulo extends Figura
{
    protected Ponto centro;
    protected int raio;
    
    public Circulo (int x, int y, int raio)
    {
        this (x, y, raio, Color.BLACK, Color.WHITE);
    }
	
    public Circulo (int x, int y, int raio, Color contorno, Color preenchimento)
    {
        super(contorno, preenchimento);
        
        this.centro = new Ponto (x,y);
        this.raio = raio;
    }

    public Circulo (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x  = Integer.parseInt(quebrador.nextToken());
        int   y  = Integer.parseInt(quebrador.nextToken());

        int   raio  = Integer.parseInt(quebrador.nextToken());

        Color contorno = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),       // G
                               Integer.parseInt(quebrador.nextToken()));      // B
        
        Color preenchimento = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),            // G
                               Integer.parseInt(quebrador.nextToken()));           // B

        
        
        this.centro  = new Ponto (x,y);
        this.raio  = raio;
        
        this.contorno = contorno;
        this.preenchimento = preenchimento;
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
        g.setColor(this.contorno);
        
        g.drawOval(this.centro.getX() - this.raio, this.centro.getY()-this.raio,   // centro
                   2*this.raio, 2*this.raio);                                      // diametro
        
        g.setColor(this.preenchimento);
        
        g.fillOval (this.centro.getX() - this.raio, this.centro.getY()-this.raio,   //preenchendo o círculo
                   2*this.raio, 2*this.raio);

        
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
               this.getPreenchimento().getBlue();
    }
}
