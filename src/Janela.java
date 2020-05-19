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
                      btnQuadrado           = new JButton ("Quadrado"),
                      btnRetangulo          = new JButton ("Retangulo"),
                      btnPoligono           = new JButton ("Poligono"),
                      btnFinalizaPoligono   = new JButton ("Finalizar Poligono"),
                      btnCor                = new JButton ("Cor"),
                      btnCorPreenchimento   = new JButton ("Cor Preenchimento"),
                      btnEscrever           = new JButton ("Escrever"),
                      btnAbrir              = new JButton ("Abrir"),
                      btnSalvar             = new JButton ("Salvar"),
                      btnApagar             = new JButton ("Apagar"),
                      btnSair               = new JButton ("Sair");

    protected MeuJPanel pnlDesenho = new MeuJPanel ();
    
    protected JLabel statusBar1 = new JLabel ("Dica: clique no botão do que deseja desenhar"),
                     statusBar2 = new JLabel ("Coordenada:");
    
    protected JLabel statusFinalizaPol = new JLabel ("Quando terminar, clique em "),
                     statusVertices = new JLabel ("  Número de Vértices: 0");
                     

    protected boolean esperaPonto, 
                      esperaInicioReta, esperaFimReta, 
                      esperaCentroCirculo, esperaRaioCirculo, 
                      esperaCantoInicalElipse, esperaCantoFinalElipse,
                      esperaInicioQuadrado, esperaFimQuadrado,
                      esperaInicioRetangulo, esperaFimRetangulo,
                      esperaInicioPoligono, esperaFimPoligono,// desenhaPoligono,
                      esperaInicioTexto;

    protected Color corAtual = Color.BLACK;
    protected Color corAtualPreenchimento;
    protected Ponto p1;
    
    protected int x[] = new int[10];
    protected int y[] = new int[10];
    
    protected int numVertices = 0;
    
    protected Figura temp = null; // figura temporária para os desenhos contínuos
    
    protected String stringTexto = null;
    protected Font fonteTexto = new Font("Arial", 0, 20);
    
    protected Vector<Figura> figuras = new Vector<Figura>();

    public Janela ()
    {
        super("Editor Gráfico");

        try
        {
            final Image btnPontoImg = ImageIO.read(getClass().getResource("resources/ponto.jpg"));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo ponto.jpg não foi encontrado",
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
                                           "Arquivo linha.jpg não foi encontrado",
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
                                           "Arquivo circulo.jpg não foi encontrado",
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
                                           "Arquivo elipse.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            final Image btnQuadradoImg = ImageIO.read(getClass().getResource("resources/quadrado.jpg"));
            btnQuadrado.setIcon(new ImageIcon(btnQuadradoImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo quadrado.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            final Image btnRetanguloImg = ImageIO.read(getClass().getResource("resources/retangulo.jpg"));
            btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo retangulo.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            final Image btnPoligonoImg = ImageIO.read(getClass().getResource("resources/poligono.jpg"));
            btnPoligono.setIcon(new ImageIcon(btnPoligonoImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo poligono.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            final Image btnCoresImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnCor.setIcon(new ImageIcon(btnCoresImg));
            
            final Image btnCorPreenchimentoImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnCorPreenchimento.setIcon(new ImageIcon(btnCorPreenchimentoImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            final Image btnEscreverImg = ImageIO.read(getClass().getResource("resources/texto.jpg"));
            btnEscrever.setIcon(new ImageIcon(btnEscreverImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo texto.jpg não foi encontrado",
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
                                           "Arquivo abrir.jpg não foi encontrado",
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
                                           "Arquivo salvar.jpg não foi encontrado",
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
                                           "Arquivo apagar.jpg não foi encontrado",
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
                                           "Arquivo sair.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        btnPonto.addActionListener (new DesenhoDePonto());
        btnLinha.addActionListener (new DesenhoDeReta ());
        btnCirculo.addActionListener (new DesenhoDeCirculo ());
        btnElipse.addActionListener (new DesenhoDeElipse ());
        btnQuadrado.addActionListener (new DesenhoDeQuadrado ());
        btnRetangulo.addActionListener (new DesenhoDeRetangulo ());
        btnPoligono.addActionListener (new DesenhoDePoligono());
        btnFinalizaPoligono.addActionListener (new FinalizaPoligono());
        
        btnEscrever.addActionListener (new EscreverTexto ());    
        
        btnCor.addActionListener (new EscolherCor ());
        btnCorPreenchimento.addActionListener (new EscolherCorPreenchimento ());
        
        btnSair.addActionListener (new SairSalvar());
        
        btnApagar.addActionListener (new ApagarUltimoDesenho());
        
        
        final JPanel pnlBotoesDesenho = new JPanel();
        final GridLayout grdBotoesDesenho = new GridLayout(11,1); 
        pnlBotoesDesenho.setLayout (grdBotoesDesenho);

        pnlBotoesDesenho.add (btnPonto);
        pnlBotoesDesenho.add (btnLinha);
        pnlBotoesDesenho.add (btnCirculo);
        pnlBotoesDesenho.add (btnElipse);
        pnlBotoesDesenho.add (btnQuadrado);
        pnlBotoesDesenho.add (btnRetangulo);
        pnlBotoesDesenho.add (btnPoligono);     
        
        pnlBotoesDesenho.add (btnEscrever);
        
        pnlBotoesDesenho.add (btnCor);
        pnlBotoesDesenho.add (btnCorPreenchimento);
        
        final JPanel pnlBotoesOutros = new JPanel();
        final GridLayout grdBotoesOutros = new GridLayout(5,1); 
        pnlBotoesOutros.setLayout (grdBotoesOutros);
        
        pnlBotoesOutros.add (btnAbrir);
        pnlBotoesOutros.add (btnSalvar);
        pnlBotoesOutros.add (btnApagar);
        pnlBotoesOutros.add (btnSair);        
       
        final JPanel     pnlStatus = new JPanel();
        final GridLayout grdStatus = new GridLayout(1,2);
        pnlStatus.setLayout(grdStatus);

        pnlStatus.add(statusBar1);
        pnlStatus.add(statusBar2);
        
        final JPanel pnlFinalizaPol = new JPanel();
        final FlowLayout flwFinalizaPol = new FlowLayout();
        pnlFinalizaPol.setLayout (flwFinalizaPol);
        
        pnlFinalizaPol.add (statusFinalizaPol);
        pnlFinalizaPol.add (btnFinalizaPoligono);
        pnlFinalizaPol.add (statusVertices);
        
        statusFinalizaPol.setVisible(false);
        btnFinalizaPoligono.setVisible(false);
        statusVertices.setVisible(false);
        
        final Container cntForm = this.getContentPane();
        cntForm.setLayout (new BorderLayout());
        
        cntForm.add (pnlFinalizaPol,  BorderLayout.NORTH);
        cntForm.add (pnlBotoesDesenho,  BorderLayout.WEST);
        cntForm.add (pnlBotoesOutros,  BorderLayout.EAST);
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
	    
        private static final long serialVersionUID = 1L;

        public MeuJPanel()
        {
            super();

            this.addMouseListener       (this);
            this.addMouseMotionListener (this);
        }

        public void paint (final Graphics g)
        {
            super.paint(g); 
                      
            Graphics2D g2d = (Graphics2D)g.create(); // cria um segundo graphics

            for (int i=0 ; i<figuras.size(); i++) 
                figuras.get(i).torneSeVisivel(g2d); 

            if (temp!=null) 
                temp.torneSeVisivel(g2d); 
            
            g2d.dispose(); // depois de desfaz dosegundo graphics
        }
        
        // próximos try/catch caso a tentem rodar Janela sem as classes das figuras
        
        public void mousePressed (final MouseEvent e)
        {           
            if (esperaPonto)
            {
                try
                {
                    figuras.add (new Ponto (e.getX(), e.getY(), corAtual));
                    figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                              
                    statusBar1.setText("Dica: clique no local do ponto que deseja"); 
                    
                    esperaPonto = true;
                }
                catch (Exception x)
                {
                    statusBar1.setText(x.getMessage());
                }
                
            }
            else
                if (esperaInicioReta)
                {
                    try
                    {
                        esperaInicioReta = false;

                        p1 = new Ponto (e.getX(), e.getY(), corAtual);

                        statusBar1.setText("Dica: clique no ponto final da reta");

                        esperaFimReta = true;
                    }
                    catch (Exception x)
                    {
                        statusBar1.setText(x.getMessage());
                    }
                 }
                 else
                    if (esperaCentroCirculo)
                    {                                    
                        try 
                        {
                            esperaCentroCirculo = false;

                            p1 = new Ponto (e.getX(), e.getY(), corAtual);

                            statusBar1.setText("Dica: clique em um ponto do circulo");

                            esperaRaioCirculo = true;
                        }
                        catch (Exception x)
                        {
                            statusBar1.setText(x.getMessage());
                        }
                    }			
                    else
		
                                    if(esperaCantoInicalElipse)
                                    {
                                        esperaCantoInicalElipse = false;
                                        
                                        p1 = new Ponto (e.getX(), e.getY(), corAtual);
                                                                                                                        
                                        statusBar1.setText("Dica: clique no outro canto da elipse");
                                        
                                        esperaCantoFinalElipse = true;                                        
                                        
                                    }
                                    else
                                    if(esperaCantoFinalElipse)
                                    {
                                        esperaCantoFinalElipse = false;
                                                                                
                                        final int largura = Math.abs ((int)(e.getX() - p1.getX())); // modulo
                                        final int altura = Math.abs ((int)(e.getY() - p1.getY()));  // modulo
                                        
                                                                             
                                        figuras.add (new Elipse(p1.getX(), p1.getY(), e.getX(), e.getY(), largura, altura, corAtual, corAtualPreenchimento));
                                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                                        
                                        statusBar1.setText("Dica: clique em um canto da elipse");
                                        
                                        esperaCantoInicalElipse = true;
                                    }
                                    else
                                        if(esperaInicioQuadrado)
                                        {                                               
                                            esperaInicioQuadrado = false;
                                            
                                            p1 = new Ponto (e.getX(), e.getY(), corAtual);
                                            
                                            statusBar1.setText("Dica: clique em outro ponto do quadrado");
                                            
                                            esperaFimQuadrado = true;                                                                                   
                                                                                                                              
                                        }
                                        else
                                            if(esperaFimQuadrado)
                                            {
                                               esperaFimQuadrado = false;
                                                
                                               figuras.add (new Quadrado(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual, corAtualPreenchimento));
                                               figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());

                                               statusBar1.setText("Dica: clique em ponto do quadrado");
                                            
                                               esperaInicioQuadrado = true; 
                                            }
                                            else
                                                if(esperaInicioRetangulo)
                                                {
                                                   esperaInicioRetangulo = false;
                                            
                                                   p1 = new Ponto (e.getX(), e.getY(), corAtual);

                                                   statusBar1.setText("Dica: clique em outro ponto do retangulo");

                                                   esperaFimRetangulo = true;
                                                }
                                                else
                                                    if(esperaFimRetangulo)
                                                    {
                                                        esperaFimRetangulo = false;
                                                
                                                        figuras.add (new Retangulo(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual, corAtualPreenchimento));
                                                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());

                                                        statusBar1.setText("Dica: clique em ponto do retangulo");

                                                        esperaInicioRetangulo = true;
                                                    }                                                                                       
                                    else
                                        if(esperaInicioTexto)
                                        {                                         
                                                p1 = new Ponto (e.getX(), e.getY(), corAtual);
                                                
                                                esperaInicioTexto = false;
                                                
                                                stringTexto = JOptionPane.showInputDialog(null, "Texto:", "Escrever", JOptionPane.PLAIN_MESSAGE);
                                                
                                                figuras.add (new Texto(p1.getX(), p1.getY(), stringTexto, corAtual, fonteTexto));
                                                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                                                
                                                esperaInicioTexto = true;                                                
                                                
                                        }
        
        }
                
        public void mouseReleased (final MouseEvent e)
        {
            if (esperaFimReta)
            {                        
                try
                {
                    esperaFimReta = false;

                    figuras.add (new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual));
                    figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());

                    statusBar1.setText("Dica: clique no ponto inicial da reta");
                    
                    temp = null;
                    esperaInicioReta = true;
                }
                catch (Exception x)
                {
                    statusBar1.setText(x.getMessage());
                }
            }
            else
                if (esperaRaioCirculo)
                {
                    try
                    {
                        esperaRaioCirculo = false;

                        final int raio = (int)(Math.round ( Math.sqrt (
                        Math.pow (e.getY() - p1.getY(),2) + Math.pow (e.getX() - p1.getX(),2))));

                        figuras.add (new Circulo(p1.getX(), p1.getY(), raio, corAtual, corAtualPreenchimento));
                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());

                        statusBar1.setText("Dica: clique no centro do circulo");

                        temp = null;
                        esperaCentroCirculo = true;
                    }
                    catch (Exception x)
                    {
                        statusBar1.setText(x.getMessage());
                    }
		}           
                    
        }
        
        public void mouseDragged(final MouseEvent e)
        {
            if (esperaFimReta)
            {              
		try
                {
                    temp = new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual); 

                    repaint (); 

                    statusBar1.setText("Dica: solte o mouse no ponto final da reta");
                }
                catch (Exception x)
                {
                    statusBar1.setText(x.getMessage());
                }
            }
            else
                if(esperaRaioCirculo)
                {
                    try
                    {                  
                        int raio = (int)(Math.round ( Math.sqrt (
                        Math.pow (e.getY() - p1.getY(),2) + Math.pow (e.getX() - p1.getX(),2))));

                        temp = new Circulo(p1.getX(), p1.getY(), raio, corAtual, corAtualPreenchimento);

                        repaint (); 

                        statusBar1.setText("Dica: solte o mouse no tamanho do raio desejado");
                    }
                    catch (Exception x)
                    {
                        statusBar1.setText(x.getMessage());
                    }
                }                
        }
        
        public void mouseClicked (final MouseEvent e)
        {
            if(esperaInicioPoligono)
            {
                try
                {
                    esperaInicioPoligono = false;

                    p1 = new Ponto (e.getX(), e.getY(), corAtual);
                                                                
                    numVertices = 1;
                    
                    statusVertices.setText("  Número de Vértices: " + numVertices);
                                                                
                    x[0] = p1.getX();
                    y[0] = p1.getY();

                    statusBar1.setText("Dica: clique nos outros vertices para formar o poligono");

                    esperaFimPoligono = true;
                    
                }
                catch (Exception x)
                {
                    statusBar1.setText(x.getMessage());
                }
            }
            else
                if(esperaFimPoligono)
                {
                    try
                    {   
                        p1 = new Ponto (e.getX(), e.getY(), corAtual);
                        
                        x[numVertices] = p1.getX();
                        y[numVertices] = p1.getY();
                        
                        numVertices++;
                        temp = new Poligono(x, y, numVertices, corAtual, corAtualPreenchimento);
                        
                        repaint ();
                        
                        statusVertices.setText("  Número de Vértices: " + numVertices);
                    }
                    catch (ArrayIndexOutOfBoundsException erro)
                    {
                        JOptionPane.showMessageDialog (null,
                                                           "Esse desenho terminou",
                                                           "Número de Vértices Excedido",
                                                           JOptionPane.WARNING_MESSAGE);
                        FinalizaPol();
                    }
                }              
        }
        
        public void mouseEntered (final MouseEvent e)
        {}

        public void mouseExited (final MouseEvent e)
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
              if(esperaFimPoligono)
                FinalizaPol();
              
              esperaPonto               = true;
              
              esperaInicioReta          = false;
              esperaFimReta             = false;
              
              esperaCentroCirculo       = false;
              esperaRaioCirculo         = false;
              
              esperaCantoInicalElipse   = false;
              esperaCantoFinalElipse    = false;
              
              esperaInicioQuadrado      = false;
              esperaFimQuadrado         = false;
            
              esperaInicioRetangulo     = false;
              esperaFimRetangulo        = false;
              
              esperaInicioPoligono      = false;
              esperaFimPoligono         = false;
              
              esperaInicioTexto         = false;

              statusBar1.setText("Dica: clique no local do ponto que deseja");
          }
    }

    protected class DesenhoDeReta implements ActionListener
    {
        public void actionPerformed (final ActionEvent e)    
        {
            if(esperaFimPoligono)
                FinalizaPol();
            
            esperaPonto               = false;
            
            esperaInicioReta          = true;
            esperaFimReta             = false;
            
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaCantoInicalElipse   = false;
            esperaCantoFinalElipse    = false;
            
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
            
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;
            
            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
            
            esperaInicioTexto         = false;

            statusBar1.setText("Dica: clique no ponto inicial da reta");
        }
    }

    protected class DesenhoDeCirculo implements ActionListener
    {
        public void actionPerformed (final ActionEvent e)    
        {
            if(esperaFimPoligono)
                FinalizaPol();
            
            esperaPonto               = false;
            
            esperaInicioReta          = false;
            esperaFimReta             = false;
            
            esperaCentroCirculo       = true;
            esperaRaioCirculo         = false;
              
            esperaCantoInicalElipse   = false;
            esperaCantoFinalElipse    = false;
            
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
            
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;
            
            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
            
            esperaInicioTexto         = false;

            statusBar1.setText("Dica: clique no centro do circulo");
        }
    }
        
    protected class DesenhoDeElipse implements ActionListener
    {
        public void actionPerformed (final ActionEvent e)    
        {
            if(esperaFimPoligono)
                FinalizaPol();
            
            esperaPonto               = false;
            
            esperaInicioReta          = false;
            esperaFimReta             = false;
            
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaCantoInicalElipse   = true;
            esperaCantoFinalElipse    = false;
            
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
            
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;
            
            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
            
            esperaInicioTexto         = false;
            
            statusBar1.setText("Dica: clique em um canto da elipse");
        }
    }
                
    protected class DesenhoDeQuadrado implements ActionListener
    {
        public void actionPerformed (final ActionEvent e)    
        {
            if(esperaFimPoligono)
                FinalizaPol();
            
            esperaPonto               = false;
            
            esperaInicioReta          = false;
            esperaFimReta             = false;
            
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaCantoInicalElipse   = false;
            esperaCantoFinalElipse    = false;
            
            esperaInicioQuadrado      = true;
            esperaFimQuadrado         = false;
            
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;
            
            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
            
            esperaInicioTexto         = false;
                        
            statusBar1.setText("Dica: clique em ponto do quadrado");
        }
    }
    
    protected class DesenhoDeRetangulo implements ActionListener
    {
        public void actionPerformed (final ActionEvent e)    
        {
            if(esperaFimPoligono)
                FinalizaPol();
            
            esperaPonto               = false;
            
            esperaInicioReta          = false;
            esperaFimReta             = false;
            
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaCantoInicalElipse   = false;
            esperaCantoFinalElipse    = false;
            
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
            
            esperaInicioRetangulo     = true;
            esperaFimRetangulo        = false;
            
            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
            
            esperaInicioTexto         = false;
                        
            statusBar1.setText("Dica: clique em ponto do retangulo");
        }
    }
    
    protected class DesenhoDePoligono implements ActionListener
    {
        public void actionPerformed (final ActionEvent e)    
        {            
            statusFinalizaPol.setVisible(true);
            btnFinalizaPoligono.setVisible(true);
            statusVertices.setVisible(true);
            
            if(esperaFimPoligono)
                FinalizaPol();
            
            esperaPonto               = false;

            esperaInicioReta          = false;
            esperaFimReta             = false;

            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;

            esperaCantoInicalElipse   = false;
            esperaCantoFinalElipse    = false;

            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;

            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;

            esperaInicioPoligono      = true;
            esperaFimPoligono         = false;            

            esperaInicioTexto         = false;

            statusBar1.setText("Dica: clique no vertice inical do poligono");            
            
        }
    }
    
    protected class FinalizaPoligono implements ActionListener // usando com clique
    {
        public void actionPerformed (final ActionEvent e)    
        {            
            if(esperaFimPoligono)
            {   
                FinalizaPol();
                
                esperaInicioPoligono = true;
                
                statusFinalizaPol.setVisible(true);
                btnFinalizaPoligono.setVisible(true);
                statusVertices.setVisible(true);
            }
            else
            {  
                JOptionPane.showMessageDialog (null,
                                               "Começe a desenhar um poligono antes de finalizar",
                                               "Nenhum poligono está sendo desenhado",
                                               JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    private void FinalizaPol () // para usar sem o clique
    {
        try
        {
            esperaPonto               = false;
            
            esperaInicioReta          = false;
            esperaFimReta             = false;
            
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaCantoInicalElipse   = false;
            esperaCantoFinalElipse    = false;
            
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
            
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;
            
            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;            
            
            esperaInicioTexto         = false;
            
            figuras.add (new Poligono(x, y, numVertices, corAtual, corAtualPreenchimento));
            figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());

            statusBar1.setText("Dica: clique no vertice inical do poligono");
            statusVertices.setText("  Número de Vértices: 0");

            temp = null;
            
            statusFinalizaPol.setVisible(false);
            btnFinalizaPoligono.setVisible(false);
            statusVertices.setVisible(false); 
        }
        catch (Exception x)
        {
            statusBar1.setText(x.getMessage());
        }   
    }
    
    private class EscolherCor implements ActionListener 
    {        
        public void actionPerformed (ActionEvent e) 
        {
            if(esperaFimPoligono)
                FinalizaPol();
            
            esperaPonto               = false;
              
            esperaInicioReta          = false;
            esperaFimReta             = false;
              
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaCantoInicalElipse   = false;
            esperaCantoFinalElipse    = false;
              
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
              
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;

            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
              
            esperaInicioTexto         = false;
            
            statusBar1.setText("Dica:");
            
            JColorChooser javacor = new JColorChooser();
            
            Color corContorno = javacor.showDialog(btnCor, "Selecione a Cor Desejada", Color.black);
            
            corAtual = corContorno;
            
            statusBar1.setText("Dica: clique no botão do que deseja desenhar");
            
        }
    }
    
    private class EscolherCorPreenchimento implements ActionListener 
    {        
        public void actionPerformed (ActionEvent e) 
        {
            if(esperaFimPoligono)
               FinalizaPol(); 
            
            esperaPonto               = false;
              
            esperaInicioReta          = false;
            esperaFimReta             = false;
              
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaCantoInicalElipse   = false;
            esperaCantoFinalElipse    = false;
              
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
              
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;
            
            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
              
            esperaInicioTexto         = false;
            
            
            statusBar1.setText("Dica:");
              
            JColorChooser javacor = new JColorChooser();
            
            Color corPreenchimento = javacor.showDialog(btnCorPreenchimento, "Selecione a Cor Desejada", Color.black);
            
            corAtualPreenchimento = corPreenchimento;
            
            statusBar1.setText("Dica: clique no botão do que deseja desenhar");
        }
    }
    
    private class EscreverTexto extends JFrame implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
            if(esperaFimPoligono)
                FinalizaPol();
            
            esperaPonto               = false;
              
            esperaInicioReta          = false;
            esperaFimReta             = false;
              
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
             
            esperaCantoInicalElipse   = false;
            esperaCantoFinalElipse    = false;
              
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
              
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;
            
            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
              
            esperaInicioTexto         = true;            
            
            statusBar1.setText("Dica: clique onde deseja escrever o texto");
        }
    }
    
    protected class ApagarUltimoDesenho implements ActionListener // apagando o ultimo desenho
    {
        public void actionPerformed (final ActionEvent e)    
        {
            try
            {
              if(esperaFimPoligono)
                FinalizaPol();
                
              esperaPonto               = false;
              
              esperaInicioReta          = false;
              esperaFimReta             = false;
              
              esperaCentroCirculo       = false;
              esperaRaioCirculo         = false;
              
              esperaCantoInicalElipse   = false;
              esperaCantoFinalElipse    = false;
              
              esperaInicioQuadrado      = false;
              esperaFimQuadrado         = false;
              
              esperaInicioRetangulo     = false;
              esperaFimRetangulo        = false;
            
              esperaInicioPoligono      = false;
              esperaFimPoligono         = false;
              
              esperaInicioTexto         = false;
            
           
              statusBar1.setText("Dica: clique no botão do que deseja desenhar");  
            
              figuras.remove(figuras.size()-1); // remove o ultimo salvo
           
              repaint(); // desenha de novo
            }
            catch (ArrayIndexOutOfBoundsException erro)
            {
                JOptionPane.showMessageDialog (null,
                                               "Não há nada para apagar!",
                                               "Tela Limpa",
                                               JOptionPane.WARNING_MESSAGE);
            }
                       
           
        }
    }
    
    protected class SairSalvar implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            if(esperaFimPoligono)
                FinalizaPol();
            
            //add salvar aqui
            
            System.exit(0);
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
