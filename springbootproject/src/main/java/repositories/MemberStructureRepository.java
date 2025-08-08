package repositories;

import entities.MemberStructure;
import entities.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberStructureRepository extends JpaRepository<MemberStructure,Long> {


    boolean existsByEmail(String email);

    long countByStructure_Id(Long structureId);

    Optional<MemberStructure> findByEmail(String email);
}