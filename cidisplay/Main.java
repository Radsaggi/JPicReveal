/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cidisplay;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Radsaggi
 */
public class Main extends JFrame {

    private static final int ROWS = 7, COLUMNS = 9;
    private static final int IMAGE_WIDTH = 640, IMAGE_HEIGHT = 480;
    private CutImageGrid imageGrid;
    private JButton changeB, showB;
    private JLabel msgL;
    private static final File FOLDER = new File("images/");

    public Main() {
        super("Image Display");
        setSize(1024, 728);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        {
            JPanel p = new JPanel();
            p.setLayout(null);
            p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            imageGrid = new CutImageGrid(ROWS, COLUMNS);
            int w = this.getWidth() - IMAGE_WIDTH;
            int h = this.getHeight() - IMAGE_HEIGHT;
            w /= 2; h /= 2;
            imageGrid.setBounds(w, h, IMAGE_WIDTH, IMAGE_HEIGHT);
            
            p.add(imageGrid, BorderLayout.CENTER);
            this.add(p, BorderLayout.CENTER);
        }

        {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

            showB = new JButton("Show image");
            showB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    imageGrid.setAllButtonState(true);
                }
            });
            p.add(showB);

            changeB = new JButton("Change image");
            changeB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changeImage();
                }
            });
            p.add(changeB);

            msgL = new JLabel("The image is not set...");
            p.add(msgL);

            this.add(p, BorderLayout.SOUTH);
        }
        
        
    }

    private void setImage(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            image = createBI(image.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH));
            imageGrid.setImage(image);

            msgL.setText(file.getName());
        } catch (IOException e) {
            e.printStackTrace(System.err);
            msgL.setText(e.getMessage());
        }
    }

    private void changeImage() {
        
        imageGrid.setAllButtonState(false);
        
        File files[] = FOLDER.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String name = pathname.getName();
                return name.endsWith(".JPG") || name.endsWith(".jpg");
            }
        });

        int l = files.length;
        System.out.println(l);
        int idx = (int) (Math.random() * l);

        setImage(files[idx]);
    }

    private static BufferedImage createBI(Image image) {
        BufferedImage buffered = new BufferedImage(image.getWidth(null),
                image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        buffered.getGraphics().drawImage(image, 0, 0, null);
        return buffered;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main frame = new Main();
                frame.setVisible(true);
            }
        });
    }
}
