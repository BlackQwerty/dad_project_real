package com.utm.lostfound.controller;

import com.utm.lostfound.model.Item;
import com.utm.lostfound.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.utm.lostfound.dto.ItemDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.utm.lostfound.dto.LoginRequest;
import com.utm.lostfound.dto.MatchRequest;
import com.utm.lostfound.model.User;
import com.utm.lostfound.repository.UserRepository;
import com.utm.lostfound.util.JwtUtil;

import org.springframework.http.HttpStatus;


import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // allow frontend access
public class ItemController {

    @Autowired
    private ItemRepository itemRepo;

    @PostMapping("/report-lost")
    public ResponseEntity<Item> reportLost(@RequestBody ItemDTO dto) {
        Item item = new Item();
        item.setName(dto.getName());
        item.setCategory(dto.getCategory());
        item.setDescription(dto.getDescription());
        item.setLocation(dto.getLocation());
        item.setDateReported(dto.getDateReported());  // ✅ was dto.getDate_reported()
        item.setImageUrl(dto.getImageUrl());          // ✅ was dto.getImage_url()
        item.setType("lost");
        item.setStatus("pending");
        item.setReportedBy(dto.getReportedBy());      // ✅ was dto.getReported_by()
        return ResponseEntity.ok(itemRepo.save(item));
    }

    @PostMapping("/report-found")
    public ResponseEntity<Item> reportFound(@RequestBody ItemDTO dto) {
        Item item = new Item();
        item.setName(dto.getName());
        item.setCategory(dto.getCategory());
        item.setDescription(dto.getDescription());
        item.setLocation(dto.getLocation());
        item.setDateReported(dto.getDateReported());  // ✅ updated
        item.setImageUrl(dto.getImageUrl());          // ✅ updated
        item.setType("found");
        item.setStatus("pending");
        item.setReportedBy(dto.getReportedBy());      // ✅ updated
        return ResponseEntity.ok(itemRepo.save(item));
    }


    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    @GetMapping("/items/{type}/{status}")
    public List<Item> getByTypeAndStatus(@PathVariable String type, @PathVariable String status) {
        return itemRepo.findByTypeAndStatus(type, status);
    }

    @PatchMapping("/item/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam String status) {
        Optional<Item> item = itemRepo.findById(id);
        if (item.isPresent()) {
            item.get().setStatus(status);
            return ResponseEntity.ok(itemRepo.save(item.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> optionalUser = userRepo.findByUsernameAndPassword(
            request.getUsername(), request.getPassword());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        User user = optionalUser.get();

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("role", user.getRole());
        response.put("username", user.getUsername());

        return ResponseEntity.ok(response);
    }
    
    @Autowired
    private ItemRepository itemRepository;

    // ✅ 1. Get all reports (for admin dashboard table)
    @GetMapping("/reports")
    public List<Item> getAllReports() {
        return itemRepository.findAll();
    }

    // ✅ 2. Match items (update both items as matched)
    @PostMapping("/match")
    public ResponseEntity<?> processMatchItems(@RequestBody MatchRequest match) {
        Item lost = itemRepository.findById(match.getLostId()).orElse(null);
        Item found = itemRepository.findById(match.getFoundId()).orElse(null);

        if (lost != null && found != null) {
            lost.setStatus("Matched");
            found.setStatus("Matched");

            itemRepository.save(lost);
            itemRepository.save(found);

            return ResponseEntity.ok("Items matched successfully");
        }

        return ResponseEntity.badRequest().body("Invalid item IDs");
    }


    // ✅ 3. Mark an item as claimed
    @PutMapping("/claim/{id}")
    public ResponseEntity<?> markItemAsClaimed(@PathVariable Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item != null) {
            item.setStatus("Claimed");
            itemRepository.save(item);
            return ResponseEntity.ok("Item marked as claimed");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
    }

    // ✅ 4. Get stats for dashboard analytics
    @GetMapping("/stats")
    public Map<String, Long> getStatistics() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("total", itemRepository.count());
        stats.put("lost", itemRepository.countByTypeIgnoreCase("Lost"));
        stats.put("found", itemRepository.countByTypeIgnoreCase("Found"));
        stats.put("matched", itemRepository.countByStatusIgnoreCase("Matched"));
        stats.put("claimed", itemRepository.countByStatusIgnoreCase("Claimed"));
        return stats;
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Long>> getSummary() {
        Map<String, Long> summary = new HashMap<>();
        summary.put("totalReports", itemRepository.count());
        summary.put("matchedItems", itemRepository.countByStatusIgnoreCase("Matched"));
        summary.put("lostItems", itemRepository.countByTypeIgnoreCase("Lost"));
        summary.put("foundItems", itemRepository.countByTypeIgnoreCase("Found"));
        return ResponseEntity.ok(summary);
    }
    
    @GetMapping("/dashboard-stats")
    public ResponseEntity<Map<String, Long>> getDashboardStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalReports", itemRepository.count());
        stats.put("matchedItems", itemRepository.countByStatusIgnoreCase("Matched"));
        stats.put("lostItems", itemRepository.countByTypeIgnoreCase("Lost"));
        stats.put("foundItems", itemRepository.countByTypeIgnoreCase("Found"));

        return ResponseEntity.ok(stats);
    }
    
    @PostMapping("/match-items")
    public ResponseEntity<?> matchItems(@RequestBody MatchRequest request) {
        List<Item> foundItems = itemRepo.findByTypeAndStatus("Found", "Unclaimed");
        List<Item> lostItems = itemRepo.findByTypeAndStatus("Lost", "Unclaimed");

        List<Item> matched = new ArrayList<>();
        for (Item lost : lostItems) {
            for (Item found : foundItems) {
                if (lost.getCategory().equalsIgnoreCase(found.getCategory()) &&
                    lost.getLocation().equalsIgnoreCase(found.getLocation())) {
                    matched.add(lost);
                    matched.add(found);
                }
            }
        }
        return ResponseEntity.ok(matched);
    }
 // 1. Lost Items by Category
    @GetMapping("/analytics/lost-by-category")
    public Map<String, Long> getLostByCategory() {
        List<Item> lostItems = itemRepo.findByTypeAndStatus("Lost", "Pending");
        Map<String, Long> result = new HashMap<>();
        for (Item item : lostItems) {
            result.put(item.getCategory(), result.getOrDefault(item.getCategory(), 0L) + 1);
        }
        return result;
    }

    // 2. Lost Items by Location
    @GetMapping("/analytics/lost-by-location")
    public Map<String, Long> getLostByLocation() {
        List<Item> lostItems = itemRepo.findByTypeAndStatus("Lost", "Pending");
        Map<String, Long> result = new HashMap<>();
        for (Item item : lostItems) {
            result.put(item.getLocation(), result.getOrDefault(item.getLocation(), 0L) + 1);
        }
        return result;
    }

    // 3. Matched vs Unmatched Items
    @GetMapping("/analytics/matched-vs-unmatched")
    public Map<String, Long> getMatchedVsUnmatched() {
        long matched = itemRepo.countByStatusIgnoreCase("Matched");
        long pending = itemRepo.countByStatusIgnoreCase("Pending");        
        Map<String, Long> result = new HashMap<>();
        result.put("Matched", matched);
        result.put("Unmatched", pending);
        return result;
    }
    
    @GetMapping("/claimed")
    public List<Item> getClaimedItems() {
        return itemRepository.findByStatusIgnoreCase("claimed");
    }

}
