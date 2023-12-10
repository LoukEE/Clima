package weatherapp;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class AppGUI extends JFrame {
    public AppGUI(){
        //JFRAME: titulo do app
        super("Clima");
        //Swing: Mata o programa ao fechar (não fica rodando em segundo plano)
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //tamanho da GUI: largura, altura.
        setSize(450,650);
        //centraliza a GUI.
        setLocationRelativeTo(null);
        //torna o gerenciador de layout null, para que se faça manualmente.
        setLayout(null);
        //torna a GUI não redimensionavel, uma vez que ela não é responsiva.
        setResizable(false);

        addGuiComponents();
    }

    private void addGuiComponents(){
        //area de busca
        JTextField search = new JTextField();
        //setBounds(x,y,width, height)
        search.setBounds(15,15,350,45);
        //setando a fonte
        search.setFont(new Font("Dialog", Font.PLAIN,24));
        //adicionando a area de busca
        add(search);

    }
}
