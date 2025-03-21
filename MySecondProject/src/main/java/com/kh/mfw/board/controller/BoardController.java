package com.kh.mfw.board.controller;

import com.kh.mfw.board.model.service.BoardService;

@Slf4j
@Controller
//@RequestMapping("boards")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("boards")
	public String toBoardList() {
		return "board/board_list";
	}
	
	@GetMapping("form.bo")
	public String goToForm() {
		return "board/insert_board";
	}
	
	@PostMapping
	public ModelAndView newBoard(ModelAndView mv, BoardDTO board, MultipartFile upfile) {
		return null;
	}
}
