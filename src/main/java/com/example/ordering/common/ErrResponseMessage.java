package com.example.ordering.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ErrResponseMessage {
    public static ResponseEntity<Map<String, Object>>
        makeMessage(HttpStatus status, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", Integer.toString(status.value()));
        map.put("status message", status.getReasonPhrase());
        map.put("error message", message);
        return new ResponseEntity<>(map, status);
    }
}
