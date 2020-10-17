package tech.jaykay12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class APIController {

    @Autowired
    MUIdentifier muIdentifier;

    @Autowired
    ResponseDTO responseDTO;

    @RequestMapping("/")
    public String index(){
        return "Spring BOOT Running!\n";
    }

    @GetMapping("/clean")
    public ResponseDTO identifyMeasurementUnits(@RequestParam(name = "keyword") String keyword) {

        Map<String,String> response = muIdentifier.identifyMU(keyword);
        responseDTO.setCleanedString(response.get("string"));
        responseDTO.setMeasurementUnit(response.get("mu"));

        return responseDTO;
    }
}
