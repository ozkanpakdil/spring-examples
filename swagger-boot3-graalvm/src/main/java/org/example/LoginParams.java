package org.example;

import lombok.Builder;

@Builder
public record LoginParams(String server, String login, String password, String build, String webManager) {
}
