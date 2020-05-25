import java.awt.*;
import javax.swing.JOptionPane;

public abstract class Figura
{
    protected Color cor;
    protected Color preenchimento;
    
	  
    protected Figura ()
    {
        this (Color.BLACK);
    }
	  
    protected Figura (Color cor)
    {        
        this (cor, Color.BLACK);  
    }
    
    protected Figura (Color cor, Color preenchimento)
    {
       try
       {
          this.setCor (cor);
          this.setPreenchimento(preenchimento);
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
    public void setCor (Color cor) throws IllegalArgumentException
    {
        if((cor.getRed() > 255)||(cor.getGreen() > 255)||(cor.getBlue() > 255))
           throw new IllegalArgumentException ("Valores de cor inválidos");
        
        if((cor.getRed() < 0)||(cor.getGreen() < 0)||(cor.getBlue() < 0))
           throw new IllegalArgumentException ("Valores de cor inválidos");
        
        this.cor = cor;     
    }
    
    public void setPreenchimento (Color preenchimento) throws IllegalArgumentException
    {
       if((preenchimento.getRed() > 255)||(preenchimento.getGreen() > 255)||(preenchimento.getBlue() > 255)||(preenchimento.getAlpha() > 255))
           throw new IllegalArgumentException ("Valores de cor inválidos");
       
       if((preenchimento.getRed() < 0)||(preenchimento.getGreen() < 0)||(preenchimento.getBlue() < 0)||(preenchimento.getAlpha() < 0))
           throw new IllegalArgumentException ("Valores de cor inválidos");
       
        this.preenchimento = preenchimento;
    }
    
    // getters
    public Color getCor() // cor do contorno
    {
    	return this.cor;        
    }
    
    public Color getPreenchimento()
    {
    	return this.preenchimento;        
    }
   
  //public abstract boolean equals         (Object obj);
  //public abstract int     hashCode       ();
  //public abstract Object  clone          ();
    public abstract String  toString       ();
    public abstract void    torneSeVisivel (Graphics g);
}
