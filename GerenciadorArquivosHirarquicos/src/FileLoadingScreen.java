import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class FileLoadingScreen {
 
    private JFrame frame;
    private JProgressBar progressBar;
 
    public static void main(String[] args) {
        try {
            FileLoadingScreen window = new FileLoadingScreen();
            window.frame.setVisible(true);
            window.fillProgressBar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public FileLoadingScreen() {
        initialize();
    }
 
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 300, 120);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("File Loading Screen");
 
        // Layout
        frame.setLayout(new GridLayout(2, 1));
 
        // ProgressBar
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        frame.add(progressBar);
 
        // Fecha a tela após 3 segundos (pode ser ajustado conforme necessário)
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
 
    // Simula o preenchimento da barra de progresso (pode ser substituído por seu próprio código de carregamento)
    private void fillProgressBar() {
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = progressBar.getValue() + 1;
                progressBar.setValue(value);
                if (value < progressBar.getMaximum()) {
                    fillProgressBar();
                }
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}