public class sepeda implements Movable, Fuelable {

    @Override
    public void bergerak() {
        System.out.println("Sepeda melaju di jalan raya");
    }

    @Override
    public double kecepatanMaksimum() {
        return 30;
    }

    @Override
    public void isiBahanBakar(double jumlah) {
        throw new UnsupportedOperationException("Sepeda tidak menggunakan bahan bakar.");
    }

    @Override
    public double kapasitasTangki() {
        return 0;
    }

    @Override
    public TipeBahanBakar tipeBahanBakar() {
        throw new UnsupportedOperationException("Sepeda tidak menggunakan bahan bakar.");
    }
}