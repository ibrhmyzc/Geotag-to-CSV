import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class PhotoLocationToCSV {
    private static BufferedWriter bw = null;
    private static final String INPUT_DIRECTORY = "D:\\TestFolder";
    private static final String OUTPUT_FILENAME = "locations.csv";

    public static void main(String[] args) throws IOException {
        bw = new BufferedWriter(new FileWriter(new File(OUTPUT_FILENAME)));
        Files.find(Paths.get(INPUT_DIRECTORY),
                Integer.MAX_VALUE,
                (filePath, fileAttr) -> fileAttr.isRegularFile())
                .parallel()
                .forEach(f -> {
                    System.out.println(f);
                    try {
                        javaxt.io.Image image = new javaxt.io.Image(f.toAbsolutePath().toString());
                        double[] gps = image.getGPSCoordinate();
                        if(gps != null) {
                            bw.write("Location,"
                                    + gps[1] + "° " + (image.getGpsTags().get(1).equals("N") ? "N" : "S") + ","
                                    + gps[0] + "° " + (image.getGpsTags().get(3).equals("E") ? "E" : "W") + "\n"
                            );
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        bw.close();
    }
}

