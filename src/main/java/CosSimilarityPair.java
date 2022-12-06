public class CosSimilarityPair {
    private String strWord1;
    private String strWord2;
    private double doubCS;
    private Vector vecVector;

    public CosSimilarityPair(String _word1, String _word2, double _cosinesimilarity) {
        //TODO_Completed Task 3.1
        strWord1 = _word1;
        strWord2 = _word2;
        doubCS = _cosinesimilarity;
    }

    public CosSimilarityPair(Vector _vector, String _word2, double _cosinesimilarity) {
        //TODO_Completed Task 3.2
        vecVector = _vector;
        strWord2 = _word2;
        doubCS = _cosinesimilarity;
    }

    public String getWord1() {
        //TODO_Completed Task 3.3
        return strWord1;
    }

    public String getWord2() {
        //TODO_Completed Task 3.4
        return strWord2;
    }

    public double getCosineSimilarity() {
        //TODO_Completed Task 3.5
        return doubCS;
    }

    public void setCosineSimilarity(double _cs) {
        //TODO_Completed Task 3.6
        doubCS = _cs;
    }

    public void setWord1(String _word) {
        //TODO_Completed Task 3.7
        strWord1 = _word;
    }

    public void setWord2(String _word) {
        //TODO_Completed Task 3.8
        strWord2 = _word;
    }

    public Vector getVector() {
        //TODO_Completed Task 3.9
        return vecVector;
    }

    public void setVector(Vector _vector) {
        //TODO_Completed Task 3.10
        vecVector = _vector;
    }

}
