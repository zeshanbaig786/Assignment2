public class Vector {
    private final double[] doubElements;

    public Vector(double[] _elements) {
        //TODO_Completed Task 1.1
        doubElements = _elements;
    }

    public double getElementatIndex(int _index) {
        //TODO_Completed Task 1.2
        if (_index < 0 || _index >= doubElements.length)
            return -1;
        return doubElements[_index];
    }

    public void setElementatIndex(double _value, int _index) {
        //TODO_Completed Task 1.3
        int in = _index;
        if (_index < 0 || _index >= doubElements.length)
            in = doubElements.length - 1;
        doubElements[in] = _value;
    }

    public double[] getAllElements() {
        //TODO_Completed Task 1.4
        return doubElements;
    }

    public int getVectorSize() {
        //TODO_Completed Task 1.5
        if (doubElements == null)
            return 0;
        return doubElements.length;
    }

    public Vector reSize(int _size) {
        //TODO_Completed Task 1.6
        if (doubElements == null)
            return this;
        if (_size == doubElements.length || _size <= 0)
            return this;
        double[] newDoubElements = new double[_size];
        for (int i = 0; i < _size; i++) {
            if (i >= doubElements.length)
                newDoubElements[i] = -1.0d;
            else
                newDoubElements[i] = doubElements[i];
        }
        return new Vector(newDoubElements);
    }

    /**
     * @param me    self vector
     * @param other other vector
     * @return 0 index has resized me vector, 1 index has resized to vector
     */
    private static Vector[] makeEqualSize(Vector me, Vector other) {
        Vector[] toReturn = new Vector[2];
        toReturn[0] = me;
        toReturn[1] = other;
        if (toReturn[1].getVectorSize() > toReturn[0].getVectorSize())
            toReturn[0] = toReturn[0].reSize(toReturn[1].getVectorSize());
        else if (toReturn[0].getVectorSize() > toReturn[1].getVectorSize()) {
            toReturn[1] = toReturn[1].reSize(toReturn[0].getVectorSize());
        }
        return toReturn;
    }

    public Vector add(Vector _v) {
        //TODO_Completed Task 1.7
        Vector[] vectors = makeEqualSize(this, _v);
        Vector me = vectors[0];
        Vector other = vectors[1];
        Vector result = new Vector(new double[me.getVectorSize()]);
        for (int i = 0; i < me.getVectorSize(); i++) {
            result.setElementatIndex(me.getElementatIndex(i) + other.getElementatIndex(i), i);
        }
        return result;
    }

    public Vector subtraction(Vector _v) {
        //TODO_Completed Task 1.8
        Vector[] vectors = makeEqualSize(this, _v);
        Vector me = vectors[0];
        Vector other = vectors[1];
        Vector result = new Vector(new double[me.getVectorSize()]);
        for (int i = 0; i < me.getVectorSize(); i++) {
            result.setElementatIndex(me.getElementatIndex(i) - other.getElementatIndex(i), i);
        }
        return result;
    }

    public double dotProduct(Vector _v) {
        //TODO_Completed Task 1.9
        Vector[] vectors = makeEqualSize(this, _v);
        Vector me = vectors[0];
        Vector other = vectors[1];
        double result = 0.0;
        for (int i = 0; i < me.getVectorSize(); i++) {
            result += (me.getElementatIndex(i) * other.getElementatIndex(i));
        }
        return result;
    }

    private double computeSimilarity(double[] a, double[] b) {
        double dotProduct = 0.0d;
        double normASum = 0.0d;
        double normBSum = 0.0d;

        for (int i = 0; i < a.length; i++) {
            dotProduct += a[i] * b[i];
            normASum += a[i] * a[i];
            normBSum += b[i] * b[i];
        }
        if (normASum <= 0.0 || normBSum <= 0.0) {
            return 0.0d;
        }
        double eucledianDist = (double) (Math.sqrt(normASum) * Math.sqrt(normBSum));
        return (double) (dotProduct / eucledianDist);
    }

    public double cosineSimilarity(Vector _v) {
        //TODO_Completed Task 1.10
        Vector[] vectors = makeEqualSize(this, _v);
        Vector me = vectors[0];
        Vector other = vectors[1];
        return computeSimilarity(me.getAllElements(), other.getAllElements());
    }

    @Override
    public boolean equals(Object _obj) {
        if (_obj instanceof Vector v) {
            if (v.getVectorSize() != this.getVectorSize())
                return false;
            for (int i = 0; i < this.getVectorSize(); i++) {
                if (v.getElementatIndex(i) != this.getElementatIndex(i))
                    return false;
            }
            //TODO_Completed Task 1.11
            return true;
        }
        return false;
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
