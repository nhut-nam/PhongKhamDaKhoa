/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nnhp.controllers;

import com.nnhp.utils.RoomUtils;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hoang
 */
@RestController
@RequestMapping("/api/webrtc")
@CrossOrigin
public class ApiWebRTCController {

    private final Map<String, String> offers = new ConcurrentHashMap<>();
    private final Map<String, String> answers = new ConcurrentHashMap<>();
    private final Map<String, List<Map<String, Object>>> candidates = new ConcurrentHashMap<>();

    @GetMapping("/room-name")
    public ResponseEntity<String> getRoomName(@RequestParam(name = "bacSiId") int bacsiId, @RequestParam(name = "benhNhanId") int benhNhanId) throws NoSuchAlgorithmException {
        String room = RoomUtils.generateRoomName(bacsiId, benhNhanId);
        return ResponseEntity.ok(room);
    }

    @PostMapping("/offer")
    public ResponseEntity<Void> saveOffer(@RequestParam(name = "roomId") String roomId, @RequestBody String sdp) {
        offers.put(roomId, sdp);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/offer")
    public ResponseEntity<String> getOffer(@RequestParam(name = "roomId") String roomId) {
        return ResponseEntity.ok(offers.get(roomId));
    }

    @PostMapping("/answer")
    public ResponseEntity<Void> saveAnswer(@RequestParam(name = "roomId") String roomId, @RequestBody String sdp) {
        answers.put(roomId, sdp);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/answer")
    public ResponseEntity<String> getAnswer(@RequestParam(name = "roomId") String roomId) {
        return ResponseEntity.ok(answers.get(roomId));
    }

    @PostMapping("/candidate")
    public ResponseEntity<Void> addCandidate(@RequestParam(name = "roomId") String roomId, @RequestBody Map<String, Object> candidate) {
        candidates.computeIfAbsent(roomId, k -> new ArrayList<>());
        candidates.get(roomId).add(candidate);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/candidates")
    public ResponseEntity<List<Map<String, Object>>> getCandidates(@RequestParam(name = "roomId") String roomId) {
        return ResponseEntity.ok(candidates.getOrDefault(roomId, List.of()));
    }

}
