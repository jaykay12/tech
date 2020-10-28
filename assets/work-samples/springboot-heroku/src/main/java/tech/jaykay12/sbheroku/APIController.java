package tech.jaykay12.sbheroku;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @RequestMapping("/")
    public String index(){
        return "Spring BOOT Running!\n";
    }
}
