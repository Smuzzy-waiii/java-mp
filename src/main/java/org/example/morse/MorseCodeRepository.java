package org.example.morse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MorseCodeRepository extends JpaRepository<MorseCodeModel, Long> {
    // You can define additional query methods here if needed
}

