<?php

namespace Perpustakaan\Model;

use InvalidArgumentException;
use Perpustakaan\Model\Jurusan;
use Perpustakaan\Model\Mahasiswa;

class Anggota extends Jurusan
{
    private string $kodeAnggota;
    private ?\DateTime $anggotaSejak = null;
    private Mahasiswa $anggotaPerpus;

    public function __construct(string $kodeJurusan, string $namaJurusan, string $kodeAnggota)
    {
        parent::__construct($kodeJurusan, $namaJurusan);
        $this->kodeAnggota = $kodeAnggota;
    }

    public function daftarkanAnggota(Mahasiswa $m, \DateTime $masaKeanggotaan): void
    {
        if ($m === null) {
            throw new InvalidArgumentException("Mahasiswa tidak boleh kosong");
        }

        $this->anggotaPerpus = $m;
        $this->anggotaSejak = $masaKeanggotaan;
    }

    public function checkMasaKeanggotaan(\DateTime $dateNow): string
    {
        if ($this->anggotaSejak === null) {
            return "Keanggotaan belum didaftarkan";
        }


        // Mengecek apakah waktu sekarang melebihi 3 tahun dari masa pendaftaran
        $batasKeanggotaan = (clone $this->anggotaSejak)->modify('+3 years');
        if ($dateNow > $batasKeanggotaan) {
            return "Keanggotaan tidak valid (lebih dari 3 tahun)";
        }

        return "Keanggotaan valid";
    }

    public function getKeanggotaan(): void
    {
        echo "Nama Anggota: " . ($this->anggotaPerpus) . "\n";
        echo "Jurusan: " . parent::getNama() . "\n";
        echo "Kode Anggota: " . $this->kodeAnggota . "\n";
        echo "Anggota Perpustakaan Sejak: " . ($this->anggotaSejak ? $this->formatDateMasaAnggota($this->anggotaSejak) : "-") . "\n";
    }

    private function formatDateMasaAnggota(\DateTime $date): string
    {
        return $date->format('d-m-Y');
    }
}