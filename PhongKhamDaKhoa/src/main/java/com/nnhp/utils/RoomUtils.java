/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author hoang
 */
public class RoomUtils {
    public static String generateRoomName(int bacsiId, int benhNhanId) throws NoSuchAlgorithmException {
        String raw = "bacsi-" + bacsiId + "-benhnhan-" + benhNhanId;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(raw.getBytes(StandardCharsets.UTF_8));
        StringBuilder hex = new StringBuilder();
        for (byte b : hashBytes)
            hex.append(String.format("%02x", b));
        return hex.toString().substring(0, 20);
    }
}
