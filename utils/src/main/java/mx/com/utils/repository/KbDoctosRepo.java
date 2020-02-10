package mx.com.utils.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.utils.entity.KbDoctosEntity;
import mx.com.utils.entity.embedded.KbDoctosPK;

/**
 * The Interface KbDoctosRepo.
 */
public interface KbDoctosRepo extends JpaRepository<KbDoctosEntity, KbDoctosPK> {

}