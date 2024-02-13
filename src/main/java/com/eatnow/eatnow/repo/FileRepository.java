package com.eatnow.eatnow.repo;

import com.eatnow.eatnow.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface FileRepository extends JpaRepository<File, Long> {
    // Hier können benutzerdefinierte Methoden für die Datenbankinteraktion hinzugefügt werden
}

