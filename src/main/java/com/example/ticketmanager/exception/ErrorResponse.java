package com.example.ticketmanager.exception;

import java.util.List;

public record ErrorResponse(int status, String message, List<String> errors) { }
