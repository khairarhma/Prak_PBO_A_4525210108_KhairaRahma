<?php
declare(strict_types=1);

/**
 * Sesi 3 — constructor berdelegasi, anggota statis, dan konstanta.
 *
 * Invariant:
 *   1. saldo tidak pernah negatif
 *   2. nomor rekening tidak berubah setelah objek dibuat
 *   3. setoran dan penarikan selalu bernilai positif
 */
class RekeningBank
{
    // Konstanta
    private const BUNGA_TAHUNAN = 0.025;
    private const BIAYA_ADMIN = 5000;
    private const BATAS_PENARIKAN = 5000000;

    // Penghitung jumlah rekening
    private static int $jumlahRekening = 0;

    private float $saldo;

    public function __construct(
        private readonly string $nomor,
        private readonly string $pemilik,
        float $saldoAwal = 0,
    ) {
        // Validasi nomor rekening
        if (trim($nomor) === '') {
            throw new InvalidArgumentException(
                'Nomor rekening tidak boleh kosong'
            );
        }

        // Validasi saldo awal
        if ($saldoAwal < 0) {
            throw new InvalidArgumentException(
                'Saldo awal tidak boleh negatif'
            );
        }

        $this->saldo = $saldoAwal;

        // Menambah jumlah rekening
        self::$jumlahRekening++;
    }

    // Named constructor untuk rekening pelajar
    public static function rekeningPelajar(
        string $nomor,
        string $pemilik
    ): static {
        return new static($nomor, $pemilik, 0);
    }

    // Menyetor uang
    public function setor(float $jumlah): void
    {
        if ($jumlah <= 0) {
            throw new InvalidArgumentException(
                'Jumlah setor harus lebih dari 0'
            );
        }

        $this->saldo += $jumlah;
    }

    // Menarik uang
    public function tarik(float $jumlah): void
    {
        if ($jumlah <= 0) {
            throw new InvalidArgumentException(
                'Jumlah penarikan harus lebih dari 0'
            );
        }

        if ($jumlah > $this->saldo) {
            throw new RuntimeException(
                'Saldo tidak mencukupi'
            );
        }

        if ($jumlah > self::BATAS_PENARIKAN) {
            throw new RuntimeException(
                'Penarikan melebihi batas sekali tarik'
            );
        }

        $this->saldo -= $jumlah;
    }

    // Memotong biaya administrasi (tidak boleh sampai saldo negatif)
    public function potongBiayaAdmin(): void
    {
        $this->saldo = max(0, $this->saldo - self::BIAYA_ADMIN);
    }

    // Mendapatkan jumlah rekening
    public static function getJumlahRekening(): int
    {
        return self::$jumlahRekening;
    }

    // Menghitung bunga setahun
    public static function bungaSetahun(float $pokok): float
    {
        if ($pokok < 0) {
            throw new InvalidArgumentException(
                'Pokok tidak boleh negatif'
            );
        }

        return $pokok * self::BUNGA_TAHUNAN;
    }

    // Mendapatkan saldo
    public function getSaldo(): float
    {
        return $this->saldo;
    }

    // Mendapatkan nomor rekening
    public function getNomor(): string
    {
        return $this->nomor;
    }

    // Menampilkan informasi rekening
    public function __toString(): string
    {
        return sprintf(
            'Rekening[%s] %-14s Rp%s',
            $this->nomor,
            $this->pemilik,
            number_format(
                $this->saldo,
                2,
                ',',
                '.'
            )
        );
    }
}