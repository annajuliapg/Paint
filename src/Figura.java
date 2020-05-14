import java.awt.*;

public abstract class Figura
{
    protected Color contorno;
    protected Color preenchimento = new Color (0,0,0,0);
    
	  
    protected Figura ()
    {
        this (Color.BLACK);
    }
	  
    protected Figura (Color contorno)
    {
       this (contorno, Color.WHITE);
    }
    
    protected Figura (Color contorno, Color preenchimento)
    {
        this.setCor (contorno, preenchimento);
    }
    
    
    // setters
    public void setCor (Color contorno, Color preenchimento)
    {
        this.contorno = contorno;
        this.preenchimento = preenchimento;
        
    }
    
    // getters
    public Color getCor() // cor do contorno
    {
    	return this.contorno;        
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
