import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class createStickers {

    public void createSticker(InputStream inputStream, String nameFile) throws IOException {

        //Ler a imagem
        BufferedImage originalImage = ImageIO.read(inputStream);

        // Criar nova imagem com Transparência e um novo tamanho (em memória)
        int height = originalImage.getHeight();
        int width = originalImage.getWidth();
        int newHeight = height + 200;

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // Copiar a imagem orinal para a nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // Configurando a fonte da imagem
        var font = new Font(Font.MONOSPACED, Font.BOLD, 90);
        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);

        // Escrever uma frase na imagem
        graphics.drawString("XXXXX", 250, (newHeight-100));

        // Escrever a nova imagem em um arquivo
        ImageIO.write(newImage, "png", new File(nameFile));

    }

}
