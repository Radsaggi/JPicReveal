/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cidisplay;

import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Radsaggi
 */
public final class CutImageGrid extends JPanel {

    private ImageToggleButton buttons[][];
    private int eachWidth, eachHeight;
    private int rows, columns;
    private BufferedImage image;

    public CutImageGrid(int rows, int columns) {
        super();

        setLayout(new GridLayout(rows, columns));
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        this.rows = rows;
        this.columns = columns;

        buttons = new ImageToggleButton[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                buttons[i][j] = new ImageToggleButton(new String(
                        new char[]{(char) ('A' + i), ' ', (char) ('A' + j)}));
                this.add(buttons[i][j]);
            }
        }

        eachWidth = eachHeight = 0;
    }

    public CutImageGrid(BufferedImage image, int rows, int columns) {
        this(rows, columns);
        setImage(image);
    }

    public void setImage(BufferedImage image) {
        this.image = image;

        eachHeight = image.getHeight() / rows;
        eachWidth = image.getWidth() / columns;


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                buttons[i][j].setImage(image.getSubimage(
                        j * eachWidth, i * eachHeight, eachWidth, eachHeight));
            }
        }
    }

    public boolean isImageSet() {
        return image != null;
    }

    public void setAllButtonState(boolean z) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                buttons[i][j].setSelected(z);
            }
        }
    }
}
