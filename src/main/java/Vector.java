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
                newDoubElements[i] = -1;
            else
                newDoubElements[i] = doubElements[i];
        }
        return new Vector(newDoubElements);
    }

    public Vector add(Vector _v) {
        //TODO_Completed Task 1.7
        Vector me = this;
        Vector other = _v;
        if (other.getVectorSize() > this.getVectorSize())
            me = me.reSize(other.getVectorSize());
        else if (this.getVectorSize() > other.getVectorSize()) {
            other = other.reSize(me.getVectorSize());
        }
        Vector result = new Vector(new double[me.getVectorSize()]);
        for (int i = 0; i < me.getVectorSize(); i++) {
            result.setElementatIndex(me.getElementatIndex(i) + other.getElementatIndex(i), i);
        }
        return result;
    }

    public Vector subtraction(Vector _v) {
        //TODO_Completed Task 1.8
        Vector me = this;
        Vector other = _v;
        if (other.getVectorSize() > this.getVectorSize())
            me = me.reSize(other.getVectorSize());
        else if (this.getVectorSize() > other.getVectorSize()) {
            other = other.reSize(me.getVectorSize());
        }
        Vector result = new Vector(new double[me.getVectorSize()]);
        for (int i = 0; i < me.getVectorSize(); i++) {
            result.setElementatIndex(me.getElementatIndex(i) - other.getElementatIndex(i), i);
        }
        return result;
    }

    public double dotProduct(Vector _v) {
        //TODO_Completed Task 1.9
        Vector me = this;
        Vector other = _v;
        if (other.getVectorSize() > this.getVectorSize())
            me = me.reSize(other.getVectorSize());
        else if (this.getVectorSize() > other.getVectorSize()) {
            other = other.reSize(me.getVectorSize());
        }
        double result = 0;
        for (int i = 0; i < me.getVectorSize(); i++) {
            result += (me.getElementatIndex(i) * other.getElementatIndex(i));
        }
        return result;
    }

    public double cosineSimilarity(Vector _v) {
        //TODO_Completed Task 1.10
        Vector me = this;
        Vector other = _v;
        if (other.getVectorSize() > this.getVectorSize())
            me = me.reSize(other.getVectorSize());
        else if (this.getVectorSize() > other.getVectorSize()) {
            other = other.reSize(me.getVectorSize());
        }
        double result;
        double dotProduct = me.dotProduct(other);
        double normA = 0;
        double normB = 0;
        for (int i = 0; i < me.getVectorSize(); i++) {
            normA += me.getElementatIndex(i) * me.getElementatIndex(i);
            normB += other.getElementatIndex(i) * other.getElementatIndex(i);
        }
        result = dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
        return result;
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
