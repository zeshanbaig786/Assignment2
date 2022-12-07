import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SemanticMain {
    public List<String> listVocabulary = new ArrayList<>();  //List that contains all the vocabularies loaded from the csv file.
    public List<double[]> listVectors = new ArrayList<>(); //Associated vectors from the csv file.
    public List<Glove> listGlove = new ArrayList<>();
    public final List<String> STOPWORDS;

    public SemanticMain() throws IOException {
        STOPWORDS = Toolkit.loadStopWords();
        Toolkit.loadGLOVE();
    }


    public static void main(String[] args) throws IOException {
        StopWatch mySW = new StopWatch();
        mySW.start();
        SemanticMain mySM = new SemanticMain();
        mySM.listVocabulary = Toolkit.getListVocabulary();
        mySM.listVectors = Toolkit.getlistVectors();
        mySM.listGlove = mySM.CreateGloveList();

        List<CosSimilarityPair> listWN = mySM.WordsNearest("computer");
        Toolkit.PrintSemantic(listWN, 5);

        listWN = mySM.WordsNearest("phd");
        Toolkit.PrintSemantic(listWN, 5);

        List<CosSimilarityPair> listLA = mySM.LogicalAnalogies("china", "uk", "london", 5);
        Toolkit.PrintSemantic("china", "uk", "london", listLA);

        listLA = mySM.LogicalAnalogies("woman", "man", "king", 5);
        Toolkit.PrintSemantic("woman", "man", "king", listLA);

        listLA = mySM.LogicalAnalogies("banana", "apple", "red", 3);
        Toolkit.PrintSemantic("banana", "apple", "red", listLA);
        mySW.stop();

        if (mySW.getTime() > 2000)
            System.out.println("It takes too long to execute your code!\nIt should take less than 2 second to run.");
        else
            System.out.println("Well done!\nElapsed time in milliseconds: " + mySW.getTime());
    }

    public List<Glove> CreateGloveList() {
        List<Glove> listResult = new ArrayList<>();
        int len = listVocabulary.size();
        for (int i = 0; i < len; i++) {
            String word = listVocabulary.get(i);
            if (!STOPWORDS.contains(word)) {
                listResult.add(new Glove(word, new Vector(listVectors.get(i))));
            }
        }
        //TODO Task 6.1
        return listResult;
    }

    public List<CosSimilarityPair> WordsNearest(String _word) {
        List<CosSimilarityPair> listCosineSimilarity = new ArrayList<>();
        //TODO Task 6.2
        Vector sourceVector = null, errorVector = null;
        int sourceIndex = -1, errorIndex = -1;
        for (int i = 0; i < listGlove.size(); i++) {
            if (listGlove.get(i).getVocabulary().equals(_word)) {
                sourceVector = listGlove.get(i).getVector();
                sourceIndex = i;
            }
            if (listGlove.get(i).getVocabulary().equals("error")) {
                errorVector = listGlove.get(i).getVector();
                errorIndex = i;
            }
        }
        if (sourceVector == null) {
            sourceVector = errorVector;
            sourceIndex = errorIndex;
        }
        for (Glove glove : listGlove) {
            if (glove.getVocabulary().equals(_word))
                continue;
            double cosineSimilarity = glove.getVector().cosineSimilarity(sourceVector);
            CosSimilarityPair cosSimilarityPair = new CosSimilarityPair(_word, glove.getVocabulary(), cosineSimilarity);
            listCosineSimilarity.add(cosSimilarityPair);
        }
        return HeapSort.doHeapSort(listCosineSimilarity);
    }

    public List<CosSimilarityPair> WordsNearest(Vector _vector) {
        List<CosSimilarityPair> listCosineSimilarity = new ArrayList<>();
        //TODO Task 6.2
        for (Glove glove : listGlove) {
            if (glove.getVector().equals(_vector))
                continue;
            double cosineSimilarity = glove.getVector().cosineSimilarity(_vector);
            CosSimilarityPair cosSimilarityPair = new CosSimilarityPair(_vector, glove.getVocabulary(), cosineSimilarity);
            listCosineSimilarity.add(cosSimilarityPair);
        }
        //TODO Task 6.3
        return HeapSort.doHeapSort(listCosineSimilarity);
    }

    /**
     * Method to calculate the logical analogies by using references.
     * <p>
     * Example: uk is to london as china is to XXXX.
     * _firISRef  _firTORef _secISRef
     * In the above example, "uk" is the first IS reference; "london" is the first TO reference
     * and "china" is the second IS reference. Moreover, "XXXX" is the vocabulary(ies) we'd like
     * to get from this method.
     * <p>
     * If _top <= 0, then returns an empty listResult.
     * If the vocabulary list does not include _secISRef or _firISRef or _firTORef, then returns an empty listResult.
     *
     * @param _secISRef The second IS reference
     * @param _firISRef The first IS reference
     * @param _firTORef The first TO reference
     * @param _top      How many vocabularies to include.
     */
    public List<CosSimilarityPair> LogicalAnalogies(String _secISRef, String _firISRef, String _firTORef, int _top) {
        List<CosSimilarityPair> listResult = new ArrayList<>();
        //TODO Task 6.4
        Vector vecFirIsRef = null;
        Vector vecFirToRef = null;
        Vector vecSecIsRef = null;
        for (Glove glove : listGlove) {
            if (glove.getVocabulary().equals(_firISRef))
                vecFirIsRef = glove.getVector();
            else if (glove.getVocabulary().equals(_firTORef))
                vecFirToRef = glove.getVector();
            else if (glove.getVocabulary().equals(_secISRef))
                vecSecIsRef = glove.getVector();
            if (vecSecIsRef != null && vecFirIsRef != null && vecFirToRef != null)
                break;
        }
        if (vecSecIsRef != null && vecFirIsRef != null && vecFirToRef != null) {
            Vector newVec = vecSecIsRef.subtraction(vecFirIsRef.add(vecFirToRef));
            listResult = WordsNearest(newVec);
        }
        if (listResult.size() > _top)
            return listResult.subList(listResult.size() - _top, listResult.size());
        return listResult;
    }
}