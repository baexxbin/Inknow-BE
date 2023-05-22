package InknowCorp.Inknow.api;

import InknowCorp.Inknow.domain.Member;
import InknowCorp.Inknow.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    // 회원 등록
    @PostMapping("/api/members")
    public CreateMemberResponse saveMember(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setEmail(request.getEmail());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }


    // 공개이름 수정
    /*
     * 회원가입 시 필요한 정보 다 입력받은 후 실행, 현재 information null값으로 인한 오류 발생
     */
    @PutMapping("/api/members/{id}")
    public UpdateMemberResponse updateMember(@PathVariable("id") Long id,
                                             @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id, request.getOpenName());
        Member findMember = memberService.findOneMember(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getInformation().getOpenName());
    }

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

    @Data
    static class UpdateMemberRequest {
        private String openName;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String openName;
    }
}
