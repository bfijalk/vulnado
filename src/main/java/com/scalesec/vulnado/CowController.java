package com.scalesec.vulnado;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@EnableAutoConfiguration
public class CowController {
    @RequestMapping(value = "/cowsay", method = RequestMethod.GET)
    public String cowsay(@RequestParam(defaultValue = "I love Linux!") String input) {
        return Cowsay.run(input);
    }
    }
}
