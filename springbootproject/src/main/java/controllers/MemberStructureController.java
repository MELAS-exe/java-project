package controllers;

import entities.MemberStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.MemberStructureService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/membres_structures")
public class MemberStructureController {

    @Autowired
    private MemberStructureService memberStructureService;

    //Admin seulement
    @PostMapping
    public MemberStructure addMember(@RequestBody MemberStructure memberStructure){
        return memberStructureService.addMembreStructure(memberStructure);
    }

    //Admin seulement
    @GetMapping
    public List<MemberStructure> listMembers(){
        return memberStructureService.listMembersStructure();
    }

    //Admin seulement
    @GetMapping("/{id}")
    public Optional<MemberStructure> getMember(@PathVariable Long id){
        return memberStructureService.getMemberById(id);
    }

    //Admin et membre structure seulement
    @PutMapping("/{id}")
    public MemberStructure updateMember(@PathVariable Long id, @RequestBody MemberStructure updatedMember){
        return memberStructureService.updateMember(id, updatedMember);
    }

    //Admin seulement
    @DeleteMapping("/{id}")
        public void deleteMember(@PathVariable Long id){
        memberStructureService.deleteById(id);
    }
}