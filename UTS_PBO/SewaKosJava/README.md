# Proyek UTS PBO — Aplikasi Sewa Kos (Java)

## Informasi Proyek

| Informasi | Keterangan |
|---|---|
| Mata kuliah | Pemrograman Berbasis Objek (PBO) |
| Tema | Sewa Kos |
| PJ tema | Naufal Maulana Saputra |
| Bahasa | Java saja |
| Jenis aplikasi | Console application |

## Deskripsi

Aplikasi console untuk menampilkan kamar kos, mencatat penyewaan, menghitung tagihan, memproses pembayaran, dan menampilkan informasi pengguna. Data disimpan sementara selama program berjalan; aplikasi ini belum menggunakan database.

## Fitur

1. Menampilkan daftar kamar, harga, dan status.
2. Membuat transaksi sewa dengan validasi data penyewa, kamar, serta durasi sewa.
3. Menghitung tagihan berdasarkan harga kamar × durasi + biaya admin.
4. Memproses pembayaran dan menolak nominal tidak valid atau pembayaran kurang.
5. Menampilkan transaksi dan informasi pengguna.

## Struktur File

```text
SewaKosJava/
├── README.md
├── uml/
│   ├── SewaKosUML.puml
│   ├── SewaKosUML.dot
│   ├── SewaKosUML.png
│   └── SewaKosUML.svg
└── src/
    └── com/sewakos/
        ├── app/
        │   └── Main.java
        ├── model/
        │   ├── DapatDibayar.java
        │   ├── Kamar.java
        │   ├── Pengelola.java
        │   ├── PenggunaKos.java
        │   ├── Penyewa.java
        │   ├── SewaKos.java
        │   ├── StatusKamar.java
        │   └── StatusPembayaran.java
        └── service/
            └── ManajemenKos.java
```

## Konsep Materi Pertemuan 1–7

| Materi | Implementasi |
|---|---|
| GitHub | Seluruh source code berada dalam satu proyek/repository. Commit dan push dilakukan melalui Git. GitHub sendiri bukan fitur bahasa Java. |
| Kelas, objek, enkapsulasi | Kelas `Kamar`, `Penyewa`, `PenggunaKos`, dan `SewaKos`; atribut dibuat `private` serta diakses melalui getter/method. |
| Konstruktor, static, konstanta | Konstruktor memvalidasi objek; `Kamar.jumlahKamar`, `SewaKos.urutanSewa`, dan konstanta `BIAYA_ADMIN`, `MIN_HARGA_PER_BULAN`, `MAX_LAMA_SEWA_BULAN`. |
| Pewarisan | `Penyewa` dan `Pengelola` mewarisi `PenggunaKos`. |
| Polimorfisme | `ManajemenKos` menyimpan objek berbeda dalam `List<PenggunaKos>` dan memanggil `tampilkanInfo()` yang dioverride subclass. |
| Abstract, interface, enum | `PenggunaKos` adalah abstract class; `DapatDibayar` adalah interface; `StatusKamar` dan `StatusPembayaran` adalah enum. |
| Relasi objek | `SewaKos` memiliki referensi `Penyewa` dan `Kamar`; `ManajemenKos` mengelola kumpulan kamar, sewa, dan pengguna. |

## Validasi

Nilai yang tidak valid ditolak menggunakan `throw new Exception`, antara lain nama kosong, nomor telepon salah, nomor kamar tidak sesuai format, harga di bawah minimum, durasi di luar rentang 1–24 bulan, kamar tidak tersedia, nominal pembayaran tidak valid, pembayaran kurang, dan ID sewa tidak ditemukan.

## Menjalankan Program

Gunakan JDK 8 atau lebih baru. Jalankan perintah dari folder proyek.

### Windows PowerShell

```powershell
$files = Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $files
java -cp out com.sewakos.app.Main
```

### Linux/macOS

```bash
javac -d out $(find src -name '*.java')
java -cp out com.sewakos.app.Main
```

## Alur Singkat

1. Pilih menu **1** untuk memeriksa kamar.
2. Pilih menu **2** untuk membuat sewa; catat ID sewa yang ditampilkan.
3. Pilih menu **3** untuk membayar berdasarkan ID sewa.
4. Pilih menu **4** untuk melihat transaksi atau **5** untuk melihat polimorfisme pengguna.
5. Pilih menu **0** untuk keluar.

Tagihan dihitung dengan rumus: **(harga kamar per bulan × lama sewa) + biaya admin Rp50.000**.

## Diagram UML

Lihat `uml/SewaKosUML.png` untuk diagram siap dilihat, atau `uml/SewaKosUML.puml` sebagai source PlantUML yang dapat diedit.
