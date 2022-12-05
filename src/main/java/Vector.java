public class Vector {
    private double[] doubElements;

    public Vector(double[] _elements) {
        //TODO Task 1.1
    }

    public double getElementatIndex(int _index) {
        //TODO Task 1.2
        return 0;
    }

    public void setElementatIndex(double _value, int _index) {
        //TODO Task 1.3
    }

    public double[] getAllElements() {
        //TODO Task 1.4
        return null;
    }

    public int getVectorSize() {
        //TODO Task 1.5
        return 0;
    }

    public Vector reSize(int _size) {
        //TODO Task 1.6
        return null;
    }

    public Vector add(Vector _v) {
        //TODO Task 1.7
        return null;
    }

    public Vector subtraction(Vector _v) {
        //TODO Task 1.8
        return null;
    }

    public double dotProduct(Vector _v) {
        //TODO Task 1.9
        return 0;
    }

    public double cosineSimilarity(Vector _v) {
        //TODO Task 1.10
        return 0;
    }

    @Override
    public boolean equals(Object _obj) {
        Vector v = (Vector) _obj;
        boolean boolEquals = true;
        //TODO Task 1.11
        return boolEquals;
    }

    @Override
    public String toString() {
        StringBuilder mySB = new StringBuilder();
        for (int i = 0; i < this.getVectorSize(); i++) {
            mySB.append(String.format("%.5f", doubElements[i])).append(",");
        }
        mySB.delete(mySB.length() - 1, mySB.length());
        return mySB.toString();
    }
}
