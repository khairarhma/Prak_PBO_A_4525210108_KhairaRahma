public class segitiga extends BangunDatar {

    private final double sisiA;
    private final double sisiB;
    private final double sisiC;

    public segitiga(double sisiA, double sisiB, double sisiC) {
        super("Segitiga");
        if (sisiA <= 0 || sisiB <= 0 || sisiC <= 0) {
            throw new Error("Sisi segitiga harus lebih besar dari 0.");
        }
        this.sisiA = sisiA;
        this.sisiB = sisiB;
        this.sisiC = sisiC;
    }

    @Override public double luas() {
        // Menggunakan rumus Heron untuk menghitung luas segitiga.
        double s = (sisiA + sisiB + sisiC) / 2; // semi-perimeter
        return Math.sqrt(s * (s - sisiA) * (s - sisiB) * (s - sisiC));
    }

    @Override public double keliling() {
        return sisiA + sisiB + sisiC;
    }
    
}
