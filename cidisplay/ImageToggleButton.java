
package cidisplay;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JToggleButton;

/**
 *
 * @author Radsaggi
 */
public final class ImageToggleButton extends JToggleButton {

    private BufferedImage image;
    private Dimension dimension;

    public ImageToggleButton(String text) {
        super(text);
    }

    public ImageToggleButton(String text, BufferedImage image) {
        this(text);
        this.setImage(image);
    }

    public boolean isImageSet() {
        return this.image != null;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        dimension = new Dimension(image.getWidth(), image.getHeight());
    }

    public Dimension getImageDimension() {
        return dimension;
    }

    public int getImageWidth() {
        return (int) dimension.getWidth();
    }

    public int getImageHeight() {
        return (int) dimension.getHeight();
    }

    @Override
    public void paintComponent(Graphics g) {
        if (!this.isImageSet() || !super.isSelected()) {
            super.paintComponent(g);
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();

        g2.drawImage(image, new AffineTransform(1f, 0f, 0f, 1f, 0f, 0f), null);

        g2.dispose();
    }
}
