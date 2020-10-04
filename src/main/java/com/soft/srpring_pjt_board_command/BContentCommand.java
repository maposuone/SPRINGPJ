package com.soft.srpring_pjt_board_command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.soft.srpring_pjt_board_dao.BDao;
import com.soft.srpring_pjt_board_dto.BDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(Model model) {
		System.out.println("BContentCommand()");
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bId = request.getParameter("bId");
		
		BDao dao = new BDao();
		BDto dto = dao.contentView(bId);

		model.addAttribute("content_view", dto);

	}

}
