import java.awt.*;
import java.util.*;
import javax.swing.JOptionPane;
 
public class Poligono extends Figura
{
    protected int tamanhoVetor = 10;
    
    protected int x[] = new int[tamanhoVetor];
    protected int y[] = new int[tamanhoVetor];
   
    protected int numVert;
       
    public Poligono (int x[], int y[], int n)
    {
        this (x, y, n, Color.BLACK, Color.BLACK);
    }
       
    public Poligono (int X[], int Y[], int n, Color cor, Color preenchimento)
    {
        super (cor, preenchimento);
       
        for(int i = 0; i < n; i++)
        {
            x[i] = X[i];
            y[i] = Y[i];
        }
       
        numVert = n; 
    }
 
    public Poligono (String s)
    {
        try
        {
            StringTokenizer quebrador = new StringTokenizer(s,":");

            quebrador.nextToken();

            int   numVert = Math.abs(Integer.parseInt(quebrador.nextToken()));

            int x [] = new int [tamanhoVetor];
            int y [] = new int [tamanhoVetor];

            for(int i = 0; i < tamanhoVetor; i++) // não pode ser i < número de vértices, tem que ser i < tamanho do vetor
            {
                x[i] = Math.abs(Integer.parseInt(quebrador.nextToken()));
                y[i] = Math.abs(Integer.parseInt(quebrador.nextToken()));
            }

            Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                                   Integer.parseInt(quebrador.nextToken()),  // G
                                   Integer.parseInt(quebrador.nextToken())); // B

            Color preenchimento = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                                             Integer.parseInt(quebrador.nextToken()),  // G
                                             Integer.parseInt(quebrador.nextToken()),  // B
                                             Integer.parseInt(quebrador.nextToken())); // Alpha

            this.numVert = numVert;

            this.x = x;
            this.y = y;

            this.cor            = cor;
            this.preenchimento  = preenchimento;
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
    
    //setters
    public void setVetorX (int X[])
    {
       for(int i = 0; i < tamanhoVetor; i++)
        {
            this.x[i] = X[i];
        }      
    }
    
    public void setVetorY (int Y[])
    {
       for(int i = 0; i < tamanhoVetor; i++)
        {
            this.y[i] = Y[i];
        }      
    }
    
    public void setNumVert (int numVert)
    {
        this.numVert = numVert;
    }
    
    //getters
    public int[] getVetorX ()
    {        
        return this.x;
    }
    
    public int[] getVetorY ()
    {        
        return this.y;
    }
    
    public int getNumVert ()
    {
        return this.numVert;
    }
 
    public void torneSeVisivel (Graphics g)
    {
            g.setColor(this.preenchimento);
            
            g.fillPolygon(x, y, numVert); // preenchendo o poligono
            
            g.setColor (this.cor);
            
            g.drawPolygon(x, y, numVert);
 
    }
 
    public String toString() 
    {
        String vertices = "";
        
        for(int i = 0; i < tamanhoVetor; i++) // não pode ser i < número de vértices, tem que ser i < tamanho do vetor
        {
            vertices = vertices + x[i] + ":" + y[i] + ":";
        }
        
        //g: poliGono
        return "g:" +
               numVert +
               ":" +               
               vertices +
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

    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 53 * hash + this.tamanhoVetor;
        hash = 53 * hash + Arrays.hashCode(this.x);
        hash = 53 * hash + Arrays.hashCode(this.y);
        hash = 53 * hash + this.numVert;
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
        
        final Poligono other = (Poligono) obj;
        
        if (this.tamanhoVetor != other.tamanhoVetor) 
            return false;
        
        if (this.numVert != other.numVert) 
            return false;
        
        if (!Arrays.equals(this.x, other.x)) 
            return false;
        
        if (!Arrays.equals(this.y, other.y)) 
            return false;
        
        return true;
    }
}
