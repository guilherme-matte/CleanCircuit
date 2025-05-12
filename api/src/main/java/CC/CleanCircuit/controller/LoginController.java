package CC.CleanCircuit.controller;

import CC.CleanCircuit.services.SenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {


    @Autowired
    private SenhaService loginServiceLogin;
}
