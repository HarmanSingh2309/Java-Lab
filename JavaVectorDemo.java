import java.util.Vector;

// Custom Exception
class VectorException extends Exception {
    public VectorException(String msg) {
        super(msg);
    }
}

// 3D Vector Class
class Vector3D {

    private double x, y, z;

    // Default Constructor
    public Vector3D() {
        x = y = z = 0;
    }

    // Parameterized Constructor
    public Vector3D(double x, double y, double z) throws VectorException {

        if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z))
            throw new VectorException("Vector components cannot be NaN");

        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Addition
    public Vector3D add(Vector3D v) throws VectorException {

        if (v == null)
            throw new VectorException("Null vector cannot be added");

        return new Vector3D(
                this.x + v.x,
                this.y + v.y,
                this.z + v.z
        );
    }

    // Subtraction
    public Vector3D subtract(Vector3D v) throws VectorException {

        if (v == null)
            throw new VectorException("Null vector cannot be subtracted");

        return new Vector3D(
                this.x - v.x,
                this.y - v.y,
                this.z - v.z
        );
    }

    // Dot Product
    public double dotProduct(Vector3D v) throws VectorException {

        if (v == null)
            throw new VectorException("Null vector for dot product");

        return (this.x * v.x) +
                (this.y * v.y) +
                (this.z * v.z);
    }

    // Display
    public void display() {
        System.out.println("(" + x + ", " + y + ", " + z + ")");
    }
}

// Main Class
public class JavaVectorDemo {

    public static void main(String[] args) {

        // Java Vector Collection
        Vector<Vector3D> vectorList = new Vector<>();

        try {

            // Creating 3D vectors
            Vector3D v1 = new Vector3D(2, 3, 4);
            Vector3D v2 = new Vector3D(1, 5, 2);
            Vector3D v3 = new Vector3D(7, 1, 6);

            // Adding to Java Vector collection
            vectorList.add(v1);
            vectorList.add(v2);
            vectorList.add(v3);

            System.out.println("Stored 3D Vectors:");
            for (Vector3D v : vectorList) {
                v.display();
            }

            // Operations using elements from Java Vector
            System.out.println("\nOperations on Vectors:");

            Vector3D add = vectorList.get(0).add(vectorList.get(1));
            System.out.print("Addition: ");
            add.display();

            Vector3D sub = vectorList.get(0).subtract(vectorList.get(1));
            System.out.print("Subtraction: ");
            sub.display();

            double dot = vectorList.get(0).dotProduct(vectorList.get(1));
            System.out.println("Dot Product: " + dot);

            // Exception Demo
            Vector3D nullVector = null;
            vectorList.get(0).add(nullVector);

        }
        catch (VectorException e) {
            System.out.println("Vector Exception: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("General Exception: " + e);
        }
    }
}
