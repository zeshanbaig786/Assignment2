public class Glove {
    private String strVocabulary;
    private Vector vecVector;

    public Glove(String _vocabulary, Vector _vector) {
        //TODO_Completed Task 2.1
        strVocabulary = _vocabulary;
        vecVector = _vector;
    }

    public String getVocabulary() {
        //TODO_Completed Task 2.2
        return strVocabulary;
    }

    public Vector getVector() {
        //TODO_Completed Task 2.3
        return vecVector;
    }

    public void setVocabulary(String _vocabulary) {
        //TODO_Completed Task 2.4
        strVocabulary = _vocabulary;
    }

    public void setVector(Vector _vector) {
        //TODO_Completed Task 2.5
        vecVector = _vector;
    }
}
