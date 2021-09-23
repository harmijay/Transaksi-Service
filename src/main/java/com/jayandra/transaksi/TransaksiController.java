package com.jayandra.transaksi;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="api/transaksi")
public class TransaksiController {

    @Autowired
    private WebClient.Builder tagihanService;
//    @LoadBalanced
//    public WebClient tagihanService = WebClient.create("http://tagihanservice");

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

    @GetMapping("/tagihan")
    public String testTagihan(){
//        WebClient client = tagihanService.baseUrl("http://10.10.30.41:7004").build();
        WebClient client = WebClient.create("http://10.10.30.41:7004");

        WebClient.ResponseSpec responseSpec = client.get()
                .uri("/tagihan/all")
                .retrieve();

        String responseBody = responseSpec.bodyToMono(String.class).block();

        return responseBody;
    }

    @GetMapping("/tagihan/post")
    public String postTagihan() throws JSONException {
        WebClient client = WebClient.create("http://10.10.30.41:7004");

        HashMap hsmap = new HashMap();
        hsmap.put("idPenagih", 3);
        hsmap.put("idYangDitagih", 5);
        hsmap.put("tagihanNominal", 2222.00);
        hsmap.put("tagihanLunas", "false");
        hsmap.put("tagihanDurasi", 7);
        hsmap.put("tagihanCreated", "null");
        hsmap.put("tagihanKeterangan", "mantapgan123");

        WebClient.ResponseSpec responseSpec = client.post()
                .uri("/tagihan")
                .body(Mono.just(hsmap), HashMap.class)
                .retrieve();

        String responseBody = responseSpec.bodyToMono(String.class).block();

        return responseBody;
    }

    @GetMapping("/tabungan/post")
    public String postTabungan() throws JSONException {
        WebClient client = WebClient.create("http://10.10.30.32:7002");

        HashMap hsmap = new HashMap();
        hsmap.put("saldo", 3);
        hsmap.put("jenisTabungan", "Platinum");

        WebClient.ResponseSpec responseSpec = client.post()
                .uri("/tabungan/")
                .body(Mono.just(hsmap), HashMap.class)
                .retrieve();

        String responseBody = responseSpec.bodyToMono(String.class).block();

        return responseBody;
    }

    @GetMapping("/tabungan/put")
    public String putTabungan() throws JSONException {
        WebClient client = WebClient.create("http://10.10.30.32:7002");

        HashMap hsmap = new HashMap();
        hsmap.put("nomorRekening", 3);
        hsmap.put("jumlah", 2);

        WebClient.ResponseSpec responseSpec = client.put()
                .uri("/tabungan/kurangi_saldo")
                .body(Mono.just(hsmap), HashMap.class)
                .retrieve();

        String responseBody = responseSpec.bodyToMono(String.class).block();

        return responseBody;
    }
}
