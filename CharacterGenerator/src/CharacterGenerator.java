import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class CharacterGenerator {

    private static final int GAP = 1;
    private static final int THROW_GAP = 2;
    private static final char GRAY_LEVEL_0 = ' ';
    private static final char GRAY_LEVEL_1 = '-';
    private static final char GRAY_LEVEL_2 = '=';
    private static final char GRAY_LEVEL_3 = '/';
    private static final char GRAY_LEVEL_4 = 'A';
    private static final char GRAY_LEVEL_5 = 'E';
    private static final boolean IF_SAVE = true;
    private static final String SAVE_PATH = "D:\\";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> Enter a picture path: ");
        String path = scanner.nextLine();
        File file = new File(path);
        BufferedImage bufferedImage = loadImage(file);
        int[][] imageGray = new int[bufferedImage.getWidth()][bufferedImage.getHeight()];
        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                imageGray[x][y] = getGrayColor(getPixelColor(bufferedImage, x, y)).getR();
            }
        }
        int[][] avgGray = new int[bufferedImage.getWidth() / GAP][bufferedImage.getHeight() / GAP];
        for (int x = 0; x < bufferedImage.getWidth() / GAP; x++) {
            for (int y = 0; y < bufferedImage.getHeight() / GAP; y++) {
                int tempGray[][] = new int[GAP][GAP];
                for (int i = 0; i < GAP; i++) {
                    for (int j = 0; j < GAP; j++) {
                        tempGray[i][j] = imageGray[x * GAP + i][y * GAP + j];
                    }
                }
                avgGray[x][y] = getAvgGray(tempGray);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < bufferedImage.getHeight() / GAP; y++) {
            if (y % THROW_GAP == 0) {
                for (int x = 0; x < bufferedImage.getWidth() / GAP; x++) {
                    System.out.print(getGrayChar(avgGray[x][y]));
                    builder.append(getGrayChar(avgGray[x][y]));
                }
                System.out.println();
                builder.append("\r\n");
            }
        }
        System.out.println("> Converted successfully.");
        if (IF_SAVE) {
            save(builder.toString(), file.getName());
        }
    }

    private static BufferedImage loadImage(File file) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }

    private static void save(String str, String fileName) {
        String path = String.format("%s\\Characterized-%s.txt", SAVE_PATH, fileName);
        File file = new File(path);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("> Saved to %s successfully.\n", path);
    }

    private static RGB getPixelColor(BufferedImage bufferedImage, int x, int y) {
        int pixelColor = bufferedImage.getRGB(x, y);
        int r = (pixelColor & 16711680) >> 16;
        int g = (pixelColor & 65280) >> 8;
        int b = (pixelColor & 255);
        return new RGB(r, g, b);
    }

    private static RGB getGrayColor(RGB pixel) {
        int gray = (pixel.getR() * 30 + pixel.getG() * 60 + pixel.getB() * 10) / 100;
        return new RGB(gray, gray, gray);
    }

    private static int getAvgGray(int[][] gray) {
        int totalGray = 0;
        for (int x = 0; x < GAP; x++) {
            for (int y = 0; y < GAP; y++) {
                totalGray = totalGray + gray[x][y];
            }
        }
        return totalGray / (GAP * GAP);
    }

    private static char getGrayChar(int gray) {
        int grayLevel = gray / 51;
        char returnChar;
        switch (grayLevel) {
            case 5:
                returnChar = GRAY_LEVEL_0;
                break;
            case 4:
                returnChar = GRAY_LEVEL_1;
                break;
            case 3:
                returnChar = GRAY_LEVEL_2;
                break;
            case 2:
                returnChar = GRAY_LEVEL_3;
                break;
            case 1:
                returnChar = GRAY_LEVEL_4;
                break;
            case 0:
                returnChar = GRAY_LEVEL_5;
                break;
            default:
                returnChar = ' ';
                break;
        }
        return returnChar;
    }

}

class RGB {

    private int r;
    private int g;
    private int b;

    RGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }


    int getR() {
        return r;
    }

    int getG() {
        return g;
    }

    int getB() {
        return b;
    }

}