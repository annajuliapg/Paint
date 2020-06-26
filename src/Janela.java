import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import javax.swing.filechooser.*;
import say.swing.JFontChooser;

 
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
                      btnFonte              = new JButton ("Fonte Texto"),
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
                      esperaInicioElipse, esperaFimElipse,
                      esperaInicioQuadrado, esperaFimQuadrado,
                      esperaInicioRetangulo, esperaFimRetangulo,
                      esperaInicioPoligono, esperaFimPoligono,
                      esperaInicioTexto;

    protected Color corAtual = Color.BLACK;
    protected Color corAtualPreenchimento = Color.BLACK;
    protected Ponto p1;
    
    protected int tamanhoVetor = 10;
    
    protected int x[] = new int[tamanhoVetor];
    protected int y[] = new int[tamanhoVetor];
    
    protected int numVertices = 0;
    
    protected Figura temp = null; // figura temporária para os desenhos contínuos
    
    protected String stringTexto = null;
    protected Font fonteTexto = new Font ("Calibri", 0, 20);
    
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
            final Image btnFonteImg = ImageIO.read(getClass().getResource("resources/fonte.jpg"));
            btnFonte.setIcon(new ImageIcon(btnFonteImg));
        }
        catch (final IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo fonte.jpg não foi encontrado",
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
        btnFonte.addActionListener (new EscolherFonteTexto ());
        
        btnCor.addActionListener (new EscolherCor ());
        btnCorPreenchimento.addActionListener (new EscolherCorPreenchimento ());
        
        btnAbrir.addActionListener (new AbrirDesenho());
        
        btnSalvar.addActionListener (new SalvarDesenho());
        
        btnSair.addActionListener (new Sair());
        
        btnApagar.addActionListener (new ApagarUltimoDesenho());
        
        
        final JPanel pnlBotoesDesenho = new JPanel();
        final GridLayout grdBotoesDesenho = new GridLayout(12,1); 
        pnlBotoesDesenho.setLayout (grdBotoesDesenho);

        pnlBotoesDesenho.add (btnPonto);
        pnlBotoesDesenho.add (btnLinha);
        pnlBotoesDesenho.add (btnCirculo);
        pnlBotoesDesenho.add (btnElipse);
        pnlBotoesDesenho.add (btnQuadrado);
        pnlBotoesDesenho.add (btnRetangulo);
        pnlBotoesDesenho.add (btnPoligono);     
        
        pnlBotoesDesenho.add (btnEscrever);
        pnlBotoesDesenho.add (btnFonte);
        
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
            
            g2d.dispose(); // depois de desfaz do segundo graphics
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
                    System.out.println(x.getMessage());
                }
                
            }
            else
                if (esperaInicioReta)
                {
                    try
                    {
                        esperaInicioReta = false;

                        p1 = new Ponto (e.getX(), e.getY(), corAtual);

                        esperaFimReta = true;
                    }
                    catch (Exception x)
                    {
                        System.out.println(x.getMessage());
                    }
                 }
                 else
                    if (esperaCentroCirculo)
                    {                                    
                        try 
                        {
                            esperaCentroCirculo = false;

                            p1 = new Ponto (e.getX(), e.getY(), corAtual);

                            esperaRaioCirculo = true;
                        }
                        catch (Exception x)
                        {
                            System.out.println(x.getMessage());
                        }
                    }			
                    else
                        if(esperaInicioElipse)
                        {
                            try
                            {
                                esperaInicioElipse = false;

                                p1 = new Ponto (e.getX(), e.getY(), corAtual);

                                esperaFimElipse = true;
                            }
                            catch (Exception x)
                            {
                                System.out.println(x.getMessage());
                            }
                                        
                        }
                        else
                            if(esperaInicioQuadrado)
                            {                                               
                                try
                                {
                                    esperaInicioQuadrado = false;

                                    p1 = new Ponto (e.getX(), e.getY(), corAtual);

                                    esperaFimQuadrado = true;
                                }
                                catch (Exception x)
                                {
                                    System.out.println(x.getMessage());
                                }
                                                                                                                              
                            }
                            else
                                if(esperaInicioRetangulo)
                                {
                                    try
                                    {
                                        esperaInicioRetangulo = false;

                                        p1 = new Ponto (e.getX(), e.getY(), corAtual);

                                        esperaFimRetangulo = true;
                                    }
                                    catch (Exception x)
                                    {
                                        System.out.println(x.getMessage());
                                    }
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

                    statusBar1.setText("Dica: clique no ponto inicial da reta e arraste o mouse");
                    
                    temp = null;
                    esperaInicioReta = true;
                }
                catch (Exception x)
                {
                    System.out.println(x.getMessage());
                }
            }
            else
                if (esperaRaioCirculo)
                {
                    try
                    {
                        esperaRaioCirculo = false;

                        //math.abs traz o módulo
                        final int raio = (int) Math.abs (Math.round (Math.sqrt(Math.pow 
                                         (e.getY() - p1.getY(),2) + Math.pow (e.getX() - p1.getX(),2))));

                        figuras.add (new Circulo(p1.getX(), p1.getY(), raio, corAtual, corAtualPreenchimento));
                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());

                        statusBar1.setText("Dica: clique no centro do circulo e arraste o mouse");

                        temp = null;
                        esperaCentroCirculo = true;
                    }
                    catch (Exception x)
                    {
                        System.out.println(x.getMessage());
                    }
		}
                else
                    if(esperaFimElipse)
                    {
                        try
                        {
                            esperaFimElipse = false;

                            //math.abs traz o módulo
                            final int largura = Math.abs ((int)(e.getX() - p1.getX()));
                            final int altura = Math.abs ((int)(e.getY() - p1.getY()));

                            figuras.add (new Elipse(p1.getX(), p1.getY(), e.getX(), e.getY(), largura, altura, corAtual, corAtualPreenchimento));
                            figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());

                            statusBar1.setText("Dica: clique em um canto da elipse e arraste o mouse");

                            temp = null;
                            esperaInicioElipse = true;
                        }
                        catch (Exception x)
                        {
                            System.out.println(x.getMessage());
                        }
                    }
                    else
                        if(esperaFimQuadrado)
                        {
                            try
                            {
                                esperaFimQuadrado = false;

                                figuras.add (new Quadrado(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual, corAtualPreenchimento));
                                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());

                                statusBar1.setText("Dica: clique em um ponto do quadrado e arraste o mouse");

                                temp = null;
                                esperaInicioQuadrado = true;
                            }
                            catch (Exception x)
                            {
                                System.out.println(x.getMessage());
                            }
                        }
                        else
                            if(esperaFimRetangulo)
                            {
                                try
                                {
                                    esperaFimRetangulo = false;

                                    figuras.add (new Retangulo(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual, corAtualPreenchimento));
                                    figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());

                                    statusBar1.setText("Dica: clique em um ponto do retangulo e arraste o mouse");

                                    temp = null;
                                    esperaInicioRetangulo = true;
                                }
                                catch (Exception x)
                                {
                                    System.out.println(x.getMessage());
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
                    System.out.println(x.getMessage());
                }
            }
            else
                if(esperaRaioCirculo)
                {
                    try
                    {                  
                        //math.abs traz o módulo
                        int raio = (int) Math.abs (Math.round (Math.sqrt(Math.pow 
                                   (e.getY() - p1.getY(),2) + Math.pow (e.getX() - p1.getX(),2))));

                        temp = new Circulo(p1.getX(), p1.getY(), raio, corAtual, corAtualPreenchimento);

                        repaint (); 

                        statusBar1.setText("Dica: solte o mouse no tamanho do raio desejado");
                    }
                    catch (Exception x)
                    {
                        System.out.println(x.getMessage());
                    }
                }
                else
                    if(esperaFimElipse)
                    {
                        try
                        {
                            //math.abs traz o módulo
                            int largura = Math.abs ((int)(e.getX() - p1.getX()));
                            int altura = Math.abs ((int)(e.getY() - p1.getY()));

                            temp = new Elipse(p1.getX(), p1.getY(), e.getX(), e.getY(), largura, altura, corAtual, corAtualPreenchimento);

                            repaint (); 

                            statusBar1.setText("Dica: solte o mouse no canto final da elipse");
                        }
                        catch (Exception x)
                        {
                            System.out.println(x.getMessage());
                        }
                    }
                    else
                        if(esperaFimQuadrado)
                        {
                            try
                            {
                               temp = new Quadrado(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual, corAtualPreenchimento);

                               repaint (); 

                               statusBar1.setText("Dica: solte o mouse no canto final do quadrado");
                            }
                            catch (Exception x)
                            {
                                System.out.println(x.getMessage());
                            }
                        }
                        else
                            if(esperaFimRetangulo)
                            {
                                try
                                {
                                    temp = new Retangulo(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual, corAtualPreenchimento);

                                    repaint (); 

                                    statusBar1.setText("Dica: solte o mouse no canto final do retangulo");
                                }
                                catch (Exception x)
                                {
                                    System.out.println(x.getMessage());
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
                    System.out.println(x.getMessage());
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
                        esperaInicioPoligono = true;
                
                        statusFinalizaPol.setVisible(true);
                        btnFinalizaPoligono.setVisible(true);
                        statusVertices.setVisible(true);                        
                    }
                    catch (Exception x)
                    {
                        System.out.println(x.getMessage());
                    }
                }
                else
                    if(esperaInicioTexto)
                    {                                         
                        try
                        {
                            p1 = new Ponto (e.getX(), e.getY(), corAtual);

                            esperaInicioTexto = false;

                            stringTexto = JOptionPane.showInputDialog(null, "Texto:", "Escrever", JOptionPane.PLAIN_MESSAGE);

                            if((stringTexto != null) && (!stringTexto.isEmpty()))
                            {
                              figuras.add (new Texto(p1.getX(), p1.getY(), stringTexto, corAtual, fonteTexto));
                              figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());  
                            }

                            esperaInicioTexto = true;
                        }
                        catch (Exception x)
                        {
                            System.out.println(x.getMessage());
                        }
                    }
        }
        
        public void mouseEntered (final MouseEvent e)
        {}

        public void mouseExited (final MouseEvent e)
        {}
        
        public void mouseMoved(final MouseEvent e)
        {         
            statusBar2.setText("Coordenada X: "+ e.getX() + " Coordenada Y: " + e.getY());
        }
    }
    
    protected class DesenhoDePonto implements ActionListener
    {
          public void actionPerformed (final ActionEvent e)    
          {
              if(esperaFimPoligono)
               FinalizaPol();
              else
              if(esperaInicioPoligono)
              {
                statusFinalizaPol.setVisible(false);
                btnFinalizaPoligono.setVisible(false);
                statusVertices.setVisible(false);
              }
                 
              
              esperaPonto               = true;
              
              esperaInicioReta          = false;
              esperaFimReta             = false;
              
              esperaCentroCirculo       = false;
              esperaRaioCirculo         = false;
              
              esperaInicioElipse        = false;
              esperaFimElipse           = false;
              
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
              else
              if(esperaInicioPoligono)
              {
                statusFinalizaPol.setVisible(false);
                btnFinalizaPoligono.setVisible(false);
                statusVertices.setVisible(false);
              } 
            
            esperaPonto               = false;
            
            esperaInicioReta          = true;
            esperaFimReta             = false;
            
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaInicioElipse        = false;
            esperaFimElipse           = false;
            
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
            
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;
            
            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
            
            esperaInicioTexto         = false;

            statusBar1.setText("Dica: clique no ponto inicial da reta e arraste o mouse");
        }
    }

    protected class DesenhoDeCirculo implements ActionListener
    {
        public void actionPerformed (final ActionEvent e)    
        {
            if(esperaFimPoligono)
               FinalizaPol();
              else
              if(esperaInicioPoligono)
              {
                statusFinalizaPol.setVisible(false);
                btnFinalizaPoligono.setVisible(false);
                statusVertices.setVisible(false);
              }
            
            esperaPonto               = false;
            
            esperaInicioReta          = false;
            esperaFimReta             = false;
            
            esperaCentroCirculo       = true;
            esperaRaioCirculo         = false;
              
            esperaInicioElipse        = false;
            esperaFimElipse           = false;
            
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
            
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;
            
            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
            
            esperaInicioTexto         = false;

            statusBar1.setText("Dica: clique no centro do circulo e arraste o mouse");
        }
    }
        
    protected class DesenhoDeElipse implements ActionListener
    {
        public void actionPerformed (final ActionEvent e)    
        {
            if(esperaFimPoligono)
               FinalizaPol();
              else
              if(esperaInicioPoligono)
              {
                statusFinalizaPol.setVisible(false);
                btnFinalizaPoligono.setVisible(false);
                statusVertices.setVisible(false);
              }
            
            esperaPonto               = false;
            
            esperaInicioReta          = false;
            esperaFimReta             = false;
            
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaInicioElipse        = true;
            esperaFimElipse           = false;
            
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
            
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;
            
            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
            
            esperaInicioTexto         = false;
            
            statusBar1.setText("Dica: clique em um canto da elipse e arraste o mouse");
        }
    }
                
    protected class DesenhoDeQuadrado implements ActionListener
    {
        public void actionPerformed (final ActionEvent e)    
        {
            if(esperaFimPoligono)
               FinalizaPol();
              else
              if(esperaInicioPoligono)
              {
                statusFinalizaPol.setVisible(false);
                btnFinalizaPoligono.setVisible(false);
                statusVertices.setVisible(false);
              }
            
            esperaPonto               = false;
            
            esperaInicioReta          = false;
            esperaFimReta             = false;
            
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaInicioElipse        = false;
            esperaFimElipse           = false;
            
            esperaInicioQuadrado      = true;
            esperaFimQuadrado         = false;
            
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;
            
            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
            
            esperaInicioTexto         = false;
                        
            statusBar1.setText("Dica: clique em um ponto do quadrado e arraste o mouse");
        }
    }
    
    protected class DesenhoDeRetangulo implements ActionListener
    {
        public void actionPerformed (final ActionEvent e)    
        {
            if((esperaFimPoligono)||(esperaInicioPoligono))
               FinalizaPol(); 
            
            esperaPonto               = false;
            
            esperaInicioReta          = false;
            esperaFimReta             = false;
            
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaInicioElipse        = false;
            esperaFimElipse           = false;
            
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
            
            esperaInicioRetangulo     = true;
            esperaFimRetangulo        = false;
            
            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
            
            esperaInicioTexto         = false;
                        
            statusBar1.setText("Dica: clique em um ponto do retangulo e arraste o mouse");
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

            esperaInicioElipse        = false;
            esperaFimElipse           = false;

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
    
    protected class FinalizaPoligono implements ActionListener // usando com clique, somente clicando no botão
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
                if(esperaInicioPoligono)
                {
                    
                    FinalizaPol();
                
                    esperaInicioPoligono = true;

                    statusFinalizaPol.setVisible(true);
                    btnFinalizaPoligono.setVisible(true);
                    statusVertices.setVisible(true);
                    
                    JOptionPane.showMessageDialog (null,
                                                   "Começe a desenhar um poligono antes de finalizar",
                                                   "Nenhum poligono está sendo desenhado",
                                                   JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
    
    private void FinalizaPol () // para usar sem o clique, pode chamar em qq outro metodo
    {
        try
        {
            esperaPonto               = false;

            esperaInicioReta          = false;
            esperaFimReta             = false;

            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;

            esperaInicioElipse        = false;
            esperaFimElipse           = false;

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
            System.out.println(x.getMessage());
        }
        
    }
    
    private class EscreverTexto implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
            if(esperaFimPoligono)
               FinalizaPol();
              else
              if(esperaInicioPoligono)
              {
                statusFinalizaPol.setVisible(false);
                btnFinalizaPoligono.setVisible(false);
                statusVertices.setVisible(false);
              }
            
            esperaPonto               = false;
              
            esperaInicioReta          = false;
            esperaFimReta             = false;
              
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
             
            esperaInicioElipse        = false;
            esperaFimElipse           = false;
              
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
            
    private class EscolherFonteTexto implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
            if(esperaFimPoligono)
               FinalizaPol();
              else
              if(esperaInicioPoligono)
              {
                statusFinalizaPol.setVisible(false);
                btnFinalizaPoligono.setVisible(false);
                statusVertices.setVisible(false);
              }
            
            esperaPonto               = false;
              
            esperaInicioReta          = false;
            esperaFimReta             = false;
              
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
             
            esperaInicioElipse        = false;
            esperaFimElipse           = false;
              
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
              
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;
            
            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
              
            esperaInicioTexto         = false;
            
            JFontChooser fontChooser = new JFontChooser();
                        
            int result = fontChooser.showDialog(Janela.this);
                        
            if (result == JFontChooser.OK_OPTION)
                fonteTexto = fontChooser.getSelectedFont();
            
            statusBar1.setText("Dica: clique no botão do que deseja desenhar");          
        }
    }
    
    private class EscolherCor implements ActionListener 
    {        
        public void actionPerformed (ActionEvent e) 
        {
            if(esperaFimPoligono)
               FinalizaPol();
              else
              if(esperaInicioPoligono)
              {
                statusFinalizaPol.setVisible(false);
                btnFinalizaPoligono.setVisible(false);
                statusVertices.setVisible(false);
              }
            
            esperaPonto               = false;
              
            esperaInicioReta          = false;
            esperaFimReta             = false;
              
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaInicioElipse        = false;
            esperaFimElipse           = false;
              
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
              
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;

            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
              
            esperaInicioTexto         = false;
            
            statusBar1.setText("Dica:");
            
            JColorChooser javacor = new JColorChooser();
            
            Color cor = javacor.showDialog(btnCor, "Selecione a Cor Desejada", Color.black);
            
            corAtual = cor;
            
            statusBar1.setText("Dica: clique no botão do que deseja desenhar");            
        }
    }
    
    private class EscolherCorPreenchimento implements ActionListener 
    {        
        public void actionPerformed (ActionEvent e) 
        {
            if(esperaFimPoligono)
               FinalizaPol();
              else
              if(esperaInicioPoligono)
              {
                statusFinalizaPol.setVisible(false);
                btnFinalizaPoligono.setVisible(false);
                statusVertices.setVisible(false);
              }
            
            esperaPonto               = false;
              
            esperaInicioReta          = false;
            esperaFimReta             = false;
              
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaInicioElipse        = false;
            esperaFimElipse           = false;
              
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

    protected class ApagarUltimoDesenho implements ActionListener // apagando o ultimo desenho
    {
        public void actionPerformed (final ActionEvent e)    
        {
            try
            {
              if(esperaFimPoligono)
               FinalizaPol();
              else
                if(esperaInicioPoligono)
                {
                    statusFinalizaPol.setVisible(false);
                    btnFinalizaPoligono.setVisible(false);
                    statusVertices.setVisible(false);
                }
              
              esperaPonto               = false;
              
              esperaInicioReta          = false;
              esperaFimReta             = false;
              
              esperaCentroCirculo       = false;
              esperaRaioCirculo         = false;
              
              esperaInicioElipse        = false;
              esperaFimElipse           = false;
              
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
                figuras.clear();
                
                JOptionPane.showMessageDialog (null,
                                               "Não há nada para apagar!",
                                               "Tela Limpa",
                                               JOptionPane.WARNING_MESSAGE);
                
                
            }
        }
    }
    
    protected class SalvarDesenho implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            if(esperaFimPoligono)
               FinalizaPol();
              else
              if(esperaInicioPoligono)
              {
                statusFinalizaPol.setVisible(false);
                btnFinalizaPoligono.setVisible(false);
                statusVertices.setVisible(false);
              }
            
            esperaPonto               = false;
              
            esperaInicioReta          = false;
            esperaFimReta             = false;
              
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaInicioElipse        = false;
            esperaFimElipse           = false;
              
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
              
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;

            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
              
            esperaInicioTexto         = false;
            
            try
            {
                Salvar();
            }
            catch (ArrayIndexOutOfBoundsException erro)
            {
                JOptionPane.showMessageDialog (null,
                                               "Não há nada para salvar!",
                                               "Tela Limpa",
                                               JOptionPane.WARNING_MESSAGE);
            }
            
            statusBar1.setText("Dica: clique no botão do que deseja desenhar");         
        }
    }
    
    protected class AbrirDesenho implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            if(esperaFimPoligono)
               FinalizaPol();
              else
              if(esperaInicioPoligono)
              {
                statusFinalizaPol.setVisible(false);
                btnFinalizaPoligono.setVisible(false);
                statusVertices.setVisible(false);
              }
            
            esperaPonto               = false;
              
            esperaInicioReta          = false;
            esperaFimReta             = false;
              
            esperaCentroCirculo       = false;
            esperaRaioCirculo         = false;
              
            esperaInicioElipse        = false;
            esperaFimElipse           = false;
              
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
              
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;

            esperaInicioPoligono      = false;
            esperaFimPoligono         = false;
              
            esperaInicioTexto         = false;
            
            Abrir();
        }
    }
    
    public void Salvar() throws ArrayIndexOutOfBoundsException
    {
       if(figuras.isEmpty()) throw new ArrayIndexOutOfBoundsException ("Sem desenho na tela");  
        
       JFileChooser chooser = new JFileChooser();
    
       FileNameExtensionFilter filter = new FileNameExtensionFilter("Paint Files", "paint", ".paint");
    
       chooser.setFileFilter(filter);
               
       int returnVal = chooser.showSaveDialog(Janela.this);

       if(returnVal == JFileChooser.APPROVE_OPTION) 
       {           
           try
           {
            String arquivo = chooser.getSelectedFile().getPath();
            
            String nomeArquivo = chooser.getSelectedFile().getName();
            
            if(!arquivo.endsWith(".paint"))
                arquivo+=".paint";
            
            File teste = new File (arquivo);
            
            int resposta = 0; //sim = 0 não = 1
                        
            if(teste.exists())
            {
                resposta = JOptionPane.showConfirmDialog(null, "Deseja substituí-lo?", "Esse nome de arquivo já existe", JOptionPane.YES_NO_OPTION);
            }            
            
            if (resposta == 0) // caso sim o arquivo é salvo
            {
                PrintWriter arq = new PrintWriter (new FileWriter (arquivo));
                
                for (Figura f : this.figuras)
                     arq.println (f);

                arq.close();
                
                JOptionPane.showMessageDialog (null,
                                               "Desenho salvo como: " + nomeArquivo + ".paint",
                                               "Salvo",
                                               JOptionPane.INFORMATION_MESSAGE);
                
            }
            else
            {
                JOptionPane.showMessageDialog (null,
                                               "Desenho não salvo, tente novamente com outro nome",
                                               "Atenção",
                                               JOptionPane.WARNING_MESSAGE);
            }
            
           }
           catch (Exception e)
           {
                e.printStackTrace();
           }
        }
    }
    
    public void Abrir()
    {
       JFileChooser chooser = new JFileChooser();
    
       FileNameExtensionFilter filter = new FileNameExtensionFilter("Paint Files", "paint", ".paint");
    
       chooser.setFileFilter(filter);
    
       int returnVal = chooser.showOpenDialog(Janela.this);
       
       if(returnVal == JFileChooser.APPROVE_OPTION) 
       {
          try
          {
            String arquivo = chooser.getSelectedFile().getPath();
            
            BufferedReader arq = new BufferedReader (new FileReader (arquivo));
            
            this.figuras.clear();
            
            repaint();
            
                while (arq.ready())
                {
                    String linhaTexto = arq.readLine();

                    switch (linhaTexto.charAt(0))
                    {
                        case 'p': 
                        this.figuras.add (new Ponto (linhaTexto));
                        break;

                        case 'l': 
                        this.figuras.add (new Linha (linhaTexto));
                        break;
                        
                        case 'c':
                        this.figuras.add(new Circulo (linhaTexto));
                        break;
                        
                        case 'e':
                        this.figuras.add(new Elipse (linhaTexto));
                        break;
                        
                        case 'q':
                        this.figuras.add(new Quadrado (linhaTexto));
                        break;
                        
                        case 'r':
                        this.figuras.add(new Retangulo (linhaTexto));
                        break;
                        
                        case 'g':
                        this.figuras.add(new Poligono (linhaTexto));
                        break;
                        
                        case 't':
                        this.figuras.add(new Texto (linhaTexto));
                        break;
                    }

                    this.figuras.get (this.figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                }
          
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    protected class Sair implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            if(esperaFimPoligono)
               FinalizaPol();
              else
              if(esperaInicioPoligono)
              {
                statusFinalizaPol.setVisible(false);
                btnFinalizaPoligono.setVisible(false);
                statusVertices.setVisible(false);
              }
            
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