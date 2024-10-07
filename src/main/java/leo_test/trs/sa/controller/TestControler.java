package leo_test.trs.sa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "test")
public class TestControler {

    @GetMapping(path = "string")
    public String getString(){
        return "Hello World";
    }
}
