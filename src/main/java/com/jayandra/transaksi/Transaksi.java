package com.jayandra.transaksi;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Transaksi {

    public Transaksi(int nomorNasabah, int jenisTransaksi, LocalDateTime waktuTransaksi, int statusTransaksi, String logTransaksi) {
        this.nomorNasabah = nomorNasabah;
        this.jenisTransaksi = jenisTransaksi;
        this.waktuTransaksi = waktuTransaksi;
        this.statusTransaksi = statusTransaksi;
        this.logTransaksi = logTransaksi;
    }

    @Id
    @SequenceGenerator(
            name = "transaksi_sequence",
            sequenceName = "transaksi_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaksi_sequence"
    )
    private Long idTransaksi;
    private int nomorNasabah;
    private int jenisTransaksi;
    private LocalDateTime waktuTransaksi;
    private int statusTransaksi;
    private String logTransaksi;

}
