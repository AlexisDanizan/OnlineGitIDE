package Controller;

import Model.User;
import Service.UserService;
import Service.UserServiceImpl;
import Util.Constantes;
import Util.Status;
import Util.StatusOK;
import Util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger( UserController.class.getName() );
    @PostConstruct
    public void init(){
        userService = new UserServiceImpl();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<User> add(
                                @RequestParam(value="username") String username,
                                @RequestParam(value="mail") String mail,
                                @RequestParam(value="password") String password){
        User user;

        try{
            user = userService.addEntity(mail, username,password);
        }catch(Exception ex){
            LOGGER.log( Level.FINE, ex.toString(), ex);
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<User> get(@RequestParam(value="username") String username,
                                                  @RequestParam(value = "password") String passwd){


        User user;

        try{
            user = userService.authEntity(username,passwd);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<User> get(@RequestParam(value="mail") String mail){
        User user;

        try{
            user = userService.getEntityByMail(mail);
        }catch(Exception ex){
            return new ResponseEntity(Util.convertToJson(new Status(Constantes.OPERATION_CODE_RATE,
                    ex.getMessage())), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(Util.convertToJson(user), HttpStatus.ACCEPTED);
    }


    @RequestMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> getAll(){
        List<User> users;

        try{
            users = userService.getEntityList();
        }catch(Exception ex){
            return new ResponseEntity(Util.convertToJson(new Status(Constantes.OPERATION_CODE_RATE,
                    ex.getMessage())), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(Util.convertListToJson(users), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> remove(@RequestParam(value="mail") String mail){

        try{
            userService.deleteEntity(mail);
        }catch(Exception ex){
            return new ResponseEntity(Util.convertToJson(new Status(Constantes.OPERATION_CODE_RATE,
                    ex.getMessage())), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(Util.convertToJson(new Status(Constantes.OPERATION_CODE_REUSSI,
                Constantes.OPERATION_MSG_REUSSI)), HttpStatus.ACCEPTED);
    }

}
