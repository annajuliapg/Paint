import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
 
public class Janela extends JFrame 
{
    protected static final long serialVersionUID = 1L;

    protected JButton btnPonto              = new JButton ("Ponto"),
                      btnLinha              = new JButton ("Linha"),
                      btnCirculo            = new JButton ("Circulo"),
                      btnElipse             = new JButton ("Elipse"),
                      btnCorContorno        = new JButton ("Cor Contorno"),
                      btnCorPreenchimento   = new JButton ("Cor Preenchimento"),
                      btnAbrir              = new JButton ("Abrir"),
                      btnSalvar             = new JButton ("Salvar"),
                      btnApagar             = new JButton ("Apagar"),
                      btnSair               = new JButton ("Sair");

    protected MeuJPanel pnlDesenho = new MeuJPanel ();
    
    protected JLabel statusBar1 = new JLabel ("Dica:"),
                     statusBar2 = new JLabel ("Coordenada:");

    protected boolean esperaPonto, esperaInicioReta, esperaFimReta, esperaCentroCirculo, esperaRaioCirculo, esperaCantoInicalElipse, esperaCantoFinalElipse;

    protected Color corAtualContorno = Color.BLACK;
    protected Color corAtualPreenchimento;
    protected Ponto p1;
    
    protected Vector<Figura> figuras = new Vector<Figura>();

