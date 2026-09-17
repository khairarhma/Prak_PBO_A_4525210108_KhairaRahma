/**
 * Sesi 3 — constructor berdelegasi, anggota statis, dan konstanta.
 *
 * Invariant:
 *   1. saldo tidak pernah negatif
 *   2. nomor rekening tidak berubah setelah objek dibuat
 *   3. setoran dan penarikan selalu bernilai positif
 */
public class RekeningBank {

    // TODO 1
    public static final double BUNGA_TAHUNAN = 0.025;
    public static final double BIAYA_ADMINISTRASI = 5000;
    public static final double BATAS_PENARIKAN_SEKALI = 5000000;

    // TODO 2
    private static int jumlahRekening = 0;

    private final String nomor;
    private final String pemilik;
    private double saldo;

    /**
     * Constructor ringkas.
     * TODO 3: delegasi ke constructor lengkap.
     */
    public RekeningBank(String nomor, String pemilik) {
        this(nomor, pemilik, 0);
    }

    /** Constructor lengkap — SATU-SATUNYA tempat validasi berada. */
    public RekeningBank(String nomor, String pemilik, double saldoAwal) {
        // TODO 4
        if (nomor == null || nomor.isBlank()) {
            throw new IllegalArgumentException("Nomor rekening tidak boleh kosong");
        }
        if (saldoAwal < 0) {
            throw new IllegalArgumentException("Saldo awal tidak boleh negatif");
        }

        this.nomor = nomor;
        this.pemilik = pemilik;
        this.saldo = saldoAwal;

        // TODO 5
        jumlahRekening++;
        // Hanya di sini karena constructor ringkas mendelegasikan (this(...))
        // ke constructor ini juga, jadi kalau dinaikkan di kedua constructor
        // akan terhitung dua kali untuk satu objek yang sama.
    }

    public void setor(double jumlah) {
        // TODO 6
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah setor harus positif");
        }
        saldo += jumlah;
    }

    public void tarik(double jumlah) {
        // TODO 7
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah tarik harus positif");
        }
        if (jumlah > saldo) {
            throw new IllegalArgumentException("Saldo tidak cukup");
        }
        if (jumlah > BATAS_PENARIKAN_SEKALI) {
            throw new IllegalArgumentException("Melebihi batas penarikan sekali transaksi");
        }
        saldo -= jumlah;
    }

    /** TODO 8 */
    public void potongBiayaAdmin() {
        saldo = Math.max(0, saldo - BIAYA_ADMINISTRASI);
    }

    /** TODO 9 */
    public static int getJumlahRekening() {
        return jumlahRekening;
    }

    /** TODO 10 */
    public static double bungaSetahun(double pokok) {
        return pokok * BUNGA_TAHUNAN;
    }

    public double getSaldo()  { return saldo; }
    public String getNomor()  { return nomor; }

    @Override
    public String toString() {
        return String.format("Rekening[%s] %-14s Rp%,.2f", nomor, pemilik, saldo);
    }
}