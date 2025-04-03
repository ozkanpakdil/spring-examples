package com.example.demo.model;

import lombok.Getter;

@Getter
public enum MaritalStatus {
    NOT_MARRIED("Не женат"),
    DATING("Встречаюсь"),
    ENGAGED("Помолвлен"),
    MARRIED("Женат"),
    COMPLICATED("Все сложно"),
    ACTIVE_SEARCH("В активном поиске");

    private final String label;

    MaritalStatus(String label) {
        this.label = label;
    }
}
