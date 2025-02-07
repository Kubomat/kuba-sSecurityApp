package com.springsecurity.jakob.services;

import com.springsecurity.jakob.models.AuditLog;
import com.springsecurity.jakob.models.Note;

import java.util.List;

public interface AuditLogService {

    void logNoteCreation(String username, Note note);

    void logNoteUpdate(String username, Note note);

    void logNoteDeletion(String username, Long noteId);

    List<AuditLog> getAllAuditLogs();

    List<AuditLog> getAuditLogsForNoteId(Long noteId);

}
