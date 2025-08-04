package services;


import entities.MemberStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.MemberStructureRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberStructureService {

    @Autowired
    private MemberStructureRepository memberStructureRepository;

    public MemberStructure addMembreStructure(MemberStructure memberStructure){
        if (memberStructureRepository.existsByEmail(memberStructure.getEmail())){
            throw new RuntimeException("This email is already used");
        }

        Long structureId = memberStructure.getStructure().getId();
        long count = memberStructureRepository.countByStructure_Id(structureId);

        if (count >= 2) {
            throw new RuntimeException("This structure already has the maximum number of members (2)");
        }

        return memberStructureRepository.save(memberStructure);
    }


    public Optional<MemberStructure> getMemberById(Long id){
        return memberStructureRepository.findById(id);
    }

    public List<MemberStructure> listMembersStructure(){
        return memberStructureRepository.findAll();
    }

    public MemberStructure updateMember(Long memberId, MemberStructure updatedMember){
        MemberStructure thisMember = memberStructureRepository.findById(memberId)
                .orElseThrow(()-> new RuntimeException("Member already exists by this id"));
        thisMember.setEmail(updatedMember.getEmail());
        thisMember.setFirstName(updatedMember.getFirstName());
        thisMember.setLastName(updatedMember.getLastName());
        thisMember.setRole(updatedMember.getRole());
        return memberStructureRepository.save(thisMember);
    }

    public void deleteById(Long id){
        memberStructureRepository.deleteById(id);
    }

}
