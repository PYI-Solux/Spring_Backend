package com.solux.pyi.pyiplanyouridea.todos.controller;

import com.solux.pyi.pyiplanyouridea.memos.domain.Memos;
import com.solux.pyi.pyiplanyouridea.memos.repository.MemosRepository;
import com.solux.pyi.pyiplanyouridea.todos.dto.TodosSaveRequestDto;
import com.solux.pyi.pyiplanyouridea.todos.dto.TodosUpdateRequestDto;
import com.solux.pyi.pyiplanyouridea.todos.service.TodosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


// 3

// API 요청을 받을 Controller

// DTOs
// > Web Layer
// (controllers, exception handlers,
// filters, view templates, and so on)
// - 흔히 사용하는 컨트롤러(@Controller)와
// JSP/Freemaker 등의 뷰 템플릿 영역
// - 이외에도 필터(@Filter), 인터셉터,
// 컨트롤러 어드바이스(@ControllerAdvice) 등의
// 외부 요청과 응답에 대한 전반적인 영역을 이야기한다.

@RequiredArgsConstructor
// 스프링에서 Bean을 주입받는 방식들 : @Autowired, setter, 생성자
// 이 중 가장 권장하는 방식이 생성자로 주입받는 방식이다.
// (@Autowired는 권장하지 않는다.)
// 즉 생성자로 Bean 객체를 받도록 하면
// @Autowired와 동일한 효과를 볼 수 있다는 것이다.
// 생성자는 @RequiredArgsConstructor에서 해결해 준다.
// final이 선언된 모든 필드를 인자값으로 하는 생성자를
// 롬복의 @RequiredArgsConstructor가 대신 생성해준다.
// 생성자를 직접 안 쓰고 롬복 어노테이션을 사용한 이유 :
// 해당 클래스의 의존성 관계가 변경될 때마다
// 생성자 코드를 계속해서 수정하는 번거로움을 해결하기 위해
// 롬복 어노테이션이 있으면 해당 컨트롤러에 새로운 서비스를 추가하거나,
// 기존 컴포넌트를 제거하는 등의 상황이 발생해도
// 생성자 코드는 전혀 손대지 않아도 된다.
@RestController
// @RestController = @Controller + @ResponseBody
public class TodosController {

    private final TodosService todosService;
    private final MemosRepository memosRepository;

    // 계획 하나 생성
    @PostMapping("/createtodo/{memoUuid}")
    public Long save(@PathVariable Long memoUuid, @RequestBody TodosSaveRequestDto requestDto) {
        Memos memos = memosRepository.getReferenceById(memoUuid);
        return todosService.save(memos, requestDto);
    }

    // 계획 하나 수정
    @PutMapping("/edittodo/{todoUuid}")
    public Long update(@PathVariable Long todoUuid, @RequestBody TodosUpdateRequestDto requestDto) {
        return todosService.update(todoUuid, requestDto);
    }

    // 계획 하나 삭제
    @DeleteMapping("/deletetodo/{todoUuid}")
    public Long delete(@PathVariable Long todoUuid) {
        todosService.delete(todoUuid);
        return todoUuid;
    }



//    // Postman Test
//    // 할 일 하나 조회
//    @GetMapping("/viewtodo/{todoId}")
//    public TodosResponseDto findById(@PathVariable Long todoId) {
//        return todosService.findById(todoId);
//    }

}
