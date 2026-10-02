# Polimorfisme: Bangun Datar & Notifikasi (Java + PHP)
# Laporan Praktikum PBO Pertemuan05
*Nama          :* Khaira Rahma Aprilliani
*NPM           :* 4525210108
*Mata Kuliah   :* Pemrograman Berorientasi Objek
## Materi
Menghitung bangun datar
Materi: Polimorfisme

Latihan Sesi 5 tentang **polimorfisme** dalam OOP. Kelas induk menetapkan *kontrak*, kelas turunan mengisi *caranya*, dan kode pemanggil tidak perlu tahu tipe konkretnya.

Proyek ini ada dalam dua bahasa:

| Bahasa | Isi |
|--------|-----|
| Java | Hierarki `BangunDatar` + perbandingan anti-pattern vs polimorfik |
| PHP  | Hierarki `BangunDatar` + latihan mandiri hierarki `Notifikasi` |

---

## Struktur File

```
.
├── java/
│   ├── BangunDatar.java           # kelas abstrak (kontrak)
│   ├── Lingkaran.java
│   ├── Persegi.java
│   ├── Segitiga.java
│   ├── Trapesium.java
│   ├── Main.java                  # program uji (loop tidak boleh diubah)
│   ├── AntiPattern.java           # versi TANPA polimorfisme (jangan dihapus)
│   └── AntiPatternRefaktor.java   # versi polimorfik
└── php/
    ├── BangunDatar.php            # BangunDatar + semua turunannya
    ├── main.php
    └── notifikasi.php             # Notifikasi, Email, SMS, WhatsApp
```

---

## Hierarki Kelas

### Bangun Datar

```
BangunDatar (abstract)
│   luas(): double / float        <- abstract
│   keliling(): double / float    <- abstract
│   toString() / __toString()     <- memanggil luas() & keliling()
│
├── Lingkaran    (jari-jari)
├── Persegi      (sisi)
├── Segitiga     (a, b, c)  -> luas pakai rumus Heron
└── Trapesium    (sejajarA, sejajarB, kakiC, kakiD, tinggi)
```

### Notifikasi (PHP)

```
Notifikasi (abstract)
│   $tujuan (readonly)
│   kirim(string $pesan): void    <- abstract
│   saluran(): string
│
├── Email
├── SMS
└── WhatsApp
```

---

## Konsep yang Dipraktikkan

- **Kelas abstrak & kontrak**: setiap bangun datar wajib punya `luas()` dan `keliling()`.
- **Upcasting**: variabel bertipe induk menampung objek turunan (`BangunDatar[] daftar`).
- **Dynamic dispatch**: `toString()` ada di kelas induk tetapi memanggil `luas()` milik turunan. Saat runtime, method dipilih berdasarkan tipe objek sebenarnya, bukan tipe variabelnya.
- **Validasi di konstruktor**: ukuran `<= 0` ditolak dengan `IllegalArgumentException` (Java) / `InvalidArgumentException` (PHP). Segitiga juga menolak sisi yang tidak memenuhi ketaksamaan segitiga.
- **Tanpa pemeriksaan tipe**: `kirimSemua()` hanya melakukan `foreach` lalu `kirim()`, tanpa `instanceof` atau `match`/`switch`.
- **Downcasting seperlunya**: di `Main.java`, `instanceof Lingkaran` dipakai hanya untuk mengakses `getJariJari()` yang tidak ada di induk.
- Pakai `Math.PI` / `M_PI`, bukan `3.14`.

---

## Cara Menjalankan

### Java

Butuh **JDK 16 atau lebih baru** (memakai `record` dan pattern matching `instanceof`).

```bash
cd java
javac *.java

java Main                  # hierarki polimorfik
java AntiPattern           # versi tanpa polimorfisme
java AntiPatternRefaktor   # versi polimorfik dari AntiPattern
```

### PHP

Butuh **PHP 8.1 atau lebih baru** (memakai `readonly` property).

```bash
cd php
php main.php
php notifikasi.php
```

---

## Hasil yang Diharapkan

Nilai luas dan keliling untuk contoh data:

| Bangun | Ukuran | Luas | Keliling |
|--------|--------|-----:|---------:|
| Lingkaran | r = 7 | 153,94 | 43,98 |
| Persegi | sisi = 5 | 25,00 | 20,00 |
| Segitiga | 3, 4, 5 | 6,00 | 12,00 |
| Trapesium | 6, 4, 3, 3, tinggi 2,5 | 12,50 | 16,00 |

Total luas semua bangun: **197,44** (tanpa Trapesium: **184,94**).

Contoh keluaran `notifikasi.php`: tiap saluran mencetak format pesannya masing-masing untuk pesan yang sama.

---

## Anti-Pattern vs Polimorfik

`AntiPattern.java` memakai satu method `hitungLuas(Object)` berisi rantai `if / else if` dengan `instanceof`. `AntiPatternRefaktor.java` memindahkan rumus ke tiap kelas.

| Pertanyaan | Anti-pattern | Polimorfik |
|------------|--------------|------------|
| Menambah satu bangun baru | Sunting method lama (sekitar 3 baris: `record` + cabang `else if`), plus tambah ke array | 0 baris kode lama disunting. Buat kelas baru, tambah ke array |
| Lupa menambah cabang / implementasi | Jatuh ke `throw` saat **runtime** | Kompilator menolak karena method abstract belum diimplementasi |
| Letak pengetahuan rumus luas | Di method terpusat di luar | Di kelas bangunnya sendiri (enkapsulasi) |

Prinsip yang terlihat di sini adalah *Open/Closed*: kode terbuka untuk ekstensi (kelas baru) tetapi tertutup untuk modifikasi (loop dan method lama tidak berubah).

---

## Catatan

- Angka pada contoh `Trapesium` hanya contoh. Sesuaikan dengan soal bila ada.
- Pada `Main.java`, aturan Langkah 1-2: hanya boleh **menambah** baris pada array, logika perulangan tidak diubah.
- Jangan hapus `AntiPattern.java`; file itu dipakai berdampingan dengan versi refaktor saat demo.
 
 # Screenshoot
 ## Main java
 ![alt text](image.png)
 ## Main php
 ![alt text](image-1.png)

