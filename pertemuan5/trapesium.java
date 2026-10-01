class Trapesium extends BangunDatar {

    private final double sisiAtas;
    private final double sisiBawah;
    private final double tinggi;
    private final double sisiMiring;

    public Trapesium(double sisiAtas, double sisiBawah, double tinggi, double sisiMiring) {
        super("Trapesium");
        if (sisiAtas <= 0 || sisiBawah <= 0 || tinggi <= 0 || sisiMiring <= 0) {
            throw new Error("Semua dimensi trapesium harus lebih besar dari 0.");
        }
        this.sisiAtas = sisiAtas;
        this.sisiBawah = sisiBawah;
        this.tinggi = tinggi;
        this.sisiMiring = sisiMiring;
    }

    @Override public double luas() {
        return ((sisiAtas + sisiBawah) * tinggi) / 2;
    }

    @Override public double keliling() {
        return sisiAtas + sisiBawah + 2 * sisiMiring;
    }

}
