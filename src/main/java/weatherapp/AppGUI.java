package weatherapp;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.image.BufferedImage;
import java.awt.Cursor;
import java.awt.Font;



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

        //imagem do clima
        JLabel conditionImage = new JLabel(loadImage("src/assets/rain.png"));
        conditionImage.setBounds(0,125,450,217);
        add(conditionImage);

        //texto -> temperatura
        JLabel temperatureText = new JLabel("32 C");
        temperatureText.setBounds(0,350,450,54);
        temperatureText.setFont(new Font("Dialog", Font.BOLD,48));
        //centralizando o texto
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);

        //descriçao do clima
        JLabel conditionDesc = new JLabel("Chuvoso");
        conditionDesc.setBounds(0,405,450,36);
        conditionDesc.setFont(new Font("Dialog", Font.PLAIN, 32));
        conditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(conditionDesc);

        //imagem da humidade
        JLabel humidityImage = new JLabel(loadImage("src/assets/humidity.png"));
        humidityImage.setBounds(15,500,74,65);
        add(humidityImage);

        //descriçao da humidade, utilizando html dentro do JLabel apenas para facilitar do bolder
        JLabel humidityText = new JLabel("<html><b>Umidade</b> 100%</html>");
        humidityText.setBounds(90, 500, 85, 55);
        humidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(humidityText);

        JLabel windspeedImage = new JLabel(loadImage("src/assets/windspeed.png"));
        windspeedImage.setBounds(220, 500, 75, 65);
        add(windspeedImage);

        JLabel windspeedText = new JLabel("<html><b>Velocidade do Vento</b> 23 Km/h</html>");
        windspeedText.setBounds(310, 500, 115, 55);
        windspeedText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(windspeedText);

        //botao de pesquisa
        JButton searchButton = new JButton(loadImage("src/assets/search.png"));
        searchButton.setBounds(375, 13, 47, 45);
        //muda o cursor para uma mao quando este passa em cima do botao
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(searchButton);
        

    }

    // ImageIcon é usado para criar icones a partir de imagens
    private ImageIcon loadImage(String resourcePath){
        try{
            //ler a imagem do caminho
            BufferedImage image = ImageIO.read(new File(resourcePath));

            //retorna o icone, assim o componente pode renderaliza-lo.
            return new ImageIcon(image);
        }catch(IOException e){
            e.printStackTrace();
        }
        // se a tentativa falhar, alem do catch retornar o caminho do erro, o metodo deverá retornar null
        System.out.println("Não foi possivel carregar o recurso");
        return null;
    }
}
