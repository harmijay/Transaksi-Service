package com.jayandra.transaksi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path="api/transaksi")
public class TransaksiController {

    private final TransaksiRepository transaksiRepository;

    @Autowired
    public TransaksiController(TransaksiRepository transaksiRepository) {
        this.transaksiRepository = transaksiRepository;
    }

    @GetMapping
    public List<Transaksi> getTransaksi(){
        return transaksiRepository.findAll();
    }

    @PostMapping
    public void newTransaksi(@RequestBody Transaksi transaksi){
        transaksi.setWaktuTransaksi(LocalDateTime.now());
        transaksiRepository.save(transaksi);
    }

}
