package com.example.springSecurity.controller;

import com.example.springSecurity.dto.JoinDTO;
import com.example.springSecurity.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinP() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO) {
        log.debug("joinDTO username: {}", joinDTO.getUsername());
        joinService.joinProcess(joinDTO);
        return "redirect:/login";
    }
}
