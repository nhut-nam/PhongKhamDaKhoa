/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import VNPayConfig.Config;
import com.nnhp.formaters.Formatter;
import com.nnhp.pojo.Lichkham;
import com.nnhp.services.LichKhamService;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author namnh
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiPaymentController {

    @Autowired
    private LichKhamService lkService;

    @Value("${vnpay.tmnCode}")
    private String vnp_TmnCode;

    @Value("${vnpay.hashSecret}")
    private String vnp_HashSecret;

    @Value("${vnpay.url}")
    private String vnp_Url;

    @Value("${vnpay.returnUrl}")
    private String vnp_ReturnUrl;

    @GetMapping("/appointments/{idLichKham}")
    public ResponseEntity<Map<String, Object>> getAppointmentDetails(
            @PathVariable(name = "idLichKham") String idLichKham,
            @RequestParam(name = "userId") String userId) {
        Lichkham lk = this.lkService.getLichKhamById(Integer.parseInt(idLichKham));
        Map<String, Object> response = new HashMap<>();
        response.put("giaTien", lk.getBenhvienchuyenkhoadichvuId().getGiaTien());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create-payment")
    public ResponseEntity<Map<String, String>> createPayment(
            @RequestBody Map<String, Object> request,
            HttpServletRequest httpServletRequest) throws Exception {
        String userId = (String) request.get("userId");
        String idLichKham = (String) request.get("idLichKham");
        long amount = Long.parseLong(request.get("amount").toString());
        String orderInfo = (String) request.get("orderInfo");
        String ipAddr = httpServletRequest.getRemoteAddr();

        TreeMap<String, String> vnpParams = new TreeMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", vnp_TmnCode);
        vnpParams.put("vnp_Amount", String.valueOf(amount * 100));
        vnpParams.put("vnp_CurrCode", "VND");
        vnpParams.put("vnp_TxnRef", idLichKham + "_" + System.currentTimeMillis());
        vnpParams.put("vnp_OrderInfo", orderInfo);
        vnpParams.put("vnp_OrderType", "other");
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_ReturnUrl", vnp_ReturnUrl);
        vnpParams.put("vnp_IpAddr", ipAddr);

        vnpParams.put("vnp_CreateDate", Formatter.DATETIME_FORMATTER.format(new Date()));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 15);
        vnpParams.put("vnp_ExpireDate", Formatter.DATETIME_FORMATTER.format(cal.getTime()));

        StringBuilder hashData = new StringBuilder();
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            hashData.append(entry.getKey()).append("=")
                    .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString())).append("&");
        }
        hashData.setLength(hashData.length() - 1);

        String vnp_SecureHash = Config.hmacSHA512(vnp_HashSecret, hashData.toString());
        vnpParams.put("vnp_SecureHash", vnp_SecureHash);

        StringBuilder paymentUrl = new StringBuilder(vnp_Url).append("?");
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            paymentUrl.append(entry.getKey()).append("=")
                    .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString())).append("&");
        }
        paymentUrl.setLength(paymentUrl.length() - 1);

        Map<String, String> response = new HashMap<>();
        response.put("paymentUrl", paymentUrl.toString());
        System.out.println("Payment URL: " + paymentUrl.toString());
        return ResponseEntity.ok(response);
    }
}
