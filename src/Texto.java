import java.awt.*;
import java.util.*;
import javax.swing.JOptionPane;
 
public class Texto extends Figura 
{    
    protected Ponto posição;
    
    protected String texto;
    protected Font fonte;    
       
    public Texto (int x, int y, String texto, Font fonte) throws Exception 
    {        
        this (x, y, texto, Color.BLACK, fonte);
    }
       
    public Texto (int x, int y, String texto, Color cor, Font fonte) throws Exception
    {        
        super (cor);
        
        if("".equals(texto))
            throw new Exception ("Texto null");
        
        this.posição = new Ponto (x,y);
        
        this.texto   = texto;
        this.fonte   = fonte;
    }
 
    public Texto (String s) 
    {
        try
        {
            StringTokenizer quebrador = new StringTokenizer(s,":");

            quebrador.nextToken();

            int   x   = Math.abs(Integer.parseInt(quebrador.nextToken()));
            int   y   = Math.abs(Integer.parseInt(quebrador.nextToken()));

            String texto = quebrador.nextToken();

            Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                                   Integer.parseInt(quebrador.nextToken()),  // G
                                   Integer.parseInt(quebrador.nextToken())); // B


            Font fonte = new Font (quebrador.nextToken(),                     // nome da fonte
                                   Integer.parseInt(quebrador.nextToken()),   // estilo da fonte
                                   Integer.parseInt(quebrador.nextToken()));  // tamanho da fonte 

            this.posição = new Ponto (x,y);

            this.texto = texto;

            this.cor   = cor;

            this.fonte = fonte;
        }
        catch(NumberFormatException i)
        {
            JOptionPane.showMessageDialog (null,
                                               "Os valores no arquivo são inválidos",
                                               "Valores inválidos",
                                               JOptionPane.WARNING_MESSAGE);
        }catch (IllegalArgumentException e)
        {
           JOptionPane.showMessageDialog (null,
                                               "Pelo menos um dos valores das cores é inválido",
                                               "Valores inválidos",
                                               JOptionPane.WARNING_MESSAGE);
        }   
    }
 
    //setters
    public void setPosicao (int x, int y) 
    {
        this.posição = new Ponto (x,y,this.getCor());
    }
 
    public void setText (String texto) 
    {
        this.texto = texto;
    }
    
    //getters
    public Ponto getPosicao () 
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
               this.fonte.getFamily() +
               ":" +
               this.fonte.getStyle() +
               ":" +
               this.fonte.getSize();
    }    

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.posição);
        hash = 41 * hash + Objects.hashCode(this.texto);
        hash = 41 * hash + Objects.hashCode(this.fonte);
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
        
        final Texto other = (Texto) obj;
        
        if (!Objects.equals(this.texto, other.texto)) 
            return false;
        
        if (!Objects.equals(this.posição, other.posição)) 
            return false;
        
        if (!Objects.equals(this.fonte, other.fonte))
            return false;
        
        return true;
    }
}
