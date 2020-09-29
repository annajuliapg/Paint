import java.awt.*;
import javax.swing.JOptionPane;

public abstract class Figura
{
    // "cor" é a cor do contorno
    
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
    public void setCor (Color cor)
    {
       this.cor = cor;     
    }
    
    public void setPreenchimento (Color preenchimento)
    {
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
   
    @Override
    public abstract boolean equals(Object obj);
    
    @Override
    public abstract int hashCode();
    
    @Override
    public abstract String toString();
    
    public abstract void    torneSeVisivel (Graphics g);
}
