package com.jayandra.transaksi;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(TransaksiController.class);

    @Autowired
    private WebClient.Builder tagihanService;

    private final TransaksiService transaksiService;
    @Autowired
    public TransaksiController(TransaksiService transaksiService) {
        this.transaksiService = transaksiService;
    }

    @GetMapping
    public List<Transaksi> getTransaksi(){
        logger.info("Mengambil Transaksi dari db postgre");
        return transaksiService.getTransaksi();
    }

    @PostMapping
    public HashMap<String,Object> newTransaksi(@RequestBody Transaksi transaksi){
        return transaksiService.newTransaksi(transaksi);
    }

    @GetMapping("/tagihan")
    public String testTagihan(){
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
//        WebClient client = WebClient.create("http://10.10.30.32:7002");
        WebClient client = WebClient.create("http://localhost:7002");

        HashMap hsmap = new HashMap();
        hsmap.put("saldo", 50000);
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
//        WebClient client = WebClient.create("http://10.10.30.32:7002");
        WebClient client = WebClient.create("http://localhost:7002");

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
