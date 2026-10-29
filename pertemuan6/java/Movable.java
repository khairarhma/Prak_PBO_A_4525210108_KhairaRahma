/**
 * Sesi 6 — kontrak "bisa bergerak".
 * Interface menjawab: APA YANG BISA dilakukan, bukan APA benda ini.
 */
public interface Movable {

    void bergerak();

    double kecepatanMaksimum();

    /**
     * Default method (Java 8+) menyediakan implementasi bawaan yang boleh
     * ditimpa implementornya. PHP tidak punya padanannya di interface.
     *
     * Mengembalikan ringkasan dengan format: "kecepatan maksimum 180 km/jam"
     * menggunakan nilai dari kecepatanMaksimum().
     */
    default String ringkasanGerak() {
        return "kecepatan maksimum " + String.format("%.0f", kecepatanMaksimum()) + " km/jam";
    }
}
