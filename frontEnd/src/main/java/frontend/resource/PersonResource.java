package frontend.resource;

import com.sun.org.apache.xpath.internal.operations.Mod;
import frontend.model.Person;
import frontend.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonResource {
    private PersonService personService;

    public PersonResource(PersonService personService){
        super();
        this.personService = personService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/lista-pessoas")
    public String listaPessoas(Model model){
        Person[] persons = personService.getPersons();
        model.addAttribute("persons", persons);
        return "lista-pessoas";
    }

    @GetMapping("/cadastra-pessoas")
    private String cadastraPessoas(Model model){
        return "cadastra-pessoas";
    }

    @PostMapping(value = "salvar")
    public String save(@RequestParam("name") String name, @RequestParam("age") int age, Model model){
        Person person = new Person(name, age);
        personService.save(person);
        Person[] persons = personService.getPersons();
        model.addAttribute("persons","persons");
        return "/lista-pessoas";
    }

}
