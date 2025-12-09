package com.kh.final3.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.final3.dto.MessageDto;
import com.kh.final3.service.MessageService;
import com.kh.final3.vo.PageVO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/rest/message")
public class MessageRestController {
	@Autowired
	private MessageService messageService;
	
	// 쪽지 전송
	@PostMapping("/")
	public ResponseEntity<String> sendMessage(
											@RequestBody MessageDto messageDto,
											@RequestAttribute("memberNo") long memberNo){
		
		// 발신자 번호 설정
		messageDto.setSenderNo(memberNo);
		
		boolean success = messageService.sendMessage(messageDto);
		
		if(success) {
			return ResponseEntity.ok("전송 완료");
		} 
		else {
			return ResponseEntity.internalServerError().body("전송 실패");
		}
	}
	
	// 미확인 알림 개수 조회
	@GetMapping("/unread/count")
	public ResponseEntity<Map<String, Object>> getUnreadAlertCount(@RequestAttribute("memberNo") long memberNo) {
		
		int count = messageService.countUnreadAlerts(memberNo);
		
		Map<String, Object> response = new HashMap<>();
        response.put("memberNo", memberNo);
        response.put("unreadCount", count);
        
        return ResponseEntity.ok(response);
	}
	
	@GetMapping("/received")
	public ResponseEntity<List<MessageDto>> getReceivedMessagesByFilter(
						@RequestParam(required = false) List<String> types,
						@RequestAttribute("memberNo") long memberNo) {
		
		List<MessageDto> list;
		
		if(types == null || types.isEmpty()) {
			list = messageService.getReceivedList(memberNo);
		}
		else {
			Map<String, Object> param = new HashMap<>();
			param.put("memberNo", memberNo);
			param.put("typeList", types);
			
			list = messageService.getReceivedListByTypes(param);
		}
		
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("delete/receiver/{messageNo}")
	public ResponseEntity<String> deleteMessageForReceiver(@PathVariable Integer messageNo) {
		
		boolean success = messageService.deleteMessageByReceiver(messageNo);
		
		if(success) {
			return ResponseEntity.ok("삭제 성공");
		} 
		else {
			return ResponseEntity.internalServerError().body("삭제 실패");
		}
	}
	
	//페이징 목록 조회 엔드포인트 추가
	// 1. 수신함 목록 조회
	@GetMapping("/received/page")
    public ResponseEntity<PageVO<MessageDto>> getReceivedListByPaging(
            PageVO<MessageDto> pageVO, // page, size 등 페이징 파라미터 자동 바인딩
            @RequestAttribute("memberNo") long memberNo
    ) {
        // Service의 페이징 메서드 호출
        PageVO<MessageDto> resultVO = messageService.getReceivedListByPaging(pageVO, memberNo);
        
        return ResponseEntity.ok(resultVO);
    }
	
	// 2. 발신함 목록 조회
	@GetMapping("/sent/page")
    public ResponseEntity<PageVO<MessageDto>> getSentListByPaging(
            PageVO<MessageDto> pageVO, // page, size 등 페이징 파라미터 자동 바인딩
            @RequestAttribute("memberNo") long memberNo
    ) {
        // Service의 페이징 메서드 호출
        PageVO<MessageDto> resultVO = messageService.getSentListByPaging(pageVO, memberNo);
        
        return ResponseEntity.ok(resultVO);
    }
	
	// 3. 상세 조회 및 읽음 처리
	@GetMapping("/{messageNo}")
	public ResponseEntity<MessageDto> getMessageDetail(
			@PathVariable Integer messageNo,
	        @RequestAttribute("memberNo") long currentMemberNo // 쪽지 수신자 확인용 (보안 강화)
	) {
	    // 1. 서비스 호출: 상세 조회 및 읽음 처리 트랜잭션 실행
	    MessageDto detail = messageService.getMessageDetailAndRead(messageNo);

	    // 2. 보안 체크 (선택 사항): 해당 쪽지의 수신자/발신자가 맞는지 확인
	    if (detail == null || (detail.getReceiverNo() != currentMemberNo && detail.getSenderNo() != currentMemberNo)) {
	         return ResponseEntity.notFound().build();
	         // 또는 권한 없음 예외 처리 (throw new UnauthorizationException("권한 없음");)
	    }

	    return ResponseEntity.ok(detail);
	}
	
}
