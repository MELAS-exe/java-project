package services;

import entities.AvailableDoc;
import entities.Structure;
import enumerations.DocumentType;
import enumerations.TypeStructure;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.StructureRepository;
import java.util.List;
import java.util.Optional;

@Service
public class StructureService {

    @Autowired
    private StructureRepository structureRepository;

    public Structure addStructure(Structure structure){
        boolean exists = structureRepository.existsByNameIgnoreCaseAndAddress_CityIgnoreCase(
                structure.getName(),
                structure.getAddress().getCity()
        );
        if (exists) {
            throw new RuntimeException("Structure with this name already exists in this city");
        }
        return structureRepository.save(structure);
    }

    public Structure addDocumentToStructure(Long id, AvailableDoc newDoc){
      Structure structure = structureRepository.findById(id).orElseThrow(()-> new RuntimeException("Structure not found"));
      structure.getAvailableDocs().add(newDoc);
      return structureRepository.save(structure);
    }

    public List<Structure> listStructures (){
        return structureRepository.findAll();
    }

    public List<Structure> getStructuresByType(TypeStructure type){
        return structureRepository.findByType(type);
    }

    public List<Structure> getStructuresByAvailableDocsType(DocumentType type){
        return structureRepository.findByAvailableDocsType(type);
    }

    public List<Structure> getStructureByRegionAndCity(String region, String city){
        return structureRepository.findByAddress_RegionIgnoreCaseAndAddress_CityIgnoreCase(region, city);
    }

    public Optional<Structure> getStructureById(Long id){
        return structureRepository.findById(id);
    }
    public List<Structure> getStructureByRegion(String region){
        return structureRepository.findByAddress_RegionIgnoreCase(region);
    }

    public List<Structure> searchByName(String name){
        return structureRepository.findStructureByNameContainingIgnoreCase(name);
    }

    @Transactional
    public  List<AvailableDoc> getAvailableDocsByStructureId(Long id){
        Structure structure = structureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Structure not found"));

        return structure.getAvailableDocs();
    }

    public List<Structure> filterStructures(TypeStructure type, String region, String city) {
        if (type != null && region != null && city != null) {
            return structureRepository.findByTypeAndAddress_RegionIgnoreCaseAndAddress_CityIgnoreCase(type, region, city);
        } else if (type != null && region != null) {
            return structureRepository.findByTypeAndAddress_RegionIgnoreCase(type, region);
        } else if (type != null && city != null) {
            return structureRepository.findByTypeAndAddress_CityIgnoreCase(type, city);
        } else if (region != null && city != null) {
            return structureRepository.findByAddress_RegionIgnoreCaseAndAddress_CityIgnoreCase(region, city);
        } else if (type != null) {
            return structureRepository.findByType(type);
        } else if (region != null) {
            return structureRepository.findByAddress_RegionIgnoreCase(region);
        } else if (city != null) {
            return structureRepository.findByAddress_CityIgnoreCase(city);
        } else {
            return structureRepository.findAll();
        }
    }

    public Structure updateStructure(Long id, Structure updatedStructure){
        Structure currentStructure = structureRepository.findById(id).orElseThrow(() -> new RuntimeException("Structure not found with id"+ id));

        currentStructure.setName(updatedStructure.getName());
        currentStructure.setType(updatedStructure.getType());
        currentStructure.setDescription(updatedStructure.getDescription());
        currentStructure.setContact(updatedStructure.getContact());
        currentStructure.setOpeningHours(updatedStructure.getOpeningHours());
        currentStructure.setAddress(updatedStructure.getAddress());
        currentStructure.setAvailableDocs(updatedStructure.getAvailableDocs());

        return structureRepository.save(currentStructure);
    }

    public void deleteStructureById(Long id){
        structureRepository.deleteById(id);
    }

}
