package InknowCorp.Inknow.api;

import InknowCorp.Inknow.domain.Member;
import InknowCorp.Inknow.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

//    @PostMapping("/api/members")
//    public CreateMemberResponse saveMember(@RequestBody @Valid CreateMemberRequest request) {
//        Member member = new Member();
//        member.setHiddenName(request.getName());
//
//        Long id = memberService.join(member);
//        return new CreateMemberResponse(id);
//    }

    @PostMapping("/api/members")
    public CreateMemberResponse saveMember(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setEmail(request.getEmail());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

//    @PutMapping("/api/members/{id}")
//    public UpdateMemberResponse updateMember(@PathVariable("id") Long id,
//                                             @RequestBody @Valid UpdateMemberRequest request) {
//
//    }

    @Data
    static class CreateMemberRequest {
        private String email;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;
        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
