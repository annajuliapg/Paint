import java.awt.*;

public abstract class Figura
{
    protected Color cor;
    protected Color preenchimento = new Color (0,0,0,0);
    
	  
    protected Figura ()
    {
        this (Color.BLACK);
    }
	  
    protected Figura (Color cor)
    {
       this (cor, Color.WHITE);
    }
    
    protected Figura (Color cor, Color preenchimento)
    {
        this.setCor (cor, preenchimento);
    }
    
    
    // setters
    public void setCor (Color cor, Color preenchimento)
    {
        this.cor = cor;
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
