package com.jayandra.transaksi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class TransaksiService {

    private final TransaksiRepository transaksiRepository;

    @Autowired
    public TransaksiService(TransaksiRepository transaksiRepository) {
        this.transaksiRepository = transaksiRepository;
    }

    public List<Transaksi> getTransaksi(){
        return transaksiRepository.findAll();
    }

    public HashMap<String,Object> newTransaksi(@RequestBody Transaksi transaksi){
        transaksi.setWaktuTransaksi(LocalDateTime.now());
        transaksiRepository.save(transaksi);

        HashMap<String,Object> response = new HashMap<>();

        response.put("code","260");
        response.put("message","Transaksi berhasil ditambahkan");

        return response;
    }
}