    public Janela ()
    {
        super("Editor Gr�fico");

        try
        {
            final Image btnPontoImg = ImageIO.read(getClass().getResource("resources/ponto.jpg"));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo ponto.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            final Image btnLinhaImg = ImageIO.read(getClass().getResource("resources/linha.jpg"));
            btnLinha.setIcon(new ImageIcon(btnLinhaImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo linha.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            final Image btnCirculoImg = ImageIO.read(getClass().getResource("resources/circulo.jpg"));
            btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo circulo.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            final Image btnElipseImg = ImageIO.read(getClass().getResource("resources/elipse.jpg"));
            btnElipse.setIcon(new ImageIcon(btnElipseImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo elipse.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            final Image btnCoresImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnCorContorno.setIcon(new ImageIcon(btnCoresImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            final Image btnCoresImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnCorPreenchimento.setIcon(new ImageIcon(btnCoresImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            final Image btnAbrirImg = ImageIO.read(getClass().getResource("resources/abrir.jpg"));
            btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo abrir.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            final Image btnSalvarImg = ImageIO.read(getClass().getResource("resources/salvar.jpg"));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo salvar.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            final Image btnApagarImg = ImageIO.read(getClass().getResource("resources/apagar.jpg"));
            btnApagar.setIcon(new ImageIcon(btnApagarImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo apagar.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            final Image btnSairImg = ImageIO.read(getClass().getResource("resources/sair.jpg"));
            btnSair.setIcon(new ImageIcon(btnSairImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo sair.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        btnPonto.addActionListener (new DesenhoDePonto());
        btnLinha.addActionListener (new DesenhoDeReta ());
        btnCirculo.addActionListener (new DesenhoDeCirculo ());
        btnElipse.addActionListener (new DesenhoDeElipse ());
        btnCorContorno.addActionListener (new EscolherCorContorno ());
        btnCorPreenchimento.addActionListener (new EscolherCorPreenchimento ());
        

        final JPanel     pnlBotoes = new JPanel();
        final FlowLayout flwBotoes = new FlowLayout(); 
        pnlBotoes.setLayout (flwBotoes);

        pnlBotoes.add (btnAbrir);
        pnlBotoes.add (btnSalvar);
        pnlBotoes.add (btnPonto);
        pnlBotoes.add (btnLinha);
        pnlBotoes.add (btnCirculo);
        pnlBotoes.add (btnElipse);
        pnlBotoes.add (btnCorContorno);
        pnlBotoes.add (btnCorPreenchimento);
        
        pnlBotoes.add (btnApagar);
        pnlBotoes.add (btnSair);

        final JPanel     pnlStatus = new JPanel();
        final GridLayout grdStatus = new GridLayout(1,2);
        pnlStatus.setLayout(grdStatus);

        pnlStatus.add(statusBar1);
        pnlStatus.add(statusBar2);

        final Container cntForm = this.getContentPane();
        cntForm.setLayout (new BorderLayout());
        cntForm.add (pnlBotoes,  BorderLayout.NORTH);
        cntForm.add (pnlDesenho, BorderLayout.CENTER);
        cntForm.add (pnlStatus,  BorderLayout.SOUTH);
        
        this.addWindowListener (new FechamentoDeJanela());

        this.setSize (1000,500);
        this.setVisible (true);
    }

    protected class MeuJPanel extends    JPanel 
                              implements MouseListener,
                                         MouseMotionListener
    {
	    /**
        	 *
        	 */
        private static final long serialVersionUID = 1L;

        public MeuJPanel()
        {
            super();

            this.addMouseListener       (this);
            this.addMouseMotionListener (this);
        }

        public void paint (final Graphics g)
        {
            for (int i=0 ; i<figuras.size(); i++)
                figuras.get(i).torneSeVisivel(g);
        }
        
        public void mousePressed (final MouseEvent e)
        {
            if (esperaPonto)
            {
                figuras.add (new Ponto (e.getX(), e.getY(), corAtualContorno));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                
                figuras.add (new Ponto (e.getX() + 1, e.getY(), corAtualContorno));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                
                figuras.add (new Ponto (e.getX() - 1, e.getY(), corAtualContorno));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                
                figuras.add (new Ponto (e.getX(), e.getY() + 1, corAtualContorno));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                
                figuras.add (new Ponto (e.getX(), e.getY() - 1, corAtualContorno));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                
                esperaPonto = true;
                statusBar1.setText("Dica: clique no local do ponto que deseja");
                
            }
            else
                if (esperaInicioReta)
                {
                    p1 = new Ponto (e.getX(), e.getY(), corAtualContorno);
                    esperaInicioReta = false;
                    esperaFimReta = true;
                    statusBar1.setText("Dica: clique no ponto final da reta");    
                 }
                 else
                    if (esperaFimReta)
                    {
                        esperaFimReta = false;
                        figuras.add (new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtualContorno));
                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                        statusBar1.setText("Dica: clique no ponto inicial da reta");
                        esperaInicioReta = true;    
                    }
                    else
				if (esperaCentroCirculo)
				{
                                    p1 = new Ponto (e.getX(), e.getY(), corAtualContorno);
                                    esperaCentroCirculo = false;
                                    esperaRaioCirculo = true;
                                    statusBar1.setText("Dica: clique em um ponto do circulo");
				}
				else
				if (esperaRaioCirculo)
				{
                                    esperaRaioCirculo = false;
                                    
                                    final int raio = (int)(Math.round ( Math.sqrt (
                                    Math.pow (e.getY() - p1.getY(),2) + Math.pow (e.getX() - p1.getX(),2))));
                                    
                                    figuras.add (new Circulo(p1.getX(), p1.getY(), raio, corAtualContorno, corAtualPreenchimento));
                                    figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                                    
                                    statusBar1.setText("Dica: clique no centro do circulo");
                                    esperaCentroCirculo = true;
				}
                                else
                                    if(esperaCantoInicalElipse)
                                    {
                                        p1 = new Ponto (e.getX(), e.getY(), corAtualContorno);
                                        esperaCantoInicalElipse = false;
                                        esperaCantoFinalElipse = true;
                                        statusBar1.setText("Dica: clique no outro canto da elipse");
                                    }
                                    else
                                    if(esperaCantoFinalElipse)
                                    {
                                        esperaCantoFinalElipse = false;
                                                                                
                                            final int largura = Math.abs ((int)(e.getX() - p1.getX())); // modulo
                                            final int altura = Math.abs ((int)(e.getY() - p1.getY()));  // modulo
                                        
                                                                             
                                        figuras.add (new Elipse(p1.getX(), p1.getY(), e.getX(), e.getY(), largura, altura, corAtualContorno, corAtualPreenchimento));
                                        
                                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                                        
                                        statusBar1.setText("Dica: clique em um canto da elipse");
                                        esperaCantoInicalElipse = true;
                                    }
        }
        
        public void mouseReleased (final MouseEvent e)
        {}
        
        public void mouseClicked (final MouseEvent e)
        {}
        
        public void mouseEntered (final MouseEvent e)
        {}

        public void mouseExited (final MouseEvent e)
        {}
        
        public void mouseDragged(final MouseEvent e)
        {}

        public void mouseMoved(final MouseEvent e)
        {
            statusBar2.setText("Coordenada: "+e.getX()+","+e.getY());
        }
    }

    protected class DesenhoDePonto implements ActionListener
    {
          public void actionPerformed (final ActionEvent e)    
          {
              esperaPonto               = true;
              esperaInicioReta          = false;
              esperaFimReta             = false;
              
              esperaCentroCirculo       = false;
              esperaRaioCirculo         = false;
              
              esperaCantoInicalElipse   = false;
              esperaCantoFinalElipse    = false;

              statusBar1.setText("Dica: clique no local do ponto que deseja");
          }
    }

    protected class DesenhoDeReta implements ActionListener
    {
        public void actionPerformed (final ActionEvent e)    
        {
            esperaPonto               = false;
            esperaInicioReta          = true;
            esperaFimReta             = false;
            
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaCantoInicalElipse   = false;
            esperaCantoFinalElipse    = false;

            statusBar1.setText("Dica: clique no ponto inicial da reta");
        }
    }

	protected class DesenhoDeCirculo implements ActionListener
    {
        public void actionPerformed (final ActionEvent e)    
        {
            esperaPonto               = false;
            esperaInicioReta          = false;
            esperaFimReta             = false;
            
            esperaCentroCirculo       = true;
            esperaRaioCirculo         = false;
              
            esperaCantoInicalElipse   = false;
            esperaCantoFinalElipse    = false;

            statusBar1.setText("Dica: clique no centro do circulo");
        }
    }
        
    protected class DesenhoDeElipse implements ActionListener
    {
        public void actionPerformed (final ActionEvent e)    
        {
            esperaPonto               = false;
            esperaInicioReta          = false;
            esperaFimReta             = false;
            
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaCantoInicalElipse   = true;
            esperaCantoFinalElipse    = false;
            
            statusBar1.setText("Dica: clique em um canto da elipse");
        }
    }

    private class EscolherCorContorno implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            JColorChooser javacor = new JColorChooser();
            Color corContorno = javacor.showDialog(btnCorContorno, "Selecione a cor que deseja", Color.black);
            corAtualContorno = corContorno;
        }
    }
    
    private class EscolherCorPreenchimento implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            JColorChooser javacor = new JColorChooser();
            Color corPreenchimento = javacor.showDialog(btnCorPreenchimento, "Selecione a cor que deseja", Color.black);
            corAtualPreenchimento = corPreenchimento;
        }
    }
    
    
    protected class FechamentoDeJanela extends WindowAdapter
    {
        public void windowClosing (final WindowEvent e)
        {
            System.exit(0);
        }
    }
}
