package controllers;

import entities.AvailableDoc;
import entities.Structure;
import enumerations.DocumentType;
import enumerations.TypeStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.StructureService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/structures")
public class StructureController {

    @Autowired
    private StructureService structureService;

    //Admin seulement
    @PostMapping
    public Structure addStructure(@RequestBody  Structure structure){
        return structureService.addStructure(structure);
    }

    //Membre structure seulement
    @PostMapping("/{id}/document")
    public Structure addDocumentToStructure(@PathVariable Long id, @RequestBody  AvailableDoc document){
        return structureService.addDocumentToStructure(id, document);
    }

    //Tout le monde
    @GetMapping
    public List<Structure> listStructures(){
        return structureService.listStructures();
    }

    //Admin seulement
    @GetMapping("/{id}")
    public Optional<Structure> getById(@PathVariable  Long id){
       return structureService.getStructureById(id);
    }

    //Tout le monde
    @GetMapping("/type/{structureType}")
    public List<Structure> getStructuresByType(@PathVariable TypeStructure structureType){
        return structureService.getStructuresByType(structureType);
    }

    //Tout le monde
    @GetMapping("/document/{documentType}")
    public List<Structure> getStructuresByAvailableDocsType(@PathVariable DocumentType documentType){
        return structureService.getStructuresByAvailableDocsType(documentType);
    }

    //Tout le monde
    @GetMapping("/search")
    public List<Structure> searchByName(@RequestParam String name){
        return structureService.searchByName(name);
    }

    //Tout le monde
    @GetMapping("/region/{region}")
    public List<Structure> getStructuresByRegion(@PathVariable String region){
        return structureService.getStructureByRegion(region);
    }

    //Tout le monde
    @GetMapping("/region/{region}/city/{city}")
    public List<Structure> getStructureByRegionAndCity(@PathVariable String region, @PathVariable String city){
        return structureService.getStructureByRegionAndCity(region, city);
    }

    //Tout le monde
    @GetMapping("/available_docs/{id}")
    public List<AvailableDoc> getAvailableDocsByStructureId(@PathVariable Long id){
        return structureService.getAvailableDocsByStructureId(id);
    }

    //Tout le monde
    @GetMapping("/filter")
    public List<Structure> filterStructures(@RequestParam(required = false) TypeStructure type, @RequestParam(required = false) String region, @RequestParam(required = false)String city){
        return structureService.filterStructures(type, region , city);
    }

    //Admin et membre structure seulement
    @PutMapping("/{id}")
    public Structure updateStructure(@PathVariable Long id, @RequestBody Structure structure){
        return  structureService.updateStructure(id, structure);
    }

    //Admin seulement
    @DeleteMapping("/{id}")
    public void deleteStructurebyId(@PathVariable Long id){
        structureService.deleteStructureById(id);
    }

}
