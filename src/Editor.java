public class Editor
{
    public static void main (String args[])
    {
       try
       {
          new Janela ();
       }
       catch (Exception x)
       {
          System.out.println(x.getMessage());
       }        
    }
}