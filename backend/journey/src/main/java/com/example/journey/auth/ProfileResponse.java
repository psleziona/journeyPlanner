package com.example.journey.auth;

import java.util.Set;

record ProfileResponse(String username, Set<String> roles) {
}
