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
                      btnRetangulo           = new JButton ("Retangulo"),
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

    protected boolean primeiroMovimento,
                      esperaPonto, 
                      esperaInicioReta, esperaFimReta,desenhaReta, 
                      esperaCentroCirculo, esperaRaioCirculo, 
                      esperaCantoInicalElipse, esperaCantoFinalElipse,
                      esperaInicioQuadrado, esperaFimQuadrado,
                      esperaInicioRetangulo, esperaFimRetangulo,
                      esperaInicioTexto;

    protected Color corAtual = Color.BLACK;
    protected Color corAtualPreenchimento;
    protected Ponto p1;
    
    protected int x[] = new int[99];
    protected int y[] = new int[99];
    
    protected Linha l;
    
    protected String stringTexto = null;
    protected Font fonteTexto = new Font("Arial", 0, 20);
    
    protected Vector<Figura> figuras = new Vector<Figura>();
    
    protected Vector<Figura> aux = new Vector<Figura>();

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
        
        btnEscrever.addActionListener (new EscreverTexto ());    
        
        btnCor.addActionListener (new EscolherCor ());
        btnCorPreenchimento.addActionListener (new EscolherCorPreenchimento ());
        
        btnSair.addActionListener (new SairSalvar());
        
        btnApagar.addActionListener (new ApagarUltimoDesenho());
        
        
        final JPanel pnlBotoesDesenho = new JPanel();
        final FlowLayout flwBotoesDesenho = new FlowLayout(); 
        pnlBotoesDesenho.setLayout (flwBotoesDesenho);

        pnlBotoesDesenho.add (btnAbrir);
        pnlBotoesDesenho.add (btnSalvar);
        
        pnlBotoesDesenho.add (btnPonto);
        pnlBotoesDesenho.add (btnLinha);
        pnlBotoesDesenho.add (btnCirculo);
        pnlBotoesDesenho.add (btnElipse);
        pnlBotoesDesenho.add (btnQuadrado);
        pnlBotoesDesenho.add (btnRetangulo);
        
        
        pnlBotoesDesenho.add (btnCor);
        pnlBotoesDesenho.add (btnCorPreenchimento);
        
        pnlBotoesDesenho.add (btnEscrever);
        
        pnlBotoesDesenho.add (btnApagar);
        pnlBotoesDesenho.add (btnSair);
        
       
        final JPanel     pnlStatus = new JPanel();
        final GridLayout grdStatus = new GridLayout(1,2);
        pnlStatus.setLayout(grdStatus);

        pnlStatus.add(statusBar1);
        pnlStatus.add(statusBar2);

        final Container cntForm = this.getContentPane();
        cntForm.setLayout (new BorderLayout());
        
        cntForm.add (pnlBotoesDesenho,  BorderLayout.NORTH);
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
            for (int i=0 ; i<figuras.size(); i++)
                figuras.get(i).torneSeVisivel(g);
        }
        
        public void mousePressed (final MouseEvent e)
        {           
            if (esperaCentroCirculo)
				{                                    
                                    esperaCentroCirculo = false;
                                    
                                    p1 = new Ponto (e.getX(), e.getY(), corAtual);
                                    
                                    statusBar1.setText("Dica: clique em um ponto do circulo");
                                    
                                    esperaRaioCirculo = true;
				}
				else
				if (esperaRaioCirculo)
				{
                                    esperaRaioCirculo = false;
                                    
                                    final int raio = (int)(Math.round ( Math.sqrt (
                                    Math.pow (e.getY() - p1.getY(),2) + Math.pow (e.getX() - p1.getX(),2))));
                                    
                                    figuras.add (new Circulo(p1.getX(), p1.getY(), raio, corAtual, corAtualPreenchimento));
                                    figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                                    
                                    statusBar1.setText("Dica: clique no centro do circulo");
                                    
                                    esperaCentroCirculo = true;
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
        
        public void mouseClicked (final MouseEvent e)
        {
            if (esperaPonto)
            {
                figuras.add (new Ponto (e.getX(), e.getY(), corAtual));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                              
                statusBar1.setText("Dica: clique no local do ponto que deseja");
                
                esperaPonto = true;
                
            }
            else
                if (esperaInicioReta)
                {
                    esperaInicioReta = false;
                    
                    p1 = new Ponto (e.getX(), e.getY(), corAtual);                                  
                    
                    statusBar1.setText("Dica: clique no ponto final da reta");
                    
                    primeiroMovimento = true;
                    
                    desenhaReta = true;
                    
                    esperaFimReta = true;
                 }
                else
                    if (esperaFimReta)
                    {
                        desenhaReta = false;
                        
                        esperaFimReta = false;
                        
                        figuras.add (new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual));
                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                        
                        statusBar1.setText("Dica: clique no ponto inicial da reta");
                        
                        esperaInicioReta = true;
                    }
				
        }
        
        public void mouseReleased (final MouseEvent e)
        {}
        
        public void mouseEntered (final MouseEvent e)
        {}

        public void mouseExited (final MouseEvent e)
        {}
        
        public void mouseDragged(final MouseEvent e)
        {}

        public void mouseMoved(final MouseEvent e)
        {
            if (desenhaReta)
            {              
		if (!primeiroMovimento)
                    RedesenhaTela();
                
		l = new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual);
                
                l.torneSeVisivel(pnlDesenho.getGraphics()); 
                
                                
		primeiroMovimento = false;
                                                
                statusBar1.setText("Dica: solte o mouse no ponto final da reta");
            }
            
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
              
              esperaInicioQuadrado      = false;
              esperaFimQuadrado         = false;
            
              esperaInicioRetangulo     = false;
              esperaFimRetangulo        = false;
              
              esperaInicioTexto         = false;

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
            
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
            
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;
            
            esperaInicioTexto         = false;

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
            
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
            
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;
            
            esperaInicioTexto         = false;

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
            
            esperaInicioQuadrado      = false;
            esperaFimQuadrado         = false;
            
            esperaInicioRetangulo     = false;
            esperaFimRetangulo        = false;
            
            esperaInicioTexto         = false;
            
            statusBar1.setText("Dica: clique em um canto da elipse");
        }
    }
                
    protected class DesenhoDeQuadrado implements ActionListener
    {
        public void actionPerformed (final ActionEvent e)    
        {
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
            
            esperaInicioTexto         = false;
                        
            statusBar1.setText("Dica: clique em ponto do quadrado");
        }
    }
    
    protected class DesenhoDeRetangulo implements ActionListener
    {
        public void actionPerformed (final ActionEvent e)    
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
            
            esperaInicioRetangulo     = true;
            esperaFimRetangulo        = false;
            
            esperaInicioTexto         = false;
                        
            statusBar1.setText("Dica: clique em ponto do retangulo");
        }
    }
    
    
    protected class ApagarUltimoDesenho implements ActionListener // apagando o ultimo desenho
    {
        public void actionPerformed (final ActionEvent e)    
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
              
              esperaInicioTexto         = false;
            
           
              statusBar1.setText("Dica:");  
            
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

    private class EscolherCor implements ActionListener 
    {        
        public void actionPerformed (ActionEvent e) {
            
              esperaPonto               = false;
              
              esperaInicioReta          = false;
              esperaFimReta             = false;
              
              esperaCentroCirculo       = false;
              esperaRaioCirculo         = false;
              
              esperaCantoInicalElipse   = false;
              esperaCantoFinalElipse    = false;
              
              esperaInicioQuadrado      = false;
              esperaFimQuadrado         = false;
              
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
        public void actionPerformed (ActionEvent e) {
            
              esperaPonto               = false;
              
              esperaInicioReta          = false;
              esperaFimReta             = false;
              
              esperaCentroCirculo       = false;
              esperaRaioCirculo         = false;
              
              esperaCantoInicalElipse   = false;
              esperaCantoFinalElipse    = false;
              
              esperaInicioQuadrado      = false;
              esperaFimQuadrado         = false;
              
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
            setCursor(new Cursor(Cursor.TEXT_CURSOR));
            
              esperaPonto               = false;
              
              esperaInicioReta          = false;
              esperaFimReta             = false;
              
              esperaCentroCirculo       = false;
              esperaRaioCirculo         = false;
              
              esperaCantoInicalElipse   = false;
              esperaCantoFinalElipse    = false;
              
              esperaInicioQuadrado      = false;
              esperaFimQuadrado         = false;
              
              esperaInicioTexto         = true;            
            
            statusBar1.setText("Dica: clique onde deseja escrever o texto");
        }
    }
    
    private void RedesenhaTela()
    {
        
        pnlDesenho.setSize(pnlDesenho.getHeight()+1, pnlDesenho.getWidth()+1);
        pnlDesenho.setSize(pnlDesenho.getHeight()-1, pnlDesenho.getWidth()-1);
    }
    
    protected class SairSalvar implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
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
