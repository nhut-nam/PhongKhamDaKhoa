/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import VNPayConfig.Config;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author namnh
 */
public class ReturnController {
    @Value("${vnpay.hashSecret}")
    private String vnp_HashSecret;

    @GetMapping("/vnpay-return")
    public String handleReturn(
            @RequestParam Map<String, String> params,
            Model model) throws Exception {
        String vnp_SecureHash = params.get("vnp_SecureHash");
        params.remove("vnp_SecureHash");

        TreeMap<String, String> sortedParams = new TreeMap<>(params);
        StringBuilder hashData = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            if (entry.getKey().startsWith("vnp_")) {
                hashData.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        hashData.setLength(hashData.length() - 1);

        String computedHash = Config.hmacSHA512(vnp_HashSecret, hashData.toString());

        if (!computedHash.equalsIgnoreCase(vnp_SecureHash)) {
            model.addAttribute("message", "Có lỗi xảy ra: Checksum không hợp lệ!");
            return "payment-result";
        }

        String vnp_ResponseCode = params.get("vnp_ResponseCode");
        if ("00".equals(vnp_ResponseCode)) {
            model.addAttribute("message", "Thanh toán thành công!");
            model.addAttribute("orderId", params.get("vnp_TxnRef"));
            model.addAttribute("amount", Long.parseLong(params.get("vnp_Amount")) / 100);
            model.addAttribute("transactionNo", params.get("vnp_TransactionNo"));
        } else {
            model.addAttribute("message", "Thanh toán thất bại. Mã lỗi: " + vnp_ResponseCode);
        }

        return "payment-result";
    }
}
