package sec.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sec.project.Address;
import sec.project.InterfDao;
import sec.project.Database;

@Controller
public class SignupController {

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
    public String loadLogin() {
        return "adminLogin";
    }

    @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public ModelAndView getdata() throws SQLException {
        List<Address> list = getList();
        //return back to index.jsp
        ModelAndView model = new ModelAndView("adminPage");

        List<String> liste = new ArrayList<String>();
        liste.add("List A");
        liste.add("List B");


        model.addObject("infos", list);
        model.addObject("lists", liste);

        return model;
    }

    public List<Address> getList() throws SQLException {
        Database database;
        List<String> items = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();
        try {

            database = new Database("jdbc:sqlite:addressess.db");
            database.init();

            InterfDao dao = new InterfDao(database);

            //dao.findAll().stream().forEach(dude -> items.add(dude.getName() + ", " + dude.getAddress()));
            addresses = dao.findAll();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return addresses;
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name,
            @RequestParam String address) throws SQLException {
        Database database;
        List<String> things = new ArrayList<>();
        try {
            database = new Database("jdbc:sqlite:addressess.db");
            database.init();

            InterfDao dao = new InterfDao(database);

            dao.addAddress(name, address);

            dao.findAll().stream().forEach(adrre -> things.add(adrre.getName() + " , " + adrre.getAddress()));

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }

        things.stream().forEach(strink -> System.out.println(strink));

        return "done";
    }

}
