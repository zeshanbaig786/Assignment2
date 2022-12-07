import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Toolkit {
    private static List<String> listVocabulary = null;
    private static List<double[]> listVectors = null;
    private static final String FILENAME_GLOVE = "glove.6B.50d_Reduced.csv";
    private static final String FILENAME_STOPWORDS = "stopwords.csv";

    public static void loadGLOVE() throws IOException {
        BufferedReader myReader = null;
        //TODO_Completed Task 5.1
        //if(listVectors == null)
        listVectors = new ArrayList<>();
        //if(listVocabulary == null)
        listVocabulary = new ArrayList<>();
        try {
            File fileFromResource = Toolkit.getFileFromResource(FILENAME_GLOVE);
            myReader = new BufferedReader(new FileReader(fileFromResource));
            String line = myReader.readLine();
            while (line != null) {
                //System.out.println(line);
                // read next line
                String[] split = line.split(",");
                listVocabulary.add(split[0]);
                double[] vectors = new double[split.length - 1];
                for (int i = 1; i < split.length; i++) {
                    vectors[i - 1] = Double.parseDouble(split[i]);
                }
                listVectors.add(vectors);

                line = myReader.readLine();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (myReader != null)
                myReader.close();
        }
    }

    public static List<String> loadStopWords() throws IOException {
        List<String> listStopWords = new ArrayList<>();
        BufferedReader myReader = null;
        //TODO_Completed Task 5.2
        try {
            File fileFromResource = Toolkit.getFileFromResource(FILENAME_STOPWORDS);
            myReader = new BufferedReader(new FileReader(fileFromResource));
            String line = myReader.readLine();
            while (line != null) {
                //System.out.println(line);
                // read next line
                listStopWords.add(line);
                line = myReader.readLine();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (myReader != null)
                myReader.close();
        }
        return listStopWords;
    }

    private static File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = Toolkit.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    public static List<String> getListVocabulary() {
        return listVocabulary;
    }

    public static List<double[]> getlistVectors() {
        return listVectors;
    }

    /**
     * DO NOT MODIFY
     * Method to print out the semantic information.
     * <p>
     * Example: uk is to london as china is to XXXX.
     * _firISRef  _firTORef _secISRef
     * In the above example, "uk" is the first IS reference; "london" is the first TO reference
     * and "china" is the second IS reference.
     *
     * @param _secISRef The second IS reference
     * @param _firISRef The first IS reference
     * @param _firTORef The first TO reference
     * @param _list     The CosSimilarityPair list
     */
    public static void PrintSemantic(String _secISRef, String _firISRef, String _firTORef, List<CosSimilarityPair> _list) {
        System.out.println("=============================");
        System.out.printf("Identifying the logical analogies of %s (use %s and %s as a reference).\r\n", _secISRef, _firISRef, _firTORef);
        System.out.printf("%s is to %s as %s is to %s.\r\nOther options include:\r\n", _firISRef, _firTORef, _secISRef, _list.get(0).getWord2());
        for (int i = 1; i < _list.size(); i++) {
            System.out.println(_list.get(i).getWord2() + ", " + _list.get(i).getCosineSimilarity());
        }
        System.out.println("=============================");
    }

    /**
     * DO NOT MODIFY
     *
     * @param _listCosineSimilarity The CosSimilarityPair list.
     * @param _top                  How many vocabularies to print.
     */
    public static void PrintSemantic(List<CosSimilarityPair> _listCosineSimilarity, int _top) {
        if (_listCosineSimilarity.size() > 0) {
            System.out.println("============" + _listCosineSimilarity.get(0).getWord1() + "============");
            System.out.println("The nearest words are:");
            for (int i = 0; i < _top; i++) {
                System.out.printf("%s,%.5f\r\n", _listCosineSimilarity.get(i).getWord2(), _listCosineSimilarity.get(i).getCosineSimilarity());
            }
        } else {
            System.out.println("The specified word doesn't exist in the vocabulary.");
        }
    }
}
