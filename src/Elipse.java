import java.awt.*;
import java.util.*;
 
public class Elipse extends Figura
{
    protected Ponto p1, p2;
    protected int largura, altura;
    protected int aux;
    
    public Elipse (int x1, int y1, int x2, int y2, int largura, int altura)
    {
        this (x1, y1, x2, y2, largura, altura, Color.BLACK);
    }
	
    public Elipse (int x1, int y1, int x2, int y2, int largura, int altura, Color cor)
    {
        super(cor);        
        
        if(x2 < x1)
        {
            if(y2 < y1) // x e y menores
            {   
                // troca os dois
                this.p1 = new Ponto (x2,y2,cor); 
                this.p2 = new Ponto (x1,y1,cor);
                
            }
            else // só o x menor
            {
                // troca o x
                this.p1 = new Ponto (x2,y1,cor); 
                this.p2 = new Ponto (x1,y2,cor);
            }                
        }
        else
            if(y2 < y1) // só o y menor
            {
                // troca o y
                this.p1 = new Ponto (x1,y2,cor);
                this.p2 = new Ponto (x2,y1,cor);
            }
            else // nenhum menor
            {
                // mantém normal
                this.p1 = new Ponto (x1,y1,cor);
                this.p2 = new Ponto (x2,y2,cor);
            }
        
        this.largura = largura;
        
        this.altura = altura;
        
    }

    public Elipse (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x1  = Integer.parseInt(quebrador.nextToken());
        int   y1  = Integer.parseInt(quebrador.nextToken());
        int   x2  = Integer.parseInt(quebrador.nextToken());
        int   y2  = Integer.parseInt(quebrador.nextToken());

        int   largura  = Integer.parseInt(quebrador.nextToken());
        int   altura  = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

         
        
        this.p1 = new Ponto (x1,y1,cor);
        this.p2 = new Ponto (x2,y2,cor);
                    
        this.largura = largura;
        
        this.altura = altura;
    }

    //setters
    public void setP1 (int x, int y)
    {
        this.p1 = new Ponto (x,y,this.getCor());
    }

    public void setP2 (int x, int y)
    {
        this.p2 = new Ponto (x,y,this.getCor());
    }
    
    public void setLargura (int largura)
    {
        this.largura = largura;
    }
    
    public void setAltura (int altura)
    {
        this.altura = altura;
    }
        
    //getters
    public Ponto getP1 ()
    {
        return this.p1;
    }

    public Ponto getP2 ()
    {
        return this.p2;
    }
    
    public int getLargura ()
    {
        return this.largura;
    }
    
    public int getAltura ()
    {
        return this.altura;
    }

    public void torneSeVisivel (Graphics g)
    {
        g.setColor(this.cor);
        g.drawOval(this.p1.getX(), this.p1.getY(),   // ponto x1,y1
                   this.largura, 
                   this.altura);               // 
    }

    public String toString()
    {
        return "p1:" +
               this.p1.getX() +
               ":" +
               this.p1.getY() +
               ":" +
               this.p2.getX() +
               ":" +
               this.p2.getY() +
               ":" +
               this.largura +
               ":" +
               this.altura +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue();
    }
}
