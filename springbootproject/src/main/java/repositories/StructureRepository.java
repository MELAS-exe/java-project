package repositories;

import entities.Structure;
import enumerations.DocumentType;
import enumerations.TypeStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StructureRepository extends JpaRepository<Structure, Long> {

    List<Structure> findByType(TypeStructure type);

    List<Structure> findByAddress_CityIgnoreCase(String addressCity);

    List<Structure> findByAddress_RegionIgnoreCase(String addressRegion);

    List<Structure> findByAvailableDocsType(DocumentType type);

    List<Structure> findStructureByNameContainingIgnoreCase(String name);

    List<Structure> findByAddress_RegionIgnoreCaseAndAddress_CityIgnoreCase(String region, String city);

    List<Structure> findByTypeAndAddress_RegionIgnoreCase(TypeStructure type, String addressRegion);

    List<Structure> findByTypeAndAddress_CityIgnoreCase(TypeStructure type, String addressCity);

    List<Structure> findByTypeAndAddress_RegionIgnoreCaseAndAddress_CityIgnoreCase(TypeStructure type, String region, String city);

    boolean existsByNameIgnoreCaseAndAddress_CityIgnoreCase(String name, String city);

}
