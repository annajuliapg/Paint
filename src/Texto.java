import java.awt.*;
import java.util.*;
 
public class Texto extends Figura {
    
    protected Ponto posição;
    
    protected String texto;
    protected Font fonte;    
       
    public Texto (int x, int y, String texto, Font fonte) 
    {        
        this (x, y, texto, Color.BLACK, fonte);
    }
       
    public Texto (int x, int y, String texto, Color cor, Font fonte) 
    {
        
        super (cor);
 
        this.posição = new Ponto (x,y);
        
        this.texto   = texto;
        this.fonte   = fonte;
    }
 
    public Texto (String s) 
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");
 
        quebrador.nextToken();
 
        int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());
 
        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
 
        this.posição   = new Ponto (x,y);
        this.cor      = cor;
    
    }
 
    public void setInicio (int x, int y) 
    {
        this.posição = new Ponto (x,y,this.getCor());
    }
 
    public void setText (String texto) 
    {
        this.texto = texto;
    }
       
    public Ponto getInicio () 
    {
        return this.posição;
    }
 
    public String getText () 
    {
        return this.texto;
    }
 
    public void torneSeVisivel (Graphics g) 
    {                          
            g.setColor (this.cor);
            
            g.setFont(this.fonte);
            
            g.drawString(this.texto, this.posição.getX(), this.posição.getY());            
    }
 
    public String toString() 
    {        
        return "t:" +
               this.posição.getX() +
               ":" +
               this.posição.getY() +
               ":" +
               this.texto +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue() +
                ":" +
               this.fonte.getSize() +
               ":" +
               this.fonte.getFamily() +
               ":" +
               this.fonte.getStyle();
    }
    
}
